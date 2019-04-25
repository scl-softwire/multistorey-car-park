import com.softwire.training.parking.Floor;
import com.softwire.training.parking.MultiStorey;
import com.softwire.training.parking.ParkingSpace;
import com.softwire.training.parking.Vehicle;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MultiStoreyTest {

    @Test
    public void multiStoreyConsistsOfFloors() {

        // Arrange
        Floor floor1 = new Floor(1);
        Floor floor2 = new Floor(2);

        // Act
        MultiStorey multiStorey = new MultiStorey(floor1, floor2);

        // Assert
        assertThat(multiStorey.getFloors()).contains(floor1, floor2);
    }

    @Test
    public void multiStoreyFindsSpaceForVehicle() {

        // Arrange
        Floor floor1 = new Floor(1);
        ParkingSpace space1 = new ParkingSpace(1, 2.0, 2.0);
        floor1.addParkingSpace(space1);
        Vehicle vehicle = new Vehicle(Vehicle.Type.CAR, "REG1", 1.5, 1.5);

        MultiStorey multiStorey = new MultiStorey(floor1);

        // Act
        ParkingSpace parkingSpace = multiStorey.getNearestSpaceForVehicle(vehicle);

        // Assert
        assertThat(parkingSpace).isEqualTo(space1);
    }

    @Test
    public void multiStoreyReturnsNullIfNoSpaceFound() {

        // Arrange
        Floor floor1 = new Floor(1);
        Vehicle vehicle = new Vehicle(Vehicle.Type.CAR, "REG1", 1.5, 1.5);

        MultiStorey multiStorey = new MultiStorey(floor1);

        // Act
        ParkingSpace parkingSpace = multiStorey.getNearestSpaceForVehicle(vehicle);

        // Assert
        assertThat(parkingSpace).isNull();
    }

    @Test
    public void multiStoreySearchesInOrderOfFloor() {

        // Arrange
        Floor floor1 = new Floor(1);
        Floor floor2 = new Floor(2);
        ParkingSpace space1 = new ParkingSpace(1, 2.0, 2.0);
        ParkingSpace space2 = new ParkingSpace(2, 2.0, 2.0);
        floor1.addParkingSpace(space2);
        floor2.addParkingSpace(space1);
        Vehicle vehicle = new Vehicle(Vehicle.Type.CAR, "REG1", 1.5, 1.5);

        MultiStorey multiStorey = new MultiStorey(floor2, floor1);

        // Act
        ParkingSpace parkingSpace = multiStorey.getNearestSpaceForVehicle(vehicle);

        // Assert
        assertThat(parkingSpace).isEqualTo(space2);
    }

    @Test
    public void multiStoreyIgnoresInvalidSpaceOnLowerFloor() {

        // Arrange
        Floor floor1 = new Floor(1);
        Floor floor2 = new Floor(2);
        Floor floor3 = new Floor(3);
        ParkingSpace space1 = new ParkingSpace(1, 2.0, 2.0);
        ParkingSpace space2 = new ParkingSpace(2, 2.0, 2.0);
        ParkingSpace space3 = new ParkingSpace(3, 1.0, 1.0);
        floor1.addParkingSpace(space3);
        floor2.addParkingSpace(space2);
        floor3.addParkingSpace(space1);
        Vehicle vehicle = new Vehicle(Vehicle.Type.CAR, "REG1", 1.5, 1.5);

        MultiStorey multiStorey = new MultiStorey(floor3, floor2, floor1);

        // Act
        ParkingSpace parkingSpace = multiStorey.getNearestSpaceForVehicle(vehicle);

        // Assert
        assertThat(parkingSpace).isEqualTo(space2);
    }

    @Test
    public void multiStoreyIgnoresOccupiedSpaceOnLowerFloor() {

        // Arrange
        Floor floor1 = new Floor(1);
        Floor floor2 = new Floor(2);
        Floor floor3 = new Floor(3);
        ParkingSpace space1 = new ParkingSpace(1, 2.0, 2.0);
        ParkingSpace space2 = new ParkingSpace(2, 2.0, 2.0);
        ParkingSpace space3 = new ParkingSpace(3, 2.0, 2.0);
        floor1.addParkingSpace(space3);
        floor2.addParkingSpace(space2);
        floor3.addParkingSpace(space1);
        Vehicle vehicle1 = new Vehicle(Vehicle.Type.CAR, "REG1", 1.5, 1.5);
        Vehicle vehicle2 = new Vehicle(Vehicle.Type.CAR, "REG2", 1.5, 1.5);
        space3.parkVehicle(vehicle1);

        MultiStorey multiStorey = new MultiStorey(floor3, floor2, floor1);

        // Act
        ParkingSpace parkingSpace = multiStorey.getNearestSpaceForVehicle(vehicle2);

        // Assert
        assertThat(parkingSpace).isEqualTo(space2);
    }

    @Test
    public void multiStoreyFindsSpaceContainingParticularVehicle() {

        // Arrange
        Floor floor1 = new Floor(1);
        Floor floor2 = new Floor(2);
        ParkingSpace space1 = new ParkingSpace(1, 2.0, 2.0);
        ParkingSpace space2 = new ParkingSpace(2, 2.0, 2.0);
        floor1.addParkingSpace(space2);
        floor2.addParkingSpace(space1);
        Vehicle vehicle = new Vehicle(Vehicle.Type.CAR, "REG1", 1.5, 1.5);
        space1.parkVehicle(vehicle);

        MultiStorey multiStorey = new MultiStorey(floor2, floor1);

        // Act
        ParkingSpace parkingSpace = multiStorey.getSpaceContainingVehicleWithRegistration(vehicle.getRegistration());

        // Assert
        assertThat(parkingSpace).isEqualTo(space1);
    }

    @Test
    public void multiStoreyReturnsNullIfVehicleNotFound() {

        // Arrange
        Floor floor1 = new Floor(1);
        Floor floor2 = new Floor(2);
        ParkingSpace space1 = new ParkingSpace(1, 2.0, 2.0);
        ParkingSpace space2 = new ParkingSpace(2, 2.0, 2.0);
        floor1.addParkingSpace(space2);
        floor2.addParkingSpace(space1);
        Vehicle vehicle1 = new Vehicle(Vehicle.Type.CAR, "REG1", 1.5, 1.5);
        Vehicle vehicle2 = new Vehicle(Vehicle.Type.CAR, "REG2", 1.5, 1.5);
        space1.parkVehicle(vehicle1);

        MultiStorey multiStorey = new MultiStorey(floor2, floor1);

        // Act
        ParkingSpace parkingSpace = multiStorey.getSpaceContainingVehicleWithRegistration(vehicle2.getRegistration());

        // Assert
        assertThat(parkingSpace).isNull();
    }
}
