package sc.udpsender;

import android.app.Activity;
import android.os.Bundle;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class MainActivity extends Activity {
    // UDP广播IP和PORT
    public static final String SERVERIP = "192.168.0.106";
    public static final int SERVERPORT = 9876;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new Client()).start();
    }
    DatagramSocket socket = null;
    public class Client implements Runnable {
        public void run() {
            // 向局域网UDP广播信息：Hello, World!
                try {
                    InetAddress serverAddress = InetAddress.getByName(SERVERIP);
                    System.out.println("Client: Start connecting\n");
                    socket = new DatagramSocket(SERVERPORT);
                    while(true) {
                    byte[] buf = Long.toString(System.currentTimeMillis()).getBytes(); //"Hello, World!".getBytes();
                    DatagramPacket packet = new DatagramPacket(buf, buf.length,
                            serverAddress, SERVERPORT);
                    System.out.println("Client: Sending ‘" +  new String(buf)
                            + "’\n");
                    socket.send(packet);
                    System.out.println("Client: Message sent\n");
                    System.out.println("Client: Succeed!\n");
                    }
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (SocketException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
//            // 接收UDP广播，有的手机不支持
//            while (true) {
//                byte[] recbuf = new byte[255];
//                DatagramPacket recpacket = new DatagramPacket(recbuf,
//                        recbuf.length);
//                try {
//                    socket.receive(recpacket);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("Server: Message received: ‘"
//                        + new String(recpacket.getData()) + "’\n");
//                System.out.println("Server: IP " + recpacket.getAddress()
//                        + "’\n");
//            }
        }
    }
}
