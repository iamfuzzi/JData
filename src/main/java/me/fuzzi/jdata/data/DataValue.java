package me.fuzzi.jdata.data;

import java.util.Objects;

public class DataValue implements DataElement {

    private final Object value;

    public DataValue(String value) { this.value = value; }
    public DataValue(int value) { this.value = value; }
    public DataValue(double value) { this.value = value; }
    public DataValue(boolean value) { this.value = value; }
    public DataValue() { this.value = null; }

    public static final DataValue TRUE = new DataValue(true);
    public static final DataValue FALSE = new DataValue(false);
    public static final DataValue NULL = new DataValue();

    public static DataValue of(String value) { return new DataValue(value); }
    public static DataValue of(int value) { return new DataValue(value); }
    public static DataValue of(double value) { return new DataValue(value); }
    public static DataValue of(boolean value) { return new DataValue(value); }
    public static DataValue empty() { return new DataValue(); }

    public boolean isString() { return value instanceof String; }
    public boolean isInt() { return value instanceof Integer; }
    public boolean isDouble() { return value instanceof Double; }
    public boolean isNumber() { return value instanceof Number; }
    public boolean isBoolean() { return value instanceof Boolean; }
    public boolean isNull() { return value == null; }

    public String asString() throws NullPointerException {
        if (value == null)
            throw new NullPointerException("Cannot convert null to String");
        return value.toString();
    }
    public int asInt() throws NullPointerException, ClassCastException {
        if (value == null)
            throw new NullPointerException("Cannot convert null to int");
        if (value instanceof Integer)
            return (Integer) value;
        if (value instanceof Number)
            return ((Number) value).intValue();
        throw new ClassCastException(
                String.format("Cannot convert %s to int",
                        value.getClass().getSimpleName()));
    }
    public double asDouble() throws NullPointerException, ClassCastException {
        if (value == null)
            throw new NullPointerException("Cannot convert null to double");
        if (value instanceof Double)
            return (Double) value;
        if (value instanceof Number)
            return ((Number) value).doubleValue();
        throw new ClassCastException(
                String.format("Cannot convert %s to double",
                        value.getClass().getSimpleName()));
    }
    public boolean asBoolean() throws NullPointerException, ClassCastException {
        if (value == null)
            throw new NullPointerException("Cannot convert null to boolean");
        if (value instanceof Boolean)
            return (Boolean) value;
        throw new ClassCastException(
                String.format("Cannot convert %s to boolean",
                        value.getClass().getSimpleName()));
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataValue value1 = (DataValue) o;
        return Objects.equals(value, value1.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}