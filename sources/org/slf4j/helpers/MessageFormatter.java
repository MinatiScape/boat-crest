package org.slf4j.helpers;

import java.util.HashMap;
import java.util.Map;
/* loaded from: classes13.dex */
public final class MessageFormatter {
    public static void a(StringBuilder sb, boolean[] zArr) {
        sb.append('[');
        int length = zArr.length;
        for (int i = 0; i < length; i++) {
            sb.append(zArr[i]);
            if (i != length - 1) {
                sb.append(", ");
            }
        }
        sb.append(']');
    }

    public static final FormattingTuple arrayFormat(String str, Object[] objArr) {
        Throwable g = g(objArr);
        if (g != null) {
            objArr = o(objArr);
        }
        return arrayFormat(str, objArr, g);
    }

    public static void b(StringBuilder sb, byte[] bArr) {
        sb.append('[');
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            sb.append((int) bArr[i]);
            if (i != length - 1) {
                sb.append(", ");
            }
        }
        sb.append(']');
    }

    public static void c(StringBuilder sb, char[] cArr) {
        sb.append('[');
        int length = cArr.length;
        for (int i = 0; i < length; i++) {
            sb.append(cArr[i]);
            if (i != length - 1) {
                sb.append(", ");
            }
        }
        sb.append(']');
    }

    public static void d(StringBuilder sb, Object obj, Map<Object[], Object> map) {
        if (obj == null) {
            sb.append("null");
        } else if (!obj.getClass().isArray()) {
            m(sb, obj);
        } else if (obj instanceof boolean[]) {
            a(sb, (boolean[]) obj);
        } else if (obj instanceof byte[]) {
            b(sb, (byte[]) obj);
        } else if (obj instanceof char[]) {
            c(sb, (char[]) obj);
        } else if (obj instanceof short[]) {
            n(sb, (short[]) obj);
        } else if (obj instanceof int[]) {
            h(sb, (int[]) obj);
        } else if (obj instanceof long[]) {
            k(sb, (long[]) obj);
        } else if (obj instanceof float[]) {
            f(sb, (float[]) obj);
        } else if (obj instanceof double[]) {
            e(sb, (double[]) obj);
        } else {
            l(sb, (Object[]) obj, map);
        }
    }

    public static void e(StringBuilder sb, double[] dArr) {
        sb.append('[');
        int length = dArr.length;
        for (int i = 0; i < length; i++) {
            sb.append(dArr[i]);
            if (i != length - 1) {
                sb.append(", ");
            }
        }
        sb.append(']');
    }

    public static void f(StringBuilder sb, float[] fArr) {
        sb.append('[');
        int length = fArr.length;
        for (int i = 0; i < length; i++) {
            sb.append(fArr[i]);
            if (i != length - 1) {
                sb.append(", ");
            }
        }
        sb.append(']');
    }

    public static final FormattingTuple format(String str, Object obj) {
        return arrayFormat(str, new Object[]{obj});
    }

    public static final Throwable g(Object[] objArr) {
        if (objArr != null && objArr.length != 0) {
            Object obj = objArr[objArr.length - 1];
            if (obj instanceof Throwable) {
                return (Throwable) obj;
            }
        }
        return null;
    }

    public static void h(StringBuilder sb, int[] iArr) {
        sb.append('[');
        int length = iArr.length;
        for (int i = 0; i < length; i++) {
            sb.append(iArr[i]);
            if (i != length - 1) {
                sb.append(", ");
            }
        }
        sb.append(']');
    }

    public static final boolean i(String str, int i) {
        return i >= 2 && str.charAt(i - 2) == '\\';
    }

    public static final boolean j(String str, int i) {
        return i != 0 && str.charAt(i - 1) == '\\';
    }

    public static void k(StringBuilder sb, long[] jArr) {
        sb.append('[');
        int length = jArr.length;
        for (int i = 0; i < length; i++) {
            sb.append(jArr[i]);
            if (i != length - 1) {
                sb.append(", ");
            }
        }
        sb.append(']');
    }

    public static void l(StringBuilder sb, Object[] objArr, Map<Object[], Object> map) {
        sb.append('[');
        if (!map.containsKey(objArr)) {
            map.put(objArr, null);
            int length = objArr.length;
            for (int i = 0; i < length; i++) {
                d(sb, objArr[i], map);
                if (i != length - 1) {
                    sb.append(", ");
                }
            }
            map.remove(objArr);
        } else {
            sb.append("...");
        }
        sb.append(']');
    }

    public static void m(StringBuilder sb, Object obj) {
        try {
            sb.append(obj.toString());
        } catch (Throwable th) {
            Util.report("SLF4J: Failed toString() invocation on an object of type [" + obj.getClass().getName() + "]", th);
            sb.append("[FAILED toString()]");
        }
    }

    public static void n(StringBuilder sb, short[] sArr) {
        sb.append('[');
        int length = sArr.length;
        for (int i = 0; i < length; i++) {
            sb.append((int) sArr[i]);
            if (i != length - 1) {
                sb.append(", ");
            }
        }
        sb.append(']');
    }

    public static Object[] o(Object[] objArr) {
        if (objArr != null && objArr.length != 0) {
            int length = objArr.length - 1;
            Object[] objArr2 = new Object[length];
            System.arraycopy(objArr, 0, objArr2, 0, length);
            return objArr2;
        }
        throw new IllegalStateException("non-sensical empty or null argument array");
    }

    public static final FormattingTuple format(String str, Object obj, Object obj2) {
        return arrayFormat(str, new Object[]{obj, obj2});
    }

    public static final FormattingTuple arrayFormat(String str, Object[] objArr, Throwable th) {
        int i;
        if (str == null) {
            return new FormattingTuple(null, objArr, th);
        }
        if (objArr == null) {
            return new FormattingTuple(str);
        }
        StringBuilder sb = new StringBuilder(str.length() + 50);
        int i2 = 0;
        int i3 = 0;
        while (i2 < objArr.length) {
            int indexOf = str.indexOf("{}", i3);
            if (indexOf == -1) {
                if (i3 == 0) {
                    return new FormattingTuple(str, objArr, th);
                }
                sb.append((CharSequence) str, i3, str.length());
                return new FormattingTuple(sb.toString(), objArr, th);
            }
            if (j(str, indexOf)) {
                if (!i(str, indexOf)) {
                    i2--;
                    sb.append((CharSequence) str, i3, indexOf - 1);
                    sb.append('{');
                    i = indexOf + 1;
                    i3 = i;
                    i2++;
                } else {
                    sb.append((CharSequence) str, i3, indexOf - 1);
                    d(sb, objArr[i2], new HashMap());
                }
            } else {
                sb.append((CharSequence) str, i3, indexOf);
                d(sb, objArr[i2], new HashMap());
            }
            i = indexOf + 2;
            i3 = i;
            i2++;
        }
        sb.append((CharSequence) str, i3, str.length());
        return new FormattingTuple(sb.toString(), objArr, th);
    }
}
