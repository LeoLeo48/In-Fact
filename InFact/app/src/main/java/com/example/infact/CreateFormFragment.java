package com.example.infact;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.infact.R;
import com.example.infact.controllers.FormularioController;
import com.example.infact.modelos.Formulario;
import com.example.infact.controllers.AyudanteBaseDeDatos;

import java.util.regex.Matcher;
//import com.example.infact.peliculas.RegisterPeliculaActivity;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateFormFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class CreateFormFragment extends Fragment {
    Button btnCrear;

    EditText etNombre;
    EditText etMes;
    EditText etAnio;

    Formulario form;
    FormularioController formularioController;
    View view;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CreateFormFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateFormFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateFormFragment newInstance(String param1, String param2) {
        CreateFormFragment fragment = new CreateFormFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_create_form, container, false);
        btnCrear = view.findViewById(R.id.btnCrear);
        etNombre = view.findViewById(R.id.etNombre);
        etMes = view.findViewById(R.id.etMes);
        etAnio = view.findViewById(R.id.etAnio);

        formularioController = new FormularioController(view.getContext());

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String nombreString = etNombre.getText().toString().trim();
                    String mesString = etMes.getText().toString().trim();
                    String anioString = etAnio.getText().toString().trim();
                    //Validar que no esten vacios
                    if (nombreString.equals("")){
                        etNombre.setError("Debes insertar un nombre");
                        etNombre.requestFocus();
                        return;
                    }
                    if (mesString.equals("")){
                        etMes.setError("Debes insertar el mes");
                        etMes.requestFocus();
                        return;
                    }
                    int mesInt = Integer.parseInt(mesString);
                    if (mesInt<0){
                        etMes.setError("El mes debe ser un numero entero positivo");
                        etMes.requestFocus();
                        return;
                    }

                    if (anioString.equals("")){
                        etAnio.setError("Debes insertar el año");
                        etAnio.requestFocus();
                        return;
                    }
                    int anioInt = Integer.parseInt(mesString);
                    if (mesInt<0){
                        etAnio.setError("El año debe ser un numero entero positivo");
                        etAnio.requestFocus();
                        return;
                    }
                    //Vamos a instanciar el docente
                    form = new Formulario(nombreString,mesInt,anioInt);
                    long resultadoInsercion = formularioController.nuevoFormulario(form);
                    if (resultadoInsercion==-1){
                        Toast.makeText(view.getContext(),"Error al insertar el formulario", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(view.getContext(),"El formulario se inserto con exito", Toast.LENGTH_LONG).show();
                        Log.i("Formulario insertado"," fila : "+resultadoInsercion);
                        limpiarFormulario();
                    }

                }catch (Exception ex){
                    Toast.makeText(view.getContext(),"Error "+ex.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
    private void limpiarFormulario() {
        etMes.setText("");
        etAnio.setText("");
        etNombre.setText("");
    }
}