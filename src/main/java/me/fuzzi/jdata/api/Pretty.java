package me.fuzzi.jdata.api;

public abstract class Pretty<P extends Pretty<?>> {
    public interface Builder<P> {
        P build();
    }
}