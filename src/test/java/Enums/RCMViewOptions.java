package Enums;

import Interfaces.Options;

// TODO: add JavaDoc
public enum RCMViewOptions implements Options {
    NUMBER("Number"),
    RWTRACK("RWTrack"),
    DATE("Date"),
    WORK("Work"),
    COMMENTS("Comments");

    private String value;

    RCMViewOptions(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
