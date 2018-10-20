import org.openqa.selenium.By;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    Properties prop;

    public ConfigReader () {

        prop = new Properties();

        try {
            FileInputStream fis = new FileInputStream("/Users/nidhishetty/Documents/workspacepython/selenium/venv/bin/AutomationXero/src/config.properties");
            prop.load(fis);
            fis.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public By getLocator(String strElement) throws Exception {

        // retrieve the specified object from the object list
        String locator = prop.getProperty(strElement);

        // extract the locator type and value from the object
        String[] splits = locator.split(":");
        String locatorType = splits[0];
        String locatorValue = splits[1];

        // for testing and debugging purposes
        System.out.println("Retrieving object of type '" + locatorType + "' and value '" + locatorValue + "' from the object map");

        // return a instance of the By class based on the type of the locator
        // this By can be used by the browser object in the actual test
        if(locatorType.toLowerCase().equals("id"))
            return By.id(locatorValue);
        else if(locatorType.toLowerCase().equals("name"))
            return By.name(locatorValue);
        else if((locatorType.toLowerCase().equals("classname")) || (locatorType.toLowerCase().equals("class")))
            return By.className(locatorValue);
        else if((locatorType.toLowerCase().equals("tagname")) || (locatorType.toLowerCase().equals("tag")))
            return By.className(locatorValue);
        else if((locatorType.toLowerCase().equals("linktext")) || (locatorType.toLowerCase().equals("link")))
            return By.linkText(locatorValue);
        else if(locatorType.toLowerCase().equals("partiallinktext"))
            return By.partialLinkText(locatorValue);
        else if((locatorType.toLowerCase().equals("cssselector")) || (locatorType.toLowerCase().equals("css")))
            return By.cssSelector(locatorValue);
        else if(locatorType.toLowerCase().equals("xpath"))
            return By.xpath(locatorValue);
        else
            throw new Exception("Unknown locator type '" + locatorType + "'");
    }

    public String getPath(String string){
        return prop.getProperty(string);
    }
}

