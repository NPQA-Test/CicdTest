package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class NegativeLoginTests1 {
    public static void main(String[] args) throws InterruptedException {
        // Add Server Link Here
        String Websitelink = "https://demo2.testgrid.io/";
        String expectedUrl = "https://demo2.testgrid.io/build/apps";

//       String browser = System.getenv("BROWSER"); // Read the environment variable
//        WebDriver driver;
//
//        if ("chrome".equalsIgnoreCase(browser)) {
//            System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
//            driver = new ChromeDriver();
//        } else if ("firefox".equalsIgnoreCase(browser)) {
//            System.setProperty("webdriver.gecko.driver", "/path/to/geckodriver");
//            driver = new FirefoxDriver();
//        } else {
//            throw new Exception("Unsupported browser: " + browser);
//        }


          // Open the login page URL
//        driver.get(Websitelink);
//        driver.manage().window().maximize();
//
//        logininvalidscenario(driver, expectedUrl);
//
//        driver.quit();

        WebDriver driver = null;
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setCapability("tg:projectName", "Visual Test Web");


            capabilities.setCapability("tg:udid", "201");
            capabilities.setCapability("tg:userToken", "cf1gih3sl6m0hr2gc3y6u2il2zf84ewz");
            capabilities.setCapability("browserName", "firefox");


            driver = new RemoteWebDriver(new URL("http://indc1.testgrid.io/browserrun35611/wd/hub"), capabilities);

            driver.get(Websitelink);

            logininvalidscenario1(driver, expectedUrl);
        } catch (Exception e) {
            System.out.println("Test run failed: " + e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }

        Thread.sleep(5000);
    }

    private static void logininvalidscenario1(WebDriver driver, String expectedUrl) throws InterruptedException {

        String Email = "naimish.patoliya+1@testgrid.io";
        String Password = "Qwe@#123";

        // Test 1: Verify that the Website Title is displayed
        WebElement Sitelogo = driver.findElement(By.id("navbar-logo"));
        if (Sitelogo.isDisplayed()) {
            System.out.println("Logo is displayed correctly.");
        } else {
            System.out.println("Logo is NOT displayed.");
        }
        Thread.sleep(2000);

        // Test 2: Invalid Email Format
        WebElement emailField = driver.findElement(By.id("email"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement signInButton = driver.findElement(By.cssSelector(".signin-button"));
        Thread.sleep(5000);

        emailField.sendKeys("nonexistentuser@example.com");
        System.out.println("Type Email Done");
        passwordField.sendKeys("wrongpassword");
        System.out.println("Type Password Done");
        signInButton.click();
        System.out.println("tap on sign in button");
        Thread.sleep(3000);

        // Verify error message for incorrect value
        WebElement loginError = driver.findElement(By.cssSelector("span.custom_success.text-danger"));
        if (loginError.isDisplayed()) {
            System.out.println("Error message displayed for invalid credentials: " + loginError.getText());
        } else {
            System.out.println("No error message displayed for invalid credentials.");
        }

        Thread.sleep(5000);

        // Verify error message text
        String elementText = loginError.getText();
        String givenText = "Email or password is incorrect!";

        if (elementText.equals(givenText)) {
            System.out.println("Text matches!");
        } else {
            System.out.println("Text does not match. Expected: " + elementText);
        }

        emailField.clear();
        passwordField.clear();

        Thread.sleep(2000);
        signInButton.click();

        Thread.sleep(2000);
        // Test 3: Verify error messages for Blank fields & check error message text
        WebElement emailEmptyError = driver.findElement(By.xpath("(//label[@class='text-danger error'])[1]"));
        WebElement passwordEmptyError = driver.findElement(By.xpath("(//label[@class='text-danger error'])[2]"));
        if (emailEmptyError.isDisplayed() && passwordEmptyError.isDisplayed()) {
            System.out.println("Error displayed for empty email and password fields.");
            String emailEmptyErrorText = emailEmptyError.getText();
            String passwordEmptyErrorText = passwordEmptyError.getText();
            String emailgivenText = "Required";
            String passwordgivenText = "Required";

            if (emailEmptyErrorText.equals(emailgivenText)) {
                System.out.println("Email validation Text matches!");
            } else {
                System.out.println("Text does not match. Expected: " + emailgivenText + ", but got: " + emailEmptyErrorText);
            }
            if (passwordEmptyErrorText.equals(passwordgivenText)) {
                System.out.println("Password Validation Text matches!");
            } else {
                System.out.println("Text does not match. Expected: " + passwordgivenText + ", but got: " + passwordEmptyErrorText);
            }
        } else {
            System.out.println("Error not displayed for empty fields.");
        }
        Thread.sleep(5000);

        WebElement emailEmptyErrorIcon = driver.findElement(By.cssSelector("i[data-title='Email field is required!']"));
        WebElement passwordEmptyErrorIcon = driver.findElement(By.cssSelector("i[data-title='Password field is required!']"));

        //check error message text for Email
        Actions actions = new Actions(driver);
        actions.moveToElement(emailEmptyErrorIcon).perform();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        String afterContent = (String) js.executeScript("return window.getComputedStyle(arguments[0], '::after').getPropertyValue('content');",emailEmptyErrorIcon);
        afterContent = afterContent.replace("\"", "");

        System.out.println("Content of ::after: " + afterContent);


        String EmailexpectedTooltipText = "Email field is required!";
        if (afterContent.equals(EmailexpectedTooltipText)) {
            System.out.println("Tooltip text is valid got: " + afterContent);
        } else {
            System.out.println("Tooltip text is invalid: Expected: " + EmailexpectedTooltipText + ", but got: " + afterContent);
        }
        Thread.sleep(2000);

        //check error message text for password
        actions.moveToElement(passwordEmptyErrorIcon).perform();

        String afterContent1 = (String) js.executeScript("return window.getComputedStyle(arguments[0], '::after').getPropertyValue('content');",passwordEmptyErrorIcon);
        afterContent1 = afterContent1.replace("\"", "");

        System.out.println("Content of ::after: " + afterContent1);

        String passwordexpectedTooltipText = "Password field is required!";
        if (afterContent1.equals(passwordexpectedTooltipText)) {
            System.out.println("Tooltip text is valid got: " + afterContent1);
        } else {
            System.out.println("Tooltip text is invalid: Expected: " + passwordexpectedTooltipText + ", but got: " + afterContent1);
        }
        Thread.sleep(2000);

        // Test 4: Verify successful login
        emailField.click();
        emailField.sendKeys(Email);
        passwordField.sendKeys(Password);
        signInButton.click();
        Thread.sleep(2000);

        // Verify Session OverWriter
        System.out.println("Start Test: Verify Session OverWriter");

        try {
            WebElement SessionOverWriter = driver.findElement(By.cssSelector("div[aria-labelledby='swal2-title']"));
            SessionOverWriter.isDisplayed();
            System.out.println("SessionOverWriter Pop-up displayed.");

            WebElement SessionOverWriterTitle = driver.findElement(By.cssSelector("h2.swal2-title"));
            WebElement OverWritermessage = driver.findElement(By.cssSelector("div#swal2-content>p"));

            String SessionOverWriterText = SessionOverWriterTitle.getText();
            String OverWritermessageText = OverWritermessage.getText();

            String expectedSessionOverWriterTitle = "Session Overwrite?";
            String expectedOverWriterMessage = "An existing session has been detected using same login credentials. Would you like to stop existing session and start a new session?";

            if (expectedSessionOverWriterTitle.equals(SessionOverWriterText)) {
                System.out.println("Session OverWriter Title displayed correctly.");
            } else {
                System.out.println("Session OverWriter Title does not match. Got: " + SessionOverWriterText);
            }

            if (OverWritermessageText.equals(expectedOverWriterMessage)) {
                System.out.println("Session OverWriter Message displayed correctly.");
            } else {
                System.out.println("Session OverWriter Message does not match. Got: " + OverWritermessageText);
            }

            WebElement CancelButton = driver.findElement(By.cssSelector("button.btn-danger.border-0"));
            if (CancelButton.isDisplayed()) {
                System.out.println("Cancel Button displayed.");
                CancelButton.click();
                System.out.println("Clicked on Cancel Button. Session OverWriter Pop-up closed.");
            } else {
                System.out.println("Cancel Button not displayed.");
            }
            Thread.sleep(1000);

            signInButton.click();
            Thread.sleep(3000);

            WebElement SessionOverWriter1 = driver.findElement(By.cssSelector("div[aria-labelledby='swal2-title']"));
            if (SessionOverWriter1.isDisplayed()){
                System.out.println("SessionOverWriter Pop up displayed.");
                WebElement YesButton = driver.findElement(By.cssSelector("button.btn-primary.border-0"));
                System.out.println("Yes Button displayed.");
                YesButton.click();
                System.out.println("Click on Yes Button.");
                Thread.sleep(5000);
            } else {
                System.out.println("SessionOverWriter Pop up Not displayed.");
            }
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println("SessionOverWriter Pop-up not displayed.");
        }

        if (driver.getCurrentUrl().equals(expectedUrl)) {
            System.out.println("Login successful. Redirected to dashboard.");
            try {
                driver.navigate().back();
                System.out.println("Try to navigating back In Tab");
                // Step 6: Verify that the user is still logged in after navigating back
                WebElement profileLinkAfterNavigateBack = driver.findElement(By.xpath("(//a[@class='dropdown-toggle'])[1]"));  // Check for logged-in element again
                if (profileLinkAfterNavigateBack.isDisplayed()) {
                    System.out.println("User is still logged in after navigating back.");
                } else {
                    System.out.println("User is not logged in after navigating back.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else{
            System.out.println("Login failed or not redirected correctly.");
        }
        Thread.sleep(5000);

        driver.quit();
    }
}



// Invalid/Negative Login Scenarios
//Login with incorrect email & password
//Attempt to Login with empty email and password
// check Error Message for Invalid Email & password
// check hover effect & Hover text when sign-in with blank Fields
//Verify that the user is still logged in after navigating back
