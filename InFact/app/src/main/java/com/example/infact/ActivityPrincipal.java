package com.example.infact;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.infact.adapters.FormularioAdapter;
import com.example.infact.controllers.FormularioController;
import com.example.infact.modelos.Formulario;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class ActivityPrincipal extends AppCompatActivity {

    //del enlistar
    ArrayList<Formulario> listaDatos = new ArrayList<Formulario>();

    RecyclerView rvPeliculas;
    FormularioAdapter adaptadorDatos;
    FormularioController formularioController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        rvPeliculas = findViewById(R.id.rvFormularios);
        //etFiltrarPelicula= findViewById(R.id.etFiltrarPelicula);
        //imgPe = findViewById(R.id.fotoP);
        mostrarLista();
    }

    //--------------------Tool Bar----------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.itemCrear:
                IrCrearFormulario();
                return true;
            case R.id.itemQuienes:
                IrQuienesSomos();
                return true;
            case R.id.itemSalir:
                Salir(findViewById(R.id.linearLayout));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void mostrarLista(){
        rvPeliculas.setLayoutManager(new GridLayoutManager(this, 1));
        formularioController = new FormularioController(ActivityPrincipal.this);
        listaDatos = formularioController.listaDeFormularios();
        adaptadorDatos = new FormularioAdapter(listaDatos);
        rvPeliculas.setAdapter(adaptadorDatos);

        adaptadorDatos.setOnItemClickListener(new FormularioAdapter.OnItemClickListener() {
            @Override
            public void onUpdateClick(int id) {
                try {
                    Intent intent = new Intent(getApplicationContext(), SelectFacturasActivity.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }
                catch (Exception ex) {
                    Toast.makeText(getApplicationContext(),ex.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    // ------------------------------Salir----------
    public class SalirSIListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            finish(); System.exit(0);
        }
    }
    /*
    public class SalirNOListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            Toast.makeText(getApplicationContext(),"Te quedas",Toast.LENGTH_SHORT).show();
        }
    }
    */

    //----------------Metodos ToolBar
    public void IrCrearFormulario()
    {
        try {
            Intent intent = new Intent(this, CrearFormularioActivity.class);
            startActivity(intent);
        }
        catch (Exception ex) {
            Toast.makeText(this,ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public void IrQuienesSomos()
    {
        try {
            Intent intent = new Intent(this, QuienesSomosActivity.class);
            startActivity(intent);
        }
        catch (Exception ex) {
            Toast.makeText(this,ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    public void Salir(View v)
    {
        Snackbar snackbar = Snackbar.make(v, getResources().getString(R.string.snackbar_message), Snackbar.LENGTH_LONG);
        snackbar.setAction(R.string.salirSI_string, new SalirSIListener());
        snackbar.show();
        //Alert Dialog (se puede borrar y no pasa nada)--------------------
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("¿Estas seguro de querer salir?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish(); System.exit(0);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        AlertDialog alt = alert.create();
        alt.setTitle("Salir de la App");
        alt.show();
        //

    }
}







