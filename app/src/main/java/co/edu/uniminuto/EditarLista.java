package co.edu.uniminuto;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import co.edu.uniminuto.entity.Tasks;
import co.edu.uniminuto.entity.User;
import co.edu.uniminuto.model.TasksDao;
import co.edu.uniminuto.model.UserDao;

public class EditarLista extends AppCompatActivity {

    private EditText etLista;
    private EditText etTituloTasks;
    private EditText etDateTasks;
    private EditText etDescriptionsTaks;
    private Button btnInsertarLista;
    private Context context;
    private String tituloLista;
    private String tituloTarea;
    private String fechaTarea;
    private String descriptionTarea;
    private  String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editar_lista);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        int userId = getIntent().getIntExtra("USER_ID", -1);
        user_id = String.valueOf(userId);
        initObject();
        context = this;
        btnInsertarLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getData()) {
                    createTaks(v);
                }
            }
        });
    }

    public boolean getData() {
        tituloLista = etLista.getText().toString();
        tituloTarea = etTituloTasks.getText().toString();
        fechaTarea = etDateTasks.getText().toString();
        descriptionTarea = etDescriptionsTaks.getText().toString();

        if (TextUtils.isEmpty(tituloLista) || TextUtils.isEmpty(tituloTarea) || TextUtils.isEmpty(fechaTarea) ||
                TextUtils.isEmpty(descriptionTarea)) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void createTaks(View view) {
        getData();
        String user = user_id;
        Tasks tasks = new Tasks(tituloTarea, tituloLista, fechaTarea, descriptionTarea, user);
        TasksDao tasksDao = new TasksDao(context, view);
        tasksDao.insertTasks(tasks);
    }

    private void initObject(){
        etLista = findViewById(R.id.etNombreLista);
        etTituloTasks = findViewById(R.id.etNombreTarea);
        etDateTasks = findViewById(R.id.etFechaTarea);
        etDescriptionsTaks = findViewById(R.id.etDescripcionTarea);
        btnInsertarLista = findViewById(R.id.btnInsertarLista);
    }
}