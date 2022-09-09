package com.example.edu.huellitas;

import android.content.Intent;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.edu.huellitas.Modelos.ReservaP;

public class HistorialPaseosPendientes_detalle extends AppCompatActivity {

    private TextView tvFechaR,tvHoraR,tvPaseador,tvDireccionR,tvDuracionPaseo,tvPrecio,tvPagadoCon,tvTicketR,tvMetodoPago;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_paseospendientes_detalle);

        if (Build.VERSION.SDK_INT >= 21){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorBlack));
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvFechaR = findViewById(R.id.tvFechaR);
        tvHoraR = findViewById(R.id.tvHoraR);
        tvPaseador = findViewById(R.id.tvPaseador);
        tvDireccionR = findViewById(R.id.tvDireccionR);
        tvDuracionPaseo = findViewById(R.id.tvDuracionPaseo);
        tvPrecio = findViewById(R.id.tvPrecio);
        tvPagadoCon = findViewById(R.id.tvPagadoCon);
        tvTicketR = findViewById(R.id.tvTicketR);
        tvMetodoPago = findViewById(R.id.tvMetodoPago);

        mQueue = Volley.newRequestQueue(this);

        ReservaP objReserva = getIntent().getParcelableExtra("reserva");
        if(getIntent().hasExtra("reserva")) {
            tvTicketR.setText(objReserva.getNro_ticket());
            tvFechaR.setText(objReserva.getFecha_r());
            tvHoraR.setText(objReserva.getHora_r());
            if(objReserva.getPetwalker() != null){
                tvPaseador.setText(objReserva.getPetwalker());
            }else{
                tvPaseador.setText("No hay Paseador");
            }
            tvDireccionR.setText(objReserva.getDireccion_r());
            tvDuracionPaseo.setText(String.valueOf(objReserva.getTiempo_paseo_r()));
            tvMetodoPago.setText(objReserva.getMetodopago());
            tvPrecio.setText(String.valueOf(objReserva.getPrecio_r()));
            tvPagadoCon.setText(String.valueOf(objReserva.getPago_r()));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent menu = new Intent(this,HistorialPaseosPendientes.class);
                startActivity(menu);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
