package com.clevertap.android.sdk.variables;

import android.text.Editable;
import com.clevertap.android.sdk.Logger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class JsonUtil {
    public static <T> List<T> a(JSONArray jSONArray) {
        Object obj;
        if (jSONArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(jSONArray.length());
        for (int i = 0; i < jSONArray.length(); i++) {
            Object opt = jSONArray.opt(i);
            if (opt != null && opt != (obj = JSONObject.NULL)) {
                if (opt instanceof JSONObject) {
                    opt = mapFromJson((JSONObject) opt);
                } else if (opt instanceof JSONArray) {
                    opt = a((JSONArray) opt);
                } else if (!obj.equals(opt)) {
                }
                arrayList.add(opt);
            }
            opt = null;
            arrayList.add(opt);
        }
        return (List) uncheckedCast(arrayList);
    }

    public static JSONArray b(Iterable<?> iterable) throws JSONException {
        if (iterable == null) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        for (Object obj : iterable) {
            if (obj instanceof Map) {
                obj = c((Map) uncheckedCast(obj));
            } else if (obj instanceof Iterable) {
                obj = b((Iterable) obj);
            } else if (obj == null) {
                obj = JSONObject.NULL;
            }
            jSONArray.put(obj);
        }
        return jSONArray;
    }

    public static JSONObject c(Map<String, ?> map) throws JSONException {
        if (map == null) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Map) {
                value = c((Map) uncheckedCast(value));
            } else if (value instanceof Iterable) {
                value = b((Iterable) value);
            } else if (value instanceof Editable) {
                value = value.toString();
            } else if (value == null) {
                value = JSONObject.NULL;
            }
            jSONObject.put(key, value);
        }
        return jSONObject;
    }

    public static Map<String, Object> fromJson(String str) {
        if (str == null) {
            return null;
        }
        try {
            return mapFromJson(new JSONObject(str));
        } catch (JSONException e) {
            Logger.v("Error converting " + str + " from JSON", e);
            return null;
        }
    }

    public static <T> Map<String, T> mapFromJson(JSONObject jSONObject) {
        Object obj;
        if (jSONObject == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        Iterator<String> keys = jSONObject.keys();
        while (keys.hasNext()) {
            String next = keys.next();
            Object opt = jSONObject.opt(next);
            if (opt != null && opt != (obj = JSONObject.NULL)) {
                if (opt instanceof JSONObject) {
                    opt = mapFromJson((JSONObject) opt);
                } else if (opt instanceof JSONArray) {
                    opt = a((JSONArray) opt);
                } else if (!obj.equals(opt)) {
                }
                hashMap.put(next, uncheckedCast(opt));
            }
            opt = null;
            hashMap.put(next, uncheckedCast(opt));
        }
        return hashMap;
    }

    public static String toJson(Map<String, ?> map) {
        if (map == null) {
            return null;
        }
        try {
            return c(map).toString();
        } catch (JSONException e) {
            Logger.v("Error converting " + map + " to JSON", e);
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T uncheckedCast(Object obj) {
        return obj;
    }
}
