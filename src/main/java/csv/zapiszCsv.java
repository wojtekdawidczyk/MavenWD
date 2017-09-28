package csv;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class zapiszCsv {
	   public static void main(String[] args) {
		   	 
		   	 try(Scanner sc = new Scanner(System.in)) {
		   		 System.out.println("Podaj pensję minimalną i maksymalną:");
		   		 
		   		 int pensjaMin = sc.nextInt();
		   		 int pensjaMax = sc.nextInt();   		 

		   		 System.out.println("Podaj nazwę pliku wynikowego np. wynik.csv");
		   		 
		   		 String plik = sc.next();
		   		 
		   		 System.out.println("min  = " + pensjaMin);
		   		 System.out.println("max  = " + pensjaMax);
		   		 System.out.println("plik = " + plik);
		   		 
		   		 pracownicyWedlugPensji(pensjaMin, pensjaMax, plik);
		   		 System.out.println("Koniec programu");
		   	 }
		    }

		    private static void pracownicyWedlugPensji(int pensjaMin, int pensjaMax, String plik) {
		   	 final String sql = "SELECT first_name, last_name, job_title, department_name, salary, street_address, postal_code, city "
		   			 +" FROM employees JOIN departments USING(department_id) JOIN locations USING(location_id) JOIN jobs USING(job_id) "
		   			 +" WHERE salary BETWEEN ? AND ?";
		   	 
		   	 
		   	 try(Connection c = DriverManager.getConnection(
		   				 "jdbc:postgresql://localhost/hr", "hr", "hr");
		   		 PreparedStatement stmt = c.prepareStatement(sql)) {
		   		 
		   		 stmt.setInt(1, pensjaMin);
		   		 stmt.setInt(2, pensjaMax);
		   		 
		   		 try(ResultSet rs = stmt.executeQuery()) {
		   			 zapiszWynikiDoPliku(rs, plik);
		   		 }
		   	 } catch (SQLException e) {
		   		 e.printStackTrace();
		   	 }
		    }

		    private static void zapiszWynikiDoPliku(ResultSet rs, String plik) {
		   	 
		   	 try(PrintWriter out = new PrintWriter(plik)) {
		   		 
		   		 CSVFormat format = CSVFormat.EXCEL.withDelimiter(';').withHeader(rs);
		   		 
		   		 try(CSVPrinter printer = new CSVPrinter(out, format)) {
		   			 printer.printRecords(rs);
		   		 }
		   	 } catch (SQLException e) {
		   		 e.printStackTrace();
		   	 } catch (FileNotFoundException e) {
		   		 e.printStackTrace();
		   	 } catch (IOException e) {
		   		 e.printStackTrace();
		   	 }
		    }
		}



		   	 

		
