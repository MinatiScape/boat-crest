package com.coveiot.android.theme;

import com.coveiot.android.leonardo.utils.MusicConstants;
import com.google.android.gms.common.Scopes;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.paho.android.service.MqttServiceConstants;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes7.dex */
public enum FirebaseEventParams {
    temp;

    /* loaded from: classes7.dex */
    public enum Description {
        ALLOW("allow"),
        CONNECTED("connected"),
        DISCONNECTED("disconnected"),
        CHARGING("charging"),
        DISCHARGING("discharging"),
        CONNECT(MqttServiceConstants.CONNECT_ACTION),
        DISCONNECT(MqttServiceConstants.DISCONNECT_ACTION),
        ERROR("error"),
        OK("ok"),
        OPEN_CONFIGURATION_SCREEN("open_configuration_screen"),
        OPEN_SYSTEM_SETTINGS("open_system_settings"),
        OPEN_PERMISSIONS_SCREEN("open_permissions_screen"),
        OPEN_PHONE_NUMBER_SCREEN("open_phone_number_screen"),
        OPEN_COUNTRY_SELECTION_SCREEN("open_country_selection_screen"),
        OPEN_OTP_SCREEN("open_otp_screen"),
        SELECT_COUNTRY_CODE("select_country_code"),
        OTP_RESENT("otp_resent"),
        OPEN_EXISTING_PROFILE_POPUP("open_existing_profile_popup"),
        OPEN_LANGUAGE_SELECTION_SCREEN("open_language_selection_screen"),
        OPEN_PAIRING_SCREEN("open_pairing_screen"),
        OPEN_DELETE_EXISTING_PROFILE_POPUP("open_delete_existing_profile_popup"),
        OPEN_SIGNUP_SCREEN("open_signup_screen"),
        OPEN_APOLOGISE_POPUP("open_apologise_popup"),
        OPEN_OTP_REPORT_ISSUE_SCREEN("open_otp_report_issue_screen"),
        SUBMIT_OTP_FEEDBACK("submit_otp_feedback"),
        OPEN_GENDER_SELECTION_SCREEN("open_gender_selection_screen"),
        ADD_PHOTO_POPUP("add_photo_popup"),
        OPEN_CAMERA("open_camera"),
        CHOOSE_GALLERY("choose_gallery"),
        REMOVE_PHOTO("remove_photo"),
        CLOSE_POPUP("close_popup"),
        OPEN_HEIGHT_WEIGHT_SCREEN("open_height_weight_screen"),
        OPEN_NAME_EMAIL_SIGN_UP_SCREEN("open_name_email_sign_up_screen"),
        OPEN_DEVICE_SELECTION_SCREEN("open_device_selection_screen"),
        OPEN_DEVICE_SCANNING_SCREEN("open_device_scanning_screen"),
        OPEN_PAIRING_PERMISSION_INFO_SCREEN("open_pairing_permission_info_screen"),
        REFRESH_DEVICE("refresh_device"),
        SCAN_ALL_DEVICES("scan_all_devices"),
        OPEN_DEVICE_PAIRING_SCREEN("open_device_pairing_screen"),
        OPEN_TROUBLE_SHOOT_SCREEN("open_trouble_shoot_screen"),
        OPEN_GOAL_SETTINGS_SCREEN("open_goal_settings_screen"),
        OPEN_MAIN_HOME_DASHBOARD("open_main_home_dashboard"),
        OPEN_BOTTOM_MENU_HEALTH("open_bottom_menu_health"),
        OPEN_BOTTON_MENU_BUDDIES("open_botton_menu_buddies"),
        OPEN_BOTTOM_MENU_HOME("open_bottom_menu_home"),
        OPEN_BOTTOM_MENU_RANK("open_bottom_menu_rank"),
        OPEN_BOTTOM_MENU_MORE("open_bottom_menu_more"),
        OPEN_HEART_RATE_CARD("open_heart_rate_card"),
        OPEN_SLEEP_CARD("open_sleep_card"),
        OPEN_CALORIES_CARD("open_calories_card"),
        OPEN_STEPS_CARD("open_steps_card"),
        OPEN_MODES_DASHBOARD("open_modes_dashboard"),
        OPEN_BATTERY_STATUS("open_battery_status"),
        OPEN_BUILD_NEW_PLAN("open_build_new_plan"),
        OPEN_CURRENT_PLAN_CARD("open_current_plan_card"),
        OPEN_CURRENT_DAY_ACTIVITY("open_current_day_activity"),
        OPEN_VIEW_MORE_PLANS("open_view_more_plans"),
        OPEN_MY_PLAN_HISTORY("open_my_plan_history"),
        OPEN_REBUILD_NEW_PLAN("open_rebuild_new_plan"),
        STEPS_SCREEN("steps_screen"),
        HEART_RATE_SCREEN("heart_rate_screen"),
        SLEEP_SCREEN("sleep_screen"),
        OPEN_SPO2_POPUP("open_spo2_popup"),
        OPEN_HEALTH_DASHBOARD("open_health_dashboard"),
        OPEN_DAYS_GRAPH("open_days_graph"),
        OPEN_STEP_HISTORY_SCREEN("open_step_history_screen"),
        OPEN_SHARE("open_share"),
        OPEN_CALENDAR("open_calendar"),
        OPEN_DAY_GRAPH("open_day_graph"),
        OPEN_WEEK_GRAPH("open_week_graph"),
        OPEN_MONTH_GRAPH("open_month_graph"),
        OPEN_STEP_CURRENT_DAY_DASHBOARD_SCREEN("open_step_current_day_dashboard_screen"),
        OPEN_HEART_RATE_HISTORY_SCREEN("open_heart_rate_history_screen"),
        OPEN_HEART_RATE_CURRENT_DAY_DASHBOARD_SCREEN("open_heart_rate_current_day_dashboard_screen"),
        OPEN_SLEEP_HISTORY_SCREEN("open_sleep_history_screen"),
        OPEN_SLEEP_CURRENT_DAY_DASHBOARD_SCREEN("open_sleep_current_day_dashboard_screen"),
        OPEN_EDIT_PROFILE_SCREEN("open_edit_profile_screen"),
        OPEN_SETTINGS_SCREEN("open_settings_screen"),
        OPEN_ABOUT_SCREEN("open_about_screen"),
        OPEN_ABOUT_YOUR_DEVICE_SCREEN("open_edit_profile_screen"),
        OPEN_WEIGHT_POP_UP("open_weight_pop_up"),
        OPEN_HEIGHT_POP_UP("open_height_pop_up"),
        OPEN_DOB_POP_UP("open_dob_pop_up"),
        OPEN_BOTTOM_MENU_MORE_SCREEN("open_bottom_menu_more_screen"),
        OPEN_DEVICE_DISCONNECT_BAND_POPUP("open_device_disconnect_band_popup"),
        OPEN_DISCLAIMER("open_disclaimer"),
        OPEN_WEBSITE("open_website"),
        OPEN_EULA("open_eula"),
        OPEN_PRIVACY_POLICY("open_privacy_policy"),
        OPEN_USER_MANUAL_SCREEN("open_user_manual_screen"),
        OPEN_CONTACT_US_SCREEN("open_contact_us_screen"),
        OPEN_FEEDBACK_SCREEN("open_feedback_screen"),
        OPEN_FAQ_SCREEN("open_faq_screen"),
        OPEN_TROUBLE_CONNECTIVITY_SCREEN("open_trouble_connectivity_screen"),
        OPEN_CONTINUOUS_MONITORING_CONFIG_SCREEN("open_continuous_monitoring_config_screen"),
        OPEN_HELP_FEEDBACK_SCREEN(" open_help_&_feedback_screen "),
        CONNECT_GOOGLE_FIT("connect_google_fit"),
        OPEN_RANK_BADGES_SCREEN("open_rank_&_badges_screen"),
        OPEN_ALL_BADGES_SCREEN("open_all_badges_screen"),
        OPEN_GOOGLE_FIT_SCREEN("open_google_fit_screen"),
        OPEN_MY_BADGES_SCREEN("open_my_badges_screen"),
        OPEN_BOTTOM_MENU_RANK_SCREEN("open_bottom_menu_rank_screen"),
        OPEN_CONFIRMATION_POPUP("open_confirmation_popup"),
        OPEN_ADD_BUDDY_SCREEN("open_add_buddy_screen"),
        OPEN_NOTIFICATION_SCREEN("open_notification_screen"),
        OPEN_MESSAGES_SCREEN("open_messages_screen"),
        OPEN_BUDDIES_SCREEN("open_buddies_screen"),
        OPEN_BUDDY_SCREEN("open_buddy_screen"),
        OPEN_NUDGE_POPUP("open_nudge_popup"),
        OPEN_CHEER_POPUP("open_cheer_popup"),
        BUDDY_SCREEN("buddy_screen"),
        OPEN_APPLAUD_POPUP("open_applaud_popup"),
        OPEN_ACTIVITY_DASHBOARD("open_activity_dashboard"),
        NUDGE_POPUP("nudge_popup"),
        CHEER_POPUP("cheer_popup"),
        APPLAUD_POPUP("applaud_popup"),
        LOCK_PHONE("lock_phone"),
        ACTIVITY_STOP("activity_stop"),
        UNLOCK_PHONE("unlock_phone"),
        ACTIVITY_PLAY("activity_play"),
        ACTIVITY_PAUSE("activity_pause"),
        OPEN_ACTIVITY_START_SCREEN("open_activity_start_screen"),
        OPEN_EDIT_DISTANCE_GOAL_POPUP("open_edit_distance_goal_popup"),
        NEXT(MusicConstants.CMDNEXT),
        DONE("done"),
        DENY("deny"),
        CV_value("cv_value"),
        CV_CONTACT_SELECTED_NAME("cv_contact_selected_name"),
        CV_CONTACT_SELECTED_NUMBER("cv_contact_selected_number"),
        CV_EMERGENCY_STATUS("cv_emrgncy_status");
        
        @NotNull
        private String value;

        Description(String str) {
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

    /* loaded from: classes7.dex */
    public enum EventName {
        KH_TAP("kh_tap"),
        ONEK_ACTIVITY_EXISITING_CONFIRM("cv_1k_act_replaced"),
        ONEK_BANNER_CLICK("cv_1k_banner_tap"),
        ONEK_FTU_CTA("cv_1k_ftu_cta"),
        ONEK_SEARCH_ACTIVITY("cv_1k_act_search"),
        ONEK_ACTIVITY_CAT_NEXT("cv_1k_cat_selected"),
        ONEK_ACTIVITY_SUBCAT_NEXT("cv_1k_act_selected"),
        ONEK_ACTIVITY_EXISITING_SELECT("cv_1k_old_act_item_tap"),
        CRICKET_MATCH_SAVE("cv_cricket_match_save"),
        WATCHFASE_SAVE("cv_watchface_save"),
        CV_VIDEO_TAP("cv_video_tap"),
        CV_REMINDER_SAVE("cv_reminder_save"),
        SYSTEM_INTERRUPTS_TIMER("system_interrupts_timer_soccer"),
        ENTITY_SPORTS_API_FAILURE("ENTITY_SPORTS_API_FAILURE"),
        CRICKET_ICON_TAP("cv_cricket_icon_tap"),
        CRICKET_MATCH_SELECT("cv_cricket_match_item_tap"),
        CRICKET_SETTINGS_SCORE_PUSH("cv_cricket_settings_toggle"),
        CRICKET_SETTINGS_DONE("cv_cricket_settings_save"),
        KH_OPEN("kh_open"),
        CV_PAIR_CONN_TRY_AGAIN("cv_pair_conn_try_again"),
        CV_PAIR_CONN_TROUBLESHOOT("cv_pair_conn_troubleshoot"),
        CV_CONN_TROUBLESHOOT("cv_conn_troubleshoot"),
        CV_OTP_RESEND("cv_otp_resend"),
        CV_OTP_REPORT_ISSUE("cv_otp_report_issue"),
        CV_BT_DATA_SYNC("cv_bt_data_sync"),
        CV_NOTIF_TROUBLESHOOT_START("cv_notif_troubleshoot_start"),
        CV_NOTIF_SEND_TEST_MSG("cv_notif_send_test_msg"),
        CV_NOTIF_TEST_MSG_RECEIVED("cv_notif_test_msg_received"),
        CV_BT_CONNECT("cv_bt_connect"),
        CV_AUTO_ACTIVITY_SELECT("cv_auto_activity_select"),
        CV_AUTO_ACTIVITY_SETTINGS("cv_auto_activity_settings"),
        CV_PAGE_VISIT("cv_page_visit"),
        CV_TROUBLESHOOT_SELECT("cv_troubleshoot_select"),
        CV_TROUBLESHOOT_CTA("cv_troubleshoot_cta"),
        CV_TROUBLESHOOT_FEEDBACK("cv_troubleshoot_feedback"),
        CV_TROUBLESHOOT_BACK("cv_troubleshoot_back"),
        CV_TRUBLESHOOT_ERROR("cv_troubleshoot_error"),
        CV_TROUBLESHOOT_POPUP("cv_troubleshoot_popup"),
        CV_FEATURE_NAME("cv_feature_name"),
        CV_RATE_APP("cv_rate_app"),
        CV_EMERGENCY_CARD_CLICK("cv_emrgncy_card_click"),
        CV_EMERGENCY_TOGGLE("cv_emrgncy_toggle"),
        CV_EMERGENCY_CONTACT_SELECT("cv_emrgncy_contact_select"),
        CV_EMERGENCY_CONTACT_EDIT("cv_emrgncy_contact_edit"),
        CV_EMERGENCY_CONTACT_DELETE("cv_emrgncy_contact_delete"),
        CV_EMERGENCY_CONTACT_CHANGE("cv_emrgncy_contact_change");
        
        @NotNull
        private String value;

        EventName(String str) {
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

    /* loaded from: classes7.dex */
    public enum MetaData {
        CV_BT_STATE("cv_bt_state"),
        CV_PHONE_BATTERY_LEVEL("cv_phone_battery_level"),
        CV_VIDEO_NAME("cv_video_name"),
        CV_VIDEO_DURATION("cv_video_duration"),
        CV_VIDEO_ID("cv_video_id"),
        CV_VIDEO_CATEGORY("cv_video_category_id"),
        CV_REMINDER_TYPE("cv_reminder_type"),
        CV_DVC_BATTERY_LEVEL("cv_dvc_battery_level"),
        CV_EVT_TRIGGER_TYPE("cv_evt_trigger_type"),
        CV_APP_PROCESS_STATUS("cv_app_process_status"),
        CV_DVC_CONN_STATUS("cv_dvc_conn_status"),
        CV_STATUS("cv_status"),
        CV_DATA_TYPE("cv_data_type"),
        CV_ERROR("cv_error"),
        CV_DND_ACTIVE("cv_dnd_active"),
        CV_NOTIF_SMS_ACTIVE("cv_notif_sms_active"),
        CV_NOTIF_CALL_ACTIVE("cv_notif_call_active"),
        CV_NOTIF_APP_ACTIVE("cv_notif_app_active"),
        CV_PERMIS_NOTIF_ACCESS("cv_permis_notif_access"),
        CV_PERMIS_CALL("cv_permis_call"),
        CV_VALUE("cv_value"),
        CV_WATCHFACE_ID("cv_watchface_id"),
        CV_WATCHFACE_FILE_NAME("cv_watchface_filename"),
        CV_POSITION("cv_position"),
        CV_WATCHFACE_CATEGORY("cv_watchface_category"),
        CV_SPORT_TYPE("cv_sport_type"),
        CV_LEAGUE_NAME("cv_league_name"),
        CV_TIME_SPENT_MILLS("cv_time_spent_mills"),
        CV_WALKING("cv_walking"),
        CV_RUNNING("cv_running"),
        CV_DETECT_DAYS("cv_detect_days"),
        CV_SMART_MODE("cv_smart_mode"),
        CV_SMART_MODE_TIME("cv_smart_mode_time"),
        CV_("cv_"),
        CV_INTERVAL("cv_interval"),
        CV_TROUBLESHOOT_TYPE("cv_troubleshoot_type"),
        CV_DND_STATUS("cv_dnd_status"),
        CV_SMS_NOTIF_STATUS("cv_sms_notif_status"),
        CV_ANDROID_NOTIF_STATUS("cv_android_notif_sett_status"),
        CV_APP_NOTIF_STATUS("cv_app_notif_status"),
        CV_CALL_NOTIF_STATUS("cv_call_notif_status"),
        CV_BT_CONNECTIVITY_STATUS("cv_bt_connectivity_status"),
        CV_APP_LST_NOTIF_ENABLE("cv_app_list_notif_enable"),
        CV_CUST_NAME("cv_cust_name"),
        CV_CUST_PH_NUM("cv_cust_ph_num"),
        CV_CUST_EMAIL("cv_cust_email"),
        CV_CUST_QUERY_TYPE("cv_cust_query_type"),
        CV_CUST_MESSAGE("cv_cust_message"),
        CV_CUST_MODE_CON("cv_cust_mode_con");
        
        @NotNull
        private String value;

        MetaData(String str) {
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

    /* loaded from: classes7.dex */
    public enum ScreenName {
        MAIN_SPLASH("main_splash"),
        LANGUAGE_SELECTION_SCREEN("language_selection_screen"),
        PREMISSION_SCREEN("premission_screen"),
        PHONE_NUMBER_SCREEN("enter_phone_number"),
        COUNTRY_SELECTION_SCREEN("country_selection_screen"),
        OTP_SCREEN("enter_otp"),
        EXISTING_PROFILE_POPUP("existing_profile_popup"),
        DELETE_EXISTING_PROFILE_POPUP("delete_existing_profile_popup"),
        APOLOGISE_POPUP("apologise_popup"),
        OTP_REPORT_ISSUE_SCREEN("otp_report_issue_screen"),
        FEEDBACK_SCREEN("feedback_screen"),
        OTP_FEEDBACK_SCREEN("otp_feedback_screen"),
        NAME_EMAIL_SIGN_UP_SCREEN("name_email_sign_up_screen"),
        ADD_PHOTO_POPUP("add_photo_popup"),
        GENDER_SELECTION_SCREEN("gender_selection_screen"),
        HEIGHT_WEIGHT_SCREEN("height_weight_screen"),
        HEIGHT_POPUP("height_popup"),
        WEIGHT_POPUP("weight_popup"),
        DOB_POPUP("dob_popup"),
        PAIRING_PERMISSION_INFO_SCREEN("pairing_permission_info_screen"),
        DEVICE_SELECTION_SCREEN("select_device_model"),
        DEVICE_SCANNING_SCREEN("device_scanning_screen"),
        DEVICE_PAIRING_SCREEN("device_pairing_screen"),
        PAIRING_SUCCESS_SCREEN("pairing_success_screen"),
        PAIRING_FAILED_SCREEN("device_pairing_failed"),
        GOAL_SETTINGS_SCREEN("goal_settings_screen"),
        BOTTOM_MENU_HEALTH_SCREEN("bottom_menu_health_screen"),
        STEP_CURRENT_DAY_DASHBOARD_SCREEN("step_current_day_dashboard_screen"),
        HEART_RATE_CURRENT_DAY_DASHBOARD_SCREEN("heart_rate_current_day_dashboard_screen"),
        SLEEP_CURRENT_DAY_DASHBOARD_SCREEN("sleep_current_day_dashboard_screen"),
        STEP_HISTORY_SCREEN("step_history_screen"),
        HEART_RATE_HISTORY_SCREEN("heart_rate_history_screen"),
        SLEEP_HISTORY_SCREEN("sleep_history_screen"),
        GRANT_PERMISSION_DIALOG("grant_permission_dialog"),
        MAIN_HOME_DASHBOARD("main_home_dash"),
        BATTERY_STATUS_SCREEN("battery_status_screen"),
        MAIN_ACTIVITY_DASHBOARD("main_activity_dashboard"),
        MAIN_MODES_DASHBOARD("main_modes_dashboard"),
        BOTTOM_MENU_RANK_SCREEN("bottom_menu_rank_screen"),
        FTU_SCREEN("ftu_screen"),
        ALL_BADGES_SCREEN("all_badges_screen"),
        MY_BADGES_SCREEN("my_badges_screen"),
        RANK_BADGES_SCREEN("rank_badges_screen"),
        CONFIRMATION_POPUP("confirmation_popup"),
        BOTTOM_MENU_BUDDY_SCREEN("bottom_menu_buddy_screen"),
        BUDDY_SCREEN("buddy_screen"),
        NOTIFICATION_SCREEN("notification_screen"),
        MESSAGE_SCREEN("message_screen"),
        ADD_BUDDY_SCREEN("add_buddy_screen"),
        GOOGLE_FIT_SCREEN("google_fit_screen"),
        BOTTOM_MENU_MORE_SCREEN("bottom_menu_more_screen"),
        DEVICE_DISCONNECT_BAND_POPUP("device_disconnect_band_popup"),
        SETTINGS_SCREEN("settings_screen"),
        ABOUT_YOUR_DEVICE_SCREEN("about_your_device_screen"),
        HELP_FEEDBACK_SCREEN("help_and_feedback"),
        ABOUT_SCREEN("about_screen"),
        EDIT_PROFILE_SCREEN("edit_profile_screen"),
        ACTIVITY_SUMMARY_SCREEN("activity_summary_screen"),
        ACTIVITY_PROGRESS_SCREEN("activity_progress_screen"),
        CONFIGURE_APP_SCREEN("configure_app_screen"),
        ACTIVITY_WORKOUT_HISTORY("activity_workout_history"),
        ONEK_ACTIVITY_LIST_SCREEN("1k_activity_list_screen"),
        SELECT_OLD_ACTIVITY_TO_REPLACE_SCREEN("select_old_activity_to_replace_screen"),
        MAIN_HOME_DASH("main_home_dash"),
        APPLY_WATCHFACE("apply_watchface"),
        REMINDERS("reminders"),
        WORKOUT_VIDEOS("workout_videos"),
        ADD_REMINDER("add_reminder"),
        ACTIVITY_TAB_ON_HOME_DASH("activity_tab_on_home_dash"),
        ONEK_FTU_SCREEN_ONE("1k-ftu-screen-1"),
        ONEK_FTU_SCREEN_TWO("1k-ftu-screen-2"),
        SELECT_1K_CATEGORY_SCREEN("select_1k_category_screen"),
        SELECT_MATCH_SCREEN("select_match_screen"),
        MORE_SCREEN("more_screen"),
        TROUBLESHOOT_AND_FAQS("troubleshoot_and_faqs"),
        SPORTS_SETTINGS("sports_settings"),
        TROUBLESHOOTING_NOTIFICATIONS("troubleshooting_notifications"),
        AUTO_ACTIVITY_DETECTION("auto_activity_detection"),
        WATCH_FEATURES("<watch>_features"),
        SETTINGS("settings"),
        SYSTEM_NOTIFICATION("system_notification"),
        CREATE_CUSTOM_WATCHFACE("create_custom_watchface"),
        WATCHFACES("watchfaces"),
        TROUBlESHOOTING("troubleshooting"),
        CONTACT_US("contact_us"),
        PARTNER_DETAILS("partner_details"),
        BOAT_COINS_DASHBOARD("boatcoin_dashboard"),
        RATE_US("rate_us"),
        PROFILE(Scopes.PROFILE),
        CREATE_WATCHFACE("create_watchface"),
        WATCHFACE("watchface"),
        SOS_FTU("SOS_FTU"),
        EMERGENCY_SOS("emergency_SOS"),
        SOS_CONTACT_SELECT("SOS_contact_select"),
        MY_WATCH("my_watch"),
        WATCH_FEATURES_SETTINGS("watch_features"),
        WATCH_SETTINGS("watch_settings");
        
        @NotNull
        private String value;

        ScreenName(String str) {
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

    /* loaded from: classes7.dex */
    public enum UiElementName {
        NEXT_BUTTON("next_button"),
        LANGUAGE_SELECTION_RADIO_BUTTON("language_selection_radio_button"),
        DISABLE_BATTERY_OPTIMIZATION_BUTTON("disable_battery_optimization_button"),
        DISABLE_POWER_OPTIMIZATION_BUTTON("disable_power_optimization_button"),
        BACK_BUTTON("back_button"),
        COUNTRY_TEXT_FIELD("country_text_field"),
        SUBMIT_BUTTON("submit_button"),
        COUNTRY_SELECTION("country_selection"),
        YES("yes"),
        NO("no"),
        REPORT_ISSUE_BUTTON("report_issue_button"),
        OK_BUTTON("ok_button"),
        RESEND_BUTTON("resend_button"),
        CONTINUE_BUTTON("continue_button"),
        YES_BUTTON("yes_button"),
        NO_BUTTON("no_button"),
        SHARE_FEEDBACK_BUTTON("share_feedback_button"),
        ADD_PHOTO("SHARE_FEEDBACK_BUTTON"),
        TAKE_PHOTO_BUTTON("take_photo_button"),
        CHOOSE_FROM_GALLERY("choose_from_gallery"),
        SELECT_HEIGHT("select_height"),
        SELECT_WEIGHT("select_weight"),
        SELECT_DOB("select_dob"),
        CANCEL_BUTTON("cancel_button"),
        SELECT_DEVICE("select_device"),
        REFRESH_BUTTON("refresh_button"),
        PAIR_BUTTON("pair_button"),
        SCAN_ALL_DEVICE_BUTTON("scan_all_device_button"),
        TRY_AGAIN("try_again"),
        PAIRING_TROUBLE_SHOOT("pairing_trouble_shoot"),
        DONE_BUTTON("done_button"),
        SLEEP_CARD("sleep_card"),
        STEPS_CARD("steps_card"),
        HEART_RATE_CARD("heart_rate_card"),
        STEP_CALENDAR_BUTTON("step_calendar_button"),
        STEP_HISTORY_BUTTON("step_history_button"),
        STEP_SHARE_BUTTON("step_share_button"),
        DAYS_BUTTON("days_button"),
        HEART_RATE_SHARE_BUTTON("heart_rate_share_button"),
        HEART_RATE_HISTORY_BUTTON("heart_rate_history_button"),
        HEART_RATE_CALENDAR_BUTTON("heart_rate_calendar_button"),
        SLEEP_HISTORY_BUTTON("sleep_history_button"),
        SLEEP_SHARE_BUTTON("sleep_share_button"),
        SLEEP_CALENDAR_BUTTON("sleep_calendar_button"),
        DAY_BUTTON("day_button"),
        WEEK_BUTTON("week_button"),
        MONTH_BUTTON("month_button"),
        STEP_HISTORY_SHARE_BUTTON("step_history_share_button"),
        HEART_RATE_HISTORY_SHARE_BUTTON("heart_rate_history_share_button"),
        SLEEP_HISTORY_SHARE_BUTTON("sleep_history_share_button"),
        BOTTOM_MENU_HEALTH_BUTTON("bottom_menu_health_button"),
        BOTTOM_MENU_BUDDIES_BUTTON("bottom_menu_buddies_button"),
        BOTTOM_MENU_HOME_BUTTON("bottom_menu_home_button"),
        BOTTOM_MENU_RANK_BUTTON("bottom_menu_rank_button"),
        BOTTOM_MENU_MORE_BUTTON("bottom_menu_more_button"),
        DASHBOARD_ACTIVITY_TAB("dashboard_activity_tab"),
        DASHBOARD_MAIN_HOME("dashboard_main_home"),
        DASHBOARD_MODE_TAB("dashboard_mode_tab"),
        BATTERY_STATUS("battery_status"),
        DASHBOARD_STEPS_CARD("dashboard_steps_card"),
        DASHBOARD_HEART_RATE_CARD("dashboard_heart_rate_card"),
        DASHBOARD_SLEEP_CARD("dashboard_sleep_card"),
        MODES_BUILD_NEW_PLAN_BUTTON("modes_build_new_plan_button"),
        MODES_VIEW_CURRENT_PLAN_CARD("modes_view_current_plan_card"),
        MODES_PLAN_HISTORY_CARD("modes_plan_history_card"),
        MODES_REBUILD_NEW_PLAN_CARD("modes_rebuild_new_plan_card"),
        MODE_START_ACTIVITY_CARD("mode_start_activity_card"),
        LOCATION_ICON("location_icon"),
        VIEW_BADGES("location_icon"),
        LEARN_ABOUT_BADGES("learn_about_badges"),
        SELECT_YOUR_LOCATION_BUTTON("select_your_location_button"),
        SHOW_MY_BAGDES_BUTTON("show_my_bagdes_button"),
        START_BUTTON("start_button"),
        ADD_BUDDIES_BUTTON("add_buddies_button"),
        BUDDIES_BUTTON("buddies_button"),
        MESSAGES_BUTTON("messages_button"),
        NOTIFICATIONS_BUTTON("notifications_button"),
        REINVITE_BUTTON("reinvite_button"),
        UN_FRIEND("un_friend"),
        DELETE_BUTTON("delete_button"),
        SEND_BUTTON("send_button"),
        NUDGE_BUTTON("nudge_button"),
        APPLAUD_BUTTON("applaud_button"),
        CHEER_BUTTON("cheer_button"),
        DISCONNECT_BUTTON("disconnect_button"),
        CONNECT_BUTTON("connect_button"),
        PROFILE_EDIT_BUTTON("profile_edit_button"),
        HELP_FEEDBACK_BUTTON("help_feedback_button"),
        GOOGLE_FIT_BUTTON("google_fit_button"),
        ABOUT_BUTTON("about_button"),
        SETTINGS_BUTTON("settings_button"),
        ABOUT_YOUR_DEVICE_BUTTON("about_your_device_button"),
        DISCONNECT_BAND_BUTTON("disconnect_band_button"),
        USER_MANUAL_BUTTON("user_manual_button"),
        FAQ_BUTTON("faq_button"),
        CONTACT_US_BUTTON("contact_us_button"),
        CONTINUOUS_MONITORING_CONFIG_BUTTON("continuous_monitoring_config_button"),
        FEEDBACK_BUTTON("feedback_button"),
        TROUBLE_CONNECTIVITY_BUTTON("trouble_connectivity_button"),
        WEBSITE_BUTTON("website_button"),
        PRIVACY_POLICY_BUTTON("privacy_policy_button"),
        EULA_BUTTON("eula_button"),
        DISCLAIMER_BUTTON("disclaimer_button"),
        SAVE_BUTTON("save_button"),
        CHANGE_HEIGHT("change_height"),
        CHANGE_WEIGHT("change_weight"),
        CHANGE_DOB("change_dob"),
        ACTIVITY_HISTORY_BUTTON("activity_history_button"),
        DEVICE_LOCK_BUTTON("device_lock_button"),
        ACTIVITY_STOP_BUTTON("activity_stop_button"),
        DEVICE_UNLOCK_BUTTON("device_unlock_button"),
        ACTIVITY_PLAY_BUTTON("activity_play_button"),
        ACTIVITY_PAUSE_BUTTON("activity_pause_button"),
        EDIT_TARGET_DISTANCE_BUTTON("edit_target_distance_button"),
        ENABLE_AUTO_START_BUTTON("enable_auto_start_button");
        
        @NotNull
        private String value;

        UiElementName(String str) {
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

    /* loaded from: classes7.dex */
    public enum UserProperties {
        CUSTOMER_ID("cv_dvc_cid"),
        DEVICE_MODEL("cv_dvc_mn"),
        CV_DVC_FW_VER("cv_dvc_fw_ver"),
        CV_DVC_BLE_DISP_NM("cv_dvc_ble_disp_nm"),
        CV_PHN_CHIPSET("cv_phn_chipset"),
        USER_DEVICE_ID("cv_usr_dvc_id");
        
        @NotNull
        private String value;

        UserProperties(String str) {
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
}
