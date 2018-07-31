package me.qspeng.api.advice;

import com.google.common.base.Strings;
import me.qspeng.api.tool.JwtToken;
import me.qspeng.utils.JSONResult;
import me.qspeng.utils.JsonUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

public class UserIdentifyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userToken = request.getHeader("userToken");
        if (Strings.isNullOrEmpty(userToken) || Strings.isNullOrEmpty(JwtToken.getUserID(userToken))) {
            returnErrorMsg(response);
            return false;
        }
        return true;
    }

    private void returnErrorMsg(HttpServletResponse response) throws IOException {
        response.setContentType("application/json; charset=utf-8");
        PrintWriter responseWriter = response.getWriter();
        responseWriter.print(Objects.requireNonNull(JsonUtils.objectToJson(JSONResult.errorMsg("unauthorized operation!"))));
        responseWriter.close();
        response.flushBuffer();
    }
}
