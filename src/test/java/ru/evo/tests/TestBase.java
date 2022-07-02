package ru.evo.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import ru.evo.drivers.LocalMobileDriver;
import ru.evo.drivers.BrowserstackMobileDriver;
import ru.evo.helpers.Attach;

import java.util.Objects;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static io.qameta.allure.Allure.step;
import static ru.evo.helpers.Attach.sessionId;

public class TestBase {

    static String deviceHost = System.getProperty("deviceHost", "local");

    @BeforeAll
    public static void setUp() {

        if (Objects.equals(deviceHost, "local")) {
            Configuration.browser = LocalMobileDriver.class.getName();
        } else if (Objects.equals(deviceHost, "browserstack")) {
            Configuration.browser = BrowserstackMobileDriver.class.getName();
        } else if ((deviceHost != "local") || (deviceHost != "browserstack")) {
            System.out.println("Set right Host parameters: local or browserstack");
            System.exit(1);
        }
        Configuration.browserSize = null;
    }

    @BeforeEach
    public void startDriver() {
        addListener("AllureSelenide", new AllureSelenide());
        open();
    }

    @AfterEach
    public void afterEach() {
        String sessionId = sessionId();

        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();

        step("Close driver", Selenide::closeWebDriver);
        if (Objects.equals(deviceHost, "browserstack")) {
            Attach.video(sessionId);
        }
    }
}
