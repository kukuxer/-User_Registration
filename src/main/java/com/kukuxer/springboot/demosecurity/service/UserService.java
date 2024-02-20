package com.kukuxer.springboot.demosecurity.service;

import com.kukuxer.springboot.demosecurity.entity.User;
import com.kukuxer.springboot.demosecurity.user.WebUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public User findByUserName(String userName);
    void save(WebUser webUser);
}
