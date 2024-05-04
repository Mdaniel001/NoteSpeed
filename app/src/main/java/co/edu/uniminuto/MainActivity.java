package co.edu.uniminuto;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import co.edu.uniminuto.model.ManagerDataBase;

public class MainActivity extends AppCompatActivity {
    //Creamos Objetos
    private Button btnRegistrarse;
    private Button btnIngresar;
    private EditText  etUsurio;
    private EditText etContraseña;
    private  Button btnHuella;

    private String usuario;
    private String password;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initObject();
        //Enlace de boton registrase


        //enlace boton Registrarse con metodo
        btnRegistrarse.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {irPantallaRegistro();
            }
        });

        //enlace Boton Ingresar con inicio Sesion
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {iniciarSesion();
            }
        });

        //Enlace boton de huela con su Metodo

        btnHuella.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarSesionConHuella(v);
            }
        });


    }

    //Metodo Iniciar Sesion y paso a Bienvenida

    private void iniciarSesion() {
        usuario = etUsurio.getText().toString();
        password = etContraseña.getText().toString();
        String hashedPassword = hashPassword(password);

        ManagerDataBase managerDataBase = new ManagerDataBase(this);
        int userId = managerDataBase.getUserIdByEmail(usuario);
        if (managerDataBase.checkLogin(usuario, hashedPassword)) {
            Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this, Bienvenida.class);
            intent.putExtra("USER_ID", userId);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }




    //metodo de inicio session con huella

    public void iniciarSesionConHuella(View view) {
        BiometricManager biometricManager = BiometricManager.from(this);
        BiometricPrompt biometricPrompt = new BiometricPrompt(this, ContextCompat.getMainExecutor(this),
                new BiometricPrompt.AuthenticationCallback() {
                    @Override
                    public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                        super.onAuthenticationSucceeded(result);
                        Toast.makeText(MainActivity.this, "Huella verificada con éxito", Toast.LENGTH_SHORT).show();
                        // Iniciar sesión
                        Intent intent = new Intent(MainActivity.this, Bienvenida.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onAuthenticationFailed() {
                        super.onAuthenticationFailed();
                        Toast.makeText(MainActivity.this, "No se pudo verificar la huella", Toast.LENGTH_SHORT).show();
                    }
                });

        BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Iniciar sesión con huella")
                .setSubtitle("Coloque su dedo en el lector de huellas")
                .setNegativeButtonText("Cancelar")
                .build();

        biometricPrompt.authenticate(promptInfo);
    }





    //Metodo para ir a la pantalla de Registro

    private void irPantallaRegistro() {
        // Se crea un intent que indica la actividad que se quiere iniciar
        Intent intent = new Intent(this, Registro.class);
        // Se inicia la actividad
        startActivity(intent);
    }


    //llamamos Objetos
    private void initObject(){
        btnRegistrarse= findViewById(R.id.btnRegistrarse);
        btnIngresar=findViewById(R.id.btnIngresar);
        etContraseña=findViewById(R.id.etContraseña);
        etUsurio=findViewById(R.id.etUsuario);
        btnHuella=findViewById(R.id.btnHuella);
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