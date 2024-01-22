package com.google.android.gms.internal.vision;

import java.io.Serializable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
/* loaded from: classes10.dex */
public final class zzcx {
    public static <T> zzcu<T> zza(zzcu<T> zzcuVar) {
        if ((zzcuVar instanceof t) || (zzcuVar instanceof r)) {
            return zzcuVar;
        }
        if (zzcuVar instanceof Serializable) {
            return new r(zzcuVar);
        }
        return new t(zzcuVar);
    }

    public static <T> zzcu<T> zze(@NullableDecl T t) {
        return new s(t);
    }
}
