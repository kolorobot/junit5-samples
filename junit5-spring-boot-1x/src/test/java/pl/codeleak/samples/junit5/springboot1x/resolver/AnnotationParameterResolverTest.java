package pl.codeleak.samples.junit5.springboot1x.resolver;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.codeleak.samples.junit5.springboot1x.domain.Owner;
import pl.codeleak.samples.junit5.springboot1x.domain.OwnerRepository;
import pl.codeleak.samples.junit5.springboot1x.domain.PetRepository;
import pl.codeleak.samples.junit5.springboot1x.resolver.factories.annotation.AnOwner;
import pl.codeleak.samples.junit5.springboot1x.resolver.factories.annotation.AnnotationFactoriesExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ExtendWith(AnnotationFactoriesExtension.class)
class AnnotationParameterResolverTest {

    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private PetRepository petRepository;

    @Test
    void name(@AnOwner(withName = "Mikolaj", withAddress = "Trojmiasto", withPetName = "Kot") Owner owner,
              TestReporter testReporter) {

        testReporter.publishEntry("owner", owner.toString());
        testReporter.publishEntry("owners", ownerRepository.findAll().toString());
        testReporter.publishEntry("pets", petRepository.findAll().toString());
    }
}
