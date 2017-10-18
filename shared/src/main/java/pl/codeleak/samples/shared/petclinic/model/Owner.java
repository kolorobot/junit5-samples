package pl.codeleak.samples.shared.petclinic.model;

import lombok.*;

import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
public class Owner extends Person {

    private final String address;
    private final String city;
    private final String telephone;

    @Singular
    private Set<Pet> pets;

    @Builder
    public Owner(String firstName, String lastName, String address, String city, String telephone) {
        super(firstName, lastName);
        this.address = address;
        this.city = city;
        this.telephone = telephone;
    }
}
