package pl.codeleak.samples.junit5.springboot1x.resolver;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.codeleak.samples.junit5.springboot1x.domain.OwnerRepository;
import pl.codeleak.samples.junit5.springboot1x.domain.PetRepository;
import pl.codeleak.samples.junit5.springboot1x.resolver.factories.complex.ComplexFactoriesExtension;
import pl.codeleak.samples.junit5.springboot1x.resolver.factories.complex.ComplexFactory;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ExtendWith(ComplexFactoriesExtension.class)
class ComplexParameterResolverTest {

    @Autowired
    private PetRepository petRepository;
    @Autowired
    private OwnerRepository ownerRepository;

    @Test
    void complexParameterTest(ComplexFactory complexFactory,
                              TestReporter testReporter) {
        complexFactory.prepareNewOwner()
                .withName("Maciek")
                .withAddress("Gdańsk")
                .withPet("Pies")
                .create();

        testReporter.publishEntry("Pet", petRepository.findAll().toString());
        testReporter.publishEntry("Owner", ownerRepository.findAll().toString());
    }
}
