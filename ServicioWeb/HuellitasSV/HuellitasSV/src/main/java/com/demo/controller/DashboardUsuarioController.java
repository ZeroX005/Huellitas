package com.demo.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import com.demo.model.DashboardUsuario;
import com.demo.service.IDashboardUsuarioService;

@Path("/dbu")
public class DashboardUsuarioController {
	
	@Inject
	private IDashboardUsuarioService service;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<DashboardUsuario> listar() {
		try {
			return service.listar();
		} catch (Exception e) {
			throw new WebApplicationException(403);
		}
	}

}
