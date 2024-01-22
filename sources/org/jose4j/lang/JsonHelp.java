package org.jose4j.lang;

import java.util.List;
import java.util.Map;
import org.jose4j.jwt.IntDate;
/* loaded from: classes13.dex */
public class JsonHelp {
    public static IntDate getIntDate(Map<String, Object> map, String str) {
        return IntDate.fromSeconds(getLong(map, str).longValue());
    }

    public static Long getLong(Map<String, ?> map, String str) {
        Object obj = map.get(str);
        if (obj != null) {
            return Long.valueOf(((Number) obj).longValue());
        }
        return null;
    }

    public static String getString(Map<String, Object> map, String str) {
        return (String) map.get(str);
    }

    public static List<String> getStringArray(Map<String, Object> map, String str) {
        return (List) map.get(str);
    }

    public static String getStringChecked(Map<String, Object> map, String str) throws JoseException {
        Object obj = map.get(str);
        try {
            return (String) obj;
        } catch (ClassCastException unused) {
            throw new JoseException("'" + str + "' parameter was " + jsonTypeName(obj) + " type but is required to be a String.");
        }
    }

    public static String jsonTypeName(Object obj) {
        return obj instanceof Number ? "Number" : obj instanceof Boolean ? "Boolean" : obj instanceof List ? "Array" : obj instanceof Map ? "Object" : obj instanceof String ? "String" : "unknown";
    }
}
