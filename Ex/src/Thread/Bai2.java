package Thread;

import IO.Bai1;

import java.io.File;

class CheckFile{
    boolean check;
    File file = new File("config.xml");
    long time_old = file.lastModified();
    long time_new = file.lastModified();
    public void checkFile(){
        synchronized (this){
            System.out.println("Check edit");
            while(true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                time_new = file.lastModified();
                if(time_new != time_old){
                    check = true;
                    time_old = time_new;
                }
                while(check){
                    try{
                        System.out.println("File edited, show file:");
                        wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                check = false;
                notify();
            }
        }
    }
    public void show(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        synchronized (this){
            while (true){
                while (!check){
                    try{
                        System.out.println("Continue check edit");
                        wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                Bai1.read3();
                check = false;
                notify();
            }
        }
    }
}

public class Bai2 {
    public static void main(String[] args) {
        CheckFile c = new CheckFile();
        c.check = false;
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                c.checkFile();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                c.show();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
