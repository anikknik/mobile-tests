package ru.evo.helpers;

import org.aeonbits.owner.ConfigFactory;
import ru.evo.owner.BrowserstackOwner;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;

public class Browserstack {
    static BrowserstackOwner bsOwner = ConfigFactory.create(BrowserstackOwner.class);

    public static String videoUrl(String sessionId) {
        String url = format("https://api.browserstack.com/app-automate/sessions/%s.json", sessionId);

        return given()
                .auth().basic(bsOwner.username(), bsOwner.access_key())
                .log().all()
                .when()
                .get(url)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .path("automation_session.video_url");
    }
}
