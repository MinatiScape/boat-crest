package com.google.android.libraries.places.internal;

import java.util.List;
/* loaded from: classes10.dex */
public final class zzux extends RuntimeException {
    private final List<String> zza;

    public zzux(zzto zztoVar) {
        super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
        this.zza = null;
    }
}
