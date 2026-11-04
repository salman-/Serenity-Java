# Serenity JUnit Starter project

A UI test automation project built with Serenity BDD and JUnit 5. The sample test logs in to [saucedemo.com](https://www.saucedemo.com/) and checks that the *Products* page appears.

## Table of contents

1. [Technologies](#technologies)
2. [Project structure](#project-structure)
3. [Serenity BDD in a nutshell](#serenity-bdd-in-a-nutshell)
   - [What problem does it solve?](#what-problem-does-it-solve)
   - [How does it work?](#how-does-it-work)
   - [Serenity and the Page Object Model](#serenity-and-the-page-object-model)
   - [Other essentials](#other-essentials)
4. [Running the tests](#running-the-tests)
5. [Reports](#reports)

## Technologies

| Technology | Purpose |
|---|---|
| Java 21 | Programming language |
| Maven | Build and test execution |
| Serenity BDD 4.2 | Test framework and reporting |
| JUnit 5 | Test runner |
| Selenium WebDriver (Chrome) | Browser automation |
| AssertJ | Assertions |
| Lombok | Less boilerplate code |
| Logback | Logging |
| GitHub Actions | CI pipeline |

## Project structure

```
src
├── main/java/starter
│   ├── actions/        # Step libraries: reusable user actions (LoginActions)
│   ├── pages/          # Page Objects: page elements and locators (LoginPage)
│   └── utility/        # Test data helpers (UserType)
└── test
    ├── java/starter/acceptancetests/   # Test classes (LoginTest)
    └── resources/serenity.conf         # Browser and Serenity settings
```

## Serenity BDD in a nutshell

### What problem does it solve?

With plain Selenium you have to write a lot of setup code yourself: starting and closing browsers, waiting for elements, taking screenshots and building reports. The reports are often hard for non-developers to read.

Serenity BDD takes care of this for you:

- **Driver management:** opens, configures and closes the browser for each test
- **Automatic waits:** waits for elements to be ready before using them, so tests are less flaky
- **Readable reports:** shows each test as a list of business-level steps, with screenshots
- **Living documentation:** the report also documents what the application does, and it updates on every run

### How does it work?

1. **The JUnit 5 extension:** `@ExtendWith(SerenityJUnit5Extension.class)` hands control of the test to Serenity.
2. **The browser:** `@Managed WebDriver driver` asks Serenity for a browser, which it creates from the settings in `serenity.conf`.
3. **Step libraries:** Serenity creates the fields marked `@Steps` and records each `@Step` method call as a step in the report.
4. **Page Objects:** Serenity creates `PageObject` fields automatically and connects them to the current browser.
5. **The report:** each test writes its results to `target/site/serenity`. The Maven plugin then combines them into the HTML report.

```
Test (LoginTest)  ──calls──▶  Steps (LoginActions)  ──uses──▶  Page Object (LoginPage)  ──▶  Browser
     what to test                business actions               locators + clicks/typing
```

### Serenity and the Page Object Model

The Page Object Model keeps **what a page looks like** (its locators) apart from **what the test does**. Serenity supports it directly:

```java
// pages/LoginPage.java: one class per page; it only knows about locators and simple interactions
public class LoginPage extends PageObject {
    @FindBy(id = "user-name")
    private WebElementFacade userNameInput;   // like WebElement, but with built-in waits

    public void insertUsername(String username) {
        userNameInput.sendKeys(username);
    }
}

// actions/LoginActions.java: business steps built from page actions; each one appears in the report
public class LoginActions extends UIInteractionSteps {
    LoginPage loginPage;                       // created by Serenity, no "new" needed

    @Step("Login as {1}")                      // {1} = the second parameter (user)
    public void login(String url, UserType user) {
        openUrl(url);
        loginPage.insertUsername(user.getUsername());
        loginPage.insertPassword(user.getPassword());
        loginPage.clickLoginButton();
    }
}

// acceptancetests/LoginTest.java: the test reads like a scenario
@Test
public void userCanLogOnViaHomePage() {
    loginActions.login("https://www.saucedemo.com/", UserType.STANDARD_USER);
    Serenity.reportThat("Title of the page should be Products",
            () -> assertTrue(loginPage.getTitle().contains("Products")));
}
```

If a locator changes, you only update the Page Object. The steps and tests stay the same.

### Other essentials

| Topic | What to know |
|---|---|
| `serenity.conf` | One place for settings: browser, headless mode, screenshots, base URL (see `src/test/resources`) |
| `WebElementFacade` | Use it instead of `WebElement`. It has helpers such as `isVisible()`, `waitUntilClickable()` and `getText()` |
| Screenshots | `serenity.take.screenshots`: `FOR_FAILURES`, `FOR_EACH_ACTION`, `AFTER_EACH_STEP` and so on |
| `Serenity.reportThat(...)` | Adds a named assertion to the report as its own step |
| Tags | Group tests with `@Tag("smoke")` and run only those with `mvn verify -Dgroups=smoke` |
| Environments | Define `environments { dev {...} staging {...} }` in `serenity.conf` and choose one with `-Denvironment=staging` |
| Screenplay pattern | Serenity's alternative to Page Objects, built around Actors, Tasks and Questions. It works well for large test suites |

Learn more in the [Serenity BDD documentation](https://serenity-bdd.github.io/docs/).

## Running the tests

You need Java 21, Maven and Google Chrome.

| Goal | Command |
|---|---|
| Run all tests, headed (default) | `mvn clean verify` |
| Run all tests, headless | `mvn clean verify -Dheadless.mode=true` |
| Run one test class | `mvn clean verify -Dit.test=LoginTest` |
| Run one test method | `mvn clean verify -Dit.test=LoginTest#userCanLogOnViaHomePage` |

- **Headed** opens a Chrome window, which helps when you debug locally. **Headless** runs without a window, which suits CI.
- In PowerShell, put quotes around flags: `"-Dheadless.mode=true"`.
- To change the default mode, edit `headless.mode` in `src/test/resources/serenity.conf`.

## Reports

After a run, open `target/site/serenity/index.html` in a browser to see the Serenity report.
