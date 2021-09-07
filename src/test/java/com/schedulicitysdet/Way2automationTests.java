package com.schedulicitysdet;

import com.google.common.base.Verify;
import org.junit.jupiter.api.*;

public class Way2automationTests {
    @BeforeEach
    public void getHomePage() {
        Pages.homePage().goTo();
    }

    //    exercise 1 - Successful login with valid username and password
    @Test
    public void SuccessWithValidUserNameAndPassword() {
        Pages.homePage().clickBoxLinkByText("Registration");
        Pages.registrationPage().completeLoginForm(true, true);
        Assertions.assertTrue(Pages.registrationPage().loginSuccess());
    }

    //    exercise 2 - UserRegistration failure with an invalid username/password combination
    @Test
    public void failureWithInvalidUserName() {
        Pages.homePage().clickBoxLinkByText("Registration");
        Pages.registrationPage().completeLoginForm(false, true);
        Assertions.assertTrue(Pages.registrationPage().loginFailure());
    }

    @Test
    public void failureWithInvalidPassword() {
        Pages.homePage().clickBoxLinkByText("Registration");
        Pages.registrationPage().completeLoginForm(true, false);
        Assertions.assertTrue(Pages.registrationPage().loginFailure());
    }

    //    exercise 3 - Successful delete user from table
    @Test
    public void successfulDeleteUserByUserName() {
        Pages.homePage().clickBoxLinkByText("WebTables");
        Pages.webTablesPage().deleteTableRowByUserName("admin");
        Pages.webTablesPage().confirmDeletion();
        Verify.verify(Pages.webTablesPage().userWasDeleted("admin"));
    }

    @AfterAll
    public static void cleanUp() {
        Browser.close();
    }
}
