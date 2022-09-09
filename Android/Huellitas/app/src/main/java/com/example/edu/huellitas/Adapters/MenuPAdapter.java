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

import com.example.edu.huellitas.Modelos.Menu;
import com.example.edu.huellitas.PaseosPendientes;
import com.example.edu.huellitas.PaseosRealizados;
import com.example.edu.huellitas.R;
import com.example.edu.huellitas.paseosLista;

import java.util.ArrayList;

public class MenuPAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    private ArrayList<Menu> lista;

    public MenuPAdapter(Context context) {
        this.context = context;
        lista = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_menup,viewGroup,false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MenuViewHolder vHolder = (MenuViewHolder)viewHolder;
        final Menu item = lista.get(i);
        vHolder.ivImagenMP.setImageResource(item.getImagen());
        vHolder.tvTituloMP.setText(item.getTitulo());
        vHolder.contenedorP1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPaseos = new Intent(context, paseosLista.class);
                Intent intentPaseosEC = new Intent(context, PaseosPendientes.class);
                Intent intentPaseosRE = new Intent(context, PaseosRealizados.class);
                if(item.equals(lista.get(0))){
                    context.startActivity(intentPaseos);
                }else if(item.equals(lista.get(1))){
                    context.startActivity(intentPaseosEC);
                }else if(item.equals(lista.get(2))){
                    context.startActivity(intentPaseosRE);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    private class MenuViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImagenMP;
        TextView tvTituloMP;
        CardView contenedorP1;

        public MenuViewHolder(View itemView) {
            super(itemView);
            ivImagenMP = itemView.findViewById(R.id.ivImagenMP);
            tvTituloMP = itemView.findViewById(R.id.tvTituloMP);
            contenedorP1 = itemView.findViewById(R.id.contenedorP1);

        }
    }

    public void agregarElementos(ArrayList<Menu> data){
        lista.clear();
        lista.addAll(data);
        notifyDataSetChanged();
    }
}
