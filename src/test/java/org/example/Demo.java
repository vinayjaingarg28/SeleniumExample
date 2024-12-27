package org.example;

import com.google.protobuf.DescriptorProtos;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.Key;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class Demo extends MyDriver{

    @BeforeMethod
    @Parameters({"browser","url"})
    public void initiate(String browser, String url){
        getChromeDriver(browser, url);
    }

    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();

        String url = "https://www.tutorialspoint.com/selenium/practice/selenium_automation_practice.php";

        driver.get(url);
        driver.manage().window().maximize();
//        driver.wait(3000);
        Thread.sleep(3000);
        String title = driver.findElement(By.cssSelector(".mb-3.fw-normal")).getText();
        Assert.assertEquals("Student Registration Form", title);
        driver.findElement(By.id("name")).sendKeys("Vinay Jain");
        driver.findElement(By.id("email")).sendKeys("VinayJain@google.com");
        driver.findElement(By.id("gender")).click();
        driver.findElement(By.id("mobile")).sendKeys("12345");
        driver.findElement(By.id("dob")).sendKeys("24-12-2025");
        driver.findElement(By.id("subjects")).sendKeys("English, CS");
        driver.findElement(By.xpath("//*[@id='practiceForm']/div[7]/div/div/div[2]/input"))
                        .click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,350)", "");

        Thread.sleep(1000);

        driver.findElement(By.id("picture")).sendKeys("C:\\Users\\DELL\\Downloads\\Vinay_medbill.jpeg");
        driver.findElement(By.tagName("textarea")).sendKeys("12345");
        Select s = new Select(driver.findElement(By.id("state")));
        s.selectByValue("Haryana");
        Select city = new Select(driver.findElement(By.id("city")));
        city.selectByValue("Agra");

        Thread.sleep(3000);
        driver.quit();
        //*[@id="practiceForm"]/div[7]/div/div/div[2]/input
    }

    @Test
    public void useAction() throws InterruptedException {
        String url = "https://www.tutorialspoint.com/selenium/practice/selenium_automation_practice.php";
        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(2000);

        WebElement first = driver.findElement(By.id("name"));

        Actions act = new Actions(driver);
        Action name = act.keyDown(Keys.SHIFT)
                         .sendKeys(first, "vinay jain")
                         .keyUp(Keys.SHIFT)
                         .build();
        name.perform();

        String value = first.getAttribute("value");
        Assert.assertEquals(value, "VINAY JAIN");

        Thread.sleep(2000);
        driver.close();
    }

    @Test
    public void useAlert() throws InterruptedException {
        String url = "https://www.tutorialspoint.com/selenium/practice/alerts.php";
        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[text()= 'Alert']")).click();
        Thread.sleep(2000);
        driver.switchTo().alert().accept();

        WebElement w = driver.findElement(By.xpath("(//button[text()= 'Click Me'])[1]"));
        w.click();
        Thread.sleep(5500);
        String text = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        Assert.assertEquals(text, "Hello just appeared");

        driver.findElement(By.xpath("(//button[text()= 'Click Me'])[2]")).click();
        Thread.sleep(2000);
        driver.switchTo().alert().accept();
        String conf = driver.findElement(By.id("desk")).getText();
        Assert.assertEquals(conf, "You pressed OK!");

        WebElement w1 = driver.findElement(By.xpath("(//button[text()= 'Click Me'])[3]"));
        w1.click();
        Thread.sleep(2000);
        driver.switchTo().alert().sendKeys("Kuch bhi");
        driver.switchTo().alert().accept();

        driver.close();
    }

    @Test
    public void usePopUpWindow() throws InterruptedException {
        String url = "https://demo.guru99.com/popup.php";
        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(2000);

        driver.findElement(By.linkText("Click Here")).click();
        String mainWindow = driver.getWindowHandle();
        Set<String> s = driver.getWindowHandles();

        for(String str: s){
            if(!mainWindow.equalsIgnoreCase(str)) {
                driver.switchTo().window(str);
                driver.findElement(By.name("emailid")).sendKeys("example@gamil.com");
                driver.findElement(By.name("emailid")).submit();
                String value = driver.findElement(By.xpath("(//h2)[2]")).getText();

                Assert.assertEquals(value, "Access details to demo site.");
                driver.close();
            }
        }
        driver.switchTo().window(mainWindow);
        Thread.sleep(2000);
        driver.close();
    }

    @Test
    public void useTabs() throws InterruptedException {
        String url = "https://www.tutorialspoint.com/selenium/practice/browser-windows.php";
        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[text()= 'New Window Message']")).click();
        String mainWindow = driver.getWindowHandle();
        Set<String> s = driver.getWindowHandles();
        for (String str : s){
            if(!mainWindow.equalsIgnoreCase(str)){
                driver.switchTo().window(str);
                String text = driver.findElement(By.xpath("/html/body/main/div/div")).getText();
                Assert.assertTrue(text.contains("Knowledge increases by sharing but not by saving. Please share this website with your friends and in your or"));
                driver.close();
            }
        }
        driver.switchTo().window(mainWindow);
        Thread.sleep(2000);
        driver.close();
    }

    @Test
    public void useFrame() throws InterruptedException {
        String url = "https://www.tutorialspoint.com/selenium/practice/frames.php";
        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(2000);

//        driver.switchTo().frame(1);
        boolean b = driver.findElement(By.xpath("(//a[@class='external-link'])")).isDisplayed();
        Assert.assertTrue(b);
        Thread.sleep(2000);
        WebElement l=driver.findElement(By.xpath("(//iframe)[2]"));
        // Javascript executor
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", l);
        driver.switchTo().frame(2);
        boolean b1 = driver.findElement(By.xpath("(//a[@class='external-link'])")).isDisplayed();
        Assert.assertTrue(b1);
        Thread.sleep(2000);
        driver.close();
    }

    @Test
    public void useModal() throws InterruptedException {
        String url = "https://www.tutorialspoint.com/selenium/practice/modal-dialogs.php";
        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//button[text()='Large Modal']")).click();
        Thread.sleep(2000);
        Assert.assertTrue(driver.findElement(By.cssSelector("#exampleModalLg .modal-body")).isDisplayed());
//        driver.findElement(By.xpath("//*[@id=\"exampleModalSm\"]/div/div/div[2]"));
        String value = driver.findElement(By.cssSelector("#exampleModalLg .modal-body")).getText();
        Assert.assertEquals(value, "Show a second modal and hide this one with the button below.");
        driver.findElement(By.cssSelector("#exampleModalLg button.btn")).click();
        Thread.sleep(2000);
        driver.close();
    }

    @Test
    public void findBrokenLinks() throws InterruptedException, IOException {
        String home = "https://www.tutorialspoint.com/";
        String url = "https://www.tutorialspoint.com/selenium/practice/broken-links.php";
        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(2000);

        HttpURLConnection con = null;

        List<WebElement> wl = driver.findElements(By.tagName("a"));
        for(WebElement w: wl){
            String add = w.getDomAttribute("href");
            if(add.isEmpty()){
                System.out.println(w.getText() +" Url not configured");
                continue;
            }
            else if(!add.startsWith(home)){
                System.out.println(w.getText()+ " is not of our domain");
                continue;
            }
            con = (HttpURLConnection) (new URL(add).openConnection());
            con.connect();
            int code = con.getResponseCode();
            if(code > 400){
                System.out.println("Not Working");
            }
            else{
                System.out.println("Working");
            }
        }
        Thread.sleep(2000);
        driver.close();
    }

    @Test
    public void handleCalender() throws InterruptedException {
        String url = "https://www.tutorialspoint.com/selenium/practice/date-picker.php";
        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(2000);

        String date = "2022-06-02 12:00";
        String year = "2022";
        int day =2;

        driver.findElement(By.cssSelector("input#datetimepicker1")).click();
        Thread.sleep(2000);
        String selYear = driver.findElement(By.xpath("(//input[@class ='numInput cur-year'])[1]"))
                .getAttribute("value");
//        selYear="2024";
        while(!year.equalsIgnoreCase(selYear)){
            if(Integer.parseInt(year) > Integer.parseInt(selYear)){
                driver.findElements(By.cssSelector(".numInputWrapper span.arrowUp")).get(0).click();
                selYear = driver.findElement(By.xpath("(//input[@class ='numInput cur-year'])[1]"))
                        .getDomAttribute("value");
            }
            else{
                WebElement element = driver.findElements(By.cssSelector(".numInputWrapper span.arrowDown")).get(0);
                Actions actions = new Actions(driver);
                actions.moveToElement(element).click().perform();
//                driver.findElements(By.cssSelector(".numInputWrapper span.arrowDown")).get(1).click();
                selYear = driver.findElement(By.xpath("(//input[@class ='numInput cur-year'])[1]"))
                        .getAttribute("value");
            }
        }
        driver.findElements(By.cssSelector("select.flatpickr-monthDropdown-months")).get(0).click();
        Select s = new Select(driver.findElements(By.cssSelector("select.flatpickr-monthDropdown-months")).get(0));
        s.selectByValue("5");

        String loc = "//div[@class = 'dayContainer']/span[text()='"+ day +"']";

        driver.findElement(By.xpath(loc)).click();
        driver.findElement(By.xpath("/html/body/main/div/div/div[2]/div")).click();

        String value = driver.findElement(By.cssSelector("input#datetimepicker1")).getAttribute("value");
        Assert.assertEquals(value, date);
        Thread.sleep(2000);
        driver.close();
    }

    @Test
    public void takeSS() throws InterruptedException, IOException {
        String url = "https://www.tutorialspoint.com/selenium/practice/date-picker.php";
        driver.get(url);
        driver.manage().window().maximize();
        Thread.sleep(2000);

        File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String dir = "C:\\Users\\DELL\\Documents";
        f.mkdirs();
        String file = dir + File.separator  + LocalDate.now().toString() + ".jpg";
        System.out.println(file);
        Files.copy(f.toPath(), new File(file).toPath(), StandardCopyOption.REPLACE_EXISTING);
        driver.close();
    }
}
