package com.api.stockcontrol.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "tb_products")
public class Product  implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column
    private String name;

    @Column
    private BigDecimal value;


}
