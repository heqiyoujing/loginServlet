package me.gacl.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

public class XmlUtils {

    private static String filename = "DB.xml";

    public static Document getDocument() throws DocumentException{
        try {
            File f = new File("F:\\loginServlet\\src\\main\\resource\\DB.xml");
            SAXReader reader = new SAXReader();
            Document doc = reader.read(f);
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        /*URL url = XmlUtils.class.getClassLoader().getResource(filename);
        String realpath = url.getPath();

        SAXReader reader = new SAXReader();
        return reader.read(new File(realpath));*/

    }

    public static void write2Xml(Document document) throws IOException{

        try {
            OutputStream outputStream = new FileOutputStream("F:\\loginServlet\\src\\main\\resource\\DB.xml");
            OutputFormat format = OutputFormat.createPrettyPrint();
            XMLWriter writer = new XMLWriter(outputStream, format);
            writer.write( document );
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*URL url = XmlUtils.class.getClassLoader().getResource(filename);
        String realpath = url.getPath();

        OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter writer = new XMLWriter(new FileOutputStream(realpath), format );
        writer.write( document );
        writer.close();*/

    }

}
