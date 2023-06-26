package com.davidvicario.conversornumerosromanos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    //Variables
    private String romano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ocultar barra de aplicaciones
        if (getSupportActionBar() != null) getSupportActionBar().hide();

        //Declaración de entrada/salida de textos
        final EditText roman = findViewById(R.id.editTextRomano);
        final TextView arabi = findViewById(R.id.resultadoArabico);

        //Accion de boton para resolucion mientras se escribe
        roman.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                romano = String.valueOf(roman.getText());

                //romano = romano.replaceAll("[^MmDdCcLlXxVvIi]","");

                int resul = romanoADecimal(romano);

                arabi.setText(String.valueOf(resul));
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    //Funcion para convertir numeros romanos a decimal
    public static int romanoADecimal (String numeroRomano){

        int decimP = 0;
        int decimU = 0;

        //Hacer que todas las letras entren como mayusculas.
        String numRomano = numeroRomano.toUpperCase();

        //Comprueba de manera decreciente.
        for (int i = numRomano.length() -1; i >= 0; i--){

            //La entrada del String pasa a Char
            char cDecimal = numRomano.charAt(i);

            //Comprueba y cambia el valor
            switch (cDecimal){
                case 'M':
                    decimP = resta(1000, decimU, decimP);
                    decimU = 1000;
                    break;
                case 'D':
                    decimP = resta(500, decimU, decimP);
                    decimU = 500;
                    break;
                case 'C':
                    decimP = resta(100, decimU, decimP);
                    decimU = 100;
                    break;
                case 'L':
                    decimP = resta(50, decimU, decimP);
                    decimU = 50;
                    break;
                case 'X':
                    decimP = resta(10, decimU, decimP);
                    decimU = 10;
                    break;
                case 'V':
                    decimP = resta(5, decimU, decimP);
                    decimU = 5;
                    break;
                case 'I':
                    decimP = resta(1, decimU, decimP);
                    decimU = 1;
                    break;
            }
        }
        return decimP;
    }

    //Funcion para la resta de decimal en numeros romanos.
    public static int resta (int decimP, int decimU, int decimT){
        if (decimU > decimP){
            return decimT - decimP;
        } else {
            return decimT + decimP;
        }
    }




}