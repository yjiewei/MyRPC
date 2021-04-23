package com.yjiewei.test;

import com.yjiewei.api.HelloService;
import com.yjiewei.server.RpcServer;

import java.io.IOException;

public class TestServer {

    public static void main(String[] args) throws IOException {
        HelloService helloService = new HelloServiceImpl();
        RpcServer rpcServer = new RpcServer();
        rpcServer.register(helloService, 9000);
    }
}
