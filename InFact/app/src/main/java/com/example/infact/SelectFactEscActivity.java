package com.example.infact;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.infact.controllers.AyudanteBaseDeDatos;
import com.example.infact.controllers.FacturaController;
import com.example.infact.modelos.Factura;
import com.example.infact.modelos.Formulario;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class SelectFactEscActivity extends AppCompatActivity {

    ListView lvLista;

    Factura factura;
    FacturaController facturaController;
    String datos;
    FloatingActionButton fabBoton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_facturas);
        fabBoton = findViewById(R.id.fabBoton);
        lvLista = findViewById(R.id.lvLista);
        factura = new Factura();
/*
        fabBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AyudanteBaseDeDatos ayudanteBaseDatos = new AyudanteBaseDeDatos(SelectFactEscActivity.this);
                SQLiteDatabase sqLiteDatabase = ayudanteBaseDatos.getReadableDatabase();

                //sera un objeto del tipo Cursor
                Cursor cursor = sqLiteDatabase.rawQuery("insert into factura(nit) VALUES("+datos+")",null);
                finish();
            }
        });
*/
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.fact_esc_toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        Intent intent;
        switch (item.getItemId()) {
            case R.id.itemAgregarQR:
                try {
                    new IntentIntegrator(this).initiateScan();
                }
                catch (Exception ex){}
                return true;
            case R.id.itemCerrar:
                AlertDialog.Builder alert = new AlertDialog.Builder(this);
                alert.setMessage("Los cambios se guardarán")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                AlertDialog alt = alert.create();
                alt.setTitle("¿Volver?");
                alt.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //String[] facturasLine = new String[0];
    //int i = 0;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            datos = result.getContents();
            //facturasLine[i] = datos;//
            // i++;
            final String[] facturas = {datos};
            //

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,facturas);
            lvLista.setAdapter(adapter);
        }
        catch (Exception ex)
        {
            Toast.makeText(this.getApplicationContext(),"error "+ex.getMessage(),Toast.LENGTH_LONG);
        }

    }
}