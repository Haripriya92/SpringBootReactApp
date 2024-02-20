package org.launchcode.givewise.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Table(name = "products")
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "product_name", length = 900)
    private String productName;

    @Column(name = "product_description", length = 900)
    private String productDescription;

    @Column(name = "image", length = 900)
    private String image;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "company_website", length = 900)
    private String companyWebsite;
}
