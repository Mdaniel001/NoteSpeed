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

public class CrearNota extends AppCompatActivity {

    private Button btnInsertarNota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_crear_nota);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initObject();

        //Enlace boton Ingreso Guardar Nota con su metodo
        btnInsertarNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {irPantallaEditarNota();
            }
        });
    }


    ///Metodo ir pantalla Notas Enumeradas
    private void irPantallaEditarNota(){
        Intent intent = new Intent(this, EditarNota.class);
        startActivity(intent);
    }


    private void initObject(){
        btnInsertarNota = findViewById(R.id.btnInsertarNota);

    }


}
