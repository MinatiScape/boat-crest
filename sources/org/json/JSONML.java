package org.json;

import kotlin.text.Typography;
/* loaded from: classes13.dex */
public class JSONML {
    /* JADX WARN: Code restructure failed: missing block: B:115:0x0187, code lost:
        r7 = r9.nextToken();
     */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x018d, code lost:
        if ((r7 instanceof java.lang.String) == false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x018f, code lost:
        r7 = (java.lang.String) r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x0191, code lost:
        if (r12 == false) goto L55;
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x0194, code lost:
        r7 = org.json.XML.stringToValue(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x01a3, code lost:
        throw r9.syntaxError("Missing value");
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v3, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.lang.Object] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.Object a(org.json.XMLTokener r9, boolean r10, org.json.JSONArray r11, boolean r12) throws org.json.JSONException {
        /*
            Method dump skipped, instructions count: 489
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.JSONML.a(org.json.XMLTokener, boolean, org.json.JSONArray, boolean):java.lang.Object");
    }

    public static JSONArray toJSONArray(String str) throws JSONException {
        return (JSONArray) a(new XMLTokener(str), true, null, false);
    }

    public static JSONObject toJSONObject(String str) throws JSONException {
        return (JSONObject) a(new XMLTokener(str), false, null, false);
    }

    public static String toString(JSONArray jSONArray) throws JSONException {
        int i;
        StringBuilder sb = new StringBuilder();
        String string = jSONArray.getString(0);
        XML.noSpace(string);
        String escape = XML.escape(string);
        sb.append(Typography.less);
        sb.append(escape);
        Object opt = jSONArray.opt(1);
        if (opt instanceof JSONObject) {
            i = 2;
            JSONObject jSONObject = (JSONObject) opt;
            for (String str : jSONObject.keySet()) {
                Object opt2 = jSONObject.opt(str);
                XML.noSpace(str);
                if (opt2 != null) {
                    sb.append(' ');
                    sb.append(XML.escape(str));
                    sb.append('=');
                    sb.append(Typography.quote);
                    sb.append(XML.escape(opt2.toString()));
                    sb.append(Typography.quote);
                }
            }
        } else {
            i = 1;
        }
        int length = jSONArray.length();
        if (i >= length) {
            sb.append('/');
            sb.append(Typography.greater);
        } else {
            sb.append(Typography.greater);
            do {
                Object obj = jSONArray.get(i);
                i++;
                if (obj != null) {
                    if (obj instanceof String) {
                        sb.append(XML.escape(obj.toString()));
                        continue;
                    } else if (obj instanceof JSONObject) {
                        sb.append(toString((JSONObject) obj));
                        continue;
                    } else if (obj instanceof JSONArray) {
                        sb.append(toString((JSONArray) obj));
                        continue;
                    } else {
                        sb.append(obj.toString());
                        continue;
                    }
                }
            } while (i < length);
            sb.append(Typography.less);
            sb.append('/');
            sb.append(escape);
            sb.append(Typography.greater);
        }
        return sb.toString();
    }

    public static JSONArray toJSONArray(String str, boolean z) throws JSONException {
        return (JSONArray) a(new XMLTokener(str), true, null, z);
    }

    public static JSONObject toJSONObject(String str, boolean z) throws JSONException {
        return (JSONObject) a(new XMLTokener(str), false, null, z);
    }

    public static JSONArray toJSONArray(XMLTokener xMLTokener, boolean z) throws JSONException {
        return (JSONArray) a(xMLTokener, true, null, z);
    }

    public static JSONObject toJSONObject(XMLTokener xMLTokener) throws JSONException {
        return (JSONObject) a(xMLTokener, false, null, false);
    }

    public static JSONArray toJSONArray(XMLTokener xMLTokener) throws JSONException {
        return (JSONArray) a(xMLTokener, true, null, false);
    }

    public static JSONObject toJSONObject(XMLTokener xMLTokener, boolean z) throws JSONException {
        return (JSONObject) a(xMLTokener, false, null, z);
    }

    public static String toString(JSONObject jSONObject) throws JSONException {
        StringBuilder sb = new StringBuilder();
        String optString = jSONObject.optString("tagName");
        if (optString == null) {
            return XML.escape(jSONObject.toString());
        }
        XML.noSpace(optString);
        String escape = XML.escape(optString);
        sb.append(Typography.less);
        sb.append(escape);
        for (String str : jSONObject.keySet()) {
            if (!"tagName".equals(str) && !"childNodes".equals(str)) {
                XML.noSpace(str);
                Object opt = jSONObject.opt(str);
                if (opt != null) {
                    sb.append(' ');
                    sb.append(XML.escape(str));
                    sb.append('=');
                    sb.append(Typography.quote);
                    sb.append(XML.escape(opt.toString()));
                    sb.append(Typography.quote);
                }
            }
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("childNodes");
        if (optJSONArray == null) {
            sb.append('/');
            sb.append(Typography.greater);
        } else {
            sb.append(Typography.greater);
            int length = optJSONArray.length();
            for (int i = 0; i < length; i++) {
                Object obj = optJSONArray.get(i);
                if (obj != null) {
                    if (obj instanceof String) {
                        sb.append(XML.escape(obj.toString()));
                    } else if (obj instanceof JSONObject) {
                        sb.append(toString((JSONObject) obj));
                    } else if (obj instanceof JSONArray) {
                        sb.append(toString((JSONArray) obj));
                    } else {
                        sb.append(obj.toString());
                    }
                }
            }
            sb.append(Typography.less);
            sb.append('/');
            sb.append(escape);
            sb.append(Typography.greater);
        }
        return sb.toString();
    }
}
