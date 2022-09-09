package com.demo.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;

import com.demo.dao.IReclamoDAO;
import com.demo.model.Reclamo;
import com.demo.service.IReclamoService;

@Named
public class ReclamoServiceImpl implements IReclamoService {
	
	@EJB
	private IReclamoDAO dao;
	
	@Override
	public Reclamo registrar(Reclamo reclamo) throws Exception {
		return dao.registrar(reclamo);
	}
	
	@Override 
	public List<Reclamo> listarCli(Reclamo reclamo) throws Exception{
		return dao.listarCli(reclamo);
	}
	
	@Override 
	public List<Reclamo> listarPetw(Reclamo reclamo) throws Exception{
		return dao.listarPetw(reclamo);
	}
	
	/*--------------CRUD-----------------*/
	
	@Override 
	public List<Reclamo> reclamos() throws Exception{
		return dao.reclamos();
	}
	
	@Override 
	public Reclamo actualizar(Reclamo reclamo) throws Exception{
		return dao.actualizar(reclamo);
	}

	
	
}
