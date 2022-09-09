package com.demo.model;

public class Usuario {

	private Integer codigo;
	private String usuario;
	private String contrasena;
	private String nombres;
	private String apellidos;
	private String dni;
	private String direccion;
	private Integer celular;
	private String fecha_nacimiento;
	private String correo;
	private String tipo;
	
	public Usuario() {}
	
	
	
	public Usuario(Integer codigo, String usuario, String contrasena, String nombres, String apellidos, String dni,
			String direccion, Integer celular, String fecha_nacimiento, String correo, String tipo) {
		super();
		this.codigo = codigo;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.dni = dni;
		this.direccion = direccion;
		this.celular = celular;
		this.fecha_nacimiento = fecha_nacimiento;
		this.correo = correo;
		this.tipo = tipo;
	}



	public Usuario(Integer codigo, String usuario, String contrasena, String nombres, String apellidos,
			String fecha_nacimiento, String correo, String tipo) {
		super();
		this.codigo = codigo;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.fecha_nacimiento = fecha_nacimiento;
		this.correo = correo;
		this.tipo = tipo;
	}


	public Usuario(Integer codigo, String tipo) {
		super();
		this.codigo = codigo;
		this.tipo = tipo;
	}
	

	public Usuario(String usuario, String contrasena, String tipo) {
		super();
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.tipo = tipo;
	}


	public Usuario(String usuario, String contrasena) {
		super();
		this.usuario = usuario;
		this.contrasena = contrasena;
	}


	public Usuario(Integer codigo, String usuario, String contrasena, String nombres, String apellidos,
			String direccion, Integer celular, String fecha_nacimiento, String correo, String tipo) {
		super();
		this.codigo = codigo;
		this.usuario = usuario;
		this.contrasena = contrasena;
		this.nombres = nombres;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.celular = celular;
		this.fecha_nacimiento = fecha_nacimiento;
		this.correo = correo;
		this.tipo = tipo;
	}




	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContraseña(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Integer getCelular() {
		return celular;
	}

	public void setCelular(Integer celular) {
		this.celular = celular;
	}

	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	

	

	

}
