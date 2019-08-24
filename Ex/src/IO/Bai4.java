package IO;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Bai4 {
    private static Set<String> list = new HashSet<String>();

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
       readData();
       writeData();

    }

}
