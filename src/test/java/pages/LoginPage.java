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

public class LoginPage extends PageBase {
    private static String URL_MATCH = "aet/login.xhtml";
    private String errorLoginTemplate = "//span[contains(text(),'%s')]";
    private BaseElement errorLogin = new BaseElement(XpathCreator.createXpath(errorLoginTemplate, ErrorLoginOptions.ERRORLOGIN));
    private Field login = new Field("//*[@id=\"LoginForm:userid\"]");
    private Field password = new Field("//*[@id=\"LoginForm:password\"]");
    private LanguageDropDown languageSelect = new LanguageDropDown("//*[@id=\"LoginForm:language\"]","//*[@id=\"LoginForm:language_input\"]/option  ");
    private Button bLogin = new Button("//*[@id=\"LoginForm:loginButton\"]");
    private Button bConfirmLogin = new Button("//*[@id=\"duplicateLoginForm:duplicateLoginYesBtn\"]");

    public LoginPage() {
        super(URL_MATCH);
    }


    @Story("login Success")
    public MainPage loginSuccess(String log, String pass){
        login(log, pass);
        selectLanguageOption();
        bLogin.click();
        if (bConfirmLogin.isVisible()) {
            bConfirmLogin.click();
        }
        return new MainPage();
    }

    @Story("login Fail")
    public LoginPage loginFail(String log, String pass){
        login(log, pass);
        selectLanguageOption();
        bLogin.click();
        return this;
    }

    public void login(String log, String pass) {
        login.sendKeysWithClear(log);
        password.sendKeysWithClear(pass);
    }

    @Step("select Language")
    private void selectLanguage(){
        languageSelect.select();
    }

    private void selectLanguageOption(){
        if (ConfigParser.language.equals("en")) {
            languageSelect.select("0");
        } else {
            languageSelect.select("1");
        }
    }

    @Step("isErrorsPresent")
    public boolean isErrorsPresent(){
        return errorLogin.isPresence();
    }

}