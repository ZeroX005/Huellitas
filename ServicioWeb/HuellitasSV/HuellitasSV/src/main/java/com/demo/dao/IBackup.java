package com.demo.dao;

import javax.ejb.Local;

@Local
public interface IBackup {
	
	void backup();

}
