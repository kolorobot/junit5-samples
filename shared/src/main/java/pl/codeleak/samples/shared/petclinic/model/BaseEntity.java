package pl.codeleak.samples.shared.petclinic.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Data
public abstract class BaseEntity {

    protected final UUID id = UUID.randomUUID();

}
