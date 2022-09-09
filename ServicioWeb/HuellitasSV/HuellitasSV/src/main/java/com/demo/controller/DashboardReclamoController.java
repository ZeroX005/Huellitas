package com.demo.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import com.demo.model.DashboardReclamo;
import com.demo.service.IDashboardReclamoService;


@Path("/dbrcl")
public class DashboardReclamoController {
	
	@Inject
	private IDashboardReclamoService service;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<DashboardReclamo> listar() {
		try {
			return service.listar();
		} catch (Exception e) {
			throw new WebApplicationException(403);
		}
	}

}
