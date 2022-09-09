package com.demo.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.demo.model.Mensaje;
import com.demo.model.Tarjeta;
import com.demo.service.ITarjetaService;

@Path("/tarjeta")
public class TarjetaController {
	
	@Inject
	private ITarjetaService service;
	
	
	@POST
	@Path("/registrar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Mensaje<Tarjeta> registrar(Tarjeta tarjeta){
		Mensaje<Tarjeta> men= new Mensaje<>();
		try {
			Tarjeta r = service.registrar(tarjeta);
			if(r == null)
			{
				men.rpta=false;
				men.mensaje="Error al regisitrar tarjeta";
			}else {
				men.rpta=true;
				men.mensaje="Tarjeta registrada";
			}
		}catch(Exception e){
			men.mensaje = e.getMessage();
		}
		return men;
	}
	
	@POST
	@Path("/listar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Mensaje<Tarjeta> listar(Tarjeta tarjeta){
		Mensaje<Tarjeta> men= new Mensaje<>();
		try {
			List<Tarjeta> t = service.listar(tarjeta);
			if(t == null)
			{
				men.rpta=false;
				men.mensaje="Error al listar";
			}else {
				men.rpta=true;
				men.objeto=t;
			}

		}catch(Exception e){
			men.mensaje = e.getMessage();
		}
		return men;

	}


}
