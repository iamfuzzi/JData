package me.fuzzi.jdata.api;

import me.fuzzi.jdata.data.DataElement;

import java.util.Collections;
import java.util.List;

public abstract class Parser<T extends Token<?>, L extends Lexer<T>> {

    protected final List<T> tokens;

    public Parser(L lexer) {
        this.tokens = Collections.unmodifiableList(lexer.tokens());
    }

    public abstract DataElement parse();
}