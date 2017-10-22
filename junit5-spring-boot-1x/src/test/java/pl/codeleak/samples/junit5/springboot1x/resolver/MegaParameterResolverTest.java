package pl.codeleak.samples.junit5.springboot1x.resolver;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestReporter;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pl.codeleak.samples.junit5.springboot1x.domain.OwnerRepository;
import pl.codeleak.samples.junit5.springboot1x.domain.PetRepository;
import pl.codeleak.samples.junit5.springboot1x.resolver.factories.mega.MegaFactoriesExtension;
import pl.codeleak.samples.junit5.springboot1x.resolver.factories.mega.MegaFactory;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ExtendWith(MegaFactoriesExtension.class)
// przykład jak połączyć nasze własne extension z tym springowym i ukryć tworzenie skomplikowanych obiektów domenowych za
// fasadą faktorek, builderów i zrozumiałego API
class MegaParameterResolverTest {

    @Autowired
    private PetRepository petRepository;
    @Autowired
    private OwnerRepository ownerRepository;

    @Test
    void megaParameterTest(MegaFactory megaFactory,
                           TestReporter testReporter) {
        megaFactory.prepareNew()
                .withAddress("Gdańsk")
                .withName("Maciek")
                .withPet("Pies")
                .create();

        testReporter.publishEntry("Pet", petRepository.findAll().toString());
        testReporter.publishEntry("Owner", ownerRepository.findAll().toString());

    }
}
