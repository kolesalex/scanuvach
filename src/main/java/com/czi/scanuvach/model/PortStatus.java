package com.czi.scanuvach.model;


import lombok.Getter;

public enum PortStatus {
    OPEN("open"), CLOSED("closed");

    PortStatus(String value) {
        this.value = value;
    }

    @Getter
    private String value;
}
