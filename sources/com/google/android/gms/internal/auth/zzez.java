package com.google.android.gms.internal.auth;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Objects;
/* loaded from: classes6.dex */
public final class zzez {

    /* renamed from: a  reason: collision with root package name */
    public static final Charset f8562a;
    public static final byte[] zzd;
    public static final ByteBuffer zze;
    public static final zzei zzf;

    static {
        Charset.forName("US-ASCII");
        f8562a = Charset.forName("UTF-8");
        Charset.forName("ISO-8859-1");
        byte[] bArr = new byte[0];
        zzd = bArr;
        zze = ByteBuffer.wrap(bArr);
        int i = zzei.zza;
        w0 w0Var = new w0(bArr, 0, 0, false, null);
        try {
            w0Var.a(0);
            zzf = w0Var;
        } catch (zzfa e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static int a(int i, byte[] bArr, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            i = (i * 31) + bArr[i4];
        }
        return i;
    }

    public static Object b(Object obj) {
        Objects.requireNonNull(obj);
        return obj;
    }

    public static Object c(Object obj, String str) {
        Objects.requireNonNull(obj, str);
        return obj;
    }

    public static Object d(Object obj, Object obj2) {
        return ((zzfw) obj).zzd().zzc((zzfw) obj2).zzg();
    }

    public static int zza(boolean z) {
        return z ? 1231 : 1237;
    }

    public static int zzb(byte[] bArr) {
        int length = bArr.length;
        int a2 = a(length, bArr, 0, length);
        if (a2 == 0) {
            return 1;
        }
        return a2;
    }

    public static int zzc(long j) {
        return (int) (j ^ (j >>> 32));
    }

    public static String zzh(byte[] bArr) {
        return new String(bArr, f8562a);
    }

    public static boolean zzi(byte[] bArr) {
        return y2.c(bArr);
    }
}
