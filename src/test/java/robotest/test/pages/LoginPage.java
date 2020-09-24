package robotest.test.pages;

import io.qameta.allure.Step;
import io.qameta.allure.Story;
import robotest.base.baseForms.*;
import robotest.test.base.PageBase;
import robotest.test.forms.LanguageDropDown;

public class LoginPage extends PageBase {
    private String URL_MATCH = "aet/login.xhtml";
    private BaseElement error = new BaseElement("//span[contains(text(),'Неправильное')]");
    private Field login = new Field("//*[@id=\"LoginForm:userid\"]");
    private Field password = new Field("//*[@id=\"LoginForm:password\"]");
    private LanguageDropDown languageSelect = new LanguageDropDown("//*[@id=\"LoginForm:language\"]","//*[@id=\"LoginForm:language_input\"]/option  ");
    private Button bLogin = new Button("//*[@id=\"LoginForm:loginButton\"]");
    private Button bConfirmLogin = new Button("//*[@id=\"duplicateLoginForm:duplicateLoginYesBtn\"]");

    public LoginPage() {
        super();
    }

    @Override
    public boolean isOpen() {
        if (!driver.getCurrentUrl().contains(URL_MATCH)) {
            throw new IllegalStateException("This is not the page you are expected");
        }
        return true;
    }

    @Story("login Success")
    public MainPage loginSuccess(String log, String pass){
        fill(login,log);
        fill(password, pass);
        selectLanguage();
        bLogin.click();

        try{
            bConfirmLogin.click();
        }catch (Exception e){
            e.printStackTrace();
        }

        return new MainPage();
    }

    @Story("login Fail")
    public LoginPage loginFail(String log, String pass){
        fill(login,log);
        fill(password, pass);
        selectLanguage();
        bLogin.click();
        if (bConfirmLogin.isVisible()) {
                bConfirmLogin.click();
        }
        return this;
    }

    private void fill(Field field, String keys){
        field.clickWithClear();
        field.sendKeys(keys);
    }

    @Step("select Language")
    private void selectLanguage(){
        languageSelect.select();
    }

    @Step("isErrorsPresent")
    public boolean isErrorsPresent(){
        return error.isPresence();
    }

}