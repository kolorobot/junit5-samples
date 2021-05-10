package pl.codeleak.samples.shared.petclinic.repository;

import pl.codeleak.samples.shared.petclinic.model.Owner;
import pl.codeleak.samples.shared.petclinic.model.Pet;

import java.time.LocalDate;
import java.util.*;

public class Pets implements Repository<Pet> {
    private static List<Pet> pets = new ArrayList<>(Arrays.asList(
        aPet("Leo", "2013-09-07", "George Franklin"),
        aPet("Basil", "2014-08-06", "Betty Davis"),
        aPet("Rosy", "2015-04-17", "Eduardo Rodriquez"),
        aPet("Jewel", "2014-03-07", "Eduardo Rodriquez"),
        aPet("Iggy", "2014-11-30", "Harold Davis"),
        aPet("George", "2014-01-20", "Peter McTavish"),
        aPet("Samantha", "2016-09-04", "Jean Coleman"),
        aPet("Max", "2015-09-04", "Jean Coleman"),
        aPet("Lucky", "2014-08-06", "Jeff Black"),
        aPet("Mulligan", "2009-02-24", "Maria Escobito"),
        aPet("Freddy", "2010-03-09", "David Schroeder"),
        aPet("Lucky", "2010-06-24", "Carlos Estaban"),
        aPet("Sly", "2012-06-08", "Carlos Estaban")
    ));


    public static Pet aPet(String name, String birthDate, String ownerFullName) {
        Pet pet = Pet.builder().name(name).birthDate(LocalDate.parse(birthDate)).build();
        Owner owner = Owners.byName(ownerFullName);
        owner.addPet(pet);
        pet.setOwner(owner);
        return pet;
    }

    public static Pet byName(String name) {
        return pets.stream().filter(p -> Objects.equals(p.getName(), name)).findFirst().orElseThrow();
    }

    public Optional<Pet> findByName(String name) {
        return findAll().stream().filter(p -> Objects.equals(p.getName(), name)).findFirst();
    }

    @Override
    public List<Pet> findAll() {
        return pets;
    }
}
