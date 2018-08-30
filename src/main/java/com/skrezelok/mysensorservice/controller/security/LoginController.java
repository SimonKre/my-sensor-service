package com.skrezelok.mysensorservice.controller.security;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.auth.AuthMethod;
import com.nexmo.client.auth.TokenAuthMethod;
import com.nexmo.client.verify.VerifyResult;
import com.skrezelok.mysensorservice.entity.UserDetails;
import com.skrezelok.mysensorservice.entity.security.User;
import com.skrezelok.mysensorservice.model.security.UserDto;
import com.skrezelok.mysensorservice.repository.UserDetailsRepository;
import com.skrezelok.mysensorservice.repository.security.UserRepository;
import com.skrezelok.mysensorservice.service.security.IUserService;
import com.skrezelok.mysensorservice.validator.security.EmailExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;


@Controller
public class LoginController {
    @Autowired
    IUserService userService;
    @Autowired
    private UserRepository ur;
    @Autowired
    private UserDetailsRepository userDetailsR;


    @GetMapping("/login")
    public ModelAndView login(Principal principal) {
        ModelAndView model = new ModelAndView();
        model.addObject("principal", principal);
        model.setViewName("security/login");
        return model;
    }

    @GetMapping("/register")
    public String register(WebRequest request, Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("user", userDto);
        return "security/register";
    }

    @PostMapping("/register")
    public String registerUserAccount(@ModelAttribute("user") @Valid UserDto accountDto, BindingResult result,
                                      WebRequest request, Errors errors, RedirectAttributes redirectAttributes) {

        User registered = new User();

        if (!result.hasErrors()) {
            registered = createUserAccount(accountDto, result);
        }
        if (registered == null) {
            result.rejectValue("email", "message.regError");
        }
        if (result.hasErrors()) {
            return "security/register";
        } else {
            redirectAttributes.addFlashAttribute("msg", "Registration successful!");
            //redirectAttributes.addFlashAttribute("userDetails", registered.getUserDetails());

            return "redirect:/login";
        }
    }

    private User createUserAccount(UserDto accountDto, BindingResult result) {
        User registered = null;
        try {
            registered = userService.registerNewUserAccount(accountDto);
        } catch (EmailExistsException e) {
            return null;
        }
        return registered;
    }

    @GetMapping("/check-user-details")
    public String completeRegistration(Authentication auth, Model model) {
        User user = ur.findByUsername(auth.getName());
        UserDetails userDetails = user.getUserDetails();

        if(userDetails.getPhone() == null  || userDetails.getName() == null ||
                userDetails.getSurname() == null || userDetails.getPhone().length() == 0  ||
                userDetails.getName().length() == 0 || userDetails.getSurname().length() == 0)
        {
            model.addAttribute("userDetails", userDetails);
            model.addAttribute("userName", user.getUsername());
            return "user/edit-details";
        }

        return "redirect:/";
    }

    @PostMapping("/check-user-details")
    public String saveDetails(@ModelAttribute("userDetails") @Valid UserDetails userDetails, BindingResult result,
                              Authentication auth) {
        User user = ur.findByUsername(auth.getName());
        UserDetails previousUserDetails = user.getUserDetails();

        if (user.getUserDetails().getId() == userDetails.getId()) {

            if ((!userDetails.isPhoneVerified() && userDetails.getPhone().length() != 0)
                    || previousUserDetails.getPhone().equals(userDetails.getPhone())) {
                //TODO complete phone verification - js?
//                System.out.println("VERIFING NUMBER");
//                AuthMethod nexmoAuth = new TokenAuthMethod("81916043", "3dJcQrypFPMflJcc");
//                NexmoClient client = new NexmoClient(nexmoAuth);
//                try {
//                    VerifyResult request = client.getVerifyClient().verify(userDetails.getPhone(), "MySensor");
//                    System.out.println(request);
//                    System.out.println(request.getStatus());
//                    System.out.println(request.getErrorText());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } catch (NexmoClientException e) {
//                    e.printStackTrace();
//                }
            }

            userDetailsR.saveAndFlush(userDetails);
        }
        return "redirect:/";
    }

    @GetMapping("/edit-user-details")
    public String editUserDetails(Authentication auth, Model model) {
        User user = ur.findByUsername(auth.getName());
        UserDetails userDetails = user.getUserDetails();

            model.addAttribute("userDetails", userDetails);
            model.addAttribute("userName", user.getUsername());
            return "user/edit-details";
    }

    @PostMapping("/edit-user-details")
    public String saveEditedDetails(@ModelAttribute("userDetails") @Valid UserDetails userDetails, BindingResult result,
                              Authentication auth) {
        User user = ur.findByUsername(auth.getName());

        if (user.getUserDetails().getId() == userDetails.getId()) {
            userDetailsR.saveAndFlush(userDetails);
        }
        return "redirect:/";
    }

}