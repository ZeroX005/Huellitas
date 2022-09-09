package com.demo.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;

import com.demo.dao.ITarjetaDAO;
import com.demo.model.Tarjeta;
import com.demo.service.ITarjetaService;

@Named
public class TarjetaServiceImpl implements ITarjetaService {

	@EJB
	private ITarjetaDAO dao;
	
	
	@Override
	public Tarjeta registrar(Tarjeta tarjeta) throws Exception {
		return dao.registrar(tarjeta);
	}

	@Override
	public List<Tarjeta> listar(Tarjeta tarjeta) throws Exception {
		return dao.listar(tarjeta);
	}
	
	
	
	

}
