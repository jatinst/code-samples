package com.jatinst.selma.demo.model;

import java.math.BigDecimal;

public class Measurement {
	private BigDecimal temp;
	private BigDecimal humidity;

	public BigDecimal getTemp() {
		return temp;
	}

	public void setTemp(BigDecimal temp) {
		this.temp = temp;
	}

	public BigDecimal getHumidity() {
		return humidity;
	}

	public void setHumidity(BigDecimal humidity) {
		this.humidity = humidity;
	}

}
