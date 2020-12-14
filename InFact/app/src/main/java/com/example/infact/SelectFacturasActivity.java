package com.example.infact;

import androidx.appcompat.app.AppCompatActivity;

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


public class SelectFacturasActivity extends AppCompatActivity {

    //EditText etUpdateNombre, etUpdateGenero, etUpdateCompania, etUpdateDuracion, etUpdatePuntuacion;
    //Button btnActualizar, btnUpdateImage;
    ListView lvLista;

    Factura factura;
    FacturaController facturaController;
    //Pattern patPuntuacion = Pattern.compile("([1-5])");
    //ImageView fotoPUpdate;
    //Bitmap imgPUpdate;
    int id;
    //private static final int PICK_IMAGE=100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_facturas);

        lvLista = findViewById(R.id.lvLista);
        factura = new Factura();
        //id = getIntent().getIntExtra("id", 0);

        facturaController = new FacturaController(SelectFacturasActivity.this);
        //
//Para realizar la consulta usaremos el ayudante y tambien una bd sqlite
        AyudanteBaseDeDatos ayudanteBaseDatos = new AyudanteBaseDeDatos(SelectFacturasActivity.this);
        SQLiteDatabase sqLiteDatabase = ayudanteBaseDatos.getReadableDatabase();

        String[] facturasLine = new String[0];
        //sera un objeto del tipo Cursor
        Cursor cursor = sqLiteDatabase.rawQuery("select * from factura;",null);
        if (cursor.moveToFirst()){
            int i = 0; facturasLine = new String[cursor.getCount()];
            do {
                int idObtenido = cursor.getInt(0);
                int nitObtenido = cursor.getInt(1);
                int facturaObtenido = cursor.getInt(2);
                String autorizacionObtenido = cursor.getString(3);
                String fechaObtenido = cursor.getString(4);
                int importeObtenido = cursor.getInt(5);
                String codigoObtenido = cursor.getString(6);

                facturasLine[i] = "N°"+idObtenido+"   "+nitObtenido+"   N°F:"+facturaObtenido+"         :"+autorizacionObtenido
                        +"    "+fechaObtenido+"     Imp:"+importeObtenido+",00    "+codigoObtenido;
                i++;
                //ahora tenemos que validar los datos

            }while (cursor.moveToNext());


        }else{
            Toast.makeText(getApplicationContext(),"No hay registros",Toast.LENGTH_LONG).show();
        }
        final String[] facturas = facturasLine;
        //

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,facturas);
        lvLista.setAdapter(adapter);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.factura_toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        Intent intent;
        switch (item.getItemId()) {
            case R.id.itemAgregarManual:
                intent = new Intent(this, CrearFacturaManual.class);
                startActivity(intent);
                return true;
            case R.id.itemAgregarQR:
                intent = new Intent(this, SelectFactEscActivity.class);
                startActivity(intent);
                return true;
            case R.id.itemExportar:
                //Salir(findViewById(R.id.linearLayout));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}