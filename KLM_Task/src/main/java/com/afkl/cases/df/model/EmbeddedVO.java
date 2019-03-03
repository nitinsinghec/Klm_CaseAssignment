package com.afkl.cases.df.model;

import java.util.List;

public class EmbeddedVO implements java.io.Serializable{
	private static final long serialVersionUID = 6467060126527613338L;
	private List<AirportVO> locations;

	public List<AirportVO> getLocations() {
		return locations;
	}

	public void setLocations(List<AirportVO> locations) {
		this.locations = locations;
	}

}
