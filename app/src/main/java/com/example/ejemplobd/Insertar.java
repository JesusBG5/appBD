package com.example.ejemplobd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Insertar extends AppCompatActivity {

    EditText cajaM, cajaN, cajaCa, cajaC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);
        cajaM = this.findViewById(R.id.matricula);
        cajaN = this.findViewById(R.id.nombre);
        cajaCa = this.findViewById(R.id.carrera);
        cajaC = this.findViewById(R.id.correo);
    }

    public void guardar(View view){
        try {
            Conexion obj = new Conexion(this, "alumno", null, 1);
            SQLiteDatabase db = obj.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("matricula", cajaM.getText().toString());
            values.put("nombre", cajaN.getText().toString());
            values.put("carrera", cajaCa.getText().toString());
            values.put("correo", cajaC.getText().toString());
            db.insert("alumno", null, values);
            Toast.makeText(this, "Alumno ingresado", Toast.LENGTH_LONG).show();
        }catch(Exception e){
            Toast.makeText(null,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

}