package PhoneBook;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static PhoneBook.fw.ContactHelper.CONTACT_LOCATOR;
import static PhoneBook.fw.ContactHelper.CONTACT_NAME;

public class DeleteContactsTests extends TestBase{
    @BeforeMethod
    public void precondition() {
       app.getUserHelper().login("katranchik21@gmail.com", "Password@1");
       app.getContactHelper().addContactPositiveData(CONTACT_NAME, "1234567890");
    }

    @Test
    public void createOneAndDeleteOneContactTest(){
        int contactsBefore= app.getContactHelper().getContactsCount();
        app.getContactHelper().clickAndDeleteOneContact();
        new WebDriverWait(app.driver, Duration.ofSeconds(2)).until(ExpectedConditions.numberOfElementsToBe
                (By.className(CONTACT_LOCATOR),contactsBefore-1));
        int contactsAfter = app.getContactHelper().getContactsCount();
        Assert.assertEquals(contactsAfter, contactsBefore-1);
    }
    @Test
    public void deleteAllContactsTests(){
        try {
            while (app.getContactHelper().hasContacts()){
               app.getContactHelper().deleteFirstContact();
            }
        } catch (NoSuchElementException e) {
            System.out.println("усі контакти були видалені");
        }
        Assert.assertEquals(app.getContactHelper().getContactsCount(), 0, "не всі контакти були видалені");
    }

}
