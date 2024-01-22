package com.ido.ble.callback;

import com.ido.ble.data.manage.database.HealthActivityV3;
import com.ido.ble.data.manage.database.HealthBloodPressureV3;
import com.ido.ble.data.manage.database.HealthBodyPower;
import com.ido.ble.data.manage.database.HealthGpsV3;
import com.ido.ble.data.manage.database.HealthHeartRateSecond;
import com.ido.ble.data.manage.database.HealthNoise;
import com.ido.ble.data.manage.database.HealthPressure;
import com.ido.ble.data.manage.database.HealthPressureItem;
import com.ido.ble.data.manage.database.HealthRespiratoryRate;
import com.ido.ble.data.manage.database.HealthSleepV3;
import com.ido.ble.data.manage.database.HealthSpO2;
import com.ido.ble.data.manage.database.HealthSpO2Item;
import com.ido.ble.data.manage.database.HealthSportV3;
import com.ido.ble.data.manage.database.HealthSwimming;
import com.ido.ble.data.manage.database.HealthTemperature;
import java.util.List;
/* loaded from: classes11.dex */
public class SyncV3CallBack {

    /* loaded from: classes11.dex */
    public interface ICallBack {
        void onFailed();

        void onGetHealthActivityV3Data(HealthActivityV3 healthActivityV3);

        void onGetHealthBloodPressure(HealthBloodPressureV3 healthBloodPressureV3);

        void onGetHealthBodyPower(HealthBodyPower healthBodyPower);

        void onGetHealthGpsV3Data(HealthGpsV3 healthGpsV3);

        void onGetHealthHeartRateSecondData(HealthHeartRateSecond healthHeartRateSecond, boolean z);

        void onGetHealthNoiseData(HealthNoise healthNoise);

        void onGetHealthPressureData(HealthPressure healthPressure, List<HealthPressureItem> list, boolean z);

        void onGetHealthRespiratoryRate(HealthRespiratoryRate healthRespiratoryRate);

        void onGetHealthSleepV3Data(HealthSleepV3 healthSleepV3);

        void onGetHealthSpO2Data(HealthSpO2 healthSpO2, List<HealthSpO2Item> list, boolean z);

        void onGetHealthSportV3Data(HealthSportV3 healthSportV3);

        void onGetHealthSwimmingData(HealthSwimming healthSwimming);

        void onGetHealthTemperature(HealthTemperature healthTemperature);

        void onProgress(int i);

        void onStart();

        void onStop();

        void onSuccess();
    }

    public static void onGetHealthActivityV3Data(final HealthActivityV3 healthActivityV3) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.SyncV3CallBack.10
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().H()) {
                    iCallBack.onGetHealthActivityV3Data(HealthActivityV3.this);
                }
            }
        });
    }

    public static void onGetHealthBloodPressure(final HealthBloodPressureV3 healthBloodPressureV3) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.SyncV3CallBack.18
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().H()) {
                    iCallBack.onGetHealthBloodPressure(HealthBloodPressureV3.this);
                }
            }
        });
    }

    public static void onGetHealthBodyPowerData(final HealthBodyPower healthBodyPower) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.SyncV3CallBack.16
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().H()) {
                    iCallBack.onGetHealthBodyPower(HealthBodyPower.this);
                }
            }
        });
    }

    public static void onGetHealthGpsV3Data(final HealthGpsV3 healthGpsV3) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.SyncV3CallBack.13
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().H()) {
                    iCallBack.onGetHealthGpsV3Data(HealthGpsV3.this);
                }
            }
        });
    }

    public static void onGetHealthHeartRateSecondData(final HealthHeartRateSecond healthHeartRateSecond, final boolean z) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.SyncV3CallBack.8
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().H()) {
                    iCallBack.onGetHealthHeartRateSecondData(HealthHeartRateSecond.this, z);
                }
            }
        });
    }

    public static void onGetHealthNoiseData(final HealthNoise healthNoise) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.SyncV3CallBack.14
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().H()) {
                    iCallBack.onGetHealthNoiseData(HealthNoise.this);
                }
            }
        });
    }

    public static void onGetHealthPressureData(final HealthPressure healthPressure, final List<HealthPressureItem> list, final boolean z) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.SyncV3CallBack.7
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().H()) {
                    iCallBack.onGetHealthPressureData(HealthPressure.this, list, z);
                }
            }
        });
    }

    public static void onGetHealthRespiratoryRateData(final HealthRespiratoryRate healthRespiratoryRate) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.SyncV3CallBack.15
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().H()) {
                    iCallBack.onGetHealthRespiratoryRate(HealthRespiratoryRate.this);
                }
            }
        });
    }

    public static void onGetHealthSleepV3Data(final HealthSleepV3 healthSleepV3) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.SyncV3CallBack.12
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().H()) {
                    iCallBack.onGetHealthSleepV3Data(HealthSleepV3.this);
                }
            }
        });
    }

    public static void onGetHealthSpO2Data(final HealthSpO2 healthSpO2, final List<HealthSpO2Item> list, final boolean z) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.SyncV3CallBack.1
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().H()) {
                    iCallBack.onGetHealthSpO2Data(HealthSpO2.this, list, z);
                }
            }
        });
    }

    public static void onGetHealthSportV3Data(final HealthSportV3 healthSportV3) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.SyncV3CallBack.11
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().H()) {
                    iCallBack.onGetHealthSportV3Data(HealthSportV3.this);
                }
            }
        });
    }

    public static void onGetHealthSwimmingData(final HealthSwimming healthSwimming) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.SyncV3CallBack.9
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().H()) {
                    iCallBack.onGetHealthSwimmingData(HealthSwimming.this);
                }
            }
        });
    }

    public static void onGetHealthTemperature(final HealthTemperature healthTemperature) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.SyncV3CallBack.17
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().H()) {
                    iCallBack.onGetHealthTemperature(HealthTemperature.this);
                }
            }
        });
    }

    public static void onSyncV3HealthFailed() {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.SyncV3CallBack.6
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().H()) {
                    iCallBack.onFailed();
                }
            }
        });
    }

    public static void onSyncV3HealthProgress(final int i) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.SyncV3CallBack.3
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().H()) {
                    iCallBack.onProgress(i);
                }
            }
        });
    }

    public static void onSyncV3HealthStart() {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.SyncV3CallBack.2
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().H()) {
                    iCallBack.onStart();
                }
            }
        });
    }

    public static void onSyncV3HealthStop() {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.SyncV3CallBack.4
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().H()) {
                    iCallBack.onStop();
                }
            }
        });
    }

    public static void onSyncV3HealthSuccess() {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.SyncV3CallBack.5
            @Override // java.lang.Runnable
            public void run() {
                for (ICallBack iCallBack : b.N().H()) {
                    iCallBack.onSuccess();
                }
            }
        });
    }
}
