package ficticiusclean.fuelbills.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ficticiusclean.fuelbills.dto.PrevisionCalcParamsDto;
import ficticiusclean.fuelbills.dto.PrevisionDto;
import ficticiusclean.fuelbills.entity.Vehicle;
import ficticiusclean.fuelbills.repo.VehicleRepository;

@Service
@Transactional
public class VehicleService {
    private final VehicleRepository vehicleRepository;

    @Autowired
    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    public Vehicle createVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

	public List<PrevisionDto> calculatePrevision(PrevisionCalcParamsDto previsionCalcParamsDto) {
		List<Vehicle> vehicles = this.getAllVehicles();
		List<PrevisionDto> previsions = new ArrayList<PrevisionDto>();
		
		for(Vehicle vehicle: vehicles){
            Double amounLitersKmStreet = previsionCalcParamsDto.getTotalKmStreet() / vehicle.getStreetAverageFuel();
            Double amounLitersKmRoad = previsionCalcParamsDto.getTotalKmRoad() / vehicle.getRoadAverageFuel();
            Double amounFuelLiters = amounLitersKmRoad + amounLitersKmStreet;
            Double totalFuelValue = amounFuelLiters * previsionCalcParamsDto.getFuelValue();
            previsions.add(new PrevisionDto(amounFuelLiters, totalFuelValue, vehicle));
        }
		
		previsions.sort(new Comparator<PrevisionDto>() {

			@Override
			public int compare(PrevisionDto arg0, PrevisionDto arg1) {
				if(arg0.getAmounFuel() == arg1.getAmounFuel()) {
					return 0;
				}
				return arg0.getAmounFuel() < arg1.getAmounFuel() ? -1 : 1;
			}
		});
		return previsions;
	}
    
}
