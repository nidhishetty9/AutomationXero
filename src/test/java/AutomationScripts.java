import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

public class AutomationScripts extends ReusableMethods {
    WebDriver driver;
    ConfigReader configReader;

    @BeforeClass
    public void setUp() throws Exception {
        ReusableMethods.executionReport("XeroAutomationScripts");
    }

    @AfterClass
    public void cleanUp() throws Exception {
        extent.flush();
    }

    @BeforeMethod
    public void testSetup(Method method) throws Exception {
        logger = extent.createTest(method.getName());
        configReader = new ConfigReader();
        driver = initialSetUp("firefox");
    }



    @AfterMethod
    public void testCleanup(Method method) throws Exception {
        driver.quit();
        logger.log(Status.PASS, MarkupHelper.createLabel(method.getName(), ExtentColor.GREEN));
    }

    @Test(description = "Navigate to XERO", enabled = false)
    public void navigate() throws Exception {
        initialLogin(driver, configReader.getPath("user"), configReader.getPath("pwd"));
    }

    @Test(description = "Test wrong password", enabled = true)
    public void enterWrongPass() throws Exception {
        initialLogin(driver, configReader.getPath("user"), configReader.getPath("wrongPwd"));
        //Assert the text
    }

    @Test(description = "Test forgot password", enabled = false)
    public void forgotPass() throws Exception {
        WebElement loginPage = driver.findElement(configReader.getLocator("xeroLoginPage"));
        clickObj(loginPage, "Login page");
        /* password*/
        WebElement forgotPwd = driver.findElement(configReader.getLocator("forgotPwd"));
        clickObj(forgotPwd, "Forgot Your Password");

        WebElement userName = driver.findElement(configReader.getLocator("userName"));
        enterText(userName, configReader.getPath("user"), "Username");

        userName = driver.findElement(By.xpath(" //span[@class='text']"));
        clickObj(userName, "Send Link");
    }

    @Test(description = "Test free trial", enabled = false)
    public void freeTrial() throws Exception {
        WebElement freeTrail = driver.findElement(configReader.getLocator("freeTrial"));
        clickObj(freeTrail, "Free Trial");

        WebElement firstName = driver.findElement(By.xpath(" //input[@name='FirstName']"));
        enterText(firstName, configReader.getPath("firstName"), "First name");

        WebElement lastName = driver.findElement(By.xpath("//input[@name='LastName']"));
        enterText(lastName, configReader.getPath("lastName"), "Last Name");

        WebElement emailAdress = driver.findElement(By.xpath("//input[@name='EmailAddress']"));
        enterText(emailAdress, configReader.getPath("user"), "Email Address");

        WebElement phoneNumber = driver.findElement(By.xpath("//input[@name='PhoneNumber']"));
        enterText(phoneNumber, configReader.getPath("phoneNumber"), "Phone Number");

        WebElement country = driver.findElement(By.xpath("//option[@value='US']"));
        enterText(country, "USA", "Country");

        WebElement termsCheckBox = driver.findElement(By.xpath("//input[@value='true']"));
        clickObj(termsCheckBox,"Check For Terms" );

        WebElement getStarted = driver.findElement(By.xpath("//span[@class='g-recaptcha-submit']"));
        clickObj(getStarted, "Get Started");

//        getScreenShots(driver, "Username dropdown should be displayed");
//        logger.log(Status.PASS, MarkupHelper.createLabel("Username dropdown displayed", ExtentColor.GREEN));
    }

    @Test(description = "Test free trial config", enabled = false)
    public void  freeTrialConfig() throws Exception {

        WebElement freeTrail = driver.findElement(configReader.getLocator("freeTrial"));
        clickObj(freeTrail, "Free Trial");

        WebElement getStarted = driver.findElement(By.xpath("//span[@class='g-recaptcha-submit']"));
        clickObj(getStarted, "Get Started");
        Thread.sleep(3000);

     /* System.out.println("Error Message:First name,Last name,Email address, Phone number can't be empty\" should be dispalyed")*/

        WebElement emailAdress = driver.findElement(By.xpath("//input[@name='EmailAddress']"));
        enterText(emailAdress, "nidhipunja8@gail.com", "Email Address");
        Thread.sleep(2000);

        /*System.out.println("Error Message : "Email address is invalid\" should be displayed");*/

        /*the error message willnot display imstead asks us to enter the phonenumber*/

        getStarted = driver.findElement(By.xpath("//span[@class='g-recaptcha-submit']"));
        clickObj(getStarted, "Get Started");
        Thread.sleep(2000);

        /*System.out.println("Error Message :"term and Policy checkbox should be highlight.");*/

        getScreenShots(driver, "Username dropdown should be displayed");
        logger.log(Status.PASS, MarkupHelper.createLabel("Username dropdown displayed", ExtentColor.GREEN));
    }

    @Test(description = "Test free trial terms link", enabled = false)
    public void freeTrialTermsLink() throws Exception {

        WebElement freeTrail = driver.findElement(configReader.getLocator("freeTrial"));
        clickObj(freeTrail, "Free Trial");

        WebElement termsOfUse = driver.findElement(By.xpath("//a[contains(text(),'terms of use')]"));
        clickObj(termsOfUse, "linkfor terms");
        Thread.sleep(2000);

        String oldWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        String[] allWindowsArray = allWindows.toArray(new String[allWindows.size()]);
        driver.switchTo().window(allWindowsArray[0]);
        Thread.sleep(2000);

        WebElement privacyPolicy = driver.findElement(By.xpath("//a[contains(text(),'privacy notice')]"));
        clickObj(privacyPolicy, "Privacy Policy");

    }

    @Test(description = "Test full offer details", enabled = false)
    public void fullOfferDetails() throws Exception {

        WebElement freeTrail = driver.findElement(configReader.getLocator("freeTrial"));
        clickObj(freeTrail, "Free Trial");


        WebElement offerDetails = driver.findElement(By.xpath("//a[contains(text(),'offer details')]"));
        clickObj(offerDetails, "Offer Details");
        Thread.sleep(4000);
    }

    @Test(description = "Test all tabs", enabled = false)
    public void testAllTabs() throws Exception {

        initialLogin(driver, configReader.getPath("user"), configReader.getPath("pwd"));

        WebElement dashBoardd = driver.findElement(By.xpath(" //a[contains(text(),'Dashboard')]"));
        clickObj(dashBoardd, "DashBoard Tab");
        Thread.sleep( 2000);

//        getScreenShots(, )etScreenShots(driver, "Username dropdown should be displayed");
//        logger.log(Status.PASS, MarkupHelper.createLabel("Username dropdown displayed", ExtentColor.GREEN));

        WebElement accounts = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[2]/div[1]/ul/li[2]/a"));
        clickObj(accounts,"Accounts Tab" );
        Thread.sleep( 2000);
//        getScreenShots(, )etScreenShots(driver, "Username dropdown should be displayed");
//        logger.log(Status.PASS, MarkupHelper.createLabel("Username dropdown displayed", ExtentColor.GREEN));

        WebElement reports = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[2]/div[1]/ul/li[5]/a"));
        clickObj(reports,"Reports Tab" );
        Thread.sleep( 2000);

        //        getScreenShots(, )etScreenShots(driver, "Username dropdown should be displayed");
//        logger.log(Status.PASS, MarkupHelper.createLabel("Username dropdown displayed", ExtentColor.GREEN));

        WebElement contacts = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[2]/div[1]/ul/li[6]/a"));
        clickObj(contacts,"contacts Tab" );
        Thread.sleep( 2000);
        //        getScreenShots(, )etScreenShots(driver, "Username dropdown should be displayed");
//        logger.log(Status.PASS, MarkupHelper.createLabel("Username dropdown displayed", ExtentColor.GREEN));


        WebElement settings = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[2]/div[1]/ul/li[7]/a"));
        clickObj(settings,"contacts Tab" );
        Thread.sleep( 2000);


        //        getScreenShots(, )etScreenShots(driver, "Username dropdown should be displayed");
//        logger.log(Status.PASS, MarkupHelper.createLabel("Username dropdown displayed", ExtentColor.GREEN));

        WebElement createNew = driver.findElement(By.xpath("//div/ul/li/a[@id ='quicklaunchTab']"));
        clickObj(createNew," Create new "+" " );
        Thread.sleep(2000);

        //        getScreenShots(, )etScreenShots(driver, "Username dropdown should be displayed");
//        logger.log(Status.PASS, MarkupHelper.createLabel("Username dropdown displayed", ExtentColor.GREEN));


        WebElement files = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[2]/div[2]/ul/li[3]/a"));
        clickObj(files,"Files" );
        Thread.sleep(2000);

        //        getScreenShots(, )etScreenShots(driver, "Username dropdown should be displayed");
//        logger.log(Status.PASS, MarkupHelper.createLabel("Username dropdown displayed", ExtentColor.GREEN));

        WebElement notifications = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[2]/div[2]/ul/li[3]/a"));
        clickObj(notifications,"Notifications" );
        Thread.sleep(2000);

        //        getScreenShots(, )etScreenShots(driver, "Username dropdown should be displayed");
//        logger.log(Status.PASS, MarkupHelper.createLabel("Username dropdown displayed", ExtentColor.GREEN));

        WebElement search = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[2]/div[2]/ul/li[4]/a"));
        clickObj(search,"Search" );
        Thread.sleep(2000);


        WebElement help = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[2]/div[2]/ul/li[5]/a"));
        clickObj(help, "Help");


//        getScreenShots((driver, "what do you need help with area field should be shown with help centre,get help for this page"
//        logger.log(Status.PASS, MarkupHelper.createLabel("what do you need help with area field should be shown with help centre,get help for this page", ExtentColor.GREEN));
    }

    @Test(description = "Test logout", enabled = false)
    public void logout() throws Exception {

        initialLogin(driver, configReader.getPath("user"), configReader.getPath("pwd"));

        logger.log(Status.INFO, MarkupHelper.createLabel("Logging out", ExtentColor.LIME));
        WebElement userMenu = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[1]/div[2]/a"));
        clickObj(userMenu, "Usermenu DropDown");

        WebElement logout = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[1]/div[2]/div/ul/li[3]/a"));
        clickObj(logout,"Logout" );
        Thread.sleep(1000);
        ReusableMethods.getScreenShots(driver, "Logout");

    }

    @Test(description = "Test account create", enabled = false)
    public void accountCreate() throws Exception {

        initialLogin(driver, configReader.getPath("user"), configReader.getPath("pwd"));


        WebElement accounts = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[2]/div[1]/ul/li[2]/a"));
        clickObj(accounts,"Accounts Tab" );
        Thread.sleep( 2000);

        WebElement bankAcc = driver.findElement(By.xpath("//a[contains(text(),'Bank Accounts')]"));
        clickObj(bankAcc, "Bank Account");

        WebElement addNewAcc = driver.findElement(By.xpath("/html/body/div[2]/form/div/div[1]/div[2]/a/span"));
        clickObj(addNewAcc, "Bank Account");

        WebElement selectAccType = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/section/div[2]/ul/li[2]"));
        clickObj(selectAccType, "Capital One");


        WebElement skipButton = driver.findElement(By.xpath("/html/body/main/div/section/footer/a"));
        clickObj(skipButton, "Skip");

        WebElement accountName = driver.findElement(By.xpath("//input[@id = 'accountname-1025-inputEl']"));
        clickObj(accountName, "Account Name");
        enterText(accountName,"CapitalOne Acc" , "Capital one Account");

        WebElement accountType = driver.findElement(By.xpath("//*[@id=\"accounttype-1027-trigger-picker\"]"));
        clickObj(accountType, "Account Type");
        Thread.sleep(2000);
//        selectAccType.findElement(By.xpath(""))

        WebElement searchTypeAcc = driver.findElement(By.xpath("//input[@id='xui-searchfield-1018-inputEl']"));
        clickObj(searchTypeAcc, "Account");


     WebElement userMenu = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[1]/div[2]/a"));
        clickObj(userMenu, "Usermenu DropDown");

        WebElement logout = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[1]/div[2]/div/ul/li[3]/a"));
        clickObj(logout,"Logout" );

        Thread.sleep(2000);


        getScreenShots(driver, "ErrorPage");

    logger.log(Status.PASS, MarkupHelper.createLabel("Error Message: Please enter the password..", ExtentColor.GREEN));
    }

    @Test(description = "Test profile image", enabled = false)
    public  void profileImage() throws Exception {

        initialLogin(driver, configReader.getPath("user"), configReader.getPath("pwd"));

        WebElement userMenu = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[1]/div[2]/a"));
        clickObj(userMenu, "Usermenu DropDown");


        WebElement profile = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[1]/div[2]/div/ul/li[1]/a"));
        clickObj(profile, "Profile");

        WebElement uploads = driver.findElement(By.xpath("//*[@id=\"button-1041-btnInnerEl\"]"));
        clickObj(uploads, "Upload Image");

        WebElement browse = driver.findElement(By.xpath("//*[@id=\"filefield-1174-button-fileInputEl\"]"));
        clickObj(browse, "Browse");
        browse.sendKeys("/Users/nidhishetty/Downloads/upload4.JPG");

       WebElement upload = driver.findElement(By.id("button-1178-btnWrap"));
       clickObj(upload, "Upload");

//        getScreenShots(driver, "ErrorPage");

        //logger.log(Status.PASS, MarkupHelper.createLabel("Error Message: Please enter the password..", ExtentColor.GREEN));

    }

    @Test(description = "Test organization", enabled = false)
    public void organisation() throws Exception {

        initialLogin(driver, configReader.getPath("user"), configReader.getPath("pwd"));

        WebElement navigateToOrganisation = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[1]/div[1]/div/h2/a"));
        clickObj(navigateToOrganisation, "Organisation");

        WebElement myZeroClick = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[1]/div[1]/div/div/div/a"));
        clickObj(myZeroClick, "My Xero");

        WebElement addOrganisation = driver.findElement(By.xpath("//div/a[@id = 'ext-gen1042']"));
        clickObj(addOrganisation, "Add organisations");

        WebElement nameOrganisation = driver.findElement(By.xpath("//input[@id = 'text-1022-inputEl']"));
        clickObj(nameOrganisation, "Name of the Organisation");
        enterText(nameOrganisation, "Self", "Name of the organisation");


        WebElement payTax = driver.findElement(By.xpath("//*[@id=\"countryCmb-inputEl\"]"));
        clickObj(payTax, "payTax");

        WebElement buisness = driver.findElement(By.id("industrysearchcombofield-1025-inputEl"));
        clickObj(buisness, "What does the organisation do");
        enterText(buisness, "Accounting", "What does the organisation do");

        WebElement startTrail = driver.findElement(By.xpath("//*[@id=\"simplebutton-1035\"]"));
        clickObj(startTrail, "Start Trail");
        Thread.sleep(3000);

//        getScreenShots(driver, "ErrorPage");

        //logger.log(Status.PASS, MarkupHelper.createLabel("Error Message: Please enter the password..", ExtentColor.GREEN));

    }

    @Test(description = "Test organisation Purchase Starter", enabled = false)
    public void organisationPurchaseStarter() throws Exception {

        initialLogin(driver, configReader.getPath("user"), configReader.getPath("pwd"));

        WebElement navigateToOrganisation = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[1]/div[1]/div/h2/a"));
        clickObj(navigateToOrganisation, "Organisation");

        WebElement myZeroClick = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[1]/div[1]/div/div/div/a"));
        clickObj(myZeroClick, "My Xero");

        WebElement addOrganisation = driver.findElement(By.xpath("//*[@id=\"ext-gen1043\"]"));
        clickObj(addOrganisation, "Add organisations");

        WebElement nameOrganisation = driver.findElement(By.xpath("//input[@id = 'text-1022-inputEl']"));
        clickObj(nameOrganisation, "Name of the Organisation");
        Thread.sleep(2000);
        enterText(nameOrganisation, "Self3", "Name of the organisation");


        WebElement payTax = driver.findElement(By.xpath("//*[@id=\"countryCmb-inputEl\"]"));
        clickObj(payTax, "payTax");

        WebElement buisness = driver.findElement(By.xpath("//*[@id=\"industrysearchcombofield-1025-inputEl\"]"));
        clickObj(buisness, "What does the organisation do");


        enterText(buisness, "Accounting", "What does the organisation do");
        buisness.sendKeys(Keys.TAB);
        Thread.sleep(2000);


        WebElement buyNowTab = driver.findElement(By.xpath("//*[@id=\"simplebutton-1036\"]"));
        clickObj(buyNowTab, "BUY NOW");


        WebElement staterPLan = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div[3]/div/div/div/section[1]/div[1]/label/div"));
        clickObj(staterPLan, "Starter");

        Thread.sleep(2000);

        WebElement continueBilling = driver.findElement(By.xpath("//button[@id='continueButton']"));
        clickObj(continueBilling, "Continue Billing");


        WebElement streetAddress = driver.findElement(By.id("contactAddress"));
        clickObj(streetAddress, "Street Address");
        enterText(streetAddress, "3450 granada ave", "Address");



        WebElement town = driver.findElement(By.id("contactCity"));
        clickObj(town, "Street Address");
        enterText(town, "Santa Clara", "Town");


        WebElement state = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div[2]/div[3]/div/div/div/form/div[5]/div[4]/div/button"));
        clickObj(state, "State");
        state.findElement(By.xpath("//*[@id=\"California\"]"));
        clickObj(state, "State");


        WebElement zipCode = driver.findElement(By.id("contactPostalCode"));
        clickObj(zipCode, "Zip Code");
        enterText(zipCode, "95051", "ZipCode");

        WebElement continueToReview = driver.findElement(By.xpath("//button[contains(text(),'Continue to Review & Pay')]"));
        clickObj(continueToReview, "Continue to Review and Pay");

        driver.switchTo().frame("__privateStripeFrame6");
        WebElement cardNumber = driver.findElement(By.xpath("/html/body/div/form/span[2]/label/input"));
        clickObj(cardNumber, "Card Number");
        enterText(cardNumber, "5528 2780 7573 7472", "Card Name");
        driver.switchTo().defaultContent();


        driver.switchTo().frame("__privateStripeFrame7");
        WebElement expiryDate = driver.findElement(By.xpath("/html/body/div/form/span[2]/label/input"));
        clickObj(expiryDate, "expiry Date");
        enterText(expiryDate, "0920", "expiry Date");
        driver.switchTo().defaultContent();


        driver.switchTo().frame("__privateStripeFrame8");
        WebElement securityCode = driver.findElement(By.xpath("/html/body/div/form/span[2]/label/input"));
        clickObj(securityCode, "security code");
        enterText(securityCode, "675", "security codee");

        driver.switchTo().defaultContent();


        WebElement nameOnCard = driver.findElement(By.id("stripe-cardholder-name"));
        clickObj(nameOnCard, "security code");
        enterText(nameOnCard, "danny", "security codee");

        Thread.sleep(3000);


        WebElement confirmPurchase = driver.findElement(By.id("continueButton"));
        clickObj(confirmPurchase, "Confirm Purchase");
        System.out.println("enter");

        Thread.sleep(5000);
    }

    @Test(description = "Test organisation Purchase Standard", enabled = false)
    public void organisationPurchaseStandard() throws Exception {

        initialLogin(driver, configReader.getPath("user"), configReader.getPath("pwd"));

        WebElement navigateToOrganisation = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[1]/div[1]/div/h2/a"));
        clickObj(navigateToOrganisation, "Organisation");

        WebElement myZeroClick = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[1]/div[1]/div/div/div/a"));
        clickObj(myZeroClick, "My Xero");

        WebElement addOrganisation = driver.findElement(By.xpath("//*[@id=\"ext-gen1043\"]"));
        clickObj(addOrganisation, "Add organisations");

        WebElement nameOrganisation = driver.findElement(By.xpath("//input[@id = 'text-1022-inputEl']"));
        clickObj(nameOrganisation, "Name of the Organisation");
        Thread.sleep(2000);
        enterText(nameOrganisation, "Self3", "Name of the organisation");


        WebElement payTax = driver.findElement(By.xpath("//*[@id=\"countryCmb-inputEl\"]"));
        clickObj(payTax, "payTax");

        WebElement buisness = driver.findElement(By.xpath("//*[@id=\"industrysearchcombofield-1025-inputEl\"]"));
        clickObj(buisness, "What does the organisation do");


        enterText(buisness, "Accounting", "What does the organisation do");
        buisness.sendKeys(Keys.TAB);
        Thread.sleep(2000);


        WebElement buyNowTab = driver.findElement(By.xpath("//*[@id=\"simplebutton-1036\"]"));
        clickObj(buyNowTab, "BUY NOW");


        WebElement standardPlan = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div[3]/div/div/div/section[2]/div[1]/label/div"));
        clickObj(standardPlan, "Standard Plan");

        Thread.sleep(2000);

        WebElement continueBilling = driver.findElement(By.xpath("//button[@id='continueButton']"));
        clickObj(continueBilling, "Continue Billing");


        WebElement streetAddress = driver.findElement(By.id("contactAddress"));
        clickObj(streetAddress, "Street Address");
        enterText(streetAddress, "3450 granada ave", "Address");



        WebElement town = driver.findElement(By.id("contactCity"));
        clickObj(town, "Street Address");
        enterText(town, "Santa Clara", "Town");


        WebElement state = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div[2]/div[3]/div/div/div/form/div[5]/div[4]/div/button"));
        clickObj(state, "State");
        state.findElement(By.xpath("//*[@id=\"California\"]"));
        clickObj(state, "State");


        WebElement zipCode = driver.findElement(By.id("contactPostalCode"));
        clickObj(zipCode, "Zip Code");
        enterText(zipCode, "95051", "ZipCode");

        WebElement continueToReview = driver.findElement(By.xpath("//button[contains(text(),'Continue to Review & Pay')]"));
        clickObj(continueToReview, "Continue to Review and Pay");

        driver.switchTo().frame("__privateStripeFrame6");
        WebElement cardNumber = driver.findElement(By.xpath("/html/body/div/form/span[2]/label/input"));
        clickObj(cardNumber, "Card Number");
        enterText(cardNumber, "5528 2780 7573 7472", "Card Name");
        driver.switchTo().defaultContent();


        driver.switchTo().frame("__privateStripeFrame7");
        WebElement expiryDate = driver.findElement(By.xpath("/html/body/div/form/span[2]/label/input"));
        clickObj(expiryDate, "expiry Date");
        enterText(expiryDate, "0920", "expiry Date");
        driver.switchTo().defaultContent();


        driver.switchTo().frame("__privateStripeFrame8");
        WebElement securityCode = driver.findElement(By.xpath("/html/body/div/form/span[2]/label/input"));
        clickObj(securityCode, "security code");
        enterText(securityCode, "675", "security codee");

        driver.switchTo().defaultContent();


        WebElement nameOnCard = driver.findElement(By.id("stripe-cardholder-name"));
        clickObj(nameOnCard, "security code");
        enterText(nameOnCard, "danny", "security codee");

        Thread.sleep(3000);


        WebElement confirmPurchase = driver.findElement(By.id("continueButton"));
        clickObj(confirmPurchase, "Confirm Purchase");
        System.out.println("enter");

        Thread.sleep(5000);

    }

    @Test(description = "Test organisation Purchase Premium", enabled = false)
    public void organisationPurchasePremium() throws Exception {

        initialLogin(driver, configReader.getPath("user"), configReader.getPath("pwd"));

        WebElement navigateToOrganisation = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[1]/div[1]/div/h2/a"));
        clickObj(navigateToOrganisation, "Organisation");

        WebElement myZeroClick = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[1]/div[1]/div/div/div/a"));
        clickObj(myZeroClick, "My Xero");

        WebElement addOrganisation = driver.findElement(By.xpath("//*[@id=\"ext-gen1043\"]"));
        clickObj(addOrganisation, "Add organisations");

        WebElement nameOrganisation = driver.findElement(By.xpath("//input[@id = 'text-1022-inputEl']"));
        clickObj(nameOrganisation, "Name of the Organisation");
        Thread.sleep(2000);
        enterText(nameOrganisation, "Self3", "Name of the organisation");


        WebElement payTax = driver.findElement(By.xpath("//*[@id=\"countryCmb-inputEl\"]"));
        clickObj(payTax, "payTax");

        WebElement buisness = driver.findElement(By.xpath("//*[@id=\"industrysearchcombofield-1025-inputEl\"]"));
        clickObj(buisness, "What does the organisation do");


        enterText(buisness, "Accounting", "What does the organisation do");
        buisness.sendKeys(Keys.TAB);
        Thread.sleep(2000);


        WebElement buyNowTab = driver.findElement(By.xpath("//*[@id=\"simplebutton-1036\"]"));
        clickObj(buyNowTab, "BUY NOW");


        WebElement premiumPlan = driver.findElement(By.xpath("//section[@id='Premium']//div[@class='xui-styledcheckboxradio--radio']"));
        clickObj(premiumPlan, "Standard Plan");

        Thread.sleep(2000);

        WebElement continueBilling = driver.findElement(By.xpath("//button[@id='continueButton']"));
        clickObj(continueBilling, "Continue Billing");


        WebElement streetAddress = driver.findElement(By.id("contactAddress"));
        clickObj(streetAddress, "Street Address");
        enterText(streetAddress, "3450 granada ave", "Address");



        WebElement town = driver.findElement(By.id("contactCity"));
        clickObj(town, "Street Address");
        enterText(town, "Santa Clara", "Town");


        WebElement state = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div[2]/div[3]/div/div/div/form/div[5]/div[4]/div/button"));
        clickObj(state, "State");
        state.findElement(By.xpath("//*[@id=\"California\"]"));
        clickObj(state, "State");


        WebElement zipCode = driver.findElement(By.id("contactPostalCode"));
        clickObj(zipCode, "Zip Code");
        enterText(zipCode, "95051", "ZipCode");

        WebElement continueToReview = driver.findElement(By.xpath("//button[contains(text(),'Continue to Review & Pay')]"));
        clickObj(continueToReview, "Continue to Review and Pay");

        driver.switchTo().frame("__privateStripeFrame6");
        WebElement cardNumber = driver.findElement(By.xpath("/html/body/div/form/span[2]/label/input"));
        clickObj(cardNumber, "Card Number");
        enterText(cardNumber, "5528 2780 7573 7472", "Card Name");
        driver.switchTo().defaultContent();


        driver.switchTo().frame("__privateStripeFrame7");
        WebElement expiryDate = driver.findElement(By.xpath("/html/body/div/form/span[2]/label/input"));
        clickObj(expiryDate, "expiry Date");
        enterText(expiryDate, "0920", "expiry Date");
        driver.switchTo().defaultContent();


        driver.switchTo().frame("__privateStripeFrame8");
        WebElement securityCode = driver.findElement(By.xpath("/html/body/div/form/span[2]/label/input"));
        clickObj(securityCode, "security code");
        enterText(securityCode, "675", "security codee");

        driver.switchTo().defaultContent();


        WebElement nameOnCard = driver.findElement(By.id("stripe-cardholder-name"));
        clickObj(nameOnCard, "security code");
        enterText(nameOnCard, "danny", "security codee");

        Thread.sleep(3000);


        WebElement confirmPurchase = driver.findElement(By.id("continueButton"));
        clickObj(confirmPurchase, "Confirm Purchase");
        System.out.println("enter");

        Thread.sleep(5000);
    }


    @Test(description = "Test organisation another", enabled = false)
    public void organisationAnother() throws  Exception {
        initialLogin(driver, configReader.getPath("user"), configReader.getPath("pwd"));

        WebElement navigateToOrganisation = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[1]/div[1]/div/h2/a"));
        clickObj(navigateToOrganisation, "Organisation");

        WebElement myZeroClick = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[1]/div[1]/div/div/div/a"));
        clickObj(myZeroClick, "My Xero");

        WebElement addOrganisation = driver.findElement(By.xpath("//*[@id=\"ext-gen1043\"]"));
        clickObj(addOrganisation, "Add organisations");

        WebElement nameOrganisation = driver.findElement(By.xpath("//input[@id = 'text-1022-inputEl']"));
        clickObj(nameOrganisation, "Name of the Organisation");
        Thread.sleep(2000);
        enterText(nameOrganisation, "Self3", "Name of the organisation");


        WebElement payTax = driver.findElement(By.xpath("//*[@id=\"countryCmb-inputEl\"]"));
        clickObj(payTax, "payTax");

        WebElement buisness = driver.findElement(By.xpath("//*[@id=\"industrysearchcombofield-1025-inputEl\"]"));
        clickObj(buisness, "What does the organisation do");
        enterText(buisness, "Accounting", "What does the organisation do");
        buisness.sendKeys(Keys.TAB);
        Thread.sleep(2000);

        WebElement quickBook = driver.findElement(By.id("conversionLink"));
        clickObj(quickBook, "Quick Book");

        WebElement checkboxQuickBook = driver.findElement(By.id("conversionCheckbox-inputEl"));
        clickObj(checkboxQuickBook, "CheckBox");

        /*screenshots for disbale button*/

    }


    @Test(description = "Test check for billing", enabled = false)
    public void checkForBilling() throws Exception {

        initialLogin(driver, "gopala.anumanchipalli@gmail.com", "password12");

        WebElement accounts = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[2]/div[1]/ul/li[2]/ul"));

        clickObj(accounts,"Accounts Tab" );
//        WebElement purchases = driver.findElement(By.xpath("//a[contains(text(),'Purchases')]"));
//        clickObj(purchases, "purchases");


//
//        WebElement accounts = driver.findElement(class);
//        clickObj(accounts,"Accounts Tab" );


//        WebElement accDropDown = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/div[2]/div[1]/ul/li[2]/a"));
//        Select menuList = new Select(accounts);
//        menuList.selectByVisibleText("Purchases");

//
        String tagName = accounts.getTagName();
        List<WebElement> menuList = accounts.findElements(By.tagName(tagName));
        for (WebElement item : menuList) {
            if("Purchases".equals(item.getText()))
                item.click();
        }



//        Select
//
//        dropdown.selectByVisibleText("Italy");
//

        Select fromDropDown= new Select(accounts);

        fromDropDown.selectByValue("Purchases");
        System.out.println("selecting purchases");

        Thread.sleep(2000);
        }
    }

























