package com.example.edu.huellitas;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.widget.Toolbar;
import android.widget.DatePicker;
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
import java.util.regex.Pattern;

public class Registro extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +       //at least 1 digit
                    //"(?=.*[a-z])" +       //at least 1 lower case letter
                    "(?=.*[A-Z])" +       //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=*])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{8,}" +               //at least 4 characters
                    "$");

    private Button btnRegistrar;
    private EditText etNomb,etApe,etDireccion,etEmail,etFechaNaci,etCelular,etNombUsu,etContra;
    private LinearLayout cargando;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (Build.VERSION.SDK_INT >= 21){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorBlack));
        }

        btnRegistrar = findViewById(R.id.btnRegistrar);
        etNomb = findViewById(R.id.etNomb);
        etApe = findViewById(R.id.etApe);
        etDireccion = findViewById(R.id.etDireccion);
        etEmail = findViewById(R.id.etEmail);
        etFechaNaci = findViewById(R.id.etFechaNaci);
        etCelular = findViewById(R.id.etCelular);
        etNombUsu = findViewById(R.id.etNombUsu);
        etContra = findViewById(R.id.etContra);
        cargando = findViewById(R.id.cargando);

        mQueue = Volley.newRequestQueue(this);

        cargando.setVisibility(View.GONE);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etNomb.getText().toString().equals("")) {
                    etNomb.setError("Campo Obligatorio");
                }else if(etApe.getText().toString().equals("")){
                    etApe.setError("Campo Obligatorio");
                }else if(etDireccion.getText().toString().equals("")){
                    etDireccion.setError("Campo Obligatorio");
                }else if(etEmail.getText().toString().equals("")){
                    etEmail.setError("Campo Obligatorio");
                }else if(!Patterns.EMAIL_ADDRESS.matcher(etEmail.getText().toString().trim()).matches()){
                    etEmail.setError("Ingrese un email válido");
                }else if(etFechaNaci.getText().toString().equals("")){
                    etFechaNaci.setError("Campo Obligatorio");
                }else if(etCelular.getText().toString().equals("")){
                    etCelular.setError("Campo Obligatorio");
                }else if(etNombUsu.getText().toString().equals("")){
                    etNombUsu.setError("Campo Obligatorio");
                }else if(etContra.getText().toString().equals("")){
                    etContra.setError("Campo Obligatorio");
                }else if(!PASSWORD_PATTERN.matcher(etContra.getText().toString().trim()).matches()){
                    etContra.setError("La contraseña debe contener 1 número, 1 mayúscula y 1 caracter (@,#,$,%,^,&,+,=,*)");
                }else{
                    etNomb.setEnabled(false);
                    etApe.setEnabled(false);
                    etDireccion.setEnabled(false);
                    etEmail.setEnabled(false);
                    etFechaNaci.setEnabled(false);
                    etCelular.setEnabled(false);
                    etNombUsu.setEnabled(false);
                    etContra.setEnabled(false);
                    btnRegistrar.setEnabled(false);

                    cargando.setVisibility(View.VISIBLE);
                    ConsumirSWRegistrar();
                }
            }
        });



        etFechaNaci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.etFechaNaci:
                        showDatePickerDialog();
                        break;
                }
            }
        });

        etFechaNaci.setFocusable(false);
        etFechaNaci.setEnabled(true);
        etFechaNaci.setCursorVisible(false);
        etFechaNaci.setKeyListener(null);

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

    private void ConsumirSWRegistrar() {
        Conexion con = new Conexion();
        String url = con.ConecBase()+con.RegistrarU();
        Map<String, String> params = new HashMap<>();
        params.put("usuario", etNombUsu.getText().toString());
        params.put("contraseña", etContra.getText().toString());
        params.put("nombres", etNomb.getText().toString());
        params.put("apellidos", etApe.getText().toString());
        params.put("direccion", etDireccion.getText().toString());
        params.put("celular", etCelular.getText().toString());
        params.put("fecha_nacimiento",etFechaNaci.getText().toString());
        params.put("correo", etEmail.getText().toString());
        params.put("tipo", "1");
        JSONObject paramJSON = new JSONObject(params);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, paramJSON, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    if(response.getBoolean("rpta")) {
                        startActivity(new Intent(getApplicationContext(), Loguin.class));
                        Toast.makeText(getApplicationContext(),response.getString("mensaje"),Toast.LENGTH_LONG).show();
                    }else{
                        cargando.setVisibility(View.GONE);
                        etNomb.setEnabled(true);
                        etApe.setEnabled(true);
                        etDireccion.setEnabled(true);
                        etEmail.setEnabled(true);
                        etFechaNaci.setEnabled(true);
                        etCelular.setEnabled(true);
                        etNombUsu.setEnabled(true);
                        etContra.setEnabled(true);
                        btnRegistrar.setEnabled(true);
                        Toast.makeText(getApplicationContext(),"Error al registrar usuario",Toast.LENGTH_LONG).show();
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

    private void showDatePickerDialog() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                final String selectedDate = year + "-" + (month+1) + "-" + day;
                etFechaNaci.setText(selectedDate);
                etFechaNaci.setError(null);
            }
        });

        newFragment.show(Registro.this.getSupportFragmentManager(),"datePicker");
    }
}
