package ficticiusclean.fuelbills.utils;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import ficticiusclean.fuelbills.entity.Vehicle;

public class TestHelper {
    public static Vehicle buildVehicle() {
    	Random random = new Random();
        String uuid = UUID.randomUUID().toString();
        Vehicle vehicle = new Vehicle();
		vehicle.setBrand(uuid + "brand");
		vehicle.setModel(uuid + "model");
		vehicle.setName(uuid + "name");
		vehicle.setFactoryDate(new Date());
		vehicle.setRoadAverageFuel(random.nextDouble());
		vehicle.setStreetAverageFuel(random.nextDouble());
		return vehicle;
    }

    public static Vehicle buildVehicleWithId() {
        Random random = new Random();
        Vehicle vehicle = buildVehicle();
        vehicle.setId(random.nextLong());
        return vehicle;
    }
}