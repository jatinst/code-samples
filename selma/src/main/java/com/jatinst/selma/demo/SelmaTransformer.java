package com.jatinst.selma.demo;

import java.math.BigDecimal;

import com.jatinst.selma.demo.model.Measurement;
import com.jatinst.selma.demo.model.MeasurementDto;

import fr.xebia.extras.selma.Selma;

public class SelmaTransformer {

    public MeasurementDto transform(Measurement input) {
        MeasurementMapper mapper = Selma.builder(MeasurementMapper.class).build();
        return mapper.getMeasurement(input);
    }

    public static void main(String[] args) {
        System.out.println("Test Selma mapping");
        SelmaTransformer mapper = new SelmaTransformer();
        
        Measurement input = new Measurement();
        input.setTemp(new BigDecimal("72.00"));
        input.setHumidity(new BigDecimal("10"));
        MeasurementDto dto = mapper.transform(input);
        
        System.out.println("No errors in mapping!");
    }
}
