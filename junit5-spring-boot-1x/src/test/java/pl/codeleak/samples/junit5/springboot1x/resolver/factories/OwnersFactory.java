package pl.codeleak.samples.junit5.springboot1x.resolver.factories;

import pl.codeleak.samples.junit5.springboot1x.domain.Owner;
import pl.codeleak.samples.junit5.springboot1x.domain.Pet;

public class OwnersFactory {

    public Owner createOwner(String name, String address, Pet pet) {
        Owner owner = new Owner();
        owner.setName(name);
        owner.setAddress(address);
        owner.setPet(pet);

        return owner;
    }



}
