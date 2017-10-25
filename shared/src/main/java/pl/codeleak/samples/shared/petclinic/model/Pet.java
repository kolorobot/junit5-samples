package pl.codeleak.samples.shared.petclinic.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Singular;

import java.time.LocalDate;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
public class Pet extends NamedEntity {

    private final LocalDate birthDate;

    private final PetType type;

    private final Owner owner;

    private final Set<Visit> visits;

    @Builder
    public Pet(String name, LocalDate birthDate, PetType type, Owner owner, @Singular Set<Visit> visits) {
        super(name);
        this.birthDate = birthDate;
        this.type = type;
        this.owner = owner;
        this.visits = visits;
    }
}
