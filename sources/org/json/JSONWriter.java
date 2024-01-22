package org.json;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;
/* loaded from: classes13.dex */
public class JSONWriter {
    public Appendable writer;

    /* renamed from: a  reason: collision with root package name */
    public boolean f15564a = false;
    public char mode = 'i';
    public final JSONObject[] b = new JSONObject[200];
    public int c = 0;

    public JSONWriter(Appendable appendable) {
        this.writer = appendable;
    }

    public static String valueToString(Object obj) throws JSONException {
        if (obj == null || obj.equals(null)) {
            return "null";
        }
        if (obj instanceof JSONString) {
            try {
                String jSONString = ((JSONString) obj).toJSONString();
                if (jSONString instanceof String) {
                    return jSONString;
                }
                throw new JSONException("Bad value from toJSONString: " + ((Object) jSONString));
            } catch (Exception e) {
                throw new JSONException(e);
            }
        } else if (obj instanceof Number) {
            String numberToString = JSONObject.numberToString((Number) obj);
            try {
                new BigDecimal(numberToString);
                return numberToString;
            } catch (NumberFormatException unused) {
                return JSONObject.quote(numberToString);
            }
        } else if (!(obj instanceof Boolean) && !(obj instanceof JSONObject) && !(obj instanceof JSONArray)) {
            if (obj instanceof Map) {
                return new JSONObject((Map<?, ?>) obj).toString();
            }
            if (obj instanceof Collection) {
                return new JSONArray((Collection<?>) obj).toString();
            }
            if (obj.getClass().isArray()) {
                return new JSONArray(obj).toString();
            }
            if (obj instanceof Enum) {
                return JSONObject.quote(((Enum) obj).name());
            }
            return JSONObject.quote(obj.toString());
        } else {
            return obj.toString();
        }
    }

    public final JSONWriter a(String str) throws JSONException {
        if (str != null) {
            char c = this.mode;
            if (c != 'o' && c != 'a') {
                throw new JSONException("Value out of sequence.");
            }
            try {
                if (this.f15564a && c == 'a') {
                    this.writer.append(',');
                }
                this.writer.append(str);
                if (this.mode == 'o') {
                    this.mode = 'k';
                }
                this.f15564a = true;
                return this;
            } catch (IOException e) {
                throw new JSONException(e);
            }
        }
        throw new JSONException("Null pointer");
    }

    public JSONWriter array() throws JSONException {
        char c = this.mode;
        if (c != 'i' && c != 'o' && c != 'a') {
            throw new JSONException("Misplaced array.");
        }
        d(null);
        a("[");
        this.f15564a = false;
        return this;
    }

    public final JSONWriter b(char c, char c2) throws JSONException {
        if (this.mode != c) {
            throw new JSONException(c == 'a' ? "Misplaced endArray." : "Misplaced endObject.");
        }
        c(c);
        try {
            this.writer.append(c2);
            this.f15564a = true;
            return this;
        } catch (IOException e) {
            throw new JSONException(e);
        }
    }

    public final void c(char c) throws JSONException {
        int i = this.c;
        if (i > 0) {
            JSONObject[] jSONObjectArr = this.b;
            char c2 = 'a';
            if ((jSONObjectArr[i + (-1)] == null ? 'a' : 'k') == c) {
                int i2 = i - 1;
                this.c = i2;
                if (i2 == 0) {
                    c2 = 'd';
                } else if (jSONObjectArr[i2 - 1] != null) {
                    c2 = 'k';
                }
                this.mode = c2;
                return;
            }
            throw new JSONException("Nesting error.");
        }
        throw new JSONException("Nesting error.");
    }

    public final void d(JSONObject jSONObject) throws JSONException {
        int i = this.c;
        if (i < 200) {
            this.b[i] = jSONObject;
            this.mode = jSONObject == null ? 'a' : 'k';
            this.c = i + 1;
            return;
        }
        throw new JSONException("Nesting too deep.");
    }

    public JSONWriter endArray() throws JSONException {
        return b('a', ']');
    }

    public JSONWriter endObject() throws JSONException {
        return b('k', '}');
    }

    public JSONWriter key(String str) throws JSONException {
        if (str != null) {
            if (this.mode == 'k') {
                try {
                    JSONObject jSONObject = this.b[this.c - 1];
                    if (!jSONObject.has(str)) {
                        jSONObject.put(str, true);
                        if (this.f15564a) {
                            this.writer.append(',');
                        }
                        this.writer.append(JSONObject.quote(str));
                        this.writer.append(':');
                        this.f15564a = false;
                        this.mode = 'o';
                        return this;
                    }
                    throw new JSONException("Duplicate key \"" + str + "\"");
                } catch (IOException e) {
                    throw new JSONException(e);
                }
            }
            throw new JSONException("Misplaced key.");
        }
        throw new JSONException("Null key.");
    }

    public JSONWriter object() throws JSONException {
        if (this.mode == 'i') {
            this.mode = 'o';
        }
        char c = this.mode;
        if (c != 'o' && c != 'a') {
            throw new JSONException("Misplaced object.");
        }
        a("{");
        d(new JSONObject());
        this.f15564a = false;
        return this;
    }

    public JSONWriter value(boolean z) throws JSONException {
        return a(z ? "true" : "false");
    }

    public JSONWriter value(double d) throws JSONException {
        return value(new Double(d));
    }

    public JSONWriter value(long j) throws JSONException {
        return a(Long.toString(j));
    }

    public JSONWriter value(Object obj) throws JSONException {
        return a(valueToString(obj));
    }
}
