package com.demo.service.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.inject.Named;

import com.demo.dao.IPaseoDAO;
import com.demo.model.Paseo;
import com.demo.model.Usuario;
import com.demo.service.IPaseoService;

@Named
public class PaseoServiceImpl implements IPaseoService{
	
	@EJB
	private IPaseoDAO dao;

	@Override
	public Paseo reservarPaseo (Paseo paseo) throws Exception{
		return dao.reservarPaseo(paseo);
	}
	
	@Override
	public List<Paseo> listarHR (Paseo paseo) throws Exception{
		return dao.listarHR(paseo);
	}
	
	@Override
	public List<Paseo> listarPP (Paseo paseo) throws Exception{
		return dao.listarPP(paseo);
	}
	
	@Override
	public 	Paseo listarResDet (Paseo paseo) throws Exception{
		return dao.listarResDet(paseo);
	}
	
	@Override
	public 	Paseo realizar (Paseo paseo) throws Exception{
		return dao.realizar(paseo);
	}
	
	@Override
	public 	Paseo finalizar (Paseo paseo) throws Exception{
		return dao.finalizar(paseo);
	}
	

	@Override
	public 	List<Paseo> listaPendientes () throws Exception{
		return dao.listaPendientes();
	}
	
	@Override
	public 	List<Paseo> listaEncurso (Paseo paseo) throws Exception{
		return dao.listaEncurso(paseo);
	}
	
	@Override
	public List<Paseo> listaRealizados(Paseo paseo) throws Exception{
		return dao.listaRealizados(paseo);
	}
	
	/*--------------CRUD--------------*/
	
	@Override
	public List<Paseo> listado() throws Exception{
		return dao.listado();
	}
	
	@Override
	public 	Paseo actualizar (Paseo paseo) throws Exception{
		return dao.actualizar(paseo);
	}
	

}
