package postgresql_zawodnicy;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Zapytanie {
public static void main(String[] args) {
	
	try {
		Connection c = DriverManager.getConnection("jdbc:postgresql://localhost/postgres", "postgres", "abc123");
		
		System.out.println("udalo sie polaczyc");
		
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery("select * from zawodnicy");
		
		while (rs.next()) {
			String imie = rs.getString(2); //numeracja od 1
			String nazwisko = rs.getString("nazwisko");
			int wzrost = rs.getInt("wzrost");
			Date data = rs.getDate("data_ur");
			
			System.out.println("kolejny zawodnik to : " + imie + " " + nazwisko + " " + wzrost + " " + data);
			
		}
		
		c.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
	}
}