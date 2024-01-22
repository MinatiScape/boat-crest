package com.google.android.gms.common.api.internal;

import android.app.Activity;
import androidx.annotation.NonNull;
import com.google.android.gms.common.annotation.KeepForSdk;
@KeepForSdk
/* loaded from: classes6.dex */
public abstract class ActivityLifecycleObserver {
    @NonNull
    @KeepForSdk
    public static final ActivityLifecycleObserver of(@NonNull Activity activity) {
        return new zab(a.a(activity));
    }

    @NonNull
    @KeepForSdk
    public abstract ActivityLifecycleObserver onStopCallOnce(@NonNull Runnable runnable);
}
