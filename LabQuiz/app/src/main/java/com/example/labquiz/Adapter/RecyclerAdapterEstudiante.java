package com.example.labquiz.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.labquiz.Logic.Estudiante;
import com.example.labquiz.R;

import java.util.List;

public class RecyclerAdapterEstudiante extends RecyclerView.Adapter<RecyclerAdapterEstudiante.MyViewHolder> {

    private RecyclerAdapterEstudiante.RecyclerAdapterListener listener;
    private List<Estudiante> estudiantesListFiltered;

    public RecyclerAdapterEstudiante(RecyclerAdapterListener listener, List<Estudiante> estudiantesListFiltered) {
        this.listener = listener;
        this.estudiantesListFiltered = estudiantesListFiltered;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemsView = LayoutInflater.from(parent.getContext()).inflate(R.layout.est_recycle_row, parent, false);

        return new RecyclerAdapterEstudiante.MyViewHolder(itemsView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Estudiante est = estudiantesListFiltered.get(position);
        holder.titulo1.setText(est.getNombre());
        holder.titulo2.setText(est.getApellidos());
        holder.description.setText(est.getId());
    }

    @Override
    public int getItemCount() {
        return estudiantesListFiltered.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView titulo1, titulo2, description;
        //two layers
        public RelativeLayout viewForeground, viewBackgroundDelete, viewBackgroundEdit;


        public MyViewHolder(View view) {
            super(view);
            titulo1 = view.findViewById(R.id.titleFirstLbl);
            titulo2 = view.findViewById(R.id.titleSecLbl);
            description = view.findViewById(R.id.descriptionLbl);
            viewForeground = view.findViewById(R.id.view_foreground);
            viewBackgroundDelete = view.findViewById(R.id.view_background_delete);
            viewBackgroundEdit = view.findViewById(R.id.view_background_edit);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onContactSelected(estudiantesListFiltered.get(getAdapterPosition()));
                }
            });
        }

    }

    public interface RecyclerAdapterListener {
        void onContactSelected(Estudiante est);
    }
}
