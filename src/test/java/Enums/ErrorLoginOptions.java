package Enums;

import Interfaces.Options;

// TODO: add JavaDoc
public enum ErrorLoginOptions implements Options {
    ERRORLOGIN("ErrorLogin");

    private String value;

    ErrorLoginOptions(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
