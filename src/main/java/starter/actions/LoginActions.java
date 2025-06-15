package starter.actions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.steps.UIInteractionSteps;
import starter.pages.LoginPage;
import starter.utility.UserType;

public class LoginActions extends UIInteractionSteps {

    LoginPage loginPage;

    @Step("Login as {1}")
    public void login(String url, UserType user) {
        openUrl(url);
        loginPage.insertUsername(user.getUsername());
        loginPage.insertPassword(user.getPassword());
        loginPage.clickLoginButton();
    }
}
