package Thread;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.TimeUnit;

class Mess{
    Queue<Integer> mess = new LinkedList<Integer>();
    public void push(int i){
        mess.add(i);
    }
    public void pop(){
        if(mess.size() > 0) {
            System.out.println(mess.poll());
        }
    }
}

class ThreadPush extends Thread{
    Mess mess;
    ThreadPush(Mess mess){
        this.mess = mess;
    }
    public void run(){
        Random rd = new Random();
        mess.push(rd.nextInt());
    }
}

class ThreadPop extends Thread{
    Mess mess;
    ThreadPop(Mess mess){
        this.mess = mess;
    }
    public void run(){
        mess.pop();
    }
}
public class Bai3 {


    public static void main(String[] args) {
        Mess mess = new Mess();
        ThreadPush push = new ThreadPush(mess);
        ThreadPop pop = new ThreadPop(mess);

        while(true){
            push.start();
            pop.start();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            push.stop();
            pop.stop();
            push = new ThreadPush(mess);
            pop = new ThreadPop(mess);
        }
    }
}
