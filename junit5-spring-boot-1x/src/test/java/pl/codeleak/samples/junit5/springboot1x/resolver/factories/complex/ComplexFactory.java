package pl.codeleak.samples.junit5.springboot1x.resolver.factories.complex;

import pl.codeleak.samples.junit5.springboot1x.domain.Owner;
import pl.codeleak.samples.junit5.springboot1x.domain.OwnerRepository;
import pl.codeleak.samples.junit5.springboot1x.domain.Pet;
import pl.codeleak.samples.junit5.springboot1x.domain.PetRepository;

import java.util.Optional;

public class ComplexFactory {

    private final PetRepository petRepository;
    private final OwnerRepository ownerRepository;

    public ComplexFactory(PetRepository petRepository, OwnerRepository ownerRepository) {
        this.petRepository = petRepository;
        this.ownerRepository = ownerRepository;
    }

    public ComplexOwnerFactory prepareNewOwner() {
        return new ComplexOwnerFactory();
    }

    public class ComplexOwnerFactory {

        private Owner owner = new Owner();
        private Optional<Pet> pet = Optional.empty();

        public ComplexOwnerFactory withName(String name) {
            owner.setName(name);
            return this;
        }

        public ComplexOwnerFactory withAddress(String address) {
            owner.setAddress(address);
            return this;
        }

        public ComplexOwnerFactory withPet(String name) {
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
}