package com.yjiewei.client;

import com.yjiewei.entity.RpcRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

// 远程方法调用的消费者，也就是客户端
public class RpcClient {
    private static final Logger logger = LoggerFactory.getLogger(RpcClient.class);

    public Object sendRequest(RpcRequest rpcRequest, String host, int port){
        try (Socket socket = new Socket(host, port)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream.writeObject(rpcRequest); // 写出去然后刷新
            objectOutputStream.flush();
            return objectInputStream.readObject(); // 返回输入流读到的对象，用流来实现网络传输
        }catch (Exception e){
            logger.error("调用时有出现错误", e);
            return null;
        }
    }
}
