package org.bouncycastle.est;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import kotlin.text.Typography;
/* loaded from: classes13.dex */
public class b {

    /* loaded from: classes13.dex */
    public static class a extends HashMap<String, String[]> {
        private String actualKey(String str) {
            if (containsKey(str)) {
                return str;
            }
            for (String str2 : keySet()) {
                if (str.equalsIgnoreCase(str2)) {
                    return str2;
                }
            }
            return null;
        }

        private String[] copy(String[] strArr) {
            int length = strArr.length;
            String[] strArr2 = new String[length];
            System.arraycopy(strArr, 0, strArr2, 0, length);
            return strArr2;
        }

        private boolean hasHeader(String str) {
            return actualKey(str) != null;
        }

        public void add(String str, String str2) {
            put(str, b.a(get(str), str2));
        }

        @Override // java.util.HashMap, java.util.AbstractMap
        public Object clone() {
            a aVar = new a();
            for (Map.Entry<String, String[]> entry : entrySet()) {
                aVar.put(entry.getKey(), copy(entry.getValue()));
            }
            return aVar;
        }

        public void ensureHeader(String str, String str2) {
            if (containsKey(str)) {
                return;
            }
            set(str, str2);
        }

        public String getFirstValue(String str) {
            String[] values = getValues(str);
            if (values == null || values.length <= 0) {
                return null;
            }
            return values[0];
        }

        public String[] getValues(String str) {
            String actualKey = actualKey(str);
            if (actualKey == null) {
                return null;
            }
            return get(actualKey);
        }

        public void set(String str, String str2) {
            put(str, new String[]{str2});
        }
    }

    /* renamed from: org.bouncycastle.est.b$b  reason: collision with other inner class name */
    /* loaded from: classes13.dex */
    public static class C0905b {

        /* renamed from: a  reason: collision with root package name */
        public final String f14914a;
        public int b = 0;
        public int c = 0;

        public C0905b(String str) {
            this.f14914a = str;
        }

        public Map<String, String> a() {
            HashMap hashMap = new HashMap();
            while (this.c < this.f14914a.length()) {
                g();
                String b = b();
                if (b.length() == 0) {
                    throw new IllegalArgumentException("Expecting alpha label.");
                }
                g();
                if (!c('=')) {
                    throw new IllegalArgumentException("Expecting assign: '='");
                }
                g();
                if (!c(Typography.quote)) {
                    throw new IllegalArgumentException("Expecting start quote: '\"'");
                }
                e();
                String d = d(Typography.quote);
                f(1);
                hashMap.put(b, d);
                g();
                if (!c(',')) {
                    break;
                }
                e();
            }
            return hashMap;
        }

        public final String b() {
            char charAt = this.f14914a.charAt(this.c);
            while (this.c < this.f14914a.length() && ((charAt >= 'a' && charAt <= 'z') || (charAt >= 'A' && charAt <= 'Z'))) {
                int i = this.c + 1;
                this.c = i;
                charAt = this.f14914a.charAt(i);
            }
            String substring = this.f14914a.substring(this.b, this.c);
            this.b = this.c;
            return substring;
        }

        public final boolean c(char c) {
            if (this.c >= this.f14914a.length() || this.f14914a.charAt(this.c) != c) {
                return false;
            }
            this.c++;
            return true;
        }

        public final String d(char c) {
            while (this.c < this.f14914a.length() && this.f14914a.charAt(this.c) != c) {
                this.c++;
            }
            String substring = this.f14914a.substring(this.b, this.c);
            this.b = this.c;
            return substring;
        }

        public final void e() {
            this.b = this.c;
        }

        public final void f(int i) {
            int i2 = this.c + i;
            this.c = i2;
            this.b = i2;
        }

        public final void g() {
            while (this.c < this.f14914a.length() && this.f14914a.charAt(this.c) < '!') {
                this.c++;
            }
            this.b = this.c;
        }
    }

    public static String[] a(String[] strArr, String str) {
        if (strArr == null) {
            return new String[]{str};
        }
        int length = strArr.length;
        String[] strArr2 = new String[length + 1];
        System.arraycopy(strArr, 0, strArr2, 0, length);
        strArr2[length] = str;
        return strArr2;
    }

    public static String b(String str, Map<String, String> map) {
        StringWriter stringWriter = new StringWriter();
        stringWriter.write(str);
        stringWriter.write(32);
        boolean z = false;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (z) {
                stringWriter.write(44);
            } else {
                z = true;
            }
            stringWriter.write(entry.getKey());
            stringWriter.write("=\"");
            stringWriter.write(entry.getValue());
            stringWriter.write(34);
        }
        return stringWriter.toString();
    }

    public static Map<String, String> c(String str, String str2) {
        String trim = str2.trim();
        if (trim.startsWith(str)) {
            trim = trim.substring(str.length());
        }
        return new C0905b(trim).a();
    }
}
