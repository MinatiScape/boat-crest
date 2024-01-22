package com.google.android.gms.common.internal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.util.VisibleForTesting;
@KeepForSdk
/* loaded from: classes6.dex */
public final class RootTelemetryConfigManager {
    @Nullable
    public static RootTelemetryConfigManager b;
    public static final RootTelemetryConfiguration c = new RootTelemetryConfiguration(0, false, false, 0, 0);
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public RootTelemetryConfiguration f8331a;

    @NonNull
    @KeepForSdk
    public static synchronized RootTelemetryConfigManager getInstance() {
        RootTelemetryConfigManager rootTelemetryConfigManager;
        synchronized (RootTelemetryConfigManager.class) {
            if (b == null) {
                b = new RootTelemetryConfigManager();
            }
            rootTelemetryConfigManager = b;
        }
        return rootTelemetryConfigManager;
    }

    @Nullable
    @KeepForSdk
    public RootTelemetryConfiguration getConfig() {
        return this.f8331a;
    }

    @VisibleForTesting
    public final synchronized void zza(@Nullable RootTelemetryConfiguration rootTelemetryConfiguration) {
        if (rootTelemetryConfiguration == null) {
            this.f8331a = c;
            return;
        }
        RootTelemetryConfiguration rootTelemetryConfiguration2 = this.f8331a;
        if (rootTelemetryConfiguration2 == null || rootTelemetryConfiguration2.getVersion() < rootTelemetryConfiguration.getVersion()) {
            this.f8331a = rootTelemetryConfiguration;
        }
    }
}
