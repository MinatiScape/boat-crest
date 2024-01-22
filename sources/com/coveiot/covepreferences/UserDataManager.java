package com.coveiot.covepreferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.coveiot.covepreferences.data.AppNotificationData;
import com.coveiot.covepreferences.data.AutoActivityDetectionData;
import com.coveiot.covepreferences.data.AutoRecognitionData;
import com.coveiot.covepreferences.data.AutoSPO2SettingsData;
import com.coveiot.covepreferences.data.AutoStressSettingsData;
import com.coveiot.covepreferences.data.BatteryLevelData;
import com.coveiot.covepreferences.data.BatteryOptimizationConfig;
import com.coveiot.covepreferences.data.BpCalibrationData;
import com.coveiot.covepreferences.data.CameraSettingsData;
import com.coveiot.covepreferences.data.DisplayModeData;
import com.coveiot.covepreferences.data.DoNotDisturbData;
import com.coveiot.covepreferences.data.DrinkWaterReminderData;
import com.coveiot.covepreferences.data.EventReminderData;
import com.coveiot.covepreferences.data.FeatureMapping;
import com.coveiot.covepreferences.data.FitnessGoal;
import com.coveiot.covepreferences.data.GoalSettingsData;
import com.coveiot.covepreferences.data.HealthDataModel;
import com.coveiot.covepreferences.data.LastNightSleepData;
import com.coveiot.covepreferences.data.LatestHealthDataModel;
import com.coveiot.covepreferences.data.LiveHeartRateModel;
import com.coveiot.covepreferences.data.NotificationSettings;
import com.coveiot.covepreferences.data.QuickReplyListModel;
import com.coveiot.covepreferences.data.ScheduleData;
import com.coveiot.covepreferences.data.SedentaryReminderData;
import com.coveiot.covepreferences.data.ShowHideData;
import com.coveiot.covepreferences.data.StepsDataModel;
import com.coveiot.covepreferences.data.VibrationAlarmData;
import com.coveiot.covepreferences.data.WebViewUrlBean;
import com.coveiot.covepreferences.data.WeeklyReportPrefData;
import com.coveiot.covepreferences.data.WomenWellnessData;
import com.coveiot.covepreferences.data.WorldClockPrefData;
import com.coveiot.covepreferences.listener.LatestHealthDataUpdateListener;
import com.coveiot.utils.model.CoveContact;
import com.coveiot.utils.utility.AppUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
/* loaded from: classes8.dex */
public class UserDataManager {
    public static final String ACTIVITY_LAST_SYNC_DATE = "ACTIVITY_LAST_SYNC_DATE";
    public static final String AGPS_OBJECT = "agps_object";
    public static final String APP_NOTIFICATION_OBJECT = "app_notification_object";
    public static final String AUTO_HR_INTERVAL_PREFERENCE = "auto_hr_interval_preference";
    public static final String AUTO_RECOGNITION_OBJECT = "AUTO_RECOGNITION_OBJECT";
    public static final String AUTO_RECOGNITION_OBJECT_NEW = "AUTO_RECOGNITION_OBJECT_NEW";
    public static final String AUTO_SP02_SETTINGS_OBJECT = "auto_spo2_settings_object";
    public static final String AUTO_STRESS_SETTINGS_OBJECT = "auto_stress_settings_object";
    public static final String BATTERY_LEVEL = "battery_level";
    public static final String BATTERY_LEVEL_DATA = "battery_level_data";
    public static final String BATTERY_OPTIMIZATION_CONFIG = "battery_optimization_config";
    public static final String BP_CALIBRATION_OBJECT = "bp_calibration_object";
    public static final String CAMERA_SETTINGS_OBJECT = "camera_settings_object";
    public static final String CLOUD_WATCH_FACE_POSITION = "cloud_watch_face_position";
    public static final String CLOUD_WATCH_FACE_POSITION_S2 = "cloud_watch_face_position_s2";
    public static final String COUNTRY_ISO_CODE = "country_iso_code";
    public static final String CREATE_FITNESS_GOAL_OBJECT_CALORIES = "create_fitness_goal_object_calories";
    public static final String CREATE_FITNESS_GOAL_OBJECT_DISTANCE = "create_fitness_goal_object_distance";
    public static final String CREATE_FITNESS_GOAL_OBJECT_EXERCISE_MINUTES = "create_fitness_goal_object_exercise_minutes";
    public static final String CREATE_FITNESS_GOAL_OBJECT_SLEEP = "create_fitness_goal_object_sleep";
    public static final String CREATE_FITNESS_GOAL_OBJECT_STEPS = "create_fitness_goal_object_steps";
    public static final String CREATE_FITNESS_GOAL_OBJECT_WALK_HOUR = "create_fitness_goal_object_walk_hour";
    public static final String DISPLAY_MODE_OBJECT = "display_mode_object";
    public static final String DISTANCE_UNIT_PREFERENCE = "distance_unit_preference";
    public static final String DOC_ONLINE_DATA = "doc_online_data";
    public static final String DONOT_DISTURB_OBJECT = "donot_disturb_object";
    public static final String DRINK_WATER_REMINDER_OBJECT = "drink_water_reminder_object";
    public static final String ENABLE_SLEEP_ENERGY_SCORE_FEATURE = "enable_sleep_energy_score_feature";
    public static final String EVENT_REMINDER_OBJECT = "event_reminder_object";
    public static final String FEATURE_MAPPING_CONFIG = "feature_mapping_config";
    public static final String GOAL_SETTINGS_OBJECT = "goal_settings_object";
    public static final String GOOGLE_FIT_CONNECTED = "google_fit_connected";
    public static final String HAND_PREFERENCE = "hand_preference";
    public static final String HEIGHT_UNIT_TYPE = "height_unit_type";
    public static final String HOUR_FORMAT_PREFERENCE = "hour_format_preference";
    public static final String IOT_HEART_BEAT_API_FREW = "IOT_HEART_BEAT_API_FREW";
    public static final String IS_AUTO_HR_ENABLED = "is_auto_hr_enabled";
    public static final String IS_AUTO_TEMPERATURE_ENABLED = "is_auto_temperature_enabled";
    public static final String IS_CAMERA_LAUNCHED = "is_camera_launched";
    public static final String IS_DISCONNECTION_AT_AGPS_FILE_UPDATE = "is_disconnection_at_agps_file_update";
    public static final String IS_ONK_SUPPORTED = "isOneKSupported";
    public static final String IS_VERTEX_FORCE_LIFT_WRIST_TO_VIEW_PUSHED = "is_vertex_force_lift_wrist_to_view_pushed";
    public static final String KEY_AMBIENT_SOUND_SETTINGS_ENABLED = "ambient_sound_settings_enabled";
    public static final String KEY_AUTO_ACTIVITY_DETECTION_DISCLAIMER_SEEN = "auto_activity_detection_disclaimer_seen";
    public static final String KEY_IS_GOOGLE_FIT_HR_SPO2_TEMP_SLEEP_SUPPORT_AVAILABLE = "is_google_fit_hr_spo2_temp_sleep_support_available";
    public static final String KEY_IS_RESPIRATORY_RATE_FEATURE_ENABLED = "respiratory_rate_feature_enabled";
    public static final String KEY_LAST_INCOMING_NUMBER_TO_QUICK_REPLY = "last_incoming_number_to_quick_reply";
    public static final String KEY_LAST_NIGHT_SLEEP_DATA = "last_night_sleep_data";
    public static final String KEY_LAST_RAW_PPG_SYNC_TIMESTAMP = "last_ppg_sync_timestamp";
    public static final String LAST_ALERTED_TIMESTAMP = "last_alerted_timestamp";
    public static final String LAST_HEART_RATE_BP = "last_heart_rate_bp";
    public static final String LAST_LIVE_HR_DATE = "last_live_hr_date";
    public static final String LAST_LIVE_HR_OBJECT = "live_hr";
    public static final String LAST_UPDATED_DATE_FITNESS_COMPUTED_API_CALL_OBJ = "last_updated_date_fitness_computed_api_call_obj";
    public static final String LAST_UPDATED_WEATHER_TIMESTAMP = "last_updated_weather_timestamp";
    public static final String LAST_UPDATE_TIME_STATUS_API = "LAST_UPDATE_TIME_STATUS_API";
    public static final String LAST_WATCH_FACE_BACKGROUND_URL = "last_watch_face_background_url";
    public static final String LATEST_HR_DATA = "latest_hr_data";
    public static final String LATEST_SPO2_DATA = "latest_spo2_data";
    public static final String LATEST_TEMPERATURE_DATA = "latest_temperature_data";
    public static final String LIFT_WRIST_TO_VIEW = "lift_wrist_to_view";
    public static final String LIFT_WRIST_TO_VIEW_END_HOUR_24H_FORMAT = "lift_wrist_to_view_end_hour_24h_format";
    public static final String LIFT_WRIST_TO_VIEW_END_MINUTE = "lift_wrist_to_view_end_minute";
    public static final String LIFT_WRIST_TO_VIEW_START_HOUR_24H_FORMAT = "lift_wrist_to_view_start_hour_24h_format";
    public static final String LIFT_WRIST_TO_VIEW_START_MINUTE = "lift_wrist_to_view_start_minute";
    public static final String LIVE_STEPS = "live_steps";
    public static final String NOTIFICATION_OBJECT = "notification_object";
    public static final String PROBE_INTERVAL = "probe_interval";
    public static final String QUICK_REPLY_DATA = "quick_reply_data";
    public static final String REMOTE_MONITORING_CONSENT = "remote_monitoring_consent";
    public static final String SCEDULE_DND_ON = "scedule_dnd_on_off";
    public static final String SCHEDULE_LIFT_WRIST_TO_VIEW = "schedule_lift_wrist_to_view";
    public static final String SCHEDULE_OBJECT = "schedule_object";
    public static final String SEDENTARY_REMINDER_OBJECT = "sedentary_reminder_object";
    public static final String SELECTED_AUTO_HR_VAL = "selected_auto_hr_val";
    public static final String SELECTED_AUTO_TEMPERATURE_VAL = "selected_auto_temperature_val";
    public static final String SHORTCUTS_HIDE_SHOW_OBJECT = "SHORTCUTS_HIDE_SHOW_OBJECT";
    public static final String SHOULD_ENABLE_GUARDIAN_FEATURE = "should_enable_guardian_feature";
    public static final String SPORTS_HIDE_SHOW_OBJECT = "SPORTS_HIDE_SHOW_OBJECT";
    public static final String STRIDE_LENGTH_UNIT_TYPE = "stride_length_unit_type";
    public static final String SYNCED_CONTACTS = "synced_contacts";
    public static final String TEMPERATURE_BREACH_CONFIG = "temperature_breach_config";
    public static final String TEMPERATURE_UNIT_PREFERENCE = "temperature_unit_preference";
    public static final String TIMESTAMP_DISABLE_AUTO_DIALOG_SHOWN = "timestamp_disable_auto_dialog_shown";
    public static final String VIBRATION_ALARM_OBJECT = "vibration_alarm_object";
    public static final String WATCH_FACE_LAYOUT_SELECTED_COLOR = "watch_face_layout_selected_color";
    public static final String WATCH_FACE_LAYOUT_SELECTED_STYLE = "watch_face_layout_selected_style";
    public static final String WEB_URL_BEAN = "web_url_bean";
    public static final String WEEKLY_SUBSCRIPTION = "weekly_subscription";
    public static final String WEIGHT_UNIT_TYPE = "weight_unit_type";
    public static final String WOLRD_CLOCK_OBJ = "wolrd_clock_obj";
    public static final String WOMEN_WELLNESS_OBJECT = "women_wellness_object";
    public static SharedPreferences b;
    public static SharedPreferences.Editor c;
    public static int d;
    public static UserDataManager e;

    /* renamed from: a  reason: collision with root package name */
    public List<LatestHealthDataUpdateListener> f7005a = new ArrayList();

    /* loaded from: classes8.dex */
    public class a extends TypeToken<List<WorldClockPrefData>> {
        public a(UserDataManager userDataManager) {
        }
    }

    /* loaded from: classes8.dex */
    public class b extends TypeToken<List<EventReminderData>> {
        public b(UserDataManager userDataManager) {
        }
    }

    /* loaded from: classes8.dex */
    public class c extends TypeToken<int[]> {
        public c(UserDataManager userDataManager) {
        }
    }

    /* loaded from: classes8.dex */
    public class d extends TypeToken<List<VibrationAlarmData>> {
        public d(UserDataManager userDataManager) {
        }
    }

    /* loaded from: classes8.dex */
    public class e extends TypeToken<List<AppNotificationData>> {
        public e(UserDataManager userDataManager) {
        }
    }

    /* loaded from: classes8.dex */
    public class f extends TypeToken<List<AppNotificationData>> {
        public f(UserDataManager userDataManager) {
        }
    }

    /* loaded from: classes8.dex */
    public class g extends TypeToken<List<ScheduleData>> {
        public g(UserDataManager userDataManager) {
        }
    }

    /* loaded from: classes8.dex */
    public class h extends TypeToken<ArrayList<CoveContact>> {
        public h(UserDataManager userDataManager) {
        }
    }

    /* loaded from: classes8.dex */
    public class i extends TypeToken<List<ShowHideData>> {
        public i(UserDataManager userDataManager) {
        }
    }

    /* loaded from: classes8.dex */
    public class j extends TypeToken<List<ShowHideData>> {
        public j(UserDataManager userDataManager) {
        }
    }

    /* loaded from: classes8.dex */
    public class k extends TypeToken<List<AutoRecognitionData>> {
        public k(UserDataManager userDataManager) {
        }
    }

    public static UserDataManager getInstance(Context context) {
        if (e == null) {
            SharedPreferences sharedPreferences = context.getSharedPreferences("USERDATA_MANAGER", d);
            b = sharedPreferences;
            c = sharedPreferences.edit();
            e = new UserDataManager();
        }
        return e;
    }

    public void clearPreferences(Context context) {
        for (String str : b.getAll().keySet()) {
            remove(context, str);
        }
        unregisterAllLatestHealthDataUpdateListener();
    }

    public String getAgpsFileLastUpdatedDate() {
        return b.getString(AGPS_OBJECT, null);
    }

    public List<AppNotificationData> getAppNotificationsData() {
        String string = b.getString(APP_NOTIFICATION_OBJECT, null);
        new ArrayList();
        return (List) new Gson().fromJson(string, new e(this).getType());
    }

    public String[] getAppNotificationsPackageListFromPreference() {
        String string = b.getString(APP_NOTIFICATION_OBJECT, null);
        List<AppNotificationData> arrayList = new ArrayList();
        Gson gson = new Gson();
        if (string != null) {
            arrayList = (List) gson.fromJson(string, new f(this).getType());
        }
        if (AppUtils.isEmpty(arrayList)) {
            return null;
        }
        String[] strArr = new String[arrayList.size()];
        int i2 = 0;
        for (AppNotificationData appNotificationData : arrayList) {
            strArr[i2] = appNotificationData.getPackageName();
            i2++;
        }
        return strArr;
    }

    public AutoActivityDetectionData getAutoActivityDetectionData() {
        return (AutoActivityDetectionData) new Gson().fromJson(b.getString(AUTO_RECOGNITION_OBJECT_NEW, null), (Class<Object>) AutoActivityDetectionData.class);
    }

    public int getAutoHrInterval() {
        return b.getInt(SELECTED_AUTO_HR_VAL, 30);
    }

    public List<AutoRecognitionData> getAutoRecognitionList() {
        String string = b.getString(AUTO_RECOGNITION_OBJECT, null);
        new ArrayList();
        return (List) new Gson().fromJson(string, new k(this).getType());
    }

    public AutoSPO2SettingsData getAutoSPO2SettingsData() {
        return (AutoSPO2SettingsData) new Gson().fromJson(b.getString(AUTO_SP02_SETTINGS_OBJECT, null), (Class<Object>) AutoSPO2SettingsData.class);
    }

    public AutoStressSettingsData getAutoStressSettingsData() {
        return (AutoStressSettingsData) new Gson().fromJson(b.getString(AUTO_STRESS_SETTINGS_OBJECT, null), (Class<Object>) AutoStressSettingsData.class);
    }

    public boolean getAutoTemperatureEnabled() {
        return b.getBoolean(IS_AUTO_TEMPERATURE_ENABLED, true);
    }

    public int getAutoTemperatureInterval() {
        return b.getInt(SELECTED_AUTO_TEMPERATURE_VAL, 60);
    }

    public BatteryLevelData getBatteryLevelData() {
        return (BatteryLevelData) new Gson().fromJson(b.getString(BATTERY_LEVEL_DATA, null), (Class<Object>) BatteryLevelData.class);
    }

    public BatteryOptimizationConfig getBatteryOptimizationConfig() {
        return (BatteryOptimizationConfig) new Gson().fromJson(b.getString(BATTERY_OPTIMIZATION_CONFIG, null), (Class<Object>) BatteryOptimizationConfig.class);
    }

    public BpCalibrationData getBpCalibrationData() {
        return (BpCalibrationData) new Gson().fromJson(b.getString(BP_CALIBRATION_OBJECT, null), (Class<Object>) BpCalibrationData.class);
    }

    public CameraSettingsData getCameraSettingsData() {
        return (CameraSettingsData) new Gson().fromJson(b.getString(CAMERA_SETTINGS_OBJECT, null), (Class<Object>) CameraSettingsData.class);
    }

    public int getCloudWatchFacePosition() {
        return b.getInt(CLOUD_WATCH_FACE_POSITION, -1);
    }

    public int getCloudWatchFacePositionForS2() {
        return b.getInt(CLOUD_WATCH_FACE_POSITION_S2, -1);
    }

    public String getConnectedBTCallDeviceMac() {
        return b.getString("CONNECTED_BT_CALL_DEVICE_MAC", null);
    }

    public String getConnectedBTCallDeviceName() {
        return b.getString("CONNECTED_BT_CALL_DEVICE_NAME", null);
    }

    public String getConnectedSecondaryDeviceMacAddress() {
        return b.getString("SECONDARY_CONNECTED_DEVICE_MACADDRESS", "");
    }

    public String getConnectedSecondaryDeviceName() {
        return b.getString("SECONDARY_CONNECTED_DEVICE_NAME", "");
    }

    public String getCountryCode() {
        return b.getString(COUNTRY_ISO_CODE, "");
    }

    public int[] getDisplayModeData() {
        Gson gson = new Gson();
        String string = b.getString(DISPLAY_MODE_OBJECT, null);
        if (string == null || TextUtils.isEmpty(string)) {
            return new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1};
        }
        int[] iArr = (int[]) gson.fromJson(string, new c(this).getType());
        return iArr == null ? new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1} : iArr;
    }

    public DoNotDisturbData getDoNotDisturbData() {
        return (DoNotDisturbData) new Gson().fromJson(b.getString(DONOT_DISTURB_OBJECT, null), (Class<Object>) DoNotDisturbData.class);
    }

    public DrinkWaterReminderData getDrinkWaterReminderData() {
        return (DrinkWaterReminderData) new Gson().fromJson(b.getString(DRINK_WATER_REMINDER_OBJECT, null), (Class<Object>) DrinkWaterReminderData.class);
    }

    public DisplayModeData getDsiplayModeData() {
        return (DisplayModeData) new Gson().fromJson(b.getString(DISPLAY_MODE_OBJECT, null), (Class<Object>) DisplayModeData.class);
    }

    public List<EventReminderData> getEventReminderData() {
        String string = b.getString(EVENT_REMINDER_OBJECT, null);
        return string != null ? (List) new Gson().fromJson(string, new b(this).getType()) : new ArrayList();
    }

    public FeatureMapping getFeatureMappingConfig() {
        return (FeatureMapping) new Gson().fromJson(b.getString(FEATURE_MAPPING_CONFIG, null), (Class<Object>) FeatureMapping.class);
    }

    public String getFitnessComputedApiCallLastUpdatedDate() {
        return b.getString(LAST_UPDATED_DATE_FITNESS_COMPUTED_API_CALL_OBJ, null);
    }

    public FitnessGoal getFitnessGoalCaloriesData() {
        return (FitnessGoal) new Gson().fromJson(b.getString(CREATE_FITNESS_GOAL_OBJECT_CALORIES, null), (Class<Object>) FitnessGoal.class);
    }

    public FitnessGoal getFitnessGoalDistanceData() {
        return (FitnessGoal) new Gson().fromJson(b.getString(CREATE_FITNESS_GOAL_OBJECT_DISTANCE, null), (Class<Object>) FitnessGoal.class);
    }

    public FitnessGoal getFitnessGoalExerciseMinutesData() {
        return (FitnessGoal) new Gson().fromJson(b.getString(CREATE_FITNESS_GOAL_OBJECT_EXERCISE_MINUTES, null), (Class<Object>) FitnessGoal.class);
    }

    public FitnessGoal getFitnessGoalSleepData() {
        return (FitnessGoal) new Gson().fromJson(b.getString(CREATE_FITNESS_GOAL_OBJECT_SLEEP, null), (Class<Object>) FitnessGoal.class);
    }

    public FitnessGoal getFitnessGoalStepsData() {
        return (FitnessGoal) new Gson().fromJson(b.getString(CREATE_FITNESS_GOAL_OBJECT_STEPS, null), (Class<Object>) FitnessGoal.class);
    }

    public FitnessGoal getFitnessGoalWalkHourData() {
        return (FitnessGoal) new Gson().fromJson(b.getString(CREATE_FITNESS_GOAL_OBJECT_WALK_HOUR, null), (Class<Object>) FitnessGoal.class);
    }

    public GoalSettingsData getGoalSttingsData() {
        GoalSettingsData goalSettingsData = (GoalSettingsData) new Gson().fromJson(b.getString(GOAL_SETTINGS_OBJECT, null), (Class<Object>) GoalSettingsData.class);
        return goalSettingsData == null ? GoalSettingsData.getInstance() : goalSettingsData;
    }

    public String getHeightUnit() {
        return b.getString(HEIGHT_UNIT_TYPE, AppUtils.getUnitMapping(getCountryCode()).get("HEIGHT").toString());
    }

    public int getIotUserHeartbeatApiFrequency() {
        return b.getInt(IOT_HEART_BEAT_API_FREW, 15);
    }

    public Date getLastActivitySyncDate() {
        Date date = null;
        try {
            date = (Date) new GsonBuilder().setDateFormat("MMM dd, yyyy HH:mm:ss").create().fromJson(b.getString(ACTIVITY_LAST_SYNC_DATE, null), (Class<Object>) Date.class);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (date == null) {
            Calendar calendar = Calendar.getInstance();
            calendar.add(6, -6);
            return calendar.getTime();
        }
        return date;
    }

    public String getLastIncomingNumberToQuickReply() {
        return b.getString(KEY_LAST_INCOMING_NUMBER_TO_QUICK_REPLY, null);
    }

    public LiveHeartRateModel getLastLiveHr() {
        return (LiveHeartRateModel) new Gson().fromJson(b.getString(LAST_LIVE_HR_OBJECT, null), (Class<Object>) LiveHeartRateModel.class);
    }

    public LastNightSleepData getLastNightSleepData(String str) {
        Gson gson = new Gson();
        SharedPreferences sharedPreferences = b;
        return (LastNightSleepData) gson.fromJson(sharedPreferences.getString(KEY_LAST_NIGHT_SLEEP_DATA + str, null), (Class<Object>) LastNightSleepData.class);
    }

    public Long getLastPPGSyncTimestamp(String str) {
        SharedPreferences sharedPreferences = b;
        return Long.valueOf(sharedPreferences.getLong(KEY_LAST_RAW_PPG_SYNC_TIMESTAMP + str, -1L));
    }

    public long getLastUpdateEnergyMeterHistoryTimeStamp(String str) {
        return b.getLong("LAST_SYNCED_DATE_FOR_ENERGY_METER_HISTORY" + str, 0L);
    }

    public long getLastUpdateTimeStatusApi() {
        return b.getLong(LAST_UPDATE_TIME_STATUS_API, 0L);
    }

    public long getLastUpdateWeatherTimeStamp() {
        return b.getLong(LAST_UPDATED_WEATHER_TIMESTAMP, 0L);
    }

    public String getLastWatchFaceBackgroundUrl() {
        return b.getString(LAST_WATCH_FACE_BACKGROUND_URL, null);
    }

    public LatestHealthDataModel getLatestHRValueFromPref() {
        return (LatestHealthDataModel) new Gson().fromJson(b.getString(LATEST_HR_DATA, null), (Class<Object>) LatestHealthDataModel.class);
    }

    public LatestHealthDataModel getLatestSpo2FromPref() {
        return (LatestHealthDataModel) new Gson().fromJson(b.getString(LATEST_SPO2_DATA, null), (Class<Object>) LatestHealthDataModel.class);
    }

    public LatestHealthDataModel getLatestTemperatureValueFromPref() {
        return (LatestHealthDataModel) new Gson().fromJson(b.getString(LATEST_TEMPERATURE_DATA, null), (Class<Object>) LatestHealthDataModel.class);
    }

    public int getLiftWristToViewEndHour() {
        return b.getInt(LIFT_WRIST_TO_VIEW_END_HOUR_24H_FORMAT, 22);
    }

    public int getLiftWristToViewEndMin() {
        return b.getInt(LIFT_WRIST_TO_VIEW_END_MINUTE, 0);
    }

    public int getLiftWristToViewStartHour() {
        return b.getInt(LIFT_WRIST_TO_VIEW_START_HOUR_24H_FORMAT, 8);
    }

    public int getLiftWristToViewStartMin() {
        return b.getInt(LIFT_WRIST_TO_VIEW_START_MINUTE, 0);
    }

    public HealthDataModel getLiveHealthData() {
        HealthDataModel healthDataModel = (HealthDataModel) new Gson().fromJson(b.getString(LAST_HEART_RATE_BP, null), (Class<Object>) HealthDataModel.class);
        return healthDataModel == null ? new HealthDataModel() : healthDataModel;
    }

    public StepsDataModel getLiveStepsData(Calendar calendar, String str) {
        String str2 = LIVE_STEPS + AppUtils.formatDate(calendar.getTime(), "yyyy-MM-dd") + str;
        StepsDataModel stepsDataModel = (StepsDataModel) new Gson().fromJson(b.getString(str2, null), (Class<Object>) StepsDataModel.class);
        if (stepsDataModel == null) {
            stepsDataModel = new StepsDataModel();
            for (String str3 : b.getAll().keySet()) {
                if (str3.startsWith(LIVE_STEPS) && !str3.equalsIgnoreCase(str2)) {
                    c.remove(str3);
                    c.commit();
                }
            }
        }
        return stepsDataModel;
    }

    public NotificationSettings getNotificationsData() {
        return (NotificationSettings) new Gson().fromJson(b.getString(NOTIFICATION_OBJECT, null), (Class<Object>) NotificationSettings.class);
    }

    public WebViewUrlBean getPlanConfigUrlsFromPref() {
        return (WebViewUrlBean) new Gson().fromJson(b.getString(WEB_URL_BEAN, null), (Class<Object>) WebViewUrlBean.class);
    }

    public int getProbeInterval() {
        return b.getInt(PROBE_INTERVAL, 15);
    }

    public QuickReplyListModel getQuickReplyListFromPref() {
        return (QuickReplyListModel) new Gson().fromJson(b.getString(QUICK_REPLY_DATA, null), (Class<Object>) QuickReplyListModel.class);
    }

    public List<ScheduleData> getScheduleData() {
        String string = b.getString(SCHEDULE_OBJECT, null);
        return string != null ? (List) new Gson().fromJson(string, new g(this).getType()) : new ArrayList();
    }

    public SedentaryReminderData getSedentaryReminderData() {
        return (SedentaryReminderData) new Gson().fromJson(b.getString(SEDENTARY_REMINDER_OBJECT, null), (Class<Object>) SedentaryReminderData.class);
    }

    public List<ShowHideData> getShortcutsList() {
        String string = b.getString(SHORTCUTS_HIDE_SHOW_OBJECT, null);
        new ArrayList();
        return (List) new Gson().fromJson(string, new i(this).getType());
    }

    public List<ShowHideData> getSportsTypeList() {
        String string = b.getString(SPORTS_HIDE_SHOW_OBJECT, null);
        new ArrayList();
        return (List) new Gson().fromJson(string, new j(this).getType());
    }

    public String getStrideLengthUnit() {
        return b.getString(STRIDE_LENGTH_UNIT_TYPE, AppUtils.getUnitMapping(getCountryCode()).get("HEIGHT").toString());
    }

    public ArrayList<CoveContact> getSyncedContacts() {
        String string = b.getString(SYNCED_CONTACTS, null);
        return string != null ? (ArrayList) new Gson().fromJson(string, new h(this).getType()) : new ArrayList<>();
    }

    public long getTimestampDisableAutoHRDialogShown(String str) {
        SharedPreferences sharedPreferences = b;
        return sharedPreferences.getLong("timestamp_disable_auto_dialog_shown_" + str, 0L);
    }

    public boolean getVertexForceLiftWristToViewPushed() {
        return b.getBoolean(IS_VERTEX_FORCE_LIFT_WRIST_TO_VIEW_PUSHED, false);
    }

    public List<VibrationAlarmData> getVibrationAlarmData() {
        String string = b.getString(VIBRATION_ALARM_OBJECT, null);
        return string != null ? (List) new Gson().fromJson(string, new d(this).getType()) : new ArrayList();
    }

    public int getWatchFaceLayoutSelectedColor() {
        return b.getInt(WATCH_FACE_LAYOUT_SELECTED_COLOR, 0);
    }

    public int getWatchFaceLayoutSelectedStyle() {
        return b.getInt(WATCH_FACE_LAYOUT_SELECTED_STYLE, 0);
    }

    public WeeklyReportPrefData getWeeklyReportData() {
        return (WeeklyReportPrefData) new Gson().fromJson(b.getString(WEEKLY_SUBSCRIPTION, null), (Class<Object>) WeeklyReportPrefData.class);
    }

    public String getWeightUnit() {
        return b.getString(WEIGHT_UNIT_TYPE, AppUtils.getUnitMapping(getCountryCode()).get("WEIGHT").toString());
    }

    public WomenWellnessData getWomenWellnessData() {
        return (WomenWellnessData) new Gson().fromJson(b.getString(WOMEN_WELLNESS_OBJECT, null), (Class<Object>) WomenWellnessData.class);
    }

    public List<WorldClockPrefData> getWorldClocList() {
        String string = b.getString(WOLRD_CLOCK_OBJ, null);
        new ArrayList();
        return (List) new Gson().fromJson(string, new a(this).getType());
    }

    public Boolean isAmbientSoundSettingsEnabled() {
        return Boolean.valueOf(b.getBoolean(KEY_AMBIENT_SOUND_SETTINGS_ENABLED, false));
    }

    public Boolean isAutoActivityDetectionDisclaimerSeen() {
        return Boolean.valueOf(b.getBoolean(KEY_AUTO_ACTIVITY_DETECTION_DISCLAIMER_SEEN, false));
    }

    public Boolean isAutoHRIntervalSaved() {
        return Boolean.valueOf(b.getBoolean("auto_hr_interval_preference", false));
    }

    public boolean isAutoHrEnabled() {
        return b.getBoolean(IS_AUTO_HR_ENABLED, true);
    }

    public Boolean isCameraLaunched() {
        return Boolean.valueOf(b.getBoolean(IS_CAMERA_LAUNCHED, false));
    }

    public boolean isDisconnectionAtAgpsFileUpdate() {
        return b.getBoolean(IS_DISCONNECTION_AT_AGPS_FILE_UPDATE, false);
    }

    public Boolean isDistanceUnitInMile() {
        return Boolean.valueOf(b.getBoolean(DISTANCE_UNIT_PREFERENCE, false));
    }

    public boolean isEnableSleepEnergyScoreFeature(Context context) {
        return b.getBoolean(ENABLE_SLEEP_ENERGY_SCORE_FEATURE, false);
    }

    public boolean isGoogleFitConnected(Context context) {
        return b.getBoolean(GOOGLE_FIT_CONNECTED, false);
    }

    public Boolean isGoogleFitHrSPO2TempSleepSupportAvailable() {
        return Boolean.valueOf(b.getBoolean(KEY_IS_GOOGLE_FIT_HR_SPO2_TEMP_SLEEP_SUPPORT_AVAILABLE, false));
    }

    public Boolean isLiftWristToViewEnable() {
        return Boolean.valueOf(b.getBoolean(LIFT_WRIST_TO_VIEW, true));
    }

    public boolean isOneKSupported() {
        return b.getBoolean(IS_ONK_SUPPORTED, false);
    }

    public boolean isRemoteMonitoringConsentGiven() {
        return b.getBoolean(REMOTE_MONITORING_CONSENT, false);
    }

    public boolean isRespiratoryRateFeatureEnabled(Context context) {
        return b.getBoolean(KEY_IS_RESPIRATORY_RATE_FEATURE_ENABLED, false);
    }

    public Boolean isRightHandSelected() {
        return Boolean.valueOf(b.getBoolean(HAND_PREFERENCE, false));
    }

    public boolean isScheduleDndOn() {
        return b.getBoolean(SCEDULE_DND_ON, false);
    }

    public Boolean isScheduleLiftWristToViewEnable() {
        return Boolean.valueOf(b.getBoolean(SCHEDULE_LIFT_WRIST_TO_VIEW, true));
    }

    public Boolean isTemperatureUnitInFahrenheit() {
        return Boolean.valueOf(b.getBoolean(TEMPERATURE_UNIT_PREFERENCE, true));
    }

    public Boolean isTimeIn12HourFormat() {
        return Boolean.valueOf(b.getBoolean(HOUR_FORMAT_PREFERENCE, true));
    }

    public void registerLatestHealthDataUpdateListener(LatestHealthDataUpdateListener latestHealthDataUpdateListener) {
        this.f7005a.add(latestHealthDataUpdateListener);
    }

    public void remove(Context context, String str) {
        if (b.contains(str)) {
            c.remove(str);
            c.commit();
        }
    }

    public void resetHeathData(Context context) {
        remove(context, LATEST_SPO2_DATA);
        remove(context, LATEST_HR_DATA);
        remove(context, LATEST_TEMPERATURE_DATA);
    }

    public void saveAgpsFileLastUpdatedDate(String str) {
        c.putString(AGPS_OBJECT, str);
        c.commit();
    }

    public void saveAmbientSoundSettingsEnabled(Boolean bool) {
        c.putBoolean(KEY_AMBIENT_SOUND_SETTINGS_ENABLED, bool.booleanValue());
        c.commit();
    }

    public void saveAppNotificationsSettings(List<AppNotificationData> list) {
        c.putString(APP_NOTIFICATION_OBJECT, new Gson().toJson(list));
        c.commit();
    }

    public void saveAutoActivityDetectionData(AutoActivityDetectionData autoActivityDetectionData) {
        c.putString(AUTO_RECOGNITION_OBJECT_NEW, new Gson().toJson(autoActivityDetectionData));
        c.commit();
    }

    public void saveAutoActivityDetectionDisclaimerSeen(Boolean bool) {
        c.putBoolean(KEY_AUTO_ACTIVITY_DETECTION_DISCLAIMER_SEEN, bool.booleanValue());
        c.commit();
    }

    public void saveAutoHrEnabled(boolean z) {
        c.putBoolean(IS_AUTO_HR_ENABLED, z).commit();
    }

    public void saveAutoHrInterval(int i2) {
        c.putInt(SELECTED_AUTO_HR_VAL, i2).commit();
    }

    public void saveAutoRecognitionList(List<AutoRecognitionData> list) {
        c.putString(AUTO_RECOGNITION_OBJECT, new Gson().toJson(list));
        c.commit();
    }

    public void saveAutoSPO2Settings(AutoSPO2SettingsData autoSPO2SettingsData) {
        c.putString(AUTO_SP02_SETTINGS_OBJECT, new Gson().toJson(autoSPO2SettingsData));
        c.commit();
    }

    public void saveAutoStressSettings(AutoStressSettingsData autoStressSettingsData) {
        c.putString(AUTO_STRESS_SETTINGS_OBJECT, new Gson().toJson(autoStressSettingsData));
        c.commit();
    }

    public void saveAutoTemperatureEnabled(Boolean bool) {
        c.putBoolean(IS_AUTO_TEMPERATURE_ENABLED, bool.booleanValue());
        c.commit();
    }

    public void saveAutoTemperatureInterval(Integer num) {
        c.putInt(SELECTED_AUTO_TEMPERATURE_VAL, num != null ? num.intValue() : 60);
        c.commit();
    }

    public void saveBatteryLevelData(BatteryLevelData batteryLevelData) {
        c.putString(BATTERY_LEVEL_DATA, new Gson().toJson(batteryLevelData));
        c.commit();
    }

    public void saveBatteryOptimizationConfig(BatteryOptimizationConfig batteryOptimizationConfig) {
        c.putString(BATTERY_OPTIMIZATION_CONFIG, new Gson().toJson(batteryOptimizationConfig));
        c.commit();
    }

    public void saveBpCalibrationSettings(BpCalibrationData bpCalibrationData) {
        c.putString(BP_CALIBRATION_OBJECT, new Gson().toJson(bpCalibrationData));
        c.commit();
    }

    public void saveCameraSettings(CameraSettingsData cameraSettingsData) {
        c.putString(CAMERA_SETTINGS_OBJECT, new Gson().toJson(cameraSettingsData));
        c.commit();
    }

    public void saveCloudWatchFacePosition(int i2) {
        c.putInt(CLOUD_WATCH_FACE_POSITION, i2);
        c.commit();
    }

    public void saveCloudWatchFacePositionForS2(int i2) {
        c.putInt(CLOUD_WATCH_FACE_POSITION_S2, i2);
        c.commit();
    }

    public void saveConnectedBTCallDeviceMac(String str) {
        c.putString("CONNECTED_BT_CALL_DEVICE_MAC", str).commit();
    }

    public void saveConnectedBTCallDeviceName(String str) {
        c.putString("CONNECTED_BT_CALL_DEVICE_NAME", str).commit();
    }

    public void saveConnectedSecondaryDeviceMAcAddress(String str) {
        c.putString("SECONDARY_CONNECTED_DEVICE_MACADDRESS", str).commit();
    }

    public void saveConnectedSecondaryDeviceName(String str) {
        c.putString("SECONDARY_CONNECTED_DEVICE_NAME", str).commit();
    }

    public void saveCountryCode(String str) {
        c.putString(COUNTRY_ISO_CODE, str);
        c.commit();
    }

    public void saveDisconnectionAtAgpsFileUpdate(boolean z) {
        c.putBoolean(IS_DISCONNECTION_AT_AGPS_FILE_UPDATE, z);
        c.commit();
    }

    public void saveDisplayModeSettings(int[] iArr) {
        c.putString(DISPLAY_MODE_OBJECT, new Gson().toJson(iArr));
        c.commit();
    }

    public void saveDistanceUnitPref(Boolean bool) {
        c.putBoolean(DISTANCE_UNIT_PREFERENCE, bool.booleanValue());
        c.commit();
    }

    public void saveDoNotDisturbSettings(DoNotDisturbData doNotDisturbData) {
        c.putString(DONOT_DISTURB_OBJECT, new Gson().toJson(doNotDisturbData));
        c.commit();
    }

    public void saveDrinkWaterReminderSettings(DrinkWaterReminderData drinkWaterReminderData) {
        c.putString(DRINK_WATER_REMINDER_OBJECT, new Gson().toJson(drinkWaterReminderData));
        c.commit();
    }

    public void saveEventReminderData(List<EventReminderData> list) {
        c.putString(EVENT_REMINDER_OBJECT, new Gson().toJson(list));
        c.commit();
    }

    public void saveFeatureMappingConfig(FeatureMapping featureMapping) {
        c.putString(FEATURE_MAPPING_CONFIG, new Gson().toJson(featureMapping));
        c.commit();
    }

    public void saveFitnessComputedApiCallLastUpdatedDate(String str) {
        c.putString(LAST_UPDATED_DATE_FITNESS_COMPUTED_API_CALL_OBJ, str).commit();
    }

    public void saveFitnessGoalCalories(FitnessGoal fitnessGoal) {
        c.putString(CREATE_FITNESS_GOAL_OBJECT_CALORIES, new Gson().toJson(fitnessGoal));
        c.commit();
    }

    public void saveFitnessGoalDistance(FitnessGoal fitnessGoal) {
        c.putString(CREATE_FITNESS_GOAL_OBJECT_DISTANCE, new Gson().toJson(fitnessGoal));
        c.commit();
    }

    public void saveFitnessGoalExerciseMinutes(FitnessGoal fitnessGoal) {
        c.putString(CREATE_FITNESS_GOAL_OBJECT_EXERCISE_MINUTES, new Gson().toJson(fitnessGoal));
        c.commit();
    }

    public void saveFitnessGoalSleep(FitnessGoal fitnessGoal) {
        c.putString(CREATE_FITNESS_GOAL_OBJECT_SLEEP, new Gson().toJson(fitnessGoal));
        c.commit();
    }

    public void saveFitnessGoalSteps(FitnessGoal fitnessGoal) {
        c.putString(CREATE_FITNESS_GOAL_OBJECT_STEPS, new Gson().toJson(fitnessGoal));
        c.commit();
    }

    public void saveFitnessGoalWalkHour(FitnessGoal fitnessGoal) {
        c.putString(CREATE_FITNESS_GOAL_OBJECT_WALK_HOUR, new Gson().toJson(fitnessGoal));
        c.commit();
    }

    public void saveGoalSettings(GoalSettingsData goalSettingsData) {
        c.putString(GOAL_SETTINGS_OBJECT, new Gson().toJson(goalSettingsData));
        c.commit();
    }

    public void saveGoogleFitConnect(boolean z) {
        c.putBoolean(GOOGLE_FIT_CONNECTED, z).commit();
    }

    public void saveHandPref(Boolean bool) {
        c.putBoolean(HAND_PREFERENCE, bool.booleanValue());
        c.commit();
    }

    public void saveHeightUnitType(String str) {
        c.putString(HEIGHT_UNIT_TYPE, str);
        c.commit();
    }

    public void saveHourFormatPref(Boolean bool) {
        c.putBoolean(HOUR_FORMAT_PREFERENCE, bool.booleanValue());
        c.commit();
    }

    public void saveIotUserHeartbeatApiFrequency(int i2) {
        c.putInt(IOT_HEART_BEAT_API_FREW, i2);
        c.commit();
    }

    public void saveIsGoogleFitHrSPO2TempSleepSupportAvailable(Boolean bool) {
        c.putBoolean(KEY_IS_GOOGLE_FIT_HR_SPO2_TEMP_SLEEP_SUPPORT_AVAILABLE, bool.booleanValue());
        c.commit();
    }

    public void saveLastActivitySyncedDate(Date date) {
        c.putString(ACTIVITY_LAST_SYNC_DATE, new GsonBuilder().setDateFormat("MMM dd, yyyy HH:mm:ss").create().toJson(date));
        c.commit();
    }

    public void saveLastIncomingNumberToQuickReply(String str) {
        c.putString(KEY_LAST_INCOMING_NUMBER_TO_QUICK_REPLY, str).commit();
    }

    public void saveLastNightSleepData(String str, LastNightSleepData lastNightSleepData) {
        String json = new Gson().toJson(lastNightSleepData);
        SharedPreferences.Editor editor = c;
        editor.putString(KEY_LAST_NIGHT_SLEEP_DATA + str, json);
        c.commit();
    }

    public void saveLastPPGSyncTimestamp(String str, Long l) {
        SharedPreferences.Editor editor = c;
        editor.putLong(KEY_LAST_RAW_PPG_SYNC_TIMESTAMP + str, l.longValue()).commit();
    }

    public void saveLastUpdateEnergyMeterHistoryTimeStamp(long j2, String str) {
        c.putLong("LAST_SYNCED_DATE_FOR_ENERGY_METER_HISTORY" + str, j2);
        c.commit();
    }

    public void saveLastUpdateTimeStatusApi(long j2) {
        c.putLong(LAST_UPDATE_TIME_STATUS_API, j2);
        c.commit();
    }

    public void saveLastUpdateWeatherTimeStamp(long j2) {
        c.putLong(LAST_UPDATED_WEATHER_TIMESTAMP, j2);
        c.commit();
    }

    public void saveLastWatchFaceBackgroundUrl(String str) {
        c.putString(LAST_WATCH_FACE_BACKGROUND_URL, str);
        c.commit();
    }

    public void saveLatestHRValue(LatestHealthDataModel latestHealthDataModel) {
        c.putString(LATEST_HR_DATA, new Gson().toJson(latestHealthDataModel));
        c.commit();
        List<LatestHealthDataUpdateListener> list = this.f7005a;
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < this.f7005a.size(); i2++) {
            this.f7005a.get(i2).onUpdate(latestHealthDataModel);
        }
    }

    public void saveLatestSpo2Value(LatestHealthDataModel latestHealthDataModel) {
        c.putString(LATEST_SPO2_DATA, new Gson().toJson(latestHealthDataModel));
        c.commit();
        List<LatestHealthDataUpdateListener> list = this.f7005a;
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < this.f7005a.size(); i2++) {
            this.f7005a.get(i2).onUpdate(latestHealthDataModel);
        }
    }

    public void saveLatestTemperatureValue(LatestHealthDataModel latestHealthDataModel) {
        c.putString(LATEST_TEMPERATURE_DATA, new Gson().toJson(latestHealthDataModel));
        c.commit();
        List<LatestHealthDataUpdateListener> list = this.f7005a;
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i2 = 0; i2 < this.f7005a.size(); i2++) {
            this.f7005a.get(i2).onUpdate(latestHealthDataModel);
        }
    }

    public void saveLiftWristPref(Boolean bool) {
        c.putBoolean(LIFT_WRIST_TO_VIEW, bool.booleanValue());
        c.commit();
    }

    public void saveLiftWristToViewEndHour(Integer num) {
        c.putInt(LIFT_WRIST_TO_VIEW_END_HOUR_24H_FORMAT, num.intValue());
        c.commit();
    }

    public void saveLiftWristToViewEndMin(Integer num) {
        c.putInt(LIFT_WRIST_TO_VIEW_END_MINUTE, num.intValue());
        c.commit();
    }

    public void saveLiftWristToViewStartHour(Integer num) {
        c.putInt(LIFT_WRIST_TO_VIEW_START_HOUR_24H_FORMAT, num.intValue());
        c.commit();
    }

    public void saveLiftWristToViewStartMin(Integer num) {
        c.putInt(LIFT_WRIST_TO_VIEW_START_MINUTE, num.intValue());
        c.commit();
    }

    public void saveLiveHR(LiveHeartRateModel liveHeartRateModel) {
        c.putString(LAST_LIVE_HR_OBJECT, new Gson().toJson(liveHeartRateModel));
        c.commit();
    }

    public void saveLiveHealthData(HealthDataModel healthDataModel) {
        c.putString(LAST_HEART_RATE_BP, new Gson().toJson(healthDataModel));
        c.commit();
    }

    public void saveLiveStepsData(StepsDataModel stepsDataModel, Calendar calendar, String str) {
        String json = new Gson().toJson(stepsDataModel);
        c.putString(LIVE_STEPS + AppUtils.formatDate(calendar.getTime(), "yyyy-MM-dd") + str, json);
        c.commit();
    }

    public void saveNotificationsSettings(NotificationSettings notificationSettings) {
        c.putString(NOTIFICATION_OBJECT, new Gson().toJson(notificationSettings));
        c.commit();
    }

    public void saveOneKSupported(boolean z) {
        c.putBoolean(IS_ONK_SUPPORTED, z);
        c.commit();
    }

    public void savePlanConfigUrlsToPref(WebViewUrlBean webViewUrlBean) {
        c.putString(WEB_URL_BEAN, new Gson().toJson(webViewUrlBean));
        c.commit();
    }

    public void saveQuickReplyListData(QuickReplyListModel quickReplyListModel) {
        c.putString(QUICK_REPLY_DATA, new Gson().toJson(quickReplyListModel));
        c.commit();
    }

    public void saveRemoteMonitoringConsent(boolean z) {
        c.putBoolean(REMOTE_MONITORING_CONSENT, z).commit();
    }

    public void saveRespiratoryRateFeatureEnabled(boolean z) {
        c.putBoolean(KEY_IS_RESPIRATORY_RATE_FEATURE_ENABLED, z).commit();
    }

    public void saveScheduleLiftWristPref(Boolean bool) {
        c.putBoolean(SCHEDULE_LIFT_WRIST_TO_VIEW, bool.booleanValue());
        c.commit();
    }

    public void saveScheduleSettings(List<ScheduleData> list) {
        c.putString(SCHEDULE_OBJECT, new Gson().toJson(list));
        c.commit();
    }

    public void saveScheuleDnd(boolean z) {
        c.putBoolean(SCEDULE_DND_ON, z).commit();
    }

    public void saveSedentaryReminderSettings(SedentaryReminderData sedentaryReminderData) {
        c.putString(SEDENTARY_REMINDER_OBJECT, new Gson().toJson(sedentaryReminderData));
        c.commit();
    }

    public void saveShortcutsList(List<ShowHideData> list) {
        c.putString(SHORTCUTS_HIDE_SHOW_OBJECT, new Gson().toJson(list));
        c.commit();
    }

    public void saveSleepEnergyScoreFeature(boolean z) {
        c.putBoolean(ENABLE_SLEEP_ENERGY_SCORE_FEATURE, z).commit();
    }

    public void saveSportsTypeList(List<ShowHideData> list) {
        c.putString(SPORTS_HIDE_SHOW_OBJECT, new Gson().toJson(list));
        c.commit();
    }

    public void saveStrideLengthUnitType(String str) {
        c.putString(STRIDE_LENGTH_UNIT_TYPE, str);
        c.commit();
    }

    public void saveSyncedContacts(ArrayList<CoveContact> arrayList) {
        c.putString(SYNCED_CONTACTS, new Gson().toJson(arrayList));
        c.commit();
    }

    public void saveTemperatureUnitPref(Boolean bool) {
        c.putBoolean(TEMPERATURE_UNIT_PREFERENCE, bool.booleanValue());
        c.commit();
    }

    public void saveTimestampDisableAutoHRDialogShown(String str, long j2) {
        SharedPreferences.Editor editor = c;
        editor.putLong("timestamp_disable_auto_dialog_shown_" + str, j2);
        c.commit();
    }

    public void saveVertexForceLiftWristToViewPushed(Boolean bool) {
        c.putBoolean(IS_VERTEX_FORCE_LIFT_WRIST_TO_VIEW_PUSHED, bool.booleanValue());
        c.commit();
    }

    public void saveVibrationAlarmSettings(List<VibrationAlarmData> list) {
        c.putString(VIBRATION_ALARM_OBJECT, new Gson().toJson(list));
        c.commit();
    }

    public void saveWatchFaceLayoutSelectedColor(int i2) {
        c.putInt(WATCH_FACE_LAYOUT_SELECTED_COLOR, i2);
        c.commit();
    }

    public void saveWatchFaceLayoutSelectedStyle(int i2) {
        c.putInt(WATCH_FACE_LAYOUT_SELECTED_STYLE, i2);
        c.commit();
    }

    public void saveWeeklyReport(WeeklyReportPrefData weeklyReportPrefData) {
        c.putString(WEEKLY_SUBSCRIPTION, new Gson().toJson(weeklyReportPrefData));
        c.commit();
    }

    public void saveWeightUnitType(String str) {
        c.putString(WEIGHT_UNIT_TYPE, str);
        c.commit();
    }

    public void saveWomenWellnessSettings(WomenWellnessData womenWellnessData) {
        c.putString(WOMEN_WELLNESS_OBJECT, new Gson().toJson(womenWellnessData));
        c.commit();
    }

    public void saveWorldClockList(List<WorldClockPrefData> list) {
        c.putString(WOLRD_CLOCK_OBJ, new Gson().toJson(list));
        c.commit();
    }

    public void setAutoHRIntervalSaved(Boolean bool) {
        c.putBoolean("auto_hr_interval_preference", bool.booleanValue());
        c.commit();
    }

    public void setEnableGuardianFeature(Boolean bool) {
        c.putBoolean(SHOULD_ENABLE_GUARDIAN_FEATURE, bool.booleanValue());
        c.commit();
    }

    public void setIsCameraLaunched(Boolean bool) {
        c.putBoolean(IS_CAMERA_LAUNCHED, bool.booleanValue());
        c.commit();
    }

    public void setProbeInterval(int i2) {
        c.putInt(PROBE_INTERVAL, i2);
        c.commit();
    }

    public Boolean shouldEnableGuardianFeature() {
        return Boolean.valueOf(b.getBoolean(SHOULD_ENABLE_GUARDIAN_FEATURE, false));
    }

    public void unregisterAllLatestHealthDataUpdateListener() {
        this.f7005a.clear();
    }

    public void unregisterLatestHealthDataUpdateListener(LatestHealthDataUpdateListener latestHealthDataUpdateListener) {
        this.f7005a.remove(latestHealthDataUpdateListener);
    }

    public void saveDisplayModeSettings(DisplayModeData displayModeData) {
        c.putString(DISPLAY_MODE_OBJECT, new Gson().toJson(displayModeData));
        c.commit();
    }
}
