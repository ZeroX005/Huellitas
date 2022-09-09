package com.example.edu.huellitas.Modelos;

import android.os.Parcel;
import android.os.Parcelable;

public class Reclamo implements Parcelable {
    private String nro_ticketReclamo;
    private String asunto_rec;
    private String detalle_rec;
    private String fh_recl_gen;
    private String nro_ticket;
    private String estado_rec;
    private String responsable;
    private int cod_responsable;
    private int cod_afectado;

    public Reclamo (){
    }

    public Reclamo(String nro_ticketReclamo, String asunto_rec, String detalle_rec, String fh_recl_gen, String nro_ticket, String estado_rec, String responsable, int cod_responsable, int cod_afectado) {
        this.nro_ticketReclamo = nro_ticketReclamo;
        this.asunto_rec = asunto_rec;
        this.detalle_rec = detalle_rec;
        this.fh_recl_gen = fh_recl_gen;
        this.nro_ticket = nro_ticket;
        this.estado_rec = estado_rec;
        this.responsable = responsable;
        this.cod_responsable = cod_responsable;
        this.cod_afectado = cod_afectado;
    }

    protected Reclamo(Parcel in) {
        nro_ticketReclamo = in.readString();
        asunto_rec = in.readString();
        detalle_rec = in.readString();
        fh_recl_gen = in.readString();
        nro_ticket = in.readString();
        estado_rec = in.readString();
        responsable = in.readString();
        cod_responsable = in.readInt();
        cod_afectado = in.readInt();
    }

    public static final Creator<Reclamo> CREATOR = new Creator<Reclamo>() {
        @Override
        public Reclamo createFromParcel(Parcel in) {
            return new Reclamo(in);
        }

        @Override
        public Reclamo[] newArray(int size) {
            return new Reclamo[size];
        }
    };

    public String getNro_ticketReclamo() {
        return nro_ticketReclamo;
    }

    public void setNro_ticketReclamo(String nro_ticketReclamo) {
        this.nro_ticketReclamo = nro_ticketReclamo;
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

    public int getCod_responsable() {
        return cod_responsable;
    }

    public void setCod_responsable(int cod_responsable) {
        this.cod_responsable = cod_responsable;
    }

    public int getCod_afectado() {
        return cod_afectado;
    }

    public void setCod_afectado(int cod_afectado) {
        this.cod_afectado = cod_afectado;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nro_ticketReclamo);
        parcel.writeString(asunto_rec);
        parcel.writeString(detalle_rec);
        parcel.writeString(fh_recl_gen);
        parcel.writeString(nro_ticket);
        parcel.writeString(estado_rec);
        parcel.writeString(responsable);
        parcel.writeInt(cod_responsable);
        parcel.writeInt(cod_afectado);
    }
}

