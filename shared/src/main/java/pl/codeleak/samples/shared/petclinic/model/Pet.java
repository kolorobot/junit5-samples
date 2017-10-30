package pl.codeleak.samples.shared.petclinic.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
public class Pet extends NamedEntity {

    private LocalDate birthDate;

    private PetType type;

    private Owner owner;

    private Set<Visit> visits = new HashSet<>();

    @Builder
    public Pet(String name, LocalDate birthDate, PetType type, Owner owner) {
        super(name);
        this.birthDate = birthDate;
        this.type = type;
        this.owner = owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
        // this.owner.addPet(this);
    }

    public void addVisit(Visit visit) {
        this.visits.add(visit);
    }
}
