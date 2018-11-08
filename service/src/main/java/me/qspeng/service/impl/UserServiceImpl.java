package me.qspeng.service.impl;

import lombok.var;
import me.qspeng.dao.LikedFanMapper;
import me.qspeng.dao.LikedVideoMapper;
import me.qspeng.dao.ReportMapper;
import me.qspeng.dao.UserMapper;
import me.qspeng.model.LikedFan;
import me.qspeng.model.LikedVideo;
import me.qspeng.model.Report;
import me.qspeng.model.User;
import me.qspeng.service.UserService;
import me.qspeng.utils.MD5Utils;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private Sid sid;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LikedFanMapper likedFanMapper;

    @Autowired
    private LikedVideoMapper likedVideoMapper;

    @Autowired
    private ReportMapper reportMapper;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean isUsernameExist(String userName) {
        var user = new User();
        user.setUsername(userName);
        return userMapper.selectOne(user) != null;
    }

    @Override
    public void saveUser(User user) {
        user.setId(sid.nextShort());
        userMapper.insert(user);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User loginQuery(User user) throws Exception {
        var userExample = new Example(User.class);
        var criteria = userExample.createCriteria();
        criteria.andEqualTo("username", user.getUsername());
        criteria.andEqualTo("password", MD5Utils.getMD5Str(user.getPassword()));
        return userMapper.selectOneByExample(userExample);
    }

    @Override
    public User getUserById(String userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public void updateUserInfo(User user) {
        var userExample = new Example(User.class);
        var criteria = userExample.createCriteria();
        criteria.andEqualTo("id", user.getId());
        userMapper.updateByExampleSelective(user, userExample);
    }

    @Override
    public boolean isUserLikeVideo(String userId, String videoId) {
        var example = new Example(LikedVideo.class);
        var criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        criteria.andEqualTo("videoId", videoId);

        var list = likedVideoMapper.selectByExample(example);
        return list != null && list.size() > 0;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addUserFanRelation(String userId, String fanId) {
        String id = sid.nextShort();

        LikedFan likedFan = new LikedFan();
        likedFan.setId(id);
        likedFan.setUserId(userId);
        likedFan.setFanId(fanId);

        likedFanMapper.insert(likedFan);
        userMapper.addFansCount(userId);
        userMapper.addFollowersCount(fanId);
    }

    @Override
    public void deleteUserFanRelation(String userId, String fanId) {
        var example = new Example(LikedFan.class);
        var criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        criteria.andEqualTo("fanId", fanId);

        likedFanMapper.deleteByExample(example);
        userMapper.reduceFansCount(userId);
        userMapper.reduceFollowersCount(fanId);
    }

    @Override
    public boolean queryIfFollowUser(String userId, String fanId) {
        var example = new Example(LikedFan.class);
        var criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        criteria.andEqualTo("fanId", fanId);

        var list = likedFanMapper.selectByExample(example);
        return list != null && list.size() > 0;
    }

    @Override
    public void reportUser(Report userReport) {
        String id = sid.nextShort();
        userReport.setId(id);
        userReport.setCreateDate(new Date());
        reportMapper.insert(userReport);
    }
}
