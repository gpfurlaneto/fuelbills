package ficticiusclean.fuelbills.dto;

import ficticiusclean.fuelbills.entity.Vehicle;

public class PrevisionDto {

	private String name;
	private String brand;
	private String model;
	private String year;
	private Double amounFuel; 
	private Double totalFuelValue;
	
	public PrevisionDto(Double amounFuel, Double totalFuelValue, Vehicle vehicle) {
		super();
		this.amounFuel = amounFuel;
		this.totalFuelValue = totalFuelValue;
		this.year = String.valueOf(vehicle.getFactoryDate().getYear());
		this.name = vehicle.getName();
		this.model = vehicle.getModel();
		this.brand = vehicle.getBrand();
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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Double getAmounFuel() {
		return amounFuel;
	}

	public void setAmounFuel(Double amounFuel) {
		this.amounFuel = amounFuel;
	}

	public Double getTotalFuelValue() {
		return totalFuelValue;
	}

	public void setTotalFuelValue(Double totalFuelValue) {
		this.totalFuelValue = totalFuelValue;
	}
}