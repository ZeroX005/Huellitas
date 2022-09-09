package com.example.edu.huellitas;

import android.content.Intent;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.edu.huellitas.Modelos.Conexion;
import com.example.edu.huellitas.Modelos.ReservaP;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class HistorialReservasFinalizadas_detalle extends AppCompatActivity {

    private TextView tvTicketFI,tvFechaFI,tvHoraFI,tvPaseadorFI,tvDireccionFI,tvDuracionPaseoFI,tvMetodoPagoFI,tvPrecioFI,tvPagadoConFI;
    private Button btnReclamar;
    private RequestQueue mQueue;
    private LinearLayout cargando7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_reservasfinalizadas_detalle);

        if (Build.VERSION.SDK_INT >= 21){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorBlack));
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvTicketFI = findViewById(R.id.tvTicketFI);
        tvFechaFI = findViewById(R.id.tvFechaFI);
        tvHoraFI = findViewById(R.id.tvHoraFI);
        tvPaseadorFI = findViewById(R.id.tvPaseadorFI);
        tvDireccionFI = findViewById(R.id.tvDireccionFI);
        tvDuracionPaseoFI = findViewById(R.id.tvDuracionPaseoFI);
        tvMetodoPagoFI = findViewById(R.id.tvMetodoPagoFI);
        tvPrecioFI = findViewById(R.id.tvPrecioFI);
        tvPagadoConFI = findViewById(R.id.tvPagadoConFI);
        cargando7 = findViewById(R.id.cargando7);
        btnReclamar = findViewById(R.id.btnReclamar);
        mQueue = Volley.newRequestQueue(this);

        cargando7.setVisibility(View.GONE);

        CosumirWS();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent menu = new Intent(this,HistorialReservasFinalizadas.class);
                startActivity(menu);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void CosumirWS() {
        Conexion con = new Conexion();
        String url= con.ConecBase() + con.DetalleReserva();
        if(getIntent().hasExtra("reserva")){
            ReservaP objReserva = getIntent().getParcelableExtra("reserva");
            Map<String, String> params = new HashMap<>();
            params.put("nro_ticket",objReserva.getNro_ticket());
            JSONObject paramsJSON = new JSONObject(params);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, paramsJSON, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try{
                        tvTicketFI.setText(response.getString("nro_ticket"));
                        tvFechaFI.setText(response.getString("fecha_r"));
                        tvHoraFI.setText(response.getString("hora_r"));
                        tvPaseadorFI.setText(response.getString("petwalker"));
                        tvDireccionFI.setText(response.getString("direccion_r"));
                        tvDuracionPaseoFI.setText(String.valueOf(response.getString("tiempo_paseo_r")));
                        tvMetodoPagoFI.setText(response.getString("metodopago"));
                        tvPrecioFI.setText(String.valueOf(response.getString("precio_r")));
                        tvPagadoConFI.setText(String.valueOf(response.getString("pago_r")));

                        final Bundle envioDatos = new Bundle();
                        envioDatos.putString("nombre",response.getString("petwalker"));
                        envioDatos.putString("idresponsable",response.getString("cod_usuario_petw"));
                        envioDatos.putString("idafectado",response.getString("cod_usuario_cli"));
                        envioDatos.putString("ticket", response.getString("nro_ticket"));
                        envioDatos.putString("tipo","cliente");


                        btnReclamar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                cargando7.setVisibility(View.VISIBLE);
                                btnReclamar.setEnabled(false);
                                Intent intent = new Intent(getApplicationContext(), Reclamo.class);
                                intent.putExtras(envioDatos);
                                startActivity(intent);
                                cargando7.setVisibility(View.GONE);
                                btnReclamar.setEnabled(true);
                            }
                        });
                    }catch(Exception ex){
                        ex.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });
            mQueue.add(request);
        }

    }

}
