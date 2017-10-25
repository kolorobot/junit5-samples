package pl.codeleak.samples.shared.petclinic.repository;

public enum VisitType {
    SURGERY(1), CONSULTATION(3), DIAGNOSTICS(2);

    private int maxVisits;

    VisitType(int maxVisits) {
        this.maxVisits = maxVisits;
    }

    public int getMaxVisits() {
        return maxVisits;
    }
}