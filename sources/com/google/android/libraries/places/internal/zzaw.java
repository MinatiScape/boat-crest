package com.google.android.libraries.places.internal;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.Task;
/* loaded from: classes10.dex */
final /* synthetic */ class zzaw implements Continuation {
    public static final Continuation zza = new zzaw();

    private zzaw() {
    }

    @Override // com.google.android.gms.tasks.Continuation
    public final Object then(Task task) {
        return zzaq.zza(task);
    }
}
