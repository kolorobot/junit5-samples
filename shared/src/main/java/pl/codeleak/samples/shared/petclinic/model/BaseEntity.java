package pl.codeleak.samples.shared.petclinic.model;

import lombok.Data;

import java.util.UUID;

@Data
public abstract class BaseEntity {
    protected UUID id = UUID.randomUUID();
}
