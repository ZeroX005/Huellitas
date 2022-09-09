package com.example.edu.huellitas.Adapters;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.edu.huellitas.HistorialReclamo_detalle;
import com.example.edu.huellitas.Modelos.Reclamo;
import com.example.edu.huellitas.R;

import java.util.ArrayList;

public class ReclamoAdapter extends RecyclerView.Adapter<ReclamoAdapter.ReclamoViewHolder> {

    private Context context;
    private ArrayList<Reclamo> lista;

    public ReclamoAdapter(Context context){
        this.context = context;
        lista = new ArrayList<>();
    }

    @NonNull
    @Override
    public ReclamoAdapter.ReclamoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View vista = LayoutInflater.from(context).inflate(R.layout.item_reclamos,viewGroup,false);
        return new ReclamoViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ReclamoAdapter.ReclamoViewHolder reclamoViewHolder, int i) {
        final Reclamo objRec = lista.get(i);
        reclamoViewHolder.tvAsuntoReclamo.setText(objRec.getAsunto_rec());
        reclamoViewHolder.contenedor2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHRD = new Intent(context, HistorialReclamo_detalle.class);
                intentHRD.putExtra("reclamo",objRec);
                context.startActivity(intentHRD);
            }
        });
    }

    public void agregarElementos(ArrayList<Reclamo> data){
        lista.clear();
        lista.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ReclamoViewHolder extends RecyclerView.ViewHolder {
        TextView tvAsuntoReclamo;
        CardView contenedor2;
        public ReclamoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAsuntoReclamo = itemView.findViewById(R.id.tvAsuntoReclamo);
            contenedor2 = itemView.findViewById(R.id.contenedor2);
        }
    }
}
