package com.example.edu.huellitas;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

public class FinalizarPaseo extends AppCompatActivity {

    private TextView tvFechaRF,tvHoraRF,tvClienteF,tvDireccionRF,tvDuracionPaseoF,tvPrecioF,tvPagadoConF,tvTicketRF,tvMetodoPagoF;
    private Button btnFinalizar;
    private LinearLayout cargando5;
    private RequestQueue mQueue;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalizar_paseo);

        if (Build.VERSION.SDK_INT >= 21){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorBlack));
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnFinalizar = findViewById(R.id.btnFinalizar);
        tvFechaRF = findViewById(R.id.tvFechaRF);
        tvHoraRF = findViewById(R.id.tvHoraRF);
        tvClienteF = findViewById(R.id.tvClienteF);
        tvDireccionRF = findViewById(R.id.tvDireccionRF);
        tvDuracionPaseoF = findViewById(R.id.tvDuracionPaseoF);
        tvPrecioF = findViewById(R.id.tvPrecioF);
        tvPagadoConF = findViewById(R.id.tvPagadoConF);
        tvMetodoPagoF = findViewById(R.id.tvMetodoPagoF);
        tvTicketRF = findViewById(R.id.tvTicketRF);
        cargando5 = findViewById(R.id.cargando5);

        mQueue = Volley.newRequestQueue(this);

        cargando5.setVisibility(View.GONE);

        if(getIntent().hasExtra("paseo")){
            ReservaP objPaseo = getIntent().getParcelableExtra("paseo");
            tvTicketRF.setText(objPaseo.getNro_ticket());
            tvFechaRF.setText(objPaseo.getFecha_r());
            tvHoraRF.setText(objPaseo.getHora_r());
            tvClienteF.setText(objPaseo.getCliente());
            tvDireccionRF.setText(objPaseo.getDireccion_r());
            tvDuracionPaseoF.setText(String.valueOf(objPaseo.getTiempo_paseo_r()));
            tvMetodoPagoF.setText(objPaseo.getMetodopago());
            tvPrecioF.setText(String.valueOf(objPaseo.getPrecio_r()));
            tvPagadoConF.setText(String.valueOf(objPaseo.getPago_r()));
        }

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnFinalizar.setEnabled(false);
                cargando5.setVisibility(View.VISIBLE);
                ConsumirWS();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent menu = new Intent(this,PaseosPendientes.class);
                startActivity(menu);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void ConsumirWS() {
        Conexion con = new Conexion();
        String url = con.ConecBase() + con.FinalizarReserva();
        SharedPreferences preferencesP = getSharedPreferences("appLoginP", MODE_PRIVATE);
        Map<String, String> params = new HashMap<>();
        if(getIntent().hasExtra("paseo")) {
            ReservaP objPaseo = getIntent().getParcelableExtra("paseo");
            params.put("nro_ticket", objPaseo.getNro_ticket());
        }
        JSONObject paramJSON = new JSONObject(params);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, paramJSON, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    if(response.getBoolean("rpta")) {
                        startActivity(new Intent(getApplicationContext(), Loguin.class));
                        Toast.makeText(getApplicationContext(),response.getString("mensaje"),Toast.LENGTH_LONG).show();
                    }else{
                        btnFinalizar.setEnabled(true);
                        cargando5.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(),"Error al finalizar paseo",Toast.LENGTH_LONG).show();
                    }
                }catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mQueue.add(request);
    }
}
