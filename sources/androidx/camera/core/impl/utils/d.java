package androidx.camera.core.impl.utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.clevertap.android.sdk.Constants;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
/* loaded from: classes.dex */
public final class d {
    public static final Charset d = StandardCharsets.US_ASCII;
    public static final String[] e = {"", "BYTE", "STRING", "USHORT", "ULONG", "URATIONAL", "SBYTE", "UNDEFINED", "SSHORT", "SLONG", "SRATIONAL", "SINGLE", "DOUBLE", "IFD"};
    public static final int[] f = {0, 1, 1, 2, 4, 8, 1, 1, 2, 4, 8, 4, 8, 1};
    public static final byte[] g = {65, 83, 67, 73, 73, 0, 0, 0};

    /* renamed from: a  reason: collision with root package name */
    public final int f741a;
    public final int b;
    public final byte[] c;

    public d(int i, int i2, byte[] bArr) {
        this(i, i2, -1L, bArr);
    }

    @NonNull
    public static d a(@NonNull String str) {
        if (str.length() == 1 && str.charAt(0) >= '0' && str.charAt(0) <= '1') {
            return new d(1, 1, new byte[]{(byte) (str.charAt(0) - '0')});
        }
        byte[] bytes = str.getBytes(d);
        return new d(1, bytes.length, bytes);
    }

    @NonNull
    public static d b(@NonNull double[] dArr, @NonNull ByteOrder byteOrder) {
        ByteBuffer wrap = ByteBuffer.wrap(new byte[f[12] * dArr.length]);
        wrap.order(byteOrder);
        for (double d2 : dArr) {
            wrap.putDouble(d2);
        }
        return new d(12, dArr.length, wrap.array());
    }

    @NonNull
    public static d c(@NonNull int[] iArr, @NonNull ByteOrder byteOrder) {
        ByteBuffer wrap = ByteBuffer.wrap(new byte[f[9] * iArr.length]);
        wrap.order(byteOrder);
        for (int i : iArr) {
            wrap.putInt(i);
        }
        return new d(9, iArr.length, wrap.array());
    }

    @NonNull
    public static d d(@NonNull f[] fVarArr, @NonNull ByteOrder byteOrder) {
        ByteBuffer wrap = ByteBuffer.wrap(new byte[f[10] * fVarArr.length]);
        wrap.order(byteOrder);
        for (f fVar : fVarArr) {
            wrap.putInt((int) fVar.b());
            wrap.putInt((int) fVar.a());
        }
        return new d(10, fVarArr.length, wrap.array());
    }

    @NonNull
    public static d e(@NonNull String str) {
        byte[] bytes = (str + (char) 0).getBytes(d);
        return new d(2, bytes.length, bytes);
    }

    @NonNull
    public static d f(long j, @NonNull ByteOrder byteOrder) {
        return g(new long[]{j}, byteOrder);
    }

    @NonNull
    public static d g(@NonNull long[] jArr, @NonNull ByteOrder byteOrder) {
        ByteBuffer wrap = ByteBuffer.wrap(new byte[f[4] * jArr.length]);
        wrap.order(byteOrder);
        for (long j : jArr) {
            wrap.putInt((int) j);
        }
        return new d(4, jArr.length, wrap.array());
    }

    @NonNull
    public static d h(@NonNull f[] fVarArr, @NonNull ByteOrder byteOrder) {
        ByteBuffer wrap = ByteBuffer.wrap(new byte[f[5] * fVarArr.length]);
        wrap.order(byteOrder);
        for (f fVar : fVarArr) {
            wrap.putInt((int) fVar.b());
            wrap.putInt((int) fVar.a());
        }
        return new d(5, fVarArr.length, wrap.array());
    }

    @NonNull
    public static d i(@NonNull int[] iArr, @NonNull ByteOrder byteOrder) {
        ByteBuffer wrap = ByteBuffer.wrap(new byte[f[3] * iArr.length]);
        wrap.order(byteOrder);
        for (int i : iArr) {
            wrap.putShort((short) i);
        }
        return new d(3, iArr.length, wrap.array());
    }

    public double j(@NonNull ByteOrder byteOrder) {
        Object l = l(byteOrder);
        if (l != null) {
            if (l instanceof String) {
                return Double.parseDouble((String) l);
            }
            if (l instanceof long[]) {
                long[] jArr = (long[]) l;
                if (jArr.length == 1) {
                    return jArr[0];
                }
                throw new NumberFormatException("There are more than one component");
            } else if (l instanceof int[]) {
                int[] iArr = (int[]) l;
                if (iArr.length == 1) {
                    return iArr[0];
                }
                throw new NumberFormatException("There are more than one component");
            } else if (l instanceof double[]) {
                double[] dArr = (double[]) l;
                if (dArr.length == 1) {
                    return dArr[0];
                }
                throw new NumberFormatException("There are more than one component");
            } else if (l instanceof f[]) {
                f[] fVarArr = (f[]) l;
                if (fVarArr.length == 1) {
                    return fVarArr[0].c();
                }
                throw new NumberFormatException("There are more than one component");
            } else {
                throw new NumberFormatException("Couldn't find a double value");
            }
        }
        throw new NumberFormatException("NULL can't be converted to a double value");
    }

    @Nullable
    public String k(@NonNull ByteOrder byteOrder) {
        Object l = l(byteOrder);
        if (l == null) {
            return null;
        }
        if (l instanceof String) {
            return (String) l;
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        if (l instanceof long[]) {
            long[] jArr = (long[]) l;
            while (i < jArr.length) {
                sb.append(jArr[i]);
                i++;
                if (i != jArr.length) {
                    sb.append(Constants.SEPARATOR_COMMA);
                }
            }
            return sb.toString();
        } else if (l instanceof int[]) {
            int[] iArr = (int[]) l;
            while (i < iArr.length) {
                sb.append(iArr[i]);
                i++;
                if (i != iArr.length) {
                    sb.append(Constants.SEPARATOR_COMMA);
                }
            }
            return sb.toString();
        } else if (l instanceof double[]) {
            double[] dArr = (double[]) l;
            while (i < dArr.length) {
                sb.append(dArr[i]);
                i++;
                if (i != dArr.length) {
                    sb.append(Constants.SEPARATOR_COMMA);
                }
            }
            return sb.toString();
        } else if (l instanceof f[]) {
            f[] fVarArr = (f[]) l;
            while (i < fVarArr.length) {
                sb.append(fVarArr[i].b());
                sb.append('/');
                sb.append(fVarArr[i].a());
                i++;
                if (i != fVarArr.length) {
                    sb.append(Constants.SEPARATOR_COMMA);
                }
            }
            return sb.toString();
        } else {
            return null;
        }
    }

    /* JADX WARN: Not initialized variable reg: 3, insn: 0x019c: MOVE  (r2 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY]), block:B:152:0x019c */
    /* JADX WARN: Removed duplicated region for block: B:176:0x019f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object l(java.nio.ByteOrder r11) {
        /*
            Method dump skipped, instructions count: 452
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.camera.core.impl.utils.d.l(java.nio.ByteOrder):java.lang.Object");
    }

    public int m() {
        return f[this.f741a] * this.b;
    }

    public String toString() {
        return "(" + e[this.f741a] + ", data length:" + this.c.length + ")";
    }

    public d(int i, int i2, long j, byte[] bArr) {
        this.f741a = i;
        this.b = i2;
        this.c = bArr;
    }
}
