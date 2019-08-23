package IO;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Random;

public class Bai2 {

    public static void sinhFile() throws IOException {
        PrintWriter pw = new PrintWriter(new File("data.txt"));
        Random rd = new Random();
        for(int i = 1; i <= 1000000; i++){
            //System.out.println(rd.nextInt());
            pw.println(rd.nextInt());
        }
        pw.close();

    }

    public static void buffer() throws IOException {
        FileReader fr = new FileReader("data.txt");
        BufferedReader bw = new BufferedReader(fr);
        int cs = 0;
        String tmp;
        while((tmp = bw.readLine()) != null){
            ++cs;
            if(cs >= 999991 && cs <= 1000000){
                System.out.println(tmp);
            }
        }
        bw.close();
        fr.close();
    }

    public static void randomAccess(int n){
        File file = new File("data.txt");
        StringBuilder builder = new StringBuilder();
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile("data.txt", "r");
            long pos = file.length() - 1;
            randomAccessFile.seek(pos);
            for (long i = pos - 1; i >= 0; i--) {
                randomAccessFile.seek(i);
                char c = (char) randomAccessFile.read();
                if (c == '\n') {
                    n--;
                    if (n == 0) {
                        break;
                    }
                }
                builder.append(c);
            }
            builder.reverse();
           System.out.println(builder.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // su dung NIO

    public static void readByNIO() throws IOException {
        //StringBuilder builder = new StringBuilder();
        File file = new File("data.txt");
        RandomAccessFile aFile = new RandomAccessFile(file, "r");
        FileChannel inChannel = aFile.getChannel();
        long pos = file.length();
        inChannel.position(pos - 108);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (inChannel.read(buffer) > 0) {
            buffer.flip();
            for (int i = 0; i < buffer.limit(); i++) {
                System.out.print((char)buffer.get());
            }
            buffer.clear();
        }
        inChannel.close();
        aFile.close();

    }

    public static void main(String[] args) throws IOException {
        //sinhFile();
        System.out.println("đọc 10 dòng cuối dùng BufferReader");
//        buffer();
        System.out.println("Sử dụng RandomAccess:");
        randomAccess(10);
        System.out.println("Sử dụng RandomAccess + NIO:");
        readByNIO();
    }
}
