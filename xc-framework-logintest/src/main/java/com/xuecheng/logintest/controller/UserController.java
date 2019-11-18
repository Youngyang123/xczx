package com.xuecheng.logintest.controller;

import com.xuecheng.api.authtest.UserControllerApi;
import com.xuecheng.framework.domain.ucenter.request.LoginRequest;
import com.xuecheng.framework.domain.ucenter.response.AuthCode;
import com.xuecheng.framework.domain.ucenter.response.LoginResult;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.ObjectResponse;
import com.xuecheng.framework.model.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController implements UserControllerApi {
    // http://localhost:8888/user/login
    @PostMapping("login")
    public LoginResult login(@RequestBody LoginRequest loginRequest) {
        log.debug(loginRequest.toString());
        if (!"admin".equals(loginRequest.getUsername()) && !"123456".equals(loginRequest.getPassword())) {
            //
            return new LoginResult(AuthCode.AUTH_CREDENTIAL_ERROR, null);
        }
        String token = "admin-token";
        return new LoginResult(CommonCode.SUCCESS, token);
    }

    @GetMapping("info/{token}")
    public ObjectResponse<Map<String, String>> info(@PathVariable String token) {
        if (!"admin-token".equals(token)) {
            new ObjectResponse<>(20000, "token错误", null);
        }
        Map<String, String> map = new HashMap<>();
        map.put("name", "young");
        map.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return new ObjectResponse<>(10000, "success", map);
    }

    @PostMapping("logout")
    public ResponseResult logout(@RequestBody String token) {
        log.debug(token);
        if (!"admin-token".equals(token)) {
            return ResponseResult.SUCCESS();
        }

        return ResponseResult.FAIL();
    }
}
