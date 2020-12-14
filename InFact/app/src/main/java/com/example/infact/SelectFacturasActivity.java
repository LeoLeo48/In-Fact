package com.example.infact;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


public class SelectFacturasActivity extends AppCompatActivity {

    //EditText etUpdateNombre, etUpdateGenero, etUpdateCompania, etUpdateDuracion, etUpdatePuntuacion;
    //Button btnActualizar, btnUpdateImage;
    ListView lvLista;

    //Factura factura;
    //FacturaController fecturaController;
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
        //formulario = new Pelicula();
        id = getIntent().getIntExtra("id", 0);

        //peliculaController = new PeliculaController(SelectFacturasActivity.this);
        //pelicula = peliculaController.peliculaEspecifica(id);

        final String[] nombres = {"nombre1","nombre2"};
        final String[] meses = {"12","21"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,nombres);
        lvLista.setAdapter(adapter);

        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long id) {
                //tvMEnsaje.setText("Infectados en "+paises[posicion]+" son: "+infectados[posicion]);
                //para optener el item en la posicion donde se selecciono:
                Toast.makeText(getApplicationContext(),"item"+lvLista.getItemAtPosition(posicion)+" id: "+lvLista.getItemIdAtPosition(posicion),Toast.LENGTH_LONG).show();

            }
        });
    }
}