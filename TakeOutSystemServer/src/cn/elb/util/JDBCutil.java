package cn.elb.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCutil {
	private static String DRIVER;
	private static String URL;
	private static String USER;
	private static String PASSWORD;
	private static File file = new File("jdbc.properties");
	static {
		Properties p = new Properties();
		if (!file.exists()) {
			p.setProperty("oracledriver", "oracle.jdbc.driver.OracleDriver");
			p.setProperty("oracleurl", "jdbc:oracle:thin:@localhost:1521:XE");
			p.setProperty("oracleuser", "scott");
			p.setProperty("oraclepassword", "1596357hu");
			p.setProperty("datatype", "oracle");
			try {
				p.store(new FileWriter(file), "jdbc");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			p.load(new FileReader(file));
			String dataType = p.getProperty("datatype");
			System.out.println("---------" + dataType);
			DRIVER = p.getProperty(dataType + "driver");
			URL = p.getProperty(dataType + "url");
			USER = p.getProperty(dataType + "user");
			PASSWORD = p.getProperty(dataType + "password");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConn() {
		Connection conn = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static int update(String sql, Object... obj) {
		Connection conn = null;
		PreparedStatement stmt = null;
		int result = 0;
		conn = getConn();
		try {
			stmt = conn.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				stmt.setObject(i + 1, obj[i]);
			}
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void closeAll(Statement stmt, ResultSet rs, Connection conn) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		System.out.println(getConn());
	}
}
