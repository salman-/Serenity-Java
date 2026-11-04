package starter.acceptancetests;

import net.serenitybdd.annotations.Steps;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import starter.actions.LoginActions;
import starter.pages.LoginPage;
import starter.utility.UserType;

@ExtendWith(SerenityJUnit5Extension.class)
public class LoginTest extends GeneralWebTest {

    @Steps
    LoginActions loginActions;

    LoginPage loginPage;

    @Test
    public void userCanLogOnViaHomePage() {

        loginActions.login("https://www.saucedemo.com/", UserType.STANDARD_USER);

        Serenity.reportThat("Title of the page should be Products",
                ()-> Assertions.assertTrue(loginPage.getTitle().contains("Products")));
    }




}
