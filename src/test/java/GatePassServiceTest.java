
import com.research.exception.BusinessRuleViolationException;
import com.research.model.GateLane;
import com.research.model.LaneStatus;
import com.research.model.PassDirection;
import com.research.model.Vehicle;
import com.research.repository.GateLaneRepository;
import com.research.repository.GatePassRepository;
import com.research.service.GatePassService;
import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@Epic("Traffic Management")
@Feature("Gate Pass")
class GatePassServiceTest {

    @Mock
    private GatePassRepository gatePassRepository;

    @Mock
    private GateLaneRepository gateLaneRepository;

    private GatePassService gatePassService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        gatePassService = new GatePassService(gatePassRepository, gateLaneRepository);
    }

    @Test
    @Story("Request Entry")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("❌ Reject entry when lane is closed")
    void shouldRejectWhenLaneClosed() {

        GateLane lane = new GateLane(1, 1, 5, LaneStatus.CLOSED);
        when(gateLaneRepository.getById(1)).thenReturn(lane);

        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.isAllowed()).thenReturn(true);

        assertThrows(BusinessRuleViolationException.class, () ->
                gatePassService.requestPass(
                        1, vehicle, 1, PassDirection.ENTRY
                ));
    }

    @Test
    @Story("Request Entry")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("❌ Reject entry when vehicle is not allowed")
    void shouldRejectWhenVehicleNotAllowed() {

        GateLane lane = new GateLane(1, 1, 5, LaneStatus.OPEN);
        when(gateLaneRepository.getById(1)).thenReturn(lane);

        Vehicle vehicle = mock(Vehicle.class);
        when(vehicle.isAllowed()).thenReturn(false);

        assertThrows(BusinessRuleViolationException.class, () ->
                gatePassService.requestPass(
                        2, vehicle, 1, PassDirection.ENTRY
                ));
    }
}
