package com.google.android.gms.internal.vision;

import android.os.Binder;
/* loaded from: classes10.dex */
public final /* synthetic */ class zzat {
    public static <V> V zza(zzaw<V> zzawVar) {
        try {
            return zzawVar.zzt();
        } catch (SecurityException unused) {
            long clearCallingIdentity = Binder.clearCallingIdentity();
            try {
                return zzawVar.zzt();
            } finally {
                Binder.restoreCallingIdentity(clearCallingIdentity);
            }
        }
    }
}
