package com.yjiewei.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 封装请求参数
 */
@Data
@Builder
public class RpcRequest implements Serializable {
    // rpc请求，向服务器发送请求，要让它知道我们到底要什么？
    // 接口名称，方法名，方法参数，参数类型，这些能够确定
    private String interfaceName;
    private String methodName;
    private Object[] parameters;
    private Class<?>[] paramTypes;
}
