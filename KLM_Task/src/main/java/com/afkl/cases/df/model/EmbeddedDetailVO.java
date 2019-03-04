package com.afkl.cases.df.model;

import java.util.List;

public class EmbeddedDetailVO implements java.io.Serializable {
	private static final long serialVersionUID = -7456953444512336561L;
	private List<AirportDetailVO> locations;

	public List<AirportDetailVO> getLocations() {
		return locations;
	}

	public void setLocations(List<AirportDetailVO> locations) {
		this.locations = locations;
	}

}
