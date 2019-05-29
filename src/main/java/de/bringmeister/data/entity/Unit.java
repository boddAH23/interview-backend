package de.bringmeister.data.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Unit {
    @JsonProperty("piece")
    PIECE("piece"),
    @JsonProperty("package")
    PACKAGE("package");

    private String name;

    Unit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
