package com.google.android.gms.internal.mlkit_vision_text_common;

import android.os.IBinder;
import android.os.IInterface;
/* loaded from: classes6.dex */
public abstract class zzoy extends zzb implements zzoz {
    public static zzoz zza(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.mlkit.vision.text.aidls.ITextRecognizerCreator");
        return queryLocalInterface instanceof zzoz ? (zzoz) queryLocalInterface : new zzox(iBinder);
    }
}
