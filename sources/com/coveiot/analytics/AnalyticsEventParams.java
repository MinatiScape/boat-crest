package com.coveiot.analytics;

import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes2.dex */
public enum AnalyticsEventParams {
    temp;

    /* loaded from: classes2.dex */
    public enum Key {
        PREVIOUS_SCREEN_NAME("cv_prev_screen_name"),
        SCREEN_NAME("cv_screen_name"),
        UI_ELEMENT("kh_ui_element_name"),
        DESCRIPTION("kh_description_key"),
        PARENT_SCREEN_NAME("kh_parent_screen_name"),
        OLD_STEPS_TARGET("kh_old_steps_target"),
        NEW_STEPS_TARGET("kh_new_steps_target"),
        OLD_COUNTRY_CODE("kh_old_country_code"),
        NEW_COUNTRY_CODE("kh_new_country_code"),
        APP_PERMISSION_ID("kh_app_permission_id"),
        COMMAND_STATUS("kh_command_status"),
        COMMAND_NAME("kh_command_name"),
        TIME_STAMP("kh_time_stamp"),
        DEVICE_CONNECTION_STATUS("kh_device_connection_status"),
        KH_BUILD_FINGERPRINT("kh_build_fingerprint"),
        KH_BUILD_DISPLAY_NAME("kh_build_display_name"),
        KH_WATCH_FACE_SET("kh_watch_face_set"),
        KH_SECONDARY_TIME_ZONE("kh_secondary_time_zone"),
        CV_PREV_SCREEN_NAME("cv_prev_screen_name"),
        CV_SCREEN_NAME("cv_screen_name"),
        CV_OLD_ACT_CODE("cv_old_act_code"),
        CV_NEW_ACT_CODE("cv_new_act_code"),
        CV_DESC_KEY("cv_desc_key"),
        CV_CAT_ID("cv_cat_id"),
        CV_ACT_CODE("cv_act_code"),
        CV_CTX("cv_ctx"),
        CV_MATCH_ID("cv_match_id"),
        CV_MATCH_DATE("cv_match_date"),
        CV_CRICKET_TEAM1("cv_cricket_team1"),
        CV_CRICKET_TEAM2("cv_cricket_team2"),
        CV_MATCH_TYPE("cv_match_type"),
        CV_MATCH_START_TIME("cv_match_start_time"),
        CV_VALUE("cv_value");
        
        public String textValue;

        @NotNull
        public final String getTextValue$analytics_release() {
            String str = this.textValue;
            if (str != null) {
                return str;
            }
            Intrinsics.throwUninitializedPropertyAccessException("textValue");
            return null;
        }

        public final void setTextValue$analytics_release(@NotNull String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.textValue = str;
        }

        @NotNull
        public final String textValue$analytics_release() {
            return getTextValue$analytics_release();
        }

        Key(String str) {
            setTextValue$analytics_release(str);
        }
    }
}
