package com.coveiot.coveaccess.model.server;

import androidx.core.app.NotificationCompat;
import com.coveiot.coveaccess.userappsetting.BpCalibrationBean;
import com.coveiot.coveaccess.userappsetting.CalendarBean;
import com.google.android.gms.fitness.FitnessActivities;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.messaging.Constants;
import com.google.firebase.remoteconfig.RemoteConfigConstants;
import com.google.gson.annotations.SerializedName;
import com.jstyle.blesdk1860.constant.DeviceKey;
import com.szabh.smable3.entity.BleNotificationSettings;
import java.util.List;
/* loaded from: classes8.dex */
public class SUserAppSettingsReq {
    @SerializedName(NotificationCompat.CATEGORY_ALARM)
    private AlarmBean alarm;
    @SerializedName("autoHr")
    private AutoHr autoHr;
    @SerializedName("autoBodyTemp")
    private AutoTemperature autoTemperature;
    @SerializedName("calendar")
    private CalendarBean calender;
    @SerializedName("callQuickReplies")
    private CallQuickRepliesBean callQuickReplies;
    @SerializedName("contactTracing")
    private ContactTracingBean contactTracing;
    @SerializedName("deviceSpecificParams")
    private DeviceSpecificParamsBean deviceSpecificParams;
    @SerializedName("dnd")
    private DNDBean dnd;
    @SerializedName("drinkReminder")
    private DrinkReminderAlertBean drinkReminderAlert;
    @SerializedName("dualTime")
    private DualTimeBean dualTimeBean;
    @SerializedName("lap")
    private LapsBean lap;
    @SerializedName("liftWristToView")
    private LiftWristToViewBean liftWristToViewBean;
    @SerializedName("menstruation")
    private MensturationBean menstruation;
    @SerializedName("notification")
    private NotificationBean notification;
    @SerializedName("phoneFinder")
    private PhoneFinderBean phoneFinder;
    @SerializedName("sedentaryAlert")
    private SedentaryAlertBean sedentaryAlert;
    @SerializedName(FitnessActivities.SLEEP)
    private SleepBean sleep;
    @SerializedName("socialDistancing")
    private SocialDistancingBean socialDistancing;
    @SerializedName("weatherForecast")
    private WeatherForecastBean weatherForecast;

    /* loaded from: classes8.dex */
    public static class AlarmBean {
        @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
        private Boolean active;
        @SerializedName("list")
        private List<ListBean> list;
        @SerializedName("snooze")
        private Snooze snooze;

        /* loaded from: classes8.dex */
        public static class ListBean {
            @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
            private boolean active;
            @SerializedName(DeviceKey.KAlarmId)
            private String alarmId;
            @SerializedName("repeat")
            private boolean isRepeat;
            @SerializedName(Constants.ScionAnalytics.PARAM_LABEL)
            private String label;
            @SerializedName(NotificationCompat.MessagingStyle.Message.KEY_TIMESTAMP)
            private String time;
            @SerializedName("type")
            private String type;
            @SerializedName("weekDays")
            private String week;

            public String getAlarmId() {
                return this.alarmId;
            }

            public String getLabel() {
                return this.label;
            }

            public String getTime() {
                return this.time;
            }

            public String getType() {
                return this.type;
            }

            public String getWeek() {
                return this.week;
            }

            public boolean isActive() {
                return this.active;
            }

            public boolean isRepeat() {
                return this.isRepeat;
            }

            public void setActive(boolean z) {
                this.active = z;
            }

            public void setAlarmId(String str) {
                this.alarmId = str;
            }

            public void setLabel(String str) {
                this.label = str;
            }

            public void setRepeat(boolean z) {
                this.isRepeat = z;
            }

            public void setTime(String str) {
                this.time = str;
            }

            public void setType(String str) {
                this.type = str;
            }

            public void setWeek(String str) {
                this.week = str;
            }
        }

        public Boolean getActive() {
            return this.active;
        }

        public List<ListBean> getList() {
            return this.list;
        }

        public Snooze getSnooze() {
            return this.snooze;
        }

        public void setActive(Boolean bool) {
            this.active = bool;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public void setSnooze(Snooze snooze) {
            this.snooze = snooze;
        }
    }

    /* loaded from: classes8.dex */
    public static class AutoHr {
        @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
        private Boolean active;
        @SerializedName("interval")
        private String interval;

        public Boolean getActive() {
            return this.active;
        }

        public String getInterval() {
            return this.interval;
        }

        public void setActive(Boolean bool) {
            this.active = bool;
        }

        public void setInterval(String str) {
            this.interval = str;
        }
    }

    /* loaded from: classes8.dex */
    public static class AutoTemperature {
        @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
        private Boolean active;
        @SerializedName("interval")
        private String interval;

        public Boolean getActive() {
            return this.active;
        }

        public String getInterval() {
            return this.interval;
        }

        public void setActive(Boolean bool) {
            this.active = bool;
        }

        public void setInterval(String str) {
            this.interval = str;
        }
    }

    /* loaded from: classes8.dex */
    public static class ContactTracingBean {
        @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
        private Boolean active;

        public Boolean getActive() {
            return this.active;
        }

        public void setActive(Boolean bool) {
            this.active = bool;
        }
    }

    /* loaded from: classes8.dex */
    public static class DeviceSpecificParamsBean {
        @SerializedName("activeDisplays")
        private List<String> activeDisplays;
        @SerializedName("bpCalibration")
        private BpCalibrationBean bpCalibration;
        @SerializedName("clockFormat")
        private String clockFormat;
        @SerializedName("currentWatchFace")
        private CurrentWatchFaceBean currentWatchFace;
        @SerializedName("distanceUnit")
        private String distanceUnit;
        @SerializedName("liftWristToView")
        private boolean liftWristToView;
        @SerializedName("screenOrientation")
        private String screenOrientation;
        @SerializedName(DeviceKey.TempUnit)
        private String temperatureUnit;
        @SerializedName("wearingOn")
        private String wearingOn;

        public List<String> getActiveDisplays() {
            return this.activeDisplays;
        }

        public BpCalibrationBean getBpCalibration() {
            return this.bpCalibration;
        }

        public String getClockFormat() {
            return this.clockFormat;
        }

        public CurrentWatchFaceBean getCurrentWatchFace() {
            return this.currentWatchFace;
        }

        public String getDistanceUnit() {
            return this.distanceUnit;
        }

        public Boolean getLiftWristToView() {
            return Boolean.valueOf(this.liftWristToView);
        }

        public String getScreenOrientation() {
            return this.screenOrientation;
        }

        public String getTemperatureUnit() {
            return this.temperatureUnit;
        }

        public String getWearingOn() {
            return this.wearingOn;
        }

        public void setActiveDisplays(List<String> list) {
            this.activeDisplays = list;
        }

        public void setBpCalibration(BpCalibrationBean bpCalibrationBean) {
            this.bpCalibration = bpCalibrationBean;
        }

        public void setClockFormat(String str) {
            this.clockFormat = str;
        }

        public void setCurrentWatchFace(CurrentWatchFaceBean currentWatchFaceBean) {
            this.currentWatchFace = currentWatchFaceBean;
        }

        public void setDistanceUnit(String str) {
            this.distanceUnit = str;
        }

        public void setLiftWristToView(Boolean bool) {
            this.liftWristToView = bool.booleanValue();
        }

        public void setScreenOrientation(String str) {
            this.screenOrientation = str;
        }

        public void setTemperatureUnit(String str) {
            this.temperatureUnit = str;
        }

        public void setWearingOn(String str) {
            this.wearingOn = str;
        }
    }

    /* loaded from: classes8.dex */
    public static class DrinkReminderAlertBean {
        @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
        private boolean active;
        @SerializedName("endTime")
        private String endTime;
        @SerializedName("interval")
        private String interval;
        @SerializedName("startTime")
        private String startTime;

        public String getEndTime() {
            return this.endTime;
        }

        public String getInterval() {
            return this.interval;
        }

        public String getStartTime() {
            return this.startTime;
        }

        public boolean isActive() {
            return this.active;
        }

        public void setActive(boolean z) {
            this.active = z;
        }

        public void setEndTime(String str) {
            this.endTime = str;
        }

        public void setInterval(String str) {
            this.interval = str;
        }

        public void setStartTime(String str) {
            this.startTime = str;
        }
    }

    /* loaded from: classes8.dex */
    public static class LapsBean {
        @SerializedName("cycleDistance")
        private int cycleDistance;
        @SerializedName("runDistance")
        private int runDistance;
        @SerializedName("walkDistance")
        private int walkDistance;

        public int getCycleDistance() {
            return this.cycleDistance;
        }

        public int getRunDistance() {
            return this.runDistance;
        }

        public int getWalkDistance() {
            return this.walkDistance;
        }

        public void setCycleDistance(int i) {
            this.cycleDistance = i;
        }

        public void setRunDistance(int i) {
            this.runDistance = i;
        }

        public void setWalkDistance(int i) {
            this.walkDistance = i;
        }
    }

    /* loaded from: classes8.dex */
    public static class NotificationBean {
        @SerializedName(NotificationCompat.CATEGORY_CALL)
        private CallBean call;
        @SerializedName("dnd")
        private DndBean dnd;
        @SerializedName("otherApps")
        private AppBean otherApps;
        @SerializedName(BleNotificationSettings.SMS)
        private SmsBean sms;

        /* loaded from: classes8.dex */
        public static class AppBean {
            @SerializedName("androidApps")
            private List<AndroidApps> androidApps;
            @SerializedName("apps")
            private List<Apps> apps;
            @SerializedName("enableAll")
            private boolean enableAll;

            /* loaded from: classes8.dex */
            public static class AndroidApps {
                @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
                private Boolean active;
                @SerializedName("pkgId")
                private String packageX;

                public Boolean getActive() {
                    return this.active;
                }

                public String getPackageX() {
                    return this.packageX;
                }

                public void setActive(Boolean bool) {
                    this.active = bool;
                }

                public void setPackageX(String str) {
                    this.packageX = str;
                }
            }

            /* loaded from: classes8.dex */
            public static class Apps {
                @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
                private Boolean active;
                @SerializedName(RemoteConfigConstants.RequestFieldKey.APP_ID)
                private String appId;

                public Boolean getActive() {
                    return this.active;
                }

                public String getAppId() {
                    return this.appId;
                }

                public void setActive(Boolean bool) {
                    this.active = bool;
                }

                public void setAppId(String str) {
                    this.appId = str;
                }
            }

            public List<AndroidApps> getAndroidApps() {
                return this.androidApps;
            }

            public List<Apps> getApps() {
                return this.apps;
            }

            public boolean isEnableAll() {
                return this.enableAll;
            }

            public void setAndroidApps(List<AndroidApps> list) {
                this.androidApps = list;
            }

            public void setApps(List<Apps> list) {
                this.apps = list;
            }

            public void setEnableAll(boolean z) {
                this.enableAll = z;
            }
        }

        /* loaded from: classes8.dex */
        public static class CallBean {
            @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
            private boolean active;
            @SerializedName("favContacts")
            private List<FavContactsBean> favContacts;

            /* loaded from: classes8.dex */
            public static class FavContactsBean {
                @SerializedName("hourIndex")
                private int hourIndex;
                @SerializedName("mobileNumber")
                private String mobileNumber;
                @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
                private String name;

                public int getHourIndex() {
                    return this.hourIndex;
                }

                public String getMobileNumber() {
                    return this.mobileNumber;
                }

                public String getName() {
                    return this.name;
                }

                public void setHourIndex(int i) {
                    this.hourIndex = i;
                }

                public void setMobileNumber(String str) {
                    this.mobileNumber = str;
                }

                public void setName(String str) {
                    this.name = str;
                }
            }

            public List<FavContactsBean> getFavContacts() {
                return this.favContacts;
            }

            public boolean isActive() {
                return this.active;
            }

            public void setActive(boolean z) {
                this.active = z;
            }

            public void setFavContacts(List<FavContactsBean> list) {
                this.favContacts = list;
            }
        }

        /* loaded from: classes8.dex */
        public static class DndBean {
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
        public static class SmsBean {
            @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
            private boolean active;

            public boolean isActive() {
                return this.active;
            }

            public void setActive(boolean z) {
                this.active = z;
            }
        }

        public CallBean getCall() {
            return this.call;
        }

        public DndBean getDnd() {
            return this.dnd;
        }

        public AppBean getOtherApps() {
            return this.otherApps;
        }

        public SmsBean getSms() {
            return this.sms;
        }

        public void setCall(CallBean callBean) {
            this.call = callBean;
        }

        public void setDnd(DndBean dndBean) {
            this.dnd = dndBean;
        }

        public void setOtherApps(AppBean appBean) {
            this.otherApps = appBean;
        }

        public void setSms(SmsBean smsBean) {
            this.sms = smsBean;
        }
    }

    /* loaded from: classes8.dex */
    public static class PhoneFinderBean {
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
    public static class SedentaryAlertBean {
        @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
        private boolean active;
        @SerializedName("endTime")
        private String endTime;
        @SerializedName("interval")
        private String interval;
        @SerializedName("siesta")
        private SiestaBean siesta;
        @SerializedName("startTime")
        private String startTime;

        /* loaded from: classes8.dex */
        public static class SiestaBean {
            @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
            private boolean active;

            public boolean isActive() {
                return this.active;
            }

            public void setActive(boolean z) {
                this.active = z;
            }
        }

        public String getEndTime() {
            return this.endTime;
        }

        public String getInterval() {
            return this.interval;
        }

        public SiestaBean getSiesta() {
            return this.siesta;
        }

        public String getStartTime() {
            return this.startTime;
        }

        public boolean isActive() {
            return this.active;
        }

        public void setActive(boolean z) {
            this.active = z;
        }

        public void setEndTime(String str) {
            this.endTime = str;
        }

        public void setInterval(String str) {
            this.interval = str;
        }

        public void setSiesta(SiestaBean siestaBean) {
            this.siesta = siestaBean;
        }

        public void setStartTime(String str) {
            this.startTime = str;
        }
    }

    /* loaded from: classes8.dex */
    public static class SleepBean {
        @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
        private boolean active;
        @SerializedName("napTime")
        private NapTimeBean napTime;
        @SerializedName("sleepTime")
        private SleepTimeBean sleepTime;

        /* loaded from: classes8.dex */
        public static class NapTimeBean {
            @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
            private boolean active;
            @SerializedName("endTime")
            private String endTime;
            @SerializedName("notifyActive")
            private boolean notifyActive;
            @SerializedName("notifyTime")
            private String notifyTime;
            @SerializedName("startTime")
            private String startTime;

            public String getEndTime() {
                return this.endTime;
            }

            public String getNotifyTime() {
                return this.notifyTime;
            }

            public String getStartTime() {
                return this.startTime;
            }

            public boolean isActive() {
                return this.active;
            }

            public boolean isNotifyActive() {
                return this.notifyActive;
            }

            public void setActive(boolean z) {
                this.active = z;
            }

            public void setEndTime(String str) {
                this.endTime = str;
            }

            public void setNotifyActive(boolean z) {
                this.notifyActive = z;
            }

            public void setNotifyTime(String str) {
                this.notifyTime = str;
            }

            public void setStartTime(String str) {
                this.startTime = str;
            }
        }

        /* loaded from: classes8.dex */
        public static class SleepTimeBean {
            @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
            private boolean active;
            @SerializedName("endTime")
            private String endTime;
            @SerializedName("notifyActive")
            private boolean notifyActive;
            @SerializedName("notifyTime")
            private String notifyTime;
            @SerializedName("startTime")
            private String startTime;

            public String getEndTime() {
                return this.endTime;
            }

            public String getNotifyTime() {
                return this.notifyTime;
            }

            public String getStartTime() {
                return this.startTime;
            }

            public boolean isActive() {
                return this.active;
            }

            public boolean isNotifyActive() {
                return this.notifyActive;
            }

            public void setActive(boolean z) {
                this.active = z;
            }

            public void setEndTime(String str) {
                this.endTime = str;
            }

            public void setNotifyActive(boolean z) {
                this.notifyActive = z;
            }

            public void setNotifyTime(String str) {
                this.notifyTime = str;
            }

            public void setStartTime(String str) {
                this.startTime = str;
            }
        }

        public NapTimeBean getNapTime() {
            return this.napTime;
        }

        public SleepTimeBean getSleepTime() {
            return this.sleepTime;
        }

        public boolean isActive() {
            return this.active;
        }

        public void setActive(boolean z) {
            this.active = z;
        }

        public void setNapTime(NapTimeBean napTimeBean) {
            this.napTime = napTimeBean;
        }

        public void setSleepTime(SleepTimeBean sleepTimeBean) {
            this.sleepTime = sleepTimeBean;
        }
    }

    /* loaded from: classes8.dex */
    public static class SocialDistancingBean {
        @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
        private Boolean active;

        public Boolean getActive() {
            return this.active;
        }

        public void setActive(Boolean bool) {
            this.active = bool;
        }
    }

    public AlarmBean getAlarm() {
        return this.alarm;
    }

    public AutoHr getAutoHr() {
        return this.autoHr;
    }

    public AutoTemperature getAutoTemperature() {
        return this.autoTemperature;
    }

    public CalendarBean getCalender() {
        return this.calender;
    }

    public CallQuickRepliesBean getCallQuickReplies() {
        return this.callQuickReplies;
    }

    public ContactTracingBean getContactTracing() {
        return this.contactTracing;
    }

    public DeviceSpecificParamsBean getDeviceSpecificParams() {
        return this.deviceSpecificParams;
    }

    public DNDBean getDnd() {
        return this.dnd;
    }

    public DrinkReminderAlertBean getDrinkReminderAlert() {
        return this.drinkReminderAlert;
    }

    public DualTimeBean getDualTimeBean() {
        return this.dualTimeBean;
    }

    public LapsBean getLap() {
        return this.lap;
    }

    public LiftWristToViewBean getLiftWristToView() {
        return this.liftWristToViewBean;
    }

    public MensturationBean getMenstruation() {
        return this.menstruation;
    }

    public NotificationBean getNotification() {
        return this.notification;
    }

    public PhoneFinderBean getPhoneFinder() {
        return this.phoneFinder;
    }

    public SedentaryAlertBean getSedentaryAlert() {
        return this.sedentaryAlert;
    }

    public SleepBean getSleep() {
        return this.sleep;
    }

    public SocialDistancingBean getSocialDistancing() {
        return this.socialDistancing;
    }

    public WeatherForecastBean getWeatherForecast() {
        return this.weatherForecast;
    }

    public void setAlarm(AlarmBean alarmBean) {
        this.alarm = alarmBean;
    }

    public void setAutoHr(AutoHr autoHr) {
        this.autoHr = autoHr;
    }

    public void setAutoTemperature(AutoTemperature autoTemperature) {
        this.autoTemperature = autoTemperature;
    }

    public void setCalender(CalendarBean calendarBean) {
        this.calender = calendarBean;
    }

    public void setCallQuickReplies(CallQuickRepliesBean callQuickRepliesBean) {
        this.callQuickReplies = callQuickRepliesBean;
    }

    public void setContactTracing(ContactTracingBean contactTracingBean) {
        this.contactTracing = contactTracingBean;
    }

    public void setDeviceSpecificParams(DeviceSpecificParamsBean deviceSpecificParamsBean) {
        this.deviceSpecificParams = deviceSpecificParamsBean;
    }

    public void setDnd(DNDBean dNDBean) {
        this.dnd = dNDBean;
    }

    public void setDrinkReminderAlert(DrinkReminderAlertBean drinkReminderAlertBean) {
        this.drinkReminderAlert = drinkReminderAlertBean;
    }

    public void setDualTimeBean(DualTimeBean dualTimeBean) {
        this.dualTimeBean = dualTimeBean;
    }

    public void setLap(LapsBean lapsBean) {
        this.lap = lapsBean;
    }

    public void setLiftWristToViewBean(LiftWristToViewBean liftWristToViewBean) {
        this.liftWristToViewBean = liftWristToViewBean;
    }

    public void setMenstruation(MensturationBean mensturationBean) {
        this.menstruation = mensturationBean;
    }

    public void setNotification(NotificationBean notificationBean) {
        this.notification = notificationBean;
    }

    public void setPhoneFinder(PhoneFinderBean phoneFinderBean) {
        this.phoneFinder = phoneFinderBean;
    }

    public void setSedentaryAlert(SedentaryAlertBean sedentaryAlertBean) {
        this.sedentaryAlert = sedentaryAlertBean;
    }

    public void setSleep(SleepBean sleepBean) {
        this.sleep = sleepBean;
    }

    public void setSocialDistancing(SocialDistancingBean socialDistancingBean) {
        this.socialDistancing = socialDistancingBean;
    }

    public void setWeatherForecast(WeatherForecastBean weatherForecastBean) {
        this.weatherForecast = weatherForecastBean;
    }
}
