package com.example.dm2.xml;

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
    private static int contDia=1;

    public List<Dia> getDias() {
        return dias;
    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
        dias=new ArrayList<Dia>();
        texto=new StringBuilder();
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);

       if(localName.equals("dia"))
       {
               diaActual=new Dia();

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

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);

        if(diaActual!=null)
        {
            if(localName.equals("nombre"))
            {
                diaActual.setLocalidad(texto.toString());
            }
            else
            {
                if(localName.equals("maxima"))
                {
                    diaActual.setTemp_Max(11);
                    //diaActual.setTemp_Min(0);
                }
               /* else
                {
                   if(localName.equals("estado_cielo"))
                    {
                        String valorhoras = attributes.getValue("periodo");
                        if(valorhoras.equals("00-24"))
                        {
                            diaActual.setEstado_cielo(attributes.getValue("descripcion"));
                        }
                    }
                }*/
            }
        }
            texto.setLength(0);
    }

}
