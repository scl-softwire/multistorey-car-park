import com.softwire.training.parking.Vehicle;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class VehicleTest {

    @Test
    public void vehiclesStoreRequiredInformation() {

        // Arrange
        Vehicle.Type type = Vehicle.Type.CAR;
        String registration = "WL60FFY";
        double height = 1.55;
        double width = 1.92;

        // Act
        Vehicle vehicle = new Vehicle(type, registration, height, width);

        // Assert
        assertThat(vehicle.getType()).isEqualTo(type);
        assertThat(vehicle.getRegistration()).isEqualTo(registration);
        assertThat(vehicle.getHeight()).isEqualTo(height);
        assertThat(vehicle.getWidth()).isEqualTo(width);
    }
}
