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

public class ActualizarClienteCRUD extends AppCompatActivity {


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
    private EditText etnombrecli2A,etapellidocli2A,etdireccioncliA,etcorreocliA,etfncliA,etcelularcliA,etusuariocli2A,etcontrasenacliA;
    private LinearLayout cargandoCLIA;
    private Button btnActualizarCliA;
    private RequestQueue mQueue;
    String codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_cliente_crud);

        if (Build.VERSION.SDK_INT >= 21){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorBlack));
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        etnombrecli2A = findViewById(R.id.etnombrecli2A);
        etapellidocli2A = findViewById(R.id.etapellidocli2A);
        etdireccioncliA = findViewById(R.id.etdireccioncliA);
        etcorreocliA = findViewById(R.id.etcorreocliA);
        etfncliA = findViewById(R.id.etfncliA);
        etcelularcliA = findViewById(R.id.etcelularcliA);
        etusuariocli2A = findViewById(R.id.etusuariocli2A);
        etcontrasenacliA = findViewById(R.id.etcontrasenacliA);
        cargandoCLIA = findViewById(R.id.cargandoCLIA);
        btnActualizarCliA = findViewById(R.id.btnActualizarCliA);

        mQueue = Volley.newRequestQueue(this);

        cargandoCLIA.setVisibility(View.GONE);


        Bundle parametros1 = this.getIntent().getExtras();
        if(parametros1 !=null){
            etnombrecli2A.setText(parametros1.getString("nombre"));
            etapellidocli2A.setText(parametros1.getString("apellido"));
            etdireccioncliA.setText(parametros1.getString("direccion"));
            etcorreocliA.setText(parametros1.getString("correo"));
            etfncliA.setText(parametros1.getString("fecha_naci"));
            etcelularcliA.setText(parametros1.getString("celular"));
            etusuariocli2A.setText(parametros1.getString("usuario"));
            etcontrasenacliA.setText(parametros1.getString("contrasena"));
            codigo = parametros1.getString("codigo");
        }

        btnActualizarCliA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etnombrecli2A.getText().toString().equals("")) {
                    etnombrecli2A.setError("Campo Obligatorio");
                }else if(etapellidocli2A.getText().toString().equals("")){
                    etapellidocli2A.setError("Campo Obligatorio");
                }else if(etdireccioncliA.getText().toString().equals("")){
                    etdireccioncliA.setError("Campo Obligatorio");
                }else if(etcorreocliA.getText().toString().equals("")){
                    etcorreocliA.setError("Campo Obligatorio");
                }else if(!Patterns.EMAIL_ADDRESS.matcher(etcorreocliA.getText().toString().trim()).matches()){
                    etcorreocliA.setError("Ingrese un email válido");
                }else if(etfncliA.getText().toString().equals("")){
                    etfncliA.setError("Campo Obligatorio");
                }else if(etcelularcliA.getText().toString().equals("")){
                    etcelularcliA.setError("Campo Obligatorio");
                }else if(etusuariocli2A.getText().toString().equals("")){
                    etusuariocli2A.setError("Campo Obligatorio");
                }else if(etcontrasenacliA.getText().toString().equals("")){
                    etcontrasenacliA.setError("Campo Obligatorio");
                }else if(!PASSWORD_PATTERN.matcher(etcontrasenacliA.getText().toString().trim()).matches()){
                    etcontrasenacliA.setError("La contraseña debe contener 1 número, 1 mayúscula y 1 caracter (@,#,$,%,^,&,+,=,*)");
                }else{
                    etnombrecli2A.setEnabled(false);
                    etapellidocli2A.setEnabled(false);
                    etdireccioncliA.setEnabled(false);
                    etcorreocliA.setEnabled(false);
                    etfncliA.setEnabled(false);
                    etcelularcliA.setEnabled(false);
                    etusuariocli2A.setEnabled(false);
                    etcontrasenacliA.setEnabled(false);
                    btnActualizarCliA.setEnabled(false);

                    cargandoCLIA.setVisibility(View.VISIBLE);
                    ConsumirSWRegistrar();
                }
            }
        });



        etfncliA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.etfncliA:
                        showDatePickerDialog();
                        break;
                }
            }
        });

        etfncliA.setFocusable(false);
        etfncliA.setEnabled(true);
        etfncliA.setCursorVisible(false);
        etfncliA.setKeyListener(null);
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
        String url = con.ConecBase()+con.ActualizarCliente();
        Map<String, String> params = new HashMap<>();
        params.put("codigo", codigo);
        params.put("usuario", etusuariocli2A.getText().toString());
        params.put("contraseña", etcontrasenacliA.getText().toString());
        params.put("nombres", etnombrecli2A.getText().toString());
        params.put("apellidos", etapellidocli2A.getText().toString());
        params.put("direccion", etdireccioncliA.getText().toString());
        params.put("celular", etcelularcliA.getText().toString());
        params.put("fecha_nacimiento",etfncliA.getText().toString());
        params.put("correo", etcorreocliA.getText().toString());
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
                        cargandoCLIA.setVisibility(View.GONE);
                        etnombrecli2A.setEnabled(true);
                        etapellidocli2A.setEnabled(true);
                        etdireccioncliA.setEnabled(true);
                        etcorreocliA.setEnabled(true);
                        etfncliA.setEnabled(true);
                        etcelularcliA.setEnabled(true);
                        etusuariocli2A.setEnabled(true);
                        etcontrasenacliA.setEnabled(true);
                        btnActualizarCliA.setEnabled(true);
                        Toast.makeText(getApplicationContext(),"Error al actualizar cliente",Toast.LENGTH_LONG).show();
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
                etfncliA.setText(selectedDate);
                etfncliA.setError(null);
            }
        });

        newFragment.show(ActualizarClienteCRUD.this.getSupportFragmentManager(),"datePicker");
    }
}
