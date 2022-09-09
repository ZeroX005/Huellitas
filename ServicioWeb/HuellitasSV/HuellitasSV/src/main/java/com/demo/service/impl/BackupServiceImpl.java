package com.demo.service.impl;

import javax.ejb.EJB;
import javax.inject.Named;

import com.demo.dao.IBackup;
import com.demo.dao.impl.BackupDAOImpl;
import com.demo.service.IBackupService;

@Named
public class BackupServiceImpl implements IBackupService{
	
	@EJB
	private IBackup dao;

	@Override
	public void backup() {
		dao.backup();
		
	}
	
	

}
