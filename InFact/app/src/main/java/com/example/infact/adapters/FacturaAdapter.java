package com.example.infact.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infact.R;
import com.example.infact.modelos.Factura;

import java.util.ArrayList;
import java.util.Locale;

public class FacturaAdapter extends RecyclerView.Adapter<FacturaAdapter.FacturasViewHolder> {
    ArrayList<Factura> listFacturas;

    public class FacturasViewHolder extends RecyclerView.ViewHolder
    {
        TextView etNit, etFactura, etAutorizacion,etFecha,etImporte,etCodigo;

        public FacturasViewHolder(@NonNull View itemView){
            super(itemView);
            etNit = itemView.findViewById(R.id.etNit);
            etFactura = itemView.findViewById(R.id.etFactura); //tvUsername
            etAutorizacion = itemView.findViewById(R.id.etAutorizacion);

            etImporte = itemView.findViewById(R.id.etImporte);
            etCodigo = itemView.findViewById(R.id.etCodigo);
        }

        public void asignarFacturas (Factura u){
                etNit.setText("NIT: " + u.getNit());
                etFactura.setText("Factura: " + u.getFactura());
                etAutorizacion.setText("Autorizacion: " + u.getAutorizacion());
                etFecha.setText("Fecha: " + u.getFecha());
                etImporte.setText("Importe: " + u.getImporte());
                etCodigo.setText("Codigo: " + u.getCodigo());
        }
    }

    public FacturaAdapter(ArrayList<Factura> listFacturas){
        this.listFacturas = listFacturas;
    }

    @NonNull
    @Override
    public FacturasViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list, parent, false); //ACA ESTA ELEMENTOS_LISTA parent, false
        return new FacturasViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FacturaAdapter.FacturasViewHolder holder, int position) {
        holder.asignarFacturas(listFacturas.get(position));
    }

    @Override
    public int getItemCount() {
        return listFacturas.size();
    }
}
