package ficticiusclean.fuelbills.web.controller;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ficticiusclean.fuelbills.dto.PrevisionCalcParamsDto;
import ficticiusclean.fuelbills.dto.PrevisionDto;
import ficticiusclean.fuelbills.entity.Vehicle;
import ficticiusclean.fuelbills.service.VehicleService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/vehicles")
@Slf4j
public class VehicleController {

	private final VehicleService vehiclerService;

    @Autowired
    public VehicleController(VehicleService vehiclerService) {
        this.vehiclerService = vehiclerService;
    }
    
	@GetMapping("")
    public List<Vehicle> getVehicles() {
        
        return vehiclerService.getAllVehicles();
    }
	
	@PostMapping("")
    @ResponseStatus(CREATED)
    public Vehicle createVehicle(@RequestBody @Valid Vehicle vehicle) {
        return vehiclerService.createVehicle(vehicle);
    }
	
	@PostMapping("/prevision-calc")
	public List<PrevisionDto> calculatePrevision(@RequestBody @Valid PrevisionCalcParamsDto previsionCalcParamsDto) {
		return vehiclerService.calculatePrevision(previsionCalcParamsDto);
	}
	
}
