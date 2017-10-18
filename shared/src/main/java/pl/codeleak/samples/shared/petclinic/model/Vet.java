package pl.codeleak.samples.shared.petclinic.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Singular;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
public class Vet extends Person {

    private final Set<Specialty> specialties;

    @Builder
    public Vet(String firstName, String lastName, @Singular Set<Specialty> specialties) {
        super(firstName, lastName);
        this.specialties = specialties;
    }
}
