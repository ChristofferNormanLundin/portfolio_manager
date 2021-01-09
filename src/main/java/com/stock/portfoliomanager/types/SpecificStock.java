package com.stock.portfoliomanager.types;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Builder
@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class SpecificStock {

    private String name;
    private double averageAcquisitionValue;
    private double payed;
    private double sold;
    private double earnings;
    private int stocksLeft;
}
