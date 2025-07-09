package me.fuzzi.jdata.json;

import me.fuzzi.jdata.api.Pretty;

public class JsonPretty extends Pretty<JsonPretty> {

    public static final JsonPretty RAW = new Builder().build();
    public static final JsonPretty COMPACT = new Builder()
            .newLines(false)
            .indent("")
            .afterArrayComma(" ")
            .afterArrayStart(" ")
            .beforeArrayEnd(" ")
            .afterObjectComma(" ")
            .afterObjectColon(" ")
            .afterObjectStart(" ")
            .beforeObjectEnd(" ")
            .build();
    public static final JsonPretty FANCY = new Builder()
            .newLines(true)
            .indent("    ")
            .afterObjectColon(" ")
            .build();

    private final String indent;
    private final boolean newLines;

    private final String beforeArrayStart;
    private final String afterArrayStart;
    private final String beforeArrayEnd;
    private final String afterArrayEnd;
    private final String beforeArrayComma;
    private final String afterArrayComma;

    private final String beforeObjectStart;
    private final String afterObjectStart;
    private final String beforeObjectEnd;
    private final String afterObjectEnd;
    private final String beforeObjectComma;
    private final String afterObjectComma;
    private final String beforeObjectColon;
    private final String afterObjectColon;

    private JsonPretty(Builder builder) {

        this.indent = builder.indent;
        this.newLines = builder.newLines;

        this.beforeArrayStart = builder.beforeArrayStart;
        this.afterArrayStart = builder.afterArrayStart;
        this.beforeArrayEnd = builder.beforeArrayEnd;
        this.afterArrayEnd = builder.afterArrayEnd;
        this.beforeArrayComma = builder.beforeArrayComma;
        this.afterArrayComma = builder.afterArrayComma;

        this.beforeObjectStart = builder.beforeObjectStart;
        this.afterObjectStart = builder.afterObjectStart;
        this.beforeObjectEnd = builder.beforeObjectEnd;
        this.afterObjectEnd = builder.afterObjectEnd;
        this.beforeObjectComma = builder.beforeObjectComma;
        this.afterObjectComma = builder.afterObjectComma;
        this.beforeObjectColon = builder.beforeObjectColon;
        this.afterObjectColon = builder.afterObjectColon;
    }

    String indent() { return indent; }
    boolean newLines() { return newLines; }

    String beforeArrayStart() { return beforeArrayStart; }
    String afterArrayStart() { return afterArrayStart; }
    String beforeArrayEnd() { return beforeArrayEnd; }
    String afterArrayEnd() { return afterArrayEnd; }
    String beforeArrayComma() { return beforeArrayComma; }
    String afterArrayComma() { return afterArrayComma; }

    String beforeObjectStart() { return beforeObjectStart; }
    String afterObjectStart() { return afterObjectStart; }
    String beforeObjectEnd() { return beforeObjectEnd; }
    String afterObjectEnd() { return afterObjectEnd; }
    String beforeObjectComma() { return beforeObjectComma; }
    String afterObjectComma() { return afterObjectComma; }
    String beforeObjectColon() { return beforeObjectColon; }
    String afterObjectColon() { return afterObjectColon; }

    public static class Builder implements Pretty.Builder<JsonPretty> {

        private String indent = "";
        private boolean newLines = false;

        private String beforeArrayStart = "";
        private String afterArrayStart = "";
        private String beforeArrayEnd = "";
        private String afterArrayEnd = "";
        private String beforeArrayComma = "";
        private String afterArrayComma = "";

        private String beforeObjectStart = "";
        private String afterObjectStart = "";
        private String beforeObjectEnd = "";
        private String afterObjectEnd = "";
        private String beforeObjectComma = "";
        private String afterObjectComma = "";
        private String beforeObjectColon = "";
        private String afterObjectColon = "";

        public Builder indent(String indent) {
            this.indent = indent;
            return this;
        }
        public Builder newLines(boolean newLines) {
            this.newLines = newLines;
            return this;
        }

        public Builder beforeArrayStart(String beforeArrayStart) {
            this.beforeArrayStart = beforeArrayStart;
            return this;
        }
        public Builder afterArrayStart(String afterArrayStart) {
            this.afterArrayStart = afterArrayStart;
            return this;
        }
        public Builder beforeArrayEnd(String beforeArrayEnd) {
            this.beforeArrayEnd = beforeArrayEnd;
            return this;
        }
        public Builder afterArrayEnd(String afterArrayEnd) {
            this.afterArrayEnd = afterArrayEnd;
            return this;
        }
        public Builder beforeArrayComma(String beforeArrayComma) {
            this.beforeArrayComma = beforeArrayComma;
            return this;
        }
        public Builder afterArrayComma(String afterArrayComma) {
            this.afterArrayComma = afterArrayComma;
            return this;
        }

        public Builder beforeObjectStart(String beforeObjectStart) {
            this.beforeObjectStart = beforeObjectStart;
            return this;
        }
        public Builder afterObjectStart(String afterObjectStart) {
            this.afterObjectStart = afterObjectStart;
            return this;
        }
        public Builder beforeObjectEnd(String beforeObjectEnd) {
            this.beforeObjectEnd = beforeObjectEnd;
            return this;
        }
        public Builder afterObjectEnd(String afterObjectEnd) {
            this.afterObjectEnd = afterObjectEnd;
            return this;
        }
        public Builder beforeObjectComma(String beforeObjectComma) {
            this.beforeObjectComma = beforeObjectComma;
            return this;
        }
        public Builder afterObjectComma(String afterObjectComma) {
            this.afterObjectComma = afterObjectComma;
            return this;
        }
        public Builder beforeObjectColon(String beforeObjectColon) {
            this.beforeObjectColon = beforeObjectColon;
            return this;
        }
        public Builder afterObjectColon(String afterObjectColon) {
            this.afterObjectColon = afterObjectColon;
            return this;
        }

        @Override
        public JsonPretty build() {
            return new JsonPretty(this);
        }
    }
}