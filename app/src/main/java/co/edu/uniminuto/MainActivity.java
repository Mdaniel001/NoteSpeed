package co.edu.uniminuto;

import android.content.Intent;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {
    //Creamos Objetos
    private Button btnRegistrarse;
    private Button btnIngresar;
    private EditText  etUsurio;
    private EditText etContraseña;
    private  Button btnHuella;





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

    //Metodo Iniciar Sesion

    private void iniciarSesion() {
        String correo = etUsurio.getText().toString();
        String contrasena = etContraseña.getText().toString();

        // Verificar si el correo y contraseña coinciden con los registrados
        // Si coinciden, iniciar sesión
        Toast.makeText(this, "Iniciando sesión...", Toast.LENGTH_SHORT).show();
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


}