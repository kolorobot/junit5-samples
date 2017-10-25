package pl.codeleak.samples.shared.petclinic.repository;

import pl.codeleak.samples.shared.petclinic.model.Owner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Owners implements Repository<Owner> {

    public static final String OPERATION_NOT_SUPPORTED_MESSAGE = "Operation not supported!";

    private static List<Owner> owners =
        Arrays.asList(
            anOwner("George", "Franklin", "110 W. Liberty St.", "Madison", "6085551023"),
            anOwner("Betty", "Davis", "638 Cardinal Ave.", "Sun Prairie", "6085551749"),
            anOwner("Eduardo", "Rodriquez", "2693 Commerce St.", "McFarland", "6085558763"),
            anOwner("Harold", "Davis", "563 Friendly St.", "Windsor", "6085553198"),
            anOwner("Peter", "McTavish", "2387 S. Fair Way", "Madison", "6085552765"),
            anOwner("Jean", "Coleman", "105 N. Lake St.", "Monona", "6085552654"),
            anOwner("Jeff", "Black", "1450 Oak Blvd.", "Monona", "6085555387"),
            anOwner("Maria", "Escobito", "345 Maple St.", "Madison", "6085557683"),
            anOwner("David", "Schroeder", "2749 Blackhawk Trail", "Madison", "6085559435"),
            anOwner("Carlos", "Estaban", "2335 Independence La.", "Waunakee", "6085555487")
        );

    public static Owner anOwner(String firstname, String lastname, String address, String city, String telephone) {
        return Owner.builder()
                    .firstName(firstname)
                    .lastName(lastname)
                    .address(address)
                    .city(city)
                    .telephone(telephone)
                    .build();
    }

    @Override
    public List<Owner> findAll() {
        return owners;
    }

    @Override
    public void add(Owner entity) {
        throw new UnsupportedOperationException(OPERATION_NOT_SUPPORTED_MESSAGE);
    }

    public Optional<Owner> findByName(String firstName) {
        return findAll().stream()
                        .filter(o -> o.getFirstName().equalsIgnoreCase(firstName))
                        .findFirst();
    }
}
