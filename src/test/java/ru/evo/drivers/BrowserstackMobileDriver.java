package ru.evo.drivers;

import com.codeborne.selenide.WebDriverProvider;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import owner.BrowserstackOwner;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserstackMobileDriver implements WebDriverProvider {

    static BrowserstackOwner bsOwner = ConfigFactory.create(BrowserstackOwner.class);

    @Override
    public WebDriver createDriver(Capabilities capabilities) {
        MutableCapabilities mutableCapabilities = new MutableCapabilities();
        mutableCapabilities.merge(capabilities);
        // Set your access credentials
        mutableCapabilities.setCapability("browserstack.user", bsOwner.username());
        mutableCapabilities.setCapability("browserstack.key", bsOwner.access_key());

        // Set URL of the application under test
//        mutableCapabilities.setCapability("app", "bs://c700ce60cf13ae8ed97705a55b8e022f13c5827c");
        mutableCapabilities.setCapability("app", bsOwner.app());

        // Specify device and os_version for testing
        mutableCapabilities.setCapability("device", bsOwner.device());
        mutableCapabilities.setCapability("os_version", bsOwner.osVersion());

        // Set other BrowserStack capabilities
        mutableCapabilities.setCapability("project", bsOwner.project());
        mutableCapabilities.setCapability("build", bsOwner.build());
        mutableCapabilities.setCapability("name", bsOwner.name());
        return new RemoteWebDriver(getBrowserstackUrl(), mutableCapabilities);
    }

    public static URL getBrowserstackUrl() {
        try {
            return new URL(bsOwner.baseUrl());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
