package co.edu.uniminuto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class PaginaPrincipal extends AppCompatActivity {
private Button btnIngresoNotas;
private Button btnIngresoListas;
private Button btnIngresoNotasEnumeradas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pagina_principal);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        initObject();

        //Enlace boton Ingreso Notas con su metodo
        btnIngresoNotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {irPantallaNotas();
            }

        });
        //Enlace boton Ingreso Notas Enumeradas con su metodo
        btnIngresoNotasEnumeradas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {irPantallaNotasEnumeradas();
            }

        });

        //Enlace boton Ingreso Lista Tareas con su metodo
        btnIngresoListas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {irPantallaListas();
            }

        });
    }





    ///Metodo ir pantalla Notas Enumeradas
    private void irPantallaNotasEnumeradas(){
        Intent intent = new Intent(this, CrearNotaNumerica.class);
        startActivity(intent);
    }

    ///Metodo ir pantalla Lista Tareas
    private void irPantallaListas(){
        Intent intent = new Intent(this, CrearLista.class);
        startActivity(intent);
    }

    ///Metodo ir pantalla Notas
    private void irPantallaNotas(){
        Intent intent = new Intent(this, CrearNota.class);
        startActivity(intent);
    }







    private void initObject(){

        btnIngresoNotas = findViewById(R.id.btnIngresoNotas);
        btnIngresoNotasEnumeradas=findViewById(R.id.btnIngresoNotasEnumeradas);
        btnIngresoListas=findViewById(R.id.btnIngresoListas);
    }
}