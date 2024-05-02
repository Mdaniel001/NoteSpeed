package co.edu.uniminuto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegistroExitoso extends AppCompatActivity {

    private Button btnIngresoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro_exitoso);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initObject();
        btnIngresoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irPatanllaLogin();
            }
        });
    }

    private void initObject(){
        btnIngresoLogin = findViewById(R.id.btnSiguiente);
    }
    private void irPatanllaLogin(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}