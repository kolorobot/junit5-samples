package pl.codeleak.samples.shared.petclinic.model;

import lombok.Builder;

public class PetType extends NamedEntity {

    @Builder
    public PetType(String name) {
        super(name);
    }
}
