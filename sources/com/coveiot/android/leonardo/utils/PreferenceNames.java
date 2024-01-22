package com.coveiot.android.leonardo.utils;

import com.coveiot.covepreferences.UserDataManager;
/* loaded from: classes5.dex */
public enum PreferenceNames implements SavedPreference {
    USER_ID("user_id"),
    FCM_REGISTRATION_ID("FcmRegistrationId"),
    APP_NOTIFICATION_DATA("app_notification_data"),
    SELECTED_AUTO_HR_VAL(UserDataManager.SELECTED_AUTO_HR_VAL),
    IS_AUTO_HR_ENABLED(UserDataManager.IS_AUTO_HR_ENABLED),
    FCM_REGISTRATION_TO_SERVER_OK("fcmToServerOk"),
    APP_CONFIG_URLS("app_config_urls"),
    TEMPERATURE_CALIBRATION_VAL("temperature_calibration_val"),
    SPO2_FTU("spo2_ftu"),
    BP_SECONDARY_DEVICE_NAME("telemedicine_tnc"),
    IS_STEP_GOAL_CHANGED("is_step_goal_changed"),
    IS_GOAL_COMPLETE_NOTIFICATION_SHOWN("is_goal_complete_notification_shown"),
    GOAL_COMPLETION_NOTIFICATION_SHOWN_TIME("goal_completion_notification_shown_time"),
    TEMPERATURE_DISCLAIMER_DO_NOT_SHOW_ON("temperature_disclaimer_do_not_show_on"),
    IS_TEMPERATURE_DISCLAIMER_SHOWN("is_temperature_disclaimer_shown"),
    CUSTOM_MESSAGE_CONFIG("custom_message_config"),
    IS_WALK_STRIDE_LENGTH_MANUALLY_EDITED("is_walk_stride_length_manually_edited"),
    IS_RUN_STRIDE_LENGTH_MANUALLY_EDITED("is_run_stride_length_manually_edited"),
    IS_SENS_AI_ENABLED("is_sens_ai_enabled"),
    IS_SENS_AI_FTU_SHOWN("is_sens_ai_ftu_shown"),
    LAST_SENS_AI_SERVER_SYNC("last_sens_ai_server_sync"),
    IS_BOAT_COINS_ENABLED("is_boat_coins_enabled");
    
    private String name;

    PreferenceNames(String str) {
        this.name = str;
    }

    @Override // com.coveiot.android.leonardo.utils.SavedPreference
    public String getName() {
        return this.name;
    }

    @Override // com.coveiot.android.leonardo.utils.SavedPreference
    public PreferenceType getPreferenceType() {
        return PreferenceType.SF_APP;
    }
}
