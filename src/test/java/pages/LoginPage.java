package pages;

import Enums.ErrorLoginOptions;
import base.BaseElement;
import base.Button;
import base.Field;
import helpers.LanguageFactory;
import helpers.LoginData;
import helpers.XpathCreator;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import base.PageBase;
import forms.LanguageDropDown;
import parsers.ConfigParser;

/**
 * LoginPage page object class
 * contains wrapped elements
 * and methods for any intended page interactions
 */
public class LoginPage extends PageBase {

    /** URL_MATCH contains part of the UPL which is unique for this page */
    // TODO: parse url_match from json
    private final String URL_MATCH = "aet/login.xhtml";


    /** wrapped elements of the page */
    private final String errorLoginTemplate = "//span[contains(text(),'%s')]";
    private final BaseElement errorLogin = new BaseElement(XpathCreator.createXpath(errorLoginTemplate, ErrorLoginOptions.ERRORLOGIN));
    private final Field login = new Field("//*[@id=\"LoginForm:userid\"]");
    private final Field password = new Field("//*[@id=\"LoginForm:password\"]");
    private final LanguageDropDown languageSelect = new LanguageDropDown("//*[@id=\"LoginForm:language\"]","//*[@id=\"LoginForm:language_input\"]/option  ");
    private final Button bLogin = new Button("//*[@id=\"LoginForm:loginButton\"]");
    private final Button bConfirmLogin = new Button("//*[@id=\"duplicateLoginForm:duplicateLoginYesBtn\"]");


    /** Constructor */
    public LoginPage() {
        super();
    }

    /**
     * isOpen method indicates if this page object is loaded on the correct (corresponding) page
     *
     * @return boolean
     * TRUE if we are on the intended page
     * FALSE if we are on different page
     */
    @Override
    public boolean isOpen() {
        return driver.getCurrentUrl().contains(URL_MATCH);
    }

    /**
     * loginSuccess parses LoginData data to Login and Password fields,
     * then selects lang, then submits the login request
     *
     * Login is expected to succeed
     *
     * @param data LoginData containing Login and Password
     * @return MainPage object since we are expected to appear on the main page after successful login
     */
    @Story("login Success")
    public MainPage loginSuccess(LoginData data){
        fill(data.login, data.password);
        selectLanguageOption();
        bLogin.click();
        if (bConfirmLogin.isVisible()) {
            bConfirmLogin.click();
        }
        return new MainPage();
    }


    /**
     * loginFail parses LoginData data to Login and Password fields,
     * then selects lang, then submits the login request
     *
     * Login is expected to fail
     *
     * @param data LoginData containing Login and Password
     * @return LoginPage object since we are expected to fail login attempt and remain on the login page
     */
    @Story("login Fail")
    public LoginPage loginFail(LoginData data){
        fill(data.login, data.password);
        selectLanguageOption();
        bLogin.click();
        return this;
    }

    /**
     * fill method fills Login and Password fields with given Strings
     *
     * @param log login to be entered to the Login field
     * @param pass password to be entered to the Password field
     */
    @Step("fill login & password")
    private void fill(String log, String pass) {
        login.sendKeysWithClear(log);
        password.sendKeysWithClear(pass);
    }


    /**
     * selectLanguage method can select specified item of the lang dropdown
     *
     * @param lang String value
     *             Should be "0" for eng, "1" for rus, etc.
     */
    @Step("select Language as arg")
    private void selectLanguage(String lang){
        languageSelect.select(lang);
    }

    /** selectLanguageOption method selects language, specified in the config file */
    @Step("select Language from Config")
    private void selectLanguageOption(){
        if (ConfigParser.language.equals("en")) {
            languageSelect.select("0");
        } else if (ConfigParser.language.equals("ru")){
            languageSelect.select("1");
        }
    }


    /**
     * isErrorsPresent checks whether any error is present or not
     *
     * @return boolean
     * TRUE if any error is present
     * FALSE if not
     */
    @Step("is Errors Present")
    public boolean isErrorsPresent(){
        return errorLogin.isPresent();
    }

}