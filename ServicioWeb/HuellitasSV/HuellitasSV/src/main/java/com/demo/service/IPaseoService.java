package com.demo.service;

import java.util.List;

import com.demo.model.Paseo;

public interface IPaseoService {

	Paseo reservarPaseo(Paseo paseo) throws Exception;
	
	List<Paseo> listarHR (Paseo paseo) throws Exception;
	
	List<Paseo> listarPP (Paseo paseo) throws Exception;
	
	Paseo listarResDet (Paseo paseo) throws Exception; 
	
	Paseo realizar (Paseo paseo) throws Exception;
	
	Paseo finalizar (Paseo paseo) throws Exception;
	
	List<Paseo> listaPendientes() throws Exception;
	
	List<Paseo> listaEncurso(Paseo paseo) throws Exception;
	
	List<Paseo> listaRealizados(Paseo paseo) throws Exception;
	
	/*--------------CRUD---------------------*/
	
	List<Paseo> listado () throws Exception;
	
	Paseo actualizar (Paseo paseo) throws Exception;
	

	
}
