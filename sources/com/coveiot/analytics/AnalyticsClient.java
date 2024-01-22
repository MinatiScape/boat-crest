package com.coveiot.analytics;

import android.app.Activity;
import com.coveiot.analytics.AnalyticsUserProperty;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public interface AnalyticsClient {
    void logEvent(@NotNull AnalyticsLog analyticsLog);

    void logOnUserLogin(@Nullable Map<String, ? extends Object> map);

    void logPushProfile(@Nullable Map<String, ? extends Object> map);

    void logUserAuthInfo(@NotNull String str, @NotNull String str2);

    void putAnalyticsUserProperty(@NotNull AnalyticsUserProperty.KeyName keyName, @NotNull String str);

    void setCurrentScreenName(@NotNull Activity activity, @NotNull String str, @NotNull String str2);
}
