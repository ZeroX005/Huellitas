package com.demo.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

import com.demo.model.Mensaje;
import com.demo.model.Reclamo;
import com.demo.service.IReclamoService;

@Path("/reclamo")
public class ReclamoController {
	
	@Inject
	private IReclamoService service;
	
	@POST
	@Path("/registrar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Mensaje<Reclamo> registrar(Reclamo reclamo){
		Mensaje<Reclamo> men= new Mensaje<>();
		try {
			Reclamo r = service.registrar(reclamo);
			if(r == null)
			{
				men.rpta=false;
				men.mensaje="Error al reportar";
			}else {
				men.rpta=true;
				men.mensaje="Reclamo enviado";
			}
		}catch(Exception e){
			men.mensaje = e.getMessage();
		}
		return men;
	}
	
	@POST
	@Path("/listarCli")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Mensaje<Reclamo> listarCli(Reclamo reclamo){
		Mensaje<Reclamo> men= new Mensaje<>();
		try {
			List<Reclamo> r = service.listarCli(reclamo);
			if(r == null)
			{
				men.rpta=false;
				men.mensaje="Error al listar";
			}else {
				men.rpta=true;
				men.objeto=r;
			}

		}catch(Exception e){
			men.mensaje = e.getMessage();
		}
		return men;

	}
	
	@POST
	@Path("/listarPetw")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Mensaje<Reclamo> listarPetw(Reclamo reclamo){
		Mensaje<Reclamo> men= new Mensaje<>();
		try {
			List<Reclamo> r = service.listarPetw(reclamo);
			if(r == null)
			{
				men.rpta=false;
				men.mensaje="Error al listar";
			}else {
				men.rpta=true;
				men.objeto=r;
			}

		}catch(Exception e){
			men.mensaje = e.getMessage();
		}
		return men;

	}
	
	
	/*------------------CRUD--------------------*/
	
	@GET
	@Path("/listado")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Reclamo> reclamos(){
		try {
			return service.reclamos();	
		}catch(Exception e){
			throw new WebApplicationException(500);
		}
	}
	
	@POST
	@Path("/actualizar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Mensaje<Reclamo> actualizar(Reclamo reclamo){
		Mensaje<Reclamo> men= new Mensaje<>();
		try {
			Reclamo r = service.actualizar(reclamo);
			if(r == null)
			{
				men.rpta=false;
				men.mensaje="Error al actualziar";
			}else {
				men.rpta=true;
				men.mensaje="Reclamo actualizado";
			}
		}catch(Exception e){
			men.mensaje = e.getMessage();
		}
		return men;
	}

}
