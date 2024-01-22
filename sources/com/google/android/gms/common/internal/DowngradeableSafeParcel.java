package com.google.android.gms.common.internal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
@KeepForSdk
/* loaded from: classes6.dex */
public abstract class DowngradeableSafeParcel extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Object i = new Object();
    public boolean h = false;

    @KeepForSdk
    public static boolean canUnparcelSafely(@NonNull String str) {
        synchronized (i) {
        }
        return true;
    }

    @Nullable
    @KeepForSdk
    public static Integer getUnparcelClientVersion() {
        synchronized (i) {
        }
        return null;
    }

    @KeepForSdk
    public abstract boolean prepareForClientVersion(int i2);

    @KeepForSdk
    public void setShouldDowngrade(boolean z) {
        this.h = z;
    }

    @KeepForSdk
    public boolean shouldDowngrade() {
        return this.h;
    }
}
