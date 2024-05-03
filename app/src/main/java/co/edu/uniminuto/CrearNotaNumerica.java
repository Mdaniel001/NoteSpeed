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

public class CrearNotaNumerica extends AppCompatActivity {

    private Button btnInsertarNotaEnumerada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_crear_nota_numerica);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        //Enlace Boton ir a pantalla editar con su metodo
        btnInsertarNotaEnumerada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {irPanatllaEditarNotaEnumerada();

            }
        });

    }

    private void irPanatllaEditarNotaEnumerada() {
        Intent intent = new Intent(this, EditarNotaNumerica.class);
        startActivity(intent);

    }










    private void initObject(){
        btnInsertarNotaEnumerada=findViewById(R.id.btnInsertarNotaEnumerada);

    }






}