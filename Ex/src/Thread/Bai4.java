package Thread;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

class Thread4 implements Runnable{

    @Override
    public void run() {
        try {
            IO.Bai4.readData();
            IO.Bai4.writeData();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

public class Bai4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap so luong thread");
        int n = sc.nextInt();
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(n);
        Thread4 t = new Thread4();
        threadPoolExecutor.execute(t);
        threadPoolExecutor.shutdown();
    }

}
