package com.example.dm2.xml;

import android.util.Log;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dm2 on 21/12/2016.
 */
public class GestorContenidoXML extends DefaultHandler{

    private ArrayList<Dia> dias;
    private Dia diaActual;
    private StringBuilder texto;
    private boolean datosPrimerDia=false;

    public List<Dia> getDias() {
        return dias;
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        dias=new ArrayList<Dia>();
        texto=new StringBuilder();
        diaActual=new Dia();

    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);

        Log.e("etiqueta",localName);
           if(localName.equals("dia")&&datosPrimerDia==false)
           {
             int length= attributes.getLength();
               for (int i = 0; i < length; i++) {
                   String value = attributes.getValue(i);
                   diaActual.setFecha(value);
               }

           }



    }

    public void characters(char[] ch, int start, int length) throws SAXException
    {
        super.characters(ch, start, length);

        if(this.diaActual!=null)
        {
            texto.append(ch,start,length);
        }
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
        dias.add(diaActual);

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);

        Log.e("etiqueta",localName);

            if(localName.equals("nombre"))
            {
                diaActual.setLocalidad(texto.toString().replaceAll("\n",""));
            }
            else
            {
                    if(localName.equals("maxima")&&datosPrimerDia==false)
                    {
                        diaActual.setTemp_Max(texto.toString().replaceAll("\n","").replaceAll("\t",""));
                        //diaActual.setTemp_Min(0);
                    }
                    else
                    {
                        if(localName.equals("minima")&&datosPrimerDia==false)
                        {
                            diaActual.setTemp_Min(texto.toString().replaceAll("\n","").replaceAll("\t",""));
                            /*ya hemos recopilado los datos de hoy
                             */
                            datosPrimerDia=true;
                          // elementoActual="";
                        }
                    }
            }
        texto.setLength(0);

        }


    }

//}
