package com.demo.model;

public class Tarjeta {
	
    private Integer cod_tarjeta;
    private String nro_tarjeta;
    private String nombre_propietario;
    private String apellido_propietario;
    private Integer cod_cliente;
    
    public Tarjeta() {}
  
    
	
	public Tarjeta(Integer cod_tarjeta, String nro_tarjeta, String nombre_propietario, String apellido_propietario,
			Integer cod_cliente) {
		super();
		this.cod_tarjeta = cod_tarjeta;
		this.nro_tarjeta = nro_tarjeta;
		this.nombre_propietario = nombre_propietario;
		this.apellido_propietario = apellido_propietario;
		this.cod_cliente = cod_cliente;
	}

	public Tarjeta(String nro_tarjeta, String nombre_propietario, String apellido_propietario, Integer cod_cliente) {
		super();
		this.nro_tarjeta = nro_tarjeta;
		this.nombre_propietario = nombre_propietario;
		this.apellido_propietario = apellido_propietario;
		this.cod_cliente = cod_cliente;
	}

	public Integer getCod_tarjeta() {
		return cod_tarjeta;
	}
	public void setCod_tarjeta(Integer cod_tarjeta) {
		this.cod_tarjeta = cod_tarjeta;
	}
	public String getNro_tarjeta() {
		return nro_tarjeta;
	}
	public void setNro_tarjeta(String nro_tarjeta) {
		this.nro_tarjeta = nro_tarjeta;
	}
	public String getNombre_propietario() {
		return nombre_propietario;
	}
	public void setNombre_propietario(String nombre_propietario) {
		this.nombre_propietario = nombre_propietario;
	}
	public String getApellido_propietario() {
		return apellido_propietario;
	}
	public void setApellido_propietario(String apellido_propietario) {
		this.apellido_propietario = apellido_propietario;
	}
	public Integer getCod_cliente() {
		return cod_cliente;
	}
	public void setCod_cliente(Integer cod_cliente) {
		this.cod_cliente = cod_cliente;
	}
    
    

}
