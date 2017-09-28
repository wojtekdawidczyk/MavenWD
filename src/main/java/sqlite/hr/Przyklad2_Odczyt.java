package sqlite.hr;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Przyklad2_Odczyt {

	public static void main(String[] args) {
		// Dla baz danych też można używać try()
		// Obiekty zamykalne to są: Connection, Statement, ResultSet
		try(Connection c = DriverManager.getConnection(
					"jdbc:sqlite:hr.db")) {
			
			System.out.println("Udało się połączyć, c=" + c);
			
			// W jednym try można otworzyć kilka rzeczy, jeśli nastepuje to bezpośrednio po sobie
			try(Statement stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM employees")) {
			
				while(rs.next()) {
					int id = rs.getInt(1);
					String imie = rs.getString(2);
					String nazwisko = rs.getString(3);
					
					BigDecimal pensja = rs.getBigDecimal("salary");
					String job = rs.getString("job_id");
					String data = rs.getString("hire_date");
					
					System.out.printf("%d %s %s %s %s %s\n",
							id, imie, nazwisko, pensja, job, data);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
