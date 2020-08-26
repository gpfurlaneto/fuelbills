package ficticiusclean.fuelbills.web.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import ficticiusclean.fuebills.entity.User;
//import ficticiusclean.fuebills.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import ficticiusclean.fuelbills.dto.PrevisionCalcParamsDto;
import ficticiusclean.fuelbills.dto.PrevisionDto;
import ficticiusclean.fuelbills.entity.Vehicle;
import ficticiusclean.fuelbills.service.VehicleService;
import ficticiusclean.fuelbills.utils.TestHelper;


@RunWith(SpringRunner.class)
@WebMvcTest(controllers = VehicleController.class)
public class VehicleControllerTests {

    @MockBean
    VehicleService vehicleService;

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    Vehicle existingVehicle ;
    Vehicle newVehicle ;
    Vehicle updateVehicle ;

    @Before
    public void setUp() {
        newVehicle = TestHelper.buildVehicleWithId();
        existingVehicle= TestHelper.buildVehicleWithId();
        updateVehicle = TestHelper.buildVehicleWithId();
    }

    @Test
    public void should_get_all_vehicles() throws Exception {
        given(vehicleService.getAllVehicles()).willReturn(Arrays.asList(existingVehicle, updateVehicle));

        this.mockMvc
                .perform(get("/api/vehicles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void should_create_vehicle() throws Exception {
        given(vehicleService.createVehicle(newVehicle)).willReturn(newVehicle);

        this.mockMvc
                .perform(post("/api/vehicles/")
                		.accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newVehicle))
                ) 
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(existingVehicle.getName())))
                .andExpect(jsonPath("$.brand", is(existingVehicle.getBrand())))
                .andExpect(jsonPath("$.model", is(existingVehicle.getModel())))
                .andExpect(jsonPath("$.roadAverageFuel", is(existingVehicle.getRoadAverageFuel())))
                .andExpect(jsonPath("$.sreetAverageFuel", is(existingVehicle.getStreetAverageFuel())));
                
    }

    @Test
    public void should_calculate_prevision() throws Exception {
    	PrevisionDto prevision = new PrevisionDto(4.0, 5.0, existingVehicle);
    	List<PrevisionDto> result = new ArrayList<PrevisionDto>();
    	result.add(prevision);
    	
    	PrevisionCalcParamsDto previsionParam = new PrevisionCalcParamsDto(1.0, 2.0, 3.0);
        given(vehicleService.calculatePrevision(previsionParam)).willReturn(result);

        this.mockMvc
                .perform(put("/api/users/prevision-calc")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(previsionParam))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

}
