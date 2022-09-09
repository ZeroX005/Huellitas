package com.demo.controller;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;

import com.demo.service.IBackupService;

@Path("/backup")
public class BackupController {
	
	@Inject
	private IBackupService service;
	
	@GET
	public void backup() {
		try {
			service.backup();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
	
	

}
