package com.coveiot.coveaccess.model.server;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.coveiot.coveaccess.userdevicesetting.model.AmbientSoundSettings;
import com.coveiot.coveaccess.userdevicesetting.model.AutoSpo2Settings;
import com.coveiot.coveaccess.userdevicesetting.model.Button4hActionItems;
import com.coveiot.coveaccess.userdevicesetting.model.ContactsSettings;
import com.coveiot.coveaccess.userdevicesetting.model.MapNavigationSettings;
import com.coveiot.coveaccess.userdevicesetting.model.RespiratoryRateSettings;
import com.coveiot.coveaccess.userdevicesetting.model.SOSSettings;
import com.coveiot.coveaccess.userdevicesetting.model.SedentaryAlertUserDeviceSettingsBean;
import com.coveiot.coveaccess.userdevicesetting.model.SmartAlertSettings;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.messaging.Constants;
import com.google.gson.annotations.SerializedName;
import com.jieli.watchtesttool.util.WatchTestConstant;
import java.util.List;
/* loaded from: classes8.dex */
public class SAllUserDeviceSettingRes {
    @SerializedName("data")
    private DataBean dataBean;
    @SerializedName(Constants.KEY_MESSAGE)
    private String message;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    private String status;

    /* loaded from: classes8.dex */
    public static class DataBean {
        @SerializedName("activitySession")
        private ActivitySession activitySession;
        @SerializedName("ambientSound")
        private AmbientSoundSettings ambientSoundSettings;
        @SerializedName("appDashboard")
        private AppDashboardDTO appDashboard;
        @SerializedName("autoRecognize")
        private AutoRecognize autoRecognize;
        @SerializedName("autoSpo2")
        private AutoSpo2Settings autoSPO2Settings;
        @SerializedName("autoStress")
        private AutoStress autoStress;
        @SerializedName("buttonC11n")
        private Button4hActionItems.ButtonC11n buttonC11n;
        @SerializedName(WatchTestConstant.DIR_CONTACTS)
        private List<ContactsSettings> contactsSettings;
        @SerializedName("messages")
        private CustomMessages customMessages;
        @SerializedName("reminders")
        private CustomReminders customReminders;
        @SerializedName("mapNavigation")
        private MapNavigationSettings mapNavigationSettings;
        @SerializedName("respiratoryRate")
        private RespiratoryRateSettings respiratoryRateSettings;
        @SerializedName("sedentaryAlert")
        private SedentaryAlertUserDeviceSettingsBean sedentaryAlertUserDeviceSettingsBean;
        @SerializedName("smartAlert")
        private SmartAlertSettings smartAlertSettings;
        @SerializedName("sos")
        private SOSSettings sosSettings;
        @SerializedName("userDeviceId")
        private String userDeviceId;

        /* loaded from: classes8.dex */
        public static class CustomMessages {
            @SerializedName("vibration")
            private NudgeMessageVibration nudgeMessageVibration;
            @SerializedName("nudges")
            private NudgeMessages nudgeMessages;

            /* loaded from: classes8.dex */
            public static class NudgeMessageVibration {
                @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
                private boolean active;

                public boolean isActive() {
                    return this.active;
                }

                public void setActive(boolean z) {
                    this.active = z;
                }
            }

            /* loaded from: classes8.dex */
            public static class NudgeMessages {
                @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
                private boolean active;

                public boolean isActive() {
                    return this.active;
                }

                public void setActive(boolean z) {
                    this.active = z;
                }
            }

            public NudgeMessageVibration getNudgeMessageVibration() {
                return this.nudgeMessageVibration;
            }

            public NudgeMessages getNudgeMessages() {
                return this.nudgeMessages;
            }

            public void setNudgeMessageVibration(NudgeMessageVibration nudgeMessageVibration) {
                this.nudgeMessageVibration = nudgeMessageVibration;
            }

            public void setNudgeMessages(NudgeMessages nudgeMessages) {
                this.nudgeMessages = nudgeMessages;
            }
        }

        /* loaded from: classes8.dex */
        public static class CustomReminders {
            @SerializedName("list")
            private List<CustomReminderListItem> customReminderListItems;
            @SerializedName("tzOffset")
            private String tzOffset;

            /* loaded from: classes8.dex */
            public static class CustomReminderListItem {
                @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
                private boolean active;
                @SerializedName("endDate")
                private String endDate;
                @SerializedName("endTime")
                private String endTime;
                @SerializedName("frequency")
                private String frequency;
                @SerializedName("interval")
                private String interval;
                @SerializedName(Constants.ScionAnalytics.PARAM_LABEL)
                private String label;
                @SerializedName("remindAt")
                private List<String> remindAt;
                @SerializedName("remindBefore")
                private int remindBefore;
                @SerializedName("reminderId")
                private String reminderId;
                @SerializedName("startDate")
                private String startDate;
                @SerializedName("startTime")
                private String startTime;
                @SerializedName("type")
                private String type;
                @SerializedName("weekDays")
                private String weekDays;

                public String getEndDate() {
                    return this.endDate;
                }

                public String getEndTime() {
                    return this.endTime;
                }

                public String getFrequency() {
                    return this.frequency;
                }

                public String getInterval() {
                    return this.interval;
                }

                public String getLabel() {
                    return this.label;
                }

                public List<String> getRemindAt() {
                    return this.remindAt;
                }

                public int getRemindBefore() {
                    return this.remindBefore;
                }

                public String getReminderId() {
                    return this.reminderId;
                }

                public String getStartDate() {
                    return this.startDate;
                }

                public String getStartTime() {
                    return this.startTime;
                }

                public String getType() {
                    return this.type;
                }

                public String getWeekDays() {
                    return this.weekDays;
                }

                public boolean isActive() {
                    return this.active;
                }

                public void setActive(boolean z) {
                    this.active = z;
                }

                public void setEndDate(String str) {
                    this.endDate = str;
                }

                public void setEndTime(String str) {
                    this.endTime = str;
                }

                public CustomReminderListItem setFrequency(String str) {
                    this.frequency = str;
                    return this;
                }

                public void setInterval(String str) {
                    this.interval = str;
                }

                public void setLabel(String str) {
                    this.label = str;
                }

                public void setRemindAt(List<String> list) {
                    this.remindAt = list;
                }

                public void setRemindBefore(int i) {
                    this.remindBefore = i;
                }

                public void setReminderId(String str) {
                    this.reminderId = str;
                }

                public void setStartDate(String str) {
                    this.startDate = str;
                }

                public void setStartTime(String str) {
                    this.startTime = str;
                }

                public void setType(String str) {
                    this.type = str;
                }

                public void setWeekDays(String str) {
                    this.weekDays = str;
                }
            }

            public List<CustomReminderListItem> getCustomReminderListItems() {
                return this.customReminderListItems;
            }

            public String getTzOffset() {
                return this.tzOffset;
            }

            public void setCustomReminderListItems(List<CustomReminderListItem> list) {
                this.customReminderListItems = list;
            }

            public void setTzOffset(String str) {
                this.tzOffset = str;
            }
        }

        public ActivitySession getActivitySession() {
            return this.activitySession;
        }

        public AmbientSoundSettings getAmbientSoundSettings() {
            return this.ambientSoundSettings;
        }

        public AppDashboardDTO getAppDashboard() {
            return this.appDashboard;
        }

        public AutoRecognize getAutoRecognize() {
            return this.autoRecognize;
        }

        public AutoSpo2Settings getAutoSPO2Settings() {
            return this.autoSPO2Settings;
        }

        public AutoStress getAutoStress() {
            return this.autoStress;
        }

        public Button4hActionItems.ButtonC11n getButtonC11n() {
            return this.buttonC11n;
        }

        public List<ContactsSettings> getContactsSettings() {
            return this.contactsSettings;
        }

        public CustomMessages getCustomMessages() {
            return this.customMessages;
        }

        public CustomReminders getCustomReminders() {
            return this.customReminders;
        }

        public MapNavigationSettings getMapNavigationSettings() {
            return this.mapNavigationSettings;
        }

        public RespiratoryRateSettings getRespiratoryRateSettings() {
            return this.respiratoryRateSettings;
        }

        public SedentaryAlertUserDeviceSettingsBean getSedentaryAlertUserDeviceSettingsBean() {
            return this.sedentaryAlertUserDeviceSettingsBean;
        }

        public SmartAlertSettings getSmartAlertSettings() {
            return this.smartAlertSettings;
        }

        public SOSSettings getSosSettings() {
            return this.sosSettings;
        }

        public String getUserDeviceId() {
            return this.userDeviceId;
        }

        public void setActivitySession(ActivitySession activitySession) {
            this.activitySession = activitySession;
        }

        public void setAmbientSoundSettings(AmbientSoundSettings ambientSoundSettings) {
            this.ambientSoundSettings = ambientSoundSettings;
        }

        public void setAppDashboard(AppDashboardDTO appDashboardDTO) {
            this.appDashboard = appDashboardDTO;
        }

        public void setAutoRecognize(AutoRecognize autoRecognize) {
            this.autoRecognize = autoRecognize;
        }

        public void setAutoSPO2Settings(AutoSpo2Settings autoSpo2Settings) {
            this.autoSPO2Settings = autoSpo2Settings;
        }

        public void setAutoStress(AutoStress autoStress) {
            this.autoStress = autoStress;
        }

        public void setButtonC11n(Button4hActionItems.ButtonC11n buttonC11n) {
            this.buttonC11n = buttonC11n;
        }

        public void setContactsSettings(List<ContactsSettings> list) {
            this.contactsSettings = list;
        }

        public void setCustomMessages(CustomMessages customMessages) {
            this.customMessages = customMessages;
        }

        public void setCustomReminders(CustomReminders customReminders) {
            this.customReminders = customReminders;
        }

        public void setMapNavigationSettings(MapNavigationSettings mapNavigationSettings) {
            this.mapNavigationSettings = mapNavigationSettings;
        }

        public void setRespiratoryRateSettings(RespiratoryRateSettings respiratoryRateSettings) {
            this.respiratoryRateSettings = respiratoryRateSettings;
        }

        public void setSedentaryAlertUserDeviceSettingsBean(SedentaryAlertUserDeviceSettingsBean sedentaryAlertUserDeviceSettingsBean) {
            this.sedentaryAlertUserDeviceSettingsBean = sedentaryAlertUserDeviceSettingsBean;
        }

        public void setSmartAlertSettings(SmartAlertSettings smartAlertSettings) {
            this.smartAlertSettings = smartAlertSettings;
        }

        public void setSosSettings(SOSSettings sOSSettings) {
            this.sosSettings = sOSSettings;
        }

        public void setUserDeviceId(String str) {
            this.userDeviceId = str;
        }
    }

    public DataBean getDataBean() {
        return this.dataBean;
    }

    public String getMessage() {
        return this.message;
    }

    public String getStatus() {
        return this.status;
    }

    public void setDataBean(DataBean dataBean) {
        this.dataBean = dataBean;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}
