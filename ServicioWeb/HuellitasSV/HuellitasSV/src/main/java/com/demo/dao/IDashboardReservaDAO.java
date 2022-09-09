package com.demo.dao;

import java.util.List;

import javax.ejb.Local;

import com.demo.model.DashboardReserva;

@Local
public interface IDashboardReservaDAO {
	
	List<DashboardReserva> listar();

}
