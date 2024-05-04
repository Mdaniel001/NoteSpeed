package co.edu.uniminuto;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import co.edu.uniminuto.entity.Note;
import co.edu.uniminuto.model.NoteDao;

public class EditarNota extends AppCompatActivity {

    private EditText etNota;
    private EditText etDate;
    private Button btnGuardarNota;
    private EditText etNombreNota;
    private ImageButton btnSelecColor;

    private String date;
    private String nonbreNota;
     private String nota;



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


        btnGuardarNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarNota();
            };
        });






    }

    //metodo Guardar Nota
    private void guardarNota() {

        String title = etNombreNota.getText().toString();
        String descriptions = etNota.getText().toString();
        String dateNote = etDate.getText().toString();

        Note note = new Note(title, descriptions, dateNote);
        NoteDao.insertNote(note);


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
        etDate=findViewById(R.id.etDate);
        etNombreNota=findViewById(R.id.etNombreNota);
        btnGuardarNota=findViewById(R.id.btnGuardarNota);


    }






}