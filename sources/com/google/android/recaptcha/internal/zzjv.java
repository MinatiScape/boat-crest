package com.google.android.recaptcha.internal;
/* loaded from: classes10.dex */
public final class zzjv extends RuntimeException {
    public zzjv(zzip zzipVar) {
        super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
    }

    public final zzhp zza() {
        return new zzhp(getMessage());
    }
}
