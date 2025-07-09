package me.fuzzi.jdata.data;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class DataObject implements DataElement {

    private final Map<String, DataElement> storage;

    public DataObject() { this.storage = new LinkedHashMap<>(); }
    public DataObject(Map<String, DataElement> map) { this.storage = new LinkedHashMap<>(map); }
    public DataObject(DataObject other) { this.storage = new LinkedHashMap<>(other.storage); }

    public void put(String key, DataElement value) { storage.put(key, value); }

    public DataElement get(String key) { return storage.get(key); }

    public DataElement remove(String key) { return storage.remove(key); }

    public boolean contains(String key) { return storage.containsKey(key); }

    public int size() { return storage.size(); }
    public boolean isEmpty() { return storage.isEmpty(); }
    public void clear() { storage.clear(); }

    public Set<String> keys() { return storage.keySet(); }
    public Map<String, DataElement> toMap() { return new LinkedHashMap<>(storage); }

    public void forEach(BiConsumer<? super String, ? super DataElement> action) { storage.forEach(action); }

    @Override
    public String toString() {
        return storage.entrySet().stream()
                .map(entry -> String.format("\"%s\":%s", entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(", ", "{", "}"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataObject that = (DataObject) o;
        return Objects.equals(storage, that.storage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storage);
    }
}