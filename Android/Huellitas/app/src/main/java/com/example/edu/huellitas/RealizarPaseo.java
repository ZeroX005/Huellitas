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


public class RealizarPaseo extends AppCompatActivity {

    private TextView tvFechaRP,tvHoraRP,tvClienteP,tvDireccionRP,tvDuracionPaseoP,tvPrecioP,tvPagadoConP,tvTicketRP,tvMetodoPagoP;
    private Button btnRealizar;
    private LinearLayout cargando4;
    private RequestQueue mQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realizar_paseo);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (Build.VERSION.SDK_INT >= 21){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorBlack));
        }

        btnRealizar = findViewById(R.id.btnRealizar);
        tvFechaRP = findViewById(R.id.tvFechaRP);
        tvHoraRP = findViewById(R.id.tvHoraRP);
        tvClienteP = findViewById(R.id.tvClienteP);
        tvDireccionRP = findViewById(R.id.tvDireccionRP);
        tvDuracionPaseoP = findViewById(R.id.tvDuracionPaseoP);
        tvPrecioP = findViewById(R.id.tvPrecioP);
        tvPagadoConP = findViewById(R.id.tvPagadoConP);
        tvTicketRP = findViewById(R.id.tvTicketRP);
        tvMetodoPagoP = findViewById(R.id.tvMetodoPagoP);
        cargando4 = findViewById(R.id.cargando4);

        mQueue = Volley.newRequestQueue(this);

        cargando4.setVisibility(View.GONE);

        if(getIntent().hasExtra("paseo")){
            ReservaP objPaseo = getIntent().getParcelableExtra("paseo");
            tvTicketRP.setText(objPaseo.getNro_ticket());
            tvFechaRP.setText(objPaseo.getFecha_r());
            tvHoraRP.setText(objPaseo.getHora_r());
            tvClienteP.setText(objPaseo.getCliente());
            tvDireccionRP.setText(objPaseo.getDireccion_r());
            tvDuracionPaseoP.setText(String.valueOf(objPaseo.getTiempo_paseo_r()));
            tvPrecioP.setText(String.valueOf(objPaseo.getPrecio_r()));
            tvPagadoConP.setText(String.valueOf(objPaseo.getPago_r()));
            tvMetodoPagoP.setText(objPaseo.getMetodopago());
        }

        btnRealizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnRealizar.setEnabled(false);
                cargando4.setVisibility(View.VISIBLE);
                ConsumirWS();
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent menu = new Intent(this,paseosLista.class);
                startActivity(menu);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void ConsumirWS() {
        Conexion con = new Conexion();
        String url = con.ConecBase() + con.RealizarReserva();

        SharedPreferences preferencesP = getSharedPreferences("appLoginP", MODE_PRIVATE);
        Map<String, String> params = new HashMap<>();
        if(getIntent().hasExtra("paseo")) {
            ReservaP objPaseo = getIntent().getParcelableExtra("paseo");
            params.put("nro_ticket", objPaseo.getNro_ticket());
        }
        params.put("cod_usuario_petw", preferencesP.getString("idusuarioP",""));


        JSONObject paramJSON = new JSONObject(params);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, paramJSON, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    if(response.getBoolean("rpta")) {
                        startActivity(new Intent(getApplicationContext(), Loguin.class));
                        Toast.makeText(getApplicationContext(),response.getString("mensaje"),Toast.LENGTH_LONG).show();
                    }else{
                        btnRealizar.setEnabled(true);
                        cargando4.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(),"Error al realizar paseo",Toast.LENGTH_LONG).show();
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
