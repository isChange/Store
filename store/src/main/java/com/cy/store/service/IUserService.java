package com.cy.store.service;

import com.cy.store.entity.User;

public interface IUserService {

    void insert(User user);

    User login(String username,String password);

    void updateForPassword(Integer uid , String username,
                           String oldPassword, String newPassword);

    User findUserByUid (Integer uid);

    void updateForUserResource(Integer uid , String modifiedUser, User user);

    void updateUserAvatar(Integer uid , String avatar , String username);

}
