package co.edu.uniminuto.model;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import co.edu.uniminuto.RegistroExitoso;
import co.edu.uniminuto.entity.User;

public class UserDao {
    private ManagerDataBase managerDataBase;
    Context context;
    View view;
    private User user;

    public UserDao(Context context, View view) {
        this.context = context;
        this.view = view;
        managerDataBase= new ManagerDataBase(this.context);
    }

    public void insertUser(User user) {
        try {
            SQLiteDatabase db = managerDataBase.getWritableDatabase();
            if (db != null) {
                ContentValues values = new ContentValues();
                values.put("use_names", user.getNames());
                values.put("use_lastNames", user.getLastNames());
                values.put("use_email", user.getEmail());
                values.put("use_password", user.getPassword());
                values.put("use_password_confirm", user.getPasswordConfirm());
                values.put("use_link_imagen", user.getLink_imagen());
                values.put("use_status","1");

                long rowId = db.insert("users", null, values);

                if (rowId != -1) {
                    Log.d("UserDao", "Usuario insertado exitosamente");
                    Snackbar.make(this.view, "Se ha registrado el usuario", Snackbar.LENGTH_SHORT).show();
                    Intent intent = new Intent(context, RegistroExitoso.class);
                    context.startActivity(intent);
                } else {
                    Log.e("UserDao", "No se pudo insertar el usuario");
                    Snackbar.make(this.view, "No se ha podido registrar el usuario", Snackbar.LENGTH_SHORT).show();
                }
            } else {
                Log.e("UserDao", "Error al acceder a la base de datos");
                Snackbar.make(this.view, "Error al acceder a la base de datos", Snackbar.LENGTH_SHORT).show();
            }
        } catch (SQLException e) {
            Log.e("UserDao", "Error al insertar usuario: " + e.getMessage());
            Snackbar.make(this.view, "Error al insertar usuario", Snackbar.LENGTH_SHORT).show();
        }
    }



}
