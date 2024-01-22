package com.coveiot.analytics.firebase;

import android.app.Activity;
import android.content.Context;
import com.coveiot.analytics.AnalyticsClient;
import com.coveiot.analytics.AnalyticsLog;
import com.coveiot.analytics.AnalyticsUserProperty;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class CoveFirebaseAnalyticsClient implements AnalyticsClient {
    @NotNull
    public static final Companion Companion = new Companion(null);
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public static CoveFirebaseAnalyticsClient f2699a;
    @Nullable
    public static FirebaseAnalytics b;

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final CoveFirebaseAnalyticsClient getInstance() {
            if (CoveFirebaseAnalyticsClient.f2699a == null) {
                CoveFirebaseAnalyticsClient.f2699a = new CoveFirebaseAnalyticsClient(null);
            }
            CoveFirebaseAnalyticsClient coveFirebaseAnalyticsClient = CoveFirebaseAnalyticsClient.f2699a;
            Intrinsics.checkNotNull(coveFirebaseAnalyticsClient);
            return coveFirebaseAnalyticsClient;
        }
    }

    public CoveFirebaseAnalyticsClient() {
    }

    public /* synthetic */ CoveFirebaseAnalyticsClient(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public final void initAnalytics(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        b = FirebaseAnalytics.getInstance(context);
    }

    @Override // com.coveiot.analytics.AnalyticsClient
    public void logEvent(@NotNull AnalyticsLog log) {
        Intrinsics.checkNotNullParameter(log, "log");
        FirebaseAnalytics firebaseAnalytics = b;
        if (firebaseAnalytics != null) {
            try {
                Intrinsics.checkNotNull(firebaseAnalytics);
                String eventName = log.getEventName();
                Intrinsics.checkNotNull(eventName);
                firebaseAnalytics.logEvent(eventName, log.toBundle());
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        throw new RuntimeException("CoveFirebaseAnalyticsClient is not initialized. Please call initAnalytics().");
    }

    @Override // com.coveiot.analytics.AnalyticsClient
    public void logOnUserLogin(@Nullable Map<String, ? extends Object> map) {
    }

    @Override // com.coveiot.analytics.AnalyticsClient
    public void logPushProfile(@Nullable Map<String, ? extends Object> map) {
    }

    @Override // com.coveiot.analytics.AnalyticsClient
    public void logUserAuthInfo(@NotNull String userId, @NotNull String sessionId) {
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(sessionId, "sessionId");
        FirebaseAnalytics firebaseAnalytics = b;
        if (firebaseAnalytics != null) {
            Intrinsics.checkNotNull(firebaseAnalytics);
            firebaseAnalytics.setUserId(userId);
            if (sessionId.length() > 0) {
                putAnalyticsUserProperty(AnalyticsUserProperty.KeyName.USER_SESSION_ID, sessionId);
                return;
            }
            return;
        }
        throw new RuntimeException("CoveFirebaseAnalyticsClient is not initialized. Please call initAnalytics().");
    }

    @Override // com.coveiot.analytics.AnalyticsClient
    public void putAnalyticsUserProperty(@NotNull AnalyticsUserProperty.KeyName propertyKey, @NotNull String value) {
        Intrinsics.checkNotNullParameter(propertyKey, "propertyKey");
        Intrinsics.checkNotNullParameter(value, "value");
        FirebaseAnalytics firebaseAnalytics = b;
        if (firebaseAnalytics != null) {
            try {
                Intrinsics.checkNotNull(firebaseAnalytics);
                firebaseAnalytics.setUserProperty(propertyKey.textValue(), value);
                return;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        throw new RuntimeException("CoveFirebaseAnalyticsClient is not initialized. Please call initAnalytics().");
    }

    @Override // com.coveiot.analytics.AnalyticsClient
    public void setCurrentScreenName(@NotNull Activity activity, @NotNull String screenName, @NotNull String className) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(screenName, "screenName");
        Intrinsics.checkNotNullParameter(className, "className");
        FirebaseAnalytics firebaseAnalytics = b;
        if (firebaseAnalytics != null) {
            Intrinsics.checkNotNull(firebaseAnalytics);
            firebaseAnalytics.setCurrentScreen(activity, screenName, className);
            return;
        }
        throw new RuntimeException("CoveFirebaseAnalyticsClient is not initialized. Please call initAnalytics().");
    }
}
