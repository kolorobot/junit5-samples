package pl.codeleak.samples.junit5.springboot1x;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.codeleak.samples.shared.petclinic.model.Owner;
import pl.codeleak.samples.shared.petclinic.model.Pet;
import pl.codeleak.samples.shared.petclinic.repository.Owners;
import pl.codeleak.samples.shared.petclinic.repository.Pets;

import java.util.List;

@RestController
@RequestMapping("/petclinic")
public class PetClinicApi {

    @Autowired
    private Pets pets;

    @Autowired
    private Owners owners;

    @GetMapping("/pets")
    List<Pet> pets() {
        return pets.findAll();
    }

    @GetMapping("/pets/{name}")
    Owner petByName(@PathVariable String name) {
        return owners.findByName(name).orElse(null);
    }

    @GetMapping("/owners")
    List<Owner> owners() {
        return owners.findAll();
    }
}
