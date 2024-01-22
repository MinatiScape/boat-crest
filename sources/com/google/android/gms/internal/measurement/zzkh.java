package com.google.android.gms.internal.measurement;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Objects;
/* loaded from: classes8.dex */
public final class zzkh {

    /* renamed from: a  reason: collision with root package name */
    public static final Charset f8963a = Charset.forName("UTF-8");
    public static final byte[] zzc;
    public static final ByteBuffer zzd;
    public static final zzjc zze;

    static {
        Charset.forName("ISO-8859-1");
        byte[] bArr = new byte[0];
        zzc = bArr;
        zzd = ByteBuffer.wrap(bArr);
        int i = zzjc.zza;
        q2 q2Var = new q2(bArr, 0, 0, false, null);
        try {
            q2Var.a(0);
            zze = q2Var;
        } catch (zzkj e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static int a(int i, byte[] bArr, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            i = (i * 31) + bArr[i4];
        }
        return i;
    }

    public static <T> T b(T t) {
        Objects.requireNonNull(t);
        return t;
    }

    public static <T> T c(T t, String str) {
        Objects.requireNonNull(t, str);
        return t;
    }

    public static Object d(Object obj, Object obj2) {
        return ((zzlg) obj).zzbD().zzau((zzlg) obj2).zzaC();
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
        return new String(bArr, f8963a);
    }

    public static boolean zzi(byte[] bArr) {
        return y4.e(bArr);
    }
}
