package com.example.edu.huellitas.Adapters;

import android.content.Context;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.edu.huellitas.HistorialReservasFinalizadas;
import com.example.edu.huellitas.HistorialPaseosPendientes;
import com.example.edu.huellitas.Modelos.Menu;
import com.example.edu.huellitas.R;
import com.example.edu.huellitas.ReservarPaseo;

import java.util.ArrayList;

public class MenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private ArrayList<Menu> lista;

    public MenuAdapter(Context context) {
        this.context = context;
        lista = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_menu,viewGroup,false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MenuViewHolder vHolder = (MenuViewHolder)viewHolder;
        final Menu item = lista.get(i);
        vHolder.ivImagenM.setImageResource(item.getImagen());
        vHolder.tvTituloM.setText(item.getTitulo());
        vHolder.contenedor1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentReservarPaseo = new Intent(context, ReservarPaseo.class);
                Intent intentHistorialReserva = new Intent(context, HistorialReservasFinalizadas.class);
                Intent intentPaseosPendientes = new Intent(context, HistorialPaseosPendientes.class);
                if(item.equals(lista.get(0))) {
                    context.startActivity(intentReservarPaseo);
                }else if(item.equals(lista.get(1))){
                    context.startActivity(intentPaseosPendientes);
                }else if(item.equals(lista.get(2))){
                    context.startActivity(intentHistorialReserva);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    private class MenuViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImagenM;
        TextView tvTituloM;
        CardView contenedor1;

        public MenuViewHolder(View itemView) {
            super(itemView);
            ivImagenM = itemView.findViewById(R.id.ivImagenM);
            tvTituloM = itemView.findViewById(R.id.tvTituloM);
            contenedor1 = itemView.findViewById(R.id.contenedor1);

        }
    }

    public void agregarElementos(ArrayList<Menu> data){
        lista.clear();
        lista.addAll(data);
        notifyDataSetChanged();
    }
}
