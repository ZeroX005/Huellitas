package com.example.edu.huellitas;

import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.edu.huellitas.Modelos.Reclamo;

public class HistorialReclamoPetwalker_detalle extends AppCompatActivity {

    private TextView tvAsuntoReclDP,tvTicketReclDP,tvTicketReservaDP,tvFechaHoraRDP,tvResponsableReclDP,tvDetalleReclamoDP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_reclamo_petwalker_detalle);

        if (Build.VERSION.SDK_INT >= 21){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorBlack));
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvTicketReclDP = findViewById(R.id.tvTicketReclDP);
        tvTicketReservaDP = findViewById(R.id.tvTicketReservaDP);
        tvResponsableReclDP = findViewById(R.id.tvResponsableReclDP);
        tvAsuntoReclDP = findViewById(R.id.tvAsuntoReclDP);
        tvFechaHoraRDP = findViewById(R.id.tvFechaHoraRDP);
        tvDetalleReclamoDP = findViewById(R.id.tvDetalleReclamoDP);

        if(getIntent().hasExtra("reclamo")){
            Reclamo objRec = getIntent().getParcelableExtra("reclamo");
            tvTicketReclDP.setText(objRec.getNro_ticketReclamo());
            tvResponsableReclDP.setText(objRec.getResponsable());
            tvTicketReservaDP.setText(objRec.getNro_ticket());
            tvAsuntoReclDP.setText(objRec.getAsunto_rec());
            tvFechaHoraRDP.setText(objRec.getFh_recl_gen());
            tvDetalleReclamoDP.setText(objRec.getDetalle_rec());

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
