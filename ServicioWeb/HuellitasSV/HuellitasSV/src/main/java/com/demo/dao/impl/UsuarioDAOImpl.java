package com.demo.dao.impl;

import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.demo.dao.Conexion;
import com.demo.dao.IUsuarioDAO;
import com.demo.model.Usuario;

@Stateless
public class UsuarioDAOImpl implements IUsuarioDAO{

	private Connection cx;
	
	public UsuarioDAOImpl() {
		cx = Conexion.conectar();
	}
	
	/*------------CRUD---------------*/
	
	@Override
	public List<Usuario> listadocli() {
		List<Usuario> u = new ArrayList<>();
		try {
			String sql = "select * from perfil_cliente2";
			PreparedStatement preparedStatement = cx.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				u.add(new Usuario(rs.getInt("cod_usuario"),
								  rs.getString("usuario_u"),
								  rs.getString("contrasena"),
								  rs.getString("nomb_cli"),
								  rs.getString("ape_cli"),
								  rs.getString("direcc_cli"),
								  rs.getInt("cel_cli"),
								  rs.getString("fh_naci_cli"),
								  rs.getString("correo_cli"),
								  rs.getString("TIPO")));
			}
			rs.close();
			preparedStatement.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
			u = new ArrayList<>();
		}
		return u;
	}
	
	
	@Override
	public List<Usuario> listadopetw() {
		List<Usuario> u = new ArrayList<>();
		try {
			String sql = "select * from perfil_petwalker2";
			PreparedStatement preparedStatement = cx.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				u.add(new Usuario(rs.getInt("cod_usuario"),
								  rs.getString("usuario_u"),
								  rs.getString("contrasena"),
								  rs.getString("nomb_petw"),
								  rs.getString("ape_petw"),
								  rs.getString("dni_petw"),
								  rs.getString("direcc_petw"),
								  rs.getInt("cel_petw"),
								  rs.getString("fh_naci_petw"),
								  rs.getString("correo_petw"),
								  rs.getString("TIPO")));
			}
			rs.close();
			preparedStatement.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
			u = new ArrayList<>();
		}
		return u;
	}
	
	
	@Override
	public Usuario registroCLI(Usuario usuario) {
		try {
			String sql = "{CALL INSERT_USER_CLIENTE (?,?,?,?,?,?,?,?,?)}";
			CallableStatement callableStatement = cx.prepareCall(sql);
			callableStatement.setString(1, usuario.getUsuario());
			callableStatement.setString(2, usuario.getContrasena());
			callableStatement.setString(3, usuario.getTipo());
			callableStatement.setString(4, usuario.getNombres());
			callableStatement.setString(5, usuario.getApellidos());
			callableStatement.setString(6, usuario.getDireccion());
			callableStatement.setInt(7, usuario.getCelular());
			callableStatement.setString(8, usuario.getFecha_nacimiento());
			callableStatement.setString(9, usuario.getCorreo());
			callableStatement.executeUpdate();	
			ResultSet resultSet = callableStatement.getGeneratedKeys();
			int generatedKey = 0;
			if (resultSet.next()) {
	
				generatedKey = resultSet.getInt(1);
			}
			resultSet.close();
			usuario.setCodigo(generatedKey);
			callableStatement.close();
			return usuario;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}
	
	@Override
	public Usuario registroPETW(Usuario usuario) {
		try {
			String sql = "{CALL INSERT_USER_PETWALKER (?,?,?,?,?,?,?,?,?,?)}";
			CallableStatement callableStatement = cx.prepareCall(sql);
			callableStatement.setString(1, usuario.getUsuario());
			callableStatement.setString(2, usuario.getContrasena());
			callableStatement.setString(3, usuario.getTipo());
			callableStatement.setString(4, usuario.getNombres());
			callableStatement.setString(5, usuario.getApellidos());
			callableStatement.setString(6, usuario.getDni());
			callableStatement.setString(7, usuario.getDireccion());
			callableStatement.setString(8, usuario.getFecha_nacimiento());
			callableStatement.setInt(9, usuario.getCelular());
			callableStatement.setString(10, usuario.getCorreo());
			callableStatement.executeUpdate();	
			ResultSet resultSet = callableStatement.getGeneratedKeys();
			int generatedKey = 0;
			if (resultSet.next()) {
	
				generatedKey = resultSet.getInt(1);
			}
			resultSet.close();
			usuario.setCodigo(generatedKey);
			callableStatement.close();
			return usuario;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}
	
	@Override
	public Usuario updateCLI(Usuario usuario) {
		try {
			String sql = "{CALL UPDATE_USER_CLIENTE (?,?,?,?,?,?,?,?,?,?)}";
			CallableStatement callableStatement = cx.prepareCall(sql);
			callableStatement.setInt(1, usuario.getCodigo());
			callableStatement.setString(2, usuario.getUsuario());
			callableStatement.setString(3, usuario.getContrasena());
			callableStatement.setString(4, usuario.getTipo());
			callableStatement.setString(5, usuario.getNombres());
			callableStatement.setString(6, usuario.getApellidos());
			callableStatement.setString(7, usuario.getDireccion());
			callableStatement.setInt(8, usuario.getCelular());
			callableStatement.setString(9, usuario.getFecha_nacimiento());
			callableStatement.setString(10, usuario.getCorreo());
			callableStatement.executeUpdate();	
			ResultSet resultSet = callableStatement.getGeneratedKeys();
			int generatedKey = 0;
			if (resultSet.next()) {
	
				generatedKey = resultSet.getInt(1);
			}
			resultSet.close();
			usuario.setCodigo(generatedKey);
			callableStatement.close();
			return usuario;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}
	
	@Override
	public Usuario updatePETW(Usuario usuario) {
		try {
			String sql = "{CALL UPDATE_USER_PETWALKER (?,?,?,?,?,?,?,?,?,?,?)}";
			CallableStatement callableStatement = cx.prepareCall(sql);
			callableStatement.setInt(1, usuario.getCodigo());
			callableStatement.setString(2, usuario.getUsuario());
			callableStatement.setString(3, usuario.getContrasena());
			callableStatement.setString(4, usuario.getTipo());
			callableStatement.setString(5, usuario.getNombres());
			callableStatement.setString(6, usuario.getApellidos());
			callableStatement.setString(7, usuario.getDni());
			callableStatement.setString(8, usuario.getDireccion());
			callableStatement.setString(9, usuario.getFecha_nacimiento());
			callableStatement.setInt(10, usuario.getCelular());
			callableStatement.setString(11, usuario.getCorreo());
			callableStatement.executeUpdate();	
			ResultSet resultSet = callableStatement.getGeneratedKeys();
			int generatedKey = 0;
			if (resultSet.next()) {
	
				generatedKey = resultSet.getInt(1);
			}
			resultSet.close();
			usuario.setCodigo(generatedKey);
			callableStatement.close();
			return usuario;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}
	
	@Override
	public Usuario eliminarCLI(Usuario usuario) {
		try {
			String sql = "{CALL ELIMINAR_CLIENTE (?)}";
			CallableStatement callableStatement = cx.prepareCall(sql);
			callableStatement.setInt(1, usuario.getCodigo());
			callableStatement.executeUpdate();
			ResultSet resultSet = callableStatement.getGeneratedKeys();
			int generatedKey = 0;
			if (resultSet.next()) {
	
				generatedKey = resultSet.getInt(1);
			}
			resultSet.close();
			usuario.setCodigo(generatedKey);
			callableStatement.close();
			return usuario;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	@Override
	public Usuario eliminarPETW(Usuario usuario) {
		try {
			String sql = "{CALL ELIMINAR_PETWALKER (?)}";
			CallableStatement callableStatement = cx.prepareCall(sql);
			callableStatement.setInt(1, usuario.getCodigo());
			callableStatement.executeUpdate();
			ResultSet resultSet = callableStatement.getGeneratedKeys();
			int generatedKey = 0;
			if (resultSet.next()) {
	
				generatedKey = resultSet.getInt(1);
			}
			resultSet.close();
			usuario.setCodigo(generatedKey);
			callableStatement.close();
			return usuario;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	
	
	
	/*-------------------------------*/
	
	
	@Override
	public List<Usuario> login() {
		List<Usuario> usuario = new ArrayList<>();
		try {
			String sql = "SELECT u.usuario_u,u.contrasena,t.rol as TIPO FROM USUARIO u inner join tipo_usuario t where u.cod_tipousuario = t.cod_tipousuario";
			PreparedStatement preparedStatement = cx.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				usuario.add( new Usuario(
						resultSet.getString("usuario_u"),
						resultSet.getString("contrasena"),
						resultSet.getString("TIPO")));
			}
			resultSet.close();
			preparedStatement.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			usuario = new ArrayList<>();
		}
		return usuario;
	
    }
	
	@Override
	public Usuario autenticar(Usuario u) {
		List<Usuario> usr = new ArrayList<>();
		try {
			String sql = "SELECT u.cod_usuario,t.rol as TIPO FROM USUARIO u inner join tipo_usuario t where u.cod_tipousuario = t.cod_tipousuario and usuario_u=? and contrasena=?";
			PreparedStatement preparedStatement = cx.prepareStatement(sql);
			preparedStatement.setString(1, u.getUsuario());
			preparedStatement.setString(2, u.getContrasena());
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			if(resultSet.getString("cod_usuario").equals("")) {
				return null;
			}else {
				usr.add(new Usuario(resultSet.getInt("cod_usuario"),
								    resultSet.getString("TIPO")));
			/*String id = resultSet.getString("cod_usuario");*/
			resultSet.close();
			preparedStatement.close();
			Usuario usuario = usr != null && !usr.isEmpty() ? usr.get(0):new Usuario();
			return usuario;
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}
	
	@Override
	public Usuario registrar(Usuario usuario) {
		try {
			String sql = "{CALL INSERT_USER_CLIENTE (?,?,?,?,?,?,?,?,?)}";
			CallableStatement callableStatement = cx.prepareCall(sql);
			callableStatement.setString(1, usuario.getUsuario());
			callableStatement.setString(2, usuario.getContrasena());
			callableStatement.setString(3, usuario.getTipo());
			callableStatement.setString(4, usuario.getNombres());
			callableStatement.setString(5, usuario.getApellidos());
			callableStatement.setString(6, usuario.getDireccion());
			callableStatement.setInt(7, usuario.getCelular());
			callableStatement.setString(8, usuario.getFecha_nacimiento());
			callableStatement.setString(9, usuario.getCorreo());
			callableStatement.executeUpdate();	
			ResultSet resultSet = callableStatement.getGeneratedKeys();
			int generatedKey = 0;
			if (resultSet.next()) {
	
				generatedKey = resultSet.getInt(1);
			}
			resultSet.close();
			usuario.setCodigo(generatedKey);
			callableStatement.close();
			return usuario;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}
	
	@Override
	public Usuario perfil(Usuario usuario) {
		List<Usuario> u = new ArrayList<>();
		try {
			String sql = "select * from perfil_cliente where cod_usuario = ?";
			PreparedStatement preparedStatement = cx.prepareStatement(sql);
			preparedStatement.setInt(1, usuario.getCodigo());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				u.add(new Usuario(rs.getInt("cod_usuario"),
								  rs.getString("usuario_u"),
								  rs.getString("contrasena"),
								  rs.getString("nomb_cli"),
								  rs.getString("ape_cli"),
								  rs.getString("direcc_cli"),
								  rs.getInt("cel_cli"),
								  rs.getString("fh_naci_cli"),
								  rs.getString("correo_cli"),
								  rs.getString("TIPO")));
			}
			rs.close();
			preparedStatement.close();
			Usuario us = u != null && !u.isEmpty() ? u.get(0):new Usuario();
			return us;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}

	}
	
	
	@Override
	public Usuario perfilP(Usuario usuario) {
		List<Usuario> u = new ArrayList<>();
		try {
			String sql = "select * from perfil_petwalker where cod_usuario = ?";
			PreparedStatement preparedStatement = cx.prepareStatement(sql);
			preparedStatement.setInt(1, usuario.getCodigo());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				u.add(new Usuario(rs.getInt("cod_usuario"),
								  rs.getString("usuario_u"),
								  rs.getString("contrasena"),
								  rs.getString("nomb_petw"),
								  rs.getString("ape_petw"),
								  rs.getString("direcc_petw"),
								  rs.getInt("cel_petw"),
								  rs.getString("fh_naci_petw"),
								  rs.getString("correo_petw"),
								  rs.getString("TIPO")));
			}
			rs.close();
			preparedStatement.close();
			Usuario us = u != null && !u.isEmpty() ? u.get(0):new Usuario();
			return us;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	@Override
	public Usuario perfilA(Usuario usuario) {
		List<Usuario> u = new ArrayList<>();
		try {
			String sql = "select * from perfil_administrador where cod_usuario = ?";
			PreparedStatement preparedStatement = cx.prepareStatement(sql);
			preparedStatement.setInt(1, usuario.getCodigo());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				u.add(new Usuario(rs.getInt("cod_usuario"),
								  rs.getString("usuario_u"),
								  rs.getString("contrasena"),
								  rs.getString("nomb_adm"),
								  rs.getString("ape_adm"),
								  rs.getString("fh_naci_adm"),
								  rs.getString("correo_adm"),
								  rs.getString("TIPO")));
			}
			rs.close();
			preparedStatement.close();
			Usuario us = u != null && !u.isEmpty() ? u.get(0):new Usuario();
			return us;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
