package hu.feladat.elastic.exception;

import java.util.List;

public class ElasticException extends RuntimeException {
    private final List<String> messages;
    public ElasticException(List<String> messages) {
        this.messages = messages;
    }

    public List<String> getMessages() {
        return messages;
    }
}
