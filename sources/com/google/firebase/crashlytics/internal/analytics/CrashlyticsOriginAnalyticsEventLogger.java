package com.google.firebase.crashlytics.internal.analytics;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.firebase.analytics.connector.AnalyticsConnector;
/* loaded from: classes10.dex */
public class CrashlyticsOriginAnalyticsEventLogger implements AnalyticsEventLogger {
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public final AnalyticsConnector f11129a;

    public CrashlyticsOriginAnalyticsEventLogger(@NonNull AnalyticsConnector analyticsConnector) {
        this.f11129a = analyticsConnector;
    }

    @Override // com.google.firebase.crashlytics.internal.analytics.AnalyticsEventLogger
    public void logEvent(@NonNull String str, @Nullable Bundle bundle) {
        this.f11129a.logEvent("clx", str, bundle);
    }
}
