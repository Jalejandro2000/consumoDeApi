package com.example.apieventospaises;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.ScrollingMovementMethod;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.apieventospaises.interfaces.apiUsuarios;
import com.example.apieventospaises.modelo.usuarios;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private Context Ctx;

    TextView datos;
    TextView txtTitel ;


    TextView id;
    TextView vol;
    TextView num;
    TextView fechaP;
    TextView titulo;
    TextView doi;
    ImageView cover;
    Button btnVisualizar;



    Spinner sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //iniciar spiner ok
        sp = findViewById(R.id.spiner8);
        //convertir el xml y adaptarlo a un spiner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.lista, android.R.layout.simple_spinner_item);
        sp.setAdapter(adapter);

        datos = findViewById(R.id.txtdatos);
        datos.setMovementMethod(new ScrollingMovementMethod());

        txtTitel = findViewById(R.id.txtMensaje);

        id = findViewById(R.id.txtid);



    }

    private void findR(){

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://gorest.co.in/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        apiUsuarios apiPaises2 = retrofit.create(apiUsuarios.class);
        Call<List<usuarios>> call = apiPaises2.getusuarios();

        call.enqueue(new Callback<List<usuarios>>() {
            @Override
            public void onResponse(Call<List<usuarios>> call, retrofit2.Response<List<usuarios>> response) {
                if(!response.isSuccessful()){
                    datos.setText("Código: " + response.code());
                    return;
                }
                List<usuarios> kushkiList = response.body();

                //Mostrar los datos en el TextView
                for (usuarios ladata: kushkiList)
                {
                    String labelId = "Id: ";
                    String labelNombre = "Nombre: ";



                    SpannableString myTextCode = new SpannableString(labelId + ladata.getData() +"\n");
                    SpannableString myTextName = new SpannableString(labelNombre + ladata.getData()+ "\n\n");

                    StyleSpan bold = new StyleSpan(Typeface.BOLD);
                    StyleSpan bold2 = new StyleSpan(Typeface.BOLD);

                    myTextCode.setSpan(bold, 0 , labelId.length()-1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
                    myTextName.setSpan(bold2, 0 , labelNombre.length()-1, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

                    datos.append(myTextCode);
                    datos.append(myTextName);
                }


            }

            @Override
            public void onFailure(Call<List<usuarios>> call, Throwable t) {
                String msj = "Mensaje de error: " + t.getMessage();
                datos.setText(msj);
            }
        });


    }


    public void btnVisualizar_Click(View view)
    {
        txtTitel.setText("Utilizando la librería " + sp.getSelectedItem());
        datos.setText("");
        if(sp.getSelectedItem().toString().toUpperCase().equals("Retrofit".toUpperCase()))
        {
            Toast.makeText(this, "Su petición está siendo procesada.....", Toast.LENGTH_LONG).show();
            findR();
        }
        else if(sp.getSelectedItem().toString().toUpperCase().equals("Volley".toUpperCase()))
        {
            Toast.makeText(this, "Su petición está siendo procesada.....", Toast.LENGTH_LONG).show();
              findV();
        }
        else
        {
            txtTitel.setText("Título de librería");
            Toast.makeText(sp.getContext(), "Seleccionar una librería para mostrar datos", Toast.LENGTH_LONG).show();
        }

        //Toast.makeText(spOption.getContext(), "Selección: " + spOption.getSelectedItem(), Toast.LENGTH_LONG).show();
    }


 private void findV() {

        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);
        String URL = "https://gorest.co.in/public/v1/users?page=1";



         ArrayList<String> lista = new ArrayList<String>();
         JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
             @Override
             public void onResponse(JSONObject response) {
                 try {
                     JSONArray jsonArray = response.getJSONArray("data");

                     for (int i = 0; i < jsonArray.length(); i++) {

                         JSONObject data = jsonArray.getJSONObject(i);
                         lista.add("Nombre: " +
                                 data.getString("name").toString()  +"\n Email: " +

                                 data.getString("email") +   "\n Género: " +

                                 data.getString("gender") +  "\n Estado: " +

                                 data.getString("status") + "\n\n");
                     }
                     datos.setKeyListener(null);
                     datos.setText(lista.toString());
                 } catch (JSONException e) {
                     e.printStackTrace();
                 }
             }
         }, new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError error) {
                 datos.append(String.valueOf("ERROR"));
                 error.printStackTrace();

             }
         });

     requestQueue.add(request);


 }


 }