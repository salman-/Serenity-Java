# Serenity JUnit Starter project

A UI test automation project built with Serenity BDD and JUnit 5. The sample test logs in to [saucedemo.com](https://www.saucedemo.com/) and checks that the *Products* page appears.

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
