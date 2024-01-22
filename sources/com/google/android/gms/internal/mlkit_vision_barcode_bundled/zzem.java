package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Objects;
/* loaded from: classes8.dex */
public final class zzem {

    /* renamed from: a  reason: collision with root package name */
    public static final Charset f9632a;
    public static final byte[] zzd;
    public static final ByteBuffer zze;
    public static final zzdf zzf;

    static {
        Charset.forName("US-ASCII");
        f9632a = Charset.forName("UTF-8");
        Charset.forName("ISO-8859-1");
        byte[] bArr = new byte[0];
        zzd = bArr;
        zze = ByteBuffer.wrap(bArr);
        int i = zzdf.zza;
        x xVar = new x(bArr, 0, 0, false, null);
        try {
            xVar.a(0);
            zzf = xVar;
        } catch (zzeo e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static int a(int i, byte[] bArr, int i2, int i3) {
        for (int i4 = i2; i4 < i2 + i3; i4++) {
            i = (i * 31) + bArr[i4];
        }
        return i;
    }

    public static Object b(Object obj, String str) {
        Objects.requireNonNull(obj, str);
        return obj;
    }

    public static int zza(boolean z) {
        return z ? 1231 : 1237;
    }

    public static String zzd(byte[] bArr) {
        return new String(bArr, f9632a);
    }
}
