package co.edu.uniminuto.model;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import co.edu.uniminuto.CrearLista;
import co.edu.uniminuto.RegistroExitoso;
import co.edu.uniminuto.entity.Tasks;
import co.edu.uniminuto.entity.User;

public class TasksDao {
    private  ManagerDataBase managerDataBase;
    Context context;
    View view;

    private Tasks tasks;

    public TasksDao(Context context, View view) {
        this.context = context;
        this.view = view;
        this.managerDataBase = new ManagerDataBase(context); // Inicializa ManagerDataBase con el contexto
    }

    public void insertTasks(Tasks tasks){
        try{
            SQLiteDatabase db = managerDataBase.getWritableDatabase();
            if(db != null){
                ContentValues values = new ContentValues();
                values.put("task_title",tasks.getTitle_tasks());
                values.put("task_nameList",tasks.getTitle_list());
                values.put("task_descriptions",tasks.getDescription_tasks());
                values.put("task_dateTasks",tasks.getDate_tasks());
                values.put("task_user_id",tasks.getUser_id());
                values.put("task_status","1");

                long rowId = db.insert("tasks",null,values);
                if (rowId != -1) {
                    Log.d("UserDao", "Tarea insertada exitosamente");
                    Snackbar.make(this.view, "Se ha registrado la tarea", Snackbar.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, CrearLista.class);
                    context.startActivity(intent);
                } else {
                    Log.e("UserDao", "No se pudo insertar la tarea");
                    Snackbar.make(this.view, "No se ha podido registrar la tarea", Snackbar.LENGTH_SHORT).show();
                }
            }else {
                Log.e("UserDao", "Error al acceder a la base de datos");
                Snackbar.make(this.view, "Error al acceder a la base de datos", Snackbar.LENGTH_SHORT).show();
            }
        }catch (SQLException e) {
            Log.e("UserDao", "Error al insertar tarea: " + e.getMessage());
            Snackbar.make(this.view, "Error al insertar tarea", Snackbar.LENGTH_SHORT).show();
        }
    }
    public Cursor getTasksByUserId(int userId, String searchQuery) {
        SQLiteDatabase db = managerDataBase.getReadableDatabase();
        String[] projection = {"task_id", "task_title", "task_nameList", "task_descriptions", "task_dateTasks"};
        String selection = "task_user_id = ?";
        String[] selectionArgs = {String.valueOf(userId)};
        if (searchQuery != null && !searchQuery.isEmpty()) {
            selection += " AND task_nameList LIKE ?";
            selectionArgs = new String[]{String.valueOf(userId), "%" + searchQuery + "%"};
        }
        return db.query("tasks", projection, selection, selectionArgs, null, null, null);
    }

}
