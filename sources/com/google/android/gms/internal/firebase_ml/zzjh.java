package com.google.android.gms.internal.firebase_ml;

import java.io.IOException;
/* loaded from: classes7.dex */
public final class zzjh {
    public static long zzb(zzjq zzjqVar) throws IOException {
        y0 y0Var = new y0();
        try {
            zzjqVar.writeTo(y0Var);
            y0Var.close();
            return y0Var.h;
        } catch (Throwable th) {
            y0Var.close();
            throw th;
        }
    }
}
