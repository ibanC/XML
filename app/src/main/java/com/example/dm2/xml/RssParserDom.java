package com.example.dm2.xml;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by dm2 on 13/12/2016.
 */
public class RssParserDom {

    private URL rssUrl;

    public RssParserDom(String url){
        try {
            this.rssUrl=new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Noticia> parse()
    {
        //Instanciamos la fabrica para DOM

        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        ArrayList<Noticia> noticias=new ArrayList<Noticia>();

        //creamos un nuevo parser para DOM
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();

            //realizamos la lectura completa del XML
            Document dom = builder.parse(this.getInputStream());



            //nos posicionamos en el nodo principal del arbol(<rss>)
            Element root = dom.getDocumentElement();

            //localizamos todos los elementos <item>
            NodeList items = root.getElementsByTagName("item");

            //recorremos la lista de noticias
            for (int i = 0; i < items.getLength(); i++) {
                Noticia noticia = new Noticia();

                //obtenemos la noticia actual
                Node item = items.item(i);

                //Obtenemos la lista de datos de la noticia actual
                NodeList datosNoticia = item.getChildNodes();

                //Procesamos cada dato de la noticia
                for (int j = 0; j < datosNoticia.getLength(); j++) {
                    Node dato = datosNoticia.item(j);
                    String etiqueta = dato.getNodeName();

                    if (etiqueta.equals("title")) {
                        String texto = obtenerTexto(dato);
                        noticia.setTitulo(texto);
                    } else {
                        if (etiqueta.equals("link")) {
                            String texto = dato.getFirstChild().getNodeValue();
                            noticia.setLink(texto);
                        } else {
                            if (etiqueta.equals("guid")) {
                                noticia.setGuid(dato.getFirstChild().getNodeValue());
                            } else {
                                if (etiqueta.equals("pubDate")) {
                                    noticia.setFecha(dato.getFirstChild().getNodeValue());
                                }
                            }
                        }
                    }
                }
                noticias.add(noticia);
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        catch (Exception ex){
            throw new RuntimeException(ex);
        }
        return noticias;
    }

    public String obtenerTexto(Node dato){
        StringBuilder texto=new StringBuilder();
        NodeList fragmentos=dato.getChildNodes();

        for(int k=0;k<fragmentos.getLength();k++)
        {
            texto.append(fragmentos.item(k).getNodeValue());
        }
        return texto.toString();
    }

    public InputStream getInputStream(){
        try {
            return rssUrl.openConnection().getInputStream();
        } catch (IOException e) {
           throw new RuntimeException(e);
        }

    }
}
