package me.fuzzi.jdata.data;

public interface DataElement {

    default boolean isValue() { return this instanceof DataValue; }
    default boolean isObject() { return this instanceof DataObject; }
    default boolean isArray() { return this instanceof DataArray; }

    default DataValue asValue() throws ClassCastException { return (DataValue) this; }
    default DataObject asObject() throws ClassCastException { return (DataObject) this; }
    default DataArray asArray() throws ClassCastException { return (DataArray) this; }
}