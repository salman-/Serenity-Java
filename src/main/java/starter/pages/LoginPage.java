package starter.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class LoginPage extends PageObject {

    @FindBy(css = ".title")
    private WebElementFacade pageTitle;

    @FindBy(id = "user-name")
    private WebElementFacade userNameInput;

    @FindBy(id = "password")
    private WebElementFacade passwordInput;

    @FindBy(id = "login-button")
    private WebElementFacade loginButton;

    public String getTitle() {
        return pageTitle.getText();
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public void insertPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void insertUsername(String username) {
        userNameInput.sendKeys(username);
    }


}
