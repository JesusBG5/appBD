package com.example.ejemplobd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Eliminar extends AppCompatActivity {

    EditText cajaNombreE,cajaMatriculaE,cajaCarreraE,cajaCorreoE;
    String [] [] contactos;
    int contador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);

        cajaNombreE = this.findViewById(R.id.cajaNombreE);
        cajaMatriculaE = this.findViewById(R.id.cajaMatriculaE);
        cajaCarreraE = this.findViewById(R.id.cajaCarreraE);
        cajaCorreoE = this.findViewById(R.id.cajaCorreoE);

        try {
            Conexion obj = new Conexion(this, "alumno", null, 1);
            SQLiteDatabase db = obj.getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM alumno",null);
            if(c!=null){
                contactos = new String[c.getCount()][4];
                int i = 0;
                c.moveToFirst();
                do{
                    contactos[i][0] = c.getString(0);
                    contactos[i][1] = c.getString(1);
                    contactos[i][2] = c.getString(2);
                    contactos[i][3] = c.getString(3);
                    i++;
                }while(c.moveToNext());

                cajaMatriculaE.setText(contactos[0][0]);
                cajaNombreE.setText(contactos[0][1]);
                cajaCarreraE.setText(contactos[0][2]);
                cajaCorreoE.setText(contactos[0][3]);
            }
            c.close();
            db.close();
        }catch(Exception e){
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        }

    }

    public void siguiente(View view){
        if(contador+1 < contactos.length){
            contador++;
            cajaMatriculaE.setText(contactos[contador][0]);
            cajaNombreE.setText(contactos[contador][1]);
            cajaCarreraE.setText(contactos[contador][2]);
            cajaCorreoE.setText(contactos[contador][3]);
        }
    }

    public void anterior(View view){
        if(contador-1 >= 0){
            contador--;
            cajaMatriculaE.setText(contactos[contador][0]);
            cajaNombreE.setText(contactos[contador][1]);
            cajaCarreraE.setText(contactos[contador][2]);
            cajaCorreoE.setText(contactos[contador][3]);
        }
    }

    public void eliminar(View view){
        try {
            Conexion obj = new Conexion(this, "alumno", null, 1);
            SQLiteDatabase db = obj.getWritableDatabase();
            db.rawQuery("DELETE FROM alumno WHERE matricula='" +
                    contactos[contador][0] + "'", null);
            Intent obj2 = new Intent(this,Eliminar.class);
            this.startActivity(obj2);
            this.finish();
        }catch(Exception e){
            Toast.makeText(this,"Contacto eliminado",Toast.LENGTH_LONG).show();
        }
    }
}