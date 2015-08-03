package com.jatinst.selma.demo;

import com.jatinst.selma.demo.model.Measurement;
import com.jatinst.selma.demo.model.MeasurementDto;

import fr.xebia.extras.selma.Field;
import fr.xebia.extras.selma.Mapper;

@Mapper(withCustomFields = {
        @Field({ "com.jatinst.selma.demo.model.MeasurementDto.temp",
                "com.jatinst.selma.demo.model.Measurement.temperature" }),
        @Field({ "com.jatinst.selma.demo.model.MeasurementDto.celsiustemp",
                "com.jatinst.selma.demo.model.Measurement.temperature" }),
        @Field({ "com.jatinst.selma.demo.model.MeasurementDto.fahrentemp",
                "com.jatinst.selma.demo.model.Measurement.temperature" }) }, withCustom = { BigDecimalToDoubleMapper.class })
public interface MeasurementMapper {
    public MeasurementDto getMeasurement(Measurement measure);

}
