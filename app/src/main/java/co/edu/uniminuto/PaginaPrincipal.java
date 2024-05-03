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
    private Button btnNotasEnumeradas;

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
        initObjects();

        // Initialize button listeners
        initButtonListeners();
    }

    private void initObjects() {
        btnIngresoNotas = findViewById(R.id.btnIngresoNotas);
        btnNotasEnumeradas = findViewById(R.id.btnNotasEnumeradas);
        btnIngresoListas = findViewById(R.id.btnIngresoListas);
    }

    private void initButtonListeners() {
        btnIngresoNotas.setOnClickListener(v -> irPantallaNotas());
        btnNotasEnumeradas.setOnClickListener(v -> irPantallaNotasEnumeradas());
        btnIngresoListas.setOnClickListener(v -> irPantallaListas());
    }

    private void irPantallaNotasEnumeradas() {
        Intent intent = new Intent(this, CrearNotaNumerica.class);
        startActivity(intent);
    }

    private void irPantallaListas() {
        Intent intent = new Intent(this, CrearLista.class);
        startActivity(intent);
    }

    private void irPantallaNotas() {
        Intent intent = new Intent(this, CrearNota.class);
        startActivity(intent);
    }
}
