package starter.acceptancetests;

import net.serenitybdd.annotations.Steps;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import starter.actions.LoginActions;
import starter.utility.UserType;

@ExtendWith(SerenityJUnit5Extension.class)
public class LoginTest extends GeneralWebTest {

    @Steps
    LoginActions loginActions;

    @Test
    public void userCanLogOnViaHomePage() {

        loginActions.login(ONLINE_SHOP_URL, UserType.STANDARD_USER);
        loginActions.verifyPageTitle("Products");
    }

}
