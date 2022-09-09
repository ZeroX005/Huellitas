package com.demo.dao;

import java.util.List;

import javax.ejb.Local;

import com.demo.model.Paseo;

@Local
public interface IPaseoDAO {
	
	Paseo reservarPaseo (Paseo paseo);
	
	List<Paseo> listarHR (Paseo paseo);
	
	List<Paseo> listarPP (Paseo paseo);
	
	Paseo listarResDet (Paseo paseo);
	
	Paseo realizar (Paseo paseo);
	
	Paseo finalizar (Paseo paseo);
	
	List<Paseo> listaPendientes();
	
	List<Paseo> listaEncurso(Paseo paseo);
	
	List<Paseo> listaRealizados(Paseo paseo);
	
	/*--------------CRUD---------------------*/
	
	List<Paseo> listado ();
	
	Paseo actualizar (Paseo paseo);
	

}
