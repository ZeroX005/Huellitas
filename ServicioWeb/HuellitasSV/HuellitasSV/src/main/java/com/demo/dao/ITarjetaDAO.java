package com.demo.dao;

import java.util.List;

import javax.ejb.Local;

import com.demo.model.Tarjeta;

@Local
public interface ITarjetaDAO {
	
	Tarjeta registrar (Tarjeta tarjeta);
	
	List<Tarjeta> listar (Tarjeta tarjeta);

}
