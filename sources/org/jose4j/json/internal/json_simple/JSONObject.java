package org.jose4j.json.internal.json_simple;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import kotlin.text.Typography;
/* loaded from: classes13.dex */
public class JSONObject extends HashMap implements JSONAware, JSONStreamAware {
    private static final long serialVersionUID = -503443796854799292L;

    public JSONObject() {
    }

    public static String escape(String str) {
        return JSONValue.escape(str);
    }

    public static String toJSONString(Map map) {
        StringWriter stringWriter = new StringWriter();
        try {
            writeJSONString(map, stringWriter);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeJSONString(Map map, Writer writer) throws IOException {
        if (map == null) {
            writer.write("null");
            return;
        }
        boolean z = true;
        writer.write(123);
        for (Map.Entry entry : map.entrySet()) {
            if (z) {
                z = false;
            } else {
                writer.write(44);
            }
            writer.write(34);
            writer.write(escape(String.valueOf(entry.getKey())));
            writer.write(34);
            writer.write(58);
            JSONValue.writeJSONString(entry.getValue(), writer);
        }
        writer.write(125);
    }

    @Override // java.util.AbstractMap
    public String toString() {
        return toJSONString();
    }

    public JSONObject(Map map) {
        super(map);
    }

    public static String toString(String str, Object obj) {
        StringBuilder sb = new StringBuilder();
        sb.append(Typography.quote);
        if (str == null) {
            sb.append("null");
        } else {
            JSONValue.a(str, sb);
        }
        sb.append(Typography.quote);
        sb.append(':');
        sb.append(JSONValue.toJSONString(obj));
        return sb.toString();
    }

    @Override // org.jose4j.json.internal.json_simple.JSONAware
    public String toJSONString() {
        return toJSONString(this);
    }

    @Override // org.jose4j.json.internal.json_simple.JSONStreamAware
    public void writeJSONString(Writer writer) throws IOException {
        writeJSONString(this, writer);
    }
}
