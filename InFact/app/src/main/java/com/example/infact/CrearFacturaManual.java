package com.example.infact;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.infact.controllers.AyudanteBaseDeDatos;
import com.example.infact.controllers.FacturaController;
import com.example.infact.modelos.Factura;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CrearFacturaManual extends AppCompatActivity {

    Button btnCrear;

    EditText etNit;
    EditText etFactura;
    EditText etAutorizacion;
    EditText etImporte;
    EditText etCodigo;

    Factura fact;
    FacturaController facturaController;

    //patern texto
    Pattern patTexto = Pattern.compile("[a-zA-Z]");
    //patern numeros
    Pattern patNumero = Pattern.compile("[0-9]");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_factura_manual);

        btnCrear = findViewById(R.id.btnCrear);

        etNit = findViewById(R.id.etNit);
        etFactura = findViewById(R.id.etFactura);
        etAutorizacion = findViewById(R.id.etAutorizacion);
        etImporte = findViewById(R.id.etImporte);
        etCodigo = findViewById(R.id.etCodigo);
        facturaController = new FacturaController(CrearFacturaManual.this);

        btnCrear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                try{
                    String nit = etNit.getText().toString();
                    String factura = etFactura.getText().toString();
                    String autorizacion = etAutorizacion.getText().toString();
                    String importe = etImporte.getText().toString();
                    String codigo = etCodigo.getText().toString();

                    if ("".equals(nit)){
                        etNit.setError("You need to enter a name");
                        etNit.requestFocus();
                        return;
                    }
                    if ("".equals(factura)) {
                        etNit.setError("You need to enter a name");
                        etNit.requestFocus();
                        return;
                    }
                    if ("".equals(autorizacion)){
                        etNit.setError("You need to enter a name");
                        etNit.requestFocus();
                        return;
                    }
                    if ("".equals(importe)){
                        etNit.setError("You need to enter a name");
                        etNit.requestFocus();
                        return;
                    }
                    if ("".equals(codigo)){
                        etNit.setError("You need to enter a name");
                        etNit.requestFocus();
                    }

                        int nitInt = Integer.parseInt(nit);
                        int facturaInt = Integer.parseInt(factura);
                        int importeInt = Integer.parseInt(importe);
                        if(validarCopia())
                        {
                            String date = String.valueOf(android.text.format.DateFormat.format("dd-MM-yyyy", new java.util.Date()));
                            fact = new Factura(nitInt,facturaInt,autorizacion,date,importeInt,codigo);
                            long creado = facturaController.nuevoFactura(fact);
                            if (creado==-1){
                                    Toast.makeText(CrearFacturaManual.this, "Error al insertar la factura",Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(CrearFacturaManual.this,"Se insertó correctamente",Toast.LENGTH_LONG).show();
                                finish();
                            }
                        }

                }catch (Exception ex){
                    Toast.makeText(CrearFacturaManual.this, ex.getMessage(),Toast.LENGTH_LONG).show();
                }
            }

        });
    }
    public boolean validarCopia()
    {
        boolean noExiste = true;
        /*
        try{
            String usuarioString = etUsuario.getText().toString();
            AyudanteBaseDeDatos ayudanteBaseDeDatos = new AyudanteBaseDeDatos(CrearFacturaManual.this);
            SQLiteDatabase bd = ayudanteBaseDeDatos.getReadableDatabase();
            Cursor c = bd.rawQuery("SELECT usuario, password FROM usuarios", null);
            if(c.getCount() == 0) Toast.makeText(CrearFacturaManual.this, "Error!",Toast.LENGTH_LONG).show();
            if(c.moveToFirst()){
                do{
                    String usuarioEncontrado = c.getString(0);
                    if(usuarioString.equals(usuarioEncontrado)) {
                        if(Locale.getDefault().getLanguage().equals("en")){
                            Toast.makeText(CrearFacturaManual.this, "Error, this user already exists.",Toast.LENGTH_LONG).show();
                        }
                        else if(Locale.getDefault().getLanguage().equals("es")){
                            Toast.makeText(CrearFacturaManual.this, "Error, el Usuario ya existe.",Toast.LENGTH_LONG).show();
                        }
                        noExiste = false;
                        break;
                    }
                } while (c.moveToNext());
            }
        }
        catch (Exception ex)
        {
            Toast.makeText(CrearFacturaManual.this, "Error: "+ex.getMessage(), Toast.LENGTH_LONG).show();
        }
        */
        return noExiste;
    }
}