package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Parameters;

public class MyDriver {

    public WebDriver driver;

    @Parameters({"browser","url"})
    public void getChromeDriver(String browser , String url ){
        if (browser == null || browser.isEmpty()) {
            browser = "chrome";
        }
        if (url == null || url.isEmpty()) {
            url = "https://www.google.com";
        }
        if(browser.equalsIgnoreCase("chrome")){
            driver = new ChromeDriver();
        }
    }
}
