package com.demo.service;

import java.util.List;

import com.demo.model.Reclamo;

public interface IReclamoService {

	Reclamo registrar(Reclamo reclamo) throws Exception;
	
	List<Reclamo> listarCli(Reclamo reclamo) throws Exception;
	
	List<Reclamo> listarPetw(Reclamo reclamo) throws Exception;
	
	/*-----------CRUD-----------------*/
	
	List<Reclamo> reclamos() throws Exception;
	
	Reclamo actualizar(Reclamo reclamo) throws Exception;
}
