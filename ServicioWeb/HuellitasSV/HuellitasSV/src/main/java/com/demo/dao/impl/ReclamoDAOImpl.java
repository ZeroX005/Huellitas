package com.demo.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.demo.dao.Conexion;
import com.demo.dao.IReclamoDAO;
import com.demo.model.Reclamo;

@Stateless
public class ReclamoDAOImpl implements IReclamoDAO{
	
	private Connection cx;
	
	public ReclamoDAOImpl() {
		cx = Conexion.conectar();
	}
	
	/*------------CRUD---------------*/
	
	@Override
	public List<Reclamo> reclamos(){
		List<Reclamo> re = new ArrayList<>();
		try{
			String sql = "select * from listado_reclamo";
			PreparedStatement ps = cx.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				re.add(new Reclamo(rs.getString("Nro_ticketReclamo"),
								  rs.getString("asunto_rec"),
								  rs.getString("detalle_rec"),
								  rs.getString("fh_recl_gen"),
								  rs.getString("nro_ticket"),
								  rs.getString("estado"),
								  rs.getString("responsable"),
								  rs.getString("afectado"),
								  rs.getInt("cod_responsable"),
								  rs.getInt("cod_afectado")));
			}
			rs.close();
			ps.close();
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			re = new ArrayList<>();
		}
		return re;
	}
	
	@Override
	public Reclamo actualizar(Reclamo reclamo) {
		try {
			String sql = "{CALL UPDATE_RECLAMO(?,?,?,?)}";
			CallableStatement cs = cx.prepareCall(sql);
			cs.setString(1, reclamo.getNro_ticketReclamo());
			cs.setString(2, reclamo.getAsunto_rec());
			cs.setString(3, reclamo.getDetalle_rec());
			cs.setString(4, reclamo.getEstado_rec());
			cs.executeUpdate();
			ResultSet rs = cs.getGeneratedKeys();
			String generatedKeys = "";
			if(rs.next()) {
				generatedKeys = rs.getString(1);
			}
			rs.close();
			reclamo.setNro_ticket(generatedKeys);
			cs.close();
			return reclamo;
				
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	
	
	/*-------------------------------*/
	
	@Override
	public Reclamo registrar(Reclamo reclamo) {
		try {
			String sql = "{CALL REGISTRO_RECLAMO(?,?,?,?,?)}";
			CallableStatement cs = cx.prepareCall(sql);
			cs.setString(1, reclamo.getAsunto_rec());
			cs.setString(2, reclamo.getDetalle_rec());
			cs.setString(3, reclamo.getNro_ticket());
			cs.setInt(4, reclamo.getCod_responsable());
			cs.setInt(5, reclamo.getCod_afectado());
			cs.executeUpdate();
			ResultSet rs = cs.getGeneratedKeys();
			String generatedKeys = "";
			if(rs.next()) {
				generatedKeys = rs.getString(1);
			}
			rs.close();
			reclamo.setNro_ticket(generatedKeys);
			cs.close();
			return reclamo;
				
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<Reclamo> listarCli(Reclamo reclamo){
		List<Reclamo> re = new ArrayList<>();
		try{
			String sql = "select * from listado_reclamo_cli where cod_afectado = ?";
			PreparedStatement ps = cx.prepareStatement(sql);
			ps.setInt(1, reclamo.getCod_afectado());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				re.add(new Reclamo(rs.getString("Nro_ticketReclamo"),
								  rs.getString("asunto_rec"),
								  rs.getString("detalle_rec"),
								  rs.getString("fh_recl_gen"),
								  rs.getString("nro_ticket"),
								  rs.getString("estado"),
								  rs.getString("responsable"),
								  rs.getInt("cod_responsable"),
								  rs.getInt("cod_afectado")));
			}
			rs.close();
			ps.close();
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
		return re;
	}
	
	@Override
	public List<Reclamo> listarPetw(Reclamo reclamo){
		List<Reclamo> re = new ArrayList<>();
		try{
			String sql = "select * from listado_reclamo_petw where cod_afectado = ?";
			PreparedStatement ps = cx.prepareStatement(sql);
			ps.setInt(1, reclamo.getCod_afectado());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				re.add(new Reclamo(rs.getString("Nro_ticketReclamo"),
						  rs.getString("asunto_rec"),
						  rs.getString("detalle_rec"),
						  rs.getString("fh_recl_gen"),
						  rs.getString("nro_ticket"),
						  rs.getString("estado"),
						  rs.getString("responsable"),
						  rs.getInt("cod_responsable"),
						  rs.getInt("cod_afectado")));
			}
			rs.close();
			ps.close();
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
		return re;
	}

}
