package com.afkl.cases.df.model;

public class AirportDetailVO implements java.io.Serializable{
	
	private static final long serialVersionUID = -6411062094777532151L;
	private String code;
	private String name;
	private String description;
	private CoordinatesVO coordinates;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CoordinatesVO getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(CoordinatesVO coordinates) {
		this.coordinates = coordinates;
	}
}
