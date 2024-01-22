package com.htsmart.wristband2.a.e;

import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.htsmart.wristband2.WristbandApplication;
import com.htsmart.wristband2.bean.BatteryStatus;
import com.htsmart.wristband2.bean.DialUiInfo;
import com.htsmart.wristband2.bean.ExerciseTarget;
import com.htsmart.wristband2.bean.HealthyDataResult;
import com.htsmart.wristband2.bean.PhotovoltaicStation;
import com.htsmart.wristband2.bean.WristbandAlarm;
import com.htsmart.wristband2.bean.WristbandConfig;
import com.htsmart.wristband2.bean.WristbandContacts;
import com.htsmart.wristband2.bean.WristbandHabit;
import com.htsmart.wristband2.bean.WristbandNotification;
import com.htsmart.wristband2.bean.WristbandSchedule;
import com.htsmart.wristband2.bean.WristbandVersion;
import com.htsmart.wristband2.bean.assist.AssistInfo;
import com.htsmart.wristband2.bean.config.BloodPressureConfig;
import com.htsmart.wristband2.bean.config.DrinkWaterConfig;
import com.htsmart.wristband2.bean.config.FunctionConfig;
import com.htsmart.wristband2.bean.config.HealthyConfig;
import com.htsmart.wristband2.bean.config.NotDisturbConfig;
import com.htsmart.wristband2.bean.config.NotificationConfig;
import com.htsmart.wristband2.bean.config.PageConfig;
import com.htsmart.wristband2.bean.config.SdkFunction;
import com.htsmart.wristband2.bean.config.SedentaryConfig;
import com.htsmart.wristband2.bean.config.TurnWristLightingConfig;
import com.htsmart.wristband2.bean.config.WarnBloodPressureConfig;
import com.htsmart.wristband2.bean.config.WarnHeartRateConfig;
import com.htsmart.wristband2.bean.config.WomenHealthyConfig;
import com.htsmart.wristband2.bean.data.GameData;
import com.htsmart.wristband2.bean.data.TodayTotalData;
import com.htsmart.wristband2.bean.weather.WeatherForecast;
import com.htsmart.wristband2.bean.weather.WeatherToday;
import com.htsmart.wristband2.exceptions.PacketDataFormatException;
import com.htsmart.wristband2.packet.PacketData;
import com.htsmart.wristband2.packet.SyncDataParser;
import com.htsmart.wristband2.utils.BytesUtil;
import com.htsmart.wristband2.utils.WristbandLog;
import com.realsil.sdk.dfu.DfuException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
/* loaded from: classes11.dex */
public class b {
    public static PacketData A() {
        return new PacketData((byte) 2, (byte) 71);
    }

    public static PacketData B() {
        return new PacketData((byte) 2, (byte) 32);
    }

    public static PacketData C() {
        return new PacketData((byte) 2, (byte) 16);
    }

    public static PacketData D() {
        return new PacketData((byte) 2, (byte) 64);
    }

    public static PacketData E() {
        return new PacketData((byte) 2, (byte) 4);
    }

    public static PacketData F() {
        return new PacketData((byte) 2, (byte) 97);
    }

    public static PacketData G() {
        return new PacketData((byte) 2, (byte) 28);
    }

    public static PacketData H() {
        return new PacketData((byte) 2, (byte) 48);
    }

    public static PacketData I() {
        return new PacketData((byte) 2, (byte) 83);
    }

    public static PacketData J() {
        return new PacketData((byte) 2, (byte) 68);
    }

    public static PacketData K() {
        return new PacketData((byte) 2, (byte) 42);
    }

    public static PacketData L() {
        return new PacketData((byte) 2, (byte) 56);
    }

    public static PacketData M() {
        return new PacketData((byte) 1, (byte) 2);
    }

    public static PacketData N() {
        return new PacketData((byte) 2, (byte) 26);
    }

    public static PacketData O() {
        return new PacketData((byte) 2, (byte) 36);
    }

    public static PacketData P() {
        return new PacketData((byte) 5, (byte) 67);
    }

    public static PacketData Q() {
        return new PacketData((byte) 2, com.htsmart.wristband2.a.a.a.c1);
    }

    public static PacketData R() {
        return new PacketData((byte) 2, (byte) 20);
    }

    public static PacketData S() {
        return new PacketData((byte) 2, (byte) 23);
    }

    public static PacketData T() {
        return new PacketData((byte) 2, (byte) 108);
    }

    public static PacketData U() {
        return new PacketData((byte) 2, (byte) 39);
    }

    public static PacketData V() {
        return new PacketData((byte) 5, (byte) 6);
    }

    public static PacketData W() {
        return new PacketData((byte) 5, (byte) 34);
    }

    public static PacketData X() {
        return new PacketData((byte) 2, (byte) 45);
    }

    public static PacketData Y() {
        return new PacketData((byte) 3, (byte) 22);
    }

    public static PacketData Z() {
        return new PacketData((byte) 2, (byte) 75);
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x010b A[Catch: Exception -> 0x01d8, TryCatch #1 {Exception -> 0x01d8, blocks: (B:27:0x00b6, B:29:0x00c8, B:32:0x00cf, B:34:0x0105, B:36:0x010b, B:37:0x0112, B:39:0x0118, B:41:0x011c, B:44:0x0126, B:45:0x012d, B:47:0x013e, B:49:0x0146, B:51:0x014a, B:33:0x00e2), top: B:74:0x00b6 }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0118 A[Catch: Exception -> 0x01d8, TryCatch #1 {Exception -> 0x01d8, blocks: (B:27:0x00b6, B:29:0x00c8, B:32:0x00cf, B:34:0x0105, B:36:0x010b, B:37:0x0112, B:39:0x0118, B:41:0x011c, B:44:0x0126, B:45:0x012d, B:47:0x013e, B:49:0x0146, B:51:0x014a, B:33:0x00e2), top: B:74:0x00b6 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x013e A[Catch: Exception -> 0x01d8, TryCatch #1 {Exception -> 0x01d8, blocks: (B:27:0x00b6, B:29:0x00c8, B:32:0x00cf, B:34:0x0105, B:36:0x010b, B:37:0x0112, B:39:0x0118, B:41:0x011c, B:44:0x0126, B:45:0x012d, B:47:0x013e, B:49:0x0146, B:51:0x014a, B:33:0x00e2), top: B:74:0x00b6 }] */
    /* JADX WARN: Removed duplicated region for block: B:59:0x01c3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.htsmart.wristband2.bean.DialBinInfo a(com.htsmart.wristband2.packet.PacketData r20, com.htsmart.wristband2.bean.WristbandVersion r21) throws com.htsmart.wristband2.exceptions.PacketDataFormatException {
        /*
            Method dump skipped, instructions count: 493
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.htsmart.wristband2.a.e.b.a(com.htsmart.wristband2.packet.PacketData, com.htsmart.wristband2.bean.WristbandVersion):com.htsmart.wristband2.bean.DialBinInfo");
    }

    public static AssistInfo a(PacketData packetData) throws PacketDataFormatException {
        byte[] keyData = packetData.getKeyData();
        if (keyData != null) {
            AssistInfo assistInfo = new AssistInfo();
            int i = 0;
            while (i < keyData.length) {
                int i2 = i + 1;
                int i3 = keyData[i] & 255;
                if (i3 == 0 || i2 + i3 > keyData.length) {
                    WristbandLog.w("parserAssistInfo error length", packetData.toString());
                    return assistInfo;
                }
                int i4 = i2 + 1;
                switch (keyData[i2]) {
                    case 1:
                        if (i3 < 10) {
                            break;
                        } else {
                            AssistInfo.TranslationVersion translationVersion = new AssistInfo.TranslationVersion();
                            translationVersion.customerNum = BytesUtil.bytes2Int(keyData, i4, 2, true);
                            translationVersion.customerTranslationNum = keyData[i4 + 2] & 255;
                            translationVersion.translationVersion = BytesUtil.bytes2Int(keyData, i4 + 3, 2, true);
                            byte[] bArr = new byte[4];
                            System.arraycopy(keyData, i4 + 5, bArr, 0, 4);
                            translationVersion.compileTime = BytesUtil.bytes2HexStr(bArr).replaceAll(HexStringBuilder.DEFAULT_SEPARATOR, "");
                            assistInfo.translationVersion = translationVersion;
                            break;
                        }
                    case 2:
                        if (i3 < 3) {
                            break;
                        } else {
                            assistInfo.flashSize = keyData[i4] & 255;
                            assistInfo.flashCodeSize = keyData[i4 + 1] & 255;
                            break;
                        }
                    case 3:
                        if (i3 < 2) {
                            break;
                        } else {
                            assistInfo.fontType = keyData[i4] & 255;
                            break;
                        }
                    case 4:
                        if (i3 < 7) {
                            break;
                        } else {
                            AssistInfo.TPInfo tPInfo = new AssistInfo.TPInfo();
                            tPInfo.type = keyData[i4] & 255;
                            byte b = keyData[i4 + 1];
                            tPInfo.isCOB = (b & 1) > 0;
                            tPInfo.isSupportTurnOn = (b & 2) > 0;
                            tPInfo.isSupportWakeup = (b & 4) > 0;
                            tPInfo.isSupportUpgrade = (b & 8) > 0;
                            tPInfo.isSupportTestPage = (b & 16) > 0;
                            tPInfo.isSupportRD = (b & 32) > 0;
                            byte[] bArr2 = new byte[4];
                            System.arraycopy(keyData, i4 + 2, bArr2, 0, 4);
                            tPInfo.firmwareVersion = BytesUtil.bytes2HexStr(bArr2).replaceAll(HexStringBuilder.DEFAULT_SEPARATOR, "");
                            assistInfo.tpInfo = tPInfo;
                            break;
                        }
                    case 5:
                        if (i3 < 6) {
                            break;
                        } else {
                            assistInfo.stepAlgorithm = keyData[i4] & 255;
                            byte[] bArr3 = new byte[4];
                            System.arraycopy(keyData, i4 + 1, bArr3, 0, 4);
                            assistInfo.stepAlgorithmVersion = BytesUtil.bytes2HexStr(bArr3).replaceAll(HexStringBuilder.DEFAULT_SEPARATOR, "");
                            break;
                        }
                    case 6:
                        if (i3 < 2) {
                            break;
                        } else {
                            assistInfo.raiseWristAlgorithm = keyData[i4] & 255;
                            break;
                        }
                    case 7:
                        if (i3 < 4) {
                            break;
                        } else {
                            assistInfo.caloriesAlgorithm = keyData[i4] & 255;
                            assistInfo.isCaloriesOptimizeSport = (keyData[i4 + 1] & 255) > 0;
                            assistInfo.caloriesOptimizeSportVersion = keyData[i4 + 2] & 255;
                            break;
                        }
                    case 8:
                        if (i3 < 3) {
                            break;
                        } else {
                            assistInfo.sleepAlgorithm = keyData[i4] & 255;
                            assistInfo.isSleepSupportMultiSegment = (keyData[i4 + 1] & 255) > 0;
                            break;
                        }
                    case 9:
                        if (i3 < 9) {
                            break;
                        } else {
                            AssistInfo.HeartRateInfo heartRateInfo = new AssistInfo.HeartRateInfo();
                            heartRateInfo.type = keyData[i4] & 255;
                            byte[] bArr4 = new byte[4];
                            System.arraycopy(keyData, i4 + 1, bArr4, 0, 4);
                            heartRateInfo.algorithmVersion = BytesUtil.bytes2HexStr(bArr4).replaceAll(HexStringBuilder.DEFAULT_SEPARATOR, "");
                            heartRateInfo.switchType = keyData[i4 + 5] & 255;
                            heartRateInfo.isSupportInterruptPin = (keyData[i4 + 6] & 255) > 0;
                            heartRateInfo.isSupportHotSwap = (keyData[i4 + 7] & 255) > 0;
                            assistInfo.heartRateInfo = heartRateInfo;
                            break;
                        }
                    case 10:
                        if (i3 < 2) {
                            break;
                        } else {
                            assistInfo.gsensorType = keyData[i4] & 255;
                            break;
                        }
                    case 11:
                        if (i3 < 8) {
                            break;
                        } else {
                            AssistInfo.ChargingInfo chargingInfo = new AssistInfo.ChargingInfo();
                            chargingInfo.type = keyData[i4] & 255;
                            chargingInfo.logicalVersion = keyData[i4 + 1] & 255;
                            chargingInfo.batteryMaxVoltage = keyData[i4 + 2] & 255;
                            chargingInfo.curveType = keyData[i4 + 3] & 255;
                            chargingInfo.batteryCurrentVoltage = BytesUtil.bytes2Int(keyData, i4 + 4, 2, true);
                            chargingInfo.batteryCurrentPercentage = keyData[i4 + 6] & 255;
                            assistInfo.chargingInfo = chargingInfo;
                            break;
                        }
                    case 12:
                        if (i3 < 2) {
                            break;
                        } else {
                            assistInfo.screenTPHotSwap = keyData[i4] & 255;
                            break;
                        }
                    case 13:
                        if (i3 < 2) {
                            break;
                        } else {
                            assistInfo.bloodPressureAlgorithm = keyData[i4] & 255;
                            break;
                        }
                    case 14:
                        if (i3 < 2) {
                            break;
                        } else {
                            assistInfo.antennaSignalStrength = keyData[i4] & 255;
                            break;
                        }
                    case 15:
                        if (i3 < 10) {
                            break;
                        } else {
                            AssistInfo.ScreenInfo screenInfo = new AssistInfo.ScreenInfo();
                            screenInfo.type = BytesUtil.bytes2Int(keyData, i4, 2, true);
                            screenInfo.driveNum = BytesUtil.bytes2Int(keyData, i4 + 2, 2, true);
                            screenInfo.isSupportTE = (keyData[i4 + 4] & 255) > 0;
                            screenInfo.rdPinMultiplexed = keyData[i4 + 5] & 255;
                            screenInfo.defaultBrightness = keyData[i4 + 6] & 255;
                            screenInfo.defaultBrightScreenTime = keyData[i4 + 7] & 255;
                            screenInfo.defaultRaiseWristBrightTime = keyData[i4 + 8] & 255;
                            assistInfo.screenInfo = screenInfo;
                            break;
                        }
                    case 16:
                        if (i3 < 4) {
                            break;
                        } else {
                            assistInfo.temperatureType = keyData[i4] & 255;
                            assistInfo.temperatureAlgorithm = keyData[i4 + 1] & 255;
                            assistInfo.temperatureSwitch = keyData[i4 + 2] & 255;
                            break;
                        }
                    case 17:
                        if (i3 < 3) {
                            break;
                        } else {
                            assistInfo.ecgType = keyData[i4] & 255;
                            assistInfo.ecgSwitch = keyData[i4 + 1] & 255;
                            break;
                        }
                    case 18:
                        if (i3 >= 2) {
                            assistInfo.isSupportWeChat = (keyData[i4] & 255) > 0;
                            break;
                        } else {
                            break;
                        }
                    case 19:
                        if (i3 >= 2) {
                            assistInfo.isSupportHeartRate = (keyData[i4] & 255) > 0;
                            break;
                        } else {
                            break;
                        }
                    case 20:
                        if (i3 >= 2) {
                            assistInfo.isSupportRebindRestoreData = (keyData[i4] & 255) > 0;
                            break;
                        } else {
                            break;
                        }
                    case 21:
                        if (i3 >= 2) {
                            assistInfo.isSupportLowBatteryReminder = (keyData[i4] & 255) > 0;
                            break;
                        } else {
                            break;
                        }
                }
                i = (i3 - 1) + i4;
            }
            return assistInfo;
        }
        throw new PacketDataFormatException("parserAssistInfo", packetData);
    }

    public static GameData a(byte[] bArr, int i, @NonNull GregorianCalendar gregorianCalendar) {
        GameData gameData = new GameData();
        gameData.setType(bArr[i] & 255);
        gameData.setTimeStamp(SyncDataParser.parser4BytesTimeStamp(bArr, i + 1, gregorianCalendar));
        gameData.setDuration(BytesUtil.bytes2Int(bArr, i + 5, 3, true));
        gameData.setScore(BytesUtil.bytes2Int(bArr, i + 8, 3, true));
        gameData.setLevel(bArr[i + 11] & 255);
        return gameData;
    }

    public static PacketData a() {
        return new PacketData((byte) 2, (byte) 65);
    }

    public static PacketData a(byte b) {
        return new PacketData((byte) 2, (byte) 58, new byte[]{b, 0, 0, 0});
    }

    public static PacketData a(int i, int i2, int i3) {
        PacketData packetData = new PacketData((byte) 2, (byte) 6);
        byte[] int2Bytes = BytesUtil.int2Bytes(i, true);
        byte[] int2Bytes2 = BytesUtil.int2Bytes(i2, true);
        byte[] int2Bytes3 = BytesUtil.int2Bytes(i3, true);
        byte[] bArr = new byte[12];
        System.arraycopy(int2Bytes, 0, bArr, 0, 4);
        System.arraycopy(int2Bytes2, 0, bArr, 4, 4);
        System.arraycopy(int2Bytes3, 0, bArr, 8, 4);
        packetData.setKeyData(bArr);
        return packetData;
    }

    public static PacketData a(int i, int i2, boolean z) {
        PacketData packetData = new PacketData((byte) 5, (byte) 35);
        byte[] bArr = new byte[4];
        if (z) {
            bArr[0] = (byte) ((i >> 8) & 255);
            bArr[1] = (byte) (i & 255);
        }
        if (i == 16) {
            bArr[2] = 1;
            bArr[3] = 1;
        } else {
            bArr[2] = 5;
            bArr[3] = (byte) i2;
        }
        packetData.setKeyData(bArr);
        return packetData;
    }

    public static PacketData a(@NonNull ExerciseTarget exerciseTarget) {
        PacketData packetData = new PacketData((byte) 2, (byte) 6);
        byte[] int2Bytes = BytesUtil.int2Bytes(exerciseTarget.getStep(), true);
        byte[] int2Bytes2 = BytesUtil.int2Bytes(exerciseTarget.getDistance(), true);
        byte[] int2Bytes3 = BytesUtil.int2Bytes(exerciseTarget.getCalorie(), true);
        byte[] d = d(exerciseTarget.getTimestamp());
        byte[] bArr = new byte[16];
        System.arraycopy(int2Bytes, 0, bArr, 0, 4);
        System.arraycopy(int2Bytes2, 0, bArr, 4, 4);
        System.arraycopy(int2Bytes3, 0, bArr, 8, 4);
        System.arraycopy(d, 0, bArr, 12, 4);
        packetData.setKeyData(bArr);
        return packetData;
    }

    public static PacketData a(WristbandNotification wristbandNotification, boolean z) {
        byte[] bArr;
        PacketData packetData = new PacketData((byte) 4, wristbandNotification.getType());
        byte[] bytes = TextUtils.isEmpty(wristbandNotification.getName()) ? null : wristbandNotification.getName().getBytes();
        byte[] bytes2 = TextUtils.isEmpty(wristbandNotification.getContent()) ? null : wristbandNotification.getContent().getBytes();
        int length = bytes2 == null ? 0 : bytes2.length;
        if (z) {
            int length2 = bytes == null ? 0 : bytes.length;
            if (length2 > 64) {
                length2 = 64;
            }
            int i = (242 - length2) - 2;
            if (length > i) {
                length = i;
            }
            int i2 = length2 + 1;
            byte[] bArr2 = new byte[i2 + 2 + length];
            bArr2[0] = (byte) length2;
            if (bytes != null) {
                System.arraycopy(bytes, 0, bArr2, 1, length2);
            }
            bArr2[i2] = (byte) ((length >> 8) & 255);
            bArr2[length2 + 2] = (byte) (length & 255);
            if (bytes2 != null) {
                System.arraycopy(bytes2, 0, bArr2, length2 + 3, length);
            }
            bArr = bArr2;
        } else {
            if (length > 221) {
                length = 221;
            }
            bArr = new byte[length + 22];
            if (bytes != null) {
                int length3 = bytes.length > 20 ? 20 : bytes.length;
                System.arraycopy(bytes, 0, bArr, 0, length3);
                if (length3 < 20) {
                    while (length3 < 20) {
                        bArr[length3] = 32;
                        length3++;
                    }
                }
            }
            bArr[20] = (byte) ((length >> 8) & 255);
            bArr[21] = (byte) (length & 255);
            if (bytes2 != null) {
                System.arraycopy(bytes2, 0, bArr, 22, length);
            }
        }
        packetData.setKeyData(bArr);
        return packetData;
    }

    public static PacketData a(BloodPressureConfig bloodPressureConfig) {
        return new PacketData((byte) 2, (byte) 46, bloodPressureConfig.getBytes());
    }

    public static PacketData a(DrinkWaterConfig drinkWaterConfig) {
        return new PacketData((byte) 2, (byte) 40, drinkWaterConfig.getBytes());
    }

    public static PacketData a(FunctionConfig functionConfig) {
        return new PacketData((byte) 2, (byte) 24, functionConfig.getBytes());
    }

    public static PacketData a(HealthyConfig healthyConfig, boolean z) {
        byte[] bArr;
        if (z) {
            bArr = healthyConfig.getBytes();
        } else {
            byte[] bArr2 = new byte[5];
            System.arraycopy(healthyConfig.getBytes(), 0, bArr2, 0, 5);
            bArr = bArr2;
        }
        return new PacketData((byte) 2, (byte) 34, bArr);
    }

    public static PacketData a(NotDisturbConfig notDisturbConfig) {
        return new PacketData((byte) 2, com.htsmart.wristband2.a.a.a.a1, notDisturbConfig.getBytes());
    }

    public static PacketData a(NotificationConfig notificationConfig) {
        return new PacketData((byte) 2, (byte) 18, notificationConfig.getBytes());
    }

    public static PacketData a(PageConfig pageConfig) {
        return new PacketData((byte) 2, (byte) 21, pageConfig.getBytes());
    }

    public static PacketData a(SedentaryConfig sedentaryConfig, boolean z) {
        byte[] bArr;
        if (z) {
            bArr = sedentaryConfig.getBytes();
        } else {
            byte[] bArr2 = new byte[5];
            System.arraycopy(sedentaryConfig.getBytes(), 0, bArr2, 0, 5);
            bArr = bArr2;
        }
        return new PacketData((byte) 2, (byte) 37, bArr);
    }

    public static PacketData a(TurnWristLightingConfig turnWristLightingConfig) {
        return new PacketData((byte) 2, (byte) 43, turnWristLightingConfig.getBytes());
    }

    public static PacketData a(WarnBloodPressureConfig warnBloodPressureConfig) {
        return new PacketData((byte) 2, (byte) 73, warnBloodPressureConfig.getBytes());
    }

    public static PacketData a(WarnHeartRateConfig warnHeartRateConfig) {
        return new PacketData((byte) 2, com.htsmart.wristband2.a.a.a.U0, warnHeartRateConfig.getBytes());
    }

    public static PacketData a(WomenHealthyConfig womenHealthyConfig) {
        return new PacketData((byte) 2, (byte) 87, womenHealthyConfig.getBytes());
    }

    public static PacketData a(WeatherToday weatherToday, String str) {
        byte[] bArr;
        int i = 64;
        if (str == null || str.length() <= 0) {
            bArr = null;
            i = 0;
        } else {
            bArr = str.getBytes();
            int length = bArr.length;
            if (length <= 64) {
                i = length;
            }
        }
        int i2 = i + 5;
        byte[] bArr2 = new byte[i2 + 6];
        bArr2[0] = (byte) weatherToday.getCurrentTemperature();
        bArr2[1] = (byte) weatherToday.getLowTemperature();
        bArr2[2] = (byte) weatherToday.getHighTemperature();
        bArr2[3] = (byte) weatherToday.getWeatherCode();
        bArr2[4] = (byte) i;
        if (i > 0) {
            System.arraycopy(bArr, 0, bArr2, 5, i);
        }
        byte[] int2Bytes = BytesUtil.int2Bytes(weatherToday.getPressure(), true);
        int i3 = i2 + 1;
        bArr2[i2] = int2Bytes[0];
        int i4 = i3 + 1;
        bArr2[i3] = int2Bytes[1];
        int i5 = i4 + 1;
        bArr2[i4] = int2Bytes[2];
        int i6 = i5 + 1;
        bArr2[i5] = int2Bytes[3];
        bArr2[i6] = (byte) weatherToday.getWindForce();
        bArr2[i6 + 1] = (byte) weatherToday.getVisibility();
        return new PacketData((byte) 2, (byte) 54, bArr2);
    }

    public static PacketData a(String str, long j, @NonNull WeatherToday weatherToday, @Nullable List<WeatherForecast> list, boolean z) {
        if (z) {
            byte[] bArr = null;
            int i = 64;
            if (str == null || str.length() <= 0) {
                i = 0;
            } else {
                bArr = str.getBytes();
                int length = bArr.length;
                if (length <= 64) {
                    i = length;
                }
            }
            int size = list != null ? list.size() : 0;
            int i2 = i + 5;
            byte[] bArr2 = new byte[i2 + 4 + 1 + (size * 3) + 6];
            bArr2[0] = (byte) weatherToday.getCurrentTemperature();
            bArr2[1] = (byte) weatherToday.getLowTemperature();
            bArr2[2] = (byte) weatherToday.getHighTemperature();
            bArr2[3] = (byte) weatherToday.getWeatherCode();
            bArr2[4] = (byte) i;
            if (i > 0) {
                System.arraycopy(bArr, 0, bArr2, 5, i);
            }
            byte[] d = d(j);
            System.arraycopy(d, 0, bArr2, i2, d.length);
            int length2 = i2 + d.length;
            int i3 = length2 + 1;
            bArr2[length2] = (byte) size;
            if (size > 0) {
                for (WeatherForecast weatherForecast : list) {
                    int i4 = i3 + 1;
                    bArr2[i3] = (byte) weatherForecast.getLowTemperature();
                    int i5 = i4 + 1;
                    bArr2[i4] = (byte) weatherForecast.getHighTemperature();
                    bArr2[i5] = (byte) weatherForecast.getWeatherCode();
                    i3 = i5 + 1;
                }
            }
            byte[] int2Bytes = BytesUtil.int2Bytes(weatherToday.getPressure(), true);
            int i6 = i3 + 1;
            bArr2[i3] = int2Bytes[0];
            int i7 = i6 + 1;
            bArr2[i6] = int2Bytes[1];
            int i8 = i7 + 1;
            bArr2[i7] = int2Bytes[2];
            int i9 = i8 + 1;
            bArr2[i8] = int2Bytes[3];
            bArr2[i9] = (byte) weatherToday.getWindForce();
            bArr2[i9 + 1] = (byte) weatherToday.getVisibility();
            return new PacketData((byte) 2, (byte) 54, bArr2);
        }
        return a(weatherToday, str);
    }

    public static PacketData a(List<WristbandAlarm> list) {
        PacketData packetData = new PacketData((byte) 2, (byte) 2);
        if (list != null && list.size() != 0) {
            ArrayList<byte[]> arrayList = new ArrayList(list.size());
            int i = 0;
            for (int i2 = 0; i2 < list.size(); i2++) {
                byte[] e = e(list.get(i2));
                i += e.length;
                arrayList.add(e);
            }
            byte[] bArr = new byte[i];
            int i3 = 0;
            for (byte[] bArr2 : arrayList) {
                System.arraycopy(bArr2, 0, bArr, i3, bArr2.length);
                i3 += bArr2.length;
            }
            packetData.setKeyData(bArr);
        }
        return packetData;
    }

    public static PacketData a(boolean z) {
        return new PacketData((byte) 5, (byte) 32, z ? new byte[]{0} : new byte[]{1});
    }

    public static PacketData a(boolean z, int i, float f, float f2) {
        PacketData packetData = new PacketData((byte) 2, (byte) 5);
        byte[] bArr = new byte[4];
        int i2 = ((int) (f * 10.0f)) / 5;
        int i3 = ((int) (f2 * 10.0f)) / 5;
        bArr[0] = (byte) ((z ? 128 : 0) | i);
        bArr[1] = (byte) (i2 >> 1);
        bArr[2] = (byte) ((i2 << 7) | (i3 >> 3));
        bArr[3] = (byte) (i3 << 5);
        packetData.setKeyData(bArr);
        return packetData;
    }

    public static PacketData a(boolean z, String str) {
        PacketData packetData = new PacketData((byte) 3, z ? (byte) 17 : (byte) 19);
        byte[] bArr = new byte[32];
        if (WristbandApplication.UPGRADE_PRODUCTION_TEST) {
            Arrays.fill(bArr, (byte) -1);
            bArr[31] = 0;
            bArr[30] = 0;
            bArr[29] = 0;
        } else {
            bArr[31] = 0;
            bArr[30] = 0;
            bArr[29] = 0;
            byte[] bytes = str.getBytes();
            System.arraycopy(bytes, 0, bArr, 0, bytes.length <= 28 ? bytes.length : 28);
        }
        packetData.setKeyData(bArr);
        return packetData;
    }

    public static PacketData a0() {
        return new PacketData((byte) 2, com.htsmart.wristband2.a.a.a.W0);
    }

    public static DialUiInfo b(PacketData packetData) throws PacketDataFormatException {
        byte[] keyData = packetData.getKeyData();
        if (keyData == null || keyData.length < 6) {
            throw new PacketDataFormatException("parserDialUiInfo", packetData);
        }
        DialUiInfo dialUiInfo = new DialUiInfo();
        dialUiInfo.setUiNum(BytesUtil.bytes2Int(keyData, 0, 2, true));
        dialUiInfo.setUiSerial(BytesUtil.bytes2Int(keyData, 2, 2, true));
        dialUiInfo.setStyleIndex(BytesUtil.bytes2Int(keyData, 4, 2, true));
        return dialUiInfo;
    }

    public static HealthyDataResult b(PacketData packetData, WristbandVersion wristbandVersion) throws PacketDataFormatException {
        byte[] keyData = packetData.getKeyData();
        if (keyData != null) {
            int i = 4;
            if (keyData.length >= 4) {
                HealthyDataResult healthyDataResult = new HealthyDataResult();
                healthyDataResult.setHeartRate(keyData[0] & 255);
                healthyDataResult.setOxygen(keyData[1] & 255);
                healthyDataResult.setDiastolicPressure(keyData[2] & 255);
                healthyDataResult.setSystolicPressure(keyData[3] & 255);
                if (wristbandVersion.isTemperatureEnabled()) {
                    if (keyData.length < 8) {
                        throw new PacketDataFormatException("HealthyRealTimeOperation", packetData);
                    }
                    healthyDataResult.setTemperatureWrist(BytesUtil.bytes2Short(keyData, 4, 2, true) / 100.0f);
                    healthyDataResult.setTemperatureBody(BytesUtil.bytes2Short(keyData, 6, 2, true) / 100.0f);
                    i = 8;
                }
                if (wristbandVersion.isPressureEnabled()) {
                    if (keyData.length < i + 1) {
                        throw new PacketDataFormatException("HealthyRealTimeOperation", packetData);
                    }
                    healthyDataResult.setPressure(keyData[i] & 255);
                }
                return healthyDataResult;
            }
        }
        throw new PacketDataFormatException("parserResponseLatestHealthy", packetData);
    }

    public static WristbandAlarm b(byte[] bArr) {
        int i = (bArr[0] >> 2) & 63;
        int i2 = ((bArr[0] << 2) & 15) | ((bArr[1] >> 6) & 3);
        int i3 = (bArr[1] >> 1) & 31;
        int i4 = ((bArr[1] << 4) & 16) | ((bArr[2] >> 4) & 15);
        int i5 = ((bArr[2] << 2) & 63) | ((bArr[3] >> 6) & 3);
        int i6 = (bArr[3] >> 3) & 7;
        boolean z = ((bArr[4] >> 7) & 1) == 1;
        int i7 = bArr[4] & Byte.MAX_VALUE;
        String str = bArr.length > 5 ? new String(bArr, 5, bArr.length - 5) : null;
        WristbandAlarm wristbandAlarm = new WristbandAlarm();
        wristbandAlarm.setAlarmId(i6);
        wristbandAlarm.setRepeat(i7);
        wristbandAlarm.setEnable(z);
        wristbandAlarm.setYear(i + 2000);
        wristbandAlarm.setMonth(i2 - 1);
        wristbandAlarm.setDay(i3);
        wristbandAlarm.setHour(i4);
        wristbandAlarm.setMinute(i5);
        wristbandAlarm.setLabel(str);
        return wristbandAlarm;
    }

    public static PacketData b() {
        return new PacketData((byte) 2, (byte) 57);
    }

    public static PacketData b(byte b) {
        return new PacketData((byte) 5, (byte) 1, new byte[]{b});
    }

    public static PacketData b(boolean z) {
        return new PacketData((byte) 3, z ? (byte) 18 : (byte) 20);
    }

    public static List<PacketData> b(List<WristbandContacts> list) {
        byte b;
        ArrayList<byte[]> arrayList = new ArrayList(2);
        byte[] bArr = new byte[DfuException.ERROR_READ_REMOTE_MAC_ADDR];
        if (list != null) {
            int i = 1;
            b = 0;
            for (int i2 = 0; i2 < list.size(); i2++) {
                WristbandContacts wristbandContacts = list.get(i2);
                if (TextUtils.isEmpty(wristbandContacts.getName()) || TextUtils.isEmpty(wristbandContacts.getNumber())) {
                    WristbandLog.w("WristbandContacts format error, name=%s ,number =%s", wristbandContacts.getName(), wristbandContacts.getNumber());
                } else {
                    byte[] bytes = wristbandContacts.getNumber().getBytes();
                    int min = Math.min(bytes.length, 20);
                    byte[] bytes2 = wristbandContacts.getName().getBytes();
                    int min2 = Math.min(bytes2.length, 32);
                    if (i + 3 + min2 + min >= 277) {
                        byte[] bArr2 = new byte[i];
                        System.arraycopy(bArr, 0, bArr2, 0, i);
                        arrayList.add(bArr2);
                        i = 1;
                    }
                    int i3 = i + 1;
                    bArr[i] = b;
                    int i4 = i3 + 1;
                    bArr[i3] = (byte) min;
                    System.arraycopy(bytes, 0, bArr, i4, min);
                    int i5 = i4 + min;
                    int i6 = i5 + 1;
                    bArr[i5] = (byte) min2;
                    System.arraycopy(bytes2, 0, bArr, i6, min2);
                    i = i6 + min2;
                    b = (byte) (b + 1);
                }
                if (i2 == list.size() - 1) {
                    byte[] bArr3 = new byte[i];
                    System.arraycopy(bArr, 0, bArr3, 0, i);
                    arrayList.add(bArr3);
                }
            }
        } else {
            b = 0;
        }
        ArrayList arrayList2 = new ArrayList(2);
        if (arrayList.size() <= 0) {
            PacketData packetData = new PacketData((byte) 2, (byte) 84);
            packetData.setKeyData(new byte[1]);
            arrayList2.add(packetData);
        } else {
            for (byte[] bArr4 : arrayList) {
                PacketData packetData2 = new PacketData((byte) 2, (byte) 84);
                bArr4[0] = b;
                packetData2.setKeyData(bArr4);
                arrayList2.add(packetData2);
            }
        }
        return arrayList2;
    }

    public static PacketData b0() {
        return new PacketData((byte) 2, (byte) 33);
    }

    public static TodayTotalData c(byte[] bArr) {
        TodayTotalData todayTotalData = new TodayTotalData();
        if (bArr != null && bArr.length >= 30) {
            todayTotalData.setStep(BytesUtil.bytes2Int(bArr, 0, 4, true));
            todayTotalData.setDistance(BytesUtil.bytes2Int(bArr, 4, 4, true));
            todayTotalData.setCalorie(BytesUtil.bytes2Int(bArr, 8, 4, true));
            todayTotalData.setDeepSleep(BytesUtil.bytes2Int(bArr, 12, 4, true));
            todayTotalData.setLightSleep(BytesUtil.bytes2Int(bArr, 16, 4, true));
            todayTotalData.setHeartRate(BytesUtil.bytes2Int(bArr, 20, 4, true));
            todayTotalData.setDeltaStep(BytesUtil.bytes2Int(bArr, 24, 2, true));
            todayTotalData.setDeltaDistance(BytesUtil.bytes2Int(bArr, 26, 2, true));
            todayTotalData.setDeltaCalorie(BytesUtil.bytes2Int(bArr, 28, 2, true));
        }
        return todayTotalData;
    }

    public static PacketData c() {
        return new PacketData((byte) 2, (byte) 59);
    }

    public static PacketData c(boolean z) {
        return new PacketData((byte) 7, (byte) 2, z ? new byte[]{0} : new byte[]{1});
    }

    public static List<WristbandAlarm> c(PacketData packetData) throws PacketDataFormatException {
        int i;
        ArrayList arrayList = new ArrayList(8);
        byte[] keyData = packetData.getKeyData();
        if (keyData != 0 && keyData.length > 0) {
            int i2 = 0;
            while (i2 < keyData.length) {
                int i3 = i2 + 1;
                int i4 = keyData[i2];
                if (i4 < 5 || (i = i4 + i3) > keyData.length) {
                    throw new PacketDataFormatException("parserResponseAlarmList", packetData);
                }
                byte[] bArr = new byte[i4];
                System.arraycopy(keyData, i3, bArr, 0, i4);
                arrayList.add(b(bArr));
                i2 = i;
            }
        }
        return arrayList;
    }

    public static List<PacketData> c(List<WristbandHabit> list) {
        ArrayList<byte[]> arrayList = new ArrayList();
        byte[] bArr = new byte[387];
        bArr[0] = (byte) (list == null ? 0 : list.size());
        if (list != null) {
            int i = 1;
            for (int i2 = 0; i2 < list.size(); i2++) {
                byte[] f = f(list.get(i2));
                if (f.length + i >= 387) {
                    byte[] bArr2 = new byte[i];
                    System.arraycopy(bArr, 0, bArr2, 0, i);
                    arrayList.add(bArr2);
                    i = 1;
                }
                System.arraycopy(f, 0, bArr, i, f.length);
                i += f.length;
                if (i2 == list.size() - 1) {
                    byte[] bArr3 = new byte[i];
                    System.arraycopy(bArr, 0, bArr3, 0, i);
                    arrayList.add(bArr3);
                }
            }
        }
        ArrayList arrayList2 = new ArrayList();
        if (arrayList.size() <= 0) {
            arrayList2.add(new PacketData((byte) 2, (byte) -94, new byte[1]));
        } else {
            for (byte[] bArr4 : arrayList) {
                arrayList2.add(new PacketData((byte) 2, (byte) -94, bArr4));
            }
        }
        return arrayList2;
    }

    public static byte[] c(int i, PhotovoltaicStation photovoltaicStation) {
        byte[] bArr = new byte[64];
        bArr[0] = (byte) (i << 2);
        if (photovoltaicStation.isValid()) {
            bArr[0] = (byte) (bArr[0] | 2);
        }
        if (!photovoltaicStation.isFault()) {
            bArr[0] = (byte) (bArr[0] | 1);
        }
        int todayPowerGeneration = (int) (photovoltaicStation.getTodayPowerGeneration() * 10.0f);
        bArr[1] = (byte) ((todayPowerGeneration >> 8) & 255);
        bArr[2] = (byte) (todayPowerGeneration & 255);
        int totalPowerGeneration = (int) (photovoltaicStation.getTotalPowerGeneration() * 10.0f);
        bArr[3] = (byte) ((totalPowerGeneration >> 24) & 255);
        bArr[4] = (byte) ((totalPowerGeneration >> 16) & 255);
        bArr[5] = (byte) ((totalPowerGeneration >> 8) & 255);
        bArr[6] = (byte) (totalPowerGeneration & 255);
        int equivalentPlantTrees = photovoltaicStation.getEquivalentPlantTrees();
        bArr[7] = (byte) ((equivalentPlantTrees >> 24) & 255);
        bArr[8] = (byte) ((equivalentPlantTrees >> 16) & 255);
        bArr[9] = (byte) ((equivalentPlantTrees >> 8) & 255);
        bArr[10] = (byte) (equivalentPlantTrees & 255);
        byte[] d = d(photovoltaicStation.getUpdateTime());
        bArr[11] = d[0];
        bArr[12] = d[1];
        bArr[13] = d[2];
        bArr[14] = d[3];
        byte[] bytes = !TextUtils.isEmpty(photovoltaicStation.getMasterName()) ? photovoltaicStation.getMasterName().getBytes() : null;
        int min = Math.min(12, bytes == null ? 0 : bytes.length);
        byte[] bytes2 = TextUtils.isEmpty(photovoltaicStation.getStationName()) ? null : photovoltaicStation.getStationName().getBytes();
        int min2 = Math.min(36, bytes2 == null ? 0 : bytes2.length);
        bArr[15] = (byte) ((min << 5) | min2);
        if (min > 0) {
            System.arraycopy(bytes, 0, bArr, 16, min);
        }
        if (min2 > 0) {
            System.arraycopy(bytes2, 0, bArr, 28, min2);
        }
        return bArr;
    }

    public static PacketData c0() {
        return new PacketData((byte) 2, (byte) 17);
    }

    public static BatteryStatus d(PacketData packetData) throws PacketDataFormatException {
        byte[] keyData = packetData.getKeyData();
        if (keyData == null || keyData.length < 3) {
            throw new PacketDataFormatException("parserResponseBattery", packetData);
        }
        return new BatteryStatus(keyData[0] == 1, keyData[1], keyData[2]);
    }

    public static PacketData d() {
        return new PacketData((byte) 2, (byte) 61);
    }

    public static List<PacketData> d(@Nullable List<PhotovoltaicStation> list) {
        ArrayList<byte[]> arrayList = new ArrayList();
        byte[] bArr = new byte[387];
        int min = Math.min(6, list == null ? 0 : list.size());
        bArr[0] = (byte) min;
        if (list != null) {
            int i = 1;
            for (int i2 = 0; i2 < min; i2++) {
                byte[] c = c(i2, list.get(i2));
                if (c.length + i >= 387) {
                    byte[] bArr2 = new byte[i];
                    System.arraycopy(bArr, 0, bArr2, 0, i);
                    arrayList.add(bArr2);
                    i = 1;
                }
                System.arraycopy(c, 0, bArr, i, c.length);
                i += c.length;
                if (i2 == list.size() - 1) {
                    byte[] bArr3 = new byte[i];
                    System.arraycopy(bArr, 0, bArr3, 0, i);
                    arrayList.add(bArr3);
                }
            }
        }
        ArrayList arrayList2 = new ArrayList();
        if (arrayList.size() <= 0) {
            arrayList2.add(new PacketData((byte) 2, (byte) -85, new byte[1]));
        } else {
            for (byte[] bArr4 : arrayList) {
                arrayList2.add(new PacketData((byte) 2, (byte) -85, bArr4));
            }
        }
        return arrayList2;
    }

    public static byte[] d(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        int i = calendar.get(2) + 1;
        int i2 = calendar.get(5);
        int i3 = calendar.get(11);
        int i4 = calendar.get(12);
        return new byte[]{(byte) (((calendar.get(1) - 2000) << 2) | (i >> 2)), (byte) ((i << 6) | (i2 << 1) | (i3 >> 4)), (byte) ((i3 << 4) | (i4 >> 2)), (byte) ((i4 << 6) | calendar.get(13))};
    }

    public static PacketData d0() {
        return new PacketData((byte) 2, (byte) 66);
    }

    public static byte e(PacketData packetData) throws PacketDataFormatException {
        byte[] keyData = packetData.getKeyData();
        if (keyData == null || keyData.length < 1) {
            throw new PacketDataFormatException("parserResponseBindOrLogin", packetData);
        }
        return keyData[0];
    }

    public static PacketData e() {
        return new PacketData((byte) 2, (byte) 3);
    }

    public static PacketData e(List<WristbandSchedule> list) {
        PacketData packetData = new PacketData((byte) 2, (byte) 106);
        if (list != null && list.size() != 0) {
            ArrayList<byte[]> arrayList = new ArrayList(list.size());
            int i = 0;
            for (int i2 = 0; i2 < list.size(); i2++) {
                byte[] g = g(list.get(i2));
                i += g.length;
                arrayList.add(g);
            }
            byte[] bArr = new byte[i];
            int i3 = 0;
            for (byte[] bArr2 : arrayList) {
                System.arraycopy(bArr2, 0, bArr, i3, bArr2.length);
                i3 += bArr2.length;
            }
            packetData.setKeyData(bArr);
        }
        return packetData;
    }

    public static byte[] e(WristbandAlarm wristbandAlarm) {
        int i;
        int year = wristbandAlarm.getYear();
        int month = wristbandAlarm.getMonth();
        if (year > 2000) {
            year -= 2000;
            month++;
        }
        byte[] bArr = null;
        if (TextUtils.isEmpty(wristbandAlarm.getLabel())) {
            i = 0;
        } else {
            bArr = wristbandAlarm.getLabel().getBytes();
            i = Math.min(bArr.length, 32);
        }
        byte[] bArr2 = new byte[i + 6];
        bArr2[0] = (byte) (i + 5);
        bArr2[1] = (byte) ((year << 2) | (month >> 2));
        bArr2[2] = (byte) ((month << 6) | (wristbandAlarm.getDay() << 1) | (wristbandAlarm.getHour() >> 4));
        bArr2[3] = (byte) ((wristbandAlarm.getHour() << 4) | (wristbandAlarm.getMinute() >> 2));
        bArr2[4] = (byte) ((wristbandAlarm.getMinute() << 6) | (wristbandAlarm.getAlarmId() << 3));
        bArr2[5] = (byte) (wristbandAlarm.getRepeat() | (wristbandAlarm.isEnable() ? 128 : 0));
        if (bArr != null && i > 0) {
            System.arraycopy(bArr, 0, bArr2, 6, i);
        }
        return bArr2;
    }

    public static PacketData e0() {
        return new PacketData((byte) 2, com.htsmart.wristband2.a.a.a.d1, new SdkFunction().getBytes());
    }

    public static BloodPressureConfig f(PacketData packetData) throws PacketDataFormatException {
        byte[] keyData = packetData.getKeyData();
        if (keyData == null || keyData.length < 10) {
            throw new PacketDataFormatException("parserResponseBloodPressureConfig", packetData);
        }
        return new BloodPressureConfig(keyData);
    }

    public static PacketData f() {
        return new PacketData((byte) 2, (byte) 96);
    }

    public static byte[] f(WristbandHabit wristbandHabit) {
        int i;
        byte[] bytes = !TextUtils.isEmpty(wristbandHabit.getHabitName()) ? wristbandHabit.getHabitName().getBytes() : null;
        if (wristbandHabit.getHabitType() == 0) {
            i = Math.min(32, bytes == null ? 0 : bytes.length);
        } else {
            i = 0;
        }
        byte[] bArr = new byte[i + 17];
        bArr[0] = (byte) wristbandHabit.getHabitId();
        bArr[1] = (byte) wristbandHabit.getRepeat();
        bArr[2] = (byte) wristbandHabit.getAssociatedFunction();
        bArr[3] = (byte) wristbandHabit.getRemindDuration();
        byte[] d = d(wristbandHabit.getStartTime().getTime());
        bArr[4] = d[0];
        bArr[5] = d[1];
        bArr[6] = d[2];
        bArr[7] = d[3];
        bArr[8] = (byte) ((wristbandHabit.getDuration() >> 8) & 255);
        bArr[9] = (byte) (wristbandHabit.getDuration() & 255);
        bArr[11] = (byte) wristbandHabit.getState();
        bArr[12] = (byte) wristbandHabit.getReachGoalDays();
        bArr[13] = (byte) wristbandHabit.getMaxReachGoalDays();
        bArr[14] = (byte) wristbandHabit.getTaskDays();
        bArr[15] = (byte) wristbandHabit.getHabitType();
        bArr[16] = (byte) i;
        if (i > 0) {
            System.arraycopy(bytes, 0, bArr, 17, i);
        }
        return bArr;
    }

    public static PacketData f0() {
        return new PacketData((byte) 2, (byte) 1, d(System.currentTimeMillis()));
    }

    public static DrinkWaterConfig g(PacketData packetData) throws PacketDataFormatException {
        byte[] keyData = packetData.getKeyData();
        if (keyData == null || keyData.length < 9) {
            throw new PacketDataFormatException("parserResponseDrinkWaterConfig", packetData);
        }
        return new DrinkWaterConfig(keyData);
    }

    public static PacketData g() {
        return new PacketData((byte) 2, (byte) 27);
    }

    public static byte[] g(WristbandSchedule wristbandSchedule) {
        int i;
        int year = wristbandSchedule.getYear();
        int month = wristbandSchedule.getMonth();
        if (year > 2000) {
            year -= 2000;
            month++;
        }
        byte[] bArr = null;
        if (TextUtils.isEmpty(wristbandSchedule.getLabel())) {
            i = 0;
        } else {
            bArr = wristbandSchedule.getLabel().getBytes();
            i = Math.min(bArr.length, 32);
        }
        byte[] bArr2 = new byte[i + 7];
        bArr2[0] = (byte) (i + 6);
        bArr2[1] = (byte) wristbandSchedule.getScheduleType();
        bArr2[2] = (byte) ((year << 2) | (month >> 2));
        bArr2[3] = (byte) ((month << 6) | (wristbandSchedule.getDay() << 1) | (wristbandSchedule.getHour() >> 4));
        bArr2[4] = (byte) ((wristbandSchedule.getHour() << 4) | (wristbandSchedule.getMinute() >> 2));
        bArr2[5] = (byte) ((wristbandSchedule.getMinute() << 6) | (wristbandSchedule.getScheduleId() << 3));
        bArr2[6] = (byte) (wristbandSchedule.getRepeat() | (wristbandSchedule.isEnable() ? 128 : 0));
        if (bArr != null && i > 0) {
            System.arraycopy(bArr, 0, bArr2, 7, i);
        }
        return bArr2;
    }

    public static byte h(PacketData packetData) throws PacketDataFormatException {
        byte[] keyData = packetData.getKeyData();
        if (keyData == null || keyData.length < 4) {
            throw new PacketDataFormatException("parserResponseEcgStatus", packetData);
        }
        return keyData[0];
    }

    public static WristbandSchedule h(byte[] bArr) {
        int i = bArr[0] & 255;
        int i2 = (bArr[1] >> 2) & 63;
        int i3 = ((bArr[1] << 2) & 15) | ((bArr[2] >> 6) & 3);
        int i4 = (bArr[2] >> 1) & 31;
        int i5 = ((bArr[2] << 4) & 16) | ((bArr[3] >> 4) & 15);
        int i6 = ((bArr[3] << 2) & 63) | ((bArr[4] >> 6) & 3);
        int i7 = (bArr[4] >> 3) & 7;
        boolean z = ((bArr[5] >> 7) & 1) == 1;
        int i8 = bArr[5] & Byte.MAX_VALUE;
        String str = bArr.length > 6 ? new String(bArr, 6, bArr.length - 6) : null;
        WristbandSchedule wristbandSchedule = new WristbandSchedule();
        wristbandSchedule.setScheduleId(i7);
        wristbandSchedule.setScheduleType(i);
        wristbandSchedule.setRepeat(i8);
        wristbandSchedule.setEnable(z);
        wristbandSchedule.setYear(i2 + 2000);
        wristbandSchedule.setMonth(i3 - 1);
        wristbandSchedule.setDay(i4);
        wristbandSchedule.setHour(i5);
        wristbandSchedule.setMinute(i6);
        wristbandSchedule.setLabel(str);
        return wristbandSchedule;
    }

    public static PacketData h() {
        return new PacketData((byte) 2, (byte) 47);
    }

    public static int i(PacketData packetData) throws PacketDataFormatException {
        byte[] keyData = packetData.getKeyData();
        if (keyData == null || keyData.length < 2) {
            throw new PacketDataFormatException("parserResponseEnterOTA", packetData);
        }
        if (keyData[0] == 0) {
            return 0;
        }
        return keyData[1];
    }

    public static PacketData i() {
        return new PacketData((byte) 2, (byte) 82);
    }

    public static FunctionConfig j(PacketData packetData) throws PacketDataFormatException {
        byte[] keyData = packetData.getKeyData();
        if (keyData == null || keyData.length < 2) {
            throw new PacketDataFormatException("parserResponseFunctionConfig", packetData);
        }
        return new FunctionConfig(keyData);
    }

    public static PacketData j() {
        return new PacketData((byte) 2, (byte) 67);
    }

    public static PacketData k() {
        return new PacketData((byte) 2, (byte) 41);
    }

    @Nullable
    public static List<WristbandHabit> k(PacketData packetData) {
        byte[] keyData = packetData.getKeyData();
        if (keyData == null) {
            return null;
        }
        int i = 1;
        if (keyData.length < 1) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        while (true) {
            int i2 = keyData[i + 16] & 255;
            if (keyData.length < i + 17 + i2) {
                return arrayList;
            }
            WristbandHabit wristbandHabit = new WristbandHabit();
            int i3 = i + 1;
            wristbandHabit.setHabitId(keyData[i] & 255);
            int i4 = i3 + 1;
            wristbandHabit.setRepeat(keyData[i3] & 255);
            int i5 = i4 + 1;
            wristbandHabit.setAssociatedFunction(keyData[i4] & 255);
            int i6 = i5 + 1;
            wristbandHabit.setRemindDuration(keyData[i5] & 255);
            wristbandHabit.setStartTime(new Date(SyncDataParser.parserTime4Bytes(keyData, i6, gregorianCalendar)));
            int i7 = i6 + 4;
            wristbandHabit.setDuration(((keyData[i7] & 255) << 8) | (keyData[i7 + 1] & 255));
            int i8 = i7 + 2;
            int i9 = i8 + 1;
            wristbandHabit.setState(keyData[i8] & 255);
            int i10 = i9 + 1;
            wristbandHabit.setReachGoalDays(keyData[i9] & 255);
            int i11 = i10 + 1;
            wristbandHabit.setMaxReachGoalDays(keyData[i10] & 255);
            int i12 = i11 + 1;
            wristbandHabit.setTaskDays(keyData[i11] & 255);
            int i13 = i12 + 1;
            wristbandHabit.setHabitType(keyData[i12] & 255);
            if (i2 > 0) {
                byte[] bArr = new byte[i2];
                System.arraycopy(keyData, i13, bArr, 0, i2);
                wristbandHabit.setHabitName(new String(bArr));
            }
            arrayList.add(wristbandHabit);
            i = i13;
        }
    }

    public static HealthyConfig l(PacketData packetData) {
        return new HealthyConfig(packetData.getKeyData());
    }

    public static PacketData l() {
        return new PacketData((byte) 2, (byte) 55);
    }

    public static NotDisturbConfig m(PacketData packetData) throws PacketDataFormatException {
        byte[] keyData = packetData.getKeyData();
        if (keyData == null || keyData.length < 6) {
            throw new PacketDataFormatException("parserResponseNotDisturbConfig", packetData);
        }
        return new NotDisturbConfig(keyData);
    }

    public static PacketData m() {
        return new PacketData((byte) 1, (byte) 1);
    }

    public static NotificationConfig n(PacketData packetData) throws PacketDataFormatException {
        byte[] keyData = packetData.getKeyData();
        if (keyData == null || keyData.length < 4) {
            throw new PacketDataFormatException("parserResponseNotificationConfig", packetData);
        }
        return new NotificationConfig(keyData);
    }

    public static PacketData n() {
        return new PacketData((byte) 2, (byte) 25);
    }

    public static PageConfig o(PacketData packetData) throws PacketDataFormatException {
        byte[] keyData = packetData.getKeyData();
        if (keyData == null || keyData.length < 2) {
            throw new PacketDataFormatException("parserResponsePageConfig", packetData);
        }
        return new PageConfig(keyData);
    }

    public static PacketData o() {
        return new PacketData((byte) 2, (byte) 35);
    }

    public static PacketData p() {
        return new PacketData((byte) 5, (byte) 66);
    }

    public static List<WristbandSchedule> p(PacketData packetData) throws PacketDataFormatException {
        int i;
        ArrayList arrayList = new ArrayList(8);
        byte[] keyData = packetData.getKeyData();
        if (keyData != 0 && keyData.length > 0) {
            int i2 = 0;
            while (i2 < keyData.length) {
                int i3 = i2 + 1;
                int i4 = keyData[i2];
                if (i4 < 6 || (i = i4 + i3) > keyData.length) {
                    throw new PacketDataFormatException("parserResponseAlarmList", packetData);
                }
                byte[] bArr = new byte[i4];
                System.arraycopy(keyData, i3, bArr, 0, i4);
                arrayList.add(h(bArr));
                i2 = i;
            }
        }
        return arrayList;
    }

    public static SedentaryConfig q(PacketData packetData) {
        return new SedentaryConfig(packetData.getKeyData());
    }

    public static PacketData q() {
        return new PacketData((byte) 2, (byte) 77);
    }

    public static int r(PacketData packetData) throws PacketDataFormatException {
        byte[] keyData = packetData.getKeyData();
        if (keyData == null || keyData.length < 4) {
            throw new PacketDataFormatException("parserResponseSyncSize", packetData);
        }
        return BytesUtil.bytes2Int(keyData, 0, 4, true);
    }

    public static PacketData r() {
        return new PacketData((byte) 2, (byte) 19);
    }

    public static TurnWristLightingConfig s(PacketData packetData) throws PacketDataFormatException {
        byte[] keyData = packetData.getKeyData();
        if (keyData == null || keyData.length < 9) {
            throw new PacketDataFormatException("parserResponseTurnWristLightingConfig", packetData);
        }
        return new TurnWristLightingConfig(keyData);
    }

    public static PacketData s() {
        return new PacketData((byte) 2, (byte) 22);
    }

    public static PacketData t() {
        return new PacketData((byte) 2, (byte) 107);
    }

    public static boolean t(PacketData packetData) throws PacketDataFormatException {
        byte[] keyData = packetData.getKeyData();
        if (keyData == null || keyData.length < 1) {
            throw new PacketDataFormatException("parserResponseUserUnBind", packetData);
        }
        return keyData[0] == 0;
    }

    public static WarnBloodPressureConfig u(PacketData packetData) throws PacketDataFormatException {
        byte[] keyData = packetData.getKeyData();
        if (keyData == null || keyData.length < 5) {
            throw new PacketDataFormatException("parserResponseWarnBloodPressureConfig", packetData);
        }
        return new WarnBloodPressureConfig(keyData);
    }

    public static PacketData u() {
        return new PacketData((byte) 2, (byte) 38);
    }

    public static WarnHeartRateConfig v(PacketData packetData) throws PacketDataFormatException {
        byte[] keyData = packetData.getKeyData();
        if (keyData == null || keyData.length < 4) {
            throw new PacketDataFormatException("parserResponseWarnHeartRateConfig", packetData);
        }
        return new WarnHeartRateConfig(keyData);
    }

    public static PacketData v() {
        return new PacketData((byte) 5, (byte) 5);
    }

    public static WristbandConfig w(PacketData packetData) throws PacketDataFormatException {
        WristbandConfig newInstance = WristbandConfig.newInstance(packetData.getKeyData());
        if (newInstance != null) {
            return newInstance;
        }
        throw new PacketDataFormatException("parserResponseWristbandConfig", packetData);
    }

    public static PacketData w() {
        return new PacketData((byte) 5, (byte) 33);
    }

    public static WristbandVersion x(PacketData packetData) throws PacketDataFormatException {
        WristbandVersion newInstance = WristbandVersion.newInstance(packetData.getKeyData());
        if (newInstance != null) {
            return newInstance;
        }
        throw new PacketDataFormatException("parserResponseWristbandVersion", packetData);
    }

    public static PacketData x() {
        return new PacketData((byte) 2, (byte) 44);
    }

    public static PacketData y() {
        return new PacketData((byte) 3, (byte) 21);
    }

    public static PacketData z() {
        return new PacketData((byte) 2, com.htsmart.wristband2.a.a.a.Y0);
    }
}
