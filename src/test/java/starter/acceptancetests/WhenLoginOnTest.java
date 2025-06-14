package starter.acceptancetests;

import net.serenitybdd.annotations.Managed;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.junit.jupiter.api.Test;
import starter.actions.LoginActions;
import starter.pages.LoginPage;
import starter.utility.UserType;

@ExtendWith(SerenityJUnit5Extension.class)
public class WhenLoginOnTest {

    @Managed
    WebDriver driver;

    @Steps
    LoginActions loginActions;

    LoginPage loginPage;

    @Test
    public void userCanLogOnViaHomePage() {

        loginActions.login("https://www.saucedemo.com/", UserType.STANDARD_USER);

        Assertions.assertTrue(loginPage.getTitle().contains("Products"));
    }




}
