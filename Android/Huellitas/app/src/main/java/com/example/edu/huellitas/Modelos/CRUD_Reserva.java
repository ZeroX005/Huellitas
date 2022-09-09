package com.example.edu.huellitas.Modelos;

import android.os.Parcel;
import android.os.Parcelable;

public class CRUD_Reserva implements Parcelable {

    private String nro_ticket;
    private String fecha_r;
    private String hora_r;
    private String direccion_r;
    private int tiempo_paseo;
    private int pagar_con;
    private int precio;
    private String fh_res_gen;
    private String estado;
    private String tipo_pago;
    private int cod_usuario_cli;
    private int cod_usuario_petw;
    private String Cliente;
    private String Petwalker;

    public CRUD_Reserva(String nro_ticket, String fecha_r, String hora_r, String direccion_r, int tiempo_paseo, int pagar_con, int precio, String fh_res_gen, String estado, String tipo_pago, int cod_usuario_cli, int cod_usuario_petw, String cliente, String petwalker) {
        this.nro_ticket = nro_ticket;
        this.fecha_r = fecha_r;
        this.hora_r = hora_r;
        this.direccion_r = direccion_r;
        this.tiempo_paseo = tiempo_paseo;
        this.pagar_con = pagar_con;
        this.precio = precio;
        this.fh_res_gen = fh_res_gen;
        this.estado = estado;
        this.tipo_pago = tipo_pago;
        this.cod_usuario_cli = cod_usuario_cli;
        this.cod_usuario_petw = cod_usuario_petw;
        Cliente = cliente;
        Petwalker = petwalker;
    }

    protected CRUD_Reserva(Parcel in) {
        nro_ticket = in.readString();
        fecha_r = in.readString();
        hora_r = in.readString();
        direccion_r = in.readString();
        tiempo_paseo = in.readInt();
        pagar_con = in.readInt();
        precio = in.readInt();
        fh_res_gen = in.readString();
        estado = in.readString();
        tipo_pago = in.readString();
        cod_usuario_cli = in.readInt();
        cod_usuario_petw = in.readInt();
        Cliente = in.readString();
        Petwalker = in.readString();
    }

    public static final Creator<CRUD_Reserva> CREATOR = new Creator<CRUD_Reserva>() {
        @Override
        public CRUD_Reserva createFromParcel(Parcel in) {
            return new CRUD_Reserva(in);
        }

        @Override
        public CRUD_Reserva[] newArray(int size) {
            return new CRUD_Reserva[size];
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

    public int getTiempo_paseo() {
        return tiempo_paseo;
    }

    public void setTiempo_paseo(int tiempo_paseo) {
        this.tiempo_paseo = tiempo_paseo;
    }

    public int getPagar_con() {
        return pagar_con;
    }

    public void setPagar_con(int pagar_con) {
        this.pagar_con = pagar_con;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getFh_res_gen() {
        return fh_res_gen;
    }

    public void setFh_res_gen(String fh_res_gen) {
        this.fh_res_gen = fh_res_gen;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo_pago() {
        return tipo_pago;
    }

    public void setTipo_pago(String tipo_pago) {
        this.tipo_pago = tipo_pago;
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
        return Cliente;
    }

    public void setCliente(String cliente) {
        Cliente = cliente;
    }

    public String getPetwalker() {
        return Petwalker;
    }

    public void setPetwalker(String petwalker) {
        Petwalker = petwalker;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nro_ticket);
        parcel.writeString(fecha_r);
        parcel.writeString(hora_r);
        parcel.writeString(direccion_r);
        parcel.writeInt(tiempo_paseo);
        parcel.writeInt(pagar_con);
        parcel.writeInt(precio);
        parcel.writeString(fh_res_gen);
        parcel.writeString(estado);
        parcel.writeString(tipo_pago);
        parcel.writeInt(cod_usuario_cli);
        parcel.writeInt(cod_usuario_petw);
        parcel.writeString(Cliente);
        parcel.writeString(Petwalker);
    }
}
