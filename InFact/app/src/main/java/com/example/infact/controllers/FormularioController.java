package com.example.infact.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.infact.modelos.Formulario;

import java.util.ArrayList;

public class FormularioController {
    private AyudanteBaseDeDatos ayudanteBaseDeDatos;
    private String NOMBRE_TABLA = "formulario";

    public FormularioController(Context context) {
        ayudanteBaseDeDatos = new AyudanteBaseDeDatos(context);
    }
    //metodo para nuevo Formulario:
    public long nuevoFormulario(Formulario formulario){
        SQLiteDatabase baseDeDatos = ayudanteBaseDeDatos.getWritableDatabase();
        ContentValues valoresParaInsertar = new ContentValues();
        valoresParaInsertar.put("nombre",formulario.getNombre());
        valoresParaInsertar.put("mes",formulario.getMes());
        valoresParaInsertar.put("anio",formulario.getAnio());
        valoresParaInsertar.put("estado",true);
        return baseDeDatos.insert(NOMBRE_TABLA,null,valoresParaInsertar);
    }

    public ArrayList listaDeFormularios() {
        SQLiteDatabase baseD = ayudanteBaseDeDatos.getWritableDatabase();
        ArrayList<Formulario> array_list = new ArrayList<Formulario>();
        Cursor cursor = baseD.rawQuery( "select * from " + NOMBRE_TABLA, null );
        cursor.moveToFirst();
        while(cursor.isAfterLast() == false) {
            String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
            int mes = Integer.parseInt(cursor.getString(cursor.getColumnIndex("mes")) );
            int anio = Integer.parseInt(cursor.getString(cursor.getColumnIndex("anio")) );
            Formulario u = new Formulario(nombre,mes,anio);
            array_list.add(u);
            cursor.moveToNext();
        }
        return array_list;
    }

    public ArrayList listaDeFormularios(String buscar) {
        SQLiteDatabase baseD = ayudanteBaseDeDatos.getWritableDatabase();
        ArrayList<Formulario> array_list = new ArrayList<Formulario>();
        Cursor cursor = baseD.rawQuery( "SELECT * FROM " + NOMBRE_TABLA + " WHERE nombre LIKE '%" + buscar + "%';", null );
        cursor.moveToFirst();
        while(cursor.isAfterLast() == false) {
            String nombre = cursor.getString(cursor.getColumnIndex("nombre"));
            int mes = Integer.parseInt(cursor.getString(cursor.getColumnIndex("mes")) );
            int anio = Integer.parseInt(cursor.getString(cursor.getColumnIndex("anio")) );
            Formulario u = new Formulario(nombre,mes,anio);
            array_list.add(u);
            cursor.moveToNext();
        }
        return array_list;
    }
}