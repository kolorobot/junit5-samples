package pl.codeleak.samples.shared.petclinic.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pl.codeleak.samples.shared.petclinic.repository.VisitType;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class Visit extends BaseEntity {

    private LocalDateTime date;

    private String description;

    private Pet pet;

    private VisitType visitType;

    public void setPet(Pet pet) {
        this.pet = pet;
        this.pet.addVisit(this);
    }
}
