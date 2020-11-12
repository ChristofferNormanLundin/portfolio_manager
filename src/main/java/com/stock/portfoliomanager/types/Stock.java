package com.stock.portfoliomanager.types;

import com.sun.istack.NotNull;
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
public class Stock {
    @NotNull
    private String stockName;
    @NotNull
    private int quantity;
    @NotNull
    private double payedAmount;
    @NotNull
    private Double sellPrice;
}
