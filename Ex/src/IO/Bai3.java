package IO;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Bai3 {
    private static void run() throws IOException {
        FileWriter fw = new FileWriter("bai3.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("Cach 1 \n");
        long s = 0;
        for(int i = 1; i <= 5; i++){
            long time_start = System.currentTimeMillis();
            Bai2.buffer();
            long time_end = System.currentTimeMillis();
            bw.write("Lần " + i + " = " + (time_end - time_start) + "\n");
            s += time_end - time_start;
        }
        bw.write("TB = " + ((float)s/5));
        bw.newLine();
        bw.write("Cách 2: \n");
        s = 0;
        for(int i = 1; i <= 5; i++){
            long time_start = System.currentTimeMillis();
            Bai2.randomAccess(10);
            long time_end = System.currentTimeMillis();
            bw.write("Lần " + i + " = " + (time_end - time_start) + "\n");
            s += time_end - time_start;
        }
        bw.write("TB = " + ((float)s/5));
        bw.newLine();
        bw.write("Cách 3: \n");
        s = 0;
        for(int i = 1; i <= 5; i++){
            long time_start = System.currentTimeMillis();
            Bai2.readByNIO();
            long time_end = System.currentTimeMillis();
            bw.write("Lần " + i + " = " + (time_end - time_start) + "\n");
            s += time_end - time_start;
        }
        bw.write("TB = " + ((float)s/5));

        bw.close();
        fw.close();
    }

    public static void main(String[] args) throws IOException {
        run();
    }
}
