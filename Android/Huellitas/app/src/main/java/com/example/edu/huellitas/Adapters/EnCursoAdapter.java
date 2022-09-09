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

import com.example.edu.huellitas.FinalizarPaseo;
import com.example.edu.huellitas.Modelos.ReservaP;
import com.example.edu.huellitas.R;

import java.util.ArrayList;

public class EnCursoAdapter extends RecyclerView.Adapter<EnCursoAdapter.EnCursoViewHolder> {

    private Context context;
    private ArrayList<ReservaP> lista;

    public EnCursoAdapter(Context context){
        this.context = context;
        lista = new ArrayList<>();
    }

    @NonNull
    @Override
    public EnCursoAdapter.EnCursoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View vista = LayoutInflater.from(context).inflate(R.layout.item_paseo_pendientes,viewGroup,false);
        return new EnCursoViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull EnCursoAdapter.EnCursoViewHolder enCursoViewHolder, int i) {
        final ReservaP objPaseo = lista.get(i);
        enCursoViewHolder.tvEstadoRP.setText("Estado: "+objPaseo.getEstado_r());
        enCursoViewHolder.tvTicket4.setText("Ticket: "+objPaseo.getNro_ticket());
        enCursoViewHolder.tvFechaHoraREP.setText("F/H Registro: "+objPaseo.getFh_res_gen());
        enCursoViewHolder.contenedorP3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHRD = new Intent(context, FinalizarPaseo.class);
                intentHRD.putExtra("paseo",objPaseo);
                context.startActivity(intentHRD);
            }
        });

    }


    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class EnCursoViewHolder extends RecyclerView.ViewHolder {
        TextView tvEstadoRP,tvTicket4,tvFechaHoraREP;
        CardView contenedorP3;
        public EnCursoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvEstadoRP = itemView.findViewById(R.id.tvEstadoRP);
            tvTicket4 = itemView.findViewById(R.id.tvTicket4);
            tvFechaHoraREP = itemView.findViewById(R.id.tvFechaHoraREP);
            contenedorP3 = itemView.findViewById(R.id.contenedorP3);
        }
    }

    public void agregarElementos(ArrayList<ReservaP> data){
        lista.clear();
        lista.addAll(data);
        notifyDataSetChanged();
    }
}
