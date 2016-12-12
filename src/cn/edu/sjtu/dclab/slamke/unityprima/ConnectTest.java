package cn.edu.sjtu.dclab.slamke.unityprima;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectTest {
	public static void main(String[] srg) {
		String fileName = "database.properties";
        String userName = "supmid";// = "sa";
        String userPwd = "unityprima";// = "root";
		Properties p = new Properties();
		try {
			String path = ConnectTest.class.getResource("/").getPath()+"/"+fileName;
//			/System.out.print(path);
			InputStream in = new FileInputStream(new File(path));
			p.load(in);
			in.close();
			if (p.containsKey("username")) {
				userName = p.getProperty("username");
			}
			if (p.containsKey("password")) {
				userPwd = p.getProperty("password");
			}
		} catch (IOException ex) {
			Logger.getLogger(ConnectTest.class.getName()).log(Level.SEVERE,
					null, ex);
		}

        String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String dbURL = "jdbc:sqlserver://192.168.1.158:1433;databaseName=SUPMID;";
		@SuppressWarnings("unused")
		Connection dbConn;

		try {
			Class.forName(driverName);
			System.out.println(userName);
			System.out.println(userPwd);
			dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
            System.out.println("Connection Successful! ");

															// Successful!
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
