package co.edu.uniminuto.model;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
                String hashedPassword = hashPassword(user.getPassword());
                values.put("use_password", hashedPassword);
                String hashedConfirmation = hashPassword(user.getPasswordConfirm());
                values.put("use_password_confirm", hashedConfirmation);
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

    // Método para hashear la contraseña utilizando SHA-256
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes());
            return Base64.encodeToString(hash, Base64.DEFAULT);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }


}
