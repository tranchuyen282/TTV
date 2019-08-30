package IO;

import java.io.*;
import java.util.*;

public class Bai4 {
    private static Set<String> list = new HashSet<String>();
    private static void sinhFile(){
        try {
            FileWriter fw = new FileWriter("cdr.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            Random rd = new Random();
            for(int i = 1; i <= 2000000; i++){
                int cs = rd.nextInt(7);
                int num7 = rd.nextInt(9999999);
                String t = "";
                switch (cs){
                    case 1:{
                        t = "84123";
                        t += String.valueOf(num7);
                        for(int j = 1; j <= 3; j++){
                            t += ",";
                            t += String.valueOf(rd.nextInt(1000));
                        }
                        break;
                    }
                    case 2:{
                        t = "84124";
                        t += String.valueOf(num7);
                        for(int j = 1; j <= 3; j++){
                            t += ",";
                            t += String.valueOf(rd.nextInt(1000));
                        }
                        break;
                    }
                    case 3:{
                        t = "84125";
                        t += String.valueOf(num7);
                        for(int j = 1; j <= 3; j++){
                            t += ",";
                            t += String.valueOf(rd.nextInt(1000));
                        }
                        break;
                    }
                    case 4:{
                        t = "84127";
                        t += String.valueOf(num7);
                        for(int j = 1; j <= 3; j++){
                            t += ",";
                            t += String.valueOf(rd.nextInt(1000));
                        }
                        break;
                    }
                    case 5:{
                        t = "84129";
                        t += String.valueOf(num7);
                        for(int j = 1; j <= 3; j++){
                            t += ",";
                            t += String.valueOf(rd.nextInt(1000));
                        }
                        break;

                    }
                    case 6:{
                        t = "8491";
                        t += String.valueOf(num7);
                        for(int j = 1; j <= 3; j++){
                            t += ",";
                            t += String.valueOf(rd.nextInt(1000));
                        }
                        break;
                    }
                    case 0:{
                        for(int j = 1; j <= 4; j++){
                            t += String.valueOf(rd.nextInt(1000));
                            t += ",";
                        }
                        break;
                    }
                }
                bw.write(t + "\n");
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // đọc,định dạng + lọc;
    public static void readData() throws IOException {
        FileReader fr = new FileReader("cdr.txt");
        BufferedReader br = new BufferedReader(fr);
        String t = null;
        while ((t = br.readLine()) != null){
            String arr[] = t.split(",");
            if((arr[0].length() == 11 || arr[0].length() == 12) && (arr[0].substring(0,2).equals("84"))){
                if(arr[0].substring(0,5).equals("84123")){
                    list.add("8483" + arr[0].substring(5,arr[0].length()));
                }
                else if(arr[0].substring(0,5).equals("84124")){
                    list.add("8484" + arr[0].substring(5,arr[0].length()));
                }
                else if(arr[0].substring(0,5).equals("84125")){
                    list.add("8485" + arr[0].substring(5,arr[0].length()));
                }
                else if(arr[0].substring(0,5).equals("84127")){
                    list.add("8481" + arr[0].substring(5,arr[0].length()));
                }
                else if(arr[0].substring(0,5).equals("84129")){
                    list.add("8482" + arr[0].substring(5,arr[0].length()));
                }
                else{
                    list.add(arr[0]);
                }
            }
        }
        br.close();
        fr.close();
    }

    // sort + ghi file
    public static void writeData() throws IOException {
        //sort
        TreeSet sortedList = new TreeSet<String>(list);
        //ghi file:
        FileWriter fw = new FileWriter("data.filter.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        while(sortedList.size() > 0){
            bw.write(sortedList.pollFirst() +"\n");
        }
        bw.close();
        fw.close();
    }

    public static void main(String[] args) throws IOException {
        //sinhFile();
        long ts = System.currentTimeMillis();
        readData();
        long te = System.currentTimeMillis();
        System.out.println("Time doc " + (te - ts));
        writeData();
        ts = System.currentTimeMillis();
        System.out.println("Time ghi " + (ts - te));


    }

}
