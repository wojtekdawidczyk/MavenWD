package postgresql.zawodnicy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Zapytanie {

	public static void main(String[] args) {
		try {
			Connection c = DriverManager.getConnection(
				"jdbc:postgresql://localhost/postgres",
				"postgres", "abc123");
			
			System.out.println("Udało się połączyć");
			
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM zawodnicy");
			
			while(rs.next()) {
				String imie = rs.getString(2);  // numeracja od 1
				String nazwisko = rs.getString("nazwisko");
				
				int wzrost = rs.getInt("wzrost");
				java.sql.Date data = rs.getDate("data_ur");
				
				System.out.println("Kolejny zawodnik: "
					+ imie + " " + nazwisko + " " + wzrost + " " + data);
			}

			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
