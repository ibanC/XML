package com.example.dm2.xml;

import android.widget.Toast;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
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
        try {
            parser = factory.newSAXParser();
            GestorContenidoXML gestor = new GestorContenidoXML();
            parser.parse(this.getInputStream(),gestor);
            return gestor.getDias();
        } catch (Exception e) {
           throw new RuntimeException(e);
        }

    }
    public InputStream getInputStream(){
        try {
            return Url.openConnection().getInputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
