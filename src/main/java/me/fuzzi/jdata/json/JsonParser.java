package me.fuzzi.jdata.json;

import me.fuzzi.jdata.api.*;
import me.fuzzi.jdata.data.DataArray;
import me.fuzzi.jdata.data.DataElement;
import me.fuzzi.jdata.data.DataObject;
import me.fuzzi.jdata.data.DataValue;

public class JsonParser extends Parser<JsonToken, JsonLexer> {

    public JsonParser(JsonLexer lexer) {
        super(lexer);
    }

    private int pos;

    @Override
    public DataElement parse() {
        pos = 0;
        if (tokens.isEmpty()) throw new JsonException("No tokens to parse");
        return parseValue();
    }

    private DataElement parseValue() {

        JsonToken token = tokens.get(pos);

        if (token.type().equals(JsonToken.Type.STRING)) {
            pos++;
            return new DataValue(token.value());
        } else if (token.type().equals(JsonToken.Type.INT)) {
            pos++;
            return new DataValue(Integer.parseInt(token.value()));
        } else if (token.type().equals(JsonToken.Type.DOUBLE)) {
            pos++;
            return new DataValue(Double.parseDouble(token.value()));
        } else if (token.type().equals(JsonToken.Type.BOOLEAN)) {
            pos++;
            return new DataValue(Boolean.parseBoolean(token.value()));
        } else if (token.type().equals(JsonToken.Type.NULL)) {
            pos++;
            return new DataValue();
        }
        else if (token.type().equals(JsonToken.Type.S_OBJECT)) return parseObject();
        else if (token.type().equals(JsonToken.Type.S_ARRAY)) return parseArray();
        else throw new JsonException("Unexpected token type: " + token.type() + " at line " + token.line());
    }

    private DataObject parseObject() {

        DataObject object = new DataObject();

        JsonToken start = tokens.get(pos++);
        if (!start.type().equals(JsonToken.Type.S_OBJECT))
            throw new JsonException("Expected '{' at start of object");

        while (pos < tokens.size()) {

            JsonToken currentToken = tokens.get(pos);

            if (currentToken.type() == JsonToken.Type.E_OBJECT) {
                pos++;
                return object;
            }

            if (currentToken.type() != JsonToken.Type.STRING)
                throw new JsonException("Expected string key, found: " + currentToken);
            String key = currentToken.value();
            pos++;

            JsonToken colonToken = tokens.get(pos++);
            if (colonToken.type() != JsonToken.Type.COLON)
                throw new JsonException("Expected ':' after key, found: " + colonToken);

            DataElement value = parseValue();
            object.put(key, value);

            if (pos < tokens.size()) {
                JsonToken nextToken = tokens.get(pos);
                if (nextToken.type() == JsonToken.Type.COMMA)
                    pos++;
                else if (nextToken.type() != JsonToken.Type.E_OBJECT)
                    throw new JsonException("Expected ',' or '}' after value, found: " + nextToken);
            }
        }

        throw new JsonException("Unclosed object");
    }

    private DataArray parseArray() {

        DataArray array = new DataArray();

        JsonToken startToken = tokens.get(pos++);
        if (startToken.type() != JsonToken.Type.S_ARRAY)
            throw new JsonException("Expected '[' at start of array");

        while (pos < tokens.size()) {
            JsonToken currentToken = tokens.get(pos);

            if (currentToken.type() == JsonToken.Type.E_ARRAY) {
                pos++;
                return array;
            }

            DataElement element = parseValue();
            array.add(element);

            if (pos < tokens.size()) {
                JsonToken nextToken = tokens.get(pos);
                if (nextToken.type() == JsonToken.Type.COMMA)
                    pos++;
                else if (nextToken.type() != JsonToken.Type.E_ARRAY)
                    throw new JsonException("Expected ',' or ']' after array element");
            }
        }

        throw new JsonException("Unclosed array");
    }
}