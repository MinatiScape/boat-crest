package com.google.firebase.crashlytics;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.crashlytics.internal.Logger;
import com.google.firebase.crashlytics.internal.analytics.AnalyticsEventReceiver;
import java.util.Locale;
/* loaded from: classes10.dex */
public class d implements AnalyticsConnector.AnalyticsConnectorListener {

    /* renamed from: a  reason: collision with root package name */
    public AnalyticsEventReceiver f11122a;
    public AnalyticsEventReceiver b;

    public static void a(@Nullable AnalyticsEventReceiver analyticsEventReceiver, @NonNull String str, @NonNull Bundle bundle) {
        if (analyticsEventReceiver == null) {
            return;
        }
        analyticsEventReceiver.onEvent(str, bundle);
    }

    public final void b(@NonNull String str, @NonNull Bundle bundle) {
        AnalyticsEventReceiver analyticsEventReceiver;
        if ("clx".equals(bundle.getString("_o"))) {
            analyticsEventReceiver = this.f11122a;
        } else {
            analyticsEventReceiver = this.b;
        }
        a(analyticsEventReceiver, str, bundle);
    }

    public void c(@Nullable AnalyticsEventReceiver analyticsEventReceiver) {
        this.b = analyticsEventReceiver;
    }

    public void d(@Nullable AnalyticsEventReceiver analyticsEventReceiver) {
        this.f11122a = analyticsEventReceiver;
    }

    @Override // com.google.firebase.analytics.connector.AnalyticsConnector.AnalyticsConnectorListener
    public void onMessageTriggered(int i, @Nullable Bundle bundle) {
        String string;
        Logger.getLogger().v(String.format(Locale.US, "Analytics listener received message. ID: %d, Extras: %s", Integer.valueOf(i), bundle));
        if (bundle == null || (string = bundle.getString(AppMeasurementSdk.ConditionalUserProperty.NAME)) == null) {
            return;
        }
        Bundle bundle2 = bundle.getBundle("params");
        if (bundle2 == null) {
            bundle2 = new Bundle();
        }
        b(string, bundle2);
    }
}
