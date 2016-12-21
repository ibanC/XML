package com.example.dm2.xml;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Actividad2 extends AppCompatActivity {

    private TextView txtlocalidad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad2);
        txtlocalidad=(TextView)findViewById(R.id.localidad);

        CargarXmlTask tarea = new CargarXmlTask();
        tarea.execute("http://www.aemet.es/xml/municipios/localidad_31010.xml");
    }
    //Tarea Asíncrona para cargar un XML en segundo plano
      private class CargarXmlTask extends AsyncTask<String,Integer,Boolean> {

        protected Boolean doInBackground(String... params) {

            ParserSAX saxparser =new ParserSAX(params[0]);

            saxparser.parse();

            return true;
        }
        protected  void onPostExecute(Boolean result)
        {

        }
    }
}
