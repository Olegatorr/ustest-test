package pages;

import Enums.ErrorLoginOptions;
import base.BaseElement;
import base.Button;
import base.Field;
import helpers.XpathCreator;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import base.PageBase;
import forms.LanguageDropDown;
import parsers.ConfigParser;
import org.openqa.selenium.By;

public class LoginPage extends PageBase {

    //TODO: parse url_match from json
    private final String URL_MATCH = "aet/login.xhtml";

    //TODO: find non language-dependent solution
    private final BaseElement errorLogin = new BaseElement("//span[contains(text(),'%s')]");
    private final Field login = new Field("//*[@id=\"LoginForm:userid\"]");
    private final Field password = new Field("//*[@id=\"LoginForm:password\"]");
    private final LanguageDropDown languageSelect = new LanguageDropDown("//*[@id=\"LoginForm:language\"]","//*[@id=\"LoginForm:language_input\"]/option  ");
    private final Button bLogin = new Button("//*[@id=\"LoginForm:loginButton\"]");
    private final Button bConfirmLogin = new Button("//*[@id=\"duplicateLoginForm:duplicateLoginYesBtn\"]");

    public LoginPage() {
        super();
    }

    @Override
    public boolean isOpen() {
        return driver.getCurrentUrl().contains(URL_MATCH);
    }

    @Story("login Success")
    public MainPage loginSuccess(String log, String pass){
        fill(log, pass);
        selectLanguageOption();
        bLogin.click();
        if (bConfirmLogin.isVisible()) {
            bConfirmLogin.click();
        }
        return new MainPage();
    }

    @Story("login Fail")
    public LoginPage loginFail(String log, String pass){
        fill(log, pass);
        selectLanguageOption();
        bLogin.click();
        return this;
    }

    @Step("fill login & password")
    private void fill(String log, String pass) {
        login.sendKeysWithClear(log);
        password.sendKeysWithClear(pass);
    }

    @Step("select Language as arg")
    private void selectLanguage(String lang){
        // arg should be "0" for eng, "1" for rus, etc.
        languageSelect.select(lang);
    }

    @Step("select Language from Config")
    private void selectLanguageOption(){
        if (ConfigParser.language.equals("en")) {
            languageSelect.select("0");
        } else if (ConfigParser.language.equals("ru")){
            languageSelect.select("1");
        }
    }

    @Step("is Errors Present")
    public boolean isErrorsPresent(){
        return errorLogin.isPresence();
    }

}