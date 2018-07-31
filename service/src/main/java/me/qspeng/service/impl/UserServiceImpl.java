package me.qspeng.service.impl;

import me.qspeng.utils.MD5Utils;
import org.n3r.idworker.Sid;
import lombok.var;
import me.qspeng.dao.UserMapper;
import me.qspeng.model.User;
import me.qspeng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private Sid sid;

    @Autowired
    private UserMapper userMapper;

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
        var criteria =  userExample.createCriteria();
        criteria.andEqualTo("id", user.getId());
        userMapper.updateByExampleSelective(user, userExample);
    }
}
