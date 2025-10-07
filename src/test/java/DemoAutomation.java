import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class DemoAutomation {

    RemoteWebDriver driver;

    @BeforeTest
    public void setup() throws MalformedURLException {
        String username = "shivanks";
        String accessKey = "LT_V6UEGtFRGiIPH4QcUdNKQv4RIetJjVfXKKIEkrP3Sk15opJ";
        String gridURL = "@hub.lambdatest.com/wd/hub";

        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("140.0");

        HashMap<String, Object> ltOptions = new HashMap<>();
        ltOptions.put("username", username);
        ltOptions.put("accessKey", accessKey);
        ltOptions.put("video", true);
        ltOptions.put("build", "TesingWithAzure");
        ltOptions.put("project", "DemoOnAutomation");
        ltOptions.put("selenium_version", "4.0.0");
        ltOptions.put("w3c", true);

        browserOptions.setCapability("LT:Options", ltOptions);

        driver = new RemoteWebDriver(new URL("https://" + username + ":" + accessKey + gridURL), browserOptions);
    }

    @Test
    public void runDemoAutomation() {
        driver.get("https://ecommerce-playground.lambdatest.io/");

        WebElement iphoneLink = driver.findElement(By.xpath("//a[contains(text(), 'iPhone')]"));
        iphoneLink.click();
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
