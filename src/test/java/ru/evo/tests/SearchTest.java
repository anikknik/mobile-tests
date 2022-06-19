package ru.evo.tests;

import com.codeborne.selenide.CollectionCondition;
import io.appium.java_client.AppiumBy;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

@Tag("android")
public class SearchTest extends TestBase {

    @Test
    void searchTest() {
        String valueTitleCheck = "Browserstack";
        String valueDescriptionCheck = "Software company based in India";
        back();
        step("Search of Title", () -> {
            $(AppiumBy.id("org.wikipedia:id/search_container")).click();
            $(AppiumBy.id("org.wikipedia:id/search_src_text")).sendKeys(valueTitleCheck);
        });
        step("Check result's title and description", () -> {
            $$(AppiumBy.id("org.wikipedia:id/page_list_item_title"))
                    .shouldHave(CollectionCondition.sizeGreaterThan(0));
            $(AppiumBy.id("org.wikipedia:id/page_list_item_title"))
                    .shouldHave(text(valueTitleCheck));
            $(AppiumBy.id("org.wikipedia:id/page_list_item_description"))
                    .shouldHave(text(valueDescriptionCheck));

        });
    }
}
