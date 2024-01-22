package com.google.mlkit.common.sdkinternal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.firebase.inject.Provider;
import java.util.concurrent.Executor;
@KeepForSdk
/* loaded from: classes10.dex */
public class ExecutorSelector {

    /* renamed from: a  reason: collision with root package name */
    public final Provider f11586a;

    public ExecutorSelector(@NonNull Provider provider) {
        this.f11586a = provider;
    }

    @NonNull
    @KeepForSdk
    public Executor getExecutorToUse(@Nullable Executor executor) {
        return executor != null ? executor : (Executor) this.f11586a.get();
    }
}
