package com.kukuxer.springboot.demosecurity.dao;

import com.kukuxer.springboot.demosecurity.entity.Role;

public interface RoleDao {
    public Role findRoleByName(String theRoleName);
}
