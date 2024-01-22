package org.json;

import kotlin.text.Typography;
/* loaded from: classes13.dex */
public class CDL {
    public static String a(JSONTokener jSONTokener) throws JSONException {
        char next;
        char next2;
        while (true) {
            next = jSONTokener.next();
            if (next != ' ' && next != '\t') {
                break;
            }
        }
        if (next != 0) {
            if (next != '\"' && next != '\'') {
                if (next != ',') {
                    jSONTokener.back();
                    return jSONTokener.nextTo(',');
                }
                jSONTokener.back();
                return "";
            }
            StringBuffer stringBuffer = new StringBuffer();
            while (true) {
                char next3 = jSONTokener.next();
                if (next3 == next && (next2 = jSONTokener.next()) != '\"') {
                    if (next2 > 0) {
                        jSONTokener.back();
                    }
                    return stringBuffer.toString();
                } else if (next3 == 0 || next3 == '\n' || next3 == '\r') {
                    break;
                } else {
                    stringBuffer.append(next3);
                }
            }
            throw jSONTokener.syntaxError("Missing close quote '" + next + "'.");
        }
        return null;
    }

    public static JSONArray rowToJSONArray(JSONTokener jSONTokener) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        while (true) {
            String a2 = a(jSONTokener);
            char next = jSONTokener.next();
            if (a2 == null) {
                return null;
            }
            if (jSONArray.length() == 0 && a2.length() == 0 && next != ',') {
                return null;
            }
            jSONArray.put(a2);
            while (next != ',') {
                if (next != ' ') {
                    if (next == '\n' || next == '\r' || next == 0) {
                        return jSONArray;
                    }
                    throw jSONTokener.syntaxError("Bad character '" + next + "' (" + ((int) next) + ").");
                }
                next = jSONTokener.next();
            }
        }
    }

    public static JSONObject rowToJSONObject(JSONArray jSONArray, JSONTokener jSONTokener) throws JSONException {
        JSONArray rowToJSONArray = rowToJSONArray(jSONTokener);
        if (rowToJSONArray != null) {
            return rowToJSONArray.toJSONObject(jSONArray);
        }
        return null;
    }

    public static String rowToString(JSONArray jSONArray) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < jSONArray.length(); i++) {
            if (i > 0) {
                sb.append(',');
            }
            Object opt = jSONArray.opt(i);
            if (opt != null) {
                String obj = opt.toString();
                if (obj.length() > 0 && (obj.indexOf(44) >= 0 || obj.indexOf(10) >= 0 || obj.indexOf(13) >= 0 || obj.indexOf(0) >= 0 || obj.charAt(0) == '\"')) {
                    sb.append(Typography.quote);
                    int length = obj.length();
                    for (int i2 = 0; i2 < length; i2++) {
                        char charAt = obj.charAt(i2);
                        if (charAt >= ' ' && charAt != '\"') {
                            sb.append(charAt);
                        }
                    }
                    sb.append(Typography.quote);
                } else {
                    sb.append(obj);
                }
            }
        }
        sb.append('\n');
        return sb.toString();
    }

    public static JSONArray toJSONArray(String str) throws JSONException {
        return toJSONArray(new JSONTokener(str));
    }

    public static String toString(JSONArray jSONArray) throws JSONException {
        JSONArray names;
        JSONObject optJSONObject = jSONArray.optJSONObject(0);
        if (optJSONObject == null || (names = optJSONObject.names()) == null) {
            return null;
        }
        return rowToString(names) + toString(names, jSONArray);
    }

    public static JSONArray toJSONArray(JSONTokener jSONTokener) throws JSONException {
        return toJSONArray(rowToJSONArray(jSONTokener), jSONTokener);
    }

    public static JSONArray toJSONArray(JSONArray jSONArray, String str) throws JSONException {
        return toJSONArray(jSONArray, new JSONTokener(str));
    }

    public static JSONArray toJSONArray(JSONArray jSONArray, JSONTokener jSONTokener) throws JSONException {
        if (jSONArray == null || jSONArray.length() == 0) {
            return null;
        }
        JSONArray jSONArray2 = new JSONArray();
        while (true) {
            JSONObject rowToJSONObject = rowToJSONObject(jSONArray, jSONTokener);
            if (rowToJSONObject == null) {
                break;
            }
            jSONArray2.put(rowToJSONObject);
        }
        if (jSONArray2.length() == 0) {
            return null;
        }
        return jSONArray2;
    }

    public static String toString(JSONArray jSONArray, JSONArray jSONArray2) throws JSONException {
        if (jSONArray == null || jSONArray.length() == 0) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < jSONArray2.length(); i++) {
            JSONObject optJSONObject = jSONArray2.optJSONObject(i);
            if (optJSONObject != null) {
                stringBuffer.append(rowToString(optJSONObject.toJSONArray(jSONArray)));
            }
        }
        return stringBuffer.toString();
    }
}
