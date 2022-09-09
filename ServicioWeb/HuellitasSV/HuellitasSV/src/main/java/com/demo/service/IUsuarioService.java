package com.demo.service;

import java.util.List;

import com.demo.model.Usuario;

public interface IUsuarioService {
	
	List<Usuario> login() throws Exception;
	
	Usuario autenticar(Usuario u) throws Exception;

	Usuario registrar(Usuario usuario) throws Exception;
	
	Usuario perfil(Usuario usuario) throws Exception;
	
	Usuario perfilP(Usuario usuario) throws Exception;
	
	Usuario perfilA(Usuario usuario) throws Exception;
	
	/*--------------CRUD--------------*/
	
	List<Usuario> listadocli() throws Exception;
	
	List<Usuario> listadopetw() throws Exception;
	
	Usuario registroCLI (Usuario usuario) throws Exception;
	
	Usuario registroPETW (Usuario usuario) throws Exception;
	
	Usuario updateCLI (Usuario usuario) throws Exception;
	
	Usuario updatePETW (Usuario usuario) throws Exception;
	
	Usuario eliminarCLI (Usuario usuario) throws Exception;
	
	Usuario eliminarPETW (Usuario usuario) throws Exception;
}
