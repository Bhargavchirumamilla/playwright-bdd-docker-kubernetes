
package com.company.qa.pages;

import utils.CommonUtils;

public class LoginPage extends CommonUtils {

    public static final String USER = "//input[@id='textField']";
    public static final String PASS = "#password";

    public static final String LOGINButton = "//a[@class='loginBtn noSelect']";

    public static final String LOGINSpan = "//button[@type='button']";

    public LoginPage(com.microsoft.playwright.Page page) {
        super(page);
    }

    public void login(String userName) {
       fill(USER, userName);


    }
}
