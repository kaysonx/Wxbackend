package me.qspeng.service;

import me.qspeng.model.User;

public interface UserService {
    boolean isUsernameExist(String userName);

    void saveUser(User user);

    User loginQuery(User user) throws Exception;

    User getUserById(String userId);

    void updateUserInfo(User user);
}
