package com.google.android.gms.internal.mlkit_vision_barcode;

import sun.misc.Unsafe;
/* loaded from: classes9.dex */
public final /* synthetic */ class zzef {
    public static /* synthetic */ boolean zza(Unsafe unsafe, Object obj, long j, Object obj2, Object obj3) {
        while (!unsafe.compareAndSwapObject(obj, j, obj2, obj3)) {
            if (unsafe.getObject(obj, j) != obj2) {
                return false;
            }
        }
        return true;
    }
}
