package postgresql.zawodnicy;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Polaczenie {

	public static void main(String[] args) {
		try {
			Connection c = DriverManager.getConnection(
				"jdbc:postgresql://localhost/postgres",
				"postgres", "abc123");
			
			System.out.println("Udało się połączyć");
			System.out.println(c);
			
			DatabaseMetaData metadata = c.getMetaData();
			System.out.println(metadata.getDatabaseProductName());
			System.out.println(metadata.getDatabaseProductVersion());

			System.out.println(metadata.getDriverName());
			System.out.println(metadata.getDriverVersion());

			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
