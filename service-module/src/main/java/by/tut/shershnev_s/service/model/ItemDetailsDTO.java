package by.tut.shershnev_s.service.model;

import java.math.BigDecimal;

public class ItemDetailsDTO {

    private Long itemId;
    private BigDecimal price;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
