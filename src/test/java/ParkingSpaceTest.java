import com.softwire.training.parking.ParkingSpace;
import com.softwire.training.parking.Vehicle;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class ParkingSpaceTest {

    @Test
    public void parkingSpaceHasId() {

        // Arrange
        int id = 123;

        // Act
        ParkingSpace parkingSpace = new ParkingSpace(id, 0.0, 0.0);

        // Assert
        assertThat(parkingSpace.getId()).isEqualTo(id);
    }

    @Test
    public void parkingSpaceDoesNotAcceptTooWideVehicle() {

        // Arrange
        ParkingSpace parkingSpace = new ParkingSpace(123, 2.0, 2.0);
        Vehicle vehicle = new Vehicle(Vehicle.Type.CAR, "REG", 1.5, 2.5);

        // Act
        boolean isVehicleAllowed = parkingSpace.isVehicleAllowed(vehicle);

        // Assert
        assertThat(isVehicleAllowed).isFalse();
    }

    @Test
    public void parkingSpaceDoesNotAcceptTooHighVehicle() {

        // Arrange
        ParkingSpace parkingSpace = new ParkingSpace(123, 2.0, 2.0);
        Vehicle vehicle = new Vehicle(Vehicle.Type.CAR, "REG", 2.5, 1.5);

        // Act
        boolean isVehicleAllowed = parkingSpace.isVehicleAllowed(vehicle);

        // Assert
        assertThat(isVehicleAllowed).isFalse();
    }

    @Test
    public void parkingSpaceWithTypeRestrictionDoesNotAcceptVehicleOfWrongType() {

        // Arrange
        ParkingSpace parkingSpace = new ParkingSpace(123, 2.0, 2.0, Vehicle.Type.CAR);
        Vehicle vehicle = new Vehicle(Vehicle.Type.VAN, "REG", 1.5, 1.5);

        // Act
        boolean isVehicleAllowed = parkingSpace.isVehicleAllowed(vehicle);

        // Assert
        assertThat(isVehicleAllowed).isFalse();
    }

    @Test
    public void parkingSpaceAcceptsVehicleUnderRestrictions() {

        // Arrange
        ParkingSpace parkingSpace = new ParkingSpace(123, 2.0, 2.0, Vehicle.Type.CAR);
        Vehicle vehicle = new Vehicle(Vehicle.Type.CAR, "REG", 1.5, 1.5);

        // Act
        boolean isVehicleAllowed = parkingSpace.isVehicleAllowed(vehicle);

        // Assert
        assertThat(isVehicleAllowed).isTrue();
    }

    @Test
    public void parkingSpaceAcceptsVehicleExactlyOnRestrictions() {

        // Arrange
        ParkingSpace parkingSpace = new ParkingSpace(123, 2.0, 2.0, Vehicle.Type.CAR);
        Vehicle vehicle = new Vehicle(Vehicle.Type.CAR, "REG", 2.0, 2.0);

        // Act
        boolean isVehicleAllowed = parkingSpace.isVehicleAllowed(vehicle);

        // Assert
        assertThat(isVehicleAllowed).isTrue();
    }

    @Test
    public void parkingSpaceAllowsVehicleToBeParked() {

        // Arrange
        ParkingSpace parkingSpace = new ParkingSpace(123, 2.0, 2.0);
        Vehicle vehicle = new Vehicle(Vehicle.Type.CAR, "REG", 1.5, 1.5);

        // Act
        parkingSpace.parkVehicle(vehicle);

        // Assert
        assertThat(parkingSpace.isOccupied()).isTrue();
        assertThat(parkingSpace.getParkedVehicle()).isEqualTo(vehicle);
    }

    @Test
    public void parkingSpaceCanBeVacated() {

        // Arrange
        ParkingSpace parkingSpace = new ParkingSpace(123, 2.0, 2.0);
        Vehicle vehicle = new Vehicle(Vehicle.Type.CAR, "REG", 1.5, 1.5);

        // Act
        parkingSpace.parkVehicle(vehicle);
        parkingSpace.vacate();

        // Assert
        assertThat(parkingSpace.isOccupied()).isFalse();
    }

    @Test(expected = IllegalArgumentException.class)
    public void parkingSpaceThrowsIllegalArgumentExceptionWhenParkingInvalidVehicle() {

        // Arrange
        ParkingSpace parkingSpace = new ParkingSpace(123, 2.0, 2.0);
        Vehicle vehicle = new Vehicle(Vehicle.Type.CAR, "REG", 2.5, 2.5);

        // Act
        parkingSpace.parkVehicle(vehicle);
    }

    @Test(expected = IllegalStateException.class)
    public void parkingSpaceThrowsIllegalStateExceptionWhenParkingAndAlreadyOccupied() {

        // Arrange
        ParkingSpace parkingSpace = new ParkingSpace(123, 2.0, 2.0);
        Vehicle firstVehicle = new Vehicle(Vehicle.Type.CAR, "REG1", 1.5, 1.5);
        Vehicle secondVehicle = new Vehicle(Vehicle.Type.CAR, "REG2", 1.5, 1.5);

        // Act
        parkingSpace.parkVehicle(firstVehicle);
        parkingSpace.parkVehicle(secondVehicle);
    }
}
