import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class DemoAutomation {
    public static void main(String[] args) {
        String username = "shivanks";
        String accessKey = "LT_V6UEGtFRGiIPH4QcUdNKQv4RIetJjVfXKKIEkrP3Sk15opJ";
        RemoteWebDriver driver = null;
        String gridURL = "@hub.lambdatest.com/wd/hub";
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("140.0");
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", "shivanks");
        ltOptions.put("accessKey", "LT_V6UEGtFRGiIPH4QcUdNKQv4RIetJjVfXKKIEkrP3Sk15opJ");
        ltOptions.put("video", true);
        ltOptions.put("build", "Java_Selenium_Script");
        ltOptions.put("project", "DemoOnAutomation");
        ltOptions.put("selenium_version", "4.0.0");
        ltOptions.put("w3c", true);
        browserOptions.setCapability("LT:Options", ltOptions);


        try {
            driver = new RemoteWebDriver(new URL("https://" + username + ":" + accessKey + gridURL), browserOptions);


            // Navigate to the webpage
            driver.get("https://ecommerce-playground.lambdatest.io/");


            // Find the element with the name iPhone
            WebElement iphoneLink = driver.findElement(By.xpath("//a[contains(text(), 'iPhone')]"));


            // Click on the product to visit the product page
            iphoneLink.click();

            // Close the browser
            driver.close();
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}