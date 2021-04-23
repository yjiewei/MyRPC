package com.yjiewei.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * 远程方法调用的提供者 服务端
 */
public class RpcServer {
    private final ExecutorService threadPool;
    private static final Logger logger = LoggerFactory.getLogger(RpcServer.class);

    // 利用线程池来提高连接使用率
    public RpcServer() {
        int corePoolSize = 5;
        int maximumPoolSize = 50;
        long keepAliveTime = 60;
        BlockingQueue<Runnable> workingQueue = new ArrayBlockingQueue<>(100);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workingQueue, threadFactory);
    }

    // 将service注册到port
    public void register(Object service, int port) throws IOException {
        try(ServerSocket serverSocket = new ServerSocket(port)){
            logger.info("服务器正在启动.......");
            Socket socket;
            while((socket = serverSocket.accept()) != null){
                logger.info("客户端连接！IP为" + socket.getInetAddress());
                threadPool.execute(new WorkerThread(socket, service)); // 这个才是真正的处理请求，网络传输
            }
        }catch (IOException e){
            logger.error("连接时有错误发生：", e);
        }
    }
}
