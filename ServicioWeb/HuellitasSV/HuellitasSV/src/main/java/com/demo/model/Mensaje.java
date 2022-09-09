package com.demo.model;

import java.util.List;

public class Mensaje<T> {

	public boolean rpta;
	public String mensaje;
	public String idusuario;
	public String tipoUsr;
	
	public List<T> objeto;
	
	public Mensaje() {}
	
	public Mensaje(boolean rpta, String mensaje, String idusuario, String tipoUsr, List<T> objeto) {
		super();
		this.rpta = rpta;
		this.mensaje = mensaje;
		this.idusuario = idusuario;
		this.tipoUsr = tipoUsr;
		this.objeto = objeto;
	}



	public boolean isRpta() {
		return rpta;
	}

	public void setRpta(boolean rpta) {
		this.rpta = rpta;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(String idusuario) {
		this.idusuario = idusuario;
	}

	public String getTipoUsr() {
		return tipoUsr;
	}

	public void setTipoUsr(String tipoUsr) {
		this.tipoUsr = tipoUsr;
	}

	public List<T> getObjeto() {
		return objeto;
	}

	public void setObjeto(List<T> objeto) {
		this.objeto = objeto;
	}
	

	

	
	

	
	
	
}
