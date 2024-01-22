package com.ido.ble.protocol.handler;

import android.text.TextUtils;
import androidx.exifinterface.media.ExifInterface;
import com.clevertap.android.sdk.Constants;
import com.ido.ble.callback.SyncV3CallBack;
import com.ido.ble.common.TimeUtil;
import com.ido.ble.custom.CustomConfig;
import com.ido.ble.data.manage.database.HealthActivityV3;
import com.ido.ble.data.manage.database.HealthBloodPressureV3;
import com.ido.ble.data.manage.database.HealthBodyPower;
import com.ido.ble.data.manage.database.HealthGpsItemV3;
import com.ido.ble.data.manage.database.HealthGpsV3;
import com.ido.ble.data.manage.database.HealthHeartRateSecond;
import com.ido.ble.data.manage.database.HealthHeartRateSecondItem;
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
import com.ido.ble.logs.LogTool;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/* loaded from: classes11.dex */
final class SyncV3Handler {

    /* loaded from: classes11.dex */
    public static class HealthPressureWrapper extends HealthPressure {
        public List<HealthPressureItem> items;
    }

    /* loaded from: classes11.dex */
    public static class HealthSpO2Wrapper extends HealthSpO2 {
        public List<HealthSpO2Item> items;
    }

    private static void handleActivityV3Data(byte[] bArr) {
        String str;
        String d = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(d)) {
            str = "[SYNC_DATA] handleActivityV3Data, jsonString is null";
        } else {
            LogTool.d(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] handleActivityV3Data, jsonData=" + d);
            HealthActivityV3 healthActivityV3 = (HealthActivityV3) com.ido.ble.common.k.c(d, HealthActivityV3.class);
            if (healthActivityV3 == null) {
                str = "[SYNC_DATA] handleActivityV3Data, healthActivityV3 is null";
            } else if (healthActivityV3.year != 0) {
                if (("" + healthActivityV3.year).length() == 2) {
                    healthActivityV3.year += 2000;
                }
                SyncV3CallBack.onGetHealthActivityV3Data(healthActivityV3);
                return;
            } else {
                str = "[SYNC_DATA] handleActivityV3Data, year is 0";
            }
        }
        LogTool.b(com.ido.ble.logs.a.f12307a, str);
    }

    private static void handleBloodPressureData(byte[] bArr) {
        String str;
        String d = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(d)) {
            str = "[SYNC_DATA] handleBloodPressureData, jsonString is null";
        } else {
            LogTool.d(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] handleBloodPressureData, jsonData=" + d);
            HealthBloodPressureV3 healthBloodPressureV3 = (HealthBloodPressureV3) com.ido.ble.common.k.c(d, HealthBloodPressureV3.class);
            if (healthBloodPressureV3 != null) {
                SyncV3CallBack.onGetHealthBloodPressure(healthBloodPressureV3);
                return;
            }
            str = "[SYNC_DATA] handleBloodPressureData, BloodPressureData is null";
        }
        LogTool.b(com.ido.ble.logs.a.f12307a, str);
    }

    private static void handleBodyPowerData(byte[] bArr) {
        String str;
        String d = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(d)) {
            str = "[SYNC_DATA] handleBodyPowerData, jsonString is null";
        } else {
            LogTool.d(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] handleBodyPowerData, jsonData=" + d);
            HealthBodyPower healthBodyPower = (HealthBodyPower) com.ido.ble.common.k.c(d, HealthBodyPower.class);
            if (healthBodyPower != null) {
                SyncV3CallBack.onGetHealthBodyPowerData(healthBodyPower);
                return;
            }
            str = "[SYNC_DATA] handleBodyPowerData, healthBodyPower is null";
        }
        LogTool.b(com.ido.ble.logs.a.f12307a, str);
    }

    private static List<HealthGpsItemV3> handleHealthGpsItemV3Data(HealthGpsV3 healthGpsV3) {
        double d;
        ArrayList arrayList = new ArrayList();
        List<String> list = healthGpsV3.stringItems;
        if (list != null && list.size() != 0) {
            for (String str : healthGpsV3.stringItems) {
                if (str.contains(Constants.SEPARATOR_COMMA)) {
                    String[] split = str.split(Constants.SEPARATOR_COMMA);
                    double d2 = 0.0d;
                    try {
                        d = com.ido.ble.h.c.a(split[0], ExifInterface.LONGITUDE_EAST);
                    } catch (Exception e) {
                        e = e;
                        d = 0.0d;
                    }
                    try {
                        d2 = com.ido.ble.h.c.a(split[1], "N");
                    } catch (Exception e2) {
                        e = e2;
                        LogTool.b(com.ido.ble.logs.a.j, "[handleHealthGpsItemV3Data]" + e.toString());
                        HealthGpsItemV3 healthGpsItemV3 = new HealthGpsItemV3();
                        healthGpsItemV3.latitude = Double.valueOf(d2);
                        healthGpsItemV3.longitude = Double.valueOf(d);
                        arrayList.add(healthGpsItemV3);
                    }
                    HealthGpsItemV3 healthGpsItemV32 = new HealthGpsItemV3();
                    healthGpsItemV32.latitude = Double.valueOf(d2);
                    healthGpsItemV32.longitude = Double.valueOf(d);
                    arrayList.add(healthGpsItemV32);
                }
            }
            LogTool.d(com.ido.ble.logs.a.j, "[handleHealthGpsItemV3Data] size = " + arrayList.size());
        }
        return arrayList;
    }

    private static void handleHealthGpsV3Data(byte[] bArr) {
        String d = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(d)) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] handleHealthGpsV3Data, jsonString is null");
            return;
        }
        LogTool.d(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] handleHealthGpsV3Data, jsonData=" + d);
        HealthGpsV3 healthGpsV3 = (HealthGpsV3) com.ido.ble.common.k.c(d, HealthGpsV3.class);
        if (healthGpsV3 == null) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] handleHealthGpsV3Data, healthGpsV3 is null");
        } else if (healthGpsV3.year == 0) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] handleHealthGpsV3Data, healthGpsV3.year = 0");
        } else {
            ArrayList arrayList = new ArrayList();
            healthGpsV3.items = arrayList;
            arrayList.addAll(handleHealthGpsItemV3Data(healthGpsV3));
            SyncV3CallBack.onGetHealthGpsV3Data(healthGpsV3);
        }
    }

    private static void handleHealthSleepV3Data(byte[] bArr) {
        String str;
        String d = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(d)) {
            str = "[SYNC_DATA] handleHealthSleepV3Data, jsonString is null";
        } else {
            LogTool.d(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] handleHealthSleepV3Data, jsonData=" + d);
            HealthSleepV3 healthSleepV3 = (HealthSleepV3) com.ido.ble.common.k.c(d, HealthSleepV3.class);
            if (healthSleepV3 != null) {
                SyncV3CallBack.onGetHealthSleepV3Data(healthSleepV3);
                return;
            }
            str = "[SYNC_DATA] handleHealthSleepV3Data, healthSleepV3 is null";
        }
        LogTool.b(com.ido.ble.logs.a.f12307a, str);
    }

    private static void handleHealthSportV3Data(byte[] bArr) {
        String str;
        String d = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(d)) {
            str = "[SYNC_DATA] handleHealthSportV3Data, jsonString is null";
        } else {
            LogTool.d(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] handleHealthSportV3Data, jsonData=" + d);
            HealthSportV3 healthSportV3 = (HealthSportV3) com.ido.ble.common.k.c(d, HealthSportV3.class);
            if (healthSportV3 == null) {
                str = "[SYNC_DATA] handleHealthSportV3Data, healthSportV3 is null";
            } else if (healthSportV3.year != 0) {
                if (("" + healthSportV3.year).length() == 2) {
                    healthSportV3.year += 2000;
                }
                SyncV3CallBack.onGetHealthSportV3Data(healthSportV3);
                return;
            } else {
                str = "[SYNC_DATA] handleHealthSportV3Data, year is 0";
            }
        }
        LogTool.b(com.ido.ble.logs.a.f12307a, str);
    }

    public static void handleIntResult(int i, int i2, int i3) {
        if (i != 18) {
            if (i != 19) {
                return;
            }
            SyncV3CallBack.onSyncV3HealthProgress(i3);
        } else if (com.veryfit.multi.nativeprotocol.a.SUCCESS == com.veryfit.multi.nativeprotocol.a.a(i2)) {
            LogTool.d(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] sync v3_health data success!");
            SyncV3CallBack.onSyncV3HealthSuccess();
        } else {
            LogTool.d(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] sync v3_health data failed!, error=" + i2);
            SyncV3CallBack.onSyncV3HealthFailed();
        }
    }

    public static void handleJsonResult(int i, byte[] bArr, int i2) {
        switch (i) {
            case com.veryfit.multi.nativeprotocol.b.v4 /* 7001 */:
                saveHealthSpO2Data(bArr);
                return;
            case 7002:
                saveHealthPressureData(bArr);
                return;
            case 7003:
                saveHealthHRSecondData(bArr);
                return;
            case 7004:
                handleActivityV3Data(bArr);
                return;
            case 7005:
                handleHealthGpsV3Data(bArr);
                return;
            case 7006:
                saveHealthSwimmingData(bArr);
                return;
            case com.veryfit.multi.nativeprotocol.b.A4 /* 7007 */:
                handleHealthSleepV3Data(bArr);
                return;
            case com.veryfit.multi.nativeprotocol.b.B4 /* 7008 */:
                handleHealthSportV3Data(bArr);
                return;
            case com.veryfit.multi.nativeprotocol.b.C4 /* 7009 */:
                handleNoiseData(bArr);
                return;
            case com.veryfit.multi.nativeprotocol.b.D4 /* 7010 */:
                handleTemperatureData(bArr);
                return;
            case com.veryfit.multi.nativeprotocol.b.E4 /* 7011 */:
                handleBloodPressureData(bArr);
                return;
            case com.veryfit.multi.nativeprotocol.b.F4 /* 7012 */:
                handleRespiratoryRateData(bArr);
                return;
            case com.veryfit.multi.nativeprotocol.b.G4 /* 7013 */:
                handleBodyPowerData(bArr);
                return;
            default:
                return;
        }
    }

    private static void handleNoiseData(byte[] bArr) {
        String str;
        String d = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(d)) {
            str = "[SYNC_DATA] handleNoiseData, jsonString is null";
        } else {
            LogTool.d(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] handleNoiseData, jsonData=" + d);
            HealthNoise healthNoise = (HealthNoise) com.ido.ble.common.k.c(d, HealthNoise.class);
            if (healthNoise != null) {
                SyncV3CallBack.onGetHealthNoiseData(healthNoise);
                return;
            }
            str = "[SYNC_DATA] handleNoiseData, healthNoiseData is null";
        }
        LogTool.b(com.ido.ble.logs.a.f12307a, str);
    }

    private static void handleRespiratoryRateData(byte[] bArr) {
        String str;
        String d = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(d)) {
            str = "[SYNC_DATA] handleRespiratoryRateData, jsonString is null";
        } else {
            LogTool.d(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] handleRespiratoryRateData, jsonData=" + d);
            HealthRespiratoryRate healthRespiratoryRate = (HealthRespiratoryRate) com.ido.ble.common.k.c(d, HealthRespiratoryRate.class);
            if (healthRespiratoryRate != null) {
                SyncV3CallBack.onGetHealthRespiratoryRateData(healthRespiratoryRate);
                return;
            }
            str = "[SYNC_DATA] handleRespiratoryRateData, handleRespiratoryRateData is null";
        }
        LogTool.b(com.ido.ble.logs.a.f12307a, str);
    }

    private static void handleTemperatureData(byte[] bArr) {
        String str;
        String d = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(d)) {
            str = "[SYNC_DATA] handleTemperatureData, jsonString is null";
        } else {
            LogTool.d(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] handleTemperatureData, jsonData=" + d);
            HealthTemperature healthTemperature = (HealthTemperature) com.ido.ble.common.k.c(d, HealthTemperature.class);
            if (healthTemperature != null) {
                SyncV3CallBack.onGetHealthTemperature(healthTemperature);
                return;
            }
            str = "[SYNC_DATA] handleTemperatureData, TemperatureData is null";
        }
        LogTool.b(com.ido.ble.logs.a.f12307a, str);
    }

    public static boolean isSyncV3Type(int i) {
        if (i == 18 || i == 19) {
            return true;
        }
        switch (i) {
            case com.veryfit.multi.nativeprotocol.b.v4 /* 7001 */:
            case 7002:
            case 7003:
            case 7004:
            case 7005:
            case 7006:
            case com.veryfit.multi.nativeprotocol.b.A4 /* 7007 */:
            case com.veryfit.multi.nativeprotocol.b.B4 /* 7008 */:
            case com.veryfit.multi.nativeprotocol.b.C4 /* 7009 */:
            case com.veryfit.multi.nativeprotocol.b.D4 /* 7010 */:
            case com.veryfit.multi.nativeprotocol.b.E4 /* 7011 */:
            case com.veryfit.multi.nativeprotocol.b.F4 /* 7012 */:
            case com.veryfit.multi.nativeprotocol.b.G4 /* 7013 */:
                return true;
            default:
                return false;
        }
    }

    private static void saveHealthHRSecondData(byte[] bArr) {
        String d = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(d)) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] saveHealthHRSecondData, jsonString is null");
            return;
        }
        HealthHeartRateSecond healthHeartRateSecond = (HealthHeartRateSecond) com.ido.ble.common.k.c(d, HealthHeartRateSecond.class);
        if (healthHeartRateSecond == null) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] saveHealthHRSecondData, healthHeartRateSecond is null");
        } else if (healthHeartRateSecond.getYear() == 0) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] saveHealthHRSecondData, year is 0");
        } else {
            List<HealthHeartRateSecondItem> list = healthHeartRateSecond.items;
            if (list == null || list.size() == 0) {
                LogTool.b(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] saveHealthHRSecondData, item is null or size is 0");
                return;
            }
            LogTool.d(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] saveHealthHRSecondData, jsonData=" + d);
            healthHeartRateSecond.setDate(TimeUtil.getDate(healthHeartRateSecond.getYear(), healthHeartRateSecond.getMonth(), healthHeartRateSecond.getDay()));
            if (CustomConfig.getConfig().isSaveDeviceDataToDB()) {
                com.ido.ble.f.a.b.d().a(healthHeartRateSecond);
            }
            SyncV3CallBack.onGetHealthHeartRateSecondData(healthHeartRateSecond, false);
        }
    }

    private static void saveHealthPressureData(byte[] bArr) {
        String d = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(d)) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] saveHealthPressureData, jsonString is null");
            return;
        }
        HealthPressureWrapper healthPressureWrapper = (HealthPressureWrapper) com.ido.ble.common.k.c(d, HealthPressureWrapper.class);
        if (healthPressureWrapper == null) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] saveHealthPressureData, healthSpO2Wrapper is null");
        } else if (healthPressureWrapper.getYear() == 0) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] saveHealthPressureData, year is 0");
        } else {
            LogTool.d(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] saveHealthPressureData, jsonData=" + d);
            Date date = TimeUtil.getDate(healthPressureWrapper.getYear(), healthPressureWrapper.getMonth(), healthPressureWrapper.getDay());
            healthPressureWrapper.setDate(date);
            if (CustomConfig.getConfig().isSaveDeviceDataToDB()) {
                com.ido.ble.f.a.b.d().a(healthPressureWrapper);
            }
            List<HealthPressureItem> list = healthPressureWrapper.items;
            if (list == null || list.size() == 0) {
                SyncV3CallBack.onGetHealthPressureData(healthPressureWrapper, null, true);
                LogTool.b(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] saveHealthPressureData, item size is 0");
                return;
            }
            for (HealthPressureItem healthPressureItem : healthPressureWrapper.items) {
                healthPressureItem.setYear(healthPressureWrapper.getYear());
                healthPressureItem.setMonth(healthPressureWrapper.getMonth());
                healthPressureItem.setDay(healthPressureWrapper.getDay());
                healthPressureItem.setDate(date);
            }
            if (CustomConfig.getConfig().isSaveDeviceDataToDB()) {
                com.ido.ble.f.a.b.d().c(healthPressureWrapper.items);
            }
            SyncV3CallBack.onGetHealthPressureData(healthPressureWrapper, healthPressureWrapper.items, true);
        }
    }

    private static void saveHealthSpO2Data(byte[] bArr) {
        String d = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(d)) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] saveHealthSpO2Data, jsonString is null");
            return;
        }
        HealthSpO2Wrapper healthSpO2Wrapper = (HealthSpO2Wrapper) com.ido.ble.common.k.c(d, HealthSpO2Wrapper.class);
        if (healthSpO2Wrapper == null) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] saveHealthSpO2Data, healthSpO2Wrapper is null");
        } else if (healthSpO2Wrapper.getYear() == 0) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] saveHealthSpO2Data, year is 0");
        } else {
            LogTool.d(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] saveHealthSpO2Data, jsonData=" + d);
            Date date = TimeUtil.getDate(healthSpO2Wrapper.getYear(), healthSpO2Wrapper.getMonth(), healthSpO2Wrapper.getDay());
            healthSpO2Wrapper.setDate(date);
            if (CustomConfig.getConfig().isSaveDeviceDataToDB()) {
                com.ido.ble.f.a.b.d().a(healthSpO2Wrapper);
            }
            List<HealthSpO2Item> list = healthSpO2Wrapper.items;
            if (list == null || list.size() == 0) {
                SyncV3CallBack.onGetHealthSpO2Data(healthSpO2Wrapper, null, true);
                LogTool.b(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] saveHealthSpO2Data, item size is 0");
                return;
            }
            for (HealthSpO2Item healthSpO2Item : healthSpO2Wrapper.items) {
                healthSpO2Item.setYear(healthSpO2Wrapper.getYear());
                healthSpO2Item.setMonth(healthSpO2Wrapper.getMonth());
                healthSpO2Item.setDay(healthSpO2Wrapper.getDay());
                healthSpO2Item.setDate(date);
            }
            if (CustomConfig.getConfig().isSaveDeviceDataToDB()) {
                com.ido.ble.f.a.b.d().e(healthSpO2Wrapper.items);
            }
            SyncV3CallBack.onGetHealthSpO2Data(healthSpO2Wrapper, healthSpO2Wrapper.items, true);
        }
    }

    private static void saveHealthSwimmingData(byte[] bArr) {
        String str;
        String d = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(d)) {
            str = "[SYNC_DATA] saveHealthSwimmingData, jsonString is null";
        } else {
            HealthSwimming healthSwimming = (HealthSwimming) com.ido.ble.common.k.c(d, HealthSwimming.class);
            if (healthSwimming == null) {
                str = "[SYNC_DATA] saveHealthSwimmingData, healthSwimming is null";
            } else if (healthSwimming.getYear() != 0) {
                LogTool.d(com.ido.ble.logs.a.f12307a, "[SYNC_DATA] saveHealthSwimmingData, jsonData=" + d);
                healthSwimming.setDate(TimeUtil.getDate(healthSwimming.getYear(), healthSwimming.getMonth(), healthSwimming.getDay(), healthSwimming.getHour(), healthSwimming.getMinute(), healthSwimming.getSecond()));
                if (CustomConfig.getConfig().isSaveDeviceDataToDB()) {
                    com.ido.ble.f.a.b.d().a(healthSwimming);
                }
                SyncV3CallBack.onGetHealthSwimmingData(healthSwimming);
                return;
            } else {
                str = "[SYNC_DATA] saveHealthSwimmingData, year is 0";
            }
        }
        LogTool.b(com.ido.ble.logs.a.f12307a, str);
    }
}
