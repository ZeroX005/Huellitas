package com.demo.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

	protected static Connection cx;
	
	public static Connection conectar() {
		if (cx != null) {
			return cx;
		}

		try {
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/huellitas";
			Class.forName(driver);
			cx = DriverManager.getConnection(url,"root","");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return cx;
	}

	public static void desconectar() {
		if (cx == null) {
			return;
		}

		try {
			cx.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
}
