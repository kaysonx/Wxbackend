package me.qspeng.api.controller;

import com.google.common.base.Strings;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.var;
import me.qspeng.api.tool.JwtToken;
import me.qspeng.api.vo.UserVO;
import me.qspeng.model.User;
import me.qspeng.service.UserService;
import me.qspeng.utils.JSONResult;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/user")
@Api(value = "User Operation", tags = "user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    @ApiOperation(value = "Get user info by Id")
    @ApiImplicitParam(name = "userId", value = "userId", required = true,
            dataType = "string", paramType = "path")
    public JSONResult getUserInfo(@PathVariable String userId) {
        if (Strings.isNullOrEmpty(userId)) {
            return JSONResult.errorMsg("userId cannot be empty.");
        }

        var user = userService.getUserById(userId);
        if (user == null) {
            return JSONResult.errorMsg("error userId.");
        }
        var userVo = new UserVO();
        BeanUtils.copyProperties(user, userVo);
        return JSONResult.ok(userVo);
    }

    @PostMapping("/uploadFace")
    @ApiOperation(value = "user upload face img")
    public JSONResult uploadFaceImg(@RequestParam("file") MultipartFile[] files, @RequestHeader("userToken") String userToken) throws IOException {
        if (Strings.isNullOrEmpty(userToken)) {
            return JSONResult.errorTokenMsg("unauthorized operation!");
        }
        String userId = JwtToken.getUserID(userToken);

        FileOutputStream fileOutputStream = null;
        InputStream inputStream;
        String uploadDBPath = "";

        try {
            if (files == null || files.length <= 0) {
                return JSONResult.errorMsg("no file selected!");
            }
            String fileName = files[0].getOriginalFilename();
            if (!Strings.isNullOrEmpty(fileName)) {
                final String fileSpace = "/Users/qspeng/wx-videos";
                uploadDBPath = "/" + userId + "/face/" + fileName;
                String finalFaceImgPath = fileSpace + uploadDBPath;

                File outFile = new File(finalFaceImgPath);
                if(outFile.getParentFile() == null || !outFile.getParentFile().isDirectory()) {
                    //mk face folder
                    outFile.getParentFile().mkdirs();
                }

                fileOutputStream = new FileOutputStream(outFile);
                inputStream = files[0].getInputStream();
                IOUtils.copy(inputStream, fileOutputStream);
            }
        } catch (Exception e){
            e.printStackTrace();
            return JSONResult.errorMsg("upload error.");
        } finally {
            if(fileOutputStream != null) {
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        }

        var user = new User();
        user.setId(userId);
        user.setFaceImage(uploadDBPath);
        userService.updateUserInfo(user);

        return JSONResult.ok(uploadDBPath);
    }

}
