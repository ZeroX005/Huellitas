package com.example.edu.huellitas.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.edu.huellitas.Modelos.Tarjeta;
import com.example.edu.huellitas.R;
import com.example.edu.huellitas.ReservarPaseo;

import java.util.ArrayList;

public class MetodoPagoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<Tarjeta> lista;

    public MetodoPagoAdapter(Context context) {
        this.context = context;
        lista = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_metodos_pagos,viewGroup,false);
        return new MetodoPagoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MetodoPagoViewHolder vHolder = (MetodoPagoViewHolder) viewHolder;
        final Tarjeta item = lista.get(i);
        vHolder.tvNumeroTarj.setText(item.getNro_tarjeta());
        vHolder.tvNombreProp.setText(item.getNombre_propietario() + " "+ item.getApellido_propietario());
        vHolder.contenedorTarjeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inte = ((Activity) context).getIntent();
                Bundle parametros = inte.getExtras();
                String fecha_r = "";
                String hora_r = "";
                String direccion_r = "";
                String tiempo_paseo_r = "";

                if(parametros !=null){
                    fecha_r = parametros.getString("fecha");
                    hora_r = parametros.getString("hora");
                    direccion_r = parametros.getString("direccion");
                    tiempo_paseo_r = parametros.getString("tiempo_paseo");
                }

                final Bundle retorno = new Bundle();
                retorno.putString("fecha_re",fecha_r);
                retorno.putString("hora_re",hora_r);
                retorno.putString("direccion_re",direccion_r);
                retorno.putString("tiempo_paseo_re",tiempo_paseo_r);


                Intent intentMPago = new Intent(context, ReservarPaseo.class);
                intentMPago.putExtra("metodopago",item);
                intentMPago.putExtras(retorno);
                context.startActivity(intentMPago);
                ((Activity) context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    private class MetodoPagoViewHolder extends RecyclerView.ViewHolder {
        TextView tvNumeroTarj,tvNombreProp;
        CardView contenedorTarjeta;
        public MetodoPagoViewHolder(View itemView) {
            super(itemView);
            tvNumeroTarj = itemView.findViewById(R.id.tvNumeroTarj);
            tvNombreProp = itemView.findViewById(R.id.tvNombreProp);
            contenedorTarjeta = itemView.findViewById(R.id.contenedorTarjeta);
        }
    }

    public void agregarElementos(ArrayList<Tarjeta> data){
        lista.clear();
        lista.addAll(data);
        notifyDataSetChanged();
    }
}
