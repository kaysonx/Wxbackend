package me.qspeng.dao;

import me.qspeng.model.User;
import me.qspeng.utils.MyMapper;

public interface UserMapper extends MyMapper<User> {

    void addReceivedLikeCount(String userId);

    void reduceReceivedLikeCount(String userId);

    void addFansCount(String userId);

    void addFollowersCount(String userId);

    void reduceFansCount(String userId);

    void reduceFollowersCount(String userId);
}