package ru.evo.tests.local;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.evo.tests.TestBase;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Tag("android")
public class SwitchScrollViewTests extends TestBase {

    @Test
    @DisplayName("Verify that 1st Onboard screen is opened after launching")
    void firstOnboardScreenIsOpenedByDefault() {
        step("Verify Languages are displayed", () ->
                $$(AppiumBy.id("org.wikipedia.alpha:id" +
                        "/languagesList")).shouldHave(CollectionCondition
                        .sizeGreaterThan(0)));
    }

    @Test
    @DisplayName("Verify that 2nd Onboard screen is opened after the 1st screen")
    void secondOnboardScreenIsOpened() {
        step("Open the second Onboard screen", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id" +
                        "/fragment_onboarding_forward_button")).click());
        step("Verify the second screen 'Explore' is opened", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id" +
                        "/primaryTextView")).shouldHave(Condition
                        .text("New ways to explore")));
    }

    @Test
    @DisplayName("Verify that 3d Onboard screen is opened after the 2nd screen")
    void thirdScreenIsOpened() {
        step("Open the second screen", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id" +
                        "/fragment_onboarding_forward_button")).click());
        step("Open the third Sync screen", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id" +
                        "/fragment_onboarding_forward_button")).click());
        step("Verify the second screen 'Sync' is opened", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id" +
                        "/primaryTextView")).shouldHave(Condition
                        .text("Reading lists with sync")));
    }

    @Test
    @DisplayName("Verify that 4th Onboard screen is opened after the 3d screen")
    void fourthScreenIsOpened() {
        step("Open the second screen", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id" +
                        "/fragment_onboarding_forward_button")).click());
        step("Open the third screen", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id" +
                        "/fragment_onboarding_forward_button")).click());
        step("Open the fourth AnonymousData screen", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id" +
                        "/fragment_onboarding_forward_button")).click());
        step("Verify the second screen 'AnonymousData' is opened", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id" +
                        "/primaryTextView")).shouldHave(Condition
                        .text("Send anonymous data")));
    }

    @Test
    @DisplayName("Verify that Main screen is opened after all Onboard screens")
    void mainScreenIsOpenedClickingGetStartedBtn() {
        step("Open the second screen", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id" +
                        "/fragment_onboarding_forward_button")).click());
        step("Open the third screen", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id" +
                        "/fragment_onboarding_forward_button")).click());
        step("Open the fourth screen", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id" +
                        "/fragment_onboarding_forward_button")).click());
        step("Click Get Started btn", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id" +
                        "/fragment_onboarding_done_button")).click());
        step("Verify the main screen is opened", () ->
                $(AppiumBy.id("org.wikipedia.alpha:id" +
                        "/main_toolbar_wordmark")).isDisplayed());
    }
}
