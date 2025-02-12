package PhoneBook;

import PhoneBook.data.UserData;
import PhoneBook.utils.DataProviders;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class CreateAccountTest extends TestBase {


    @BeforeMethod
    public void precondition() {
        if (app.getContactHelper().isElementPresent(By.xpath("//button[.='Sign Out']"))) {
            app.getUserHelper().clickOnSignOutButton();
        }

    }
    @Test
    public void CreateAccountPositiveTest() {
        app.driver.findElement(By.xpath("//a[.='LOGIN']")).click();
        app.driver.findElement(By.name("email")).click();
        app.driver.findElement(By.name("email")).clear();
        app.driver.findElement(By.name("email")).sendKeys(UserData.RANDOM_EMAIL);
        app.driver.findElement(By.name("password")).click();
        app.driver.findElement(By.name("password")).clear();
        app.driver.findElement(By.name("password")).sendKeys(UserData.VALID_PASSWORD);
        app.driver.findElement(By.name("registration")).click();
        Assert.assertTrue(app.getContactHelper().isElementPresent(By.xpath("//button[.='Sign Out']")));
        //Assert.assertFalse(isElementPresent(By.xpath("//div[.='Registration failed with code 409']")));
    }

    @Test
    public void CreateAccountNegativeTest() {
        app.driver.findElement(By.xpath("//a[.='LOGIN']")).click();
        app.driver.findElement(By.name("email")).click();
        app.driver.findElement(By.name("email")).clear();
        app.driver.findElement(By.name("email")).sendKeys(UserData.VALID_EMAIL);
        app.driver.findElement(By.name("password")).click();
        app.driver.findElement(By.name("password")).clear();
        app.driver.findElement(By.name("password")).sendKeys(UserData.VALID_PASSWORD);
        app.driver.findElement(By.name("registration")).click();
        //isAlertPresent();
        Assert.assertTrue(app.getContactHelper().isAlertPresent());//hardAssert зупиняє перевірку
        //Assert.assertTrue(isElementPresent(By.xpath("//div[.='Registration failed with code 409']")));
    }

    @Test
    public void CreateAccountNegativeSoftAssertTest() {
        SoftAssert softAssert = new SoftAssert();//softAssert не зупиняє перевірку результат в кінці
        app.driver.findElement(By.xpath("//a[.='LOGIN']")).click();
        app.driver.findElement(By.name("email")).click();
        app.driver.findElement(By.name("email")).clear();
        app.driver.findElement(By.name("email")).sendKeys("katranchik21@gmail.com");
        app.driver.findElement(By.name("password")).click();
        app.driver.findElement(By.name("password")).clear();
        app.driver.findElement(By.name("password")).sendKeys(UserData.VALID_PASSWORD);
        app.driver.findElement(By.name("registration")).click();
        softAssert.assertTrue(app.getContactHelper().isAlertPresent());
        softAssert.assertTrue(app.getContactHelper().isElementPresent(By.xpath("//div[.='Registration failed with code 409']")));
        softAssert.assertAll();
    }

    @Test(dataProvider = "CreateAccountDataProvider", dataProviderClass = DataProviders.class)
    public void CreateAccountDataProvidePositiveTest(String randomEmail, String password) {
        app.driver.findElement(By.xpath("//a[.='LOGIN']")).click();
        app.driver.findElement(By.name("email")).click();
        app.driver.findElement(By.name("email")).clear();
        app.driver.findElement(By.name("email")).sendKeys(randomEmail);
        app.driver.findElement(By.name("password")).click();
        app.driver.findElement(By.name("password")).clear();
        app.driver.findElement(By.name("password")).sendKeys(password);
        app.driver.findElement(By.name("registration")).click();
        Assert.assertTrue(app.getContactHelper().isElementPresent(By.xpath("//button[.='Sign Out']")));

    }
    @AfterMethod
    public void postCondition() {
        if (app.getContactHelper().isElementPresent(By.xpath("//button[.='Sign Out']"))) {
            app.getUserHelper().clickOnSignOutButton();
        } else {
            System.out.println("User is not logged in, no need to sign out.");
        }
    }
}

