package me.fuzzi.jdata.api;

import me.fuzzi.jdata.data.DataElement;

public abstract class Serializer<P extends Pretty<?>> {

    protected final P pretty;

    public Serializer(P pretty) {
        this.pretty = pretty;
    }

    public abstract String serialize(DataElement element);
}