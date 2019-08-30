import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Random;

public class test {
    private static Connection con;

    private static Connection getConnection() {
        Connection con = null;
        String dbUrl = "jdbc:mysql://localhost:3306/ttv_sdt";
        String dbClass = "com.mysql.jdbc.Driver";
        try{
            Class.forName(dbClass);
            con = DriverManager.getConnection(dbUrl,"root","27281997");
        }catch(Exception e){
            e.printStackTrace();
        }

        return con;

    }

    public static void main(String[] args) {
        Random rd = new Random();
        int a = rd.nextInt();
        con = getConnection();
        if(con == null) {
            System.out.println( "No connect");
        }else {

            String sql = "INSERT INTO tblphone (number) VALUES (?)" ;
            try {
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, String.valueOf(a));
                ps.executeUpdate();
                con.close();
            }catch(Exception e) {
                e.printStackTrace();
            }

            System.out.println(a);
        }
    }
}
