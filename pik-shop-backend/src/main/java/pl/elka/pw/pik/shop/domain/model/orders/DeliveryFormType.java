package pl.elka.pw.pik.shop.domain.model.orders;

import java.math.BigDecimal;

public enum DeliveryFormType {
    PERSONAL_COLLECTION("Odbiór osobisty", BigDecimal.ZERO),
    COURIER("Przesyłka kurierska", new BigDecimal("14.00"));

    private BigDecimal price;
    private String displayText;

    DeliveryFormType(String displayText, BigDecimal price) {
        this.displayText = displayText;
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDisplayText() {
        return displayText;
    }
}
