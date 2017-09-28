package postgresql.hr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Przyklad4_Aktualizacja {

	public static void main(String[] args) {
		String job = "IT_PROG";
		double zmiana = -0.1;
		
		zmienPensje(job, zmiana);
	}

	private static void zmienPensje(String job, double zmiana) {
		try(Connection c = DriverManager.getConnection(
					"jdbc:postgresql://localhost/hr", "hr", "hr");
			PreparedStatement stmt = c.prepareStatement(
					"UPDATE employees SET salary = salary * ? WHERE job_id = ?")) {
			
			stmt.setDouble(1, 1+zmiana);
			stmt.setString(2, job);
			
			int ile = stmt.executeUpdate();
			
			System.out.println("Zaktualizowanych rekordów: " + ile);
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
