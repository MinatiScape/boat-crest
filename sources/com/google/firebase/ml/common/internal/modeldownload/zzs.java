package com.google.firebase.ml.common.internal.modeldownload;

import androidx.annotation.Nullable;
/* loaded from: classes10.dex */
public final class zzs {
    public static final zzs zzbls = new zzs(zzr.OK, null);

    /* renamed from: a  reason: collision with root package name */
    public final zzr f11397a;

    public zzs(zzr zzrVar, @Nullable String str) {
        this.f11397a = zzrVar;
    }

    public final boolean isValid() {
        return this.f11397a == zzr.OK;
    }

    public final zzr zzoy() {
        return this.f11397a;
    }
}
