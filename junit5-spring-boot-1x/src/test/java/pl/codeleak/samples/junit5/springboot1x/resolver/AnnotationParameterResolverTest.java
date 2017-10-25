package pl.codeleak.samples.junit5.springboot1x.resolver;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.codeleak.samples.junit5.springboot1x.domain.Owner;
import pl.codeleak.samples.junit5.springboot1x.domain.OwnerRepository;
import pl.codeleak.samples.junit5.springboot1x.domain.Pet;
import pl.codeleak.samples.junit5.springboot1x.domain.PetRepository;
import pl.codeleak.samples.junit5.springboot1x.resolver.factories.annotation.AnOwner;
import pl.codeleak.samples.junit5.springboot1x.resolver.factories.annotation.AnnotationFactoriesExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ExtendWith(AnnotationFactoriesExtension.class)
class AnnotationParameterResolverTest {

    @Autowired
    private OwnerRepository ownerRepository;

    @Test
    void executesWithAnInjectedDomainObject(@AnOwner(withName = "Betty Davis", withAddress = "638 Cardinal Ave.", withPetName = "Lucky") Owner owner) {

        Owner theOwner = ownerRepository.findOne(owner.getId());

        assertAll(
            () -> assertEquals("Betty Davis", theOwner.getName()),
            () -> assertEquals("638 Cardinal Ave.", theOwner.getName()),
            () -> assertEquals("Lucky", theOwner.getPet().getName()));
    }
}
