package helpers;

import Interfaces.Language;
import parsers.ConfigParser;
import parsers.XmlParser;

import java.io.File;

public class LanguageFactory {
    private static File en = new File("src/test/resources/en.xml");
    private static File ru = new File("src/test/resources/ru.xml");

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

    public static String getWord(Language language) {
        return XmlParser.words.get(language.getValue());
    }
}
