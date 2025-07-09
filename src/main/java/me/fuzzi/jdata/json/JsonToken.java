package me.fuzzi.jdata.json;

import me.fuzzi.jdata.api.Token;

public class JsonToken extends Token<JsonToken.Type> {

    public enum Type implements TokenType {

        S_OBJECT,
        E_OBJECT,

        S_ARRAY,
        E_ARRAY,

        COLON,
        COMMA,

        STRING,
        INT,
        DOUBLE,
        BOOLEAN,
        NULL,

        EOF
    }

    protected JsonToken(Type type, String value, int line, int column) {
        super(type, value, line, column);
    }
}