package helpers;

import Interfaces.Options;

/**
 * XpathCreator class with XPath formatter
 */
public class XpathCreator {


    /**
     * XPath formatter
     *
     * @param template String template containing '%s' part to be altered
     * @param option String to be added to the XPath instead of '%s'
     * @return formatted String
     */
    public static String createXpath(String template, Options option) {
        return String.format(template, LanguageFactory.getWord(option));
    }
}
