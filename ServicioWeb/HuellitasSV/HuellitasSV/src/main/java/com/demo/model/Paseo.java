package com.demo.model;

public class Paseo {
	
	private String nro_ticket;          
	private String fecha_r;              
    private String hora_r;
	private String direccion_r;
	private Integer tiempo_paseo_r; 
	private Integer pago_r; 
	private Integer precio_r;
	private String fh_res_gen;
	private String estado_r;
	private String metodopago;
    private Integer cod_usuario_cli;
	private Integer cod_usuario_petw;
    private String cliente;
    private String petwalker;
	
	public Paseo() {}
	
	public Paseo(String nro_ticket, String fecha_r, String hora_r, String direccion_r, Integer tiempo_paseo_r,
			Integer pago_r, Integer precio_r, String fh_res_gen, String estado_r, String metodopago,
			Integer cod_usuario_cli, Integer cod_usuario_petw, String cliente, String petwalker) {
		super();
		this.nro_ticket = nro_ticket;
		this.fecha_r = fecha_r;
		this.hora_r = hora_r;
		this.direccion_r = direccion_r;
		this.tiempo_paseo_r = tiempo_paseo_r;
		this.pago_r = pago_r;
		this.precio_r = precio_r;
		this.fh_res_gen = fh_res_gen;
		this.estado_r = estado_r;
		this.metodopago = metodopago;
		this.cod_usuario_cli = cod_usuario_cli;
		this.cod_usuario_petw = cod_usuario_petw;
		this.cliente = cliente;
		this.petwalker = petwalker;
	}
	
	
	public Paseo(String nro_ticket, String fh_res_gen, String estado_r) {
		super();
		this.nro_ticket = nro_ticket;
		this.fh_res_gen = fh_res_gen;
		this.estado_r = estado_r;
	}

	public String getNro_ticket() {
		return nro_ticket;
	}

	public void setNro_ticket(String nro_ticket) {
		this.nro_ticket = nro_ticket;
	}

	public String getFecha_r() {
		return fecha_r;
	}

	public void setFecha_r(String fecha_r) {
		this.fecha_r = fecha_r;
	}

	public String getHora_r() {
		return hora_r;
	}

	public void setHora_r(String hora_r) {
		this.hora_r = hora_r;
	}

	public String getDireccion_r() {
		return direccion_r;
	}

	public void setDireccion_r(String direccion_r) {
		this.direccion_r = direccion_r;
	}

	public Integer getTiempo_paseo_r() {
		return tiempo_paseo_r;
	}

	public void setTiempo_paseo_r(Integer tiempo_paseo_r) {
		this.tiempo_paseo_r = tiempo_paseo_r;
	}

	public Integer getPago_r() {
		return pago_r;
	}

	public void setPago_r(Integer pago_r) {
		this.pago_r = pago_r;
	}

	public Integer getPrecio_r() {
		return precio_r;
	}

	public void setPrecio_r(Integer precio_r) {
		this.precio_r = precio_r;
	}

	public String getFh_res_gen() {
		return fh_res_gen;
	}

	public void setFh_res_gen(String fh_res_gen) {
		this.fh_res_gen = fh_res_gen;
	}

	public String getEstado_r() {
		return estado_r;
	}

	public void setEstado_r(String estado_r) {
		this.estado_r = estado_r;
	}

	public String getMetodopago() {
		return metodopago;
	}

	public void setMetodopago(String metodopago) {
		this.metodopago = metodopago;
	}

	public Integer getCod_usuario_cli() {
		return cod_usuario_cli;
	}

	public void setCod_usuario_cli(Integer cod_usuario_cli) {
		this.cod_usuario_cli = cod_usuario_cli;
	}

	public Integer getCod_usuario_petw() {
		return cod_usuario_petw;
	}

	public void setCod_usuario_petw(Integer cod_usuario_petw) {
		this.cod_usuario_petw = cod_usuario_petw;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public String getPetwalker() {
		return petwalker;
	}

	public void setPetwalker(String petwalker) {
		this.petwalker = petwalker;
	}


	
	

}
