package com.google.android.gms.internal.clearcut;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Objects;
/* loaded from: classes7.dex */
public final class zzci {

    /* renamed from: a  reason: collision with root package name */
    public static final Charset f8618a = Charset.forName("UTF-8");
    public static final byte[] zzkt;

    static {
        Charset.forName("ISO-8859-1");
        byte[] bArr = new byte[0];
        zzkt = bArr;
        ByteBuffer.wrap(bArr);
        zzbk.a(bArr, 0, bArr.length, false);
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

    public static Object c(Object obj, Object obj2) {
        return ((zzdo) obj).zzbc().zza((zzdo) obj2).zzbi();
    }

    public static <T> T d(T t, String str) {
        Objects.requireNonNull(t, str);
        return t;
    }

    public static boolean e(zzdo zzdoVar) {
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

    public static int zzc(boolean z) {
        return z ? 1231 : 1237;
    }

    public static boolean zze(byte[] bArr) {
        return p2.h(bArr);
    }

    public static String zzf(byte[] bArr) {
        return new String(bArr, f8618a);
    }

    public static int zzl(long j) {
        return (int) (j ^ (j >>> 32));
    }
}
