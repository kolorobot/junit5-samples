package pl.codeleak.samples.shared.petclinic.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
public class VetAddress extends BaseEntity {

    private final String postalCode;

    private final String city;

    private final String province;

    private final Vet vet;

}
