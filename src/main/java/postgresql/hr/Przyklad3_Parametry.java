package postgresql.hr;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Przyklad3_Parametry {

	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in)) {
			System.out.println("Podaj pensję minimalną i maksymalną:");
			
			int pensjaMin = sc.nextInt();
			int pensjaMax = sc.nextInt();			
			pracownicyWedlugPensji(pensjaMin, pensjaMax);
		}
	}

	private static void pracownicyWedlugPensji(int pensjaMin, int pensjaMax) {
		try(Connection c = DriverManager.getConnection(
					"jdbc:postgresql://localhost/hr", "hr", "hr");
			PreparedStatement stmt = c.prepareStatement(
					"SELECT * FROM employees WHERE salary BETWEEN ? AND ?")) {
			
			// wstawienie konkretnej wartości na parametr o podanym numerze; jest numer znaku zapytania w treści SQL licząc od lewej (od 1)
			stmt.setInt(1, pensjaMin);
			stmt.setInt(2, pensjaMax);
			
			// Tego otwierania nie można umieścić w pierwszym try, bo musielismy ustawić parametry. Musi być drugi try
			try(ResultSet rs = stmt.executeQuery()) {
				System.out.println("Zanelzieni pracownicy:");
				while(rs.next()) {
					int id = rs.getInt(1);
					String imie = rs.getString(2);
					String nazwisko = rs.getString(3);
					
					BigDecimal pensja = rs.getBigDecimal("salary");
					
					System.out.printf("%3d %-12s %-12s %8s\n",
							id, imie, nazwisko, pensja);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
