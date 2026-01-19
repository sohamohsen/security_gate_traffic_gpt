
import com.research.exception.BusinessRuleViolationException;
import com.research.model.Resident;
import com.research.model.Vehicle;
import com.research.model.VehicleType;
import com.research.repository.VehicleRepository;
import com.research.service.ValidationService;
import com.research.service.VehicleService;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Epic("Vehicle Management")
@Feature("Vehicle Service")
@ExtendWith(MockitoExtension.class)
class VehicleServiceTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @InjectMocks
    private VehicleService vehicleService;

    private ValidationService validationService = new ValidationService();

    @BeforeEach
    void init() {
        vehicleService = new VehicleService(vehicleRepository, validationService);
    }

    @Test
    @Story("Register Vehicle")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("❌ Reject duplicate plate number")
    void shouldRejectDuplicatePlate() {

        when(vehicleRepository.existsByPlateNumber("ABC-123"))
                .thenReturn(true);

        Vehicle vehicle = new Vehicle(
                1,
                "ABC-123",
                new Resident(1, "Ali", "a@mail.com", "+2010", "A-1"),
                new VehicleType(1, "Car", "Private car"),
                true
        );

        assertThrows(BusinessRuleViolationException.class,
                () -> vehicleService.registerVehicle(vehicle));
    }

    @Test
    @Story("Register Vehicle")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("✅ Register vehicle successfully")
    void shouldRegisterVehicle() {

        when(vehicleRepository.existsByPlateNumber("XYZ-999"))
                .thenReturn(false);

        Vehicle vehicle = new Vehicle(
                2,
                "XYZ-999",
                new Resident(2, "Mona", "m@mail.com", "+2011", "B-2"),
                new VehicleType(1, "SUV", "SUV car"),
                true
        );

        assertDoesNotThrow(() -> vehicleService.registerVehicle(vehicle));
    }
}
