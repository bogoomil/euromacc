package hu.feladat.elastic.dto;

import java.util.List;

public class ErrorDto {
    private List<String> errors;

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
