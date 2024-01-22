package com.clevertap.android.sdk.variables;

import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
@RestrictTo({RestrictTo.Scope.LIBRARY})
/* loaded from: classes2.dex */
public final class CTVariableUtils {
    public static final String BOOLEAN = "boolean";
    public static final String DICTIONARY = "group";
    public static final String NUMBER = "number";
    public static final String STRING = "string";
    public static final String VARS = "vars";

    @VisibleForTesting
    public static void a(String str, Map<String, Object> map, Map<String, Object> map2) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Map) {
                a(str + key + ".", (Map) JsonUtil.uncheckedCast(value), map2);
            } else {
                map2.put(str + key, value);
            }
        }
    }

    public static void b(String str) {
        Logger.d("variables", str);
    }

    public static Map<String, Object> convertFlatMapToNestedMaps(Map<String, Object> map) {
        Map hashMap = new HashMap();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            if (key.contains(".")) {
                String[] nameComponents = getNameComponents(key);
                int length = nameComponents.length - 1;
                int i = 0;
                Map map2 = hashMap;
                while (i < nameComponents.length) {
                    String str = nameComponents[i];
                    if (i == length) {
                        map2.put(str, entry.getValue());
                    } else if (!(map2.get(str) instanceof Map)) {
                        HashMap hashMap2 = new HashMap();
                        map2.put(str, hashMap2);
                        map2 = hashMap2;
                    } else {
                        map2 = (Map) JsonUtil.uncheckedCast(map2.get(str));
                    }
                    i++;
                    map2 = map2;
                }
            } else {
                hashMap.put(entry.getKey(), entry.getValue());
            }
        }
        return hashMap;
    }

    public static Map<Object, Object> deepCopyMap(Map<Object, Object> map) {
        HashMap hashMap = new HashMap();
        for (Map.Entry<Object, Object> entry : map.entrySet()) {
            Object key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof Map) {
                hashMap.put(key, deepCopyMap((Map) JsonUtil.uncheckedCast(value)));
            } else {
                hashMap.put(key, value);
            }
        }
        return hashMap;
    }

    public static JSONObject getFlatVarsJson(Map<String, Object> map, Map<String, String> map2) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", Constants.variablePayloadType);
            JSONObject jSONObject2 = new JSONObject();
            for (String str : map.keySet()) {
                String str2 = map2.get(str);
                Object obj = map.get(str);
                if (obj instanceof Map) {
                    HashMap hashMap = new HashMap();
                    hashMap.put(str, obj);
                    HashMap hashMap2 = new HashMap();
                    a("", hashMap, hashMap2);
                    for (Map.Entry entry : hashMap2.entrySet()) {
                        Object value = entry.getValue();
                        String kindFromValue = kindFromValue(value);
                        JSONObject jSONObject3 = new JSONObject();
                        jSONObject3.put("type", kindFromValue);
                        jSONObject3.put("defaultValue", value);
                        jSONObject2.put((String) entry.getKey(), jSONObject3);
                    }
                } else {
                    JSONObject jSONObject4 = new JSONObject();
                    jSONObject4.put("type", str2);
                    jSONObject4.put("defaultValue", obj);
                    jSONObject2.put(str, jSONObject4);
                }
            }
            jSONObject.put("vars", jSONObject2);
            return jSONObject;
        } catch (Throwable th) {
            th.printStackTrace();
            return new JSONObject();
        }
    }

    public static String[] getNameComponents(String str) {
        try {
            return str.split("\\.");
        } catch (Throwable th) {
            th.printStackTrace();
            return new String[0];
        }
    }

    public static <T> String kindFromValue(T t) {
        if ((t instanceof Integer) || (t instanceof Long) || (t instanceof Short) || (t instanceof Character) || (t instanceof Byte) || (t instanceof BigInteger) || (t instanceof Float) || (t instanceof Double) || (t instanceof BigDecimal)) {
            return NUMBER;
        }
        if (t instanceof String) {
            return "string";
        }
        if (t instanceof Map) {
            return "group";
        }
        if (t instanceof Boolean) {
            return "boolean";
        }
        return null;
    }

    public static Object mergeHelper(Object obj, Object obj2) {
        if (obj2 == null) {
            return obj;
        }
        if ((obj2 instanceof Number) || (obj2 instanceof Boolean) || (obj2 instanceof String) || (obj2 instanceof Character) || (obj instanceof Number) || (obj instanceof Boolean) || (obj instanceof String) || (obj instanceof Character)) {
            return obj2;
        }
        boolean z = obj2 instanceof Map;
        Iterable keySet = z ? ((Map) obj2).keySet() : (Iterable) obj2;
        boolean z2 = obj instanceof Map;
        Iterable keySet2 = z2 ? ((Map) obj).keySet() : (Iterable) obj;
        Map map = z ? (Map) obj2 : null;
        Map map2 = z2 ? (Map) obj : null;
        if (z2 || z) {
            HashMap hashMap = new HashMap();
            if (keySet2 != null) {
                for (Object obj3 : keySet2) {
                    if (map != null && map2 != null) {
                        Object obj4 = map.get(obj3);
                        Object obj5 = map2.get(obj3);
                        if (obj4 == null && obj5 != null) {
                            hashMap.put(obj3, obj5);
                        }
                    }
                }
            }
            for (Object obj6 : keySet) {
                hashMap.put(obj6, mergeHelper(map2 != null ? map2.get(obj6) : null, map != null ? map.get(obj6) : null));
            }
            return hashMap;
        }
        return null;
    }

    public static Object traverse(Object obj, Object obj2, boolean z) {
        if (obj != null && (obj instanceof Map)) {
            Map map = (Map) JsonUtil.uncheckedCast(obj);
            Object obj3 = map.get(obj2);
            if (z && obj3 == null && (obj2 instanceof String)) {
                HashMap hashMap = new HashMap();
                map.put(obj2, hashMap);
                return hashMap;
            }
            return obj3;
        }
        return null;
    }

    public static void updateValuesAndKinds(String str, String[] strArr, Object obj, String str2, Map<String, Object> map, Map<String, String> map2) {
        if (strArr != null && strArr.length > 0) {
            int i = 0;
            Object obj2 = map;
            while (i < strArr.length - 1) {
                i++;
                obj2 = traverse(obj2, strArr[i], true);
            }
            if (obj2 instanceof Map) {
                Map map3 = (Map) JsonUtil.uncheckedCast(obj2);
                Object obj3 = map3.get(strArr[strArr.length - 1]);
                if ((obj3 instanceof Map) && (obj instanceof Map)) {
                    obj = mergeHelper(obj, obj3);
                } else if (obj3 != null && obj3.equals(obj)) {
                    b(String.format("Variable with name %s will override value: %s, with new value: %s.", str, obj3, obj));
                }
                map3.put(strArr[strArr.length - 1], obj);
            }
        }
        if (map2 != null) {
            map2.put(str, str2);
        }
    }
}
