import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ReusableMethods {
    static ExtentHtmlReporter htmlReporter;
    static ExtentReports extent = null;
    static ExtentTest logger = null;

    public static WebDriver getRequiredDriver(String name) {
        WebDriver driver = null;
        //System.setProperty("webdriver.gecko.driver", "/Users/nidhishetty/Documents/workspacepython/selenium/venv/bin/SeleniumApplication/src/Utilities/geckodriver3");
        if(name.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.gecko.driver","./src/drivers/geckodriver3");
            driver = new FirefoxDriver();
        }
        else if(name.equalsIgnoreCase("chrome")){
            System.out.println("chrome entered");
            System.setProperty("webdriver.chrome.driver","./src/drivers/chromedriver2");
            driver = new ChromeDriver();
            //driver.manage().window().maximize();
        }
        else if(name.equalsIgnoreCase("ie")){
            System.out.println("chrome entered");
            System.setProperty("webdriver.ie.driver","./src/drivers/IEDriverServer");
            driver = new InternetExplorerDriver();
        }
        return driver;
    }

    public static WebDriver initialSetUp( String driverType) throws Exception {

        // Create a new instance of the object map
        ConfigReader configReader = new ConfigReader();
        //get the required driver
        WebDriver driver = getRequiredDriver(driverType);
        //get base url path
        driver.get(configReader.getPath("baseUrl"));
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        return driver;
    }

    public static void initialLogin(WebDriver driver, String usrName, String password) throws Exception {

        WebElement loginPage = driver.findElement(By.xpath("//a[@class='btn btn-tertiary-alt global-ceiling-bar-btn']"));
        clickObj(loginPage, "Login page");

        //logger.log(Status.INFO, MarkupHelper.createLabel(" login page is launched..", ExtentColor.BLUE));

        /* username*/
//        WebElement userName = driver.findElement(configReader.getLocator("userName"));
        WebElement userName = driver.findElement(By.xpath("//input[@id='email']"));
        enterText(userName, usrName, "Username");


        /* password*/
//        WebElement pwd = driver.findElement(configReader.getLocator("pwd"));
        WebElement pwd = driver.findElement(By.xpath("//input[@id='password']"));
        enterText(pwd, password,"Password");


        /* login*/
//        WebElement clickLogin = driver.findElement(configReader.getLocator("clickLogin"));
        WebElement clickLogin = driver.findElement(By.xpath("//button[@id='submitButton']"));
        clickObj(clickLogin, "Submit Button");
        Thread.sleep(1000);

//        getScreenShots(driver, "ErrorPage");

        //logger.log(Status.PASS, MarkupHelper.createLabel("Error Message: Please enter the password..", ExtentColor.GREEN));

    }

    /*
     * Name of the Method: enterText
     * Brief Description: Enters text to text field
     * Arguments: obj --> Web object, textVal --> Text value to be entered in text box, objName --> Nam eof the object
     * Created By: Automation team
     * Creation Date: Oct 09 2018
     * Last Modified: Oct 09 2018
     */
    public static void enterText(WebElement obj, String textVal, String objName) {
        if (obj.isDisplayed()) {
            obj.sendKeys(textVal);
        } else {
            logTestStatus(Status.FAIL, objName);
        }

    }

    /*
     * Name of the Method: clickObj
     * Brief Description: click on the object
     * Arguments: obj --> Web object, objName--> Name of the object
     * Created By: Automation team
     * Creation Date: Oct 09 2018
     * Last Modified: Oct 09 2018
     */

    public static void clickObj(WebElement obj, String objName) {
        if (obj.isDisplayed()) {
            obj.click();
        } else {
            logTestStatus(Status.FAIL, objName);
        }
    }

    /*
     * Name of the Method: clearObj
     * Brief Description: clear the contents of the object
     * Arguments: obj --> Web object, objName--> Name of the object
     * Created By: Automation team
     * Creation Date: Oct 13 2018
     * Last Modified: Oct  13 2018
     */


    public static void executionReport(String reportName) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String timeNow = dtf.format(now);
        timeNow = timeNow.replace(":", "_");
        timeNow = timeNow.replace(" ", "_");
        timeNow = timeNow.replace("/", "_");

        System.out.println(reportName + "_" + timeNow + ".html");

        htmlReporter = new ExtentHtmlReporter("/Users/nidhishetty/Documents/TestSuite/Reports/" + reportName + "_" + timeNow + ".html");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

    }


    public static String getScreenShots(WebDriver Driver,String screenshotName) throws IOException {
        String DateName = new SimpleDateFormat("yyyymmddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) Driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        //String destination = System.getProperty("user.dir") + "/TestReports/screenshots/" +screenshotName +DateName + ".png";
        String destination = "/Users/nidhishetty/Documents/TestSuite/Reports/screenshots/" +screenshotName +DateName + ".png";
        File finalDest = new File(destination);
        FileUtils.copyFile(source,finalDest );
        return destination;
    }

    public static void logTestStatus(Status status, String methodName) {
        if(status == Status.PASS) {
            logger.log(Status.PASS, MarkupHelper.createLabel(methodName, ExtentColor.GREEN));
        } else if(status == Status.FAIL) {
            logger.log(Status.FAIL, MarkupHelper.createLabel(methodName, ExtentColor.RED));
        } else {
            logger.log(Status.INFO, MarkupHelper.createLabel(methodName, ExtentColor.LIME));
        }
    }

}
