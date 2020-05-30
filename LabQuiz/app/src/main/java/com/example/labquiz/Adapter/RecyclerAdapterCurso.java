package com.example.labquiz.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.labquiz.Logic.Curso;
import com.example.labquiz.R;

import java.util.List;

public class RecyclerAdapterCurso extends RecyclerView.Adapter<RecyclerAdapterCurso.MyViewHolder> {

    private RecyclerAdapterListener listener;
    private List<Curso> cursosList;
    private List<Curso> cursosListFiltered;

    public RecyclerAdapterCurso(RecyclerAdapterListener listener, List<Curso> cursosList, List<Curso> cursosListFiltered) {
        this.listener = listener;
        this.cursosList = cursosList;
        this.cursosListFiltered = cursosListFiltered;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemsView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cursos_recycle_row_add, parent, false);

        return new MyViewHolder(itemsView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Curso curso = cursosListFiltered.get(position);
        holder.codCurso.setText(curso.getId());
        holder.titulo1.setText(curso.getId());
        holder.titulo2.setText(curso.getDescripcion());
        holder.description.setText(curso.getCreditos());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView titulo1, titulo2, description, codCurso;
        //two layers
        public RelativeLayout viewForeground;


        public MyViewHolder(View view) {
            super(view);
            titulo1 = view.findViewById(R.id.titleFirstLbl);
            titulo2 = view.findViewById(R.id.titleSecLbl);
            description = view.findViewById(R.id.descriptionLbl);
            codCurso = view.findViewById(R.id.codCurso);
            viewForeground = view.findViewById(R.id.view_foreground);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onContactSelected(cursosListFiltered.get(getAdapterPosition()));
                }
            });
        }

    }

    public interface RecyclerAdapterListener {
        void onContactSelected(Curso curso);
    }

}
