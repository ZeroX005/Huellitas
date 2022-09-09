package com.demo.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;

import com.demo.dao.IDashboardReclamoDAO;
import com.demo.model.DashboardReclamo;
import com.demo.service.IDashboardReclamoService;

@Named
public class DashboardReclamoServiceImpl implements IDashboardReclamoService{
	
	@EJB
	private IDashboardReclamoDAO dao;

	@Override
	public List<DashboardReclamo> listar() throws Exception {
		return dao.listar();
	}

}
