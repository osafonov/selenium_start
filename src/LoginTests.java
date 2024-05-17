import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class LoginTests {

    @Test
    public void loginLogoutSuccessTest() throws InterruptedException {

        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://the-internet.herokuapp.com/login");

        By userNameInp = By.id("username");
        By userPassInp = By.id("password");
        By loginBtn = By.tagName("button");
        By logoutBtn = By.className("button");
        By message = By.id("flash");

        webDriver.findElement(userNameInp).sendKeys("tomsmith");
        webDriver.findElement(userPassInp).sendKeys("SuperSecretPassword!");
        webDriver.findElement(loginBtn).click();

        assertEquals(webDriver.getCurrentUrl(), "https://the-internet.herokuapp.com/secure", "Current Url");
        assertEquals(webDriver.findElement(message).getText().split("\n")[0], "You logged into a secure area!", "Message");

        Wait<WebDriver> wait = new WebDriverWait(webDriver, Duration.ofMillis(100));
        wait.until(d -> isElementExist(webDriver.findElement(logoutBtn)));

        webDriver.findElement(logoutBtn).click();

        assertEquals(webDriver.getCurrentUrl(), "https://the-internet.herokuapp.com/login", "Current Url");
        assertEquals(webDriver.findElement(message).getText().split("\n")[0], "You logged out of the secure area!", "Message");

        Thread.sleep(2000);

        webDriver.quit();
    }


    private boolean isElementExist(WebElement element){
        return element.isDisplayed();
    }

}
