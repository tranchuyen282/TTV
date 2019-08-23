package Thread;

import javax.swing.text.html.HTML;

class Number{
    int i = 1;
    public void inc(){
        i++;
    }
}

class Number2{
    int i = 1;
    synchronized void inc(){
        i++;
    }
}

class Thread1 extends Thread{
    Number number;
    Thread1(Number number){
        this.number = number;
    }
    public void run(){
        number.inc();
        System.out.println("i = " + number.i);
    }
}

class Thread2 extends Thread{
    Number2 number2;
    Thread2(Number2 number2){
        this.number2 = number2;
    }
    public void run(){
        number2.inc();
        System.out.println("j= " + number2.i);
    }

}
public class Bai1 {
    public static void main(String[] args) {
        Number n = new Number();
        Number2 j = new Number2();

        Thread1 t1 = new Thread1(n);
        Thread1 t2 = new Thread1(n);
        Thread1 t3 = new Thread1(n);
        Thread1 t4 = new Thread1(n);
        Thread1 t5 = new Thread1(n);
        Thread1 t6 = new Thread1(n);
        Thread1 t7 = new Thread1(n);
        Thread1 t8 = new Thread1(n);
        Thread1 t9 = new Thread1(n);
        Thread1 t10 = new Thread1(n);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();


        Thread2 j1 = new Thread2(j);
        Thread2 j2 = new Thread2(j);
        Thread2 j3 = new Thread2(j);
        Thread2 j4 = new Thread2(j);
        Thread2 j5 = new Thread2(j);
        Thread2 j6 = new Thread2(j);
        Thread2 j7 = new Thread2(j);
        Thread2 j8 = new Thread2(j);
        Thread2 j9 = new Thread2(j);
        Thread2 j10 = new Thread2(j);

        j1.start();
        j2.start();
        j3.start();
        j4.start();
        j5.start();
        j6.start();
        j7.start();
        j8.start();
        j9.start();
        j10.start();


    }
}
