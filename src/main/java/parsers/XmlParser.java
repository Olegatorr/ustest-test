package parsers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/** XmlParser handles parsing operation for XML */
public class XmlParser {

    /** 2 Maps for variables and words */
    public static Map<String,String> variables = new HashMap<>();
    public static Map<String,String> words = new HashMap<>();

    /**
     * Method which initializes parsing from XML to public maps
     *
     * @param file String - relative path to XML file
     */
    public static void getData(File file) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            XMLHandler handler = new XMLHandler();
            parser.parse(file, handler);
        } catch (SAXException | IOException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }


    /**
     * Override of XMLHandler startElement, which triggers for every XML line
     */
    static class XMLHandler extends DefaultHandler {
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            if (qName.equals("variable")) {
                String name = attributes.getValue("name");
                String value = attributes.getValue("value");
                variables.put(name,value);
            } else if (qName.equals("word")) {
                String name = attributes.getValue("name");
                String value = attributes.getValue("value");
                words.put(name,value);
            }
        }
    }
}
