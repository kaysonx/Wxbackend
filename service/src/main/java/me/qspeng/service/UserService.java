package me.qspeng.service;

import me.qspeng.model.Report;
import me.qspeng.model.User;

public interface UserService {
    boolean isUsernameExist(String userName);

    void saveUser(User user);

    User loginQuery(User user) throws Exception;

    User getUserById(String userId);

    void updateUserInfo(User user);

    boolean isUserLikeVideo(String userId, String videoId);

    void addUserFanRelation(String userId, String fanId);

    void deleteUserFanRelation(String userId, String fanId);

    boolean queryIfFollowUser(String userId, String fanId);

    void reportUser(Report userReport);
}
