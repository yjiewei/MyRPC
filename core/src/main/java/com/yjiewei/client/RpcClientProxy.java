package com.yjiewei.client;

import com.yjiewei.entity.RpcRequest;
import com.yjiewei.entity.RpcResponse;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * rpc客户端动态代理，就不用实际生成rpc client对象去进行调用方法
 */
public class RpcClientProxy implements InvocationHandler {
    private String host;
    private int port;

    public RpcClientProxy(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * 获得代理对象
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getProxy(Class<T> clazz){
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]{clazz}, this);
    }

    /**
     * 动态调用方法
     * @param proxy 被代理对象
     * @param method 需要被代理的方法
     * @param args 其他参数
     * @return 返回一个rpc response对象进行远程访问，只传送数据
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理方法的执行....");
        RpcRequest rpcRequest = RpcRequest.builder()
                .interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .parameters(args)
                .paramTypes(method.getParameterTypes())
                .build();
        RpcClient rpcClient = new RpcClient();
        return ((RpcResponse)rpcClient.sendRequest(rpcRequest, host, port)).getData();
    }
}
