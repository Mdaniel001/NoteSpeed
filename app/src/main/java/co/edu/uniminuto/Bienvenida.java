package co.edu.uniminuto;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Bienvenida extends AppCompatActivity {

    //declaro los objetos
    private Button btnSiguiente2;
    private ImageView ivUsuario6;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_bienvenida);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initObject();
        // Obtenemos  la instancia de Registro.java
        Registro registro = new Registro();

        btnSiguiente2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irPatanllaPrincipal();
            }
        });

        String rutaImagen = getIntent().getStringExtra("ruta_imagen");

        Bitmap bitmap = BitmapFactory.decodeFile(rutaImagen);
        ivUsuario6.setImageBitmap(bitmap);


    }









    //LLamo los Objetos
    private void initObject(){
        btnSiguiente2 = findViewById(R.id.btnSiguiente2);
        ivUsuario6=findViewById(R.id.ivUsuario6);
    }





    private void irPatanllaPrincipal(){
        Intent intent = new Intent(this, PaginaPrincipal.class);
        startActivity(intent);
    }

}