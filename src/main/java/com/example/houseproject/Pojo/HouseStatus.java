package com.example.houseproject.Pojo;

public enum HouseStatus {
    SALE("售卖中"),
    RENT("出租中");

    private final String label;

    HouseStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static boolean isValid(String value) {
        return SALE.label.equals(value) || RENT.label.equals(value);
    }

    public static boolean isRent(String value) {
        return RENT.label.equals(value);
    }
}
