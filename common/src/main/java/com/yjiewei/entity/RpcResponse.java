package com.yjiewei.entity;

import com.yjiewei.entity.enumeration.ResponseCode;
import lombok.Data;

import java.io.Serializable;

/**
 * 封装响应参数
 * @param <T>
 */
@Data
public class RpcResponse<T> implements Serializable {
    private Integer statusCode;
    private String message;
    private T data;

    public static <T> RpcResponse<T> success(T data){
        RpcResponse<T> response = new RpcResponse<>();
        response.setStatusCode(ResponseCode.SUCCESS.getCode());
        response.setData(data);
        System.out.println(response.toString());
        return response;
    }

    public static <T> RpcResponse<T> fail(ResponseCode code) {
        RpcResponse<T> response = new RpcResponse<>();
        response.setStatusCode(code.getCode());
        response.setMessage(code.getMessage());
        return response;
    }
}
