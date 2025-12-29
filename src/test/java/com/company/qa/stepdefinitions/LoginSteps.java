
package com.company.qa.stepdefinitions;

import com.company.qa.base.PlaywrightFactory;
import com.company.qa.pages.LoginPage;
import com.microsoft.playwright.Page;
import io.cucumber.java.en.*;
import org.testng.asserts.SoftAssert;
import utils.PropertyReader;

public class LoginSteps {
    LoginPage loginPage;
    Page page = PlaywrightFactory.getPage();

    @Given("user logs in")
    public void userLogsIn() {

        loginPage = new LoginPage(page);
        loginPage.safeClick(LoginPage.LOGINButton);

    }


    @Then("user sends username and passcode")
    public void userSendsUsernameAndPasscode() {
        loginPage.login(PropertyReader.getTestData("login.username"));

    }

    @And("login success")
    public void loginSuccess() {
        SoftAssert softAssert = new SoftAssert();
        loginPage.safeClick(LoginPage.LOGINSpan);
        softAssert.assertEquals(loginPage.getByText("Verify With OTP"), "Verify With OTP", "User not able to move to login page");
        softAssert.assertAll();


    }



}

