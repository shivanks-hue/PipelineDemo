import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class AzureWithTunnel {

    WebDriver driver;

    @BeforeMethod
    public void setup() throws MalformedURLException {
        // LambdaTest username and access key (replace with actual values)
        String username = "shivanks";
        String authkey = "LT_V6UEGtFRGiIPH4QcUdNKQv4RIetJjVfXKKIEkrP3Sk15opJ";

        // LambdaTest Hub URL (this is the base URL for LambdaTest's Selenium Grid)
        String hub = "@hub.lambdatest.com/wd/hub";

        // Set ChromeOptions for the remote WebDriver on LambdaTest
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 10");  // OS of the machine
        browserOptions.setBrowserVersion("dev");      // Set browser version to 'dev', you can specify versions like 'latest' or a specific version number.

        // Setup LambdaTest specific options
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", username);  // LambdaTest username
        ltOptions.put("accessKey", authkey);  // LambdaTest access key
        ltOptions.put("visual", true);        // Enable visual testing (screenshots)
        ltOptions.put("video", true);         // Enable video recording of tests
        ltOptions.put("network", true);       // Enable network logs
        ltOptions.put("project", "Untitled"); // You can give the project a name here
        ltOptions.put("tunnel", true);        // Enable LambdaTest Tunnel to access localhost (important for testing local sites)
        ltOptions.put("selenium_version", "4.0.0");  // Ensure the correct Selenium version is being used
        ltOptions.put("w3c", true);           // Ensure W3C WebDriver compliance
        ltOptions.put("accessibility", true); // Enable accessibility testing
        browserOptions.setCapability("LT:Options", ltOptions);

        // Initialize RemoteWebDriver to use LambdaTest grid
        driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), browserOptions);

        // Implicit wait to handle synchronization
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testLoginForm() {
        // Open your local website (assuming you have the LambdaTest Tunnel running for localhost testing)
        driver.get("http://localhost/web1/");

        // Locate the username and password input fields and submit button using appropriate locators
        WebElement usernameField = driver.findElement(By.xpath("//*[@id='username']"));  // Adjust according to your form
        WebElement passwordField = driver.findElement(By.xpath("//*[@id='password']"));  // Adjust accordingly
        WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"1\"]"));     // Adjust if needed

        // Fill out the form with the provided credentials
        usernameField.sendKeys("shivank shukla");
        passwordField.sendKeys("12345678");

        // Click the submit button
        submitButton.click();

        // Wait for 3 seconds to let the form submission happen
        try {
            Thread.sleep(3000);  // Sleep for 3000 milliseconds (3 seconds)
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser after the test is done
        driver.quit();
    }
}
