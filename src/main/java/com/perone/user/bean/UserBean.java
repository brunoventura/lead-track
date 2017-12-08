package com.perone.user.bean;

import com.perone.dao.UserDAO;
import com.perone.entity.User;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserBean {

    @Inject
    private UserDAO userDAO;

    public User getUserById(Long userId) {
        User user = userDAO.get(userId);

        return user;
    }

}
