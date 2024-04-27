package co.edu.uniminuto;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.biometrics.BiometricManager;
import android.hardware.biometrics.BiometricPrompt;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.ByteArrayOutputStream;

public class Registro extends AppCompatActivity {
    //Creo Objetos

    private EditText etNombres;
    private EditText etApellidos;
    private EditText etEmail;
    private EditText etContraseña1;
    private EditText etContraseña2;

    private ImageView ivFoto;
    private Button btnRegistroHuella;
    private Button btnFoto;
    private byte[] imagenBytes;










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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            ivFoto.setImageBitmap(bitmap);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
            imagenBytes = bos.toByteArray();
        }
    }



    //Metodo para Registar Usuario
    public void registrarUsuario(View view) {
        String nombres = etNombres.getText().toString();
        String apellidos = etApellidos.getText().toString();
        String correo = etEmail.getText().toString();
        String contrasena = etContraseña1.getText().toString();
        String confirmarContrasena = etContraseña2.getText().toString();

        if (contrasena.equals(confirmarContrasena)) {
            // Guardar los datos en la base de datos o en SharedPreferences
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
        }
    }






    //Lamamos los Objeros

    private void initObject(){

        etNombres = findViewById(R.id.etNombres);
        etApellidos = findViewById(R.id.etApellidos);
        etEmail = findViewById(R.id.etEmail);
        etContraseña1 = findViewById(R.id.etContraseña1);
        etContraseña2 = findViewById(R.id.etContraseña2);
        btnRegistroHuella = findViewById(R.id.btnRegistroHuella);
        btnFoto = findViewById(R.id.btnFoto);
    }


}