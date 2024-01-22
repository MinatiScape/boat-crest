package org.json;
/* loaded from: classes13.dex */
public class CookieList {
    public static JSONObject toJSONObject(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONTokener jSONTokener = new JSONTokener(str);
        while (jSONTokener.more()) {
            String unescape = Cookie.unescape(jSONTokener.nextTo('='));
            jSONTokener.next('=');
            jSONObject.put(unescape, Cookie.unescape(jSONTokener.nextTo(';')));
            jSONTokener.next();
        }
        return jSONObject;
    }

    public static String toString(JSONObject jSONObject) throws JSONException {
        StringBuilder sb = new StringBuilder();
        boolean z = false;
        for (String str : jSONObject.keySet()) {
            Object opt = jSONObject.opt(str);
            if (!JSONObject.NULL.equals(opt)) {
                if (z) {
                    sb.append(';');
                }
                sb.append(Cookie.escape(str));
                sb.append("=");
                sb.append(Cookie.escape(opt.toString()));
                z = true;
            }
        }
        return sb.toString();
    }
}
