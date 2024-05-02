package co.edu.uniminuto;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import co.edu.uniminuto.entity.User;
import co.edu.uniminuto.model.UserDao;

public class Registro extends AppCompatActivity {
    //Creo Objetos

    private EditText etNombres;
    private EditText etApellidos;
    private EditText etEmail;
    private EditText etContraseña1;
    private EditText etContraseña2;

    private ImageView ivFoto;

    private Button btnFoto;
    private byte[] imagenBytes;
    private Button btnRegistrarUsuario;

    private String nombres;
    private String apellidos;
    private String correo;
    private String contrasena;
    private String confirmarContrasena;
    private String link_imagen;
    private Context context;









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initObject();
        context = this;

        // Enlace Boton Registrarse con su Metodo
        btnRegistrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getData()) {
                    createUser(v);// Llama al método para registrar el usuario
                }
            }
        });


        //Enlace Boton Foto con su Metodo

        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tomarFoto();
            }
        });


    }

    //Metodo Tomar Foto

    private void tomarFoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            ivFoto.setImageBitmap(bitmap);
            guardarImagen(bitmap);
        }
    }

    private void guardarImagen(Bitmap bitmap) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";

        // Guardar la imagen en el directorio de almacenamiento externo
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imageFile = new File(storageDir, imageFileName + ".jpg");

        try {
            FileOutputStream fos = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
            Toast.makeText(this, "Imagen guardada en: " + imageFile.getAbsolutePath(), Toast.LENGTH_SHORT).show();

            // Guardar la URL de la imagen en la variable link_imagen
            link_imagen = imageFile.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Error", "Error al guardar la imagen: " + e.getMessage());
            Toast.makeText(this, "Error al guardar la imagen", Toast.LENGTH_SHORT).show();
        }
    }




    //Método para obtener los datos ingresados por el usuario
    public boolean getData() {
        nombres = etNombres.getText().toString();
        apellidos = etApellidos.getText().toString();
        correo = etEmail.getText().toString();
        contrasena = etContraseña1.getText().toString();
        confirmarContrasena = etContraseña2.getText().toString();

        if (TextUtils.isEmpty(nombres) || TextUtils.isEmpty(apellidos) || TextUtils.isEmpty(correo) ||
                TextUtils.isEmpty(contrasena) || TextUtils.isEmpty(confirmarContrasena)) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            Toast.makeText(this, "Ingrese una dirección de correo electrónico válida", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!contrasena.equals(confirmarContrasena)) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (contrasena.length() < 6) {
            Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    //Metodo para Registar Usuario
    private void createUser(View view){
        getData();
        String linkImagen = link_imagen;
        User user = new User(nombres,apellidos,correo,contrasena,confirmarContrasena,linkImagen);
        UserDao userDao = new UserDao(context, view);
        userDao.insertUser(user);

    }






    //Lamamos los Objeros

    private void initObject(){

        etNombres = findViewById(R.id.etNombres);
        etApellidos = findViewById(R.id.etApellidos);
        etEmail = findViewById(R.id.etEmail);
        etContraseña1 = findViewById(R.id.etContraseña1);
        etContraseña2 = findViewById(R.id.ivPassword3);
                btnFoto = findViewById(R.id.btnFoto);
        btnRegistrarUsuario = findViewById(R.id.btnRegistrarUsuario);
        ivFoto = findViewById(R.id.ivFoto);
    }


}