package pl.codeleak.samples.shared.petclinic.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class Visit extends BaseEntity {

    private final LocalDateTime date;

    private final String description;

    private final Pet pet;

}
