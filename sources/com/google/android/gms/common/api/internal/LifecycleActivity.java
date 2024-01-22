package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.content.ContextWrapper;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
@KeepForSdk
/* loaded from: classes6.dex */
public class LifecycleActivity {

    /* renamed from: a  reason: collision with root package name */
    public final Object f8256a;

    public LifecycleActivity(@NonNull Activity activity) {
        Preconditions.checkNotNull(activity, "Activity must not be null");
        this.f8256a = activity;
    }

    @NonNull
    public final Activity zza() {
        return (Activity) this.f8256a;
    }

    @NonNull
    public final FragmentActivity zzb() {
        return (FragmentActivity) this.f8256a;
    }

    public final boolean zzc() {
        return this.f8256a instanceof Activity;
    }

    public final boolean zzd() {
        return this.f8256a instanceof FragmentActivity;
    }

    @KeepForSdk
    public LifecycleActivity(@NonNull ContextWrapper contextWrapper) {
        throw new UnsupportedOperationException();
    }
}
