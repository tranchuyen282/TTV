package Thread;

import javax.jws.soap.SOAPBinding;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


class Mess{
    boolean check;
    Queue<Integer> msg = new LinkedList<Integer>();
    Random rd = new Random();
    public void pushMsg(){
        synchronized (this){
            System.out.println("Push msg");
            while(true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int t = rd.nextInt(1500);
                msg.add(t);
                if (msg.size() > 0) {
                    System.out.println("Thread - 1 push msg = " + t);
                    check = true;
                }
                while(check){
                    try{
                        System.out.print("Thread - 2 show msg: ");
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
    public void showMsg(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        synchronized (this){
            while (true){
                while (!check){
                    try{
                        System.out.println("Continue push");
                        wait();
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                if (msg.size()>0){
                    System.out.println(msg.poll());
                    check = false;
                }

                notify();
            }
        }
    }


}


public class Bai3 {


    public static void main(String[] args) {
        Mess c = new Mess();

        c.check = false;
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                c.pushMsg();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                c.showMsg();
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
