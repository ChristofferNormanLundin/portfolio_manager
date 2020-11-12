package com.stock.portfoliomanager.types;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Builder
@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @NotNull
    private int portfolioId;
    @NotNull
    private LocalDate transactionDate;
    @NotNull
    private String stockName;
    @NotNull
    private int quantity;
    @NotNull
    private double payedAmount;
    @NotNull
    private double sellPrice;
    @NotNull
    private TransactionTypes transactionType;
}
