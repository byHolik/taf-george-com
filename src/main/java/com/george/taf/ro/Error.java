package com.george.taf.ro;

import java.util.Objects;

public class Error {
    private String status;
    private String code;
    private String description;
    private int challenge = 0;

    private Error() {
    }

    public Error(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public Error(String status, String errorCode, String errorDescription) {
        this.status = status;
        this.code = errorCode;
        this.description = errorDescription;
    }

    public Error(String status, String errorCode, String errorDescription, int challenge) {
        this.status = status;
        this.code = errorCode;
        this.description = errorDescription;
        this.challenge = challenge;
    }

    @Override
    public String toString() {
        return "GeneralError{" +
                "status='" + status + '\'' +
                ", errorCode='" + code + '\'' +
                ", errorDescription='" + description + '\'' +
                ", challenge=" + challenge +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Error that = (Error) o;
        return challenge == that.challenge && Objects.equals(status, that.status) && Objects.equals(code, that.code) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, code, description, challenge);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
