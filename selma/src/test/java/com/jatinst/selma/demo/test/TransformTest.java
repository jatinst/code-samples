package com.jatinst.selma.demo.test;

import java.math.BigDecimal;

import junit.framework.Assert;

import org.junit.Test;

import com.jatinst.selma.demo.SelmaTransformer;
import com.jatinst.selma.demo.model.Measurement;
import com.jatinst.selma.demo.model.MeasurementDto;

public class TransformTest {

    @Test
    public void selmaTransform() {

        Measurement input = new Measurement();
        input.setTemp(new BigDecimal("72.00"));
        input.setHumidity(new BigDecimal("10"));
        MeasurementDto dto = new SelmaTransformer().transform(input);

        Assert.assertEquals("Not mapped correctly", input.getTemp().doubleValue(), dto.getTemperature());
    }
}
