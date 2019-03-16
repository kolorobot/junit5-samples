package pl.codeleak.samples.junit5.basics;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

class _7_ConditionalTestExecution {

    @Test
    @EnabledOnOs(OS.MAC)
    void onlyOnMac() {

    }

    @Test
    @EnabledOnOs(OS.WINDOWS)
    void onlyOnWindows() {

    }

    @Test
    @DisabledOnOs(OS.WINDOWS)
    void notOnWindows() {

    }

    @Test
    @EnabledOnJre(JRE.JAVA_8)
    void onlyOnJava8() {
    }

    @Test
    @DisabledOnJre(JRE.JAVA_10)
    void notOnJava10() {

    }

    @Test
    @EnabledOnJre(JRE.JAVA_11)
    void onJava11() {

    }

    @Test
    @EnabledIfEnvironmentVariable(named = "ENV", matches = "true")
    void onlyWhenEnvVariableMatchesValue() {

    }

    @Test
    @EnabledIfSystemProperty(named = "SYS", matches = "true")
    void onlyWhenSystemPropertyMatchesValue() {

    }
}
