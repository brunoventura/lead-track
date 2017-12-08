package com.perone.dao;

import com.perone.entity.User;

public class UserDAO extends AbstractDAO<User, Long> {

    public UserDAO() {
        super(User.class);
    }

}
