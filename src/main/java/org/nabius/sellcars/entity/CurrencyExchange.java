package org.nabius.sellcars.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity
@Getter
@Setter

public class CurrencyExchange {
    @Id
    private int id;

    @Temporal(TemporalType.DATE)
    private Date date;
    @ManyToOne
    @JoinColumn(name = "currency_code")
    private Currency currency;

    private double buyAmount;
    private double sellAmount;

}
