package com.demo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.demo.dao.Conexion;
import com.demo.dao.IDashboardUsuarioDAO;
import com.demo.model.DashboardUsuario;

@Stateless
public class DashboardUsuarioDAOImpl implements IDashboardUsuarioDAO{
	
	private Connection cx;
	
	public DashboardUsuarioDAOImpl() {
		cx = Conexion.conectar();
	}
	
	@Override
	public List<DashboardUsuario> listar() {
		List<DashboardUsuario> dbu = new ArrayList<>();
		try {
			String sql = "select * from CANTIDAD_USUARIO";
			PreparedStatement preparedStatement = cx.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				dbu.add( new DashboardUsuario(
						resultSet.getString("Rol"),
						resultSet.getString("Cantidad")));
			}
			resultSet.close();
			preparedStatement.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			dbu = new ArrayList<>();
		}
		return dbu;
	}

    }
	
	

