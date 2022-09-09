package com.demo.dao;

import java.util.List;

import javax.ejb.Local;

import com.demo.model.Usuario;

@Local
public interface IUsuarioDAO {
	
	List<Usuario> login();
	
	Usuario autenticar(Usuario u);
	
	Usuario registrar (Usuario usuario);
	
	Usuario perfil(Usuario usuario);
	
	Usuario perfilP(Usuario usuario);
	
	Usuario perfilA(Usuario usuario);
	
	/*--------------CRUD--------------*/
	
	List<Usuario> listadocli();
	
	List<Usuario> listadopetw();
	
	Usuario registroCLI (Usuario usuario);
	
	Usuario registroPETW (Usuario usuario);
	
	Usuario updateCLI (Usuario usuario);
	
	Usuario updatePETW (Usuario usuario);
	
	Usuario eliminarCLI (Usuario usuario);
	
	Usuario eliminarPETW (Usuario usuario);

}
