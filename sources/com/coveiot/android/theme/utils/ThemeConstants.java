package com.coveiot.android.theme.utils;

import com.coveiot.covepreferences.UserDataManager;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public enum ThemeConstants {
    REMOTE_CONFIG_DEVICE_LIST("device_filter_android"),
    REMOTE_CONFIG_DEVICE_LIST_NEW("device_model_listing_new"),
    REMOTE_CONFIG_SCAN_ON_DISCONNECT_CRITERIA("scan_on_disconnect_criteria"),
    REMOTE_CONFIG_ONEK_LIST("onek_filter_android"),
    REMOTE_CONFIG_SLEEP_ENERGY_SCORE_LIST("sleep_score_filter_android"),
    BATTERY_OPTIMIZATION_CONFIG(UserDataManager.BATTERY_OPTIMIZATION_CONFIG),
    IS_WEATHER_FROM_SETTING_SCREEN("is_weather_from_setting_screen"),
    BOAT_COINS_VISIBILITY("boat_coins_visibility"),
    SHOULD_USE_REFLECTION_API_PAIRING("shouldUseReflectAPIForBonding"),
    REMOTE_CONFIG_DEVICE_CAPABILITY_CONFIG("device_capability_config"),
    ACTION_CONNECTION_STATE_CHANGED("ACTION_CONNECTION_STATE_CHANGED"),
    NOTIFY_IDENTIFIER("notifIdentifier"),
    INTENT_EXTRA_FROM_NOTIFICATION("FROM_NOTIFICATION"),
    INTENT_EXTRA_FROM_DASHBOARD("FROM_DASHBOARD"),
    FROM_SMART_GRID("FROM_SMART_GRID"),
    FCM_NOTIFICATION_TYPE("FCM_NOTIFICATION_TYPE"),
    FITNESS_CHALLENGES("fitness_challenges"),
    RSA_ENCRYPTDECRYPT_KEYS("rsa_encryptdecrypt_keys"),
    CONTACTUS_PREFERMODE_VISIBILITY("contactus_prefermode_visibility"),
    OTP_RESEND_TIMER_CONFIG("otp_resend_timer_config"),
    FEATURE_MAPPING("feature_mapping"),
    SMART_GRID_VISIBILITY("smart_grid_visibility");
    
    @NotNull
    private String value;

    ThemeConstants(String str) {
        this.value = str;
    }

    @NotNull
    public final String getValue() {
        return this.value;
    }

    public final void setValue(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.value = str;
    }
}
