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

public class ReclamoCRUD extends AppCompatActivity {

    private TextView tvcodreclamo,tvasuntorcl,tvfhgrcl,tvticketreservarcl,tvestadorecl,tvcodresponsable,tvresponsablercl,tvcodafectado,tvafectadorcl,tvdetallercl;
    private Button btnactualizarrcl;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamo_crud);

        if (Build.VERSION.SDK_INT >= 21){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorBlack));
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvcodreclamo=findViewById(R.id.tvcodreclamo);
        tvasuntorcl=findViewById(R.id.tvasuntorcl);
        tvfhgrcl=findViewById(R.id.tvfhgrcl);
        tvticketreservarcl=findViewById(R.id.tvticketreservarcl);
        tvestadorecl=findViewById(R.id.tvestadorecl);
        tvcodresponsable=findViewById(R.id.tvcodresponsable);
        tvresponsablercl=findViewById(R.id.tvresponsablercl);
        tvcodafectado=findViewById(R.id.tvcodafectado);
        tvafectadorcl=findViewById(R.id.tvafectadorcl);
        tvdetallercl=findViewById(R.id.tvdetallercl);
        btnactualizarrcl=findViewById(R.id.btnactualizarrcl);

        mQueue = Volley.newRequestQueue(this);

        if(getIntent().hasExtra("reclamo")){
            CRUD_Reclamo objRcl = getIntent().getParcelableExtra("reclamo");
            tvcodreclamo.setText(objRcl.getNro_ticketReclamo());
            tvasuntorcl.setText(objRcl.getAsunto_rec());
            tvfhgrcl.setText(objRcl.getFh_recl_gen());
            tvticketreservarcl.setText(objRcl.getTicket_res());
            tvestadorecl.setText(objRcl.getEstado());
            tvcodresponsable.setText(String.valueOf(objRcl.getCod_responsable()));
            tvresponsablercl.setText(objRcl.getResponsable());
            tvcodafectado.setText(String.valueOf(objRcl.getCod_afectado()));
            tvafectadorcl.setText(objRcl.getAfectado());
            tvdetallercl.setText(objRcl.getDetalle_rec());
        }

        btnactualizarrcl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("ticket_rcl",tvcodreclamo.getText().toString());
                bundle.putString("asunto_rcl",tvasuntorcl.getText().toString());
                bundle.putString("detalle_rcl",tvdetallercl.getText().toString());
                bundle.putString("estado_rcl",tvestadorecl.getText().toString());


                Intent intent = new Intent(getApplicationContext(),ActualizarReclamoCRUD.class);
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
