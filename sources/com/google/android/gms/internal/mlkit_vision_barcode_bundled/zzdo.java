package com.google.android.gms.internal.mlkit_vision_barcode_bundled;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
/* loaded from: classes8.dex */
public final class zzdo {
    public static final zzdo b = new zzdo(true);
    public static final /* synthetic */ int zzb = 0;

    /* renamed from: a  reason: collision with root package name */
    public final Map f9630a;

    public zzdo() {
        this.f9630a = new HashMap();
    }

    public static zzdo zza() {
        return b;
    }

    public final zzeb zzb(zzfo zzfoVar, int i) {
        return (zzeb) this.f9630a.get(new b0(zzfoVar, i));
    }

    public zzdo(boolean z) {
        this.f9630a = Collections.emptyMap();
    }
}
