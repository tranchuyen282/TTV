package ws;

import java.lang.Thread.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Random;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.jws.*;


@WebService(endpointInterface = "ws.demo")
public class DemoImpl implements demo{
	
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
	
	
	
	@Override
	public String Hello() {
		Random rd = new Random();
		int a = rd.nextInt();
		con = getConnection();
		if(con == null) {
			return "No connect";
		}else {
			
			String sql = "INSERT INTO tblphone (number) VALUES (?)" ;
			try {
				 PreparedStatement ps = con.prepareStatement(sql);
				 ps.setString(1, String.valueOf(a));
				 ps.executeUpdate();
				
			}catch(Exception e) {
				e.printStackTrace();
			}
			return String.valueOf(a);
		}
		
	}
	
}
