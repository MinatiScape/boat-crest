package com.google.android.libraries.places.internal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.tasks.CancellationTokenSource;
import com.google.android.gms.tasks.Task;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes10.dex */
public abstract class zzet<T> {
    private Task<T> zza;

    private zzet() {
    }

    @NonNull
    public abstract CancellationTokenSource zza();

    public final void zza(@NonNull Task<T> task) {
        this.zza = task;
    }

    @Nullable
    public final Task<T> zzc() {
        return this.zza;
    }
}
