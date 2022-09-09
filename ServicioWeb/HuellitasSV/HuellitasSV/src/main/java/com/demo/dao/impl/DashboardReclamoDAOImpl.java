package com.demo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.demo.dao.Conexion;
import com.demo.dao.IDashboardReclamoDAO;
import com.demo.model.DashboardReclamo;

@Stateless
public class DashboardReclamoDAOImpl implements IDashboardReclamoDAO{
	
	private Connection cx;
	
	public DashboardReclamoDAOImpl() {
		cx = Conexion.conectar();
	}
	
	@Override
	public List<DashboardReclamo> listar() {
		List<DashboardReclamo> dbrcl = new ArrayList<>();
		try {
			String sql = "select * from CANTIDAD_RECLAMOS_PC";
			PreparedStatement preparedStatement = cx.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				dbrcl.add( new DashboardReclamo(
						resultSet.getString("AFECTADO"),
						resultSet.getString("CANTIDAD")));
			}
			resultSet.close();
			preparedStatement.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			dbrcl = new ArrayList<>();
		}
		return dbrcl;
	}

}
