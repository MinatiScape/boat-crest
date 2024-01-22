package com.ido.ble.protocol.handler;

import android.text.TextUtils;
import com.ido.ble.callback.ConnectCallBack;
import com.ido.ble.callback.SyncCallBack;
import com.ido.ble.common.TimeUtil;
import com.ido.ble.custom.CustomConfig;
import com.ido.ble.data.manage.database.HealthActivity;
import com.ido.ble.data.manage.database.HealthBloodPressed;
import com.ido.ble.data.manage.database.HealthBloodPressedItem;
import com.ido.ble.data.manage.database.HealthHeartRate;
import com.ido.ble.data.manage.database.HealthHeartRateItem;
import com.ido.ble.data.manage.database.HealthSleep;
import com.ido.ble.data.manage.database.HealthSleepItem;
import com.ido.ble.data.manage.database.HealthSport;
import com.ido.ble.data.manage.database.HealthSportItem;
import com.ido.ble.logs.LogTool;
import java.util.Date;
import java.util.List;
/* loaded from: classes11.dex */
final class SyncHandler {

    /* loaded from: classes11.dex */
    public static class HealthBloodPressedWrapper extends HealthBloodPressed {
        public List<HealthBloodPressedItem> items;
    }

    /* loaded from: classes11.dex */
    public static class HealthHeartRateWrapper extends HealthHeartRate {
        public List<HealthHeartRateItem> items;
    }

    /* loaded from: classes11.dex */
    public static class HealthSleepWrapper extends HealthSleep {
        public List<HealthSleepItem> items;
    }

    /* loaded from: classes11.dex */
    public static class HealthSportWrapper extends HealthSport {
        public List<HealthSportItem> items;
    }

    public static void a(int i, int i2, int i3) {
        String str;
        if (i == 4) {
            if (com.veryfit.multi.nativeprotocol.a.SUCCESS == com.veryfit.multi.nativeprotocol.a.a(i2)) {
                LogTool.d(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] sync config success!");
                com.ido.ble.event.stat.one.c.i("sync_config");
                SyncCallBack.h();
                return;
            }
            LogTool.d(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] sync config failed!");
            com.ido.ble.event.stat.one.c.a("sync_config", "error:" + i2);
            SyncCallBack.e();
        } else if (i == 5) {
            if (com.veryfit.multi.nativeprotocol.a.SUCCESS == com.veryfit.multi.nativeprotocol.a.a(i2)) {
                LogTool.d(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] sync health data success!");
                com.ido.ble.event.stat.one.c.i("sync_health");
                SyncCallBack.l();
                return;
            }
            LogTool.d(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] sync health data failed!");
            com.ido.ble.event.stat.one.c.a("sync_health", "error:" + i2);
            SyncCallBack.i();
        } else if (i == 6) {
            SyncCallBack.a(i3);
        } else if (i == 11) {
            LogTool.d(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] fast sync config complete.error:" + i2);
            ConnectCallBack.c(com.ido.ble.bluetooth.a.e());
            ConnectCallBack.f(com.ido.ble.bluetooth.a.e());
        } else {
            if (i == 650) {
                LogTool.d(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] sync activity data failed!");
                str = "error:13";
            } else if (i != 651) {
                return;
            } else {
                if (com.veryfit.multi.nativeprotocol.a.SUCCESS == com.veryfit.multi.nativeprotocol.a.a(i2)) {
                    LogTool.d(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] sync activity data success!");
                    com.ido.ble.event.stat.one.c.i("sync_activity");
                    SyncCallBack.d();
                    return;
                }
                LogTool.d(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] sync activity data failed!");
                str = "error:" + i2;
            }
            com.ido.ble.event.stat.one.c.a("sync_activity", str);
            SyncCallBack.a();
        }
    }

    public static void a(int i, byte[] bArr, int i2) {
        if (i == 652) {
            a(bArr);
            return;
        }
        switch (i) {
            case 6000:
                e(bArr);
                return;
            case com.veryfit.multi.nativeprotocol.b.o4 /* 6001 */:
                d(bArr);
                return;
            case com.veryfit.multi.nativeprotocol.b.p4 /* 6002 */:
                c(bArr);
                return;
            case com.veryfit.multi.nativeprotocol.b.q4 /* 6003 */:
                b(bArr);
                return;
            default:
                return;
        }
    }

    private static void a(byte[] bArr) {
        String str;
        String d = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(d)) {
            str = "[SYNC_DATA] saveActivityData, jsonString is null";
        } else {
            LogTool.d(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] saveActivityData, jsonData=" + d);
            HealthActivity healthActivity = (HealthActivity) com.ido.ble.common.k.c(d, HealthActivity.class);
            if (healthActivity == null) {
                str = "[SYNC_DATA] saveActivityData, healthActivity is null";
            } else if (healthActivity.getYear() != 0) {
                if (("" + healthActivity.getYear()).length() == 2) {
                    healthActivity.setYear(healthActivity.getYear() + 2000);
                }
                healthActivity.setHr_data_vlaue_json(com.ido.ble.common.k.a(healthActivity.getHr_data_vlaue()));
                healthActivity.setDate(TimeUtil.getDate(healthActivity.getYear(), healthActivity.getMonth(), healthActivity.getDay(), healthActivity.getHour(), healthActivity.getMinute(), healthActivity.getSecond()));
                if (CustomConfig.getConfig().isSaveDeviceDataToDB()) {
                    com.ido.ble.f.a.b.d().a(healthActivity);
                }
                SyncCallBack.a(healthActivity);
                return;
            } else {
                str = "[SYNC_DATA] saveActivityData, year is 0";
            }
        }
        LogTool.b(com.ido.ble.logs.a.f12307a, str);
    }

    public static boolean a(int i) {
        if (i == 4 || i == 5 || i == 6 || i == 10 || i == 11 || i == 14) {
            return true;
        }
        switch (i) {
            case com.veryfit.multi.nativeprotocol.b.W2 /* 650 */:
            case com.veryfit.multi.nativeprotocol.b.X2 /* 651 */:
            case com.veryfit.multi.nativeprotocol.b.Y2 /* 652 */:
                return true;
            default:
                switch (i) {
                    case 6000:
                    case com.veryfit.multi.nativeprotocol.b.o4 /* 6001 */:
                    case com.veryfit.multi.nativeprotocol.b.p4 /* 6002 */:
                    case com.veryfit.multi.nativeprotocol.b.q4 /* 6003 */:
                        return true;
                    default:
                        return false;
                }
        }
    }

    private static void b(byte[] bArr) {
        String d = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(d)) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] saveBloodPressureData, jsonString is null");
            return;
        }
        HealthBloodPressedWrapper healthBloodPressedWrapper = (HealthBloodPressedWrapper) com.ido.ble.common.k.c(d, HealthBloodPressedWrapper.class);
        if (healthBloodPressedWrapper == null) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] saveBloodPressureData, healthBloodPressedWrapper is null");
        } else if (healthBloodPressedWrapper.getYear() == 0) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] saveBloodPressureData, year is 0");
        } else {
            LogTool.d(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] saveBloodPressureData, jsonData=" + d);
            Date date = TimeUtil.getDate(healthBloodPressedWrapper.getYear(), healthBloodPressedWrapper.getMonth(), healthBloodPressedWrapper.getDay());
            healthBloodPressedWrapper.setDate(date);
            if (CustomConfig.getConfig().isSaveDeviceDataToDB()) {
                com.ido.ble.f.a.b.d().a(healthBloodPressedWrapper);
            }
            List<HealthBloodPressedItem> list = healthBloodPressedWrapper.items;
            if (list == null || list.size() == 0) {
                SyncCallBack.a((HealthBloodPressed) healthBloodPressedWrapper, (List<HealthBloodPressedItem>) null, false);
                return;
            }
            for (HealthBloodPressedItem healthBloodPressedItem : healthBloodPressedWrapper.items) {
                healthBloodPressedItem.setYear(healthBloodPressedWrapper.getYear());
                healthBloodPressedItem.setMonth(healthBloodPressedWrapper.getMonth());
                healthBloodPressedItem.setDay(healthBloodPressedWrapper.getDay());
                healthBloodPressedItem.setDate(date);
            }
            if (com.ido.ble.common.e.c() && com.ido.ble.common.h.a(healthBloodPressedWrapper.day)) {
                if (CustomConfig.getConfig().isSaveDeviceDataToDB()) {
                    com.ido.ble.f.a.b.d().a(healthBloodPressedWrapper.items);
                }
                com.ido.ble.common.g.a(healthBloodPressedWrapper.day, healthBloodPressedWrapper.items.size());
                SyncCallBack.a((HealthBloodPressed) healthBloodPressedWrapper, healthBloodPressedWrapper.items, true);
                return;
            }
            if (CustomConfig.getConfig().isSaveDeviceDataToDB()) {
                com.ido.ble.f.a.b.d().c(healthBloodPressedWrapper.getYear(), healthBloodPressedWrapper.getMonth(), healthBloodPressedWrapper.getDay());
                com.ido.ble.f.a.b.d().a(healthBloodPressedWrapper.items);
            }
            SyncCallBack.a((HealthBloodPressed) healthBloodPressedWrapper, healthBloodPressedWrapper.items, false);
        }
    }

    private static void c(byte[] bArr) {
        String d = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(d)) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] saveHeartRateData, jsonString is null");
            return;
        }
        HealthHeartRateWrapper healthHeartRateWrapper = (HealthHeartRateWrapper) com.ido.ble.common.k.c(d, HealthHeartRateWrapper.class);
        if (healthHeartRateWrapper == null) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] saveHeartRateData, healthHeartRateWrapper is null");
        } else if (healthHeartRateWrapper.getYear() == 0) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] saveHeartRateData, year is 0");
        } else {
            LogTool.d(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] saveHeartRateData, jsonData=" + d);
            Date date = TimeUtil.getDate(healthHeartRateWrapper.getYear(), healthHeartRateWrapper.getMonth(), healthHeartRateWrapper.getDay());
            healthHeartRateWrapper.setDate(date);
            if (CustomConfig.getConfig().isSaveDeviceDataToDB()) {
                com.ido.ble.f.a.b.d().a(healthHeartRateWrapper);
            }
            List<HealthHeartRateItem> list = healthHeartRateWrapper.items;
            if (list == null || list.size() == 0) {
                SyncCallBack.a((HealthHeartRate) healthHeartRateWrapper, (List<HealthHeartRateItem>) null, false);
                return;
            }
            for (HealthHeartRateItem healthHeartRateItem : healthHeartRateWrapper.items) {
                healthHeartRateItem.setYear(healthHeartRateWrapper.getYear());
                healthHeartRateItem.setMonth(healthHeartRateWrapper.getMonth());
                healthHeartRateItem.setDay(healthHeartRateWrapper.getDay());
                healthHeartRateItem.setDate(date);
            }
            if (com.ido.ble.common.e.c() && com.ido.ble.common.h.a(healthHeartRateWrapper.day)) {
                if (CustomConfig.getConfig().isSaveDeviceDataToDB()) {
                    com.ido.ble.f.a.b.d().b(healthHeartRateWrapper.items);
                }
                com.ido.ble.common.g.b(healthHeartRateWrapper.day, healthHeartRateWrapper.items.size());
                SyncCallBack.a((HealthHeartRate) healthHeartRateWrapper, healthHeartRateWrapper.items, true);
                return;
            }
            if (CustomConfig.getConfig().isSaveDeviceDataToDB()) {
                com.ido.ble.f.a.b.d().e(healthHeartRateWrapper.getYear(), healthHeartRateWrapper.getMonth(), healthHeartRateWrapper.getDay());
                com.ido.ble.f.a.b.d().b(healthHeartRateWrapper.items);
            }
            SyncCallBack.a((HealthHeartRate) healthHeartRateWrapper, healthHeartRateWrapper.items, false);
        }
    }

    private static void d(byte[] bArr) {
        String d = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(d)) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] saveSleepData, jsonString is null");
            return;
        }
        HealthSleepWrapper healthSleepWrapper = (HealthSleepWrapper) com.ido.ble.common.k.c(d, HealthSleepWrapper.class);
        if (healthSleepWrapper == null) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] saveSleepData, healthSleepWrapper is null");
        } else if (healthSleepWrapper.getYear() == 0) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] saveSleepData, year is 0");
        } else {
            LogTool.d(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] saveSleepData, jsonData=" + d);
            Date date = TimeUtil.getDate(healthSleepWrapper.getYear(), healthSleepWrapper.getMonth(), healthSleepWrapper.getDay());
            healthSleepWrapper.setDate(date);
            if (CustomConfig.getConfig().isSaveDeviceDataToDB()) {
                com.ido.ble.f.a.b.d().a(healthSleepWrapper);
            }
            List<HealthSleepItem> list = healthSleepWrapper.items;
            if (list == null || list.size() == 0) {
                SyncCallBack.a(healthSleepWrapper, null);
                return;
            }
            for (HealthSleepItem healthSleepItem : healthSleepWrapper.items) {
                healthSleepItem.setYear(healthSleepWrapper.getYear());
                healthSleepItem.setMonth(healthSleepWrapper.getMonth());
                healthSleepItem.setDay(healthSleepWrapper.getDay());
                healthSleepItem.setDate(date);
            }
            if (CustomConfig.getConfig().isSaveDeviceDataToDB()) {
                com.ido.ble.f.a.b.d().j(healthSleepWrapper.getYear(), healthSleepWrapper.getMonth(), healthSleepWrapper.getDay());
                com.ido.ble.f.a.b.d().d(healthSleepWrapper.items);
            }
            SyncCallBack.a(healthSleepWrapper, healthSleepWrapper.items);
        }
    }

    private static void e(byte[] bArr) {
        String d = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(d)) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] saveSportData, jsonString is null");
            return;
        }
        HealthSportWrapper healthSportWrapper = (HealthSportWrapper) com.ido.ble.common.k.c(d, HealthSportWrapper.class);
        if (healthSportWrapper == null) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] saveSportData, healthSportWrapper is null");
        } else if (healthSportWrapper.getYear() == 0) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] saveSportData, year is 0");
        } else {
            LogTool.d(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] saveSportData, jsonData=" + d);
            Date date = TimeUtil.getDate(healthSportWrapper.getYear(), healthSportWrapper.getMonth(), healthSportWrapper.getDay());
            healthSportWrapper.setDate(date);
            if (CustomConfig.getConfig().isSaveDeviceDataToDB()) {
                com.ido.ble.f.a.b.d().a(healthSportWrapper);
            }
            List<HealthSportItem> list = healthSportWrapper.items;
            if (list == null || list.size() == 0) {
                SyncCallBack.a((HealthSport) healthSportWrapper, (List<HealthSportItem>) null, false);
                return;
            }
            for (HealthSportItem healthSportItem : healthSportWrapper.items) {
                healthSportItem.setYear(healthSportWrapper.getYear());
                healthSportItem.setMonth(healthSportWrapper.getMonth());
                healthSportItem.setDay(healthSportWrapper.getDay());
                healthSportItem.setDate(date);
            }
            if (com.ido.ble.common.e.c() && com.ido.ble.common.h.a(healthSportWrapper.day)) {
                if (CustomConfig.getConfig().isSaveDeviceDataToDB()) {
                    com.ido.ble.f.a.b.d().f(healthSportWrapper.items);
                }
                com.ido.ble.common.g.c(healthSportWrapper.day, healthSportWrapper.items.size());
                SyncCallBack.a((HealthSport) healthSportWrapper, healthSportWrapper.items, true);
                return;
            }
            if (CustomConfig.getConfig().isSaveDeviceDataToDB()) {
                com.ido.ble.f.a.b.d().n(healthSportWrapper.getYear(), healthSportWrapper.getMonth(), healthSportWrapper.getDay());
                com.ido.ble.f.a.b.d().f(healthSportWrapper.items);
            }
            SyncCallBack.a((HealthSport) healthSportWrapper, healthSportWrapper.items, false);
        }
    }
}
