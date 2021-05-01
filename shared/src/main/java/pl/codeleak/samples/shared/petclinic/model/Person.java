package pl.codeleak.samples.shared.petclinic.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
abstract class Person extends BaseEntity {

    private String firstName;
    private String lastName;

}
