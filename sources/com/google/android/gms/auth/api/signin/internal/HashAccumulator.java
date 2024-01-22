package com.google.android.gms.auth.api.signin.internal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
@KeepForSdk
/* loaded from: classes6.dex */
public class HashAccumulator {
    @VisibleForTesting
    public static int b = 31;

    /* renamed from: a  reason: collision with root package name */
    public int f8208a = 1;

    @NonNull
    @CanIgnoreReturnValue
    @KeepForSdk
    public HashAccumulator addObject(@Nullable Object obj) {
        this.f8208a = (b * this.f8208a) + (obj == null ? 0 : obj.hashCode());
        return this;
    }

    @KeepForSdk
    public int hash() {
        return this.f8208a;
    }

    @NonNull
    @CanIgnoreReturnValue
    public final HashAccumulator zaa(boolean z) {
        this.f8208a = (b * this.f8208a) + (z ? 1 : 0);
        return this;
    }
}
