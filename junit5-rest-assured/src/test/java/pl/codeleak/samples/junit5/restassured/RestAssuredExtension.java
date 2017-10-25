package pl.codeleak.samples.junit5.restassured;

import io.restassured.RestAssured;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.Optional;

public class RestAssuredExtension implements BeforeAllCallback {

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        Optional<Integer> port = Optional.ofNullable(System.getProperty("port")).map(Integer::valueOf);
        Optional<String> baseUri = Optional.ofNullable(System.getProperty("baseUri"));
        Optional<String> rootPath = Optional.ofNullable(System.getProperty("rootPath"));

        RestAssured.port = port.orElse(RestAssured.DEFAULT_PORT);
        RestAssured.baseURI = baseUri.orElse(RestAssured.DEFAULT_URI);
        RestAssured.rootPath = rootPath.orElse(RestAssured.DEFAULT_PATH);
    }
}
