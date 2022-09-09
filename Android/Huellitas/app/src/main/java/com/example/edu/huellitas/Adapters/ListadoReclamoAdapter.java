package com.example.edu.huellitas.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.edu.huellitas.Modelos.CRUD_Reclamo;
import com.example.edu.huellitas.R;
import com.example.edu.huellitas.ReclamoCRUD;

import java.util.ArrayList;

public class ListadoReclamoAdapter extends RecyclerView.Adapter<ListadoReclamoAdapter.ListadoReclamoViewHolder>  {

    private Context context;
    private ArrayList<CRUD_Reclamo> lista;

    public ListadoReclamoAdapter(Context context){
        this.context = context;
        lista = new ArrayList<>();
    }

    @NonNull
    @Override
    public ListadoReclamoAdapter.ListadoReclamoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View vista = LayoutInflater.from(context).inflate(R.layout.item_listado_reclamo,viewGroup,false);
        return new ListadoReclamoViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ListadoReclamoAdapter.ListadoReclamoViewHolder holder, int i) {
        final CRUD_Reclamo objRcl = lista.get(i);
        holder.tvinc.setText(objRcl.getNro_ticketReclamo());
        holder.tvafectado.setText(objRcl.getAfectado());
        holder.tvestado2.setText(objRcl.getEstado());
        holder.contenedorRCL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHRD = new Intent(context, ReclamoCRUD.class);
                intentHRD.putExtra("reclamo",objRcl);
                context.startActivity(intentHRD);
            }
        });
    }

    public void agregarElementos(ArrayList<CRUD_Reclamo> data){
        lista.clear();
        lista.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ListadoReclamoViewHolder extends RecyclerView.ViewHolder {
        TextView tvinc,tvafectado,tvestado2;
        CardView contenedorRCL;
        public ListadoReclamoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvinc = itemView.findViewById(R.id.tvinc);
            tvafectado = itemView.findViewById(R.id.tvafectado);
            tvestado2 = itemView.findViewById(R.id.tvestado2);
            contenedorRCL = itemView.findViewById(R.id.contenedorRCL);
        }
    }
}
