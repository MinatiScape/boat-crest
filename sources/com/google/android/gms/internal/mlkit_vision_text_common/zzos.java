package com.google.android.gms.internal.mlkit_vision_text_common;

import androidx.annotation.Nullable;
/* loaded from: classes6.dex */
public final class zzos {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public static zzos f9956a;

    public static synchronized zzos zza() {
        zzos zzosVar;
        synchronized (zzos.class) {
            if (f9956a == null) {
                f9956a = new zzos();
            }
            zzosVar = f9956a;
        }
        return zzosVar;
    }
}
