package co.edu.uniminuto;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EditarNotaNumerica extends AppCompatActivity {

    //
    private TextView tvTextoEnumerado;
    private ImageButton btnSelecColor3;
    private Button btnEnumerarTexto;
    private EditText etIngresoTexto;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editar_nota_numerica);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initObject();

        //Enlace Boton View con metoco cambiar color editext
        btnSelecColor3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showColorDialog();
            };
        });
        //Enlace Boton Enumerar Texto

        btnEnumerarTexto.setOnClickListener(new View.OnClickListener(){
             @Override
            public void onClick(View v){enumerarTexto();};

        });
    }


    //Metodo Enumerar Texto
    private void enumerarTexto(){

        String text = etIngresoTexto.getText().toString();
        String[] lines = text.split("\n");
        StringBuilder enumeratedText = new StringBuilder();

        for (int i = 0; i < lines.length; i++) {
            enumeratedText.append((i + 1) + ". " + lines[i] + "\n");
        }
        tvTextoEnumerado.setText(enumeratedText.toString());
    };


    //Metodo Paleta de colores
    private void showColorDialog (){

        final String[] colors = {"Rojo", "Verde", "Azul", "Amarillo", "Morado", "Blanco", "Negro"};
        final int[] colorValues = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.MAGENTA, Color.WHITE, Color.BLACK};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Seleccionar color");
        builder.setItems(colors, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                tvTextoEnumerado.setBackgroundColor(colorValues[which]);
            }
        });
        builder.show();
    }






//Llamamos los Objetos

    private void initObject () {
        tvTextoEnumerado = findViewById(R.id.tvTextoEnumerado);
        btnSelecColor3=findViewById(R.id.btnSelecColor3);
        btnEnumerarTexto=findViewById(R.id.btnEnumerarTexto);
        etIngresoTexto=findViewById(R.id.etIngresoTexto);



    }





}