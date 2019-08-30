import java.io.*;
import java.sql.*;

public class Ex {
    private static Connection con;

    private static Connection getConnection() {
        Connection con = null;
        if(con == null){
            String dbUrl = "jdbc:mysql://localhost:3306/ttv_sdt";
            String dbClass = "com.mysql.jdbc.Driver";
            try{
                Class.forName(dbClass);
                con = DriverManager.getConnection(dbUrl,"root","27281997");
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return con;

    }


    private static void ghi(){

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("E:\\TTV\\TTV\\Ex\\data.filter2.txt");
            DataOutputStream out = new DataOutputStream(fileOutputStream);
            FileInputStream fileInputStream = new FileInputStream("E:\\TTV\\TTV\\Ex\\data.filter.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream,"UTF-8"));
            String t = null;
            int count = 0;
            while((t = br.readLine()) != null){
                ++count;
                out.writeBytes(t + "\n");
                out.flush();
                if(count == 50006){
                    break;
                }
            }

            out.close();
            fileOutputStream.close();
            br.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private static void creatTable(){
        String _creatTable = "CREATE TABLE `msisdn` (\n" +
                "`msisdn` varchar(20) NOT NULL\n" +
                ");\n";
        try{
            Statement statement = con.createStatement();
            statement.execute(_creatTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void insertData(){
        truncateTable();
        String _insert = "INSERT INTO msisdn (msisdn) VALUE (?)";
        try {
            PreparedStatement psInsert = con.prepareStatement(_insert);
            FileInputStream fileInputStream = new FileInputStream("E:\\TTV\\TTV\\Ex\\data.filter2.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream,"UTF-8"));
            String t = null;
            while((t = br.readLine()) != null){
                psInsert.setString(1,t);
                psInsert.execute();
            }
            br.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void truncateTable(){
        String _truncate = "TRUNCATE TABLE msisdn";
        try {
            Statement statement = con.createStatement();
            statement.executeUpdate(_truncate);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void selectData(){
        String sql = "SELECT * FROM `ttv_sdt`.`msisdn` LIMIT 9998,10000";
        try {
            PreparedStatement ps  = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            FileOutputStream fo = new FileOutputStream("data10000.txt");
            DataOutputStream out = new DataOutputStream(fo);
            String s;
            while(rs.next()){
                s = rs.getString("msisdn");
                out.writeBytes(s + "\n");
                out.flush();
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

    }

    private static void insertForLine(int line){
        String _insert = "INSERT INTO msisdn (msisdn) VALUE (?)";
        try {
            con.setAutoCommit(false);
            PreparedStatement psInsert = con.prepareStatement(_insert);
            FileInputStream fileInputStream = new FileInputStream("E:\\TTV\\TTV\\Ex\\data.filter2.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream,"UTF-8"));
            String t = null;
            int count = 0;
            while((t = br.readLine()) != null){
                ++count;
                psInsert.setString(1,t);
                psInsert.addBatch();
                if(count == line){
                    psInsert.executeLargeBatch();
                    count = 0;
                }
            }
            if(count != 0)
                psInsert.executeLargeBatch();
            con.commit();
            br.close();
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void timeOfInsert(){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("TimeOfInsert.txt");
            DataOutputStream out = new DataOutputStream(fileOutputStream);
            out.writeBytes("Cách 1: \n");
            long totalTime = 0, time_start = 0, time_end = 0;
            for(int i = 1; i <= 5; i++){
                System.out.println("Cách 1 lần " + i);
                truncateTable();
                time_start = System.currentTimeMillis();
                insertData();
                time_end = System.currentTimeMillis();
                out.writeBytes("Lần " + i + " = " + (time_end - time_start) + " ms\n");
                totalTime += (time_end - time_start);
            }
            out.writeBytes("TB = " + String.valueOf((float) totalTime / 5) + "\n");
            out.writeBytes("Cách 2: \n");
            totalTime = 0;
            for(int i = 1; i <= 5; i++){
                System.out.println("Cách 2 lần " + i);
                truncateTable();
                time_start = System.currentTimeMillis();
                insertForLine(100);
                time_end = System.currentTimeMillis();
                out.writeBytes("Lần " + i + " = " + (time_end - time_start) + " ms\n");
                totalTime += (time_end - time_start);
            }
            out.writeBytes("TB = " + String.valueOf((float) totalTime / 5) + "\n");

            out.writeBytes("Cách 3: \n");
            totalTime = 0;
            for(int i = 1; i <= 5; i++){
                System.out.println("Cách 3 lần " + i);
                truncateTable();
                time_start = System.currentTimeMillis();
                insertForLine(1000);
                time_end = System.currentTimeMillis();
                out.writeBytes("Lần " + i + " = " + (time_end - time_start) + " ms\n");
                totalTime += (time_end - time_start);
            }
            out.writeBytes("TB = " + String.valueOf((float) totalTime / 5) + "\n");

            out.writeBytes("Cách 4: \n");
            totalTime = 0;
            for(int i = 1; i <= 5; i++){
                System.out.println("Cách 4 lần " + i);
                truncateTable();
                time_start = System.currentTimeMillis();
                insertForLine(5000);
                time_end = System.currentTimeMillis();
                out.writeBytes("Lần " + i + " = " + (time_end - time_start) + " ms\n");
                totalTime += (time_end - time_start);
            }
            out.writeBytes("TB = " + String.valueOf((float) totalTime / 5) + "\n");

            out.writeBytes("Cách 5: \n");
            totalTime = 0;
            for(int i = 1; i <= 5; i++){
                System.out.println("Cách 5 lần " + i);
                truncateTable();
                time_start = System.currentTimeMillis();
                insertForLine(10000);
                time_end = System.currentTimeMillis();
                out.writeBytes("Lần " + i + " = " + (time_end - time_start) + " ms\n");
                totalTime += (time_end - time_start);
            }
            out.writeBytes("TB = " + String.valueOf((float) totalTime / 5) + "\n");

            out.flush();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
       con = getConnection();
        //creatTable();
        //insertData();
        //selectData();
        timeOfInsert();
        //ghi();
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
