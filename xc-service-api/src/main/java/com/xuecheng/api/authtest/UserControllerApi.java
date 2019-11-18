package com.xuecheng.api.authtest;

import com.xuecheng.framework.domain.ucenter.request.LoginRequest;
import com.xuecheng.framework.domain.ucenter.response.LoginResult;
import com.xuecheng.framework.model.response.ObjectResponse;
import com.xuecheng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Api(value="用户权限验证",description = "用户登陆/获取用户信息/退出")
public interface UserControllerApi {
    @ApiOperation("登陆")
    LoginResult login(LoginRequest loginRequest);

    @ApiOperation("获取用户信息")
    @ApiImplicitParam(name="token", value = "验证用户的令牌", required = true, paramType = "path")
    ObjectResponse<Map<String, String>> info(String token);

    @ApiOperation("用户退出")
    @ApiImplicitParam(name="token", value = "对应用户的令牌", required = true, paramType = "path")
    ResponseResult logout(@RequestBody String token);
}
