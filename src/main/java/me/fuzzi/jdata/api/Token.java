package me.fuzzi.jdata.api;

import java.util.Objects;

public abstract class Token<E extends Token.TokenType> {

    public interface TokenType {}

    private final E type;
    private final String value;
    private final int line, column;

    protected Token(E type, String value, int line, int column) {
        this.type = type;
        this.value = value;
        this.line = line;
        this.column = column;
    }

    public E type() {
        return type;
    }
    public String value() {
        return value;
    }
    public int line() {
        return line;
    }
    public int column() {
        return column;
    }

    @Override
    public String toString() {
        return "Token{" +
                "type=" + type +
                ", value='" + value + '\'' +
                ", line=" + line +
                ", column=" + column +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token<?> token = (Token<?>) o;
        return line == token.line && column == token.column && Objects.equals(type, token.type) && Objects.equals(value, token.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value, line, column);
    }
}