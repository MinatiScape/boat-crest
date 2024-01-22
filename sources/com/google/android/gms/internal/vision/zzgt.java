package com.google.android.gms.internal.vision;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Objects;
/* loaded from: classes10.dex */
public final class zzgt {

    /* renamed from: a  reason: collision with root package name */
    public static final Charset f10024a = Charset.forName("UTF-8");
    public static final byte[] zzxi;

    static {
        Charset.forName("ISO-8859-1");
        byte[] bArr = new byte[0];
        zzxi = bArr;
        ByteBuffer.wrap(bArr);
        zzft.a(bArr, 0, bArr.length, false);
    }

    public static <T> T a(T t) {
        Objects.requireNonNull(t);
        return t;
    }

    public static int b(int i, byte[] bArr, int i2, int i3) {
        for (int i4 = i2; i4 < i2 + i3; i4++) {
            i = (i * 31) + bArr[i4];
        }
        return i;
    }

    public static <T> T c(T t, String str) {
        Objects.requireNonNull(t, str);
        return t;
    }

    public static Object d(Object obj, Object obj2) {
        return ((zzic) obj).zzgi().zza((zzic) obj2).zzgb();
    }

    public static boolean e(zzic zzicVar) {
        if (zzicVar instanceof zzev) {
            zzev zzevVar = (zzev) zzicVar;
            return false;
        }
        return false;
    }

    public static int hashCode(byte[] bArr) {
        int length = bArr.length;
        int b = b(length, bArr, 0, length);
        if (b == 0) {
            return 1;
        }
        return b;
    }

    public static int zzab(long j) {
        return (int) (j ^ (j >>> 32));
    }

    public static boolean zzg(byte[] bArr) {
        return m4.i(bArr);
    }

    public static String zzh(byte[] bArr) {
        return new String(bArr, f10024a);
    }

    public static int zzm(boolean z) {
        return z ? 1231 : 1237;
    }
}
