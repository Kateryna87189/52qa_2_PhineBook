package PhoneBook;

import PhoneBook.data.UserData;
import PhoneBook.model.User;
import PhoneBook.utils.DataProviders;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



public class LoginTests extends TestBase{


    @BeforeMethod
    public void preCondition(){

//        if (app.getUserHelper().isSignOutButtonPresent()){
//            logger.info("user already logged in Sing out...");
//            app.getUserHelper().clickOnSignOutButton();
//        }else {
//            logger.info("LOGIN link ist present. Not need to Sign out");
//        }
    }
    @Test(invocationCount = 1)
    public void loginExistedUserPositiveTest(){
      app.getUserHelper().clickOnLoginLink();
        app.getUserHelper().typeEmail("katranchik21@gmail.com");
        app.getUserHelper().typePassword("Password@1");
        app.getUserHelper().clickOnLoginButton();
        app.getUserHelper().checkLogin();
    }
    @Test(invocationCount = 1)
    public void loginExistedDataUserPositiveTest(){
        app.getUserHelper().clickOnLoginLink();
        app.getUserHelper().typeEmail(UserData.VALID_EMAIL);
        app.getUserHelper().typePassword(UserData.VALID_PASSWORD);
        app.getUserHelper().clickOnLoginButton();
        app.getUserHelper().checkLogin();
    }

    @Test
    public void loginWOEmailNegativeTest(){
        app.getUserHelper().clickOnLoginLink();
        app.getUserHelper().fillInLoginForm(new User()
               // .setEmail("katranchik21@gmail.com")
                .setPassword("Password@1"));
        app.getUserHelper(). clickOnLoginButton();
        Assert.assertTrue( app.getUserHelper().isAlertPresent());
    }
    //"katranchik21@gmail.com"
    //"Password@1"
    @Test(dataProvider = "loginDataProvider", dataProviderClass = DataProviders.class)
    public void loginExistedUserDataProviderPositiveTest(String email, String password){
        app.getUserHelper().clickOnLoginLink();
        app.getUserHelper().typeEmail(email);
        app.getUserHelper().typePassword(password);
        app.getUserHelper().clickOnLoginButton();
        app.getUserHelper().checkLogin();
    }


@AfterMethod
    public void postCondition(){
    //app.getUserHelper().clickOnSignOutButton();

    }
}
