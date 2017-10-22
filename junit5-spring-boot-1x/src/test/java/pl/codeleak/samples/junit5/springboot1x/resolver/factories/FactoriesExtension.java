package pl.codeleak.samples.junit5.springboot1x.resolver.factories;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

public class FactoriesExtension implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return isOwnersFactory(parameterContext) || isPetsFactory(parameterContext);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        if (isOwnersFactory(parameterContext)) {
            return new OwnersFactory();
        }

        if (isPetsFactory(parameterContext)) {
            return new PetsFactory();
        }

        throw new ParameterResolutionException("Unsupported factory type");
    }

    private boolean isOwnersFactory(ParameterContext parameterContext) {
        return parameterContext.getParameter().getType().isAssignableFrom(OwnersFactory.class);
    }

    private boolean isPetsFactory(ParameterContext parameterContext) {
        return parameterContext.getParameter().getType().isAssignableFrom(PetsFactory.class);
    }
}
