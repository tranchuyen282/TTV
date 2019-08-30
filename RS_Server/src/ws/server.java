package ws;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Random;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Application;

@Path("/TTV")
@ApplicationPath("/GetPhone")
public class server extends Application{
	@GET
	@Path("/getPhone")
	public String getPhone() {
		Connection con = null;
		String dbUrl = "jdbc:mysql://localhost:3306/ttv_sdt";
        String dbClass = "com.mysql.jdbc.Driver";
        try{
            Class.forName(dbClass);
            con = DriverManager.getConnection(dbUrl,"root","27281997");
        }catch(Exception e){
            e.printStackTrace();
        }
		Random rd = new Random();
		int a = rd.nextInt();
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
		}
		
		
		return String.valueOf(a);
	}
}
