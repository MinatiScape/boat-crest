package com.google.android.gms.internal.mlkit_vision_barcode;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
/* loaded from: classes9.dex */
public final /* synthetic */ class zzed {
    public static /* synthetic */ boolean zza(AtomicReferenceFieldUpdater atomicReferenceFieldUpdater, Object obj, Object obj2, Object obj3) {
        while (!atomicReferenceFieldUpdater.compareAndSet(obj, obj2, obj3)) {
            if (atomicReferenceFieldUpdater.get(obj) != obj2) {
                return false;
            }
        }
        return true;
    }
}
