package com.example.edu.huellitas;

import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import com.example.edu.huellitas.Modelos.Reclamo;

public class HistorialReclamo_detalle extends AppCompatActivity {

    private TextView tvAsuntoReclD,tvTicketReclD,tvTicketReservaD,tvFechaHoraRD,tvResponsableReclD,tvDetalleReclamoD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_reclamo_detalle);

        if (Build.VERSION.SDK_INT >= 21){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorBlack));
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvTicketReclD = findViewById(R.id.tvTicketReclD);
        tvTicketReservaD = findViewById(R.id.tvTicketReservaD);
        tvResponsableReclD = findViewById(R.id.tvResponsableReclD);
        tvAsuntoReclD = findViewById(R.id.tvAsuntoReclD);
        tvFechaHoraRD = findViewById(R.id.tvFechaHoraRD);
        tvDetalleReclamoD = findViewById(R.id.tvDetalleReclamoD);

        if(getIntent().hasExtra("reclamo")){
           Reclamo objRec = getIntent().getParcelableExtra("reclamo");
           tvTicketReclD.setText(objRec.getNro_ticketReclamo());
           tvResponsableReclD.setText(objRec.getResponsable());
           tvTicketReservaD.setText(objRec.getNro_ticket());
           tvAsuntoReclD.setText(objRec.getAsunto_rec());
           tvFechaHoraRD.setText(objRec.getFh_recl_gen());
           tvDetalleReclamoD.setText(objRec.getDetalle_rec());

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
