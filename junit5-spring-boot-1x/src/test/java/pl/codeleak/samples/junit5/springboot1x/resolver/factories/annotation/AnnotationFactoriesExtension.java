package pl.codeleak.samples.junit5.springboot1x.resolver.factories.annotation;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.codeleak.samples.junit5.springboot1x.domain.Owner;
import pl.codeleak.samples.junit5.springboot1x.domain.OwnerRepository;
import pl.codeleak.samples.junit5.springboot1x.domain.PetRepository;
import pl.codeleak.samples.junit5.springboot1x.resolver.factories.mega.MegaFactory;

public class AnnotationFactoriesExtension implements ParameterResolver {
    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType().isAssignableFrom(Owner.class) &&
                parameterContext.getParameter().isAnnotationPresent(AnOwner.class);
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        MegaFactory megaFactory = prepareMegaFactory(extensionContext);
        AnOwner anOwner = parameterContext.getParameter().getAnnotation(AnOwner.class);

        return megaFactory.prepareNew()
                .withName(anOwner.withName())
                .withAddress(anOwner.withAddress())
                .withPet(anOwner.withPetName())
                .create();
    }

    private MegaFactory prepareMegaFactory(ExtensionContext extensionContext) {
        ApplicationContext context = SpringExtension.getApplicationContext(extensionContext);
        PetRepository petRepository = context.getBean(PetRepository.class);
        OwnerRepository ownerRepository = context.getBean(OwnerRepository.class);

        return new MegaFactory(petRepository, ownerRepository);

    }


}
