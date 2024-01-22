package com.google.android.gms.internal.mlkit_vision_barcode;

import android.os.SystemClock;
import java.util.concurrent.TimeUnit;
/* loaded from: classes9.dex */
public final class b extends zzbf {
    @Override // com.google.android.gms.internal.mlkit_vision_barcode.zzbf
    public final long zza() {
        return TimeUnit.MILLISECONDS.toNanos(SystemClock.elapsedRealtime());
    }
}
