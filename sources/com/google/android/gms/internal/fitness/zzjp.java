package com.google.android.gms.internal.fitness;

import java.util.List;
/* loaded from: classes8.dex */
public final class zzjp extends RuntimeException {
    private final List<String> zzabr;

    public zzjp(zzik zzikVar) {
        super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
        this.zzabr = null;
    }
}
