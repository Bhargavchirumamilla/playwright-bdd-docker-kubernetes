package com.company.qa.stepdefinitions;

import com.company.qa.base.PlaywrightFactory;
import com.company.qa.pages.SearchPage;
import com.microsoft.playwright.Page;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;


public class SearchSteps {

    SearchPage searchPage;
    Page page = PlaywrightFactory.getPage();

    @Given("^User provide (.*) content to search$")

    public void userProvideContentToSearch(String searchContentName) {
        searchPage = new SearchPage( page);
        searchPage.search(searchContentName);
    }

    @Then("Top Search Results screen {int}")
    public void topSearchResultsScreen(int index) {
        searchPage.searchResult(index);
        searchPage.tapContent(index);

    }

    @And("User navigate to player and able play the content")
    public void userNavigateToPlayerAndAblePlayTheContent() {
        Assert.assertTrue(searchPage.isVisible(SearchPage.PAUSE), "❌ Pause button is NOT visible");

    }
}
