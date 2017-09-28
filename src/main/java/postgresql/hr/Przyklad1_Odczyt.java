package postgresql.hr;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Przyklad1_Odczyt {

	public static void main(String[] args) {
		try {
			Connection c = DriverManager.getConnection(
					"jdbc:postgresql://localhost/hr", "hr", "hr");
			
			System.out.println("Udało się połączyć, c=" + c);
			
			// Proste zadawanie zapytań:
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM employees");
			// "kursor" albo "iterator" dający dostęp do wyników
			
			while(rs.next()) {
				// Teraz jesteśmy w kolejnym rekordzie i możemy odczytywać pola tego rekordu
				
				// Można odczytywać pola wg numeru kolumny, numeracja od 1
				int id = rs.getInt(1);
				String imie = rs.getString(2);
				String nazwisko = rs.getString(3);
				
				// Można też używać nazw kolumn. Jeśli w zapuytaniu był wprowadzony alias dla kolumny, to tu używamy aliasu
				
				BigDecimal pensja = rs.getBigDecimal("salary");
				String email = rs.getString("email");
				
				// java.sql.Date - typ daty na potrzeby dostepu do baz danych;
				// To nie jest to samo co java.util.Date ani java.time.LocalDate
				Date data = rs.getDate("hire_date");
				
				System.out.printf("%d %s %s %s %s %s\n",
						id, imie, nazwisko, pensja, email, data);
			}
			
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
