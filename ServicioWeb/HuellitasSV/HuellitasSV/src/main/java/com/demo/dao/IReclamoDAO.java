package com.demo.dao;

import java.util.List;

import javax.ejb.Local;

import com.demo.model.Reclamo;

@Local
public interface IReclamoDAO {

	Reclamo registrar(Reclamo reclamo);
	
	List<Reclamo> listarCli(Reclamo reclamo);
	
	List<Reclamo> listarPetw(Reclamo reclamo);
	
	/*-----------CRUD-----------------*/
	
	List<Reclamo> reclamos();
	
	Reclamo actualizar(Reclamo reclamo);

}
