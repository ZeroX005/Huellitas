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

import com.example.edu.huellitas.HistorialReclamoPetwalker_detalle;
import com.example.edu.huellitas.Modelos.Reclamo;
import com.example.edu.huellitas.R;

import java.util.ArrayList;

public class ReclamoPetwAdapter extends RecyclerView.Adapter<ReclamoPetwAdapter.ReclamoPViewHolder>{

    private Context context;
    private ArrayList<Reclamo> lista;

    public ReclamoPetwAdapter(Context context){
        this.context = context;
        lista = new ArrayList<>();
    }

    @NonNull
    @Override
    public ReclamoPetwAdapter.ReclamoPViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View vista = LayoutInflater.from(context).inflate(R.layout.item_reclamos_petw,viewGroup,false);
        return new ReclamoPViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ReclamoPetwAdapter.ReclamoPViewHolder reclamoPViewHolder, int i) {
        final Reclamo objRec = lista.get(i);
        reclamoPViewHolder.tvAsuntoReclamoP.setText(objRec.getAsunto_rec());
        reclamoPViewHolder.contenedor50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHRD = new Intent(context, HistorialReclamoPetwalker_detalle.class);
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

    public class ReclamoPViewHolder extends RecyclerView.ViewHolder {
        TextView tvAsuntoReclamoP;
        CardView contenedor50;
        public ReclamoPViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAsuntoReclamoP = itemView.findViewById(R.id.tvAsuntoReclamoP);
            contenedor50 = itemView.findViewById(R.id.contenedor50);
        }
    }
}
