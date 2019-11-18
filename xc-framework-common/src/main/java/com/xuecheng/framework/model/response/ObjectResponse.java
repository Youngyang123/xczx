package com.xuecheng.framework.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ObjectResponse<T> {
    private Integer code;
    private String message;
    private T data;

}
