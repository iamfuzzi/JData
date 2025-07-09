package me.fuzzi.jdata.json;

import me.fuzzi.jdata.api.Serializer;
import me.fuzzi.jdata.data.*;

import java.util.ArrayList;

public class JsonSerializer extends Serializer<JsonPretty> {

    public JsonSerializer(JsonPretty pretty) {
        super(pretty);
    }

    private final StringBuilder builder = new StringBuilder();
    private int indent;

    private void newLine() {
        if (pretty.newLines()) {
            builder.append('\n');
            for (int i = 0; i < indent; i++)
                builder.append(pretty.indent());
        }
    }

    private void serializeString(String string) {
        builder.append('"');
        for (char c : string.toCharArray())
            builder.append(switch (c) {
                case '"' -> "\\\"";
                case '\\' -> "\\\\";
                case '/' -> "\\/";
                case '\b' -> "\\b";
                case '\f' -> "\\f";
                case '\n' -> "\\n";
                case '\r' -> "\\r";
                case '\t' -> "\\t";
                default -> c;
            });
        builder.append('"');
    }

    @Override
    public String serialize(DataElement data) {
        builder.delete(0, builder.length());
        indent = 0;
        serializeElement(data);
        return builder.toString();
    }

    private void serializeElement(DataElement element) {
        if (element == null) {
            builder.append("null");
            return;
        }
        if (element.isValue())
            serializeValue(element.asValue());
        else if (element.isObject())
            serializeObject(element.asObject());
        else if (element.isArray())
            serializeArray(element.asArray());
    }

    private void serializeValue(DataValue value) {

        if (value.isNull())
            builder.append("null");
        else if (value.isBoolean())
            builder.append(value.asBoolean());
        else if (value.isInt())
            builder.append(value.asInt());
        else if (value.isDouble()) {
            double d = value.asDouble();
            if (Double.isNaN(d) || Double.isInfinite(d))
                builder.append("null");
            else
                builder.append(d);
        } else if (value.isString())
            serializeString(value.asString());
    }

    private void serializeObject(DataObject object) {

        if (!pretty.beforeObjectStart().equals("")) builder.append(pretty.beforeObjectStart());
        builder.append('{');
        if (!pretty.afterObjectStart().equals("")) builder.append(pretty.afterObjectStart());

        indent++;

        boolean first = true;
        for (String key : new ArrayList<>(object.keys())) {
            if (first) first = false;
            else {
                if (!pretty.beforeObjectComma().equals("")) builder.append(pretty.beforeObjectComma());
                builder.append(',');
                if (!pretty.afterObjectComma().equals("")) builder.append(pretty.afterObjectComma());
            }

            newLine();
            serializeString(key);

            if (!pretty.beforeObjectColon().equals("")) builder.append(pretty.beforeObjectColon());
            builder.append(':');
            if (!pretty.afterObjectColon().equals("")) builder.append(pretty.afterObjectColon());

            serializeElement(object.get(key));
        }

        indent--;
        if (!first) newLine();

        if (!pretty.beforeObjectEnd().equals("")) builder.append(pretty.beforeObjectEnd());
        builder.append('}');
        if (!pretty.afterObjectEnd().equals("")) builder.append(pretty.afterObjectEnd());
    }

    private void serializeArray(DataArray array) {

        if (!pretty.beforeArrayStart().equals("")) builder.append(pretty.beforeArrayStart());
        builder.append('[');
        if (!pretty.afterArrayStart().equals("")) builder.append(pretty.afterArrayStart());

        indent++;

        boolean first = true;
        for (DataElement element : array) {
            if (first) first = false;
            else {
                if (!pretty.beforeArrayComma().equals("")) builder.append(pretty.beforeArrayComma());
                builder.append(',');
                if (!pretty.afterArrayComma().equals("")) builder.append(pretty.afterArrayComma());
            }
            newLine();
            serializeElement(element);
        }

        indent--;
        if (!first) newLine();

        if (!pretty.beforeArrayEnd().equals("")) builder.append(pretty.beforeArrayEnd());
        builder.append(']');
        if (!pretty.afterArrayEnd().equals("")) builder.append(pretty.afterArrayEnd());
    }
}