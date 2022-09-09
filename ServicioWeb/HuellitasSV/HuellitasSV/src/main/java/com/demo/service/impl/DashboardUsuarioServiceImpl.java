package com.demo.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;

import com.demo.dao.IDashboardUsuarioDAO;
import com.demo.model.DashboardUsuario;
import com.demo.service.IDashboardUsuarioService;

@Named
public class DashboardUsuarioServiceImpl implements IDashboardUsuarioService{
	
	@EJB
	private IDashboardUsuarioDAO dao;

	@Override
	public List<DashboardUsuario> listar() throws Exception {
		return dao.listar();
	}

}
