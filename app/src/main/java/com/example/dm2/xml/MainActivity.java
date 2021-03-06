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

public class MainActivity extends AppCompatActivity {

    private Button btnCargar;
    private ArrayList<Noticia> noticias;
    private ListView lstOpciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCargar = (Button) findViewById(R.id.btnCargar);

        btnCargar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//Con Tarea Asíncrona
                CargarXmlTask tarea = new CargarXmlTask();
                tarea.execute("http://www.eitb.eus/es/rss/deportes/baloncesto/baskonia");

            }
        });
    }
   //Tarea Asíncrona para cargar un XML en segundo plano
    private class CargarXmlTask extends AsyncTask<String,Integer,Boolean> {

        protected Boolean doInBackground(String... params) {

            RssParserDom saxparser =new RssParserDom(params[0]);

            noticias = saxparser.parse();

            return true;
        }

        protected void onPostExecute(Boolean result) {

            //Tratamos la lista de noticias
            //Por ejemplo: escribimos los títulos en pantalla

            lstOpciones = (ListView) findViewById(R.id.lista);

            ArrayAdapter<Noticia> adaptador = new ArrayAdapter<Noticia>(MainActivity.this, android.R.layout.simple_list_item_1, noticias);

            lstOpciones.setAdapter(adaptador);

            lstOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String opcionSeleccionada = ((Noticia) adapterView.getItemAtPosition(i)).getLink();
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(opcionSeleccionada));
                    startActivity(intent);
                }
            });
        }

    }
}
