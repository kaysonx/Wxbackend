package me.qspeng.dao;

import me.qspeng.model.User;
import me.qspeng.utils.MyMapper;

public interface UserMapper extends MyMapper<User> {
    /**
     * @Description: 用户受喜欢数累加
     */
    void addReceiveLikeCount(String userId);

    /**
     * @Description: 用户受喜欢数累减
     */
    void reduceReceiveLikeCount(String userId);

    /**
     * @Description: 增加粉丝数
     */
    void addFansCount(String userId);

    /**
     * @Description: 增加关注数
     */
    void addFollowersCount(String userId);

    /**
     * @Description: 减少粉丝数
     */
    void reduceFansCount(String userId);

    /**
     * @Description: 减少关注数
     */
    void reduceFollowersCount(String userId);
}