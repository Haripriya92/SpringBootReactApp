package org.launchcode.givewise.request;


import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductRequest {
    private String productName;
    private String productDescription;
    private String image;
    private BigDecimal price;
    private String companyWebsite;
}
