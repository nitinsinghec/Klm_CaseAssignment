package com.afkl.cases.df.model;

public class EmbeddedWrapperVO implements java.io.Serializable{
	private static final long serialVersionUID = -5830958680244342734L;
	private EmbeddedVO _embedded;

	public EmbeddedVO get_embedded() {
		return _embedded;
	}

	public void set_embedded(EmbeddedVO _embedded) {
		this._embedded = _embedded;
	}	
}
