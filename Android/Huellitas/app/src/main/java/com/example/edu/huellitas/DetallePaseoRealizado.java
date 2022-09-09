package com.example.edu.huellitas;

import android.content.Intent;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.edu.huellitas.Modelos.Conexion;
import com.example.edu.huellitas.Modelos.ReservaP;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DetallePaseoRealizado extends AppCompatActivity {

    private TextView tvFechaS,tvHoraS,tvClienteS,tvDireccionS,tvDuracionPaseoS,tvPrecioS,tvPagadoConS,tvTicketS,tvMetodoPagoS;
    private Button btnReclamarS;
    private LinearLayout cargando6;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_paseo_realizado);

        if (Build.VERSION.SDK_INT >= 21){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.colorBlack));
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnReclamarS = findViewById(R.id.btnReclamarS);
        tvFechaS = findViewById(R.id.tvFechaS);
        tvHoraS = findViewById(R.id.tvHoraS);
        tvClienteS = findViewById(R.id.tvClienteS);
        tvDireccionS = findViewById(R.id.tvDireccionS);
        tvDuracionPaseoS = findViewById(R.id.tvDuracionPaseoS);
        tvPrecioS = findViewById(R.id.tvPrecioS);
        tvPagadoConS = findViewById(R.id.tvPagadoConS);
        tvTicketS = findViewById(R.id.tvTicketS);
        tvMetodoPagoS = findViewById(R.id.tvMetodoPagoS);
        cargando6 = findViewById(R.id.cargando6);

        mQueue = Volley.newRequestQueue(this);

        cargando6.setVisibility(View.GONE);

        ConsumirWS();

      /*  SharedPreferences preferences = getSharedPreferences("responsable",MODE_PRIVATE);*/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent menu = new Intent(this,PaseosRealizados.class);
                startActivity(menu);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void ConsumirWS() {
        Conexion con = new Conexion();
        String url=con.ConecBase() + con.DetalleReserva();
        if(getIntent().hasExtra("paseo")){
            final ReservaP objPaseo = getIntent().getParcelableExtra("paseo");
            Map<String, String> params = new HashMap<>();
            params.put("nro_ticket",objPaseo.getNro_ticket());
            JSONObject paramsJSON = new JSONObject(params);
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, paramsJSON, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try{
                        tvTicketS.setText(objPaseo.getNro_ticket());
                        tvFechaS.setText(objPaseo.getFecha_r());
                        tvHoraS.setText(objPaseo.getHora_r());
                        tvClienteS.setText(objPaseo.getCliente());
                        tvDireccionS.setText(objPaseo.getDireccion_r());
                        tvDuracionPaseoS.setText(String.valueOf(objPaseo.getTiempo_paseo_r()));
                        tvMetodoPagoS.setText(objPaseo.getMetodopago());
                        tvPrecioS.setText(String.valueOf(objPaseo.getPrecio_r()));
                        tvPagadoConS.setText(String.valueOf(objPaseo.getPago_r()));
                            /*SharedPreferences.Editor editor = getSharedPreferences("responsable", MODE_PRIVATE).edit();
                            editor.putString("nombre", response.getString("cliente"));
                            editor.putString("idresponsable", response.getString("cod_usuario"));
                            editor.apply();*/

                            final Bundle envioDatos = new Bundle();
                            envioDatos.putString("nombre",response.getString("cliente"));
                            envioDatos.putString("idresponsable",response.getString("cod_usuario_cli"));
                            envioDatos.putString("idafectado",response.getString("cod_usuario_petw"));
                            envioDatos.putString("ticket", response.getString("nro_ticket"));
                            envioDatos.putString("tipo","paseador");

                            btnReclamarS.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    cargando6.setVisibility(View.VISIBLE);
                                    btnReclamarS.setEnabled(false);
                                    Intent intent = new Intent(getApplicationContext(), Reclamo.class);
                                    intent.putExtras(envioDatos);
                                    startActivity(intent);
                                    cargando6.setVisibility(View.GONE);
                                    btnReclamarS.setEnabled(true);

                                }
                            });


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
}
