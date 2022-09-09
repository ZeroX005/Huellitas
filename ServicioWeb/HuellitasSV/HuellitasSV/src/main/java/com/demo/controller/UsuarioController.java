package com.demo.controller;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.demo.model.Mensaje;
import com.demo.model.Usuario;
import com.demo.service.IUsuarioService;

@Path("/usuario")
public class UsuarioController {
	
	@Inject
	private IUsuarioService service;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> login(){
		try {
			return service.login();	
		}catch(Exception e){
			throw new WebApplicationException(500);
		}
	}
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Mensaje<Usuario> autenticar(Usuario u){
		Mensaje<Usuario> men = new Mensaje<>();
		try {
			Usuario us = service.autenticar(u);
			if(us == null)
			{
				men.rpta=false;
				men.mensaje="El usuario o password es incorrecto";
				men.idusuario="0";
			}else {
				men.idusuario = String.valueOf(us.getCodigo());
				men.tipoUsr = us.getTipo();
				men.rpta=true;
				men.mensaje="Usuario autenticado";
			}
		}catch(Exception e){
			men.mensaje = e.getMessage();
		}
		return men;
	}
	
	@POST
	@Path("/registrar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Mensaje<Usuario> registrar(Usuario usuario){
		Mensaje<Usuario> men = new Mensaje<>();
		try {
			Usuario us = service.registrar(usuario);
			if(us == null)
			{
				men.rpta=false;
				men.mensaje="Error al registrar";
			}else {
				men.rpta=true;
				men.mensaje="Usuario registrado";
			}
		}catch(Exception e){
			men.mensaje = e.getMessage();
		}
		return men;
	}
	
	@POST
	@Path("/perfil")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario perfil(Usuario usuario) {
		try {
			return service.perfil(usuario);
		}catch(Exception ex) {
			throw new WebApplicationException(500);
		}
	}
	
	@POST
	@Path("/perfilP")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario perfilP(Usuario usuario) {
		try {
			return service.perfilP(usuario);
		}catch(Exception ex) {
			throw new WebApplicationException(500);
		}
	}
	
	@POST
	@Path("/perfilA")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Usuario perfilA(Usuario usuario) {
		try {
			return service.perfilA(usuario);
		}catch(Exception ex) {
			throw new WebApplicationException(500);
		}
	}
	
	/*------------CRUD--------------------*/
	
	@GET
	@Path("/listadoCLI")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> listadocli(){
		try {
			return service.listadocli();	
		}catch(Exception e){
			throw new WebApplicationException(500);
		}
	}
	
	@GET
	@Path("/listadoPETW")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> listadopetw(){
		try {
			return service.listadopetw();
		}catch(Exception e){
			throw new WebApplicationException(500);
		}
	}
	
	@POST
	@Path("/registrarCLI")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Mensaje<Usuario> registroCLI(Usuario usuario){
		Mensaje<Usuario> men = new Mensaje<>();
		try {
			Usuario us = service.registroCLI(usuario);
			if(us == null)
			{
				men.rpta=false;
				men.mensaje="Error al registrar";
			}else {
				men.rpta=true;
				men.mensaje="Usuario registrado";
			}
		}catch(Exception e){
			men.mensaje = e.getMessage();
		}
		return men;
	}
	
	@POST
	@Path("/registrarPETW")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Mensaje<Usuario> registroPETW(Usuario usuario){
		Mensaje<Usuario> men = new Mensaje<>();
		try {
			Usuario us = service.registroPETW(usuario);
			if(us == null)
			{
				men.rpta=false;
				men.mensaje="Error al registrar";
			}else {
				men.rpta=true;
				men.mensaje="Usuario registrado";
			}
		}catch(Exception e){
			men.mensaje = e.getMessage();
		}
		return men;
	}
	
	@POST
	@Path("/actualizarCLI")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Mensaje<Usuario> updateCLI(Usuario usuario){
		Mensaje<Usuario> men = new Mensaje<>();
		try {
			Usuario us = service.updateCLI(usuario);
			if(us == null)
			{
				men.rpta=false;
				men.mensaje="Error al actualizar";
			}else {
				men.rpta=true;
				men.mensaje="Usuario actualizado";
			}
		}catch(Exception e){
			men.mensaje = e.getMessage();
		}
		return men;
	}
	
	@POST
	@Path("/actualizarPETW")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Mensaje<Usuario> updatePETW(Usuario usuario){
		Mensaje<Usuario> men = new Mensaje<>();
		try {
			Usuario us = service.updatePETW(usuario);
			if(us == null)
			{
				men.rpta=false;
				men.mensaje="Error al actualizar";
			}else {
				men.rpta=true;
				men.mensaje="Usuario actualizado";
			}
		}catch(Exception e){
			men.mensaje = e.getMessage();
		}
		return men;
	}
	
	@POST
	@Path("/eliminarCLI")
	public Mensaje<Usuario> eliminarCLI(Usuario usuario) {
		Mensaje<Usuario> men = new Mensaje<>();
		try {
			Usuario us = service.eliminarCLI(usuario);
			if(us == null)
			{
				men.rpta=false;
				men.mensaje="El cliente esta relacionado con otra tabla";
			}else {
				men.rpta=true;
				men.mensaje="Cliente eliminado";
			}
		}catch(Exception e){
			men.mensaje = e.getMessage();
		}
		return men;
	}
	
	@POST
	@Path("/eliminarPETW")
	public Mensaje<Usuario> eliminarPETW(Usuario usuario) {
		Mensaje<Usuario> men = new Mensaje<>();
		try {
			Usuario us = service.eliminarPETW(usuario);
			if(us == null)
			{
				men.rpta=false;
				men.mensaje="El petwalker esta relacionado con otra tabla";
			}else {
				men.rpta=true;
				men.mensaje="Petwalker eliminado";
			}
		}catch(Exception e){
			men.mensaje = e.getMessage();
		}
		return men;
	}
	
	
	
	
	
	
	
	

}
