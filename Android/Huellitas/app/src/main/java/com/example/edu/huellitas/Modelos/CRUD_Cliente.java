package com.example.edu.huellitas.Modelos;

import android.os.Parcel;
import android.os.Parcelable;

public class CRUD_Cliente implements Parcelable {

    private int codigo;
    private String nombres;
    private String apellidos;
    private String usuario;
    private int celular;
    private String contrasena;
    private String direccion;
    private String tipo;
    private String correo;
    private String fecha_nacimiento;

    public CRUD_Cliente(int codigo, String nombres, String apellidos, String usuario, int celular, String contrasena, String direccion, String tipo, String correo, String fecha_nacimiento) {
        this.codigo = codigo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.usuario = usuario;
        this.celular = celular;
        this.contrasena = contrasena;
        this.direccion = direccion;
        this.tipo = tipo;
        this.correo = correo;
        this.fecha_nacimiento = fecha_nacimiento;
    }

    protected CRUD_Cliente(Parcel in) {
        codigo = in.readInt();
        nombres = in.readString();
        apellidos = in.readString();
        usuario = in.readString();
        celular = in.readInt();
        contrasena = in.readString();
        direccion = in.readString();
        tipo = in.readString();
        correo = in.readString();
        fecha_nacimiento = in.readString();
    }

    public static final Creator<CRUD_Cliente> CREATOR = new Creator<CRUD_Cliente>() {
        @Override
        public CRUD_Cliente createFromParcel(Parcel in) {
            return new CRUD_Cliente(in);
        }

        @Override
        public CRUD_Cliente[] newArray(int size) {
            return new CRUD_Cliente[size];
        }
    };

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(codigo);
        parcel.writeString(nombres);
        parcel.writeString(apellidos);
        parcel.writeString(usuario);
        parcel.writeInt(celular);
        parcel.writeString(contrasena);
        parcel.writeString(direccion);
        parcel.writeString(tipo);
        parcel.writeString(correo);
        parcel.writeString(fecha_nacimiento);
    }
}
