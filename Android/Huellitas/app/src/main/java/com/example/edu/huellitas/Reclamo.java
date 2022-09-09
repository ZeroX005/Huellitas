package com.example.edu.huellitas;

import android.content.Intent;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.edu.huellitas.Modelos.Conexion;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Reclamo extends AppCompatActivity {

    private TextView tvResponsable,tvTicketRecl;
    private EditText etAsuntoRecl,etDetalleReclamo;
    private Button btnReportar;
    private String codResp, codAfec,tipo;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamo);

        if (Build.VERSION.SDK_INT >= 21){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorBlack));
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvResponsable = findViewById(R.id.tvResponsable);
        etAsuntoRecl = findViewById(R.id.etAsuntoRecl);
        etDetalleReclamo = findViewById(R.id.etDetalleReclamo);
        btnReportar = findViewById(R.id.btnReportar);
        tvTicketRecl = findViewById(R.id.tvTicketRecl);

        mQueue = Volley.newRequestQueue(this);

        btnReportar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etAsuntoRecl.getText().toString().equals("")){
                    etAsuntoRecl.setError("Ingrese un asunto");
                }else if(etDetalleReclamo.getText().toString().equals("")){
                    etDetalleReclamo.setError("Campo Obligatorio!");
                }else {
                    ConsumirWSRR();
                }
            }
        });

        Bundle recibeDatos = this.getIntent().getExtras();
        if(recibeDatos!=null && recibeDatos.getString("nombre")!=null)
            tvResponsable.setText(recibeDatos.getString("nombre"));
            tvTicketRecl.setText(recibeDatos.getString("ticket"));
            codResp = recibeDatos.getString("idresponsable");
            codAfec = recibeDatos.getString("idafectado");
            tipo = recibeDatos.getString("tipo");

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

    private void ConsumirWSRR() {
        Conexion con = new Conexion();
        String url = con.ConecBase() + con.RegistrarReclamo();
        Map<String, String> params = new HashMap<>();
        params.put("asunto_rec",etAsuntoRecl.getText().toString());
        params.put("detalle_rec",etDetalleReclamo.getText().toString());
        params.put("nro_ticket",tvTicketRecl.getText().toString());
        params.put("cod_responsable",codResp);
        params.put("cod_afectado",codAfec);
        JSONObject paramJSON = new JSONObject(params);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, paramJSON, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    if(response.getBoolean("rpta") == true) {
                        if(tipo.equals("cliente")) {
                            startActivity(new Intent(getApplicationContext(), MenuCliente.class));
                            Toast.makeText(getApplicationContext(), response.getString("mensaje"), Toast.LENGTH_LONG).show();
                        }else if(tipo.equals("paseador")){
                            startActivity(new Intent(getApplicationContext(), MenuPaseador.class));
                            finish();
                            Toast.makeText(getApplicationContext(), response.getString("mensaje"), Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(),"Error al registrar reclamo",Toast.LENGTH_LONG).show();
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
