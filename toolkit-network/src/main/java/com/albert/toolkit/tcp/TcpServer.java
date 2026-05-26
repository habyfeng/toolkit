package com.albert.toolkit.tcp;

import com.albert.data.DemoReqVo;
import com.albert.data.DemoRspVo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author losfoo
 * @since 2026-05-26
 */
public class TcpServer {

    private static final int PORT = 16886;

    private static final int BACK_LOG = 1000;

    private DemoService demoService = new DemoService();

    public void start() throws IOException, ClassNotFoundException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        ServerSocket serverSocket = new ServerSocket(PORT, BACK_LOG, inetAddress);
        while (true) {
           Socket socket = serverSocket.accept();
           System.out.println("收到连接");
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            Object object = ois.readObject();
            demoService.register((DemoReqVo) object);

            DemoRspVo demoRspVo = new DemoRspVo();
            System.out.println("处理完毕");
            demoRspVo.rspCode = 1;
            demoRspVo.rspMsg = "Success";

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(demoRspVo);
            objectOutputStream.flush();
        }
    }
}
