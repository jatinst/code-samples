package com.jatinst.selma.demo;

import java.math.BigDecimal;

public class BigDecimalToDoubleMapper {
    public double getDouble(BigDecimal input) {
        return input.doubleValue();
    }
}
