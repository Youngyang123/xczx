package com.xuecheng.framework.domain.ucenter.request;

import com.xuecheng.framework.model.request.RequestData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * Created by admin on 2018/3/5.
 */
@Data
@ToString
public class LoginRequest extends RequestData {

    @ApiModelProperty("用户名")
    String username;
    @ApiModelProperty("用户密码")
    String password;
    @ApiModelProperty("校验码")
    String verifycode;

}
