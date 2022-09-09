package com.demo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.demo.dao.Conexion;
import com.demo.dao.IDashboardReservaDAO;
import com.demo.model.DashboardReserva;

@Stateless
public class DashboardReservaDAOImpl implements IDashboardReservaDAO{
	
	private Connection cx;
	
	public DashboardReservaDAOImpl() {
		cx = Conexion.conectar();
	}
	
	@Override
	public List<DashboardReserva> listar() {
		List<DashboardReserva> dbres = new ArrayList<>();
		try {
			String sql = "select * from CANTIDAD_RESERVA_ESTADO";
			PreparedStatement preparedStatement = cx.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				dbres.add( new DashboardReserva(
						resultSet.getString("Estado"),
						resultSet.getString("Cantidad")));
			}
			resultSet.close();
			preparedStatement.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			dbres = new ArrayList<>();
		}
		return dbres;
	}

}
