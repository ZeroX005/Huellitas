package com.example.edu.huellitas.Modelos;

import android.os.Parcel;
import android.os.Parcelable;

public class Tarjeta implements Parcelable {

    private int cod_tarjeta;
    private String nro_tarjeta;
    private String nombre_propietario;
    private String apellido_propietario;
    private int cod_cliente;


    public Tarjeta(String nro_tarjeta, String nombre_propietario, String apellido_propietario, int cod_cliente) {
        this.nro_tarjeta = nro_tarjeta;
        this.nombre_propietario = nombre_propietario;
        this.apellido_propietario = apellido_propietario;
        this.cod_cliente = cod_cliente;
    }

    public Tarjeta(int cod_tarjeta, String nro_tarjeta, String nombre_propietario, String apellido_propietario, int cod_cliente) {
        this.cod_tarjeta = cod_tarjeta;
        this.nro_tarjeta = nro_tarjeta;
        this.nombre_propietario = nombre_propietario;
        this.apellido_propietario = apellido_propietario;
        this.cod_cliente = cod_cliente;
    }

    protected Tarjeta(Parcel in) {
        cod_tarjeta = in.readInt();
        nro_tarjeta = in.readString();
        nombre_propietario = in.readString();
        apellido_propietario = in.readString();
        cod_cliente = in.readInt();
    }

    public static final Creator<Tarjeta> CREATOR = new Creator<Tarjeta>() {
        @Override
        public Tarjeta createFromParcel(Parcel in) {
            return new Tarjeta(in);
        }

        @Override
        public Tarjeta[] newArray(int size) {
            return new Tarjeta[size];
        }
    };

    public int getCod_tarjeta() {
        return cod_tarjeta;
    }

    public void setCod_tarjeta(int cod_tarjeta) {
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

    public int getCod_cliente() {
        return cod_cliente;
    }

    public void setCod_cliente(int cod_cliente) {
        this.cod_cliente = cod_cliente;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(cod_tarjeta);
        parcel.writeString(nro_tarjeta);
        parcel.writeString(nombre_propietario);
        parcel.writeString(apellido_propietario);
        parcel.writeInt(cod_cliente);
    }
}
