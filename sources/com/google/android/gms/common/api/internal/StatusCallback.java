package com.google.android.gms.common.api.internal;

import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.api.internal.IStatusCallback;
@KeepForSdk
/* loaded from: classes6.dex */
public class StatusCallback extends IStatusCallback.Stub {
    @KeepForSdk

    /* renamed from: a  reason: collision with root package name */
    public final BaseImplementation.ResultHolder<Status> f8263a;

    @KeepForSdk
    public StatusCallback(@NonNull BaseImplementation.ResultHolder<Status> resultHolder) {
        this.f8263a = resultHolder;
    }

    @Override // com.google.android.gms.common.api.internal.IStatusCallback
    @KeepForSdk
    public void onResult(@NonNull Status status) {
        this.f8263a.setResult(status);
    }
}
