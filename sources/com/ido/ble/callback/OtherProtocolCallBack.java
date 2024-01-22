package com.ido.ble.callback;
/* loaded from: classes11.dex */
public class OtherProtocolCallBack {

    /* loaded from: classes11.dex */
    public interface ICallBack {
        void onFailed(SettingType settingType);

        void onSuccess(SettingType settingType);
    }

    /* loaded from: classes11.dex */
    public enum SettingType {
        MENSTRUAL,
        MENSTRUAL_REMIND,
        CALORIE_DISTANCE_GOAL,
        SPO2,
        PRESSURE,
        SPORT_MODE_SORT,
        GPSMAKEFILE
    }

    public static void onSetCallBack(final Boolean bool, final SettingType settingType) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.OtherProtocolCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().w()) {
                    if (bool.booleanValue()) {
                        iCallBack.onSuccess(settingType);
                    } else {
                        iCallBack.onFailed(settingType);
                    }
                }
            }
        });
    }
}
