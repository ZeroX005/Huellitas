package com.demo.model;

public class DashboardReclamo {
	
	private String afectado;
	private String cantidad;
	
	public DashboardReclamo(String afectado, String cantidad) {
		super();
		this.afectado = afectado;
		this.cantidad = cantidad;
	}
	
	public String getAfectado() {
		return afectado;
	}
	public void setAfectado(String afectado) {
		this.afectado = afectado;
	}
	public String getCantidad() {
		return cantidad;
	}
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	
	
	
	

}
