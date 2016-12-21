package com.example.dm2.xml;

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


    public ParserSAX(String url){
        try {
            this.Url=new URL(url);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
    public void parse()
    {
        SAXParserFactory factory=SAXParserFactory.newInstance();

        try {
            SAXParser parser=factory.newSAXParser();
            GestorContenidoXML gestor=new GestorContenidoXML();
            parser.parse(this.getInputStream(),gestor);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
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
