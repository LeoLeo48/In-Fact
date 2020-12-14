package com.example.infact.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.infact.modelos.Factura;

import java.util.ArrayList;

public class FacturaController {
    private AyudanteBaseDeDatos ayudanteBaseDeDatos;
    private String NOMBRE_TABLA = "factura";

    public FacturaController(Context context) {
        ayudanteBaseDeDatos = new AyudanteBaseDeDatos(context);
    }
    //metodo para nuevo usuario:
    public long nuevoFactura(Factura usuario){
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("nit",usuario.getNit());
        valoresParaInsertar.put("factura",usuario.getFactura());
        valoresParaInsertar.put("autorizacion",usuario.getAutorizacion());
        valoresParaInsertar.put("fecha",usuario.getFecha());
        valoresParaInsertar.put("importe",usuario.getImporte());
        valoresParaInsertar.put("codigo",usuario.getCodigo());
        return baseDeDatos.insert(NOMBRE_TABLA,null,valoresParaInsertar);
    }

    public ArrayList listaDeFacturas() {
        SQLiteDatabase baseD = ayudanteBaseDeDatos.getWritableDatabase();
        ArrayList<Factura> array_list = new ArrayList<Factura>();
        Cursor cursor = baseD.rawQuery( "select * from " + NOMBRE_TABLA, null );
        cursor.moveToFirst();
        while(cursor.isAfterLast() == false) {
            int nit = Integer.parseInt(cursor.getString(cursor.getColumnIndex("nit")));
            int factura = Integer.parseInt(cursor.getString(cursor.getColumnIndex("factura")));
            String autorizacion = cursor.getString(cursor.getColumnIndex("autorizacion"));
            String fecha = cursor.getString(cursor.getColumnIndex("fecha"));
            int importe = Integer.parseInt(cursor.getString(cursor.getColumnIndex("importe")));
            String codigo = cursor.getString(cursor.getColumnIndex("codigo"));
            Factura u = new Factura(nit,factura,autorizacion,fecha,importe,codigo);
            array_list.add(u);
            cursor.moveToNext();
        }
        return array_list;
    }

}
