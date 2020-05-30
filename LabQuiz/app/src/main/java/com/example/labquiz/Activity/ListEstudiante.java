package com.example.labquiz.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.example.labquiz.Adapter.RecyclerAdapterEstudiante;
import com.example.labquiz.Logic.Estudiante;
import com.example.labquiz.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ListEstudiante extends AppCompatActivity {

    String apiUrl = "http://localhost:8080/Servidor/";

    private RecyclerView RVListaEstudiantes;
    private List<Estudiante> estudianteList;
    private RecyclerAdapterEstudiante RAEstudiante;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_estudiante);

        estudianteList = new ArrayList<>();


        RVListaEstudiantes = findViewById(R.id.RVListaEstudiantes);
        RVListaEstudiantes.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        RVListaEstudiantes.setLayoutManager(linearLayoutManager);

        CargarEstudiantes c = new CargarEstudiantes();
        c.execute();

        RAEstudiante = new RecyclerAdapterEstudiante(this, estudianteList);


    }

    public class CargarEstudiantes extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            // implement API in background and store the response in current variable
            String current = "";

            try {
                URL url;
                HttpURLConnection urlConnection = null;
                try {
                    url = new URL(apiUrl + "listar");

                    urlConnection = (HttpURLConnection) url
                            .openConnection();

                    InputStream in = urlConnection.getInputStream();

                    InputStreamReader isw = new InputStreamReader(in);

                    int data = isw.read();
                    while (data != -1) {
                        current += (char) data;
                        data = isw.read();
                        System.out.print(current);

                    }
                    // return the data to onPostExecute method
                    return current;

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
            Log.d("Current", current);
            return current;
        }

        @Override
        protected void onPostExecute(String s) {

            Gson g = new Gson();
            try{
                JSONArray myArray = new JSONArray(s);
                estudianteList.clear();
                for(int i = 0; i < myArray.length(); i++){
                    estudianteList.add(g.fromJson(String.valueOf(myArray.getJSONObject(i)),Estudiante.class));
                }
                RAEstudiante.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }
}
