package pl.codeleak.samples.shared.petclinic.model;

import lombok.Builder;

public class Specialty extends NamedEntity {
    @Builder
    public Specialty(String name) {
        super(name);
    }
}
