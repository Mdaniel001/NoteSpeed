package co.edu.uniminuto;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import co.edu.uniminuto.model.TasksDao;


public class CrearLista extends AppCompatActivity {

    private Button btnInsertarLista;
    private  int id;
    private ListView listView;
    private TasksDao tasksDao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_crear_lista);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        int userId = getIntent().getIntExtra("USER_ID", -1);
        id = userId;
        initObject();


        //Enlace boton Ingreso Guardar con su metodo
        btnInsertarLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {irPantallaEditarLista();
            }

        });
    }



    ///Metodo ir pantalla Nueva Nota
    private void irPantallaEditarLista(){
        Intent intent = new Intent(this, EditarLista.class);
        intent.putExtra("USER_ID", String.valueOf(id));
        startActivity(intent);
    }














    private void initObject(){
        btnInsertarLista= findViewById(R.id.btnInsertarLista);

    }






}