package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import androidx.annotation.BinderThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.util.VisibleForTesting;
@VisibleForTesting
/* loaded from: classes6.dex */
public final class zzd extends zzac {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public BaseGmsClient f8345a;
    public final int b;

    public zzd(@NonNull BaseGmsClient baseGmsClient, int i) {
        this.f8345a = baseGmsClient;
        this.b = i;
    }

    @Override // com.google.android.gms.common.internal.IGmsCallbacks
    @BinderThread
    public final void onPostInitComplete(int i, @NonNull IBinder iBinder, @Nullable Bundle bundle) {
        Preconditions.checkNotNull(this.f8345a, "onPostInitComplete can be called only once per call to getRemoteService");
        this.f8345a.onPostInitHandler(i, iBinder, bundle, this.b);
        this.f8345a = null;
    }

    @Override // com.google.android.gms.common.internal.IGmsCallbacks
    @BinderThread
    public final void zzb(int i, @Nullable Bundle bundle) {
        Log.wtf("GmsClient", "received deprecated onAccountValidationComplete callback, ignoring", new Exception());
    }

    @Override // com.google.android.gms.common.internal.IGmsCallbacks
    @BinderThread
    public final void zzc(int i, @NonNull IBinder iBinder, @NonNull zzk zzkVar) {
        BaseGmsClient baseGmsClient = this.f8345a;
        Preconditions.checkNotNull(baseGmsClient, "onPostInitCompleteWithConnectionInfo can be called only once per call togetRemoteService");
        Preconditions.checkNotNull(zzkVar);
        BaseGmsClient.i(baseGmsClient, zzkVar);
        onPostInitComplete(i, iBinder, zzkVar.h);
    }
}
