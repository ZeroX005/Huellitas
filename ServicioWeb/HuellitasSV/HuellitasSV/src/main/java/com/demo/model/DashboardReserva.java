package com.demo.model;

public class DashboardReserva {
	
	private String Estado;
	private String Cantidad;
	
	public DashboardReserva(String estado, String cantidad) {
		super();
		Estado = estado;
		Cantidad = cantidad;
	}
	
	public String getEstado() {
		return Estado;
	}
	public void setEstado(String estado) {
		Estado = estado;
	}
	public String getCantidad() {
		return Cantidad;
	}
	public void setCantidad(String cantidad) {
		Cantidad = cantidad;
	}
	
	

}
