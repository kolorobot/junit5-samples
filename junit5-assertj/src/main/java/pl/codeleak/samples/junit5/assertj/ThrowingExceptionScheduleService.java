package pl.codeleak.samples.junit5.assertj;

import pl.codeleak.samples.shared.petclinic.model.Pet;
import pl.codeleak.samples.shared.petclinic.model.Visit;
import pl.codeleak.samples.shared.petclinic.service.VisitScheduleService;

class ThrowingExceptionScheduleService implements VisitScheduleService {

    static final String OPERATION_NOT_SUPPORTED_MESSAGE = "Operation not supported!";

    @Override
    public Visit scheduleVisit(Pet pet) {
        throw new UnsupportedOperationException(OPERATION_NOT_SUPPORTED_MESSAGE);
    }
}
