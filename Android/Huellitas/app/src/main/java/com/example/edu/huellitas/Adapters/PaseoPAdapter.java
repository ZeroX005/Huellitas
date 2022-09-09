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

import com.example.edu.huellitas.Modelos.ReservaP;
import com.example.edu.huellitas.R;
import com.example.edu.huellitas.RealizarPaseo;

import java.util.ArrayList;

public class PaseoPAdapter extends RecyclerView.Adapter<PaseoPAdapter.PaseoViewHolder> {

    private Context context;
    private ArrayList<ReservaP> lista;

    public PaseoPAdapter(Context context){
        this.context = context;
        lista = new ArrayList<>();
    }


    @NonNull
    @Override
    public PaseoPAdapter.PaseoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View vista = LayoutInflater.from(context).inflate(R.layout.item_paseo,viewGroup,false);
        return new PaseoViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull PaseoPAdapter.PaseoViewHolder paseoViewHolder, int i) {
        final ReservaP objPaseo = lista.get(i);
        paseoViewHolder.tvDireccionP.setText("Dirección: "+objPaseo.getDireccion_r());
        paseoViewHolder.tvTicket3.setText("Ticket: "+objPaseo.getNro_ticket());
        paseoViewHolder.tvFechaHoraP.setText("F/H Registro: "+objPaseo.getFh_res_gen());
        paseoViewHolder.contenedorP2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentHRD = new Intent(context, RealizarPaseo.class);
                intentHRD.putExtra("paseo",objPaseo);
                context.startActivity(intentHRD);
            }
        });
    }

    public void agregarElementos(ArrayList<ReservaP> data){
        lista.clear();
        lista.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class PaseoViewHolder extends RecyclerView.ViewHolder {
        TextView tvDireccionP,tvTicket3,tvFechaHoraP;
        CardView contenedorP2;
        public PaseoViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDireccionP = itemView.findViewById(R.id.tvDireccionP);
            tvTicket3 = itemView.findViewById(R.id.tvTicket3);
            tvFechaHoraP = itemView.findViewById(R.id.tvFechaHoraP);
            contenedorP2 = itemView.findViewById(R.id.contenedorP2);
        }
    }
}
