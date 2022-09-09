package com.example.edu.huellitas.Modelos;

import android.os.Parcel;
import android.os.Parcelable;

public class CRUD_Reclamo implements Parcelable {

    private String nro_ticketReclamo;
    private String asunto_rec;
    private String detalle_rec;
    private String fh_recl_gen;
    private String ticket_res;
    private String estado;
    private String responsable;
    private String afectado;
    private int cod_responsable;
    private int cod_afectado;



    public CRUD_Reclamo(String nro_ticketReclamo, String asunto_rec, String detalle_rec, String fh_recl_gen, String ticket_res, String estado, String responsable, String afectado, int cod_responsable, int cod_afectado) {
        this.nro_ticketReclamo = nro_ticketReclamo;
        this.asunto_rec = asunto_rec;
        this.detalle_rec = detalle_rec;
        this.fh_recl_gen = fh_recl_gen;
        this.ticket_res = ticket_res;
        this.estado = estado;
        this.responsable = responsable;
        this.afectado = afectado;
        this.cod_responsable = cod_responsable;
        this.cod_afectado = cod_afectado;
    }

    protected CRUD_Reclamo(Parcel in) {
        nro_ticketReclamo = in.readString();
        asunto_rec = in.readString();
        detalle_rec = in.readString();
        fh_recl_gen = in.readString();
        ticket_res = in.readString();
        estado = in.readString();
        responsable = in.readString();
        afectado = in.readString();
        cod_responsable = in.readInt();
        cod_afectado = in.readInt();
    }

    public static final Creator<CRUD_Reclamo> CREATOR = new Creator<CRUD_Reclamo>() {
        @Override
        public CRUD_Reclamo createFromParcel(Parcel in) {
            return new CRUD_Reclamo(in);
        }

        @Override
        public CRUD_Reclamo[] newArray(int size) {
            return new CRUD_Reclamo[size];
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

    public String getTicket_res() {
        return ticket_res;
    }

    public void setTicket_res(String ticket_res) {
        this.ticket_res = ticket_res;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
        parcel.writeString(ticket_res);
        parcel.writeString(estado);
        parcel.writeString(responsable);
        parcel.writeString(afectado);
        parcel.writeInt(cod_responsable);
        parcel.writeInt(cod_afectado);
    }
}
