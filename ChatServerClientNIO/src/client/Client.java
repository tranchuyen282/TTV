package client;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {
    private static final int BUFFER_SIZE = 1024;
    private static String[] messages =
    {"The best way to predict the future is to create it.",
    "As you think, so shall you become.",
    "The noblest pleasure is the joy of understanding.",
    "Courage is grace under pressure.",
    "*exit*"};

    public static void main(String[] args) {

        System.out.println("Starting Client...");
        try {
            int port = 8888;
            InetAddress hostIP = InetAddress.getLocalHost();
            InetSocketAddress myAddress =
                    new InetSocketAddress(hostIP, port);
            SocketChannel myClient = SocketChannel.open(myAddress);

            System.out.println("Trying to connect to " +
                    myAddress.getHostName()+":"+ myAddress.getPort());

            for (String msg: messages) {
                ByteBuffer myBuffer=ByteBuffer.allocate(BUFFER_SIZE);
                myBuffer.put(msg.getBytes());
                myBuffer.flip();
                int bytesWritten = myClient.write(myBuffer);
                System.out.println("Sending Message: "+ msg+ "\nbytesWritten: "+ bytesWritten);
            }
            System.out.println("Closing Client connection...");
            myClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
