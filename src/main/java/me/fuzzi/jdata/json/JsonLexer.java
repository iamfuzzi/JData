package me.fuzzi.jdata.json;

import me.fuzzi.jdata.api.Lexer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class JsonLexer extends Lexer<JsonToken> {

    public JsonLexer(String input) throws JsonException {
        super(input);
    }
    public JsonLexer(Path path) throws JsonException, IOException {
        super(path);
    }
    public JsonLexer(File file) throws JsonException, IOException {
        super(file);
    }

    private int pos, line, column;

    @Override
    protected void tokenize() {

        pos = 0;
        line = column = 1;

        while (pos < text.length()) {

            char current = text.charAt(pos);

            if (Character.isWhitespace(current)) {
                if (current == '\n') {
                    line++;
                    column = 1;
                } else column++;
                pos++;
                continue;
            }

            switch (current) {

                case '{' -> {
                    addToken(new JsonToken(JsonToken.Type.S_OBJECT, "{", line, column++));
                    pos++;
                }
                case '}' -> {
                    addToken(new JsonToken(JsonToken.Type.E_OBJECT, "}", line, column++));
                    pos++;
                }
                case '[' -> {
                    addToken(new JsonToken(JsonToken.Type.S_ARRAY, "[", line, column++));
                    pos++;
                }
                case ']' -> {
                    addToken(new JsonToken(JsonToken.Type.E_ARRAY, "]", line, column++));
                    pos++;
                }
                case ':' -> {
                    addToken(new JsonToken(JsonToken.Type.COLON, ":", line, column++));
                    pos++;
                }
                case ',' -> {
                    addToken(new JsonToken(JsonToken.Type.COMMA, ",", line, column++));
                    pos++;
                }
                case '"' -> string();

                default -> {
                    if (Character.isDigit(current) || current == '-')
                        number();
                    else if (Character.isLetter(current))
                        keyword();
                    else
                        throw new JsonException("Unexpected character: '" + current + "' at line " + line + ", column " + column);
                }
            }
        }

        addToken(new JsonToken(JsonToken.Type.EOF, "", line, column));
    }

    private void string() {

        int startLine = line;
        int startColumn = column++;
        pos++;

        StringBuilder sb = new StringBuilder();
        while (pos < text.length()) {

            char current = text.charAt(pos);

            if (current == '"') {
                pos++;
                column++;
                addToken(new JsonToken(JsonToken.Type.STRING, sb.toString(), startLine, startColumn));
                return;
            } else if (current == '\\') {

                pos++;
                column++;
                if (pos >= text.length())
                    throw new JsonException("Unterminated escape sequence at line " + line + ", column " + column);

                char escape = text.charAt(pos++);
                switch (escape) {
                    case '"' -> sb.append('"');
                    case '\\' -> sb.append('\\');
                    case '/' -> sb.append('/');
                    case 'b' -> sb.append('\b');
                    case 'f' -> sb.append('\f');
                    case 'n' -> sb.append('\n');
                    case 'r' -> sb.append('\r');
                    case 't' -> sb.append('\t');
                    default -> throw new JsonException("Invalid escape sequence: \\" + escape + " at line " + line + ", column " + column);
                }
                column++;

            } else if (current == '\n') {
                throw new JsonException("Detected newline in string at line " + line);
            } else {
                sb.append(current);
                pos++;
                column++;
            }
        }

        throw new JsonException("Unterminated string at line " + line + ", column " + column);
    }

    private void number() {

        int startLine = line;
        int startColumn = column;

        StringBuilder sb = new StringBuilder();
        if (text.charAt(pos) == '-') {
            sb.append('-');
            pos++;
            column++;
        }

        if (pos >= text.length() || !Character.isDigit(text.charAt(pos)))
            throw new JsonException("Invalid number format at line " + line + ", column " + column);


        while (pos < text.length() && Character.isDigit(text.charAt(pos))) {
            sb.append(text.charAt(pos++));
            column++;
        }

        if (pos < text.length() && text.charAt(pos) == '.') {
            sb.append('.');
            pos++;
            column++;

            if (pos >= text.length() || !Character.isDigit(text.charAt(pos)))
                throw new JsonException("Invalid number format: expected digit after '.' at line " + line + ", column " + column);

            while (pos < text.length() && Character.isDigit(text.charAt(pos))) {
                sb.append(text.charAt(pos++));
                column++;
            }

            addToken(new JsonToken(JsonToken.Type.DOUBLE, sb.toString(), startLine, startColumn));
        }
        else addToken(new JsonToken(JsonToken.Type.INT, sb.toString(), startLine, startColumn));
    }

    private void keyword() {

        int startLine = line;
        int startColumn = column;

        StringBuilder sb = new StringBuilder();
        while (pos < text.length() && Character.isLetter(text.charAt(pos))) {
            sb.append(text.charAt(pos++));
            column++;
        }

        String value = sb.toString();
        JsonToken.Type type = switch (value) {
            case "true", "false" -> JsonToken.Type.BOOLEAN;
            case "null" -> JsonToken.Type.NULL;
            default -> throw new JsonException("Unexpected keyword: '" + value + "' at line " + startLine + ", column " + startColumn);
        };

        addToken(new JsonToken(type, value, startLine, startColumn));
    }
}