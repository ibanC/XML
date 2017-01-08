package com.example.dm2.xml;

import android.widget.Toast;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by dm2 on 21/12/2016.
 */
public class ParserSAX {
    private URL Url;
    private GestorContenidoXML gestor;

    public GestorContenidoXML getGestor() {
        return gestor;
    }

    public ParserSAX(String url){
        try {
            this.Url=new URL(url);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    public Dia parse()
    {
        SAXParserFactory factory=SAXParserFactory.newInstance();
        SAXParser parser = null;
        try {
            parser = factory.newSAXParser();
            gestor = new GestorContenidoXML();
            parser.parse(this.getInputStream(),gestor);
            return gestor.getDiaActual();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            Dia d=gestor.getDiaActual();
            return d;
        }
        catch (IOException e) {
            e.printStackTrace();
        }


       /* Dia d=new Dia();
        d.setLocalidad("Altsasu");
        return d;*/
        return gestor.getDiaActual();
    }
    public InputStream getInputStream(){
        try {
            return Url.openConnection().getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
