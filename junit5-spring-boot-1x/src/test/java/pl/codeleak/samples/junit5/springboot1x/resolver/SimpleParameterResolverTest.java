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
import pl.codeleak.samples.junit5.springboot1x.resolver.factories.FactoriesExtension;
import pl.codeleak.samples.junit5.springboot1x.resolver.factories.OwnersFactory;
import pl.codeleak.samples.junit5.springboot1x.resolver.factories.PetsFactory;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ExtendWith(FactoriesExtension.class)
// proste factory jako parameter resolver -> plus podwójny extend with ale to fajnie jakby było ukryte żeby nie psuć niespodzianku
class SimpleParameterResolverTest {

    @Test
    void simpleExtensionTest(OwnersFactory ownersFactory,
                             PetsFactory petsFactory,
                             TestReporter testReporter) {

        Pet pet = petsFactory.createPet("Pies");
        Owner owner = ownersFactory.createOwner("Maciek", "Gdańsk", pet);

        testReporter.publishEntry("Pet", pet.toString());
        testReporter.publishEntry("Owner", owner.toString());
    }

    @Test
    void springSimpleExtensionTest(OwnersFactory ownersFactory,
                             PetsFactory petsFactory,
                             TestReporter testReporter) {

        Pet pet = petRepository.save(petsFactory.createPet("Pies"));
        Owner owner = ownerRepository.save(ownersFactory.createOwner("Maciek", "Gdańsk", pet));

        testReporter.publishEntry("Pet", pet.toString());
        testReporter.publishEntry("Owner", owner.toString());
    }

    @Autowired
    private PetRepository petRepository;
    @Autowired
    private OwnerRepository ownerRepository;
}
