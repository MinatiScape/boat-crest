package org.jose4j.json.internal.json_simple;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collection;
import java.util.Map;
import org.jose4j.json.internal.json_simple.parser.JSONParser;
import org.jose4j.json.internal.json_simple.parser.ParseException;
/* loaded from: classes13.dex */
public class JSONValue {
    public static void a(String str, StringBuilder sb) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt == '\f') {
                sb.append("\\f");
            } else if (charAt == '\r') {
                sb.append("\\r");
            } else if (charAt == '\"') {
                sb.append("\\\"");
            } else if (charAt != '\\') {
                switch (charAt) {
                    case '\b':
                        sb.append("\\b");
                        continue;
                    case '\t':
                        sb.append("\\t");
                        continue;
                    case '\n':
                        sb.append("\\n");
                        continue;
                    default:
                        if ((charAt >= 0 && charAt <= 31) || ((charAt >= 127 && charAt <= 159) || (charAt >= 8192 && charAt <= 8447))) {
                            String hexString = Integer.toHexString(charAt);
                            sb.append("\\u");
                            for (int i2 = 0; i2 < 4 - hexString.length(); i2++) {
                                sb.append('0');
                            }
                            sb.append(hexString.toUpperCase());
                            continue;
                        } else {
                            sb.append(charAt);
                            break;
                        }
                }
            } else {
                sb.append("\\\\");
            }
        }
    }

    public static String escape(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        a(str, sb);
        return sb.toString();
    }

    public static Object parse(Reader reader) {
        try {
            return new JSONParser().parse(reader);
        } catch (Exception unused) {
            return null;
        }
    }

    public static Object parseWithException(Reader reader) throws IOException, ParseException {
        return new JSONParser().parse(reader);
    }

    public static String toJSONString(Object obj) {
        StringWriter stringWriter = new StringWriter();
        try {
            writeJSONString(obj, stringWriter);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeJSONString(Object obj, Writer writer) throws IOException {
        if (obj == null) {
            writer.write("null");
        } else if (obj instanceof String) {
            writer.write(34);
            writer.write(escape((String) obj));
            writer.write(34);
        } else if (obj instanceof Double) {
            Double d = (Double) obj;
            if (!d.isInfinite() && !d.isNaN()) {
                writer.write(obj.toString());
            } else {
                writer.write("null");
            }
        } else if (obj instanceof Float) {
            Float f = (Float) obj;
            if (!f.isInfinite() && !f.isNaN()) {
                writer.write(obj.toString());
            } else {
                writer.write("null");
            }
        } else if (obj instanceof Number) {
            writer.write(obj.toString());
        } else if (obj instanceof Boolean) {
            writer.write(obj.toString());
        } else if (obj instanceof JSONStreamAware) {
            ((JSONStreamAware) obj).writeJSONString(writer);
        } else if (obj instanceof JSONAware) {
            writer.write(((JSONAware) obj).toJSONString());
        } else if (obj instanceof Map) {
            JSONObject.writeJSONString((Map) obj, writer);
        } else if (obj instanceof Collection) {
            JSONArray.writeJSONString((Collection) obj, writer);
        } else if (obj instanceof byte[]) {
            JSONArray.writeJSONString((byte[]) obj, writer);
        } else if (obj instanceof short[]) {
            JSONArray.writeJSONString((short[]) obj, writer);
        } else if (obj instanceof int[]) {
            JSONArray.writeJSONString((int[]) obj, writer);
        } else if (obj instanceof long[]) {
            JSONArray.writeJSONString((long[]) obj, writer);
        } else if (obj instanceof float[]) {
            JSONArray.writeJSONString((float[]) obj, writer);
        } else if (obj instanceof double[]) {
            JSONArray.writeJSONString((double[]) obj, writer);
        } else if (obj instanceof boolean[]) {
            JSONArray.writeJSONString((boolean[]) obj, writer);
        } else if (obj instanceof char[]) {
            JSONArray.writeJSONString((char[]) obj, writer);
        } else if (obj instanceof Object[]) {
            JSONArray.writeJSONString((Object[]) obj, writer);
        } else {
            writeJSONString(obj.toString(), writer);
        }
    }

    public static Object parse(String str) {
        return parse(new StringReader(str));
    }

    public static Object parseWithException(String str) throws ParseException {
        return new JSONParser().parse(str);
    }
}
