package com.coveiot.coveaccess.userappsetting;

import com.coveiot.coveaccess.model.server.CallQuickRepliesBean;
import com.coveiot.coveaccess.model.server.CurrentWatchFaceBean;
import com.coveiot.coveaccess.model.server.DNDBean;
import com.coveiot.coveaccess.model.server.DualTimeBean;
import com.coveiot.coveaccess.model.server.LiftWristToViewBean;
import com.coveiot.coveaccess.model.server.MensturationBean;
import com.coveiot.coveaccess.model.server.Snooze;
import com.coveiot.coveaccess.model.server.WeatherForecastBean;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.util.List;
/* loaded from: classes8.dex */
public class GetAllUserAppSettingsRes {

    /* renamed from: a  reason: collision with root package name */
    public AlarmBean f6811a;
    public DNDBean b;
    public SedentaryAlertBean c;
    public SleepBean d;
    public PhoneFinderBean e;
    public NotificationBean f;
    @SerializedName("menstruation")
    private MensturationBean g;
    @SerializedName("calendar")
    private CalendarBean h;
    @SerializedName("deviceSpecificParams")
    private DeviceSpecificParamsBean i;
    @SerializedName("autoHr")
    private AutoHr j;
    @SerializedName("autoBodyTemp")
    private AutoTemperature k;
    @SerializedName("socialDistancing")
    private SocialDistancingBean l;
    @SerializedName("contactTracing")
    private ContactTracingBean m;
    @SerializedName("liftWristToView")
    private LiftWristToViewBean n;
    @SerializedName("drinkReminder")
    private DrinkReminderAlertBean o;
    @SerializedName("weatherForecast")
    private WeatherForecastBean p;
    @SerializedName("callQuickReplies")
    private CallQuickRepliesBean q;
    @SerializedName("dualTime")
    private DualTimeBean r;

    /* loaded from: classes8.dex */
    public static class AlarmBean {
        @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)

        /* renamed from: a  reason: collision with root package name */
        private Boolean f6812a;
        @SerializedName("snooze")
        private Snooze b;
        public List<ListBean> c;

        /* loaded from: classes8.dex */
        public static class ListBean {

            /* renamed from: a  reason: collision with root package name */
            public int f6813a;
            public String b;
            public String c;
            public boolean d;
            public boolean e;
            public String f;
            public String g;

            public int getAlarmId() {
                return this.f6813a;
            }

            public String getLabel() {
                return this.b;
            }

            public String getTime() {
                return this.c;
            }

            public String getType() {
                return this.g;
            }

            public String getWeek() {
                return this.f;
            }

            public boolean isActive() {
                return this.d;
            }

            public boolean isRepeat() {
                return this.e;
            }

            public void setActive(boolean z) {
                this.d = z;
            }

            public void setAlarmId(int i) {
                this.f6813a = i;
            }

            public void setLabel(String str) {
                this.b = str;
            }

            public void setRepeat(boolean z) {
                this.e = z;
            }

            public void setTime(String str) {
                this.c = str;
            }

            public void setType(String str) {
                this.g = str;
            }

            public void setWeek(String str) {
                this.f = str;
            }
        }

        public Boolean getActive() {
            return this.f6812a;
        }

        public List<ListBean> getList() {
            return this.c;
        }

        public Snooze getSnooze() {
            return this.b;
        }

        public void setActive(Boolean bool) {
            this.f6812a = bool;
        }

        public void setList(List<ListBean> list) {
            this.c = list;
        }

        public void setSnooze(Snooze snooze) {
            this.b = snooze;
        }
    }

    /* loaded from: classes8.dex */
    public static class AutoHr {
        @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)

        /* renamed from: a  reason: collision with root package name */
        private Boolean f6814a;
        @SerializedName("interval")
        private String b;

        public Boolean getActive() {
            return this.f6814a;
        }

        public String getInterval() {
            return this.b;
        }

        public void setActive(Boolean bool) {
            this.f6814a = bool;
        }

        public void setInterval(String str) {
            this.b = str;
        }
    }

    /* loaded from: classes8.dex */
    public static class AutoTemperature {
        @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)

        /* renamed from: a  reason: collision with root package name */
        private Boolean f6815a;
        @SerializedName("interval")
        private String b;

        public Boolean getActive() {
            return this.f6815a;
        }

        public String getInterval() {
            return this.b;
        }

        public void setActive(Boolean bool) {
            this.f6815a = bool;
        }

        public void setInterval(String str) {
            this.b = str;
        }
    }

    /* loaded from: classes8.dex */
    public static class ContactTracingBean {
        @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)

        /* renamed from: a  reason: collision with root package name */
        private Boolean f6816a;

        public Boolean getActive() {
            return this.f6816a;
        }

        public void setActive(Boolean bool) {
            this.f6816a = bool;
        }
    }

    /* loaded from: classes8.dex */
    public static class DeviceSpecificParamsBean {
        @SerializedName("wearingOn")

        /* renamed from: a  reason: collision with root package name */
        private String f6817a;
        @SerializedName("screenOrientation")
        private String b;
        @SerializedName("activeDisplays")
        private List<String> c;
        @SerializedName("distanceUnit")
        private String d;
        @SerializedName("clockFormat")
        private String e;
        @SerializedName("liftWristToView")
        private boolean f;
        @SerializedName(DeviceKey.TempUnit)
        private String g;
        @SerializedName("bpCalibration")
        private BpCalibrationBean h;
        @SerializedName("currentWatchFace")
        private CurrentWatchFaceBean i;

        public List<String> getActiveDisplays() {
            return this.c;
        }

        public BpCalibrationBean getBpCalibration() {
            return this.h;
        }

        public String getClockFormat() {
            return this.e;
        }

        public CurrentWatchFaceBean getCurrentWatchFace() {
            return this.i;
        }

        public String getDistanceUnit() {
            return this.d;
        }

        public Boolean getLiftWristToView() {
            return Boolean.valueOf(this.f);
        }

        public String getScreenOrientation() {
            return this.b;
        }

        public String getTemperatureUnit() {
            return this.g;
        }

        public String getWearingOn() {
            return this.f6817a;
        }

        public void setActiveDisplays(List<String> list) {
            this.c = list;
        }

        public void setBpCalibration(BpCalibrationBean bpCalibrationBean) {
            this.h = bpCalibrationBean;
        }

        public void setClockFormat(String str) {
            this.e = str;
        }

        public void setCurrentWatchFace(CurrentWatchFaceBean currentWatchFaceBean) {
            this.i = currentWatchFaceBean;
        }

        public void setDistanceUnit(String str) {
            this.d = str;
        }

        public void setLiftWristToView(Boolean bool) {
            this.f = bool.booleanValue();
        }

        public void setScreenOrientation(String str) {
            this.b = str;
        }

        public void setTemperatureUnit(String str) {
            this.g = str;
        }

        public void setWearingOn(String str) {
            this.f6817a = str;
        }
    }

    /* loaded from: classes8.dex */
    public static class DrinkReminderAlertBean {

        /* renamed from: a  reason: collision with root package name */
        public boolean f6818a;
        public String b;
        public String c;
        public String d;

        public String getEndTime() {
            return this.c;
        }

        public String getInterval() {
            return this.d;
        }

        public String getStartTime() {
            return this.b;
        }

        public boolean isActive() {
            return this.f6818a;
        }

        public void setActive(boolean z) {
            this.f6818a = z;
        }

        public void setEndTime(String str) {
            this.c = str;
        }

        public void setInterval(String str) {
            this.d = str;
        }

        public void setStartTime(String str) {
            this.b = str;
        }
    }

    /* loaded from: classes8.dex */
    public static class NotificationBean {

        /* renamed from: a  reason: collision with root package name */
        public DndBean f6819a;
        public SmsBean b;
        public CallBean c;
        public AppBean d;

        /* loaded from: classes8.dex */
        public static class AppBean {

            /* renamed from: a  reason: collision with root package name */
            public boolean f6820a;
            @SerializedName("androidApps")
            private List<AndroidApps> b;
            public List<Apps> c;

            /* loaded from: classes8.dex */
            public static class AndroidApps {
                @SerializedName("pkgId")

                /* renamed from: a  reason: collision with root package name */
                private String f6821a;
                @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)
                private Boolean b;

                public Boolean getActive() {
                    return this.b;
                }

                public String getPackageX() {
                    return this.f6821a;
                }

                public void setActive(Boolean bool) {
                    this.b = bool;
                }

                public void setPackageX(String str) {
                    this.f6821a = str;
                }
            }

            /* loaded from: classes8.dex */
            public static class Apps {

                /* renamed from: a  reason: collision with root package name */
                public String f6822a;
                public Boolean b;

                public Boolean getActive() {
                    return this.b;
                }

                public String getAppId() {
                    return this.f6822a;
                }

                public void setActive(Boolean bool) {
                    this.b = bool;
                }

                public void setAppId(String str) {
                    this.f6822a = str;
                }
            }

            public List<AndroidApps> getAndroidApps() {
                return this.b;
            }

            public List<Apps> getApps() {
                return this.c;
            }

            public boolean isEnableAll() {
                return this.f6820a;
            }

            public void setAndroidApps(List<AndroidApps> list) {
                this.b = list;
            }

            public void setApps(List<Apps> list) {
                this.c = list;
            }

            public void setEnableAll(boolean z) {
                this.f6820a = z;
            }
        }

        /* loaded from: classes8.dex */
        public static class CallBean {

            /* renamed from: a  reason: collision with root package name */
            public boolean f6823a;
            public List<FavContactsBean> b;

            /* loaded from: classes8.dex */
            public static class FavContactsBean {

                /* renamed from: a  reason: collision with root package name */
                public String f6824a;
                public String b;
                public int c;

                public int getHourIndex() {
                    return this.c;
                }

                public String getMobileNumber() {
                    return this.f6824a;
                }

                public String getName() {
                    return this.b;
                }

                public void setHourIndex(int i) {
                    this.c = i;
                }

                public void setMobileNumber(String str) {
                    this.f6824a = str;
                }

                public void setName(String str) {
                    this.b = str;
                }
            }

            public List<FavContactsBean> getFavContacts() {
                return this.b;
            }

            public boolean isActive() {
                return this.f6823a;
            }

            public void setActive(boolean z) {
                this.f6823a = z;
            }

            public void setFavContacts(List<FavContactsBean> list) {
                this.b = list;
            }
        }

        /* loaded from: classes8.dex */
        public static class DndBean {

            /* renamed from: a  reason: collision with root package name */
            public boolean f6825a;

            public boolean isActive() {
                return this.f6825a;
            }

            public void setActive(boolean z) {
                this.f6825a = z;
            }
        }

        /* loaded from: classes8.dex */
        public static class SmsBean {

            /* renamed from: a  reason: collision with root package name */
            public boolean f6826a;

            public boolean isActive() {
                return this.f6826a;
            }

            public void setActive(boolean z) {
                this.f6826a = z;
            }
        }

        public CallBean getCall() {
            return this.c;
        }

        public DndBean getDnd() {
            return this.f6819a;
        }

        public AppBean getOtherApps() {
            return this.d;
        }

        public SmsBean getSms() {
            return this.b;
        }

        public void setCall(CallBean callBean) {
            this.c = callBean;
        }

        public void setDnd(DndBean dndBean) {
            this.f6819a = dndBean;
        }

        public void setOtherApps(AppBean appBean) {
            this.d = appBean;
        }

        public void setSms(SmsBean smsBean) {
            this.b = smsBean;
        }
    }

    /* loaded from: classes8.dex */
    public static class PhoneFinderBean {

        /* renamed from: a  reason: collision with root package name */
        public boolean f6827a;

        public boolean isActive() {
            return this.f6827a;
        }

        public void setActive(boolean z) {
            this.f6827a = z;
        }
    }

    /* loaded from: classes8.dex */
    public static class SedentaryAlertBean {

        /* renamed from: a  reason: collision with root package name */
        public boolean f6828a;
        public String b;
        public String c;
        public String d;

        public String getEndTime() {
            return this.c;
        }

        public String getInterval() {
            return this.d;
        }

        public String getStartTime() {
            return this.b;
        }

        public boolean isActive() {
            return this.f6828a;
        }

        public void setActive(boolean z) {
            this.f6828a = z;
        }

        public void setEndTime(String str) {
            this.c = str;
        }

        public void setInterval(String str) {
            this.d = str;
        }

        public void setStartTime(String str) {
            this.b = str;
        }
    }

    /* loaded from: classes8.dex */
    public static class SleepBean {

        /* renamed from: a  reason: collision with root package name */
        public boolean f6829a;
        public SleepTimeBean b;
        public NapTimeBean c;

        /* loaded from: classes8.dex */
        public static class NapTimeBean {

            /* renamed from: a  reason: collision with root package name */
            public boolean f6830a;
            public String b;
            public String c;
            public String d;
            public boolean e;

            public String getEndTime() {
                return this.c;
            }

            public String getNotifyTime() {
                return this.d;
            }

            public String getStartTime() {
                return this.b;
            }

            public boolean isActive() {
                return this.f6830a;
            }

            public boolean isNotifyActive() {
                return this.e;
            }

            public void setActive(boolean z) {
                this.f6830a = z;
            }

            public void setEndTime(String str) {
                this.c = str;
            }

            public void setNotifyActive(boolean z) {
                this.e = z;
            }

            public void setNotifyTime(String str) {
                this.d = str;
            }

            public void setStartTime(String str) {
                this.b = str;
            }
        }

        /* loaded from: classes8.dex */
        public static class SleepTimeBean {

            /* renamed from: a  reason: collision with root package name */
            public boolean f6831a;
            public String b;
            public String c;
            public String d;
            public boolean e;

            public String getEndTime() {
                return this.c;
            }

            public String getNotifyTime() {
                return this.d;
            }

            public String getStartTime() {
                return this.b;
            }

            public boolean isActive() {
                return this.f6831a;
            }

            public boolean isNotifyActive() {
                return this.e;
            }

            public void setActive(boolean z) {
                this.f6831a = z;
            }

            public void setEndTime(String str) {
                this.c = str;
            }

            public void setNotifyActive(boolean z) {
                this.e = z;
            }

            public void setNotifyTime(String str) {
                this.d = str;
            }

            public void setStartTime(String str) {
                this.b = str;
            }
        }

        public NapTimeBean getNapTime() {
            return this.c;
        }

        public SleepTimeBean getSleepTime() {
            return this.b;
        }

        public boolean isActive() {
            return this.f6829a;
        }

        public void setActive(boolean z) {
            this.f6829a = z;
        }

        public void setNapTime(NapTimeBean napTimeBean) {
            this.c = napTimeBean;
        }

        public void setSleepTime(SleepTimeBean sleepTimeBean) {
            this.b = sleepTimeBean;
        }
    }

    /* loaded from: classes8.dex */
    public static class SocialDistancingBean {
        @SerializedName(AppMeasurementSdk.ConditionalUserProperty.ACTIVE)

        /* renamed from: a  reason: collision with root package name */
        private Boolean f6832a;

        public Boolean getActive() {
            return this.f6832a;
        }

        public void setActive(Boolean bool) {
            this.f6832a = bool;
        }
    }

    public AlarmBean getAlarm() {
        return this.f6811a;
    }

    public AutoHr getAutoHr() {
        return this.j;
    }

    public AutoTemperature getAutoTemperature() {
        return this.k;
    }

    public CalendarBean getCalendarBean() {
        return this.h;
    }

    public CallQuickRepliesBean getCallQuickReplies() {
        return this.q;
    }

    public ContactTracingBean getContactTracing() {
        return this.m;
    }

    public DeviceSpecificParamsBean getDeviceSpecificParams() {
        return this.i;
    }

    public DNDBean getDndBean() {
        return this.b;
    }

    public DrinkReminderAlertBean getDrinkReminderAlert() {
        return this.o;
    }

    public DualTimeBean getDualTimeBean() {
        return this.r;
    }

    public LiftWristToViewBean getLiftWristToView() {
        return this.n;
    }

    public MensturationBean getMensturationBean() {
        return this.g;
    }

    public NotificationBean getNotification() {
        return this.f;
    }

    public PhoneFinderBean getPhoneFinder() {
        return this.e;
    }

    public SedentaryAlertBean getSedentaryAlert() {
        return this.c;
    }

    public SleepBean getSleep() {
        return this.d;
    }

    public SocialDistancingBean getSocialDistancing() {
        return this.l;
    }

    public WeatherForecastBean getWeatherForecast() {
        return this.p;
    }

    public void setAlarm(AlarmBean alarmBean) {
        this.f6811a = alarmBean;
    }

    public void setAutoHr(AutoHr autoHr) {
        this.j = autoHr;
    }

    public void setAutoTemperature(AutoTemperature autoTemperature) {
        this.k = autoTemperature;
    }

    public void setCalendarBean(CalendarBean calendarBean) {
        this.h = calendarBean;
    }

    public void setCallQuickReplies(CallQuickRepliesBean callQuickRepliesBean) {
        this.q = callQuickRepliesBean;
    }

    public void setContactTracing(ContactTracingBean contactTracingBean) {
        this.m = contactTracingBean;
    }

    public void setDeviceSpecificParams(DeviceSpecificParamsBean deviceSpecificParamsBean) {
        this.i = deviceSpecificParamsBean;
    }

    public void setDndBean(DNDBean dNDBean) {
        this.b = dNDBean;
    }

    public void setDrinkReminderAlert(DrinkReminderAlertBean drinkReminderAlertBean) {
        this.o = drinkReminderAlertBean;
    }

    public GetAllUserAppSettingsRes setDualTimeBean(DualTimeBean dualTimeBean) {
        this.r = dualTimeBean;
        return this;
    }

    public void setLiftWristToView(LiftWristToViewBean liftWristToViewBean) {
        this.n = liftWristToViewBean;
    }

    public void setMensturationBean(MensturationBean mensturationBean) {
        this.g = mensturationBean;
    }

    public void setNotification(NotificationBean notificationBean) {
        this.f = notificationBean;
    }

    public void setPhoneFinder(PhoneFinderBean phoneFinderBean) {
        this.e = phoneFinderBean;
    }

    public void setSedentaryAlert(SedentaryAlertBean sedentaryAlertBean) {
        this.c = sedentaryAlertBean;
    }

    public void setSleep(SleepBean sleepBean) {
        this.d = sleepBean;
    }

    public void setSocialDistancing(SocialDistancingBean socialDistancingBean) {
        this.l = socialDistancingBean;
    }

    public void setWeatherForecast(WeatherForecastBean weatherForecastBean) {
        this.p = weatherForecastBean;
    }
}
