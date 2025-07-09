package me.fuzzi.jdata.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class DataArray implements DataElement, Iterable<DataElement> {

    private final List<DataElement> storage;

    public DataArray() { this.storage = new ArrayList<>(); }
    public DataArray(List<DataElement> storage) { this.storage = new ArrayList<>(storage); }
    public DataArray(DataArray other) { this.storage = new ArrayList<>(other.storage); }

    public void add(DataElement element) { storage.add(element); }
    public void add(int index, DataElement element) { storage.add(index, element); }

    public DataElement get(int index) { return storage.get(index); }
    public DataElement set(int index, DataElement element) { return storage.set(index, element); }

    public DataElement remove(int index) { return storage.remove(index); }
    public boolean remove(DataElement element) { return storage.remove(element); }

    public boolean contains(DataElement element) { return storage.contains(element); }
    public int indexOf(DataElement element) { return storage.indexOf(element); }

    public int size() { return storage.size(); }
    public boolean isEmpty() { return storage.isEmpty(); }
    public void clear() { storage.clear(); }

    public List<DataElement> toList() { return new ArrayList<>(storage); }
    public DataElement[] toArray() { return storage.toArray(new DataElement[0]); }

    @Override
    public Iterator<DataElement> iterator() { return storage.iterator(); }

    @Override
    public void forEach(Consumer<? super DataElement> action) { storage.forEach(action); }

    @Override
    public String toString() {
        return storage.stream()
                .map(DataElement::toString)
                .collect(Collectors.joining(", ", "[", "]"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataArray that = (DataArray) o;
        return Objects.equals(storage, that.storage);
    }
    @Override
    public int hashCode() {
        return Objects.hash(storage);
    }
}