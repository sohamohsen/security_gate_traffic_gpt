
import com.research.exception.DuplicateIdException;
import com.research.model.Resident;
import com.research.repository.ResidentRepository;
import com.research.service.ResidentService;
import com.research.service.ValidationService;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Epic("Resident Management")
@Feature("Resident Service")
@ExtendWith(MockitoExtension.class)
class ResidentServiceTest {

    @Mock
    private ResidentRepository residentRepository;

    @InjectMocks
    private ResidentService residentService;

    private ValidationService validationService = new ValidationService();

    @BeforeEach
    void setup() {
        residentService = new ResidentService(residentRepository, validationService);
    }

    @Test
    @Story("Add Resident")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("✅ Add resident successfully")
    void shouldAddResidentSuccessfully() {
        Resident resident = new Resident(
                1, "Ahmed Ali", "ahmed@mail.com", "+201234567890", "A-12"
        );

        when(residentRepository.findByEmail(resident.getEmail()))
                .thenReturn(Optional.empty());

        assertDoesNotThrow(() -> residentService.addResident(resident));

        verify(residentRepository, times(1)).add(resident);
    }

    @Test
    @Story("Add Resident")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("❌ Fail when resident email already exists")
    void shouldFailWhenEmailExists() {
        Resident resident = new Resident(
                2, "Sara Mohamed", "sara@mail.com", "+201111111111", "B-7"
        );

        when(residentRepository.findByEmail(resident.getEmail()))
                .thenReturn(Optional.of(resident));

        assertThrows(DuplicateIdException.class,
                () -> residentService.addResident(resident));
    }

    @Test
    @Story("Add Resident")
    @Severity(SeverityLevel.MINOR)
    @DisplayName("❌ Fail when phone number is invalid")
    void shouldFailWhenPhoneInvalid() {
        Resident resident = new Resident(
                3, "Omar Hassan", "omar@mail.com", "INVALID_PHONE", "C-3"
        );

        assertThrows(RuntimeException.class,
                () -> residentService.addResident(resident));
    }
}
