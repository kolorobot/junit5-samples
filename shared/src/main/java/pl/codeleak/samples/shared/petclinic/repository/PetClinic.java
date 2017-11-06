package pl.codeleak.samples.shared.petclinic.repository;

public class PetClinic {

    private Owners owners = new Owners();
    private Pets pets = new Pets();
    private Vets vets = new Vets();
    private Visits visits = new Visits();

    public Owners getOwners() {
        return owners;
    }

    public Pets getPets() {
        return pets;
    }

    public Vets getVets() {
        return vets;
    }

    public Visits getVisits() {
        return visits;
    }
}
