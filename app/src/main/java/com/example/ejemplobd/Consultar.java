package com.example.ejemplobd;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Consultar extends AppCompatActivity {

    TextView agenda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar);
        try {
            agenda = this.findViewById(R.id.agenda);
            Conexion obj = new Conexion(this, "alumno", null, 1);
            SQLiteDatabase db = obj.getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM alumno",null);
            if(c!=null){
                c.moveToFirst();
                do{
                    int columna = c.getColumnIndex("nombre");
                    String nombre = c.getString(columna);
                    agenda.setText(agenda.getText() + "-" + nombre);
                }while(c.moveToNext());
            }
            c.close();
            db.close();
        }catch(Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        }
    }
}