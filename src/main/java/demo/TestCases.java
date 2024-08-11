package demo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;

import java.util.List;
import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;


public class TestCases {
    ChromeDriver driver;
    public TestCases()
    {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

    }

    public void endTest()
    {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    public  void testCase01() {
        System.out.println("Start Test Case: testCase01");

        driver.get("https://leetcode.com/");
        if(driver.getCurrentUrl().contains("leetcode")) {
            System.out.println("URL Verification Successful!");
        }
        else {
            System.out.println("URL Verification Failed!!!");
        }

        System.out.println("End Test Case: testCase01");
    }

    public  void testCase02() throws InterruptedException {
        System.out.println("Start Test Case: testCase02");

        WebElement questionsLink=driver.findElement(By.xpath("//a/p[contains(text(), 'Questions')]"));
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", questionsLink);
        questionsLink.click();
        Thread.sleep(10000);

        if(driver.getCurrentUrl().contains("problemset")) {
            System.out.println("URL Verification Successful!");
        }
        else {
            System.out.println("URL Verification Failed!!!");
        }

        //a[normalize-space()='1. Two Sum']
        List<WebElement> first5Questions=driver.findElements(By.xpath("//div[@role='row']/div[@role='cell'][1]/a/parent::div/parent::div/following-sibling::div//div[2]//a")).subList(0, 5);  
        String[] titlesArray={"Two Sum", "Add Two Numbers", "Longest Substring Without Repeating Characters", "Median of Two Sorted Arrays", "Longest Palindromic Substring"};
        for(int i=0;i<5;i++) {
            String title=first5Questions.get(i).getText();
            System.out.println(title);
            if(title.contains(titlesArray[i])) {
                System.out.printf("Question %s title matching!\n", i+1);
            }
            else {
                System.out.printf("Question %s title is not matching!\n", i+1);
            }
        }

        System.out.println("End Test Case: testCase02");
    }

    public  void testCase03() throws InterruptedException {
        System.out.println("Start Test Case: testCase03");
        
        WebElement question1=driver.findElement(By.xpath("//a[normalize-space()='1. Two Sum']"));
        question1.click();
        Thread.sleep(10000);
        if(driver.getCurrentUrl().contains("two-sum")) {
            System.out.println("URL Verification Successful!");
        }
        else {
            System.out.println("URL Verification Failed!!!");
        }

        System.out.println("End Test Case: testCase03");
    }

    public  void testCase04() throws InterruptedException {
        System.out.println("Start Test Case: testCase04");
        
        WebElement submissionsTab=driver.findElement(By.id("submissions_tab"));
        submissionsTab.click();
        Thread.sleep(2000);
        String text=driver.findElement(By.xpath("//a[contains(text(), 'Register or Sign In')]")).getText();
        if(text.equals("Register or Sign In")) {
            System.out.println("Correct message is displayed!");
        }
        else {
            System.out.println("Incorrect message is displayed!!!");
        }

        System.out.println("End Test Case: testCase04");
    }

}
