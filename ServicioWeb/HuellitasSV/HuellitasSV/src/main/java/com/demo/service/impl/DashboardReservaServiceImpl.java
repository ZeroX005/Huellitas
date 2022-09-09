package com.demo.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;

import com.demo.dao.IDashboardReservaDAO;
import com.demo.model.DashboardReserva;
import com.demo.service.IDashboardReservaService;

@Named
public class DashboardReservaServiceImpl implements IDashboardReservaService{
	
	@EJB
	private IDashboardReservaDAO dao;

	@Override
	public List<DashboardReserva> listar() throws Exception {
		return dao.listar();
	}

}
