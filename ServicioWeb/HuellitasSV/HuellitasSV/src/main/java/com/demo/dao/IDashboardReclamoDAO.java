package com.demo.dao;

import java.util.List;

import javax.ejb.Local;

import com.demo.model.DashboardReclamo;

@Local
public interface IDashboardReclamoDAO {
	
	List<DashboardReclamo> listar();

}
