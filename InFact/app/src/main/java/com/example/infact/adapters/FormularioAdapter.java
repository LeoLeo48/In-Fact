package com.example.infact.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infact.R;
import com.example.infact.modelos.Formulario;

import java.util.ArrayList;
import java.util.Locale;

public class FormularioAdapter extends RecyclerView.Adapter<FormularioAdapter.FormulariosViewHolder> {
    ArrayList<Formulario> listFormulario;

    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onUpdateClick(int id);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public class FormulariosViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvNombre, tvMes, tvAnio,tvID;
        LinearLayout idLinear;
        public FormulariosViewHolder(@NonNull View itemView, final OnItemClickListener listener){
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvMes = itemView.findViewById(R.id.tvMes);
            tvAnio = itemView.findViewById(R.id.tvAnio);
            idLinear = itemView.findViewById(R.id.idLinear);
            tvID = itemView.findViewById(R.id.tvID);
            tvNombre = itemView.findViewById(R.id.tvNombre);

            idLinear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int id = Integer.parseInt(tvID.getText().toString());
                        listener.onUpdateClick(id);
                    }
                }
            });


        }
        public void asignarUsuarios (Formulario u){
                tvID.setText(u.getId()+"");
                tvMes.setText("Mes: " + u.getMes());
                tvNombre.setText("Nombre: " + u.getNombre());
                tvAnio.setText("Año: " + u.getAnio());
        }
    }

    public FormularioAdapter(ArrayList<Formulario> listFormulario){ this.listFormulario = listFormulario; }

    @NonNull
    @Override
    public FormulariosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list, parent, false); //ACA ESTA ELEMENTOS_LISTA parent, false
        return new FormulariosViewHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull FormularioAdapter.FormulariosViewHolder holder, int position) {
        holder.asignarUsuarios(listFormulario.get(position));
    }

    @Override
    public int getItemCount() {
        return listFormulario.size();
    }
}
