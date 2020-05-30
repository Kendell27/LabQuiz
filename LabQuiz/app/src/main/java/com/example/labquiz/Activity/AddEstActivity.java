package com.example.labquiz.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.labquiz.Adapter.RecyclerAdapterCurso;
import com.example.labquiz.R;

public class AddEstActivity extends AppCompatActivity {

    private RecyclerView RVListaCursos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_est);

        RVListaCursos = findViewById(R.id.RVListaCursos);
        RVListaCursos.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        RVListaCursos.setLayoutManager(linearLayoutManager);

        //RecyclerAdapterCurso RAListaCursos = new RecyclerAdapterCurso();
    }
}
