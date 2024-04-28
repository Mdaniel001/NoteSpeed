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
        btnIngresoNotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {irPantallaNotas();

            }
        });
    }
    private void irPantallaNotas(){
        Intent intent = new Intent(this, CrearNota.class);
        startActivity(intent);
    }

    private void initObject(){
        btnIngresoNotas = findViewById(R.id.btnIngresoNotas);
    }
}