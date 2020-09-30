package helpers;

import Interfaces.Options;

public class XpathCreator {

    public static String createXpath(String template, Options option) {
        return String.format(template, LanguageFactory.getWord(option));
    }
}
