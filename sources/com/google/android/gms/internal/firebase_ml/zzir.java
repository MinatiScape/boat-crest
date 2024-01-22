package com.google.android.gms.internal.firebase_ml;

import java.nio.charset.Charset;
/* loaded from: classes7.dex */
public final class zzir {
    public static String zzd(byte[] bArr) {
        Charset charset = zzin.UTF_8;
        if (bArr == null) {
            return null;
        }
        return new String(bArr, charset);
    }
}
