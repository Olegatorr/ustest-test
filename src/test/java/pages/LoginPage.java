package pages;

import base.BaseElement;
import base.Button;
import base.Field;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import base.PageBase;
import forms.LanguageDropDown;
import org.openqa.selenium.By;

public class LoginPage extends PageBase {

    //TODO: parse url_match from json
    private final String URL_MATCH = "aet/login.xhtml";

    //TODO: find non language-dependent solution
    private final BaseElement errorLogin = new BaseElement("//span[contains(text(),'incorrect')]");
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
        selectLanguage();
        bLogin.click();
        if (bConfirmLogin.isVisible()) {
            bConfirmLogin.click();
            System.out.println("bConfirmLogin was visible");
        }else{
            System.out.println("bConfirmLogin was not visible");
        }
        return new MainPage();
    }

    @Story("login Fail")
    public LoginPage loginFail(String log, String pass){
        fill(log, pass);
        selectLanguage();
        bLogin.click();
        return this;
    }

    private void fill(String log, String pass) {
        login.sendKeysWithClear(log);
        password.sendKeysWithClear(pass);
    }

    @Step("select Language")
    private void selectLanguage(){
        languageSelect.select("0");
    }

    @Step("isErrorsPresent")
    public boolean isErrorsPresent(){
        return errorLogin.isPresence();
    }

}