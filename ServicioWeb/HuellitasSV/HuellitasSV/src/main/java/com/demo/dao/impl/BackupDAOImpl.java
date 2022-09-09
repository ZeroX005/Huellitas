package com.demo.dao.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.ejb.Stateless;

import com.demo.dao.IBackup;

@Stateless
public class BackupDAOImpl implements IBackup{
	
	public void backup() {
		
		try {

		Process p = Runtime.getRuntime().exec("C:/Program Files/MySQL/MySQL_Server_8.0/bin/mysqldump --column-statistics=0 -u root huellitas");
		 
		InputStream is = p.getInputStream();
		FileOutputStream fos = new FileOutputStream("C:/Respaldos/backup_huellitas.sql"); 
		byte[] buffer = new byte[1000]; 
		 
		int leido = is.read(buffer); 
		while (leido > 0) {
			fos.write(buffer, 0, leido);
			leido = is.read(buffer);
		}
		 
		fos.close();
		}catch (IOException ex) {
			ex.printStackTrace();
		}
		
		
	}

}
