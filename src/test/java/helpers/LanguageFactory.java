package helpers;

import Interfaces.Language;
import parsers.ConfigParser;
import parsers.XmlParser;

import java.io.File;

/**
 * LanguageFactory class which handles Language JSON setting
 * and provides correct String data for specified Language
 */
public class LanguageFactory {
    private static final File en = new File("src/test/resources/en.xml");
    private static final File ru = new File("src/test/resources/ru.xml");


    /**
     * getData method handles Language setting and loads corresponding XML
     */
    public static void getData() {
        switch (ConfigParser.language) {
            case "ru":
                XmlParser.getData(ru);
                break;
            case "en":
                XmlParser.getData(en);
                break;
        }
    }


    /**
     * getWord is an XML getter for XML strings
     *
     * @param language String key to be found in XML
     * @return String value of specified key
     */
    public static String getWord(Language language) {
        return XmlParser.words.get(language.getValue());
    }
}
