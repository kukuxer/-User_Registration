package com.kukuxer.springboot.demosecurity.dao;

import com.kukuxer.springboot.demosecurity.entity.User;
import org.springframework.transaction.annotation.Transactional;

public interface UserDao {
    User findByUserName(String userName);

    void save(User theUser);
}
