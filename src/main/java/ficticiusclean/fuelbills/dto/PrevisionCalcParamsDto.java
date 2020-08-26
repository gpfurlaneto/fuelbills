package ficticiusclean.fuelbills.dto;

import javax.validation.constraints.NotNull;

public class PrevisionCalcParamsDto {

	@NotNull
	private Double fuelValue;
	
	@NotNull
	private Double totalKmStreet;
	
	@NotNull
	private Double totalKmRoad;
	
	public PrevisionCalcParamsDto(Double fuelValue, Double totalKmStreet, Double totalKmRoad) {
		super();
		this.fuelValue = fuelValue;
		this.totalKmStreet = totalKmStreet;
		this.totalKmRoad = totalKmRoad;
	}
	public Double getFuelValue() {
		return fuelValue;
	}
	public void setFuelValue(Double fuelValue) {
		this.fuelValue = fuelValue;
	}
	public Double getTotalKmStreet() {
		return totalKmStreet;
	}
	public void setTotalKmStreet(Double totalKmStreet) {
		this.totalKmStreet = totalKmStreet;
	}
	public Double getTotalKmRoad() {
		return totalKmRoad;
	}
	public void setTotalKmRoad(Double totalKmRoad) {
		this.totalKmRoad = totalKmRoad;
	}
}
