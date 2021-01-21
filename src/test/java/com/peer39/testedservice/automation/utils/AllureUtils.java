package com.peer39.testedservice.automation.utils;

import com.peer39.testedservice.automation.BaseTest;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.APPEND;

public class AllureUtils {
    public static final String ENV_PROPERTIES_FILE_NAME = "environment.properties";
    public static final String ALLURE_RESULTS_DIR = "build/allure-results/";

    @SneakyThrows
    private static void writeToPropertiesFile(List<String> content) {

        Path resultsPath = Paths.get(ALLURE_RESULTS_DIR);
        if (!Files.exists(resultsPath)) Files.createDirectories(resultsPath);

        Path filePath = Paths.get(ALLURE_RESULTS_DIR + ENV_PROPERTIES_FILE_NAME);
        if (!Files.exists(filePath)) Files.write(filePath, content, UTF_8);
        else Files.write(filePath, content, UTF_8, APPEND);
    }

    private static void generateEnvironmentProperties() {
        List<String> lines = new ArrayList<>();
        lines.add("env=" + BaseTest.TEST_ENV);
        lines.add("some.other.property=");

        writeToPropertiesFile(lines);
    }

    public static void addEnvironmentProperty(String key, String val) {
        List<String> lines = new ArrayList<>();
        lines.add(key + "=" + val);

        writeToPropertiesFile(lines);
    }

    public static void setupAllure() {
        generateEnvironmentProperties();
    }
}
