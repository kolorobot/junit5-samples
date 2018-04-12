package pl.codeleak.samples.junit5.restassured;

import io.restassured.RestAssured;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.Optional;

public class RestAssuredExtension implements BeforeAllCallback {

    @Override
    public void beforeAll(ExtensionContext context) {
        Optional<Integer> port = Optional.ofNullable(System.getProperty("port")).map(Integer::valueOf);
        Optional<String> baseUri = Optional.ofNullable(System.getProperty("baseUri"));
        Optional<String> rootPath = Optional.ofNullable(System.getProperty("rootPath"));

        RestAssured.port = port.orElse(80);
        RestAssured.baseURI = baseUri.orElse("http://codeleak.pl");
        RestAssured.rootPath = rootPath.orElse("");
    }
}
