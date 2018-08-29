package com.skrezelok.mysensorservice.service.security;


import com.skrezelok.mysensorservice.entity.UserDetails;
import com.skrezelok.mysensorservice.entity.security.User;
import com.skrezelok.mysensorservice.model.security.UserDto;
import com.skrezelok.mysensorservice.repository.UserDetailsRepository;
import com.skrezelok.mysensorservice.repository.security.UserRepository;
import com.skrezelok.mysensorservice.validator.security.EmailExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;


@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    UserDetailsRepository userDetailsR;

    @Transactional
    public User registerNewUserAccount(UserDto accountDto)
            throws EmailExistsException {

        if (emailExist(accountDto.getEmail())) {
            throw new EmailExistsException(
                    "There is an account with that email address: " + accountDto.getEmail());
        }
        User user = new User();
        user.setUsername(accountDto.getUsername());
        user.setPassword(accountDto.getPassword());
        user.setEmail(accountDto.getEmail());
        user.setRoles(Arrays.asList("ROLE_USER"));
        user.setUserDetails(userDetailsR.saveAndFlush(new UserDetails()));
        return repository.save(user);
    }

    private boolean emailExist(String email) {
        User user = repository.findByEmail(email);
        if (user != null) {
            return true;
        }
        return false;
    }
}
