package io;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServerMulticastSocketTest {
    /**
     * 2014-1-9 下午3:22:01
     * @param args
     *
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        MulticastSocket multicastSocket;
        try {
            multicastSocket = new MulticastSocket();
            InetAddress address = InetAddress.getByName("239.0.0.1"); // 必须使用D类地址
            multicastSocket.joinGroup(address); // 以D类地址为标识，加入同一个组才能实现广播

            while (true) {
                String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                byte[] buf = time.getBytes();
                DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length);
                datagramPacket.setAddress(address); // 接收地址和group的标识相同
                datagramPacket.setPort(10000); // 发送至的端口号

                multicastSocket.send(datagramPacket);
                Thread.sleep(1000);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
