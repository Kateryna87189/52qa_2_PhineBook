package PhoneBook;

import PhoneBook.data.ContactData;
import PhoneBook.data.UserData;
import PhoneBook.model.Contact;
import PhoneBook.utils.DataProviders;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static PhoneBook.fw.ContactHelper.CONTACT_LOCATOR;
import static PhoneBook.fw.ContactHelper.CONTACT_NAME;

public class AddContactTests extends TestBase{



    @BeforeMethod
    public void preCondition() {
//        if (app.getUserHelper().isSignOutButtonPresent()){
//            logger.info("user already logged in Sing out...");
//            app.getUserHelper().clickOnSignOutButton();
//        }else {
//            logger.info("LOGIN link ist present. Not need to Sign out");
//        }
        app.getUserHelper().login(UserData.VALID_EMAIL, UserData.VALID_PASSWORD);



    }


    @Test
    public void addContactPositiveTest(){
        int contactsBefore= app.getContactHelper().getContactsCount();
        System.out.println("кількість контактів до тестів"+contactsBefore);
        // addContactPositiveData(CONTACT_NAME, "1234567890");
        app.getContactHelper().addContactPositiveData(new Contact()
                .setName(CONTACT_NAME)
                .setLastname("Lastname")
                .setPhone("1234567890")
                .setEmail("admin@gmail.com")
                .setAddress("Germany, Hannover")
                .setDescription("description"));
        int contactsAfter =app.getContactHelper(). getContactsCount();
        System.out.println("кількість контактів після тестів"+contactsAfter);
        Assert.assertTrue(app.getContactHelper().isContactAdded(CONTACT_NAME));
        Assert.assertEquals(contactsAfter, contactsBefore+1);
    }
    @Test
    public void addContactDataPositiveTest(){
        int contactsBefore= app.getContactHelper().getContactsCount();
        System.out.println("кількість контактів до тестів"+contactsBefore);
        // addContactPositiveData(CONTACT_NAME, "1234567890");
        app.getContactHelper().addContactPositiveData(new Contact()
                .setName(ContactData.VALID_NAME)
                .setLastname(ContactData.VALID_LASTNAME)
                .setPhone(ContactData.VALID_PHONE)
                .setEmail(ContactData.VALID_EMAIL)
                .setAddress(ContactData.VALID_ADDRESS)
                .setDescription(ContactData.VALID_DESCRIPTION));
        int contactsAfter =app.getContactHelper(). getContactsCount();
        System.out.println("кількість контактів після тестів"+contactsAfter);
        Assert.assertTrue(app.getContactHelper().isContactAdded(CONTACT_NAME));
        Assert.assertEquals(contactsAfter, contactsBefore+1);
    }
    @Test
    public void addContactWODescriptionPositiveTest(){
        int contactsBefore= app.getContactHelper().getContactsCount();
        System.out.println("кількість контактів до тестів"+contactsBefore);
        // addContactPositiveData(CONTACT_NAME, "1234567890");
        app.getContactHelper(). addContactPositiveData(new Contact()
                .setName(CONTACT_NAME)
                .setLastname(ContactData.VALID_LASTNAME)
                .setPhone(ContactData.VALID_PHONE)
                .setEmail(ContactData.VALID_EMAIL)
                .setAddress(ContactData.VALID_ADDRESS)
                //.setDescription("Some Description")
        );
        int contactsAfter =app.getContactHelper(). getContactsCount();
        System.out.println("кількість контактів після тестів"+contactsAfter);
        Assert.assertTrue(app.getContactHelper().isContactAdded(CONTACT_NAME));
        Assert.assertEquals(contactsAfter, contactsBefore+1);
    }



    @Test(dataProvider = "AddContactDataProvider", dataProviderClass = DataProviders.class)
    public void addContactDataProviderPositiveTest
            (String name, String lastName, String phone, String email, String address,String description ){
        int contactsBefore= app.getContactHelper().getContactsCount();
        System.out.println("кількість контактів до тестів"+contactsBefore);
        // addContactPositiveData(CONTACT_NAME, "1234567890");
        app.getContactHelper().addContactPositiveData(new Contact()
                .setName(name)
                .setLastname(lastName)
                .setPhone(phone)
                .setEmail(email)
                .setAddress(address)
                .setDescription(description));
        int contactsAfter =app.getContactHelper(). getContactsCount();
        System.out.println("кількість контактів після тестів"+contactsAfter);
        Assert.assertTrue(app.getContactHelper().isContactAdded(name));
        Assert.assertEquals(contactsAfter, contactsBefore+1);
    }

    @Test(dataProvider = "iteratorDataProvider", dataProviderClass = DataProviders.class)
    public void iteratorDataProviderPositiveTest
            (String name, String lastName, String phone, String email, String address,String description ){
        int contactsBefore= app.getContactHelper().getContactsCount();
        System.out.println("кількість контактів до тестів"+contactsBefore);
        // addContactPositiveData(CONTACT_NAME, "1234567890");
        app.getContactHelper().addContactPositiveData(new Contact()
                .setName(name)
                .setLastname(lastName)
                .setPhone(phone)
                .setEmail(email)
                .setAddress(address)
                .setDescription(description));
        int contactsAfter =app.getContactHelper(). getContactsCount();
        System.out.println("кількість контактів після тестів"+contactsAfter);
        Assert.assertTrue(app.getContactHelper().isContactAdded(name));
        Assert.assertEquals(contactsAfter, contactsBefore+1);
    }
    @Test(dataProvider = "AddContactObjectProvider", dataProviderClass = DataProviders.class)
    public void addContactObjectPositiveTest(Contact contact ){
        int contactsBefore= app.getContactHelper().getContactsCount();
        System.out.println("кількість контактів до тестів"+contactsBefore);
        app.getContactHelper().addContactPositiveData(contact);
        int contactsAfter =app.getContactHelper(). getContactsCount();
        System.out.println("кількість контактів після тестів"+contactsAfter);
        Assert.assertTrue(app.getContactHelper().isContactAdded(contact.getName()));
        Assert.assertEquals(contactsAfter, contactsBefore+1);
    }
    @Test(dataProvider = "addContactFromCsv", dataProviderClass = DataProviders.class)
    public void addContactObjectFromCsvPositiveTest(Contact contact ){
        int contactsBefore= app.getContactHelper().getContactsCount();
        System.out.println("кількість контактів до тестів"+contactsBefore);
        app.getContactHelper().addContactPositiveData(contact);
        int contactsAfter =app.getContactHelper(). getContactsCount();
        System.out.println("кількість контактів після тестів"+contactsAfter);
        Assert.assertTrue(app.getContactHelper().isContactAdded(contact.getName()));
        Assert.assertEquals(contactsAfter, contactsBefore+1);
    }

    @AfterMethod
    public void postCondition() {
        // Сколько контактов на странице
        int contactsBefore = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ДО удаления: " + contactsBefore);

        if (contactsBefore == 0) {
            System.out.println("Количество контактов 0. Нечего удалять");
            return;
        }
        app.getContactHelper().click(By.className(CONTACT_LOCATOR));
        app.getContactHelper().click(By.xpath("//button[.='Remove']"));
        // Ждём, пока не выполнено условие:
        // условие: уменьшилось ли количество контактов по сравнению с исходным значением contactsBefore
//        new WebDriverWait(driver, Duration.ofSeconds(2)).until(driver -> getContactsCount() < contactsBefore);
        // Явное ожидание, пока количество контактов не уменьшится (contactsBefore - 1)
        new WebDriverWait(app.driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.numberOfElementsToBe(By.className(CONTACT_LOCATOR),
                        contactsBefore - 1));
        int contactsAfter = app.getContactHelper().getContactsCount();
        System.out.println("Количество контактов ПОСЛЕ удаления: " + contactsAfter);
        // Проверяем, что стало на -1 контакт
        Assert.assertEquals(contactsAfter, contactsBefore - 1);
        System.out.println("Удаление контакта прошло успешно");
    }

}
