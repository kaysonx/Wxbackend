package me.qspeng.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.var;
import me.qspeng.api.tool.JwtToken;
import me.qspeng.api.vo.UserVO;
import me.qspeng.model.User;
import me.qspeng.service.UserService;
import me.qspeng.utils.JSONResult;
import me.qspeng.utils.MD5Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Basic user operate", tags = {"loginAndRegist"})
@RequestMapping("/user")
public class LoginAndRegisterController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ApiOperation(value = "register", notes = "register with username and password")
    public JSONResult register(@RequestBody User user) throws Exception {

        if (checkUserBasicInfo(user)) {
            return JSONResult.errorMsg("Username or password can't be empty");
        }

        boolean isUsernameExist = userService.isUsernameExist(user.getUsername());

        if (!isUsernameExist) {
            user.setPassword(MD5Utils.getMD5Str(user.getPassword()));
            user.setDefault();
            userService.saveUser(user);
        } else {
            return JSONResult.errorMsg("Username Already Exists, Pls Try Again.");
        }

        var userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        userVO.setUserToken(JwtToken.createToken(userVO.getId()));
        return JSONResult.ok(userVO);
    }

    @PostMapping("/login")
    @ApiOperation(value = "login", notes = "login with username and password")
    public JSONResult login(@RequestBody User user) throws Exception {

        if (checkUserBasicInfo(user)) {
            return JSONResult.errorMsg("Username or password can't be empty");
        }

        var queryUser = userService.loginQuery(user);

        if (queryUser == null) {
            return JSONResult.errorMsg("Username or password not match.");
        }

        var userVO = new UserVO();
        BeanUtils.copyProperties(queryUser, userVO);
        userVO.setUserToken(JwtToken.createToken(userVO.getId()));
        return JSONResult.ok(userVO);
    }

    private boolean checkUserBasicInfo(User user) {
        return StringUtils.isBlank(user.getUsername()) || StringUtils.isBlank(user.getPassword());
    }
}
