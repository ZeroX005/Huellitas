package com.example.edu.huellitas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.edu.huellitas.Modelos.CRUD_Reclamo;
import com.example.edu.huellitas.Modelos.CRUD_Reserva;

public class ReservaCRUD extends AppCompatActivity {

    private TextView codnroticket,tvfreserva,tvhreserva,tvdireccionreserva,tvtiempopaseo,tvpagarconres,tvprecioreserva,tvfhres,tvestadores,tvtipopagores,tvcodclienteres,tvclienteres,tvcodpetwres,tvpetwres;
    private Button btnactualizarres;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva_crud);

        if (Build.VERSION.SDK_INT >= 21){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorBlack));
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        codnroticket=findViewById(R.id.codnroticket);
        tvfreserva=findViewById(R.id.tvfreserva);
        tvhreserva=findViewById(R.id.tvhreserva);
        tvdireccionreserva=findViewById(R.id.tvdireccionreserva);
        tvtiempopaseo=findViewById(R.id.tvtiempopaseo);
        tvpagarconres=findViewById(R.id.tvpagarconres);
        tvprecioreserva=findViewById(R.id.tvprecioreserva);
        tvfhres=findViewById(R.id.tvfhres);
        tvestadores=findViewById(R.id.tvestadores);
        tvtipopagores=findViewById(R.id.tvtipopagores);
        tvcodclienteres=findViewById(R.id.tvcodclienteres);
        tvclienteres=findViewById(R.id.tvclienteres);
        tvcodpetwres=findViewById(R.id.tvcodpetwres);
        tvpetwres=findViewById(R.id.tvpetwres);
        btnactualizarres=findViewById(R.id.btnactualizarres);

        mQueue = Volley.newRequestQueue(this);

        if(getIntent().hasExtra("reserva")){
            CRUD_Reserva objRes = getIntent().getParcelableExtra("reserva");
            codnroticket.setText(objRes.getNro_ticket());
            tvfreserva.setText(objRes.getFecha_r());
            tvhreserva.setText(objRes.getHora_r());
            tvdireccionreserva.setText(objRes.getDireccion_r());
            tvtiempopaseo.setText(String.valueOf(objRes.getTiempo_paseo()));
            tvpagarconres.setText(String.valueOf(objRes.getPagar_con()));
            tvprecioreserva.setText(String.valueOf(objRes.getPrecio()));
            tvfhres.setText(objRes.getFh_res_gen());
            tvestadores.setText(objRes.getEstado());
            tvtipopagores.setText(objRes.getTipo_pago());
            tvcodclienteres.setText(String.valueOf(objRes.getCod_usuario_cli()));
            tvclienteres.setText(objRes.getCliente());
            tvcodpetwres.setText(String.valueOf(objRes.getCod_usuario_petw()));
            tvpetwres.setText(objRes.getPetwalker());
        }

        btnactualizarres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("ticket",codnroticket.getText().toString());
                bundle.putString("fecha_r",tvfreserva.getText().toString());
                bundle.putString("hora_r",tvhreserva.getText().toString());
                bundle.putString("direccion_r",tvdireccionreserva.getText().toString());
                bundle.putString("tiempo_paseo",tvtiempopaseo.getText().toString());
                bundle.putString("pagar_con",tvpagarconres.getText().toString());
                bundle.putString("precio",tvprecioreserva.getText().toString());
                bundle.putString("estado",tvestadores.getText().toString());
                bundle.putString("metodo_pago",tvtipopagores.getText().toString());


                Intent intent = new Intent(getApplicationContext(),ActualizarReservaCRUD.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
