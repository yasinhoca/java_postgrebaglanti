package postgreNormal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*interface Baglan{
	void baglan();
} */

public class Veritabani {
	static String url = "jdbc:postgresql://localhost:5432/normalogretim"; //127.0.0.1 
	//uzaktaki bir servere baðlanýrken o makinenin ip adresini yazabilirsiniz 74.68.124.23
	static Connection conn=null;
	
	/*@Override
	public void baglan() {
		// TODO Auto-generated method stub
		
	} */
	

	
	
	static void baglan() { //baglan() yerine Veritabaný() constructer olur
		try {
			conn = DriverManager.getConnection(url,"postgres","1234");
			System.out.print("Veritabanýna baðlantý baþarýlý!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Veritabanýna baðlanýlamadý!");
		}
	} 
	
	static ResultSet listele(String sorgu) {
		Statement st;
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sorgu);
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	

}
