package by.tut.shershnev_s.service.model;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ItemDTO {
    private Long id;
    @NotEmpty(message = "Cant be empty")
    private String name;
    @NotEmpty(message = "Cant be empty")
    private String description;
    @NotNull(message = "Cant be empty")
    @DecimalMin(value = "0.0", inclusive = false)
    @DecimalMax(value = "999.99", inclusive = true)
    @Digits(integer = 5, fraction = 2)
    private BigDecimal price;
    private Long shopId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }
}
