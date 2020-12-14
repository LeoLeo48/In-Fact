package com.example.infact.modelos;

import android.graphics.Bitmap;

public class Factura {

    private String autorizacion, fecha, codigo;
    private int id, nit, factura, importe;



    public Factura(){}

    public Factura( int nit, int factura, String autorizacion, String fecha, int importe,String codigo)
    {
        this.nit = nit;
        this.factura = factura;
        this.autorizacion = autorizacion;
        this.fecha = fecha;
        this.importe = importe;
        this.codigo = codigo;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) { this.id = id; }

    public int getNit() {
        return nit;
    }
    public void setNit(int nit) {
        this.nit = nit;
    }

    public int getFactura() {
        return factura;
    }
    public void setFactura(int factura) {
        this.factura = factura;
    }

    public String getAutorizacion() {
        return autorizacion;
    }
    public void setAutorizacion(String autorizacion) {
        this.autorizacion = autorizacion;
    }

    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getImporte() {
        return importe;
    }
    public void setImporte(int importe) {
        this.importe = importe;
    }

    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
