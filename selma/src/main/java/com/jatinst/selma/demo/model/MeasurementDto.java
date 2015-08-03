package com.jatinst.selma.demo.model;

public class MeasurementDto {
    private double temperature;
    private double celsiusTemp;
    private double fahrenTemp;
    private double humidity;

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getCelsiusTemp() {
        return celsiusTemp;
    }

    public void setCelsiusTemp(double celsiusTemp) {
        this.celsiusTemp = celsiusTemp;
    }

    public double getFahrenTemp() {
        return fahrenTemp;
    }

    public void setFahrenTemp(double fahrenTemp) {
        this.fahrenTemp = fahrenTemp;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

}
