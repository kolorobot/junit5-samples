package pl.codeleak.samples.junit5.mockito;

import name.falgout.jeffrey.testing.junit5.MockitoExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import pl.codeleak.samples.shared.petclinic.model.Pet;
import pl.codeleak.samples.shared.petclinic.model.Visit;
import pl.codeleak.samples.shared.petclinic.repository.Pets;
import pl.codeleak.samples.shared.petclinic.repository.Visits;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MockitoIntegrationTest {

    @InjectMocks
    private VisitNotificationService testObj;
    @Mock
    private Visits visits;
    @Mock
    private NotificationSender notificationSender;

    @Test
    @DisplayName("Should send notification about the visit for given pet")
    // wstrzykiwanie Argument Captora
    void sendsNotification(ArgumentCaptor<String> messageCaptor) {
        // arrange
        String expectedName = "Maciek";
        String expectedDate = "2017-10-26T19:00";

        Pet givenPet = Pets.aPet(expectedName, "1992-08-25");
        Visit givenVisit = Visits.aVisit(expectedDate, "Control visit", givenPet);

        when(visits.findByPet(givenPet)).thenReturn(Optional.of(givenVisit));

        // act
        testObj.notifyAboutVisit(givenPet);

        // assert
        verify(notificationSender).sendMessage(eq(expectedName), messageCaptor.capture());

        String resultMessage = messageCaptor.getValue();
        Assertions.assertTrue(resultMessage.contains(expectedDate));
    }
}