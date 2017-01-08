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

import org.xml.sax.SAXException;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView txtlocalidad,txtTempMax,txtTempMin;
    private Dia hoy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad2);
        txtlocalidad=(TextView)findViewById(R.id.localidad);
        txtTempMax=(TextView)findViewById(R.id.tempMax);
        txtTempMin=(TextView)findViewById(R.id.tempMin);

        CargarXmlTask tarea = new CargarXmlTask();
        tarea.execute("http://www.aemet.es/xml/municipios/localidad_31010.xml");
    }
    //Tarea Asíncrona para cargar un XML en segundo plano
    private class CargarXmlTask extends AsyncTask<String,Integer,Boolean> {


        protected Boolean doInBackground(String... params) {

            ParserSAX saxparser =new ParserSAX(params[0]);

            hoy=saxparser.parse();


            return true;
        }
        protected  void onPostExecute(Boolean result)
        {
            if(hoy!=null)
            {
                txtlocalidad.setText("El tiempo en "+result+"\tEstado:"+hoy.getlocalidad());
                txtTempMin.setText("Temperatura Mínima:"+hoy.getTemp_Min());
                txtTempMax.setText("Temperatura Máxima:"+hoy.getTemp_Max());
            }
            else
            {
                txtlocalidad.setText("Hoy es null");
            }



        }
    }
}


/*package com.example.dm2.xml;

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
              /* ///Sin Tarea Asíncrona
                RssParserDom domparser = new RssParserDom("http://www.eitb.eus/es/rss/deportes/baloncesto/baskonia");

                noticias = domparser.parse();

                //Tratamos la lista de noticias
                //Por ejemplo: escribimos los títulos en pantalla
                txtResultado.setText("");
                for (int i = 0; i < noticias.size(); i++) {
                    txtResultado.setText(txtResultado.getText().toString() + System.getProperty("line.separator") + noticias.get(i).getTitulo());
                }*/
  /*          }
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

            lstOpciones =(ListView)findViewById(R.id.lista);

            ArrayAdapter<Noticia> adaptador=new ArrayAdapter<Noticia>(MainActivity.this,android.R.layout.simple_list_item_1,noticias);

            lstOpciones.setAdapter(adaptador);

            lstOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String opcionSeleccionada=((Noticia)adapterView.getItemAtPosition(i)).getLink();
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(opcionSeleccionada));
                    startActivity(intent);
                }
            });

            //sobra
            /*for(int i=0; i<noticias.size(); i++)
            {
                
                /*txtResultado.setText(
                        txtResultado.getText().toString() +
                                System.getProperty("line.separator") +
                                noticias.get(i).getTitulo());*/
                /*txtResultado.setText(Html.fromHtml("<a href="+noticias.get(i).getLink()+">"+txtResultado.getText().toString() + System.getProperty("line.separator") +
                        noticias.get(i).getTitulo()+"</a>"));*/
              /* String url =noticias.get(i).getLink();
                Toast.makeText(getApplicationContext(),url,Toast.LENGTH_LONG);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        }
    }
}*/
