package ficticiusclean.fuelbills.web.controller;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import ficticiusclean.fuelbills.dto.PrevisionCalcParamsDto;
import ficticiusclean.fuelbills.dto.PrevisionDto;
import ficticiusclean.fuelbills.entity.Vehicle;
import ficticiusclean.fuelbills.repo.VehicleRepository;
import ficticiusclean.fuelbills.utils.BaseIntegrationTest;
import ficticiusclean.fuelbills.utils.TestHelper;

public class VehivleControllerIT extends BaseIntegrationTest {

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    TestRestTemplate restTemplate;

    Vehicle existingVehicle;
    Vehicle newVehicle;
    Vehicle updateVehicle;

    @Before
    public void setUp() {
        newVehicle = TestHelper.buildVehicle();

        existingVehicle = TestHelper.buildVehicle();
        existingVehicle= vehicleRepository.save(existingVehicle);

        updateVehicle = TestHelper.buildVehicle();
        updateVehicle = vehicleRepository.save(updateVehicle);
    }

    @After
    public void tearDown() {
        if(newVehicle.getId() != null) {
            vehicleRepository.deleteById(newVehicle.getId());
        }
        vehicleRepository.deleteAll(vehicleRepository.findAllById(asList(existingVehicle.getId(), updateVehicle.getId())));
    }

    @Test
    public void should_get_all_vehicles() {
        ResponseEntity<Vehicle[]> responseEntity = restTemplate.getForEntity("/api/vehicles", Vehicle[].class);
        List<Vehicle> vehicles= asList(responseEntity.getBody());
        assertThat(vehicles).isNotEmpty();
    }
    
    @Test
    public void should_create_user() {
        HttpEntity<Vehicle> request = new HttpEntity<>(newVehicle);
        ResponseEntity<Vehicle> responseEntity = restTemplate.postForEntity("/api/vehicles", request, Vehicle.class);
        Vehicle savedVehicle = responseEntity.getBody();
        assertThat(savedVehicle.getId()).isNotNull();
    }
    
    @SuppressWarnings("unchecked")
	@Test
    public void should_calculate_prevision() {
    	HttpEntity<PrevisionCalcParamsDto> request = new HttpEntity<>(new PrevisionCalcParamsDto(2.0, 1.0, 2.0));
        ResponseEntity<List> responseEntity = restTemplate.postForEntity("/api/vehicles/prevision-calc", request, List.class);
        List<PrevisionDto> prevision = (List<PrevisionDto>) responseEntity.getBody();
        assertEquals(prevision.size(), 2);
    }
}
