package starter.actions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.steps.UIInteractionSteps;
import org.junit.jupiter.api.Assertions;
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

    @Step("Expected Title {0}")
    public void verifyPageTitle(String expectedTitle) {
        Serenity.reportThat("Title of the page should be Products",
                ()-> Assertions.assertTrue(loginPage.getTitle().contains(expectedTitle)));
    }
}
