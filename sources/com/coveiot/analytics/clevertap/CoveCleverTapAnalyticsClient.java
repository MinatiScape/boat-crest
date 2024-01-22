package com.coveiot.analytics.clevertap;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.clevertap.android.sdk.CleverTapAPI;
import com.coveiot.analytics.AnalyticsClient;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.AnalyticsUserProperty;
import java.util.HashMap;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class CoveCleverTapAnalyticsClient implements AnalyticsClient {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public static CoveCleverTapAnalyticsClient f2698a;
    @Nullable
    public static CleverTapAPI b;

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final CoveCleverTapAnalyticsClient getInstance() {
            if (CoveCleverTapAnalyticsClient.f2698a == null) {
                CoveCleverTapAnalyticsClient.f2698a = new CoveCleverTapAnalyticsClient(null);
            }
            CoveCleverTapAnalyticsClient coveCleverTapAnalyticsClient = CoveCleverTapAnalyticsClient.f2698a;
            Intrinsics.checkNotNull(coveCleverTapAnalyticsClient);
            return coveCleverTapAnalyticsClient;
        }
    }

    public CoveCleverTapAnalyticsClient() {
    }

    public /* synthetic */ CoveCleverTapAnalyticsClient(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Nullable
    public final Map<String, String> bundleToMap(@NotNull Bundle extras) {
        Intrinsics.checkNotNullParameter(extras, "extras");
        HashMap hashMap = new HashMap();
        for (String str : extras.keySet()) {
            hashMap.put(str, extras.getString(str));
        }
        return hashMap;
    }

    public final void initAnalytics(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        b = CleverTapAPI.getDefaultInstance(context);
    }

    @Override // com.coveiot.analytics.AnalyticsClient
    public void logEvent(@NotNull AnalyticsLog log) {
        Intrinsics.checkNotNullParameter(log, "log");
        CleverTapAPI cleverTapAPI = b;
        if (cleverTapAPI != null) {
            try {
                Intrinsics.checkNotNull(cleverTapAPI);
                String eventName = log.getEventName();
                Intrinsics.checkNotNull(eventName);
                cleverTapAPI.pushEvent(eventName, log.getHashMapData());
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        throw new RuntimeException("CoveCleverTapAnalyticsClient is not initialized. Please call initAnalytics().");
    }

    @Override // com.coveiot.analytics.AnalyticsClient
    public void logOnUserLogin(@Nullable Map<String, ? extends Object> map) {
        CleverTapAPI cleverTapAPI = b;
        Intrinsics.checkNotNull(cleverTapAPI);
        cleverTapAPI.onUserLogin(map);
    }

    @Override // com.coveiot.analytics.AnalyticsClient
    public void logPushProfile(@Nullable Map<String, ? extends Object> map) {
        CleverTapAPI cleverTapAPI = b;
        Intrinsics.checkNotNull(cleverTapAPI);
        cleverTapAPI.pushProfile(map);
    }

    @Override // com.coveiot.analytics.AnalyticsClient
    public void logUserAuthInfo(@NotNull String userId, @NotNull String sessionId) {
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(sessionId, "sessionId");
    }

    @Override // com.coveiot.analytics.AnalyticsClient
    public void putAnalyticsUserProperty(@NotNull AnalyticsUserProperty.KeyName propertyKey, @NotNull String value) {
        Intrinsics.checkNotNullParameter(propertyKey, "propertyKey");
        Intrinsics.checkNotNullParameter(value, "value");
    }

    @Override // com.coveiot.analytics.AnalyticsClient
    public void setCurrentScreenName(@NotNull Activity activity, @NotNull String screenName, @NotNull String className) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(screenName, "screenName");
        Intrinsics.checkNotNullParameter(className, "className");
    }
}
