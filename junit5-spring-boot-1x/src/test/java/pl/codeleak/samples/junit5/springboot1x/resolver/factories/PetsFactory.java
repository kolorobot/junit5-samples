package pl.codeleak.samples.junit5.springboot1x.resolver.factories;

import pl.codeleak.samples.junit5.springboot1x.domain.Pet;

public class PetsFactory {

    public Pet createPet(String name) {
        Pet pet = new Pet();
        pet.setName(name);

        return pet;
    }

}
