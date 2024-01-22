package com.clevertap.android.sdk.leanplum;

import android.content.Context;
import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.Logger;
import java.util.Map;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes2.dex */
public final class LeanplumCT {
    @NotNull
    public static final LeanplumCT INSTANCE = new LeanplumCT();
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public static CTWrapper f2642a;

    @JvmStatic
    public static final void advanceTo(@Nullable String str) {
        advanceTo(str, null, null);
    }

    @JvmStatic
    @NotNull
    public static final String getPurchaseEventName() {
        return "Purchase";
    }

    @JvmStatic
    public static final void initWithContext(@Nullable Context context) {
        if (context != null) {
            f2642a = new CTWrapper(new CleverTapProvider(context));
        }
    }

    @JvmStatic
    public static final void initWithInstance(@Nullable CleverTapAPI cleverTapAPI) {
        if (cleverTapAPI != null) {
            f2642a = new CTWrapper(new CleverTapProvider(cleverTapAPI));
        }
    }

    @JvmStatic
    public static final void setLogLevel(@NotNull CleverTapAPI.LogLevel logLevel) {
        Intrinsics.checkNotNullParameter(logLevel, "logLevel");
        CleverTapAPI.setDebugLevel(logLevel);
    }

    @JvmStatic
    public static final void setTrafficSourceInfo(@Nullable Map<String, String> map) {
        CTWrapper a2;
        if (map == null || (a2 = INSTANCE.a()) == null) {
            return;
        }
        a2.setTrafficSourceInfo(map);
    }

    @JvmStatic
    public static final void setUserAttributes(@Nullable Map<String, ? extends Object> map) {
        CTWrapper a2 = INSTANCE.a();
        if (a2 != null) {
            a2.setUserAttributes(map);
        }
    }

    @JvmStatic
    public static final void setUserId(@Nullable String str) {
        CTWrapper a2 = INSTANCE.a();
        if (a2 != null) {
            a2.setUserId(str);
        }
    }

    @JvmStatic
    public static final void track(@Nullable String str) {
        track(str, 0.0d, null, null);
    }

    @JvmStatic
    public static final void trackGooglePlayPurchase(@Nullable String str, long j, @Nullable String str2, @Nullable String str3, @Nullable String str4) {
        trackGooglePlayPurchase(getPurchaseEventName(), str, j, str2, str3, str4, null);
    }

    @JvmStatic
    public static final void trackPurchase(@NotNull String event, double d, @Nullable String str, @Nullable Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(event, "event");
        CTWrapper a2 = INSTANCE.a();
        if (a2 != null) {
            a2.trackPurchase(event, d, str, map);
        }
    }

    public final CTWrapper a() {
        if (f2642a == null) {
            Logger.i("LeanplumCT", "Please initialize LeanplumCT before using it.");
        }
        return f2642a;
    }

    @JvmStatic
    public static final void advanceTo(@Nullable String str, @Nullable String str2) {
        advanceTo(str, str2, null);
    }

    @JvmStatic
    public static final void setUserAttributes(@Nullable String str, @Nullable Map<String, ? extends Object> map) {
        if (str != null) {
            setUserId(str);
        }
        setUserAttributes(map);
    }

    @JvmStatic
    public static final void track(@Nullable String str, double d) {
        track(str, d, null, null);
    }

    @JvmStatic
    public static final void advanceTo(@Nullable String str, @Nullable Map<String, ? extends Object> map) {
        advanceTo(str, null, map);
    }

    @JvmStatic
    public static final void track(@Nullable String str, @Nullable String str2) {
        track(str, 0.0d, str2, null);
    }

    @JvmStatic
    public static final void trackGooglePlayPurchase(@NotNull String item, long j, @NotNull String currencyCode, @NotNull String purchaseData, @NotNull String dataSignature, @Nullable Map<String, ? extends Object> map) {
        Intrinsics.checkNotNullParameter(item, "item");
        Intrinsics.checkNotNullParameter(currencyCode, "currencyCode");
        Intrinsics.checkNotNullParameter(purchaseData, "purchaseData");
        Intrinsics.checkNotNullParameter(dataSignature, "dataSignature");
        trackGooglePlayPurchase(getPurchaseEventName(), item, j, currencyCode, purchaseData, dataSignature, map);
    }

    @JvmStatic
    public static final void advanceTo(@Nullable String str, @Nullable String str2, @Nullable Map<String, ? extends Object> map) {
        CTWrapper a2 = INSTANCE.a();
        if (a2 != null) {
            a2.advanceTo(str, str2, map);
        }
    }

    @JvmStatic
    public static final void track(@Nullable String str, @Nullable Map<String, ? extends Object> map) {
        track(str, 0.0d, null, map);
    }

    @JvmStatic
    public static final void track(@Nullable String str, double d, @Nullable Map<String, ? extends Object> map) {
        track(str, d, null, map);
    }

    @JvmStatic
    public static final void trackGooglePlayPurchase(@Nullable String str, @Nullable String str2, long j, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable Map<String, ? extends Object> map) {
        if (str == null || str.length() == 0) {
            Logger.i("LeanplumCT", "Failed to call trackGooglePlayPurchase, event name is null");
            return;
        }
        CTWrapper a2 = INSTANCE.a();
        if (a2 != null) {
            a2.trackGooglePlayPurchase(str, str2, j / 1000000.0d, str3, str4, str5, map);
        }
    }

    @JvmStatic
    public static final void track(@Nullable String str, double d, @Nullable String str2) {
        track(str, d, str2, null);
    }

    @JvmStatic
    public static final void track(@Nullable String str, double d, @Nullable String str2, @Nullable Map<String, ? extends Object> map) {
        CTWrapper a2 = INSTANCE.a();
        if (a2 != null) {
            a2.track(str, d, str2, map);
        }
    }
}
