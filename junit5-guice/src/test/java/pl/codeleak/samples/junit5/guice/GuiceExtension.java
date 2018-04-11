package pl.codeleak.samples.junit5.guice;

import com.google.inject.Injector;
import com.google.inject.Module;
import org.junit.jupiter.api.extension.*;

import javax.inject.Inject;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class GuiceExtension implements TestInstancePostProcessor, TestExecutionExceptionHandler {

    @Override
    public void postProcessTestInstance(Object testInstance, ExtensionContext context) throws Exception {
        Guice guiceAnnotation = context.getRequiredTestClass().getAnnotation(Guice.class);
        if (guiceAnnotation == null) {
            throw new Exception("Use @GuiceModule with this extension!");
        }

        Class<? extends Module>[] guiceModulesClasses = guiceAnnotation.modules();
        List<? extends Module> modules = stream(guiceModulesClasses).map(this::newInstance).collect(Collectors.toList());

        Injector injector = com.google.inject.Guice.createInjector(modules);
        injector.injectMembers(testInstance);

    }

    private Module newInstance(Class<? extends Module> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        Optional<Method> testMethod = context.getTestMethod();
        if (!testMethod.isPresent()) {
            return;
        }

        if (!testMethod.get().isAnnotationPresent(Inject.class)
            && !throwable.getClass().isAssignableFrom(ParameterResolutionException.class)) {
            throw throwable;
        }
    }
}
