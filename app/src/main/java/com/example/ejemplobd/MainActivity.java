package com.example.ejemplobd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Conexion obj = new Conexion(this, "alumno",null,1);
        Toast.makeText(this,"BD creada ", Toast.LENGTH_LONG).show();
    }
    public void menuInsertar(View view){
        Intent obj = new Intent(this,Insertar.class);
        this.startActivity(obj);
    }
    public void menuConsultar(View view){
        Intent obj = new Intent(this,Consultar.class);
        this.startActivity(obj);
    }
}