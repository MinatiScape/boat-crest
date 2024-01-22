package com.google.android.gms.internal.auth;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;
/* loaded from: classes6.dex */
public final class c3 extends zzn {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ TaskCompletionSource f8519a;

    public c3(b bVar, TaskCompletionSource taskCompletionSource) {
        this.f8519a = taskCompletionSource;
    }

    @Override // com.google.android.gms.internal.auth.zzo
    public final void zzb(Status status, @Nullable Bundle bundle) {
        b.c(status, bundle, this.f8519a);
    }
}
