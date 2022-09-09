package com.demo.model;

public class Reclamo {
	
	private String Nro_ticketReclamo;
	private String asunto_rec;
	private String detalle_rec;
	private String fh_recl_gen;
	private String nro_ticket;
	private String estado_rec;
	private String responsable;
	private String afectado;
	private Integer cod_responsable;
	private Integer cod_afectado;
	
	public Reclamo() {}
	
	public Reclamo(String nro_ticketReclamo, String asunto_rec, String detalle_rec, String fh_recl_gen,
			String nro_ticket, String estado_rec, String responsable, String afectado, Integer cod_responsable,
			Integer cod_afectado) {
		super();
		Nro_ticketReclamo = nro_ticketReclamo;
		this.asunto_rec = asunto_rec;
		this.detalle_rec = detalle_rec;
		this.fh_recl_gen = fh_recl_gen;
		this.nro_ticket = nro_ticket;
		this.estado_rec = estado_rec;
		this.responsable = responsable;
		this.afectado = afectado;
		this.cod_responsable = cod_responsable;
		this.cod_afectado = cod_afectado;
	}

	public Reclamo(String nro_ticketReclamo, String asunto_rec, String detalle_rec, String fh_recl_gen,
			String nro_ticket, String estado_rec, String responsable, Integer cod_responsable, Integer cod_afectado) {
		super();
		Nro_ticketReclamo = nro_ticketReclamo;
		this.asunto_rec = asunto_rec;
		this.detalle_rec = detalle_rec;
		this.fh_recl_gen = fh_recl_gen;
		this.nro_ticket = nro_ticket;
		this.estado_rec = estado_rec;
		this.responsable = responsable;
		this.cod_responsable = cod_responsable;
		this.cod_afectado = cod_afectado;
	}



	public String getNro_ticketReclamo() {
		return Nro_ticketReclamo;
	}

	public void setNro_ticketReclamo(String nro_ticketReclamo) {
		Nro_ticketReclamo = nro_ticketReclamo;
	}

	public String getAsunto_rec() {
		return asunto_rec;
	}

	public void setAsunto_rec(String asunto_rec) {
		this.asunto_rec = asunto_rec;
	}

	public String getDetalle_rec() {
		return detalle_rec;
	}

	public void setDetalle_rec(String detalle_rec) {
		this.detalle_rec = detalle_rec;
	}

	public String getFh_recl_gen() {
		return fh_recl_gen;
	}

	public void setFh_recl_gen(String fh_recl_gen) {
		this.fh_recl_gen = fh_recl_gen;
	}

	public String getNro_ticket() {
		return nro_ticket;
	}

	public void setNro_ticket(String nro_ticket) {
		this.nro_ticket = nro_ticket;
	}

	public String getEstado_rec() {
		return estado_rec;
	}

	public void setEstado_rec(String estado_rec) {
		this.estado_rec = estado_rec;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}
	
	public String getAfectado() {
		return afectado;
	}

	public void setAfectado(String afectado) {
		this.afectado = afectado;
	}

	public Integer getCod_responsable() {
		return cod_responsable;
	}

	public void setCod_responsable(Integer cod_responsable) {
		this.cod_responsable = cod_responsable;
	}

	public Integer getCod_afectado() {
		return cod_afectado;
	}

	public void setCod_afectado(Integer cod_afectado) {
		this.cod_afectado = cod_afectado;
	}
}
	
	