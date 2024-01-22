package com.ido.ble.callback;

import com.ido.ble.data.manage.database.HealthActivity;
import com.ido.ble.data.manage.database.HealthBloodPressed;
import com.ido.ble.data.manage.database.HealthBloodPressedItem;
import com.ido.ble.data.manage.database.HealthHeartRate;
import com.ido.ble.data.manage.database.HealthHeartRateItem;
import com.ido.ble.data.manage.database.HealthSleep;
import com.ido.ble.data.manage.database.HealthSleepItem;
import com.ido.ble.data.manage.database.HealthSport;
import com.ido.ble.data.manage.database.HealthSportItem;
import com.veryfit.multi.nativeprotocol.Protocol;
import java.util.List;
/* loaded from: classes11.dex */
public class SyncCallBack {

    /* loaded from: classes11.dex */
    public interface IActivityCallBack {
        void onFailed();

        void onGetActivityData(HealthActivity healthActivity);

        void onStart();

        void onStop();

        void onSuccess();
    }

    /* loaded from: classes11.dex */
    public interface IConfigCallBack {
        void onFailed();

        void onStart();

        void onStop();

        void onSuccess();
    }

    /* loaded from: classes11.dex */
    public interface IHealthCallBack {
        void onFailed();

        void onGetBloodPressureData(HealthBloodPressed healthBloodPressed, List<HealthBloodPressedItem> list, boolean z);

        void onGetHeartRateData(HealthHeartRate healthHeartRate, List<HealthHeartRateItem> list, boolean z);

        void onGetSleepData(HealthSleep healthSleep, List<HealthSleepItem> list);

        void onGetSportData(HealthSport healthSport, List<HealthSportItem> list, boolean z);

        void onProgress(int i);

        void onStart();

        void onStop();

        void onSuccess();
    }

    public static void a() {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.SyncCallBack.10
            @Override // java.lang.Runnable
            public void run() {
                for (IActivityCallBack iActivityCallBack : b.N().E()) {
                    iActivityCallBack.onFailed();
                }
            }
        });
    }

    public static void a(final int i) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.SyncCallBack.3
            @Override // java.lang.Runnable
            public void run() {
                for (IHealthCallBack iHealthCallBack : b.N().G()) {
                    iHealthCallBack.onProgress(i);
                }
            }
        });
    }

    public static void a(final HealthActivity healthActivity) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.SyncCallBack.18
            @Override // java.lang.Runnable
            public void run() {
                for (IActivityCallBack iActivityCallBack : b.N().E()) {
                    iActivityCallBack.onGetActivityData(HealthActivity.this);
                }
            }
        });
    }

    public static void a(final HealthBloodPressed healthBloodPressed, final List<HealthBloodPressedItem> list, final boolean z) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.SyncCallBack.17
            @Override // java.lang.Runnable
            public void run() {
                for (IHealthCallBack iHealthCallBack : b.N().G()) {
                    iHealthCallBack.onGetBloodPressureData(HealthBloodPressed.this, list, z);
                }
            }
        });
    }

    public static void a(final HealthHeartRate healthHeartRate, final List<HealthHeartRateItem> list, final boolean z) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.SyncCallBack.15
            @Override // java.lang.Runnable
            public void run() {
                for (IHealthCallBack iHealthCallBack : b.N().G()) {
                    iHealthCallBack.onGetHeartRateData(HealthHeartRate.this, list, z);
                }
            }
        });
    }

    public static void a(final HealthSleep healthSleep, final List<HealthSleepItem> list) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.SyncCallBack.16
            @Override // java.lang.Runnable
            public void run() {
                for (IHealthCallBack iHealthCallBack : b.N().G()) {
                    iHealthCallBack.onGetSleepData(HealthSleep.this, list);
                }
            }
        });
    }

    public static void a(final HealthSport healthSport, final List<HealthSportItem> list, final boolean z) {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.SyncCallBack.1
            @Override // java.lang.Runnable
            public void run() {
                for (IHealthCallBack iHealthCallBack : b.N().G()) {
                    iHealthCallBack.onGetSportData(HealthSport.this, list, z);
                }
            }
        });
    }

    public static void b() {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.SyncCallBack.7
            @Override // java.lang.Runnable
            public void run() {
                for (IActivityCallBack iActivityCallBack : b.N().E()) {
                    iActivityCallBack.onStart();
                }
            }
        });
    }

    public static void c() {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.SyncCallBack.8
            @Override // java.lang.Runnable
            public void run() {
                for (IActivityCallBack iActivityCallBack : b.N().E()) {
                    iActivityCallBack.onStop();
                }
            }
        });
    }

    public static void d() {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.SyncCallBack.9
            @Override // java.lang.Runnable
            public void run() {
                for (IActivityCallBack iActivityCallBack : b.N().E()) {
                    iActivityCallBack.onSuccess();
                }
            }
        });
    }

    public static void e() {
        Protocol.IS_SYNC_CONFIG = false;
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.SyncCallBack.14
            @Override // java.lang.Runnable
            public void run() {
                for (IConfigCallBack iConfigCallBack : b.N().F()) {
                    iConfigCallBack.onFailed();
                }
            }
        });
    }

    public static void f() {
        Protocol.IS_SYNC_CONFIG = true;
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.SyncCallBack.11
            @Override // java.lang.Runnable
            public void run() {
                for (IConfigCallBack iConfigCallBack : b.N().F()) {
                    iConfigCallBack.onStart();
                }
            }
        });
    }

    public static void g() {
        Protocol.IS_SYNC_CONFIG = false;
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.SyncCallBack.12
            @Override // java.lang.Runnable
            public void run() {
                for (IConfigCallBack iConfigCallBack : b.N().F()) {
                    iConfigCallBack.onStop();
                }
            }
        });
    }

    public static void h() {
        Protocol.IS_SYNC_CONFIG = false;
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.SyncCallBack.13
            @Override // java.lang.Runnable
            public void run() {
                for (IConfigCallBack iConfigCallBack : b.N().F()) {
                    iConfigCallBack.onSuccess();
                }
            }
        });
    }

    public static void i() {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.SyncCallBack.6
            @Override // java.lang.Runnable
            public void run() {
                for (IHealthCallBack iHealthCallBack : b.N().G()) {
                    iHealthCallBack.onFailed();
                }
            }
        });
    }

    public static void j() {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.SyncCallBack.2
            @Override // java.lang.Runnable
            public void run() {
                for (IHealthCallBack iHealthCallBack : b.N().G()) {
                    iHealthCallBack.onStart();
                }
            }
        });
    }

    public static void k() {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.SyncCallBack.4
            @Override // java.lang.Runnable
            public void run() {
                for (IHealthCallBack iHealthCallBack : b.N().G()) {
                    iHealthCallBack.onStop();
                }
            }
        });
    }

    public static void l() {
        b.N().a(new Runnable() { // from class: com.ido.ble.callback.SyncCallBack.5
            @Override // java.lang.Runnable
            public void run() {
                for (IHealthCallBack iHealthCallBack : b.N().G()) {
                    iHealthCallBack.onSuccess();
                }
            }
        });
    }
}
