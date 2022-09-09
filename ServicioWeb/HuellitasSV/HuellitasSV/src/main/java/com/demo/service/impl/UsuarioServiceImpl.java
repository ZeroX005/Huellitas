package com.demo.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;

import com.demo.dao.IUsuarioDAO;
import com.demo.model.Usuario;
import com.demo.service.IUsuarioService;

@Named
public class UsuarioServiceImpl implements IUsuarioService{
	
	@EJB
	private IUsuarioDAO dao;
	
	@Override
	public List<Usuario> login() throws Exception{
		return dao.login();
	}
	
	@Override
	public Usuario autenticar(Usuario u) throws Exception{
		return dao.autenticar(u);
	}
	
	@Override
	public Usuario registrar(Usuario usuario) throws Exception{
		return dao.registrar(usuario);
	}
	
	@Override
	public Usuario perfil(Usuario usuario) throws Exception{
		return dao.perfil(usuario);
	}

	@Override
	public Usuario perfilP(Usuario usuario) throws Exception {
		return dao.perfilP(usuario);
	}
	
	@Override
	public Usuario perfilA(Usuario usuario) throws Exception {
		return dao.perfilA(usuario);
	}
	
	/*--------------CRUD--------------*/
	
	@Override
	public List<Usuario> listadocli() throws Exception{
		return dao.listadocli();
	}
	
	@Override
	public List<Usuario> listadopetw() throws Exception{
		return dao.listadopetw();
	}
	
	@Override
	public Usuario registroCLI(Usuario usuario) throws Exception {
		return dao.registroCLI(usuario);
	}
	
	@Override
	public Usuario registroPETW(Usuario usuario) throws Exception {
		return dao.registroPETW(usuario);
	}
	
	@Override
	public Usuario updateCLI(Usuario usuario) throws Exception {
		return dao.updateCLI(usuario);
	}
	
	@Override
	public Usuario updatePETW(Usuario usuario) throws Exception {
		return dao.updatePETW(usuario);
	}
	
	@Override
	public Usuario eliminarCLI(Usuario usuario) throws Exception {
		return dao.eliminarCLI(usuario);
	}
	
	@Override
	public Usuario eliminarPETW(Usuario usuario) throws Exception {
		return dao.eliminarPETW(usuario);
	}
	
	
	
}
