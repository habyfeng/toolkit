package com.albert.toolkit.tcp;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author losfoo
 * @since 2026-05-26
 */
public class TcpClient {
    private static final int PORT = 16886;

    public void send(Object sendObj) throws IOException, ClassNotFoundException {
        Socket socket = new Socket(InetAddress.getLocalHost().getHostAddress(), PORT);
        System.out.println("开始发送: " + sendObj);

        ObjectOutputStream objectOutputStream =  new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(sendObj);
        System.out.println("发送完毕");

        try (ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream())) {
            Object result = objectInputStream.readObject();
            System.out.println("收到结果: " + result);
        }
    }
}
