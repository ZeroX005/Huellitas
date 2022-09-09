package com.demo.model;

public class DashboardUsuario {
	
	private String rol;
	private String cantidad;
	
	public DashboardUsuario() {}
	
	public DashboardUsuario(String rol, String cantidad) {
		super();
		this.rol = rol;
		this.cantidad = cantidad;
	}
	
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public String getCantidad() {
		return cantidad;
	}
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	

}
