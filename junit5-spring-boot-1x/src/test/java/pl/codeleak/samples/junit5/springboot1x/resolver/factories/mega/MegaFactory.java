package pl.codeleak.samples.junit5.springboot1x.resolver.factories.mega;

import pl.codeleak.samples.junit5.springboot1x.domain.Owner;
import pl.codeleak.samples.junit5.springboot1x.domain.OwnerRepository;
import pl.codeleak.samples.junit5.springboot1x.domain.Pet;
import pl.codeleak.samples.junit5.springboot1x.domain.PetRepository;

import java.util.Optional;

public class MegaFactory {

    private final PetRepository petRepository;
    private final OwnerRepository ownerRepository;

    private Owner owner;
    private Optional<Pet> pet;

    public MegaFactory(PetRepository petRepository, OwnerRepository ownerRepository) {
        this.petRepository = petRepository;
        this.ownerRepository = ownerRepository;
    }

    public MegaFactory prepareNew() {
        owner = new Owner();
        pet = Optional.empty();
        return this;
    }

    public MegaFactory withName(String name) {
        owner.setName(name);
        return this;
    }

    public MegaFactory withAddress(String address) {
        owner.setAddress(address);
        return this;
    }

    public MegaFactory withPet(String name) {
        Pet pet = new Pet();
        pet.setName(name);

        this.pet = Optional.of(pet);
        return this;
    }

    public Owner create() {
        pet.map(petRepository::save).ifPresent(owner::setPet);
        return ownerRepository.save(owner);
    }
}