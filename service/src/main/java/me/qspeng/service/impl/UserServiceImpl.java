package me.qspeng.service.impl;

import org.n3r.idworker.Sid;
import lombok.var;
import me.qspeng.dao.UserMapper;
import me.qspeng.model.User;
import me.qspeng.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
