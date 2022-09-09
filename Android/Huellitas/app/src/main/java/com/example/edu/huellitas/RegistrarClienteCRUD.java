package com.example.edu.huellitas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

public class RegistrarClienteCRUD extends AppCompatActivity {

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


    private EditText etnombrecli2,etapellidocli2,etdireccioncli,etcorreocli,etfncli,etcelularcli,etusuariocli2,etcontrasenacli;
    private Button btnRegistrarCli;
    private LinearLayout cargandoCLI;
    private RequestQueue mQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cliente_crud);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (Build.VERSION.SDK_INT >= 21){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorBlack));
        }

        etnombrecli2 = findViewById(R.id.etnombrecli2);
        etapellidocli2 = findViewById(R.id.etapellidocli2);
        etdireccioncli = findViewById(R.id.etdireccioncli);
        etcorreocli = findViewById(R.id.etcorreocli);
        etfncli = findViewById(R.id.etfncli);
        etcelularcli = findViewById(R.id.etcelularcli);
        etusuariocli2 = findViewById(R.id.etusuariocli2);
        etcontrasenacli = findViewById(R.id.etcontrasenacli);
        btnRegistrarCli = findViewById(R.id.btnRegistrarCli);
        cargandoCLI = findViewById(R.id.cargandoCLI);

        mQueue = Volley.newRequestQueue(this);

        cargandoCLI.setVisibility(View.GONE);

        btnRegistrarCli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etnombrecli2.getText().toString().equals("")) {
                    etnombrecli2.setError("Campo Obligatorio");
                }else if(etapellidocli2.getText().toString().equals("")){
                    etapellidocli2.setError("Campo Obligatorio");
                }else if(etdireccioncli.getText().toString().equals("")){
                    etdireccioncli.setError("Campo Obligatorio");
                }else if(etcorreocli.getText().toString().equals("")){
                    etcorreocli.setError("Campo Obligatorio");
                }else if(!Patterns.EMAIL_ADDRESS.matcher(etcorreocli.getText().toString().trim()).matches()){
                    etcorreocli.setError("Ingrese un email válido");
                }else if(etfncli.getText().toString().equals("")){
                    etfncli.setError("Campo Obligatorio");
                }else if(etcelularcli.getText().toString().equals("")){
                    etcelularcli.setError("Campo Obligatorio");
                }else if(etusuariocli2.getText().toString().equals("")){
                    etusuariocli2.setError("Campo Obligatorio");
                }else if(etcontrasenacli.getText().toString().equals("")){
                    etcontrasenacli.setError("Campo Obligatorio");
                }else if(!PASSWORD_PATTERN.matcher(etcontrasenacli.getText().toString().trim()).matches()){
                    etcontrasenacli.setError("La contraseña debe contener 1 número, 1 mayúscula y 1 caracter (@,#,$,%,^,&,+,=,*)");
                }else{
                    etnombrecli2.setEnabled(false);
                    etapellidocli2.setEnabled(false);
                    etdireccioncli.setEnabled(false);
                    etcorreocli.setEnabled(false);
                    etfncli.setEnabled(false);
                    etcelularcli.setEnabled(false);
                    etusuariocli2.setEnabled(false);
                    etcontrasenacli.setEnabled(false);
                    btnRegistrarCli.setEnabled(false);

                    cargandoCLI.setVisibility(View.VISIBLE);
                    ConsumirSWRegistrar();
                }
            }
        });



        etfncli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.etfncli:
                        showDatePickerDialog();
                        break;
                }
            }
        });

        etfncli.setFocusable(false);
        etfncli.setEnabled(true);
        etfncli.setCursorVisible(false);
        etfncli.setKeyListener(null);
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
        String url = con.ConecBase()+con.RegistrarCliente();
        Map<String, String> params = new HashMap<>();
        params.put("usuario", etusuariocli2.getText().toString());
        params.put("contraseña", etcontrasenacli.getText().toString());
        params.put("nombres", etnombrecli2.getText().toString());
        params.put("apellidos", etapellidocli2.getText().toString());
        params.put("direccion", etdireccioncli.getText().toString());
        params.put("celular", etcelularcli.getText().toString());
        params.put("fecha_nacimiento",etfncli.getText().toString());
        params.put("correo", etcorreocli.getText().toString());
        params.put("tipo", "1");
        JSONObject paramJSON = new JSONObject(params);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, paramJSON, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try{
                    if(response.getBoolean("rpta")) {
                        finish();
                        startActivity(new Intent(getApplicationContext(), MenuAdmin.class));
                        Toast.makeText(getApplicationContext(),response.getString("mensaje"),Toast.LENGTH_LONG).show();
                    }else{
                        cargandoCLI.setVisibility(View.GONE);
                        etnombrecli2.setEnabled(true);
                        etapellidocli2.setEnabled(true);
                        etdireccioncli.setEnabled(true);
                        etcorreocli.setEnabled(true);
                        etfncli.setEnabled(true);
                        etcelularcli.setEnabled(true);
                        etusuariocli2.setEnabled(true);
                        etcontrasenacli.setEnabled(true);
                        btnRegistrarCli.setEnabled(true);
                        Toast.makeText(getApplicationContext(),"Error al registrar cliente",Toast.LENGTH_LONG).show();
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
                etfncli.setText(selectedDate);
                etfncli.setError(null);
            }
        });

        newFragment.show(RegistrarClienteCRUD.this.getSupportFragmentManager(),"datePicker");
    }
}
