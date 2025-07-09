package me.fuzzi.jdata.api;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public abstract class Lexer<T extends Token<?>> {

    protected final String text;
    private final List<T> tokens = new ArrayList<>();

    List<T> tokens() {
        return this.tokens;
    }

    public Lexer(String input) {
        this.text = input;
        tokenize();
    }
    public Lexer(Path path) throws IOException {
        this(Files.readString(path));
    }
    public Lexer(File file) throws IOException {
        this(file.toPath());
    }

    protected void addToken(T token) {
        this.tokens.add(token);
    }

    protected abstract void tokenize();
}