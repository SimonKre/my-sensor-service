package com.skrezelok.mysensorservice.service.security;


import com.skrezelok.mysensorservice.entity.security.User;
import com.skrezelok.mysensorservice.model.security.UserDto;
import com.skrezelok.mysensorservice.validator.security.EmailExistsException;

public interface IUserService {
    User registerNewUserAccount(UserDto accountDto)
            throws EmailExistsException;
}