package com.example.edu.huellitas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.edu.huellitas.Modelos.Conexion;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;

public class ActualizarReclamoCRUD extends AppCompatActivity {

    private EditText etAsuntoReclA,etDetalleReclamoA;
    private LinearLayout cargandoRCLA;
    private Button btnActualizarRclA;
    private RequestQueue mQueue;
    Spinner sp01;
    String ticket,codestado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_reclamo_crud);

        if (Build.VERSION.SDK_INT >= 21){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorBlack));
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        etAsuntoReclA = findViewById(R.id.etAsuntoReclA);
        etDetalleReclamoA = findViewById(R.id.etDetalleReclamoA);
        cargandoRCLA = findViewById(R.id.cargandoRCLA);
        sp01 = findViewById(R.id.sp01);
        btnActualizarRclA = findViewById(R.id.btnActualizarRclA);

        mQueue = Volley.newRequestQueue(this);
        cargandoRCLA.setVisibility(View.GONE);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.opciones1_pendiente, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,R.array.opciones1_enproceso, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(this,R.array.opciones1_finalizado, android.R.layout.simple_spinner_item);

        Bundle parametros3 = this.getIntent().getExtras();
        if(parametros3 !=null){
            ticket = parametros3.getString("ticket_rcl");
            etAsuntoReclA.setText(parametros3.getString("asunto_rcl"));
            etDetalleReclamoA.setText(parametros3.getString("detalle_rcl"));
            if(parametros3.getString("estado_rcl").equals("Pendiente")) {
                sp01.setAdapter(adapter1);
            } else if (parametros3.getString("estado_rcl").equals("En proceso")){
                sp01.setAdapter(adapter2);
            } else if(parametros3.getString("estado_rcl").equals("Finalizado")){
                sp01.setAdapter(adapter3);
            }
        }

        btnActualizarRclA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etAsuntoReclA.getText().toString().equals("")){
                    etAsuntoReclA.setError("Ingrese un asunto");
                }else if(etDetalleReclamoA.getText().toString().equals("")){
                    etDetalleReclamoA.setError("Campo Obligatorio!");
                }else {
                    etAsuntoReclA.setEnabled(false);
                    etDetalleReclamoA.setEnabled(false);
                    sp01.setEnabled(false);
                    btnActualizarRclA.setEnabled(false);

                    cargandoRCLA.setVisibility(View.VISIBLE);
                    ConsumirWSRR();
                }
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

    private void ConsumirWSRR() {
        Conexion con = new Conexion();
        String url = con.ConecBase() + con.ActualizarReclamo();
        Map<String, String> params = new HashMap<>();
        params.put("nro_ticketReclamo",ticket);
        params.put("asunto_rec",etAsuntoReclA.getText().toString());
        params.put("detalle_rec",etDetalleReclamoA.getText().toString());
        if(sp01.getSelectedItem().toString().equals("Pendiente")){
            codestado = "1";
        }else if(sp01.getSelectedItem().toString().equals("En proceso")){
            codestado = "2";
        }else if(sp01.getSelectedItem().toString().equals("Finalizado")){
            codestado = "3";
        }
        params.put("estado_rec",codestado);
        JSONObject paramJSON = new JSONObject(params);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, paramJSON, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    if(response.getBoolean("rpta") == true) {
                        finish();
                        startActivity(new Intent(getApplicationContext(), MenuAdmin.class));
                        Toast.makeText(getApplicationContext(),response.getString("mensaje"),Toast.LENGTH_LONG).show();
                    }else{
                        cargandoRCLA.setVisibility(View.GONE);
                        etAsuntoReclA.setEnabled(false);
                        etDetalleReclamoA.setEnabled(false);
                        sp01.setEnabled(false);
                        btnActualizarRclA.setEnabled(false);
                        Toast.makeText(getApplicationContext(),"Error al actualizar reclamo",Toast.LENGTH_LONG).show();
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
