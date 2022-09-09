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

import com.example.edu.huellitas.DetallePaseoRealizado;
import com.example.edu.huellitas.Modelos.ReservaP;
import com.example.edu.huellitas.R;

import java.util.ArrayList;

public class FinalizadoAdapter extends RecyclerView.Adapter<FinalizadoAdapter.FinalizadoViewHolder> {

    private Context context;
    private ArrayList<ReservaP> lista;

    public FinalizadoAdapter(Context context){
        this.context = context;
        lista = new ArrayList<>();
    }


    @NonNull
    @Override
    public FinalizadoAdapter.FinalizadoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View vista = LayoutInflater.from(context).inflate(R.layout.item_paseo_realizado,viewGroup,false);
        return new FinalizadoViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull FinalizadoAdapter.FinalizadoViewHolder finalizadoViewHolder, int i) {
        final ReservaP objPaseo = lista.get(i);
        finalizadoViewHolder.tvEstadoRF.setText("Estado: "+objPaseo.getEstado_r());
        finalizadoViewHolder.tvTicket5.setText("Ticket: "+objPaseo.getNro_ticket());
        finalizadoViewHolder.tvFechaHoraREF.setText("F/H Registro: "+objPaseo.getFh_res_gen());
        finalizadoViewHolder.contenedorP4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHRD = new Intent(context, DetallePaseoRealizado.class);
                intentHRD.putExtra("paseo",objPaseo);
                context.startActivity(intentHRD);
            }
        });

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class FinalizadoViewHolder extends RecyclerView.ViewHolder {
        TextView tvEstadoRF,tvTicket5,tvFechaHoraREF;
        CardView contenedorP4;
        public FinalizadoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEstadoRF = itemView.findViewById(R.id.tvEstadoRF);
            tvTicket5 = itemView.findViewById(R.id.tvTicket5);
            tvFechaHoraREF = itemView.findViewById(R.id.tvFechaHoraREF);
            contenedorP4 = itemView.findViewById(R.id.contenedorP4);
        }
    }

    public void agregarElementos(ArrayList<ReservaP> data){
        lista.clear();
        lista.addAll(data);
        notifyDataSetChanged();
    }
}
