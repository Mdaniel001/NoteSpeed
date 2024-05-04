package co.edu.uniminuto;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.sql.SQLException;

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
        String title = etNombreNota.getText().toString().trim();
        String descriptions = etNota.getText().toString().trim();
        String dateNote = etDate.getText().toString().trim();

        // Validación de entrada
        if (title.isEmpty() || descriptions.isEmpty() || dateNote.isEmpty()) {
            // Mostrar un mensaje al usuario indicando que los campos son obligatorios
            Toast.makeText(this, "Por favor, rellene todos los campos", Toast.LENGTH_SHORT).show();
            return; // Salir del método porque la entrada no es válida
        }

        // Crear una nueva instancia de la nota
        Note note = new Note(title, descriptions, dateNote);

        // Intentar insertar la nota en la base de datos
        try {
            NoteDao.insertNote(note, this.findViewById(android.R.id.content));
            // Mostrar un mensaje de éxito al usuario
            Toast.makeText(this, "Nota guardada correctamente", Toast.LENGTH_SHORT).show();
        } catch (android.database.SQLException e) {
            // Manejar la excepción y mostrar un mensaje de error al usuario
            Log.e("guardarNota", "Error al guardar la nota: " + e.getMessage());
            Toast.makeText(this, "Error al guardar la nota", Toast.LENGTH_SHORT).show();
        }
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