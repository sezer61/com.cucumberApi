package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Driver {
    //create a driver instance
    private static WebDriver driver;
    private static int timeout = 5;

    //What?=>It is just to create, initialize the driver instance.(Singleton driver)
    //Why?=>We don't want to create and initialize the driver when we don't need
    //We will create and initialize the driver when it is null
    //We can use Driver class with different browser(chrome,firefox,headless)
    private Driver() {
        //we don't want to create another abject. Singleton pattern
    }



    //to initialize the driver we create a static method
    public static WebDriver getDriver() {
        //create the driver if and only if it is null
        if (driver == null) {
            String browser = ConfigReader.getProperty("browser");
            if ("chrome".equals(browser)) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            } else if ("firefox".equals(browser)) {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            } else if ("ie".equals(browser)) {
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
            } else if ("safari".equals(browser)) {
                WebDriverManager.getInstance(SafariDriver.class).setup();
                driver = new SafariDriver();
            } else if ("chrome-headless".equals(browser)) {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
            }
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {//if the driver is pointing chrome
            driver.quit();//quit the driver
            driver = null;//set it back to null to make sure driver is null
            // so I can initialize it again
            //This is important especially you do cross browser testing(testing with
            // multiple browser like chrome, firefox, ie etc.)
        }
    }

}