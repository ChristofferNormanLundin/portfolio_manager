package com.stock.portfoliomanager.entity;

import com.stock.portfoliomanager.types.TransactionTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;

@Builder
@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transaction")
public class TransactionEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="transaction_id")
    private int transactionId;
    @Column(name="portfolio_id")
    private int portfolioId;
    @Column(name="transaction_date")
    private LocalDate transactionDate;
    @Column(name="stock_name")
    private String stockName;
    @Column(name="quantity")
    private int quantity;
    @Column(name="payed_amount")
    private double payedAmount;
    @Column(name="sell_price")
    private Double sellPrice;
    @Column(name="created_at")
    private Timestamp createdAt;
    @Column(columnDefinition = "ENUM('BUY', 'SELL')")
    @Enumerated(EnumType.STRING)
    private TransactionTypes transactionType;

}
