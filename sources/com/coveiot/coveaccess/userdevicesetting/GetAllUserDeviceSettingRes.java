package com.coveiot.coveaccess.userdevicesetting;

import androidx.core.app.NotificationCompat;
import com.clevertap.android.sdk.Constants;
import com.coveiot.coveaccess.model.server.ActivitySession;
import com.coveiot.coveaccess.model.server.AppDashboardDTO;
import com.coveiot.coveaccess.model.server.AutoRecognize;
import com.coveiot.coveaccess.model.server.AutoStress;
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
public class GetAllUserDeviceSettingRes {
    @SerializedName("data")

    /* renamed from: a  reason: collision with root package name */
    private DataBean f6864a;
    @SerializedName(Constants.KEY_MESSAGE)
    private String b;
    @SerializedName(NotificationCompat.CATEGORY_STATUS)
    private String c;

    /* loaded from: classes8.dex */
    public static class DataBean {
        @SerializedName("userDeviceId")

        /* renamed from: a  reason: collision with root package name */
        private String f6865a;
        @SerializedName("reminders")
        private CustomReminders b;
        @SerializedName("messages")
        private CustomMessages c;
        @SerializedName("autoStress")
        private AutoStress d;
        @SerializedName("autoRecognize")
        private AutoRecognize e;
        @SerializedName("activitySession")
        private ActivitySession f;
        @SerializedName("sedentaryAlert")
        private SedentaryAlertUserDeviceSettingsBean g;
        @SerializedName("ambientSound")
        private AmbientSoundSettings h;
        @SerializedName("respiratoryRate")
        private RespiratoryRateSettings i;
        @SerializedName("autoSpo2")
        private AutoSpo2Settings j;
        @SerializedName("appDashboard")
        private AppDashboardDTO k;
        @SerializedName("buttonC11n")
        private Button4hActionItems l;
        @SerializedName("mapNavigation")
        private MapNavigationSettings m;
        @SerializedName("smartAlert")
        private SmartAlertSettings n;
        @SerializedName("sos")
        private SOSSettings o;
        @SerializedName(WatchTestConstant.DIR_CONTACTS)
        private List<ContactsSettings> p;

        /* loaded from: classes8.dex */
        public static class CustomMessages {
            @SerializedName("nudges")

            /* renamed from: a  reason: collision with root package name */
            private NudgeMessages f6866a;
            @SerializedName("vibration")
            private NudgeMessageVibration b;

            /* loaded from: classes8.dex */
            public static class NudgeMessageVibration {
                @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)

                /* renamed from: a  reason: collision with root package name */
                private boolean f6867a;

                public boolean isActive() {
                    return this.f6867a;
                }

                public void setActive(boolean z) {
                    this.f6867a = z;
                }
            }

            /* loaded from: classes8.dex */
            public static class NudgeMessages {
                @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)

                /* renamed from: a  reason: collision with root package name */
                private boolean f6868a;

                public boolean isActive() {
                    return this.f6868a;
                }

                public void setActive(boolean z) {
                    this.f6868a = z;
                }
            }

            public NudgeMessageVibration getNudgeMessageVibration() {
                return this.b;
            }

            public NudgeMessages getNudgeMessages() {
                return this.f6866a;
            }

            public void setNudgeMessageVibration(NudgeMessageVibration nudgeMessageVibration) {
                this.b = nudgeMessageVibration;
            }

            public void setNudgeMessages(NudgeMessages nudgeMessages) {
                this.f6866a = nudgeMessages;
            }
        }

        /* loaded from: classes8.dex */
        public static class CustomReminders {
            @SerializedName("tzOffset")

            /* renamed from: a  reason: collision with root package name */
            private String f6869a;
            @SerializedName("list")
            private List<CustomReminderListItem> b;

            /* loaded from: classes8.dex */
            public static class CustomReminderListItem {
                @SerializedName("reminderId")

                /* renamed from: a  reason: collision with root package name */
                private String f6870a;
                @SerializedName("endDate")
                private String b;
                @SerializedName("weekDays")
                private String c;
                @SerializedName("remindBefore")
                private int d;
                @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
                private boolean e;
                @SerializedName("startTime")
                private String f;
                @SerializedName("interval")
                private String g;
                @SerializedName(Constants.ScionAnalytics.PARAM_LABEL)
                private String h;
                @SerializedName("endTime")
                private String i;
                @SerializedName("type")
                private String j;
                @SerializedName("startDate")
                private String k;
                @SerializedName("remindAt")
                private List<String> l;
                @SerializedName("frequency")
                private String m;

                public String getEndDate() {
                    return this.b;
                }

                public String getEndTime() {
                    return this.i;
                }

                public String getFrequency() {
                    return this.m;
                }

                public String getInterval() {
                    return this.g;
                }

                public String getLabel() {
                    return this.h;
                }

                public List<String> getRemindAt() {
                    return this.l;
                }

                public int getRemindBefore() {
                    return this.d;
                }

                public String getReminderId() {
                    return this.f6870a;
                }

                public String getStartDate() {
                    return this.k;
                }

                public String getStartTime() {
                    return this.f;
                }

                public String getType() {
                    return this.j;
                }

                public String getWeekDays() {
                    return this.c;
                }

                public boolean isActive() {
                    return this.e;
                }

                public void setActive(boolean z) {
                    this.e = z;
                }

                public void setEndDate(String str) {
                    this.b = str;
                }

                public void setEndTime(String str) {
                    this.i = str;
                }

                public CustomReminderListItem setFrequency(String str) {
                    this.m = str;
                    return this;
                }

                public void setInterval(String str) {
                    this.g = str;
                }

                public void setLabel(String str) {
                    this.h = str;
                }

                public void setRemindAt(List<String> list) {
                    this.l = list;
                }

                public void setRemindBefore(int i) {
                    this.d = i;
                }

                public void setReminderId(String str) {
                    this.f6870a = str;
                }

                public void setStartDate(String str) {
                    this.k = str;
                }

                public void setStartTime(String str) {
                    this.f = str;
                }

                public void setType(String str) {
                    this.j = str;
                }

                public void setWeekDays(String str) {
                    this.c = str;
                }
            }

            public List<CustomReminderListItem> getCustomReminderListItems() {
                return this.b;
            }

            public String getTzOffset() {
                return this.f6869a;
            }

            public void setCustomReminderListItems(List<CustomReminderListItem> list) {
                this.b = list;
            }

            public void setTzOffset(String str) {
                this.f6869a = str;
            }
        }

        public ActivitySession getActivitySession() {
            return this.f;
        }

        public AmbientSoundSettings getAmbientSoundSettings() {
            return this.h;
        }

        public AppDashboardDTO getAppDashboard() {
            return this.k;
        }

        public AutoRecognize getAutoRecognize() {
            return this.e;
        }

        public AutoSpo2Settings getAutoSPO2Settings() {
            return this.j;
        }

        public AutoStress getAutoStress() {
            return this.d;
        }

        public Button4hActionItems getButtonC11n() {
            return this.l;
        }

        public List<ContactsSettings> getContactsSettings() {
            return this.p;
        }

        public CustomMessages getCustomMessages() {
            return this.c;
        }

        public CustomReminders getCustomReminders() {
            return this.b;
        }

        public MapNavigationSettings getMapNavigationSettings() {
            return this.m;
        }

        public RespiratoryRateSettings getRespiratoryRateSettings() {
            return this.i;
        }

        public SedentaryAlertUserDeviceSettingsBean getSedentaryAlertUserDeviceSettingsBean() {
            return this.g;
        }

        public SmartAlertSettings getSmartAlertSettings() {
            return this.n;
        }

        public SOSSettings getSosSettings() {
            return this.o;
        }

        public String getUserDeviceId() {
            return this.f6865a;
        }

        public void setActivitySession(ActivitySession activitySession) {
            this.f = activitySession;
        }

        public void setAmbientSoundSettings(AmbientSoundSettings ambientSoundSettings) {
            this.h = ambientSoundSettings;
        }

        public void setAppDashboard(AppDashboardDTO appDashboardDTO) {
            this.k = appDashboardDTO;
        }

        public void setAutoRecognize(AutoRecognize autoRecognize) {
            this.e = autoRecognize;
        }

        public void setAutoSPO2Settings(AutoSpo2Settings autoSpo2Settings) {
            this.j = autoSpo2Settings;
        }

        public void setAutoStress(AutoStress autoStress) {
            this.d = autoStress;
        }

        public void setButtonC11n(Button4hActionItems button4hActionItems) {
            this.l = button4hActionItems;
        }

        public void setContactsSettings(List<ContactsSettings> list) {
            this.p = list;
        }

        public void setCustomMessages(CustomMessages customMessages) {
            this.c = customMessages;
        }

        public void setCustomReminders(CustomReminders customReminders) {
            this.b = customReminders;
        }

        public void setMapNavigationSettings(MapNavigationSettings mapNavigationSettings) {
            this.m = mapNavigationSettings;
        }

        public void setRespiratoryRateSettings(RespiratoryRateSettings respiratoryRateSettings) {
            this.i = respiratoryRateSettings;
        }

        public void setSedentaryAlertUserDeviceSettingsBean(SedentaryAlertUserDeviceSettingsBean sedentaryAlertUserDeviceSettingsBean) {
            this.g = sedentaryAlertUserDeviceSettingsBean;
        }

        public void setSmartAlertSettings(SmartAlertSettings smartAlertSettings) {
            this.n = smartAlertSettings;
        }

        public void setSosSettings(SOSSettings sOSSettings) {
            this.o = sOSSettings;
        }

        public void setUserDeviceId(String str) {
            this.f6865a = str;
        }
    }

    public DataBean getDataBean() {
        return this.f6864a;
    }

    public String getMessage() {
        return this.b;
    }

    public String getStatus() {
        return this.c;
    }

    public void setDataBean(DataBean dataBean) {
        this.f6864a = dataBean;
    }

    public void setMessage(String str) {
        this.b = str;
    }

    public void setStatus(String str) {
        this.c = str;
    }

    public String toString() {
        return "GetAllUserDeviceSettingRes{dataBean=" + this.f6864a + ", message='" + this.b + "', status='" + this.c + "'}";
    }
}
