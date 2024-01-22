package org.json;

import java.util.Iterator;
import kotlin.text.Typography;
/* loaded from: classes13.dex */
public class XML {
    public static final Character AMP = Character.valueOf(Typography.amp);
    public static final Character APOS = '\'';
    public static final Character BANG = '!';
    public static final Character EQ = '=';
    public static final Character GT = Character.valueOf(Typography.greater);
    public static final Character LT = Character.valueOf(Typography.less);
    public static final Character QUEST = Character.valueOf(org.apache.commons.codec.net.a.SEP);
    public static final Character QUOT = Character.valueOf(Typography.quote);
    public static final Character SLASH = '/';

    /* loaded from: classes13.dex */
    public class a implements Iterable<Integer> {
        public final /* synthetic */ String h;

        /* renamed from: org.json.XML$a$a  reason: collision with other inner class name */
        /* loaded from: classes13.dex */
        public class C0917a implements Iterator<Integer> {
            public int h = 0;
            public int i;

            public C0917a() {
                this.i = a.this.h.length();
            }

            @Override // java.util.Iterator
            /* renamed from: a */
            public Integer next() {
                int codePointAt = a.this.h.codePointAt(this.h);
                this.h += Character.charCount(codePointAt);
                return Integer.valueOf(codePointAt);
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.h < this.i;
            }

            @Override // java.util.Iterator
            public void remove() {
                throw new UnsupportedOperationException();
            }
        }

        public a(String str) {
            this.h = str;
        }

        @Override // java.lang.Iterable
        public Iterator<Integer> iterator() {
            return new C0917a();
        }
    }

    public static Iterable<Integer> a(String str) {
        return new a(str);
    }

    public static boolean b(int i) {
        return !(!Character.isISOControl(i) || i == 9 || i == 10 || i == 13) || ((i < 32 || i > 55295) && ((i < 57344 || i > 65533) && (i < 65536 || i > 1114111)));
    }

    /* JADX WARN: Code restructure failed: missing block: B:64:0x00ef, code lost:
        r7 = r10.nextToken();
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00f5, code lost:
        if ((r7 instanceof java.lang.String) == false) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x00f7, code lost:
        r7 = (java.lang.String) r7;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x00f9, code lost:
        if (r13 == false) goto L76;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00fc, code lost:
        r7 = stringToValue(r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x010a, code lost:
        throw r10.syntaxError("Missing value");
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v7, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r7v3, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.lang.Object] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean c(org.json.XMLTokener r10, org.json.JSONObject r11, java.lang.String r12, boolean r13) throws org.json.JSONException {
        /*
            Method dump skipped, instructions count: 415
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.XML.c(org.json.XMLTokener, org.json.JSONObject, java.lang.String, boolean):boolean");
    }

    public static String escape(String str) {
        StringBuilder sb = new StringBuilder(str.length());
        for (Integer num : a(str)) {
            int intValue = num.intValue();
            if (intValue == 34) {
                sb.append("&quot;");
            } else if (intValue == 60) {
                sb.append("&lt;");
            } else if (intValue == 62) {
                sb.append("&gt;");
            } else if (intValue == 38) {
                sb.append("&amp;");
            } else if (intValue != 39) {
                if (b(intValue)) {
                    sb.append("&#x");
                    sb.append(Integer.toHexString(intValue));
                    sb.append(';');
                } else {
                    sb.appendCodePoint(intValue);
                }
            } else {
                sb.append("&apos;");
            }
        }
        return sb.toString();
    }

    public static void noSpace(String str) throws JSONException {
        int length = str.length();
        if (length == 0) {
            throw new JSONException("Empty string.");
        }
        for (int i = 0; i < length; i++) {
            if (Character.isWhitespace(str.charAt(i))) {
                throw new JSONException("'" + str + "' contains a space character.");
            }
        }
    }

    public static Object stringToValue(String str) {
        if (str.equals("")) {
            return str;
        }
        if (str.equalsIgnoreCase("true")) {
            return Boolean.TRUE;
        }
        if (str.equalsIgnoreCase("false")) {
            return Boolean.FALSE;
        }
        if (str.equalsIgnoreCase("null")) {
            return JSONObject.NULL;
        }
        char charAt = str.charAt(0);
        if ((charAt >= '0' && charAt <= '9') || charAt == '-') {
            try {
                if (str.indexOf(46) <= -1 && str.indexOf(101) <= -1 && str.indexOf(69) <= -1 && !"-0".equals(str)) {
                    Long valueOf = Long.valueOf(str);
                    if (str.equals(valueOf.toString())) {
                        return valueOf.longValue() == ((long) valueOf.intValue()) ? Integer.valueOf(valueOf.intValue()) : valueOf;
                    }
                }
                Double valueOf2 = Double.valueOf(str);
                if (!valueOf2.isInfinite() && !valueOf2.isNaN()) {
                    return valueOf2;
                }
            } catch (Exception unused) {
            }
        }
        return str;
    }

    public static JSONObject toJSONObject(String str) throws JSONException {
        return toJSONObject(str, false);
    }

    public static String toString(Object obj) throws JSONException {
        return toString(obj, null);
    }

    public static String unescape(String str) {
        StringBuilder sb = new StringBuilder(str.length());
        int length = str.length();
        int i = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            if (charAt == '&') {
                int indexOf = str.indexOf(59, i);
                if (indexOf > i) {
                    String substring = str.substring(i + 1, indexOf);
                    sb.append(XMLTokener.c(substring));
                    i += substring.length() + 1;
                } else {
                    sb.append(charAt);
                }
            } else {
                sb.append(charAt);
            }
            i++;
        }
        return sb.toString();
    }

    public static JSONObject toJSONObject(String str, boolean z) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        XMLTokener xMLTokener = new XMLTokener(str);
        while (xMLTokener.more()) {
            xMLTokener.skipPast("<");
            if (xMLTokener.more()) {
                c(xMLTokener, jSONObject, null, z);
            }
        }
        return jSONObject;
    }

    public static String toString(Object obj, String str) throws JSONException {
        JSONArray jSONArray;
        StringBuilder sb = new StringBuilder();
        if (obj instanceof JSONObject) {
            if (str != null) {
                sb.append(Typography.less);
                sb.append(str);
                sb.append(Typography.greater);
            }
            JSONObject jSONObject = (JSONObject) obj;
            for (String str2 : jSONObject.keySet()) {
                Object opt = jSONObject.opt(str2);
                if (opt == null) {
                    opt = "";
                } else if (opt.getClass().isArray()) {
                    opt = new JSONArray(opt);
                }
                if ("content".equals(str2)) {
                    if (opt instanceof JSONArray) {
                        JSONArray jSONArray2 = (JSONArray) opt;
                        int length = jSONArray2.length();
                        for (int i = 0; i < length; i++) {
                            if (i > 0) {
                                sb.append('\n');
                            }
                            sb.append(escape(jSONArray2.opt(i).toString()));
                        }
                    } else {
                        sb.append(escape(opt.toString()));
                    }
                } else if (opt instanceof JSONArray) {
                    JSONArray jSONArray3 = (JSONArray) opt;
                    int length2 = jSONArray3.length();
                    for (int i2 = 0; i2 < length2; i2++) {
                        Object opt2 = jSONArray3.opt(i2);
                        if (opt2 instanceof JSONArray) {
                            sb.append(Typography.less);
                            sb.append(str2);
                            sb.append(Typography.greater);
                            sb.append(toString(opt2));
                            sb.append("</");
                            sb.append(str2);
                            sb.append(Typography.greater);
                        } else {
                            sb.append(toString(opt2, str2));
                        }
                    }
                } else if ("".equals(opt)) {
                    sb.append(Typography.less);
                    sb.append(str2);
                    sb.append("/>");
                } else {
                    sb.append(toString(opt, str2));
                }
            }
            if (str != null) {
                sb.append("</");
                sb.append(str);
                sb.append(Typography.greater);
            }
            return sb.toString();
        } else if (obj != null && ((obj instanceof JSONArray) || obj.getClass().isArray())) {
            if (obj.getClass().isArray()) {
                jSONArray = new JSONArray(obj);
            } else {
                jSONArray = (JSONArray) obj;
            }
            int length3 = jSONArray.length();
            for (int i3 = 0; i3 < length3; i3++) {
                sb.append(toString(jSONArray.opt(i3), str == null ? "array" : str));
            }
            return sb.toString();
        } else {
            String escape = obj == null ? "null" : escape(obj.toString());
            if (str == null) {
                return "\"" + escape + "\"";
            } else if (escape.length() == 0) {
                return "<" + str + "/>";
            } else {
                return "<" + str + ">" + escape + "</" + str + ">";
            }
        }
    }
}
