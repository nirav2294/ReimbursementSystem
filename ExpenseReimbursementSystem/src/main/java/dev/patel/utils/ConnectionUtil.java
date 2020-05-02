package dev.patel.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {

	public static Connection createConnection() {

		// jdbc:RDBMS:locationofdatabase:port/db?user=value&password=value

		
		try {

			Class.forName("org.mariadb.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mariadb://niravdb.cb3a2xejnvha.us-east-2.rds.amazonaws.com:3306/Project1?user=nirav2294&password=!Jaigurudev9326");
			return conn;


		} catch (SQLException e) {
			e.printStackTrace();
			return null;
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//			return null;
//		} catch (IOException e) {
//			e.printStackTrace();
//			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}

	}
}
