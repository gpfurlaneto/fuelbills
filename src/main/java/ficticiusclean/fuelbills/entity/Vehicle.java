package ficticiusclean.fuelbills.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "vehicles")
public class Vehicle {

	@Id
    @SequenceGenerator(name = "vehicle_id_generator", sequenceName = "vehicle_id_seq")
    @GeneratedValue(generator = "vehicle_id_generator")
    private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Date getFactoryDate() {
		return factoryDate;
	}

	public void setFactoryDate(Date factoryDate) {
		this.factoryDate = factoryDate;
	}

	public Double getStreetAverageFuel() {
		return streetAverageFuel;
	}

	public void setStreetAverageFuel(Double streetAverageFuel) {
		this.streetAverageFuel = streetAverageFuel;
	}

	public Double getRoadAverageFuel() {
		return roadAverageFuel;
	}

	public void setRoadAverageFuel(Double roadAverageFuel) {
		this.roadAverageFuel = roadAverageFuel;
	}

	@NotEmpty
	@Column(nullable = false)
    private String name;
	
	@NotEmpty
	@Column(nullable = false)
    private String brand;
	
	@NotEmpty
	@Column(nullable = false)
    private String model;
	
	@NotNull
	@Column(nullable = false)
    private Date factoryDate;
	
	@NotNull
	@Column(nullable = false)
	private Double streetAverageFuel;
	
	@NotNull
	@Column(nullable = false)
	private Double roadAverageFuel;

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", name=" + name + ", brand=" + brand + ", model=" + model + ", factoryDate="
				+ factoryDate + ", streetAverageFuel=" + streetAverageFuel + ", roadAverageFuel=" + roadAverageFuel
				+ "]";
	}
	
	
}
