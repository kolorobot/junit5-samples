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
class SimpleParameterResolverTest {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Test
    void simpleExtensionTest(OwnersFactory ownersFactory,
                             PetsFactory petsFactory,
                             TestReporter testReporter) {

        Pet pet = petsFactory.createPet("Rosy");
        Owner owner = ownersFactory.createOwner("Eduardo Rodriquez", "2693 Commerce St. McFarland", pet);

        // TODO Assert
        testReporter.publishEntry("Pet", pet.toString());
        testReporter.publishEntry("Owner", owner.toString());
    }

    @Test
    void springSimpleExtensionTest(OwnersFactory ownersFactory,
                                   PetsFactory petsFactory,
                                   TestReporter testReporter) {

        Pet pet = petsFactory.createPet("Rosy");
        Owner owner = ownersFactory.createOwner("Eduardo Rodriquez", "2693 Commerce St. McFarland", pet);

        Pet savedPet = petRepository.save(pet);
        Owner savedOwner = ownerRepository.save(owner);

        // TODO Assert
        testReporter.publishEntry("Pet", savedPet.toString());
        testReporter.publishEntry("Owner", savedOwner.toString());
    }
}
