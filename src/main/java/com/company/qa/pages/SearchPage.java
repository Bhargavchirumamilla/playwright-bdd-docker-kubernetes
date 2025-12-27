package com.company.qa.pages;

import utils.CommonUtils;

public class SearchPage extends CommonUtils {

    public static final String SEARCHINPUT = "#searchInput";

    public static final String SEARCHSUGGESTION = "div[class='noSelect suggestionitem']";

    public static final String CONTENT = "//div[@class='content']//img";

    public static final String PAUSE = "//div[@class='vjs-skip-wrapper']//button[@title='Pause']";

//span[normalize-space()='Watchlist']

    public SearchPage(com.microsoft.playwright.Page page) {
        super(page);


    }

    public void search(String searchContent) {
        fill(SEARCHINPUT, searchContent);

    }

    public void searchResult(int index) {
        page.locator(SEARCHSUGGESTION).nth(index).click();
    }

    public void tapContent(int index) {
        page.locator(CONTENT).nth(index).click();
    }
}
