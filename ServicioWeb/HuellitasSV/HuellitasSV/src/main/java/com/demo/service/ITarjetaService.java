package com.demo.service;

import java.util.List;

import com.demo.model.Tarjeta;

public interface ITarjetaService {
	
	Tarjeta registrar (Tarjeta tarjeta) throws Exception;
	
	List<Tarjeta> listar (Tarjeta tarjeta) throws Exception;

}
