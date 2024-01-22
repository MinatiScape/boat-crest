package com.coveiot.covepreferences;

import android.content.Context;
import android.content.SharedPreferences;
import com.coveiot.covepreferences.data.AclBT3LastReceivedConnectionStatus;
import com.coveiot.covepreferences.data.AlexaDetails;
import com.coveiot.covepreferences.data.AlexaLocale;
import com.coveiot.covepreferences.data.DeviceModelBean;
import com.coveiot.covepreferences.data.FirmwareCapabilityData;
import com.coveiot.covepreferences.data.NameDetails;
import com.coveiot.covepreferences.data.OTPResendTimerConfigBean;
import com.coveiot.covepreferences.data.ProfileData;
import com.coveiot.covepreferences.data.QRTrayCodeData;
import com.coveiot.covepreferences.data.RespiratoryRateRemoteConfiguration;
import com.coveiot.covepreferences.data.SavedQRCodeModel;
import com.coveiot.covepreferences.data.ScanOnDisconnectCriteria;
import com.coveiot.covepreferences.data.StressConfiguration;
import com.coveiot.covepreferences.listener.FirebaseRemoteConfigChangeListener;
import com.coveiot.covepreferences.sos.SOSData;
import com.coveiot.covepreferences.sos.SOSEvents;
import com.coveiot.utils.model.CoveContact;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.szabh.smable3.entity.Languages;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes8.dex */
public class SessionManager {
    public static final String KEY_NAME_DETAILS = "KEY_NAME_DETAILS";
    public static final String KEY_PROFILE_OBJECT = "profile_object";
    public static final String KEY_QR_TRAY_CODE_DETAILS = "KEY_QR_TRAY_CODE_DETAILS";
    public static SessionManager e;

    /* renamed from: a  reason: collision with root package name */
    public SharedPreferences f7004a;
    public SharedPreferences.Editor b;
    public int c = 0;
    public List<FirebaseRemoteConfigChangeListener> d = new ArrayList();

    /* loaded from: classes8.dex */
    public class a extends TypeToken<ArrayList<SOSEvents>> {
        public a(SessionManager sessionManager) {
        }
    }

    public SessionManager(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("FHSCore", this.c);
        this.f7004a = sharedPreferences;
        this.b = sharedPreferences.edit();
    }

    public static SessionManager getInstance(Context context) {
        if (e == null) {
            e = new SessionManager(context);
        }
        return e;
    }

    public boolean IsCricketImageUploaded() {
        return this.f7004a.getBoolean("is_cricket_image_uploaded", false);
    }

    public void clearFirebaseRemoteConfigChangeListeners() {
        this.d.clear();
    }

    public void clearPreferences(Context context) {
        for (String str : this.f7004a.getAll().keySet()) {
            remove(context, str);
        }
        clearFirebaseRemoteConfigChangeListeners();
    }

    public void createDevicePairedSession() {
        this.b.putBoolean("IsDevicePaired", true);
        this.b.commit();
    }

    public void createLoginSession(ProfileData profileData) {
        String json = new Gson().toJson(profileData);
        this.b.putBoolean("IsLoggedIn", true);
        this.b.putString("profile_object", json);
        this.b.commit();
    }

    public void deleteOptionalFirmwareBeanString() {
        this.b.remove("firmware_bean");
        this.b.commit();
    }

    public void deleteSoftwareUpdateResponseBeanString() {
        this.b.remove("software_update_bean");
        this.b.commit();
    }

    public AclBT3LastReceivedConnectionStatus getAclBt3LastReceivedConnectionStatus() {
        return (AclBT3LastReceivedConnectionStatus) new Gson().fromJson(this.f7004a.getString("acl_bt3_last_received_connection_status", null), (Class<Object>) AclBT3LastReceivedConnectionStatus.class);
    }

    public String getAlexaAccountLinkedFrom() {
        return this.f7004a.getString("alexa_account_linked_from", null);
    }

    public String getAlexaAccountLinkingAppStateCode() {
        return this.f7004a.getString("alexa_account_linking_app_state_code", null);
    }

    public boolean getAlexaAccountLinkingStatus() {
        return this.f7004a.getBoolean("key_alexa_account_linking_status", false);
    }

    public AlexaDetails getAlexaDetails() {
        return (AlexaDetails) new Gson().fromJson(this.f7004a.getString("alexa_rcf_object", null), (Class<Object>) AlexaDetails.class);
    }

    public String getAppVersionName() {
        return this.f7004a.getString("app_version_name", null);
    }

    public StressConfiguration getAutoStressConfig() {
        StressConfiguration stressConfiguration = (StressConfiguration) new Gson().fromJson(this.f7004a.getString("key_auto_stress_config", null), (Class<Object>) StressConfiguration.class);
        if (stressConfiguration == null) {
            StressConfiguration stressConfiguration2 = new StressConfiguration();
            StressConfiguration.Stress stress = new StressConfiguration.Stress();
            stress.setAlert(new StressConfiguration.Stress.Alert());
            stressConfiguration2.setStress(stress);
            return stressConfiguration2;
        }
        return stressConfiguration;
    }

    public String getAxTrackerId() {
        return this.f7004a.getString("ac_tracker_id", null);
    }

    public long getBT3CallFTUShown() {
        return this.f7004a.getLong("key_bt3_call_ftu", -1L);
    }

    public String getBleDeviceInfo() {
        return this.f7004a.getString("ble_device_info", "");
    }

    public String getBleDeviceName() {
        return this.f7004a.getString("ble_device_name", "");
    }

    public String getBleDeviceUIInfo() {
        return this.f7004a.getString("ble_device_ui_info", "");
    }

    public Integer getCloudNewWatchFaceItems() {
        return Integer.valueOf(this.f7004a.getInt("cloud_new_watch_face_items", 0));
    }

    public Integer getCloudWatchFaceItems() {
        return Integer.valueOf(this.f7004a.getInt("cloud_watch_face_items", 0));
    }

    public String getCoinsCardImage() {
        return this.f7004a.getString("coins_card_image", "");
    }

    public String getCoinsHomeUrl() {
        return this.f7004a.getString("coins_home_url", "");
    }

    public String getConnectedDeviceMacAddress() {
        return this.f7004a.getString("CONNECTED_DEVICE_MACADDRESS", "");
    }

    public String getConnectedDeviceName() {
        return this.f7004a.getString("CONNECTED_DEVICE_NAME", "");
    }

    public DeviceModelBean getDeviceModelBean() {
        return (DeviceModelBean) new Gson().fromJson(this.f7004a.getString("device_model_bean", null), (Class<Object>) DeviceModelBean.class);
    }

    public String getDeviceType() {
        return this.f7004a.getString("KEY_DEVICE_TYPE", null);
    }

    public String getDirectionApiResponse() {
        return this.f7004a.getString("direction_api_response", null);
    }

    public Integer getDiyWatchFaceListItems() {
        return Integer.valueOf(this.f7004a.getInt("diy_watch_face_items", -1));
    }

    public String getFAQUrl() {
        return this.f7004a.getString("faq_url", null);
    }

    public FirmwareCapabilityData getFirmwareCapability(String str) {
        Gson gson = new Gson();
        SharedPreferences sharedPreferences = this.f7004a;
        FirmwareCapabilityData firmwareCapabilityData = (FirmwareCapabilityData) gson.fromJson(sharedPreferences.getString("fw_capability" + str, null), (Class<Object>) FirmwareCapabilityData.class);
        return firmwareCapabilityData == null ? new FirmwareCapabilityData() : firmwareCapabilityData;
    }

    public String getGuestSessionId() {
        return this.f7004a.getString("guest_session_id", null);
    }

    public Boolean getIsBatteryOptimisationDone() {
        return Boolean.valueOf(this.f7004a.getBoolean("IS_BATTERY_OPTIMISATION_DONE", false));
    }

    public Boolean getIsDiagnosticTestSelected() {
        return Boolean.valueOf(this.f7004a.getBoolean("IS_DIAGNOSTIC_TEST_SELECTED", true));
    }

    public Boolean getIsUpdatedWalktarget() {
        return Boolean.valueOf(this.f7004a.getBoolean("is_updated_walk_target", false));
    }

    public Boolean getIsWatchReconnected() {
        return Boolean.valueOf(this.f7004a.getBoolean("isWatchReconnected", false));
    }

    public boolean getLastBt3Status() {
        return this.f7004a.getBoolean("last_bt3_status", false);
    }

    public Float getLastCalorieBurnedFitness() {
        return Float.valueOf(this.f7004a.getFloat("last_calorie_burned_fitness", 0.0f));
    }

    public String getLastConnectedOxmDeviceMacAddress() {
        return this.f7004a.getString("last_connected_oxm_ble_mac_address", "");
    }

    public String getLastConnectedOxmDeviceName() {
        return this.f7004a.getString("last_connected_oxm_ble_name", "");
    }

    public Float getLastDistanceCoveredFitness() {
        return Float.valueOf(this.f7004a.getFloat("last_distance_covered_fitness", 0.0f));
    }

    public String getLastSensAIVideoCategory() {
        return this.f7004a.getString("LAST_SENS_AI_VIDEO_CATEGORY", null);
    }

    public String getLastSensAIVideoId() {
        return this.f7004a.getString("LAST_SENS_AI_VIDEO_ID", null);
    }

    public String getLastSensAIVideoName() {
        return this.f7004a.getString("LAST_SENS_AI_VIDEO_NAME", null);
    }

    public Float getLastSensAIVideoPosition() {
        return Float.valueOf(this.f7004a.getFloat("LAST_SENS_AI_VIDEO_POSITION", -1.0f));
    }

    public String getLastVideoCategory() {
        return this.f7004a.getString("LAST_VIDEO_CATEGORY", null);
    }

    public String getLastVideoId() {
        return this.f7004a.getString("LAST_VIDEO_ID", null);
    }

    public String getLastVideoName() {
        return this.f7004a.getString("LAST_VIDEO_NAME", null);
    }

    public Float getLastVideoPosition() {
        return Float.valueOf(this.f7004a.getFloat("LAST_VIDEO_POSITION", -1.0f));
    }

    public String getLegalDocType() {
        return this.f7004a.getString("legal_doc_type", null);
    }

    public String getLegalDocUrl() {
        return this.f7004a.getString("legal_doc_url", null);
    }

    public String getLegalDocVersion() {
        return this.f7004a.getString("legal_doc_version", null);
    }

    public NameDetails getNameDetails() {
        return (NameDetails) new Gson().fromJson(this.f7004a.getString(KEY_NAME_DETAILS, null), (Class<Object>) NameDetails.class);
    }

    public String getNavigationDiscliamerData() {
        return this.f7004a.getString("navigation_disclaimer_data", null);
    }

    public String getOptionalFirmwareBeanString() {
        return this.f7004a.getString("firmware_bean", "");
    }

    public float getPlanHistoryCount() {
        return this.f7004a.getInt("plan_history_count", 0);
    }

    public String getPreviousAppUpdateShownTime() {
        return this.f7004a.getString("app_update_shown_time", null);
    }

    public String getPreviousFirmwareUpdateShownTime() {
        return this.f7004a.getString("app_update_shown_time", null);
    }

    public String getPrivacyPolicyDocUrl() {
        return this.f7004a.getString("privacy_policy_doc_url", null);
    }

    public QRTrayCodeData getQRTrayCodeDetails() {
        return (QRTrayCodeData) new Gson().fromJson(this.f7004a.getString(KEY_QR_TRAY_CODE_DETAILS, null), (Class<Object>) QRTrayCodeData.class);
    }

    public String getRegistrationToken() {
        return this.f7004a.getString("KEY_FCM_REGISTRATION_TOKEN", null);
    }

    public OTPResendTimerConfigBean getResendOTPTimerConfigBean() {
        return (OTPResendTimerConfigBean) new Gson().fromJson(this.f7004a.getString("otp_resend_timer", null), (Class<Object>) OTPResendTimerConfigBean.class);
    }

    public RespiratoryRateRemoteConfiguration getRespiratoryRateRemoteConfig() {
        RespiratoryRateRemoteConfiguration respiratoryRateRemoteConfiguration = (RespiratoryRateRemoteConfiguration) new Gson().fromJson(this.f7004a.getString("key_respiratory_rate_remote_config", null), (Class<Object>) RespiratoryRateRemoteConfiguration.class);
        return respiratoryRateRemoteConfiguration == null ? new RespiratoryRateRemoteConfiguration() : respiratoryRateRemoteConfiguration;
    }

    public Integer getRoundedImage() {
        return Integer.valueOf(this.f7004a.getInt("rounded_image", 0));
    }

    public String getRsaPrivateKey() {
        return this.f7004a.getString("rsa_private_key", "");
    }

    public SOSData getSOSConfig() {
        return (SOSData) new Gson().fromJson(this.f7004a.getString("sos_config", null), (Class<Object>) SOSData.class);
    }

    public CoveContact getSOSContact() {
        return (CoveContact) new Gson().fromJson(this.f7004a.getString("sos_contact", null), (Class<Object>) CoveContact.class);
    }

    public String getSOSDisclaimerURL() {
        return this.f7004a.getString("sos_disclaimer_url", null);
    }

    public ArrayList<SOSEvents> getSOSEventsList() {
        String string = this.f7004a.getString("sos_events_list", null);
        return string != null ? (ArrayList) new Gson().fromJson(string, new a(this).getType()) : new ArrayList<>();
    }

    public SavedQRCodeModel getSavedQRCode() {
        return (SavedQRCodeModel) new Gson().fromJson(this.f7004a.getString("saved_qr_code_data", null), (Class<Object>) SavedQRCodeModel.class);
    }

    public ScanOnDisconnectCriteria getScanOnDisconnectCriteria() {
        return (ScanOnDisconnectCriteria) new Gson().fromJson(this.f7004a.getString("scan_on_disconnect_criteria", null), (Class<Object>) ScanOnDisconnectCriteria.class);
    }

    public AlexaLocale getSelectedAlexaLocale() {
        return (AlexaLocale) new Gson().fromJson(this.f7004a.getString("key_alexa_selected_locale", null), (Class<Object>) AlexaLocale.class);
    }

    public String getSelectedLanguage() {
        return this.f7004a.getString("selected_language", Languages.DEFAULT_LANGUAGE);
    }

    public int getSelectedPosition() {
        return this.f7004a.getInt("selected_position", 0);
    }

    public String getSensAIBannerImage() {
        return this.f7004a.getString("sens_ai_banner_image", "");
    }

    public String getSensAICardImage() {
        return this.f7004a.getString("sens_ai_card_image", "");
    }

    public String getSensAIVideoCategoryPassed() {
        return this.f7004a.getString("VIDEO_SENS_AI_CATEGORY_PASSED", null);
    }

    public long getShipbookConfigUpdatedTimestamp() {
        return this.f7004a.getLong("shipbook_config_updated_timestamp", -1L);
    }

    public String getSoftwareUpdateResponseBeanString() {
        return this.f7004a.getString("software_update_bean", "");
    }

    public String getSportsDisclaimerDocUrl() {
        return this.f7004a.getString("sports_disclaimer_doc_url", null);
    }

    public float getStepsOfPedometerSensor() {
        return this.f7004a.getFloat("pedometer_sensor_steps", -1.0f);
    }

    public String getTempDestinationAutosuggestionData() {
        return this.f7004a.getString("temp_destination_auto_suggestion_data", null);
    }

    public String getTempSourceAutosuggestionData() {
        return this.f7004a.getString("temp_source_auto_suggestion_data", null);
    }

    public String getTroubleshootUrl() {
        return this.f7004a.getString("troubleshoot_url", null);
    }

    public ProfileData getUserDetails() {
        return (ProfileData) new Gson().fromJson(this.f7004a.getString("profile_object", null), (Class<Object>) ProfileData.class);
    }

    public String getUserPlanBackground() {
        return this.f7004a.getString("user_plan_bg", null);
    }

    public String getUserPlanId() {
        return this.f7004a.getString("user_plan_id", null);
    }

    public String getVideoCategoryPassed() {
        return this.f7004a.getString("VIDEO_CATEGORY_PASSED", null);
    }

    public String getWatchFaceDiyToolCardImage() {
        return this.f7004a.getString("watch_face_diy_tool_card_image", "");
    }

    public String getWatchFaceDiyUrl() {
        return this.f7004a.getString("watch_face_diy_url", "");
    }

    public Integer getWatchFaceMaxAllowed() {
        return Integer.valueOf(this.f7004a.getInt("watch_face_max_allowed", -1));
    }

    public boolean is1790HealthMonitoringOnBoardingShown() {
        return this.f7004a.getBoolean("KEY_1790_HEALTH_MONITORING_ONBOARDING", false);
    }

    public boolean isAnalyticsUserPropertiesUpdated() {
        return this.f7004a.getBoolean("is_analytics_user_properties_updated", false);
    }

    public boolean isBoatCoinsFTUShown() {
        return this.f7004a.getBoolean("key_boat_coins_ftu", false);
    }

    public boolean isDevicePaired() {
        return this.f7004a.getBoolean("IsDevicePaired", false);
    }

    public boolean isDiyWatchFaceFTUShown() {
        return this.f7004a.getBoolean("key_diy_watch-face_ftu", false);
    }

    public boolean isExistingUserFirstTime() {
        return this.f7004a.getBoolean("KEY_EXISTING_USER_FIRST_TIME", false);
    }

    public boolean isFastLaneOnBoardingShown() {
        return this.f7004a.getBoolean("KEY_FASTLANE_ONBOARDING", false);
    }

    public boolean isForceFirmwareUpdated() {
        return this.f7004a.getBoolean("is_force_firmware_updated", true);
    }

    public boolean isFromOnboarding() {
        return this.f7004a.getBoolean("is_from_onboarding", false);
    }

    public boolean isGuestUser() {
        return this.f7004a.getBoolean("IS_GUEST_USER", false);
    }

    public boolean isHealthMonitoringOnBoardingShown() {
        return this.f7004a.getBoolean("KEY_HEALTHMONITORING_ONBOARDING", false);
    }

    public boolean isLanguageSet() {
        return this.f7004a.getBoolean("is_language_page", false);
    }

    public boolean isLogged() {
        return this.f7004a.getBoolean(FirebaseAnalytics.Event.LOGIN, false);
    }

    public boolean isLoggedIn() {
        return this.f7004a.getBoolean("IsLoggedIn", false);
    }

    public boolean isManualUnPairIntialtted() {
        return this.f7004a.getBoolean("KEY_IS_MANUAL_UNPAIR_INITIALTED", false);
    }

    public boolean isNavigationFinishActivity() {
        return this.f7004a.getBoolean("navigation_finish_activity", false);
    }

    public boolean isNewUser() {
        return this.f7004a.getBoolean("is_new_user", false);
    }

    public boolean isNewUserFirstTime() {
        return this.f7004a.getBoolean("KEY_NEW_USER_FIRST_TIME", false);
    }

    public boolean isOnboardingComplete() {
        return this.f7004a.getBoolean("KEY_IS_ONBOARDING_COMPLETE", false);
    }

    public boolean isPairDeviceAfterLogin() {
        return this.f7004a.getBoolean("isPairDeviceAfterLogin", false);
    }

    public boolean isPairDeviceLater() {
        return this.f7004a.getBoolean("isPairDeviceLater", false);
    }

    public boolean isQrTrayFtu() {
        return this.f7004a.getBoolean("is_qr_tray_ftu", false);
    }

    public boolean isQrTrayIntroSeen() {
        return this.f7004a.getBoolean("is_qr_tray_intro_seen", false);
    }

    public boolean isRateUsDialogShown() {
        return this.f7004a.getBoolean("RATE_US_DIALOG_SHOWN", false);
    }

    public boolean isRemoteMonitoringSupported() {
        return this.f7004a.getBoolean("is_remotemonitoringsupported", false);
    }

    public boolean isScanAllDeviceChoosen() {
        return this.f7004a.getBoolean("is_scan_all_devices", false);
    }

    public boolean isSelectedDeviceTypePhoneOnly() {
        return this.f7004a.getBoolean("key_is_selected_device_type_phone_only", false);
    }

    public boolean isShowIndusInd() {
        return this.f7004a.getBoolean("should_show_indus_ind", false);
    }

    public boolean isSmaMigrationRequired() {
        return this.f7004a.getBoolean("sma_is_migration_required", true);
    }

    public boolean isTapAndPayFtuShown() {
        return this.f7004a.getBoolean("is_tap_and_pay_ftu_shown", false);
    }

    public boolean isTroubleshootFtuShown() {
        return this.f7004a.getBoolean("is_troubleshoot_ftu_shown", false);
    }

    public boolean isWomenWellnessFTUNormalDay() {
        return this.f7004a.getBoolean("key_women_wellness_ftu_normal_day", false);
    }

    public boolean isWomenWellnessFTUShown() {
        return this.f7004a.getBoolean("key_women_wellness_ftu", false);
    }

    public void registerFirebaseRemoteConfigChangeListener(FirebaseRemoteConfigChangeListener firebaseRemoteConfigChangeListener) {
        this.d.add(firebaseRemoteConfigChangeListener);
    }

    public void remove(Context context, String str) {
        if (this.f7004a.contains(str)) {
            if (getGuestSessionId() == null) {
                if (str.equals("KEY_FCM_REGISTRATION_TOKEN")) {
                    return;
                }
                this.b.remove(str).commit();
            } else if (str.equals("guest_session_id")) {
            } else {
                this.b.remove(str).commit();
            }
        }
    }

    public void removeFirebaseRemoteConfigChangeListener(FirebaseRemoteConfigChangeListener firebaseRemoteConfigChangeListener) {
        this.d.remove(firebaseRemoteConfigChangeListener);
    }

    public void saveAlexaAccountLinkedFrom(String str) {
        this.b.putString("alexa_account_linked_from", str).commit();
    }

    public void saveAlexaAccountLinkingAppStateCode(String str) {
        this.b.putString("alexa_account_linking_app_state_code", str).commit();
    }

    public void saveAlexaDetails(AlexaDetails alexaDetails) {
        this.b.putString("alexa_rcf_object", new Gson().toJson(alexaDetails));
        this.b.commit();
    }

    public void saveAppUpdateShownTime(String str) {
        this.b.putString("app_update_shown_time", str);
        this.b.commit();
    }

    public void saveAutoStressConfig(StressConfiguration stressConfiguration) {
        this.b.putString("key_auto_stress_config", new Gson().toJson(stressConfiguration));
        this.b.commit();
    }

    public void saveBleDeviceInfo(String str) {
        this.b.putString("ble_device_info", str);
        this.b.commit();
    }

    public void saveBleDeviceName(String str) {
        this.b.putString("ble_device_name", str);
        this.b.commit();
    }

    public void saveBleDeviceUIInfo(String str) {
        this.b.putString("ble_device_ui_info", str);
        this.b.commit();
    }

    public void saveCloudNewWatchFaceItems(Integer num) {
        this.b.putInt("cloud_new_watch_face_items", num.intValue());
        this.b.commit();
    }

    public void saveCloudWatchFaceItems(Integer num) {
        this.b.putInt("cloud_watch_face_items", num.intValue());
        this.b.commit();
    }

    public void saveCoinsCardImage(String str) {
        this.b.putString("coins_card_image", str).commit();
    }

    public void saveCoinsHomeUrl(String str) {
        this.b.putString("coins_home_url", str).commit();
    }

    public void saveConnectedDeviceMAcAddress(String str) {
        this.b.putString("CONNECTED_DEVICE_MACADDRESS", str).commit();
    }

    public void saveConnectedDeviceName(String str) {
        this.b.putString("CONNECTED_DEVICE_NAME", str).commit();
    }

    public void saveDeviceModelBean(DeviceModelBean deviceModelBean) {
        this.b.putString("device_model_bean", new Gson().toJson(deviceModelBean));
        this.b.commit();
        List<FirebaseRemoteConfigChangeListener> list = this.d;
        if (list == null || list.isEmpty()) {
            return;
        }
        for (int i = 0; i < this.d.size(); i++) {
            this.d.get(i).onFirebaseConfigChanged();
        }
    }

    public void saveDeviceType(String str) {
        this.b.putString("KEY_DEVICE_TYPE", str);
        this.b.commit();
    }

    public void saveDirectionApiResponse(String str) {
        this.b.putString("direction_api_response", str);
        this.b.commit();
    }

    public void saveDiyWatchFaceListItems(Integer num) {
        this.b.putInt("diy_watch_face_items", num.intValue());
        this.b.commit();
    }

    public void saveFirmwareCapability(String str, FirmwareCapabilityData firmwareCapabilityData) {
        String json = new Gson().toJson(firmwareCapabilityData);
        SharedPreferences.Editor editor = this.b;
        editor.putString("fw_capability" + str, json);
        this.b.commit();
    }

    public void saveFirmwareUpdateShownTime(String str) {
        this.b.putString("app_update_shown_time", str);
        this.b.commit();
    }

    public void saveIsBatteryOptimisationDone(Boolean bool) {
        this.b.putBoolean("IS_BATTERY_OPTIMISATION_DONE", bool.booleanValue()).commit();
    }

    public void saveIsDiagnosticTestSelected(Boolean bool) {
        this.b.putBoolean("IS_DIAGNOSTIC_TEST_SELECTED", bool.booleanValue()).commit();
    }

    public void saveIsUpdatedWalkTarget(Boolean bool) {
        this.b.putBoolean("is_updated_walk_target", bool.booleanValue()).commit();
    }

    public void saveIsWatchReconnected(Boolean bool) {
        this.b.putBoolean("isWatchReconnected", bool.booleanValue()).commit();
    }

    public void saveLastSensAIVideoCategory(String str) {
        this.b.putString("LAST_SENS_AI_VIDEO_CATEGORY", str).commit();
    }

    public void saveLastSensAIVideoCategoryPassed(String str) {
        this.b.putString("VIDEO_SENS_AI_CATEGORY_PASSED", str).commit();
    }

    public void saveLastSensAIVideoID(String str) {
        this.b.putString("LAST_SENS_AI_VIDEO_ID", str).commit();
    }

    public void saveLastSensAIVideoName(String str) {
        this.b.putString("LAST_SENS_AI_VIDEO_NAME", str).commit();
    }

    public void saveLastSensAIVideoPosition(Float f) {
        this.b.putFloat("LAST_SENS_AI_VIDEO_POSITION", f.floatValue()).commit();
    }

    public void saveLastVideoCategory(String str) {
        this.b.putString("LAST_VIDEO_CATEGORY", str).commit();
    }

    public void saveLastVideoCategoryPassed(String str) {
        this.b.putString("VIDEO_CATEGORY_PASSED", str).commit();
    }

    public void saveLastVideoID(String str) {
        this.b.putString("LAST_VIDEO_ID", str).commit();
    }

    public void saveLastVideoName(String str) {
        this.b.putString("LAST_VIDEO_NAME", str).commit();
    }

    public void saveLastVideoPosition(Float f) {
        this.b.putFloat("LAST_VIDEO_POSITION", f.floatValue()).commit();
    }

    public void saveNameDetails(NameDetails nameDetails) {
        this.b.putString(KEY_NAME_DETAILS, new Gson().toJson(nameDetails));
        this.b.commit();
    }

    public void saveNavigationDiscliamerData(String str) {
        this.b.putString("navigation_disclaimer_data", str);
        this.b.commit();
    }

    public void saveOptionalFirmwareBeanString(String str) {
        this.b.putString("firmware_bean", str);
        this.b.commit();
    }

    public void savePlanHistoryCount(int i) {
        this.b.putInt("plan_history_count", i);
        this.b.commit();
    }

    public void saveQRTrayCodeDetails(QRTrayCodeData qRTrayCodeData) {
        this.b.putString(KEY_QR_TRAY_CODE_DETAILS, new Gson().toJson(qRTrayCodeData));
        this.b.commit();
    }

    public void saveQrCodeData(SavedQRCodeModel savedQRCodeModel) {
        this.b.putString("saved_qr_code_data", new Gson().toJson(savedQRCodeModel));
        this.b.commit();
    }

    public void saveRegistrationToken(String str) {
        this.b.putString("KEY_FCM_REGISTRATION_TOKEN", str);
        this.b.commit();
    }

    public void saveResendOTPTimerConfigBean(OTPResendTimerConfigBean oTPResendTimerConfigBean) {
        this.b.putString("otp_resend_timer", new Gson().toJson(oTPResendTimerConfigBean));
        this.b.commit();
    }

    public void saveRespiratoryRateRemoteConfig(RespiratoryRateRemoteConfiguration respiratoryRateRemoteConfiguration) {
        this.b.putString("key_respiratory_rate_remote_config", new Gson().toJson(respiratoryRateRemoteConfiguration));
        this.b.commit();
    }

    public void saveSOSConfig(SOSData sOSData) {
        this.b.putString("sos_config", new Gson().toJson(sOSData));
        this.b.commit();
    }

    public void saveSOSContact(CoveContact coveContact) {
        this.b.putString("sos_contact", new Gson().toJson(coveContact));
        this.b.commit();
    }

    public void saveSOSEventsList(ArrayList<SOSEvents> arrayList) {
        this.b.putString("sos_events_list", new Gson().toJson(arrayList));
        this.b.commit();
    }

    public void saveSelectedAlexaLocale(AlexaLocale alexaLocale) {
        this.b.putString("key_alexa_selected_locale", new Gson().toJson(alexaLocale));
        this.b.commit();
    }

    public void saveSensAIBannerImage(String str) {
        this.b.putString("sens_ai_banner_image", str).commit();
    }

    public void saveSensAICardImage(String str) {
        this.b.putString("sens_ai_card_image", str).commit();
    }

    public void saveShowSOSTooltip(boolean z) {
        this.b.putBoolean("should_show_sos_tooltip", z);
        this.b.commit();
    }

    public void saveSoftwareUpdateResponseString(String str) {
        this.b.putString("software_update_bean", str);
        this.b.commit();
    }

    public void saveStepsOfPedometerSensor(float f) {
        this.b.putFloat("pedometer_sensor_steps", f);
        this.b.commit();
    }

    public void saveTempDestinationAutosuggestionData(String str) {
        this.b.putString("temp_destination_auto_suggestion_data", str);
        this.b.commit();
    }

    public void saveTempSourceAutosuggestionData(String str) {
        this.b.putString("temp_source_auto_suggestion_data", str);
        this.b.commit();
    }

    public void saveWatchFaceDiyToolCardImage(String str) {
        this.b.putString("watch_face_diy_tool_card_image", str).commit();
    }

    public void saveWatchFaceDiyUrl(String str) {
        this.b.putString("watch_face_diy_url", str).commit();
    }

    public void saveWatchFaceMaxAllowed(Integer num) {
        this.b.putInt("watch_face_max_allowed", num.intValue());
        this.b.commit();
    }

    public void set1790HealthMonitoringOnboardingShown(boolean z) {
        this.b.putBoolean("KEY_1790_HEALTH_MONITORING_ONBOARDING", z).commit();
    }

    public void setAclBt3LastReceivedConnectionStatus(AclBT3LastReceivedConnectionStatus aclBT3LastReceivedConnectionStatus) {
        this.b.putString("acl_bt3_last_received_connection_status", new Gson().toJson(aclBT3LastReceivedConnectionStatus));
        this.b.commit();
    }

    public void setAlexaAccountLinkingStatus(boolean z) {
        this.b.putBoolean("key_alexa_account_linking_status", z);
        this.b.commit();
    }

    public void setAnalyticsUserPropertiesUpdated(boolean z) {
        this.b.putBoolean("is_analytics_user_properties_updated", z);
        this.b.commit();
    }

    public void setAppVersionName(String str) {
        this.b.putString("app_version_name", str).commit();
    }

    public void setAxTrackerId(String str) {
        this.f7004a.edit().putString("ac_tracker_id", str).apply();
    }

    public void setBT3CallFTUShown(Long l) {
        this.b.putLong("key_bt3_call_ftu", l.longValue()).commit();
    }

    public void setBoatCoinsFTUShown(boolean z) {
        this.b.putBoolean("key_boat_coins_ftu", z).commit();
    }

    public void setDiyWatchFaceFTUShown(boolean z) {
        this.b.putBoolean("key_diy_watch-face_ftu", z).commit();
    }

    public void setExistingUserFirstTime(boolean z) {
        this.b.putBoolean("KEY_EXISTING_USER_FIRST_TIME", z).commit();
    }

    public void setFAQUrl(String str) {
        this.b.putString("faq_url", str).commit();
    }

    public void setFastlaneOnboardingShown(boolean z) {
        this.b.putBoolean("KEY_FASTLANE_ONBOARDING", z).commit();
    }

    public void setForceFirmwareUpdated(boolean z) {
        this.b.putBoolean("is_force_firmware_updated", z);
        this.b.commit();
    }

    public void setGuestSessionId(String str) {
        this.f7004a.edit().putString("guest_session_id", str).apply();
    }

    public void setHealthMonitoringOnboardingShown(boolean z) {
        this.b.putBoolean("KEY_HEALTHMONITORING_ONBOARDING", z).commit();
    }

    public void setIsCricketImageUploaded(Boolean bool) {
        this.b.putBoolean("is_cricket_image_uploaded", bool.booleanValue());
        this.b.commit();
    }

    public void setIsDevicePaired(boolean z) {
        this.f7004a.edit().putBoolean("IsDevicePaired", z).apply();
    }

    public void setIsFromOnboarding(boolean z) {
        this.f7004a.edit().putBoolean("is_from_onboarding", z).apply();
    }

    public void setIsGuestUser(boolean z) {
        this.f7004a.edit().putBoolean("IS_GUEST_USER", z).apply();
    }

    public void setIsLogged() {
        this.b.putBoolean(FirebaseAnalytics.Event.LOGIN, true);
        this.b.commit();
    }

    public void setIsLoggedIn(boolean z) {
        this.f7004a.edit().putBoolean("IsLoggedIn", z).apply();
    }

    public void setIsManualUnpairInitialted(boolean z) {
        this.b.putBoolean("KEY_IS_MANUAL_UNPAIR_INITIALTED", z);
        this.b.commit();
    }

    public void setIsNewUser(boolean z) {
        this.f7004a.edit().putBoolean("is_new_user", z).apply();
    }

    public void setIsPairDeviceAfterLogin(boolean z) {
        this.f7004a.edit().putBoolean("isPairDeviceAfterLogin", z).apply();
    }

    public void setIsPairDeviceLater(boolean z) {
        this.f7004a.edit().putBoolean("isPairDeviceLater", z).apply();
    }

    public void setIsQrTrayFtu(boolean z) {
        this.f7004a.edit().putBoolean("is_qr_tray_ftu", z).apply();
    }

    public void setIsQrTrayIntroSeen(boolean z) {
        this.f7004a.edit().putBoolean("is_qr_tray_intro_seen", z).apply();
    }

    public void setIsTapAndPayFtuShown(boolean z) {
        this.f7004a.edit().putBoolean("is_tap_and_pay_ftu_shown", z).apply();
    }

    public void setIsTroubleshootFtuShown(boolean z) {
        this.f7004a.edit().putBoolean("is_troubleshoot_ftu_shown", z).apply();
    }

    public void setLanguageSettings(Boolean bool) {
        this.b.putBoolean("is_language_page", bool.booleanValue());
        this.b.commit();
    }

    public void setLastBt3Status(boolean z) {
        this.f7004a.edit().putBoolean("last_bt3_status", z).apply();
    }

    public void setLastCalorieBurnedFitness(Float f) {
        this.f7004a.edit().putFloat("last_calorie_burned_fitness", f.floatValue()).apply();
    }

    public void setLastConnectedOxmDeviceMacAddress(String str) {
        this.b.putString("last_connected_oxm_ble_mac_address", str).commit();
    }

    public void setLastConnectedOxmDeviceName(String str) {
        this.b.putString("last_connected_oxm_ble_name", str).commit();
    }

    public void setLastDistanceCoveredFitness(Float f) {
        this.f7004a.edit().putFloat("last_distance_covered_fitness", f.floatValue()).apply();
    }

    public void setLegalDocType(String str) {
        this.b.putString("legal_doc_type", str).commit();
    }

    public void setLegalDocUrl(String str) {
        this.b.putString("legal_doc_url", str).commit();
    }

    public void setLegalDocVersion(String str) {
        this.b.putString("legal_doc_version", str).commit();
    }

    public void setNavigationFinishActivity(boolean z) {
        this.b.putBoolean("navigation_finish_activity", z);
        this.b.commit();
    }

    public void setNewUserFirstTime(boolean z) {
        this.b.putBoolean("KEY_NEW_USER_FIRST_TIME", z).commit();
    }

    public void setOnBoardingComplete(boolean z) {
        this.b.putBoolean("KEY_IS_ONBOARDING_COMPLETE", z);
        this.b.commit();
    }

    public void setPrivacyPolicyDocUrl(String str) {
        this.b.putString("privacy_policy_doc_url", str).commit();
    }

    public void setRateUsDialogShown(boolean z) {
        this.b.putBoolean("RATE_US_DIALOG_SHOWN", z);
        this.b.commit();
    }

    public void setRemoteMonitoringSupported(boolean z) {
        this.b.putBoolean("is_remotemonitoringsupported", z);
        this.b.commit();
    }

    public void setRoundedImage(Integer num) {
        this.b.putInt("rounded_image", num.intValue()).commit();
    }

    public void setRsaPrivateKey(String str) {
        this.b.putString("rsa_private_key", str);
        this.b.commit();
    }

    public void setSOSDisclaimerURL(String str) {
        this.b.putString("sos_disclaimer_url", str).commit();
    }

    public void setScanAllDevice(boolean z) {
        this.b.putBoolean("is_scan_all_devices", z);
        this.b.commit();
    }

    public void setScanOnDisconnectCriteria(ScanOnDisconnectCriteria scanOnDisconnectCriteria) {
        this.b.putString("scan_on_disconnect_criteria", new Gson().toJson(scanOnDisconnectCriteria));
        this.b.commit();
    }

    public void setSelectedDeviceTypePhoneOnly(boolean z) {
        this.b.putBoolean("key_is_selected_device_type_phone_only", z);
        this.b.commit();
    }

    public void setSelectedLanguage(String str) {
        this.b.putString("selected_language", str);
        this.b.commit();
    }

    public void setSelectedPosition(int i) {
        this.b.putInt("selected_position", i);
        this.b.commit();
    }

    public void setShipbookConfigUpdatedTimestamp(long j) {
        this.b.putLong("shipbook_config_updated_timestamp", j).commit();
    }

    public void setSmaIsMigrationRequired(boolean z) {
        this.f7004a.edit().putBoolean("sma_is_migration_required", z).apply();
    }

    public void setSportsDisclaimerDocUrl(String str) {
        this.b.putString("sports_disclaimer_doc_url", str).commit();
    }

    public void setTroubleshootUrl(String str) {
        this.b.putString("troubleshoot_url", str).commit();
    }

    public void setUserPlanBackground(String str) {
        this.f7004a.edit().putString("user_plan_bg", str).apply();
    }

    public void setUserPlanId(String str) {
        this.f7004a.edit().putString("user_plan_id", str).apply();
    }

    public void setWomenWellnessFTUNormalDay(boolean z) {
        this.b.putBoolean("key_women_wellness_ftu_normal_day", z).commit();
    }

    public void setWomenWellnessFTUShown(boolean z) {
        this.b.putBoolean("key_women_wellness_ftu", z).commit();
    }

    public void setWriteRemoteConfigAfterConnection(boolean z) {
        this.b.putBoolean("write_remote_config_after_connection", z);
        this.b.commit();
    }

    public void setWriteStepGoalAfterConnection(boolean z) {
        this.b.putBoolean("write_stepgoal_after_connection", z);
        this.b.commit();
    }

    public boolean shouldShowSOSTooltip() {
        return this.f7004a.getBoolean("should_show_sos_tooltip", false);
    }

    public boolean shouldWriteRemoteConfigAfterConnection() {
        return this.f7004a.getBoolean("write_remote_config_after_connection", false);
    }

    public boolean shouldWriteStepGoalAfterConnection() {
        return this.f7004a.getBoolean("write_stepgoal_after_connection", false);
    }

    public void showIndusInd(boolean z) {
        this.b.putBoolean("should_show_indus_ind", z);
        this.b.commit();
    }
}
