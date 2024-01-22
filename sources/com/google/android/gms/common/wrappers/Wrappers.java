package com.google.android.gms.common.wrappers;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.VisibleForTesting;
@KeepForSdk
/* loaded from: classes6.dex */
public class Wrappers {
    public static final Wrappers b = new Wrappers();
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public PackageManagerWrapper f8378a = null;

    @NonNull
    @KeepForSdk
    public static PackageManagerWrapper packageManager(@NonNull Context context) {
        return b.zza(context);
    }

    @NonNull
    @VisibleForTesting
    public final synchronized PackageManagerWrapper zza(@NonNull Context context) {
        if (this.f8378a == null) {
            if (context.getApplicationContext() != null) {
                context = context.getApplicationContext();
            }
            this.f8378a = new PackageManagerWrapper(context);
        }
        return this.f8378a;
    }
}
