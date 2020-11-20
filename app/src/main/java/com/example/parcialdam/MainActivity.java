package com.example.parcialdam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ToggleButton;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Context contexto = this;
    ArrayList<String> lista;
    Button confirmar;
    ToggleButton encendido;
    EditText nombre;
    String valor_toggle = "No encendido";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre = (EditText) findViewById(R.id.editText);
        encendido = (ToggleButton) findViewById(R.id.toggleButton);
        confirmar = (Button) findViewById(R.id.buttonConfirmar);

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Task1().execute("");
            }
        });

        encendido.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    valor_toggle = "Encendido";
                } else {
                    valor_toggle = "No encendido";
                }
            }
        });

    }

    class Task1 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            Log.i("PRUEBA", "Hasta aca llega!");
            lista = new ArrayList<String>();
            String nombre_string = nombre.getText().toString();
            lista.add(nombre_string);
            lista.add(valor_toggle);
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            Log.i("prueba", "PostExecute");
            Intent notificationIntent = new Intent(contexto, MyNotificationPublisher.class);
            notificationIntent.putExtra("nombre", lista.get(0));
            notificationIntent.putExtra("valor", lista.get(1));

            contexto.sendBroadcast(notificationIntent);
        }
    }


}