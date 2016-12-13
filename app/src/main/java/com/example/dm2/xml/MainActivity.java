package com.example.dm2.xml;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button btnCargar;
    private TextView txtResultado;

    private ArrayList<Noticia> noticias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCargar = (Button) findViewById(R.id.btnCargar);
        txtResultado = (TextView) findViewById(R.id.txtResultado);

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
            txtResultado.setText("");
            for(int i=0; i<noticias.size(); i++)
            {
                /*txtResultado.setText(
                        txtResultado.getText().toString() +
                                System.getProperty("line.separator") +
                                noticias.get(i).getTitulo());*/
                txtResultado.setText(Html.fromHtml("<a href="+noticias.get(i).getLink()+">"+txtResultado.getText().toString() + System.getProperty("line.separator") +
                        noticias.get(i).getTitulo()+"</a>"));
               /* String url =noticias.get(i).getLink();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);*/
            }
        }
    }
}
