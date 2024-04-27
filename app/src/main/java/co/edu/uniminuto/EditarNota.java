package co.edu.uniminuto;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EditarNota extends AppCompatActivity {

    private EditText etNota;
    private ImageButton btnSelecColor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editar_nota);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initObject();

        btnSelecColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showColorDialog();
            };
        });
    }



    //Metodo Paleta de colores
    private void showColorDialog (){

        final String[] colors = {"Rojo", "Verde", "Azul", "Amarillo", "Morado", "Blanco", "Negro"};
        final int[] colorValues = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.MAGENTA, Color.WHITE, Color.BLACK};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Seleccionar color");
        builder.setItems(colors, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                etNota.setBackgroundColor(colorValues[which]);
            }
        });
        builder.show();
    }


    //llamamos los Objetos
    private void initObject(){
        etNota = findViewById(R.id.etNota);
        btnSelecColor=findViewById(R.id.btnSelecColor);


    }






}