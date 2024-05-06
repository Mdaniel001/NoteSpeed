package co.edu.uniminuto;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import co.edu.uniminuto.entity.Note;
import co.edu.uniminuto.model.NoteDao;

public class CrearNota extends AppCompatActivity {

    private Button btnInsertarNota;
    private NoteDao noteDao;
    private ListView  lvNotas;
    private ImageButton btnBuscarNota;
    private Button btnVerNota;
    private Button btnEliminarNota;
    private EditText etBuscarNota;
    private EditText etEliminarNota;


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
        noteDao = new NoteDao(this);

        //Enlace boton Ingreso Guardar Nota con su metodo
        btnInsertarNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {irPantallaEditarNota();
            }
        });

        mostrarNotasEnLista();




        // Enlaza el botón btnEliminarNota con su método onClick
        btnEliminarNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String notaABuscar = etEliminarNota.getText().toString().trim();
                if (!notaABuscar.isEmpty()) {
                    eliminarNotaPorNombre(notaABuscar);
                } else {
                    Snackbar.make(v, "Por favor, ingresa el título de la nota a eliminar", Snackbar.LENGTH_LONG).show();
                }
            }
        });
        btnBuscarNota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreNota = etBuscarNota.getText().toString().trim();
                if (!nombreNota.isEmpty()) {
                    buscarYMostrarNota(nombreNota);
                } else {
                    Snackbar.make(v, "Por favor, ingresa el título de la nota a buscar", Snackbar.LENGTH_LONG).show();
                }
            }
        });


    }










    // Método para buscar la nota por su nombre y mostrarla para editar
    private void buscarYMostrarNota(String nombreNota) {

        Note notaEncontrada = noteDao.getNoteByTitleAndStatus(nombreNota);
        if (notaEncontrada != null) {

            Intent intent = new Intent(this, EditarNota.class);
            intent.putExtra("nota", (CharSequence) notaEncontrada); // Pasar la nota a la actividad de edición
            startActivity(intent);
        } else {
            Snackbar.make(findViewById(android.R.id.content), "No se encontró ninguna nota con ese título", Snackbar.LENGTH_LONG).show();
        }
    }


    //Metodo para Mostar Notas en la Lista

    private void mostrarNotasEnLista() {

        ArrayList<Note> listaNotas = noteDao.getNotaList();


        ArrayAdapter<Note> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaNotas);


        lvNotas.setAdapter(adapter);
    }



    // Método para eliminar una nota por su nombre
    private void eliminarNotaPorNombre(String nombreNota) {
        noteDao.deleteNoteByTitle(nombreNota, findViewById(android.R.id.content));
    }

    ///Metodo ir pantalla Editar Nota
    private void irPantallaEditarNota(){
        Intent intent = new Intent(this, EditarNota.class);
        startActivity(intent);
    }


    private void initObject() {
        btnInsertarNota = findViewById(R.id.btnInsertarNota);
        lvNotas = findViewById(R.id.lvNotas);
        btnVerNota = findViewById(R.id.btnVerNota);
        btnEliminarNota = findViewById(R.id.btnEliminarNota);
        btnBuscarNota = findViewById(R.id.btnBuscarNota);
        etBuscarNota = findViewById(R.id.etBuscarNota);
        etEliminarNota = findViewById(R.id.etEliminarNota);

    }


}
