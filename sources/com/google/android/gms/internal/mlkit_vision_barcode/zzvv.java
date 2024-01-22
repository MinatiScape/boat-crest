package com.google.android.gms.internal.mlkit_vision_barcode;

import android.os.IBinder;
import android.os.IInterface;
/* loaded from: classes9.dex */
public abstract class zzvv extends zzb implements zzvw {
    public static zzvw zza(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.mlkit.vision.barcode.aidls.IBarcodeScannerCreator");
        return queryLocalInterface instanceof zzvw ? (zzvw) queryLocalInterface : new zzvu(iBinder);
    }
}
