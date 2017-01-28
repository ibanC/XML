package com.example.dm2.xml;

import android.widget.Toast;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by dm2 on 21/12/2016.
 */
public class ParserSAX {
    private URL Url;
    //private GestorContenidoXML gestor;

    /*public GestorContenidoXML getGestor() {
        return gestor;
    }
*/
    public ParserSAX(String url){
        try {
            this.Url=new URL(url);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    public List<Dia> parse()
    {
        SAXParserFactory factory=SAXParserFactory.newInstance();
        SAXParser parser;
        GestorContenidoXML gestor = new GestorContenidoXML();
        try {
            parser = factory.newSAXParser();
            InputSource is = new InputSource(this.getInputStream());
            is.setEncoding("ISO-8859-1");
            parser.parse(is,gestor);
            return gestor.getDias();
        } catch (Exception e) {
           throw new RuntimeException();
        }
       /* Dia d=new Dia();
        d.setLocalidad("Vitoria");
        ArrayList<Dia> di=new ArrayList<Dia>();
        di.add(d);
        return di;*/
    }
    public InputStream getInputStream(){
        try {
            return Url.openConnection().getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
