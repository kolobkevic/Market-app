package ru.kolobkevic.market.api.dtos;

public class StringResponse {
    private String value;

    public StringResponse() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public StringResponse(String value) {
        this.value = value;
    }
}
