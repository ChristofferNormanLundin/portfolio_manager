package com.stock.portfoliomanager.types;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.List;

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
    private TransactionTypes transactionType;
    @NotNull
    private List<Stock> stocks;
}
