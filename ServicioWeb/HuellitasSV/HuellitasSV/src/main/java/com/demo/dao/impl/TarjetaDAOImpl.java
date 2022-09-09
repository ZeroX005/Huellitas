package com.demo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.demo.dao.Conexion;
import com.demo.dao.ITarjetaDAO;
import com.demo.model.Tarjeta;

@Stateless
public class TarjetaDAOImpl implements ITarjetaDAO{
	
	private Connection cx;
	
	public TarjetaDAOImpl() {
		cx = Conexion.conectar();
	}

	@Override
	public Tarjeta registrar(Tarjeta tarjeta) {
		try {
			String sql = "insert into tarjeta_pago values (default,?,?,?,?)";
			PreparedStatement ps = cx.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, tarjeta.getNro_tarjeta());
			ps.setString(2, tarjeta.getNombre_propietario());
			ps.setString(3, tarjeta.getApellido_propietario());
			ps.setInt(4, tarjeta.getCod_cliente());
			ps.executeUpdate();
			ResultSet rs = ps.getGeneratedKeys();
			int generatedKeys = 0;
			if(rs.next()) {
				generatedKeys = rs.getInt(1);
			}
			rs.close();
			tarjeta.setCod_tarjeta(generatedKeys);
			ps.close();
			return tarjeta;
				
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Tarjeta> listar(Tarjeta tarjeta) {
		List<Tarjeta> tarj = new ArrayList<>();
		try{
			String sql = "select * from tarjeta_pago where cod_cliente = ?";
			PreparedStatement ps = cx.prepareStatement(sql);
			ps.setInt(1, tarjeta.getCod_cliente());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				tarj.add(new Tarjeta(rs.getInt("cod_tarjeta"),
									 rs.getString("nro_tarjeta"),
									 rs.getString("nombre_propietario"),
									 rs.getString("apellido_propietario"),
									 rs.getInt("cod_cliente")));
			}
			rs.close();
			ps.close();
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
		return tarj;
	}

}
