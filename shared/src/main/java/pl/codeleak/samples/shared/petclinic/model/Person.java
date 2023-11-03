package pl.codeleak.samples.shared.petclinic.model;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
abstract class Person extends BaseEntity {

    private String firstName;
    private String lastName;

}
