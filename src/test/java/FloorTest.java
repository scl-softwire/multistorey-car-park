import com.softwire.training.parking.Floor;
import com.softwire.training.parking.ParkingSpace;
import com.softwire.training.parking.Vehicle;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FloorTest {

    @Test
    public void floorsHaveAFloorNumber() {

        // Arrange
        int floorNumber = 5;

        // Act
        Floor floor = new Floor(floorNumber);

        // Assert
        assertThat(floor.getFloorNumber()).isEqualTo(floorNumber);
    }

    @Test
    public void floorsCanAddParkingSpaces() {

        // Arrange
        Floor floor = new Floor(1);
        ParkingSpace space1 = new ParkingSpace(123, 2.0, 2.0);
        ParkingSpace space2 = new ParkingSpace(145, 2.0, 2.0);

        // Act
        floor.addParkingSpace(space1);

        // Assert
        assertThat(floor.getParkingSpaces()).contains(space1);
        assertThat(floor.getParkingSpaces()).doesNotContain(space2);
    }

    @Test
    public void floorTracksCapacity() {

        // Arrange
        Floor floor = new Floor(1);
        ParkingSpace space1 = new ParkingSpace(123, 2.0, 2.0);
        ParkingSpace space2 = new ParkingSpace(145, 2.0, 2.0);

        // Act
        floor.addParkingSpace(space1);
        floor.addParkingSpace(space2);

        // Assert
        assertThat(floor.getCapacity()).isEqualTo(2);
    }

    @Test
    public void floorTracksFreeSpaces() {

        // Arrange
        Floor floor = new Floor(1);
        ParkingSpace space1 = new ParkingSpace(123, 2.0, 2.0);
        ParkingSpace space2 = new ParkingSpace(145, 2.0, 2.0);
        Vehicle vehicle = new Vehicle(Vehicle.Type.CAR, "REG", 1.5, 1.5);

        // Act
        floor.addParkingSpace(space1);
        floor.addParkingSpace(space2);
        space1.parkVehicle(vehicle);

        // Assert
        assertThat(floor.getNumberOfFreeSpaces()).isEqualTo(1);
    }

}
