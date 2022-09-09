package com.demo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.demo.dao.Conexion;
import com.demo.dao.IPaseoDAO;
import com.demo.model.Paseo;



@Stateless
public class PaseoDAOImpl implements IPaseoDAO{
	
	private Connection cx;
	
	public PaseoDAOImpl() {
		cx = Conexion.conectar();
	}
	
	/*-------------CRUD--------------*/
	
	@Override
	public List<Paseo> listado(){
		List<Paseo> pa = new ArrayList<>();
		try{
			String sql = "select * from listado_reservas";
			PreparedStatement ps = cx.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				pa.add(new Paseo(rs.getString("nro_ticket"),
						  		 rs.getString("fecha_r"),
						  		 rs.getString("hora_r"),
						  		 rs.getString("direccion_r"),
						  		 rs.getInt("tiempo_paseo"),
						  		 rs.getInt("pagar_con"),
						  		 rs.getInt("precio"),
						  		 rs.getString("fh_res_gen"),
						  		 rs.getString("Estado"),
						  		 rs.getString("Tipo_pago"),
						  		 rs.getInt("cod_usuario_cli"),
						  		 rs.getInt("cod_usuario_petw"),
						  		 rs.getString("cliente"),
						  		 rs.getString("petwalker")));
			}
			rs.close();
			ps.close();
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			pa = new ArrayList<>();
		}
		return pa;
	}
	
	@Override
	public Paseo actualizar(Paseo paseo) {
		try {
			String sql = "{CALL UPDATE_RESERVA (?,?,?,?,?,?,?,?,?)}";
			CallableStatement cs = cx.prepareCall(sql);
			cs.setString(1, paseo.getNro_ticket());
			cs.setString(2, paseo.getFecha_r());
			cs.setString(3, paseo.getHora_r());
			cs.setString(4, paseo.getDireccion_r());
			cs.setInt(5, paseo.getTiempo_paseo_r());
			cs.setInt(6, paseo.getPago_r());
			cs.setInt(7, paseo.getPrecio_r());
			cs.setString(8, paseo.getEstado_r());
			cs.setString(9, paseo.getMetodopago());
			cs.executeUpdate();
			ResultSet rs = cs.getGeneratedKeys();
			String generatedKey = "";
			if (rs.next()) {
			    generatedKey = rs.getString(1);
			}
			rs.close();
			paseo.setNro_ticket(generatedKey);
			cs.close();
			return paseo;
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}
	
	/*-------------------------------*/
	
	/*-----------CLIENTE-------------*/
	
	@Override
	public Paseo reservarPaseo(Paseo paseo) {
		try {
			String sql = "{CALL REGISTRO_RESERVA_HR (CONCAT(YEAR(now()),MONTH(now()),DAY(now()),HOUR(now()),MINUTE(now()),SECOND(now())),?,?,?,?,?,?,now(),?,?,?,null)}";
			CallableStatement cs = cx.prepareCall(sql);
			cs.setString(1, paseo.getFecha_r());
			cs.setString(2, paseo.getHora_r());
			cs.setString(3, paseo.getDireccion_r());
			cs.setInt(4, paseo.getTiempo_paseo_r());
			cs.setInt(5, paseo.getPago_r());
			cs.setInt(6, paseo.getPrecio_r());
			cs.setString(7, paseo.getEstado_r());
			cs.setString(8, paseo.getMetodopago());
			cs.setInt(9, paseo.getCod_usuario_cli());
			cs.executeUpdate();
			ResultSet rs = cs.getGeneratedKeys();
			String generatedKey = "";
			if (rs.next()) {
			    generatedKey = rs.getString(1);
			}
			rs.close();
			paseo.setNro_ticket(generatedKey);
			cs.close();
			return paseo;
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}
	
	@Override
	public List<Paseo> listarHR(Paseo paseo){
		List<Paseo> pa = new ArrayList<>();
		try{
			String sql = "select * from RESERVA_FINALIZADO where cod_usuario_cli=?";
			PreparedStatement ps = cx.prepareStatement(sql);
			ps.setInt(1, paseo.getCod_usuario_cli());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				pa.add(new Paseo(rs.getString("nro_ticket"),
						  		 rs.getString("fecha_r"),
						  		 rs.getString("hora_r"),
						  		 rs.getString("direccion_r"),
						  		 rs.getInt("tiempo_paseo"),
						  		 rs.getInt("pagar_con"),
						  		 rs.getInt("precio"),
						  		 rs.getString("fh_res_gen"),
						  		 rs.getString("Estado"),
						  		 rs.getString("Tipo_pago"),
						  		 rs.getInt("cod_usuario_cli"),
						  		 rs.getInt("cod_usuario_petw"),
						  		 rs.getString("cliente"),
						  		 rs.getString("petwalker")));
			}
			rs.close();
			ps.close();
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
		return pa;
	}
	
	@Override
	public List<Paseo> listarPP(Paseo paseo){
		List<Paseo> pa = new ArrayList<>();
		try{
			String sql = "select * from PASEOS_PENDIENTES where cod_usuario_cli=?";
			PreparedStatement ps = cx.prepareStatement(sql);
			ps.setInt(1, paseo.getCod_usuario_cli());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				pa.add(new Paseo(rs.getString("nro_ticket"),
						  		 rs.getString("fecha_r"),
						  		 rs.getString("hora_r"),
						  		 rs.getString("direccion_r"),
						  		 rs.getInt("tiempo_paseo"),
						  		 rs.getInt("pagar_con"),
						  		 rs.getInt("precio"),
						  		 rs.getString("fh_res_gen"),
						  		 rs.getString("Estado"),
						  		 rs.getString("Tipo_pago"),
						  		 rs.getInt("cod_usuario_cli"),
						  		 rs.getInt("cod_usuario_petw"),
						  		 rs.getString("cliente"),
						  		 rs.getString("petwalker")));
			}
			rs.close();
			ps.close();
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
		return pa;
	}
	
	
	@Override
	public Paseo listarResDet(Paseo paseo){
		List<Paseo> pa = new ArrayList<>();
		try{
			String sql = "select * from DETALLE_RESERVA where nro_ticket=?";
			PreparedStatement ps = cx.prepareStatement(sql);
			ps.setString(1, paseo.getNro_ticket());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				pa.add(new Paseo(rs.getString("nro_ticket"),
						  		 rs.getString("fecha_r"),
						  		 rs.getString("hora_r"),
						  		 rs.getString("direccion_r"),
						  		 rs.getInt("tiempo_paseo"),
						  		 rs.getInt("pagar_con"),
						  		 rs.getInt("precio"),
						  		 rs.getString("fh_res_gen"),
						  		 rs.getString("Estado"),
						  		 rs.getString("Tipo_pago"),
						  		 rs.getInt("cod_usuario_cli"),
						  		 rs.getInt("cod_usuario_petw"),
						  		 rs.getString("cliente"),
						  		 rs.getString("petwalker")));
			}
			rs.close();
			ps.close();
			Paseo pseo = pa != null && !pa.isEmpty() ? pa.get(0):new Paseo();
			return pseo;
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
		
	}
	

	/*-----------PETWALKER-------------*/
	
	@Override
	public List<Paseo> listaPendientes(){
		List<Paseo> pa = new ArrayList<>();
		try{
			String sql = "select * from reserva_pendiente";
			PreparedStatement ps = cx.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				pa.add(new Paseo(rs.getString("nro_ticket"),
						  		 rs.getString("fecha_r"),
						  		 rs.getString("hora_r"),
						  		 rs.getString("direccion_r"),
						  		 rs.getInt("tiempo_paseo"),
						  		 rs.getInt("pagar_con"),
						  		 rs.getInt("precio"),
						  		 rs.getString("fh_res_gen"),
						  		 rs.getString("Estado"),
						  		 rs.getString("Tipo_pago"),
						  		 rs.getInt("cod_usuario_cli"),
						  		 rs.getInt("cod_usuario_petw"),
						  		 rs.getString("cliente"),
						  		 rs.getString("petwalker")));
			}
			rs.close();
			ps.close();
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
		return pa;
	}
	
	@Override
	public Paseo realizar(Paseo paseo) {
		try {
			String sql = "{CALL REALIZAR_RESERVA (?,?)}";
			CallableStatement cs = cx.prepareCall(sql);
			cs.setString(1, paseo.getNro_ticket());
			cs.setInt(2, paseo.getCod_usuario_petw());
			cs.executeUpdate();
			cs.close();
			return paseo;
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}
	
	@Override
	public List<Paseo> listaEncurso(Paseo paseo){
		List<Paseo> pa = new ArrayList<>();
		try{
			String sql = "select * from reserva_encurso where cod_usuario_petw=?";
			PreparedStatement ps = cx.prepareStatement(sql);
			ps.setInt(1, paseo.getCod_usuario_petw());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				pa.add(new Paseo(rs.getString("nro_ticket"),
						  		 rs.getString("fecha_r"),
						  		 rs.getString("hora_r"),
						  		 rs.getString("direccion_r"),
						  		 rs.getInt("tiempo_paseo"),
						  		 rs.getInt("pagar_con"),
						  		 rs.getInt("precio"),
						  		 rs.getString("fh_res_gen"),
						  		 rs.getString("Estado"),
						  		 rs.getString("Tipo_pago"),
						  		 rs.getInt("cod_usuario_cli"),
						  		 rs.getInt("cod_usuario_petw"),
						  		 rs.getString("cliente"),
						  		 rs.getString("petwalker")));
			}
			rs.close();
			ps.close();
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
		return pa;
	}
	

	@Override
	public Paseo finalizar(Paseo paseo) {
		try {
			String sql = "{CALL FINALIZAR_RESERVA (?)}";
			CallableStatement cs = cx.prepareCall(sql);
			cs.setString(1, paseo.getNro_ticket());
			cs.executeUpdate();
			cs.close();
			return paseo;
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}
	
	
	@Override
	public List<Paseo> listaRealizados(Paseo paseo){
		List<Paseo> pa = new ArrayList<>();
		try{
			String sql = "select * from RESERVA_FINALIZADO where cod_usuario_petw=?";
			PreparedStatement ps = cx.prepareStatement(sql);
			ps.setInt(1, paseo.getCod_usuario_petw());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				pa.add(new Paseo(rs.getString("nro_ticket"),
						  		 rs.getString("fecha_r"),
						  		 rs.getString("hora_r"),
						  		 rs.getString("direccion_r"),
						  		 rs.getInt("tiempo_paseo"),
						  		 rs.getInt("pagar_con"),
						  		 rs.getInt("precio"),
						  		 rs.getString("fh_res_gen"),
						  		 rs.getString("Estado"),
						  		 rs.getString("Tipo_pago"),
						  		 rs.getInt("cod_usuario_cli"),
						  		 rs.getInt("cod_usuario_petw"),
						  		 rs.getString("cliente"),
						  		 rs.getString("petwalker")));
			}
			rs.close();
			ps.close();
		}catch(Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
		return pa;
	}
	
		
}
