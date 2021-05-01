package pl.codeleak.samples.shared.petclinic.model;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
abstract class NamedEntity extends BaseEntity {

    private String name;

}
