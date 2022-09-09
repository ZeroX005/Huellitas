package com.example.edu.huellitas.Modelos;

import android.os.Parcel;
import android.os.Parcelable;

public class ReservaP implements Parcelable {
    private String nro_ticket;
    private String fecha_r;
    private String hora_r;
    private String direccion_r;
    private int tiempo_paseo_r;
    private int pago_r;
    private int precio_r;
    private String fh_res_gen;
    private String estado_r;
    private String metodopago;
    private int cod_usuario_cli;
    private int cod_usuario_petw;
    private String cliente;
    private String petwalker;

    public ReservaP(){}

    public ReservaP(String nro_ticket, String fecha_r, String hora_r, String direccion_r, int tiempo_paseo_r, int pago_r, int precio_r, String fh_res_gen, String estado_r, String metodopago, int cod_usuario_cli, int cod_usuario_petw, String cliente) {
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
    }

    public ReservaP(String nro_ticket, String fh_res_gen, String estado_r) {
        this.nro_ticket = nro_ticket;
        this.fh_res_gen = fh_res_gen;
        this.estado_r = estado_r;
    }

    public ReservaP(String nro_ticket, String fecha_r, String hora_r, String direccion_r, int tiempo_paseo_r, int pago_r, int precio_r, String fh_res_gen, String estado_r, String metodopago, int cod_usuario_cli, int cod_usuario_petw, String cliente, String petwalker) {
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

    protected ReservaP(Parcel in) {
        nro_ticket = in.readString();
        fecha_r = in.readString();
        hora_r = in.readString();
        direccion_r = in.readString();
        tiempo_paseo_r = in.readInt();
        pago_r = in.readInt();
        precio_r = in.readInt();
        fh_res_gen = in.readString();
        estado_r = in.readString();
        metodopago = in.readString();
        cod_usuario_cli = in.readInt();
        cod_usuario_petw = in.readInt();
        cliente = in.readString();
        petwalker = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nro_ticket);
        dest.writeString(fecha_r);
        dest.writeString(hora_r);
        dest.writeString(direccion_r);
        dest.writeInt(tiempo_paseo_r);
        dest.writeInt(pago_r);
        dest.writeInt(precio_r);
        dest.writeString(fh_res_gen);
        dest.writeString(estado_r);
        dest.writeString(metodopago);
        dest.writeInt(cod_usuario_cli);
        dest.writeInt(cod_usuario_petw);
        dest.writeString(cliente);
        dest.writeString(petwalker);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ReservaP> CREATOR = new Creator<ReservaP>() {
        @Override
        public ReservaP createFromParcel(Parcel in) {
            return new ReservaP(in);
        }

        @Override
        public ReservaP[] newArray(int size) {
            return new ReservaP[size];
        }
    };

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

    public int getTiempo_paseo_r() {
        return tiempo_paseo_r;
    }

    public void setTiempo_paseo_r(int tiempo_paseo_r) {
        this.tiempo_paseo_r = tiempo_paseo_r;
    }

    public int getPago_r() {
        return pago_r;
    }

    public void setPago_r(int pago_r) {
        this.pago_r = pago_r;
    }

    public int getPrecio_r() {
        return precio_r;
    }

    public void setPrecio_r(int precio_r) {
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

    public int getCod_usuario_cli() {
        return cod_usuario_cli;
    }

    public void setCod_usuario_cli(int cod_usuario_cli) {
        this.cod_usuario_cli = cod_usuario_cli;
    }

    public int getCod_usuario_petw() {
        return cod_usuario_petw;
    }

    public void setCod_usuario_petw(int cod_usuario_petw) {
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
