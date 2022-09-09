package com.example.edu.huellitas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

public class AgregarTarjeta extends AppCompatActivity {

    private EditText etn1,etn2,etn3,etn4,etmes,etanio,etccv,etNomb_prop,etApe_prop;
    private Button btnAddCard;
    private RequestQueue mQueue;
    private LinearLayout cargando3;

    final Bundle retorno2 = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_tarjeta);

        if (Build.VERSION.SDK_INT >= 21){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorBlack));
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etn1 = findViewById(R.id.etn1);
        etn2 = findViewById(R.id.etn2);
        etn3 = findViewById(R.id.etn3);
        etn4 = findViewById(R.id.etn4);
        etmes = findViewById(R.id.etmes);
        etanio = findViewById(R.id.etanio);
        etccv = findViewById(R.id.etccv);
        etNomb_prop = findViewById(R.id.etNomb_prop);
        etApe_prop = findViewById(R.id.etApe_prop);
        btnAddCard = findViewById(R.id.btnAddCard);
        cargando3 = findViewById(R.id.cargando3);

        mQueue = Volley.newRequestQueue(this);

        cargando3.setVisibility(View.GONE);

        Bundle parametros = this.getIntent().getExtras();

        String fecha_r2 = "";
        String hora_r2 = "";
        String direccion_r2 = "";
        String tiempo_paseo_r2 = "";

        if(parametros !=null){
            fecha_r2 = parametros.getString("fecha_re");
            hora_r2 = parametros.getString("hora_re");
            direccion_r2 = parametros.getString("direccion_re");
            tiempo_paseo_r2 = parametros.getString("tiempo_paseo_re");
        }


        retorno2.putString("fecha",fecha_r2);
        retorno2.putString("hora",hora_r2);
        retorno2.putString("direccion",direccion_r2);
        retorno2.putString("tiempo_paseo",tiempo_paseo_r2);

        btnAddCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etn1.getText().toString().equals("")){
                    etn1.setError("Llenar");
                }else if(etn2.getText().toString().equals("")){
                    etn2.setError("Llenar");
                }else if(etn3.getText().toString().equals("")){
                    etn3.setError("Llenar");
                }else if(etn4.getText().toString().equals("")){
                    etn4.setError("Llenar");
                }else if(etccv.getText().toString().equals("")){
                    etccv.setError("Llenar");
                }else if(etNomb_prop.getText().toString().equals("")){
                    etNomb_prop.setError("Llenar");
                }else if(etApe_prop.getText().toString().equals("")){
                    etApe_prop.setError("Llenar");
                }else if(etmes.getText().toString().equals("")){
                    etmes.setError("Llenar");
                }else if(etanio.getText().toString().equals("")){
                    etanio.setError("Llenar");


                }else if(Integer.parseInt(etn1.getText().toString()) < 1000){
                    etn1.setError("Se requiere 4 dígitos");
                }else if(Integer.parseInt(etn2.getText().toString()) < 1000){
                    etn2.setError("Se requiere 4 dígitos");
                }else if(Integer.parseInt(etn3.getText().toString()) < 1000){
                    etn3.setError("Se requiere 4 dígitos");
                }else if(Integer.parseInt(etn4.getText().toString()) < 1000){
                    etn4.setError("Se requier 4 dígitos");
                }else if(Integer.parseInt(etmes.getText().toString()) > 12){
                    etmes.setError("El máximo de meses es 12");
                }else if(Integer.parseInt(etmes.getText().toString()) == 0){
                    etmes.setError("Ingresa un mes válido");
                }else if(Integer.parseInt(etanio.getText().toString()) > 50 ) {
                    etanio.setError("Ingrese un año válido");
                }else if(Integer.parseInt(etanio.getText().toString()) <= 21 ) {
                    etanio.setError("Ingrese un año válido");
                }else if(Integer.parseInt(etccv.getText().toString()) < 100){
                    etccv.setError("Se equiere 3 dígitos");

                }else {
                    etn1.setEnabled(false);
                    etn2.setEnabled(false);
                    etn3.setEnabled(false);
                    etn4.setEnabled(false);
                    etmes.setEnabled(false);
                    etanio.setEnabled(false);
                    etccv.setEnabled(false);
                    etNomb_prop.setEnabled(false);
                    etApe_prop.setEnabled(false);
                    btnAddCard.setEnabled(false);
                    cargando3.setVisibility(View.VISIBLE);
                    ConsumirWSAddCard();
                }

            }
        });

    }


    private void ConsumirWSAddCard(){
        Conexion con = new Conexion();
        String url = con.ConecBase() + con.RegistrarTarjeta();
        SharedPreferences preferences =getSharedPreferences("appLogin", Context.MODE_PRIVATE);
        Map<String, String> params = new HashMap<>();
        params.put("nro_tarjeta",etn4.getText().toString());
        params.put("nombre_propietario",etNomb_prop.getText().toString());
        params.put("apellido_propietario",etApe_prop.getText().toString());
        params.put("cod_cliente",preferences.getString("idusuario",""));

        JSONObject paramJSON = new JSONObject(params);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, paramJSON, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    if(response.getBoolean("rpta")) {
                        Intent intent = new Intent(getApplicationContext(), MetodoPago.class);
                        intent.putExtras(retorno2);
                        finish();
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(),response.getString("mensaje"),Toast.LENGTH_LONG).show();
                    }else{
                        etn1.setEnabled(true);
                        etn2.setEnabled(true);
                        etn3.setEnabled(true);
                        etn4.setEnabled(true);
                        etmes.setEnabled(true);
                        etanio.setEnabled(true);
                        etccv.setEnabled(true);
                        etNomb_prop.setEnabled(true);
                        etApe_prop.setEnabled(true);
                        btnAddCard.setEnabled(true);
                        cargando3.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(),"Error al registrar tarjeta",Toast.LENGTH_LONG).show();
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
