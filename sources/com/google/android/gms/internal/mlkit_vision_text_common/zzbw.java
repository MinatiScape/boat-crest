package com.google.android.gms.internal.mlkit_vision_text_common;

import java.util.List;
import java.util.RandomAccess;
/* loaded from: classes6.dex */
public final class zzbw {
    public static List zza(List list, zzu zzuVar) {
        if (list instanceof RandomAccess) {
            return new h0(list, zzuVar);
        }
        return new j0(list, zzuVar);
    }
}
