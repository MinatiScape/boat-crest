package com.google.android.gms.internal.vision;

import java.util.List;
/* loaded from: classes10.dex */
public final class zzjk extends RuntimeException {
    private final List<String> zzaao;

    public zzjk(zzic zzicVar) {
        super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
        this.zzaao = null;
    }
}
