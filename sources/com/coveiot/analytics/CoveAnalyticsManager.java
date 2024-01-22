package com.coveiot.analytics;

import android.app.Activity;
import android.content.Context;
import com.coveiot.analytics.AnalyticsUserProperty;
import com.coveiot.analytics.clevertap.CoveCleverTapAnalyticsClient;
import com.coveiot.analytics.firebase.CoveFirebaseAnalyticsClient;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class CoveAnalyticsManager {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public static CoveAnalyticsManager f2697a;
    @Nullable
    public static AnalyticsClient b;
    @Nullable
    public static AnalyticsClient c;
    @NotNull
    public static final Companion Companion = new Companion(null);
    @NotNull
    public static AnalyticsType d = AnalyticsType.CLEVER_TAP;

    /* loaded from: classes2.dex */
    public static final class Companion {
        public Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final AnalyticsType getAnalyticsType$analytics_release() {
            return CoveAnalyticsManager.d;
        }

        @Nullable
        public final AnalyticsClient getCleverTapAnalyticsClient$analytics_release() {
            return CoveAnalyticsManager.c;
        }

        @Nullable
        public final AnalyticsClient getFireBaseAnalticsClient$analytics_release() {
            return CoveAnalyticsManager.b;
        }

        @NotNull
        public final CoveAnalyticsManager getInstance() {
            if (CoveAnalyticsManager.f2697a == null) {
                CoveAnalyticsManager.f2697a = new CoveAnalyticsManager(null);
            }
            CoveAnalyticsManager coveAnalyticsManager = CoveAnalyticsManager.f2697a;
            Intrinsics.checkNotNull(coveAnalyticsManager);
            return coveAnalyticsManager;
        }

        public final void initAnalyticsType(@NotNull AnalyticsType analyticsTypes) {
            Intrinsics.checkNotNullParameter(analyticsTypes, "analyticsTypes");
            setAnalyticsType$analytics_release(analyticsTypes);
        }

        public final void initialize(@NotNull Context context, @NotNull ArrayList<AnalyticsType> analyticsTypes) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(analyticsTypes, "analyticsTypes");
            Iterator<AnalyticsType> it = analyticsTypes.iterator();
            while (it.hasNext()) {
                AnalyticsType next = it.next();
                if (next == AnalyticsType.FIREBASE) {
                    CoveFirebaseAnalyticsClient.Companion companion = CoveFirebaseAnalyticsClient.Companion;
                    companion.getInstance().initAnalytics(context);
                    setFireBaseAnalticsClient$analytics_release(companion.getInstance());
                }
                if (next == AnalyticsType.CLEVER_TAP) {
                    CoveCleverTapAnalyticsClient.Companion companion2 = CoveCleverTapAnalyticsClient.Companion;
                    companion2.getInstance().initAnalytics(context);
                    setCleverTapAnalyticsClient$analytics_release(companion2.getInstance());
                }
            }
        }

        public final void setAnalyticsType$analytics_release(@NotNull AnalyticsType analyticsType) {
            Intrinsics.checkNotNullParameter(analyticsType, "<set-?>");
            CoveAnalyticsManager.d = analyticsType;
        }

        public final void setCleverTapAnalyticsClient$analytics_release(@Nullable AnalyticsClient analyticsClient) {
            CoveAnalyticsManager.c = analyticsClient;
        }

        public final void setFireBaseAnalticsClient$analytics_release(@Nullable AnalyticsClient analyticsClient) {
            CoveAnalyticsManager.b = analyticsClient;
        }
    }

    public CoveAnalyticsManager() {
    }

    public /* synthetic */ CoveAnalyticsManager(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public final void logAnalyticEvent(@NotNull AnalyticsLog log) {
        AnalyticsClient analyticsClient;
        Intrinsics.checkNotNullParameter(log, "log");
        if (d == AnalyticsType.ALL) {
            AnalyticsClient analyticsClient2 = b;
            if (analyticsClient2 != null) {
                Intrinsics.checkNotNull(analyticsClient2);
                analyticsClient2.logEvent(log);
            }
            AnalyticsClient analyticsClient3 = c;
            if (analyticsClient3 != null) {
                Intrinsics.checkNotNull(analyticsClient3);
                analyticsClient3.logEvent(log);
            }
        } else if (d == AnalyticsType.CLEVER_TAP) {
            AnalyticsClient analyticsClient4 = c;
            if (analyticsClient4 != null) {
                Intrinsics.checkNotNull(analyticsClient4);
                analyticsClient4.logEvent(log);
            }
        } else if (d != AnalyticsType.FIREBASE || (analyticsClient = b) == null) {
        } else {
            Intrinsics.checkNotNull(analyticsClient);
            analyticsClient.logEvent(log);
        }
    }

    public final void logEvent(@NotNull AnalyticsLog log) {
        Intrinsics.checkNotNullParameter(log, "log");
        AnalyticsClient analyticsClient = b;
        if (analyticsClient != null) {
            Intrinsics.checkNotNull(analyticsClient);
            analyticsClient.logEvent(log);
            return;
        }
        AnalyticsClient analyticsClient2 = c;
        if (analyticsClient2 != null) {
            Intrinsics.checkNotNull(analyticsClient2);
            analyticsClient2.logEvent(log);
        }
    }

    public final void logUserAuthInfo(@NotNull String userId, @NotNull String sessionId) {
        Intrinsics.checkNotNullParameter(userId, "userId");
        Intrinsics.checkNotNullParameter(sessionId, "sessionId");
        AnalyticsClient analyticsClient = b;
        if (analyticsClient != null) {
            Intrinsics.checkNotNull(analyticsClient);
            analyticsClient.logUserAuthInfo(userId, sessionId);
        }
        AnalyticsClient analyticsClient2 = c;
        if (analyticsClient2 != null) {
            Intrinsics.checkNotNull(analyticsClient2);
            analyticsClient2.logUserAuthInfo(userId, sessionId);
        }
    }

    public final void putAnalyticsUserProperty(@NotNull AnalyticsUserProperty.KeyName propertyKey, @NotNull String value) {
        Intrinsics.checkNotNullParameter(propertyKey, "propertyKey");
        Intrinsics.checkNotNullParameter(value, "value");
        AnalyticsClient analyticsClient = b;
        if (analyticsClient != null) {
            Intrinsics.checkNotNull(analyticsClient);
            analyticsClient.putAnalyticsUserProperty(propertyKey, value);
        }
        AnalyticsClient analyticsClient2 = c;
        if (analyticsClient2 != null) {
            Intrinsics.checkNotNull(analyticsClient2);
            analyticsClient2.putAnalyticsUserProperty(propertyKey, value);
        }
    }

    public final void resetClient(@NotNull AnalyticsType eventType) {
        Intrinsics.checkNotNullParameter(eventType, "eventType");
        if (eventType == AnalyticsType.CLEVER_TAP) {
            c = null;
        }
        if (eventType == AnalyticsType.FIREBASE) {
            b = null;
        }
    }

    public final void setCurrentScreenName(@NotNull Activity activity, @NotNull String screenName, @NotNull String className) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(screenName, "screenName");
        Intrinsics.checkNotNullParameter(className, "className");
        AnalyticsClient analyticsClient = b;
        if (analyticsClient != null) {
            Intrinsics.checkNotNull(analyticsClient);
            analyticsClient.setCurrentScreenName(activity, screenName, className);
        }
        AnalyticsClient analyticsClient2 = c;
        if (analyticsClient2 != null) {
            Intrinsics.checkNotNull(analyticsClient2);
            analyticsClient2.setCurrentScreenName(activity, screenName, className);
        }
    }

    public final void setOnUserLogin(@NotNull Map<String, ? extends Object> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        AnalyticsClient analyticsClient = c;
        if (analyticsClient != null) {
            Intrinsics.checkNotNull(analyticsClient);
            analyticsClient.logOnUserLogin(data);
        }
    }

    public final void setPushProfile(@NotNull Map<String, ? extends Object> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        AnalyticsClient analyticsClient = c;
        if (analyticsClient != null) {
            Intrinsics.checkNotNull(analyticsClient);
            analyticsClient.logPushProfile(data);
        }
    }
}
