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

public class Perfil extends AppCompatActivity {

    private EditText etNombP,etApeP,etEmailP,etFechaNaciP,etDireccionP,etCelularP;
    private Button btnRegreso1;

    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        if (Build.VERSION.SDK_INT >= 21){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorBlack));
        }

        etNombP = findViewById(R.id.etNombP);
        etApeP = findViewById(R.id.etApeP);
        etEmailP = findViewById(R.id.etEmailP);
        etFechaNaciP = findViewById(R.id.etFechaNaciP);
        etDireccionP = findViewById(R.id.etDireccionP);
        etCelularP = findViewById(R.id.etCelularP);
        btnRegreso1 = findViewById(R.id.btnRegreso1);

        mQueue = Volley.newRequestQueue(this);

        ConsumirWS();


        btnRegreso1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MenuCliente.class);
                finish();
                startActivity(intent);
            }
        });

        etNombP.setFocusable(false);
        etNombP.setEnabled(true);
        etNombP.setCursorVisible(false);
        etNombP.setKeyListener(null);

        etApeP.setFocusable(false);
        etApeP.setEnabled(true);
        etApeP.setCursorVisible(false);
        etApeP.setKeyListener(null);

        etEmailP.setFocusable(false);
        etEmailP.setEnabled(true);
        etEmailP.setCursorVisible(false);
        etEmailP.setKeyListener(null);

        etFechaNaciP.setFocusable(false);
        etFechaNaciP.setEnabled(true);
        etFechaNaciP.setCursorVisible(false);
        etFechaNaciP.setKeyListener(null);

        etDireccionP.setFocusable(false);
        etDireccionP.setEnabled(true);
        etDireccionP.setCursorVisible(false);
        etDireccionP.setKeyListener(null);

        etCelularP.setFocusable(false);
        etCelularP.setEnabled(true);
        etCelularP.setCursorVisible(false);
        etCelularP.setKeyListener(null);

    }




    private void ConsumirWS() {
        Conexion con = new Conexion();
        String url = con.ConecBase()+con.PerfilU();
        SharedPreferences preferences =getSharedPreferences("appLogin",Context.MODE_PRIVATE);
        Map<String, String> params = new HashMap<>();
        params.put("codigo", preferences.getString("idusuario",""));
        JSONObject paramJSON = new JSONObject(params);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, paramJSON, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    etNombP.setText(response.getString("nombres"));
                    etApeP.setText(response.getString("apellidos"));
                    etEmailP.setText(response.getString("correo"));
                    etFechaNaciP.setText(response.getString("fecha_nacimiento"));
                    etDireccionP.setText(response.getString("direccion"));
                    etCelularP.setText(response.getString("celular"));
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
