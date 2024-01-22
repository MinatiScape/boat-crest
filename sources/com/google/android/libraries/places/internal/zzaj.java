package com.google.android.libraries.places.internal;

import androidx.annotation.Nullable;
import com.google.android.gms.tasks.CancellationToken;
import com.google.android.libraries.places.internal.zzdc;
import java.util.Map;
/* loaded from: classes10.dex */
public abstract class zzaj<TypeT, RequestT extends zzdc> {
    private final RequestT zza;

    public zzaj(RequestT requestt) {
        this.zza = requestt;
    }

    public final RequestT zza() {
        return this.zza;
    }

    @Nullable
    public final CancellationToken zzb() {
        return this.zza.getCancellationToken();
    }

    public abstract Map<String, String> zzc();

    public abstract String zzd();
}
