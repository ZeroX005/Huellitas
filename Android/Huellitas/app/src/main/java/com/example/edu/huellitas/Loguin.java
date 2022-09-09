package com.example.edu.huellitas;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
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
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Loguin extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks{

    private TextView tvRegistrarse;
    private Button btnLoguear;
    private EditText etUsuario,etPass;
    CheckBox checkBox;
    GoogleApiClient googleApiClient;
    String SiteKey = "6LfYOH4bAAAAAFcm299xUPJmipDMRq1pUYnSsGaM";


    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loguin);

        if (Build.VERSION.SDK_INT >= 21){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorBlack));
        }

        tvRegistrarse = findViewById(R.id.tvRegistrarse);
        btnLoguear = findViewById(R.id.btnIngresar);
        etUsuario = findViewById(R.id.etUsuario);
        etPass = findViewById(R.id.etPassword);
        checkBox = findViewById(R.id.checkBox);

        SharedPreferences preferences = getSharedPreferences("appLogin",MODE_PRIVATE);
        SharedPreferences preferencesP = getSharedPreferences("appLoginP", MODE_PRIVATE);
        SharedPreferences preferencesA = getSharedPreferences("appLoginA", MODE_PRIVATE);
        if(preferences.contains("idusuario")){
            finish();
            startActivity(new Intent(Loguin.this,MenuCliente.class));
        }else if(preferencesP.contains("idusuarioP")){
            finish();
            startActivity(new Intent(Loguin.this,MenuPaseador.class));
        }else if(preferencesA.contains("idusuarioA")){
            finish();
            startActivity(new Intent(Loguin.this,MenuAdmin.class));
        }

        mQueue = Volley.newRequestQueue(this);

        googleApiClient = new GoogleApiClient.Builder(this).addApi(SafetyNet.API).addConnectionCallbacks(Loguin.this).build();
        googleApiClient.connect();

        /*
        ---Para bloquear el boton de loguin y dar función al captcha--
        btnLoguear.setEnabled(false);*/

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked()){
                    checkBox.setChecked(false);
                    SafetyNet.SafetyNetApi.verifyWithRecaptcha(googleApiClient,SiteKey).setResultCallback(new ResultCallback<SafetyNetApi.RecaptchaTokenResult>() {
                        @Override
                        public void onResult(@NonNull SafetyNetApi.RecaptchaTokenResult recaptchaTokenResult) {
                            Status status = recaptchaTokenResult.getStatus();
                            if ((status != null) && status.isSuccess()){
                                Toast.makeText(getApplicationContext(),"Verificado correctamente",Toast.LENGTH_SHORT).show();
                                checkBox.setChecked(true);
                                btnLoguear.setEnabled(true);
                                checkBox.setClickable(false);
                            }
                        }
                    });
                }else{
                    checkBox.setTextColor(Color.BLACK);
                }
            }
        });

        btnLoguear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etUsuario.getText().toString().equals("")) {
                    etUsuario.setError("Campo Obligatorio");
                }else if(etPass.getText().toString().equals("")){
                    etPass.setError("Campo Obligatorio");
                }else {
                    //startActivity(new Intent(getApplicationContext(), MenuCliente.class));
                    ConsumirWebServiceLogin();
                }
            }
        });

        etUsuario.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    etUsuario.setHint("");
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(etUsuario, InputMethodManager.SHOW_IMPLICIT);
                } else {
                    etUsuario.setHint("");

                }
            }
        });

        etPass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {
                    etPass.setHint("");
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(etPass, InputMethodManager.SHOW_IMPLICIT);
                } else {
                    etPass.setHint("");
                }
            }
        });

        tvRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Registro.class));
            }
        });


    }

    private void ConsumirWebServiceLogin() {
        Conexion con = new Conexion();
        String url = con.ConecBase()+con.LoginU();
        Map<String, String> params = new HashMap<>();
        params.put("usuario", etUsuario.getText().toString());
        params.put("contraseña", etPass.getText().toString());
        JSONObject paramJSON = new JSONObject(params);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, paramJSON, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    if(response.getBoolean("rpta") == true && response.getString("tipoUsr").equals("Cliente")){
                        //Editamos las preferencias del app.
                        SharedPreferences.Editor editor = getSharedPreferences("appLogin", MODE_PRIVATE).edit();
                        //agregar información a la preferencia.
                        editor.putString("idusuario", response.getString("idusuario"));
                        editor.apply();
                        finish();
                        startActivity(new Intent(Loguin.this,Loading.class));
                        Toast.makeText(getApplicationContext(),response.getString("mensaje"),Toast.LENGTH_LONG).show();
                    }else if(response.getBoolean("rpta") == true && response.getString("tipoUsr").equals("Petwalker")){
                        //Editamos las preferencias del app.
                        SharedPreferences.Editor editor = getSharedPreferences("appLoginP", MODE_PRIVATE).edit();
                        //agregar información a la preferencia.
                        editor.putString("idusuarioP", response.getString("idusuario"));
                        editor.apply();
                        finish();
                        startActivity(new Intent(Loguin.this,Loading2.class));
                        Toast.makeText(getApplicationContext(),response.getString("mensaje"),Toast.LENGTH_LONG).show();
                    }else if(response.getBoolean("rpta") == true && response.getString("tipoUsr").equals("Administrador")){
                        //Editamos las preferencias del app.
                        SharedPreferences.Editor editor = getSharedPreferences("appLoginA", MODE_PRIVATE).edit();
                        //agregar información a la preferencia.
                        editor.putString("idusuarioA", response.getString("idusuario"));
                        editor.apply();
                        finish();
                        startActivity(new Intent(Loguin.this,Loading3.class));
                        Toast.makeText(getApplicationContext(),response.getString("mensaje"),Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(),response.getString("mensaje"),Toast.LENGTH_LONG).show();
                    }
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

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}
