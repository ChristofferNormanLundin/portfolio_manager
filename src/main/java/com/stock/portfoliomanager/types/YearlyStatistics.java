package com.stock.portfoliomanager.types;

import com.stock.portfoliomanager.entity.PortfolioEntity;
import com.stock.portfoliomanager.entity.TransactionEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class YearlyStatistics {

    private PortfolioEntity portfolio;
    private List<TransactionEntity> transactions;

}
