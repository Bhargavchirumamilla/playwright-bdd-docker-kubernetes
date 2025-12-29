
package utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.WaitForSelectorState;

public class CommonUtils {
    protected Page page;
    double timeout = Double.parseDouble(PropertyReader.getTimeout());

    public CommonUtils(Page page) {
        this.page = page;
    }

    public void ariaRoleLink(String loc) {
        page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(loc)).click();
    }

    public void ariaRoleButton(String loc) {
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(loc)).click();
    }

    public String getByText(String loc) {
        page.getByText(loc);
        return loc;
    }

    public void getByLabel(String loc) {
        page.getByLabel(loc);
    }

    public void fill(String loc, String val) {
        page.fill(loc, val);
    }

    public void click(String loc) {
        page.click(loc);
    }

    public boolean isVisible(String loc) {
        try {
            page.locator(loc)
                    .waitFor(new Locator.WaitForOptions()
                            .setState(WaitForSelectorState.VISIBLE));
            return true;
        } catch (PlaywrightException e) {
            return false;
        }

    }


        public void safeClick(String selector) {
            Locator locator = page.locator(selector);

            locator.waitFor(new Locator.WaitForOptions()
                    .setState(WaitForSelectorState.VISIBLE)
                    .setTimeout(timeout));

            locator.scrollIntoViewIfNeeded();
            locator.click(new Locator.ClickOptions()
                    .setTimeout(timeout)
                    .setForce(true));
        }
    }


