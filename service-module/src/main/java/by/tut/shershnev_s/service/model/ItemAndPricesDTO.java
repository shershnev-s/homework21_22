package by.tut.shershnev_s.service.model;

import java.math.BigDecimal;

public class ItemAndPricesDTO {

    private String name;
    private BigDecimal priceMin;
    private BigDecimal priceMax;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(BigDecimal priceMin) {
        this.priceMin = priceMin;
    }

    public BigDecimal getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(BigDecimal priceMax) {
        this.priceMax = priceMax;
    }
}
