package pl.codeleak.samples.junit5.springboot1x;

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

@SpringBootTest
@ExtendWith(SpringExtension.class)
class SpringBoot1xIntegrationTest {

    // przykład pokazujący jak proste używanie jest spring extension, od razu jest wstrzykiwanie i te sprawy jak z runnerem

    @Autowired
    private OwnerRepository ownerRepository;
    @Autowired
    private PetRepository petRepository;

    @Test
    void contextLoads(TestReporter testReporter) {

        Pet pet = new Pet();
        pet.setName("Pies");
        Pet savedPet = petRepository.save(pet);

        Owner owner = new Owner();
        owner.setName("Maciek");
        owner.setAddress("Gdańsk");
        owner.setPet(savedPet);

        ownerRepository.save(owner);

        testReporter.publishEntry("Saved owners", ownerRepository.findAll().toString());
        testReporter.publishEntry("Saved pets", petRepository.findAll().toString());





    }
}