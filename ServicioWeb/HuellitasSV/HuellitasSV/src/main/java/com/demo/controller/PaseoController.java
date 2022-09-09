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
import com.demo.model.Paseo;
import com.demo.model.Usuario;
import com.demo.service.IPaseoService;

@Path("/paseo")
public class PaseoController {
	
	@Inject
	private IPaseoService service;
	
	/*------------CLIENTE------------*/
	
	@POST
	@Path("/reservar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Mensaje<Paseo> reservarPaseo(Paseo paseo){
		Mensaje<Paseo> men= new Mensaje<>();
		try {
			Paseo p = service.reservarPaseo(paseo);
			if(p == null)
			{
				men.rpta=false;
				men.mensaje="Error al reservar";
			}else {
				men.rpta=true;
				men.mensaje="Paseo reservado";
			}
		}catch(Exception e){
			men.mensaje = e.getMessage();
		}
		return men;
	}
	
	@POST
	@Path("/listarHR")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Mensaje<Paseo> listarHR(Paseo paseo){
		Mensaje<Paseo> men= new Mensaje<>();
		try {
			List<Paseo> p = service.listarHR(paseo);
			if(p == null)
			{
				men.rpta=false;
				men.mensaje="Error al listar";
			}else {
				men.rpta=true;
				men.objeto=p;
			}

		}catch(Exception e){
			men.mensaje = e.getMessage();
		}
		return men;

	}
	
	@POST
	@Path("/listarPP")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Mensaje<Paseo> listarPP(Paseo paseo){
		Mensaje<Paseo> men= new Mensaje<>();
		try {
			List<Paseo> p = service.listarPP(paseo);
			if(p == null)
			{
				men.rpta=false;
				men.mensaje="Error al listar";
			}else {
				men.rpta=true;
				men.objeto=p;
			}

		}catch(Exception e){
			men.mensaje = e.getMessage();
		}
		return men;

	}
	
	@POST
	@Path("/detalleReserva")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Paseo listarDetRes(Paseo paseo){
		try {
			return service.listarResDet(paseo);
		}catch(Exception ex) {
			throw new WebApplicationException(500);
		}
	}	
	
	
	
	/*------------PETWALKER------------*/
	
	@GET
	@Path("/estadoPendiente")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Paseo> listaPendientes() {
		try {
			return service.listaPendientes();
		} catch (Exception e) {
			throw new WebApplicationException(403);
		}
	}
	
	@POST
	@Path("/realizar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Mensaje<Paseo> realizar(Paseo paseo){
		Mensaje<Paseo> men= new Mensaje<>();
		try {
			Paseo p = service.realizar(paseo);
			if(p == null)
			{
				men.rpta=false;
				men.mensaje="Error al realiza paseo";
			}else {
				men.rpta=true;
				men.mensaje="Paseo en curso";
			}
		}catch(Exception e){
			men.mensaje = e.getMessage();
		}
		return men;
	}
	
	@POST
	@Path("/estadoEnCurso")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Mensaje<Paseo> listaEncurso(Paseo paseo){
		Mensaje<Paseo> men= new Mensaje<>();
		try {
			List<Paseo> p = service.listaEncurso(paseo);
			if(p == null)
			{
				men.rpta=false;
				men.mensaje="Error al listar";
			}else {
				men.rpta=true;
				men.objeto=p;
			}

		}catch(Exception e){
			men.mensaje = e.getMessage();
		}
		return men;

	}
	
	@POST
	@Path("/finalizar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Mensaje<Paseo> finalizar(Paseo paseo){
		Mensaje<Paseo> men= new Mensaje<>();
		try {
			Paseo p = service.finalizar(paseo);
			if(p == null)
			{
				men.rpta=false;
				men.mensaje="Error al finalizar paseo";
			}else {
				men.rpta=true;
				men.mensaje="Paseo finalizado";
			}
		}catch(Exception e){
			men.mensaje = e.getMessage();
		}
		return men;
	}
	
	@POST
	@Path("/estadoRealizados")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Mensaje<Paseo> listarRealizados(Paseo paseo){
		Mensaje<Paseo> men= new Mensaje<>();
		try {
			List<Paseo> p = service.listaRealizados(paseo);
			if(p == null)
			{
				men.rpta=false;
				men.mensaje="Error al listar";
			}else {
				men.rpta=true;
				men.objeto=p;
			}

		}catch(Exception e){
			men.mensaje = e.getMessage();
		}
		return men;

	}
	
	/*------------CRUD--------------------*/
	
	@GET
	@Path("/listado")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Paseo> listado(){
		try {
			return service.listado();	
		}catch(Exception e){
			throw new WebApplicationException(500);
		}
	}
	
	@POST
	@Path("/actualizar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Mensaje<Paseo> actualizar(Paseo paseo){
		Mensaje<Paseo> men= new Mensaje<>();
		try {
			Paseo p = service.actualizar(paseo);
			if(p == null)
			{
				men.rpta=false;
				men.mensaje="Error al actualizar reserva";
			}else {
				men.rpta=true;
				men.mensaje="Reserva actualizada";
			}
		}catch(Exception e){
			men.mensaje = e.getMessage();
		}
		return men;
	}
	
	

}
