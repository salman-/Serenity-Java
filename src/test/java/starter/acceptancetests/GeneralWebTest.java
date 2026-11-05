package starter.acceptancetests;

import net.serenitybdd.annotations.Managed;
import org.openqa.selenium.WebDriver;

public class GeneralWebTest {

    @Managed
    WebDriver driver;

    public static final String ONLINE_SHOP_URL = "https://www.saucedemo.com/";
}
