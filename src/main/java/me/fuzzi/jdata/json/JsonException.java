package me.fuzzi.jdata.json;

import me.fuzzi.jdata.api.DataException;

public class JsonException extends DataException {

    public JsonException() {}
    public JsonException(String message) {
        super(message);
    }
    public JsonException(Throwable cause) {
        super(cause);
    }
    public JsonException(String message, Throwable cause) {
        super(message, cause);
    }
}