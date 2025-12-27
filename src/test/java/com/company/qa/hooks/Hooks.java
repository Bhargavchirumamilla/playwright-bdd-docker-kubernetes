package com.company.qa.hooks;

import com.company.qa.base.PlaywrightFactory;
import com.microsoft.playwright.*;
import io.cucumber.java.*;

import java.io.IOException;
import java.nio.file.*;
import java.util.stream.Stream;

public class Hooks {

    @BeforeAll
    public static void cleanOldArtifacts() {
        deleteFolder("screenshots");
        deleteFolder("videos");
        deleteFolder("traces");
    }

    @Before
    public void start() {
        System.out.println(
                "Running on thread: " + Thread.currentThread().getId()
        );
        PlaywrightFactory.launchBrowser();
    }

    @After
    public void stop(Scenario scenario) {

        String safeName = scenario.getName()
                .replaceAll("[^a-zA-Z0-9]", "_");

        PlaywrightFactory.getPage().screenshot(
                new Page.ScreenshotOptions()
                        .setPath(Paths.get(
                                "screenshots/" + safeName + ".png"))
                        .setFullPage(true)
        );
        PlaywrightFactory.getContext().tracing().stop(
                new Tracing.StopOptions()
                        .setPath(Paths.get(
                                "traces/" + safeName + ".zip"))
        );

        PlaywrightFactory.quit();
    }

    // 🔥 Utility method to delete folder contents
    private static void deleteFolder(String folderName) {
        Path path = Paths.get(folderName);

        if (!Files.exists(path)) {
            return;
        }

        try (Stream<Path> files = Files.walk(path)) {
            files
                    .sorted((a, b) -> b.compareTo(a)) // delete files first
                    .forEach(p -> {
                        try {
                            Files.delete(p);
                        } catch (IOException e) {
                            System.out.println("⚠ Unable to delete: " + p);
                        }
                    });
        } catch (IOException e) {
            System.out.println("⚠ Cleanup failed for folder: " + folderName);
        }
    }
}
