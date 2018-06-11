package ru.infop.complex.dataprocessor.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Created by lconnected on 11/06/2018.
 */
public enum CheckType {
    WANTED("wanted"), HISTORY("history");

    private String checkTypeString;

    @JsonCreator
    CheckType(String checkTypeString) {
        this.checkTypeString = checkTypeString;
    }

    @JsonValue
    @Override
    public String toString() {
        return checkTypeString;
    }
}
