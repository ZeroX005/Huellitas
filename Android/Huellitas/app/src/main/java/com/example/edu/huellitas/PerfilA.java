package com.example.edu.huellitas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

public class PerfilA extends AppCompatActivity {

    private EditText etNombA,etApeA,etEmailA,etFechaNaciA;
    private Button btnRegreso3;

    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_a);

        if (Build.VERSION.SDK_INT >= 21){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorBlack));
        }

        etNombA = findViewById(R.id.etNombA);
        etApeA = findViewById(R.id.etApeA);
        etEmailA = findViewById(R.id.etEmailA);
        etFechaNaciA = findViewById(R.id.etFechaNaciA);
        btnRegreso3 = findViewById(R.id.btnRegreso3);

        mQueue = Volley.newRequestQueue(this);

        ConsumirWS();

        btnRegreso3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MenuAdmin.class);
                finish();
                startActivity(intent);
            }
        });

        etNombA.setFocusable(false);
        etNombA.setEnabled(true);
        etNombA.setCursorVisible(false);
        etNombA.setKeyListener(null);

        etApeA.setFocusable(false);
        etApeA.setEnabled(true);
        etApeA.setCursorVisible(false);
        etApeA.setKeyListener(null);

        etEmailA.setFocusable(false);
        etEmailA.setEnabled(true);
        etEmailA.setCursorVisible(false);
        etEmailA.setKeyListener(null);

        etFechaNaciA.setFocusable(false);
        etFechaNaciA.setEnabled(true);
        etFechaNaciA.setCursorVisible(false);
        etFechaNaciA.setKeyListener(null);

    }

    private void ConsumirWS() {
        Conexion con = new Conexion();
        String url = con.ConecBase() + con.PerfilA();
        SharedPreferences preferences =getSharedPreferences("appLoginA", Context.MODE_PRIVATE);
        Map<String, String> params = new HashMap<>();
        params.put("codigo", preferences.getString("idusuarioA",""));
        JSONObject paramJSON = new JSONObject(params);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, paramJSON, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    etNombA.setText(response.getString("nombres"));
                    etApeA.setText(response.getString("apellidos"));
                    etEmailA.setText(response.getString("correo"));
                    etFechaNaciA.setText(response.getString("fecha_nacimiento"));
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
