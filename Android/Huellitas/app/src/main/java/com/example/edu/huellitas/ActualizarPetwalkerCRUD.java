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

public class ActualizarPetwalkerCRUD extends AppCompatActivity {

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
    private EditText etnombrepetw2A,etapellidopetw2A,etdireccionpetwA,etdnipetwA,etcorreopetwA,etfnpetwA,etcelularpetwA,etusuariopetw2A,etcontrasenapetwA;
    private LinearLayout cargandoPETWA;
    private Button btnActualizarPetwA;
    private RequestQueue mQueue;
    String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_petwalker_crud);

        if (Build.VERSION.SDK_INT >= 21){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorBlack));
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etnombrepetw2A = findViewById(R.id.etnombrepetw2A);
        etapellidopetw2A = findViewById(R.id.etapellidopetw2A);
        etdireccionpetwA = findViewById(R.id.etdireccionpetwA);
        etdnipetwA = findViewById(R.id.etdnipetwA);
        etcorreopetwA = findViewById(R.id.etcorreopetwA);
        etfnpetwA = findViewById(R.id.etfnpetwA);
        etcelularpetwA = findViewById(R.id.etcelularpetwA);
        etusuariopetw2A = findViewById(R.id.etusuariopetw2A);
        etcontrasenapetwA = findViewById(R.id.etcontrasenapetwA);
        cargandoPETWA = findViewById(R.id.cargandoPETWA);
        btnActualizarPetwA = findViewById(R.id.btnActualizarPetwA);

        mQueue = Volley.newRequestQueue(this);

        cargandoPETWA.setVisibility(View.GONE);

        Bundle parametros2 = this.getIntent().getExtras();
        if(parametros2 !=null){
            etnombrepetw2A.setText(parametros2.getString("nombre"));
            etapellidopetw2A.setText(parametros2.getString("apellido"));
            etdireccionpetwA.setText(parametros2.getString("direccion"));
            etdnipetwA.setText(parametros2.getString("dni"));
            etcorreopetwA.setText(parametros2.getString("correo"));
            etfnpetwA.setText(parametros2.getString("fecha_naci"));
            etcelularpetwA.setText(parametros2.getString("celular"));
            etusuariopetw2A.setText(parametros2.getString("usuario"));
            etcontrasenapetwA.setText(parametros2.getString("contrasena"));
            codigo = parametros2.getString("codigo");
        }

        btnActualizarPetwA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etnombrepetw2A.getText().toString().equals("")) {
                    etnombrepetw2A.setError("Campo Obligatorio");
                }else if(etapellidopetw2A.getText().toString().equals("")){
                    etapellidopetw2A.setError("Campo Obligatorio");
                }else if(etdireccionpetwA.getText().toString().equals("")){
                    etdireccionpetwA.setError("Campo Obligatorio");
                }else if(etdnipetwA.getText().toString().equals("")){
                    etdnipetwA.setError("Campo Obligatorio");
                }else if(etcorreopetwA.getText().toString().equals("")){
                    etcorreopetwA.setError("Campo Obligatorio");
                }else if(!Patterns.EMAIL_ADDRESS.matcher(etcorreopetwA.getText().toString().trim()).matches()){
                    etcorreopetwA.setError("Ingrese un email válido");
                }else if(etfnpetwA.getText().toString().equals("")){
                    etfnpetwA.setError("Campo Obligatorio");
                }else if(etcelularpetwA.getText().toString().equals("")){
                    etcelularpetwA.setError("Campo Obligatorio");
                }else if(etusuariopetw2A.getText().toString().equals("")){
                    etusuariopetw2A.setError("Campo Obligatorio");
                }else if(etcontrasenapetwA.getText().toString().equals("")){
                    etcontrasenapetwA.setError("Campo Obligatorio");
                }else if(!PASSWORD_PATTERN.matcher(etcontrasenapetwA.getText().toString().trim()).matches()){
                    etcontrasenapetwA.setError("La contraseña debe contener 1 número, 1 mayúscula y 1 caracter (@,#,$,%,^,&,+,=,*)");
                }else{
                    etnombrepetw2A.setEnabled(false);
                    etapellidopetw2A.setEnabled(false);
                    etdireccionpetwA.setEnabled(false);
                    etdnipetwA.setEnabled(false);
                    etcorreopetwA.setEnabled(false);
                    etfnpetwA.setEnabled(false);
                    etcelularpetwA.setEnabled(false);
                    etusuariopetw2A.setEnabled(false);
                    etcontrasenapetwA.setEnabled(false);
                    btnActualizarPetwA.setEnabled(false);

                    cargandoPETWA.setVisibility(View.VISIBLE);
                    ConsumirSWRegistrar();
                }
            }
        });

        etfnpetwA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.etfnpetwA:
                        showDatePickerDialog();
                        break;
                }
            }
        });

        etfnpetwA.setFocusable(false);
        etfnpetwA.setEnabled(true);
        etfnpetwA.setCursorVisible(false);
        etfnpetwA.setKeyListener(null);
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
        String url = con.ConecBase()+con.ActualizarPetwalker();
        Map<String, String> params = new HashMap<>();
        params.put("codigo", codigo);
        params.put("usuario", etusuariopetw2A.getText().toString());
        params.put("contraseña", etcontrasenapetwA.getText().toString());
        params.put("nombres", etnombrepetw2A.getText().toString());
        params.put("apellidos", etapellidopetw2A.getText().toString());
        params.put("direccion", etdireccionpetwA.getText().toString());
        params.put("dni", etdnipetwA.getText().toString());
        params.put("celular", etcelularpetwA.getText().toString());
        params.put("fecha_nacimiento",etfnpetwA.getText().toString());
        params.put("correo", etcorreopetwA.getText().toString());
        params.put("tipo", "2");
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
                        cargandoPETWA.setVisibility(View.GONE);
                        etnombrepetw2A.setEnabled(true);
                        etapellidopetw2A.setEnabled(true);
                        etdireccionpetwA.setEnabled(true);
                        etdnipetwA.setEnabled(true);
                        etcorreopetwA.setEnabled(true);
                        etfnpetwA.setEnabled(true);
                        etcelularpetwA.setEnabled(true);
                        etusuariopetw2A.setEnabled(true);
                        etcontrasenapetwA.setEnabled(true);
                        btnActualizarPetwA.setEnabled(true);
                        Toast.makeText(getApplicationContext(),"Error al actualizar petwalker",Toast.LENGTH_LONG).show();
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
                etfnpetwA.setText(selectedDate);
                etfnpetwA.setError(null);
            }
        });

        newFragment.show(ActualizarPetwalkerCRUD.this.getSupportFragmentManager(),"datePicker");
    }
}
