package com.google.android.gms.internal.firebase_ml;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Objects;
/* loaded from: classes7.dex */
public final class zzxd {

    /* renamed from: a  reason: collision with root package name */
    public static final Charset f8814a = Charset.forName("UTF-8");
    public static final byte[] zzcmh;

    static {
        Charset.forName("ISO-8859-1");
        byte[] bArr = new byte[0];
        zzcmh = bArr;
        ByteBuffer.wrap(bArr);
        zzwh.a(bArr, 0, bArr.length, false);
    }

    public static <T> T a(T t) {
        Objects.requireNonNull(t);
        return t;
    }

    public static <T> T b(T t, String str) {
        Objects.requireNonNull(t, str);
        return t;
    }

    public static int c(int i, byte[] bArr, int i2, int i3) {
        for (int i4 = i2; i4 < i2 + i3; i4++) {
            i = (i * 31) + bArr[i4];
        }
        return i;
    }

    public static Object d(Object obj, Object obj2) {
        return ((zzyk) obj).zzut().zza((zzyk) obj2).zzva();
    }

    public static boolean e(zzyk zzykVar) {
        if (zzykVar instanceof zzvn) {
            zzvn zzvnVar = (zzvn) zzykVar;
            return false;
        }
        return false;
    }

    public static int hashCode(byte[] bArr) {
        int length = bArr.length;
        int c = c(length, bArr, 0, length);
        if (c == 0) {
            return 1;
        }
        return c;
    }

    public static int zzaf(long j) {
        return (int) (j ^ (j >>> 32));
    }

    public static int zzaz(boolean z) {
        return z ? 1231 : 1237;
    }

    public static boolean zzi(byte[] bArr) {
        return e.l(bArr);
    }

    public static String zzj(byte[] bArr) {
        return new String(bArr, f8814a);
    }
}
