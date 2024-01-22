package com.ido.ble.callback;
/* loaded from: classes11.dex */
public class SettingCallBack {

    /* loaded from: classes11.dex */
    public interface ICallBack {
        void onFailed(SettingType settingType);

        void onSuccess(SettingType settingType, Object obj);
    }

    /* loaded from: classes11.dex */
    public enum SettingType {
        TIME,
        ALARM,
        GOAL,
        USER_INFO,
        UNIT,
        DIAL_PLATE,
        SHORTCUT,
        BLOOD_ADJUST,
        LONG_SIT,
        ANTI_LOST_MODE,
        ANTI_LOST_PARA,
        HAND_MODE,
        PHONE_SYSTEM,
        NOTICE_REMINDER_SWITCH_STATUS,
        HEART_RATE_INTERVAL,
        HEART_RATE_MEASURE_MODE,
        HEART_RATE_MEASURE_MODE_V3,
        FIND_PHONE_SWITCH,
        ONE_KEY_RESET,
        UP_HAND_GESTURE,
        NOT_DISTURB,
        MUSIC_SWITCH,
        DISPLAY_MODE,
        ONE_KEY_SOS,
        WEATHER_SWITCH,
        QUICK_SPORT_MODE,
        NOTICE_SWITCH,
        SLEEP_MONITORING,
        NIGHT_TEMPERATURE_MONITORING,
        FITNESS_GUIDANCE,
        DEVICE_UNREAD_REMINDER,
        WALK_REAL_TIME_REMINDER,
        SCHEDULE_REMINDER_SWITCH,
        SCREEN_BRIGHTNESS,
        BREATHE_TRAIN,
        WALK_REMINDER,
        ACTIVITY_SWITCH,
        DRINK_WATER_REMINDER,
        MENU_LIST_SET,
        MUSIC_CONTROL_INFO,
        TIME_ZONE,
        ALARM_V3,
        SPORT_SORT_V3,
        RESTORE_FACTORY,
        PHONE_VOICE,
        QUICK_REPLY_INFO,
        MEDICINE_REMINDER,
        WASH_HAND_REMINDER,
        WEATHER_CITY_NAME,
        NOISE,
        WORLD_TIME,
        HEART_RATE_SMART,
        WATCH_DIAL_ORDER,
        MENSTRUATION_HISTORICAL_DATA,
        STOP_FIND_PHONE,
        ICON_INFORMATION_NOTICE,
        RESPIRETORY_RATE,
        BODYPOWERSWITCH,
        SPORT_PLAN,
        CAMERA_PERMISSION
    }

    public static final void a(final Boolean bool, final SettingType settingType, final Object obj) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.SettingCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().C()) {
                    if (bool.booleanValue()) {
                        iCallBack.onSuccess(settingType, obj);
                    } else {
                        iCallBack.onFailed(settingType);
                    }
                }
            }
        });
    }
}
