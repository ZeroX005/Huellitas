package com.example.edu.huellitas.Modelos;

import android.os.Parcel;
import android.os.Parcelable;

public class Menu implements Parcelable {
    private int id;
    private String titulo;
    private int imagen;

    public Menu(Integer id, String titulo, Integer imagen) {
        this.id = id;
        this.titulo = titulo;
        this.imagen = imagen;
    }

    protected Menu(Parcel in) {
        id = in.readInt();
        titulo = in.readString();
        imagen = in.readInt();
    }

    public static final Creator<Menu> CREATOR = new Creator<Menu>() {
        @Override
        public Menu createFromParcel(Parcel in) {
            return new Menu(in);
        }

        @Override
        public Menu[] newArray(int size) {
            return new Menu[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getImagen() {
        return imagen;
    }

    public void setImagen(Integer imagen) {
        this.imagen = imagen;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(titulo);
        parcel.writeInt(imagen);

    }
}
