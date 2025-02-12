package PhoneBook.core;

import com.google.common.io.Files;
import java.io.File;

import java.io.IOException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseHelper  {

   protected WebDriver driver;

    public BaseHelper(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isElementPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    public boolean isAlertPresent() {
        Alert alert = new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.alertIsPresent());
        if (alert == null) {
            return false;
        }else {
          driver.switchTo().alert().accept();
            return true;
        }
    }

    public void type(By locator, String text) {
        if (text != null) {
            click(locator);
            driver.findElement(locator).clear();
            driver.findElement(locator).sendKeys(text);
        }
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }


        public String takeScreenshot() {
            // Check for alert before taking the screenshot
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // Timeout for alert detection
                Alert alert = wait.until(ExpectedConditions.alertIsPresent());
                alert.accept();  // or alert.dismiss(); based on your use case
                System.out.println("Alert was present and accepted.");
            } catch (NoAlertPresentException e) {
                // No alert, proceed with screenshot capture
            }

            // Capture screenshot
            File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File screenshot = new File("src/test_screenshots/screen-" + System.currentTimeMillis() + ".png");
            try {
                Files.copy(tmp, screenshot);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Screenshot saved to: [" + screenshot.getAbsolutePath() + "]");
            return screenshot.getAbsolutePath();
        }

}
