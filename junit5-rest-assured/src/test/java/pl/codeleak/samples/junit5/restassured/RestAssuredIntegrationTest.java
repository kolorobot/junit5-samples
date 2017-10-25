package pl.codeleak.samples.junit5.restassured;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static io.restassured.RestAssured.get;

@ExtendWith(RestAssuredExtension.class)
class RestAssuredIntegrationTest {

    @Test
    void name() {
        get()
                .then()
                .log().all()
                .statusCode(200);
    }
}