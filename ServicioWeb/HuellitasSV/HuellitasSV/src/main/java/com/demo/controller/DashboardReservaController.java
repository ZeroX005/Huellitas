package com.demo.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import com.demo.model.DashboardReserva;
import com.demo.service.IDashboardReservaService;

@Path("/dbres")
public class DashboardReservaController {
	
	@Inject
	private IDashboardReservaService service;
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<DashboardReserva> listar() {
		try {
			return service.listar();
		} catch (Exception e) {
			throw new WebApplicationException(403);
		}
	}

}
