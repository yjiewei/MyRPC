package com.yjiewei.test;

import com.yjiewei.api.HelloObject;
import com.yjiewei.api.HelloService;
import com.yjiewei.client.RpcClientProxy;

public class TestClient {

    public static void main(String[] args) {
        RpcClientProxy proxy = new RpcClientProxy("127.0.0.1", 9000);
        HelloService helloService = proxy.getProxy(HelloService.class);
        HelloObject object = new HelloObject(12, "This is a message");
        String res = helloService.hello(object); // 当客户端准备要访问接口方法时，代理先执行再调用实际的方法
        System.out.println(res);
    }
}
