package pl.codeleak.samples.junit5.springboot1x.resolver.factories.mega;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.codeleak.samples.junit5.springboot1x.domain.OwnerRepository;
import pl.codeleak.samples.junit5.springboot1x.domain.PetRepository;

public class MegaFactoriesExtension implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().isAssignableFrom(MegaFactory.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        ApplicationContext context = SpringExtension.getApplicationContext(extensionContext);
        PetRepository petRepository = context.getBean(PetRepository.class);
        OwnerRepository ownerRepository = context.getBean(OwnerRepository.class);


        return new MegaFactory(petRepository, ownerRepository);
    }
}
