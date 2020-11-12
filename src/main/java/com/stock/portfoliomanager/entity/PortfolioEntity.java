package com.stock.portfoliomanager.entity;

import com.stock.portfoliomanager.types.Portfolio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Builder
@Data
@Component
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Portfolio")
public class PortfolioEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="portfolio_name")
    private String portfolioName;
}
