package com.company.qa.base;


import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitUntilState;
import utils.PropertyReader;

import java.nio.file.Paths;
import java.util.Arrays;

public final class PlaywrightFactory {


    private static ThreadLocal<Playwright> playwright = new ThreadLocal<>();
    private static ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> context = new ThreadLocal<>();
    private static ThreadLocal<Page> page = new ThreadLocal<>();


    public static void launchBrowser() {

        String browserName = PropertyReader.getBrowser();
        boolean headless = PropertyReader.isHeadless();
        String appUrl = PropertyReader.getUrl();
        double timeout = Double.parseDouble(PropertyReader.getTimeout());

        playwright.set(Playwright.create());

        BrowserType.LaunchOptions options =
                new BrowserType.LaunchOptions().setHeadless(headless).setArgs(Arrays.asList(
                        "--disable-dev-shm-usage",
                        "--disable-blink-features=AutomationControlled",
                        "--no-sandbox",
                        "--disable-infobars"
                ));


        switch (browserName.toLowerCase()) {

            case "chromium":
                browser.set(playwright.get().chromium().launch(options));
                break;

            case "firefox":
                browser.set(playwright.get().firefox().launch(options));
                break;

            case "webkit":
                browser.set(playwright.get().webkit().launch(options));
                break;

            case "msedge":
                browser.set(playwright.get().chromium()
                        .launch(options.setChannel("msedge")));
                break;

            case "chrome":
                browser.set(playwright.get().chromium()
                        .launch(options.setChannel("chrome")));
                break;

            default:
                System.out.println("⚠ Invalid browser: " + browserName + ", defaulting to Chromium");
                browser.set(playwright.get().chromium().launch(options));
        }

        context.set(
                browser.get().newContext(
                        new Browser.NewContextOptions()
                                .setViewportSize(1920, 1080)
                                .setRecordVideoDir(Paths.get("videos/"))
                )
        );
        context.get().tracing().start(
                new Tracing.StartOptions()
                        .setScreenshots(true)
                        .setSnapshots(true)
                        .setSources(true)
        );

        page.set(context.get().newPage());
        page.get().navigate(
                appUrl,
                new Page.NavigateOptions()
                        .setWaitUntil(WaitUntilState.DOMCONTENTLOADED)
                        .setTimeout(timeout)
                        //.setWaitUntil(WaitUntilState.LOAD)
        );
    }


    public static void quit() {
        if (page.get() != null) {
            page.get().close();
            page.remove();
        }
        if (context.get() != null) {
            context.get().close();
            context.remove();
        }
        if (browser.get() != null) {
            browser.get().close();
            browser.remove();
        }
        if (playwright.get() != null) {
            playwright.get().close();
            playwright.remove();
        }
    }

    public static Page getPage() {
        return page.get();
    }

    public static BrowserContext getContext() {
        return context.get();
    }

    public static Browser getBrowser() {
        return browser.get();
    }
}
