package com.demo.dao;

import java.util.List;

import javax.ejb.Local;

import com.demo.model.DashboardUsuario;

@Local
public interface IDashboardUsuarioDAO {
	
	List<DashboardUsuario> listar();


}
