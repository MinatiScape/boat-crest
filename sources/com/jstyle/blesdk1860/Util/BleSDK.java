package com.jstyle.blesdk1860.Util;

import android.text.TextUtils;
import android.util.Log;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.jstyle.blesdk1860.callback.DataListener1860;
import com.jstyle.blesdk1860.constant.BleConst;
import com.jstyle.blesdk1860.constant.DeviceKey;
import com.jstyle.blesdk1860.model.Clock;
import com.jstyle.blesdk1860.model.MyAutomaticHRMonitoring;
import com.jstyle.blesdk1860.model.MyDeviceInfo;
import com.jstyle.blesdk1860.model.MyDeviceTime;
import com.jstyle.blesdk1860.model.MyPersonalInfo;
import com.jstyle.blesdk1860.model.MySedentaryReminder;
import com.jstyle.blesdk1860.model.Notifier;
import com.jstyle.blesdk1860.model.SportPeriod;
import com.jstyle.blesdk1860.model.StepModel;
import com.jstyle.blesdk1860.model.WeatherData;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes11.dex */
public class BleSDK {
    public static final int DATA_DELETE = 99;
    public static final int DATA_READ_CONTINUE = 2;
    public static final int DATA_READ_START = 0;
    public static final byte DistanceMode_KM = Byte.MIN_VALUE;
    public static final byte DistanceMode_MILE = -127;
    public static boolean MusicControl = false;
    public static boolean RealTimeThreeAxisSensorData = true;
    public static final String TAG = "BleSDK";
    public static final byte TempUnit_C = Byte.MIN_VALUE;
    public static final byte TempUnit_F = -127;
    public static final byte TimeMode_12h = -127;
    public static final byte TimeMode_24h = Byte.MIN_VALUE;
    public static final byte WristOn_DisEnable = Byte.MIN_VALUE;
    public static final byte WristOn_Enable = -127;

    /* renamed from: a  reason: collision with root package name */
    public static boolean f12520a = false;
    public static boolean isruning = false;

    public static byte[] Alarm_clock_master_switch(boolean z) {
        byte[] bArr = new byte[16];
        bArr[0] = 3;
        bArr[4] = (byte) (z ? 129 : 128);
        crcValue(bArr);
        return bArr;
    }

    public static byte[] Ancs(boolean z) {
        byte[] bArr = new byte[16];
        bArr[0] = 3;
        bArr[6] = z ? (byte) -127 : Byte.MIN_VALUE;
        crcValue(bArr);
        return bArr;
    }

    public static byte[] BaseHeart(int i) {
        byte[] bArr = new byte[16];
        bArr[0] = 3;
        bArr[9] = (byte) (i + 128);
        crcValue(bArr);
        return bArr;
    }

    public static byte[] Chinese_and_English_switch(boolean z) {
        byte[] bArr = new byte[16];
        bArr[0] = 3;
        bArr[13] = (byte) (z ? 129 : 128);
        crcValue(bArr);
        return bArr;
    }

    public static void DataParsingWithData(byte[] bArr, DataListener1860 dataListener1860) {
        new HashMap();
        byte b = bArr[0];
        if (b == -86 || b == -85) {
            dataListener1860.dataCallback(bArr);
        } else if (b == 56) {
            if (bArr[2] == 0 || bArr[3] == 0) {
                return;
            }
            dataListener1860.dataCallback(ResolveUtil.GetTemperatureCorrectionValue(bArr));
        } else if (b == 57) {
            if (bArr.length > 16) {
                dataListener1860.dataCallback(ResolveUtil.getPPG(bArr));
            }
        } else if (b == 61) {
            dataListener1860.dataCallback(ResolveUtil.setMethodSuccessful(BleConst.SetDeviceName));
        } else if (b != 62) {
            switch (b) {
                case -125:
                    Map<String, Object> hashMap = new HashMap<>();
                    HashMap hashMap2 = new HashMap();
                    hashMap2.put("heartValue", ResolveUtil.getValue(bArr[1], 0) + "");
                    hashMap2.put("hrvValue", ResolveUtil.getValue(bArr[2], 0) + "");
                    hashMap2.put("Quality", ResolveUtil.getValue(bArr[3], 0) + "");
                    hashMap.put(DeviceKey.DataType, BleConst.EcgppG);
                    hashMap.put(DeviceKey.End, Boolean.TRUE);
                    hashMap.put(DeviceKey.Data, hashMap2);
                    dataListener1860.dataCallback(hashMap);
                    return;
                case -121:
                    dataListener1860.dataCallback(ResolveUtil.ReadHeartateSensorstatus(bArr));
                    return;
                case -104:
                    Map<String, Object> hashMap3 = new HashMap<>();
                    hashMap3.put(DeviceKey.DataType, BleConst.CloseECG);
                    hashMap3.put(DeviceKey.End, Boolean.TRUE);
                    hashMap3.put(DeviceKey.Data, new HashMap());
                    dataListener1860.dataCallback(hashMap3);
                    return;
                case 11:
                    dataListener1860.dataCallback(ResolveUtil.setMethodSuccessful(BleConst.SetStepGoal));
                    return;
                case 16:
                    Map<String, Object> hashMap4 = new HashMap<>();
                    hashMap4.put(DeviceKey.DataType, BleConst.BackHomeView);
                    hashMap4.put(DeviceKey.End, Boolean.TRUE);
                    hashMap4.put(DeviceKey.Data, new HashMap());
                    dataListener1860.dataCallback(hashMap4);
                    return;
                case 46:
                    dataListener1860.dataCallback(ResolveUtil.MCUReset());
                    return;
                case 52:
                    Map<String, Object> hashMap5 = new HashMap<>();
                    hashMap5.put(DeviceKey.DataType, BleConst.GPSControlCommand);
                    hashMap5.put(DeviceKey.End, Boolean.TRUE);
                    HashMap hashMap6 = new HashMap();
                    String str = BleConst.SetAlarmClockWithAllClock + ResolveUtil.bcd2String(bArr[1]) + "-" + ResolveUtil.bcd2String(bArr[2]) + "-" + ResolveUtil.bcd2String(bArr[3]) + HexStringBuilder.DEFAULT_SEPARATOR + ResolveUtil.bcd2String(bArr[4]) + ":" + ResolveUtil.bcd2String(bArr[5]) + ":" + ResolveUtil.bcd2String(bArr[6]);
                    byte[] bArr2 = new byte[4];
                    byte[] bArr3 = new byte[4];
                    for (int i = 0; i < 4; i++) {
                        int i2 = 3 - i;
                        bArr2[i2] = bArr[i + 9];
                        bArr3[i2] = bArr[i + 14];
                    }
                    String valueOf = String.valueOf(ResolveUtil.getFloat(bArr2, 0));
                    String valueOf2 = String.valueOf(ResolveUtil.getFloat(bArr3, 0));
                    int value = ResolveUtil.getValue(bArr[18], 0);
                    hashMap6.put(DeviceKey.KActivityLocationTime, str);
                    hashMap6.put(DeviceKey.KActivityLocationLatitude, valueOf);
                    hashMap6.put(DeviceKey.KActivityLocationLongitude, valueOf2);
                    hashMap6.put(DeviceKey.KActivityLocationCount, String.valueOf(value));
                    hashMap5.put(DeviceKey.Data, hashMap6.toString());
                    dataListener1860.dataCallback(hashMap5);
                    return;
                case 54:
                    dataListener1860.dataCallback(ResolveUtil.setMethodSuccessful(BleConst.SetMotorVibrationWithTimes));
                    return;
                case 68:
                    if (f12520a) {
                        Map<String, Object> hashMap7 = new HashMap<>();
                        hashMap7.put(DeviceKey.DataType, BleConst.AGPS);
                        hashMap7.put(DeviceKey.End, Boolean.TRUE);
                        hashMap7.put(DeviceKey.Data, new HashMap());
                        dataListener1860.dataCallback(hashMap7);
                        return;
                    }
                    Map<String, Object> hashMap8 = new HashMap<>();
                    HashMap hashMap9 = new HashMap();
                    hashMap8.put(DeviceKey.DataType, BleConst.GetAGPS);
                    hashMap8.put(DeviceKey.End, Boolean.TRUE);
                    hashMap9.put(DeviceKey.Days, (ResolveUtil.getValue(bArr[2], 0) + ResolveUtil.getValue(bArr[3], 1)) + "");
                    hashMap8.put(DeviceKey.Data, hashMap9);
                    dataListener1860.dataCallback(hashMap8);
                    return;
                case 73:
                    dataListener1860.dataCallback(ResolveUtil.get3d(bArr));
                    return;
                case 75:
                    dataListener1860.dataCallback(ResolveUtil.getGoal(bArr));
                    return;
                case 77:
                    dataListener1860.dataCallback(ResolveUtil.Notify());
                    return;
                case 90:
                    dataListener1860.dataCallback(ResolveUtil.getHistoryGpsData(bArr));
                    return;
                case 92:
                    dataListener1860.dataCallback(ResolveUtil.getExerciseData(bArr));
                    return;
                case 96:
                    dataListener1860.dataCallback(ResolveUtil.getBloodoxygen(bArr));
                    return;
                case 98:
                    Map<String, Object> hashMap10 = new HashMap<>();
                    HashMap hashMap11 = new HashMap();
                    hashMap10.put(DeviceKey.DataType, BleConst.ReadSerialNumber);
                    hashMap10.put(DeviceKey.End, Boolean.TRUE);
                    StringBuilder sb = new StringBuilder();
                    for (int i3 = 1; i3 < 8; i3++) {
                        sb.append(String.format("%02X", Byte.valueOf(bArr[i3])));
                    }
                    hashMap11.put(DeviceKey.ReadSerialNumber, sb.toString());
                    hashMap10.put(DeviceKey.Data, hashMap11);
                    dataListener1860.dataCallback(hashMap10);
                    return;
                default:
                    switch (b) {
                        case 1:
                            dataListener1860.dataCallback(ResolveUtil.setTimeSuccessful(bArr));
                            return;
                        case 2:
                            dataListener1860.dataCallback(ResolveUtil.setMethodSuccessful("3"));
                            return;
                        case 3:
                            dataListener1860.dataCallback(ResolveUtil.setMethodSuccessful(BleConst.SetDeviceInfo));
                            return;
                        case 4:
                            dataListener1860.dataCallback(ResolveUtil.getDeviceInfo(bArr));
                            dataListener1860.dataCallback(bArr);
                            return;
                        case 5:
                            dataListener1860.dataCallback(ResolveUtil.setMacSuccessful());
                            return;
                        case 6:
                            Map<String, Object> hashMap12 = new HashMap<>();
                            Object hashMap13 = new HashMap();
                            hashMap12.put(DeviceKey.DataType, BleConst.SetTheFactoryTimeOfTheEquipment);
                            hashMap12.put(DeviceKey.End, Boolean.TRUE);
                            hashMap12.put(DeviceKey.Data, hashMap13);
                            dataListener1860.dataCallback(hashMap12);
                            return;
                        case 7:
                            Map<String, Object> hashMap14 = new HashMap<>();
                            HashMap hashMap15 = new HashMap();
                            hashMap14.put(DeviceKey.DataType, BleConst.GetTheFactoryTimeOfTheEquipment);
                            hashMap14.put(DeviceKey.End, Boolean.TRUE);
                            String str2 = BleConst.SetAlarmClockWithAllClock + ResolveUtil.bcd2String(bArr[3]) + "." + ResolveUtil.bcd2String(bArr[4]) + "." + ResolveUtil.bcd2String(bArr[5]) + HexStringBuilder.DEFAULT_SEPARATOR + ResolveUtil.bcd2String(bArr[6]) + ":" + ResolveUtil.bcd2String(bArr[7]) + ":" + ResolveUtil.bcd2String(bArr[8]);
                            String str3 = "";
                            for (int i4 = 1; i4 < 3; i4++) {
                                str3 = str3 + ((char) ResolveUtil.getValue(bArr[i4], 0));
                            }
                            hashMap15.put(DeviceKey.Factory_name, str3 + "");
                            hashMap15.put("date", str2);
                            hashMap14.put(DeviceKey.Data, hashMap15);
                            dataListener1860.dataCallback(hashMap14);
                            return;
                        case 8:
                            if (MusicControl) {
                                Map<String, Object> hashMap16 = new HashMap<>();
                                hashMap16.put(DeviceKey.DataType, BleConst.SetMusicControl);
                                hashMap16.put(DeviceKey.End, Boolean.TRUE);
                                hashMap16.put(DeviceKey.Data, new HashMap());
                                dataListener1860.dataCallback(hashMap16);
                                return;
                            }
                            Map<String, Object> hashMap17 = new HashMap<>();
                            HashMap hashMap18 = new HashMap();
                            hashMap17.put(DeviceKey.DataType, BleConst.GetMusicControl);
                            hashMap17.put(DeviceKey.End, Boolean.TRUE);
                            hashMap18.put(DeviceKey.musicSwitchStatus, ((int) bArr[2]) + "");
                            hashMap17.put(DeviceKey.Data, hashMap18);
                            dataListener1860.dataCallback(hashMap17);
                            return;
                        case 9:
                            dataListener1860.dataCallback(ResolveUtil.getActivityData(bArr));
                            return;
                        default:
                            switch (b) {
                                case 18:
                                    dataListener1860.dataCallback(ResolveUtil.Reset());
                                    return;
                                case 19:
                                    dataListener1860.dataCallback(ResolveUtil.getDeviceBattery(bArr));
                                    return;
                                case 20:
                                    Map<String, Object> hashMap19 = new HashMap<>();
                                    hashMap19.put(DeviceKey.DataType, BleConst.SportMode);
                                    hashMap19.put(DeviceKey.End, Boolean.TRUE);
                                    hashMap19.put(DeviceKey.Data, new HashMap());
                                    dataListener1860.dataCallback(hashMap19);
                                    return;
                                case 21:
                                    Map<String, Object> hashMap20 = new HashMap<>();
                                    hashMap20.put(DeviceKey.DataType, BleConst.Weather);
                                    hashMap20.put(DeviceKey.End, Boolean.TRUE);
                                    hashMap20.put(DeviceKey.Data, new HashMap());
                                    dataListener1860.dataCallback(hashMap20);
                                    return;
                                case 22:
                                    Map<String, Object> hashMap21 = new HashMap<>();
                                    HashMap hashMap22 = new HashMap();
                                    byte b2 = bArr[1];
                                    if (b2 == 1) {
                                        byte b3 = bArr[2];
                                        if (b3 == 1) {
                                            hashMap22.put("type", "1");
                                        } else if (b3 == 2) {
                                            hashMap22.put("type", "2");
                                        }
                                    } else if (b2 == 2) {
                                        byte b4 = bArr[2];
                                        if (b4 == 0) {
                                            hashMap22.put("type", BleConst.GetDeviceInfo);
                                        } else if (b4 == 1) {
                                            hashMap22.put("type", "3");
                                        }
                                    } else if (b2 == 3) {
                                        byte b5 = bArr[2];
                                        if (b5 == 1) {
                                            hashMap22.put("type", BleConst.SetDeviceInfo);
                                        } else if (b5 == 2) {
                                            hashMap22.put("type", BleConst.CMD_Set_Mac);
                                        } else if (b5 == 3) {
                                            hashMap22.put("type", BleConst.GetStepGoal);
                                        } else if (b5 == 4) {
                                            hashMap22.put("type", BleConst.SetStepGoal);
                                        } else if (b5 == 5) {
                                            hashMap22.put("type", BleConst.GetDeviceBatteryLevel);
                                        }
                                    } else if (b2 == 4) {
                                        byte b6 = bArr[2];
                                        if (b6 == 1) {
                                            hashMap22.put("type", BleConst.GetDeviceMacAddress);
                                        } else if (b6 == 2) {
                                            hashMap22.put("type", BleConst.GetDeviceVersion);
                                        }
                                    }
                                    hashMap21.put(DeviceKey.Data, hashMap22);
                                    hashMap21.put(DeviceKey.DataType, BleConst.DeviceSendDataToAPP);
                                    hashMap21.put(DeviceKey.End, Boolean.TRUE);
                                    dataListener1860.dataCallback(hashMap21);
                                    return;
                                default:
                                    switch (b) {
                                        case 24:
                                            dataListener1860.dataCallback(ResolveUtil.getActivityExerciseData(bArr));
                                            return;
                                        case 25:
                                            dataListener1860.dataCallback(ResolveUtil.setMethodSuccessful(BleConst.EnterActivityMode));
                                            return;
                                        case 26:
                                            dataListener1860.dataCallback(ResolveUtil.setMethodSuccessful(BleConst.Sendmsg));
                                            return;
                                        default:
                                            switch (b) {
                                                case 34:
                                                    dataListener1860.dataCallback(ResolveUtil.getDeviceAddress(bArr));
                                                    return;
                                                case 35:
                                                    dataListener1860.dataCallback(ResolveUtil.updateClockSuccessful(bArr));
                                                    return;
                                                case 36:
                                                    Map<String, Object> hashMap23 = new HashMap<>();
                                                    HashMap hashMap24 = new HashMap();
                                                    hashMap24.put(DeviceKey.sportType, ((int) bArr[1]) + "");
                                                    hashMap23.put(DeviceKey.DataType, BleConst.Send_app_indoor_outdoor);
                                                    hashMap23.put(DeviceKey.End, Boolean.TRUE);
                                                    hashMap23.put(DeviceKey.Data, hashMap24);
                                                    dataListener1860.dataCallback(hashMap23);
                                                    return;
                                                case 37:
                                                    dataListener1860.dataCallback(ResolveUtil.setMethodSuccessful(BleConst.SetSedentaryReminder));
                                                    return;
                                                case 38:
                                                    dataListener1860.dataCallback(ResolveUtil.getActivityAlarm(bArr));
                                                    return;
                                                case 39:
                                                    dataListener1860.dataCallback(ResolveUtil.getDeviceVersion(bArr));
                                                    return;
                                                case 40:
                                                    Map<String, Object> hashMap25 = new HashMap<>();
                                                    HashMap hashMap26 = new HashMap();
                                                    hashMap26.put(DeviceKey.sportType, ((int) bArr[1]) + "");
                                                    hashMap25.put(DeviceKey.DataType, BleConst.Get_indoor_outdoor);
                                                    hashMap25.put(DeviceKey.End, Boolean.TRUE);
                                                    hashMap25.put(DeviceKey.Data, hashMap26);
                                                    dataListener1860.dataCallback(hashMap25);
                                                    return;
                                                case 41:
                                                    Map<String, Object> hashMap27 = new HashMap<>();
                                                    hashMap27.put(DeviceKey.DataType, BleConst.Language);
                                                    hashMap27.put(DeviceKey.End, Boolean.TRUE);
                                                    hashMap27.put(DeviceKey.Data, new HashMap());
                                                    dataListener1860.dataCallback(hashMap27);
                                                    return;
                                                case 42:
                                                    dataListener1860.dataCallback(ResolveUtil.setMethodSuccessful(BleConst.SetAutomaticHRMonitoring));
                                                    return;
                                                case 43:
                                                    dataListener1860.dataCallback(ResolveUtil.getAutoHeart(bArr));
                                                    return;
                                                default:
                                                    switch (b) {
                                                        case 64:
                                                            dataListener1860.dataCallback(ResolveUtil.BloodOxygen_PPG(bArr));
                                                            return;
                                                        case 65:
                                                            dataListener1860.dataCallback(ResolveUtil.getDeviceTime(bArr));
                                                            return;
                                                        case 66:
                                                            dataListener1860.dataCallback(ResolveUtil.getUserInfo(bArr));
                                                            return;
                                                        default:
                                                            switch (b) {
                                                                case 81:
                                                                    dataListener1860.dataCallback(ResolveUtil.getTotalStepData(bArr));
                                                                    return;
                                                                case 82:
                                                                    dataListener1860.dataCallback(ResolveUtil.getDetailData(bArr));
                                                                    return;
                                                                case 83:
                                                                    dataListener1860.dataCallback(ResolveUtil.getSleepData(bArr));
                                                                    return;
                                                                case 84:
                                                                    dataListener1860.dataCallback(ResolveUtil.getHeartData(bArr));
                                                                    return;
                                                                case 85:
                                                                    dataListener1860.dataCallback(ResolveUtil.getOnceHeartData(bArr));
                                                                    return;
                                                                case 86:
                                                                    dataListener1860.dataCallback(ResolveUtil.getHrvTestData(bArr));
                                                                    return;
                                                                case 87:
                                                                    dataListener1860.dataCallback(ResolveUtil.getClockData(bArr));
                                                                    return;
                                                                default:
                                                                    return;
                                                            }
                                                    }
                                            }
                                    }
                            }
                    }
            }
        } else {
            dataListener1860.dataCallback(ResolveUtil.getDeviceName(bArr));
        }
    }

    public static byte[] Dialinterface(int i) {
        byte[] bArr = new byte[16];
        bArr[0] = 3;
        bArr[12] = (byte) (i + 128);
        crcValue(bArr);
        return bArr;
    }

    public static byte[] Do_not_disturb_mode_switch(boolean z) {
        byte[] bArr = new byte[16];
        bArr[0] = 3;
        bArr[5] = (byte) (z ? 129 : 128);
        crcValue(bArr);
        return bArr;
    }

    public static byte[] EnterActivityMode(int i, int i2) {
        byte[] bArr = new byte[16];
        bArr[0] = 25;
        bArr[1] = (byte) i2;
        bArr[2] = (byte) i;
        crcValue(bArr);
        return bArr;
    }

    public static byte[] EnterPhotoMode() {
        byte[] bArr = new byte[16];
        bArr[0] = 32;
        crcValue(bArr);
        return bArr;
    }

    public static byte[] ExitPhotoMode() {
        byte[] bArr = new byte[16];
        bArr[0] = 16;
        crcValue(bArr);
        return bArr;
    }

    public static byte[] Float2ByteArray(float f) {
        return Float2ByteArray(Float.floatToIntBits(f));
    }

    public static byte[] Float2ByteArray(int i) {
        return new byte[]{(byte) (i & 255), (byte) ((65280 & i) >> 8), (byte) ((16711680 & i) >> 16), (byte) (((-16777216) & i) >> 24)};
    }

    public static byte[] GPSControlCommand(boolean z) {
        byte[] bArr = new byte[16];
        bArr[0] = 52;
        bArr[1] = z ? (byte) 1 : (byte) 0;
        crcValue(bArr);
        return bArr;
    }

    public static byte[] GetAGPS() {
        f12520a = false;
        byte[] bArr = new byte[16];
        bArr[0] = 68;
        crcValue(bArr);
        return bArr;
    }

    public static byte[] GetActivityModeDataWithMode(int i, Calendar calendar) {
        byte[] bArr = new byte[16];
        bArr[0] = 92;
        bArr[1] = (byte) i;
        if (calendar != null) {
            insertDateValue(bArr, calendar);
        }
        crcValue(bArr);
        return bArr;
    }

    public static byte[] GetAlarmClock(int i) {
        byte[] bArr = new byte[16];
        bArr[0] = 87;
        bArr[1] = (byte) i;
        crcValue(bArr);
        return bArr;
    }

    public static byte[] GetAutomaticHRMonitoring() {
        byte[] bArr = new byte[16];
        bArr[0] = 43;
        crcValue(bArr);
        return bArr;
    }

    public static byte[] GetDetailActivityDataWithMode(int i, Calendar calendar) {
        byte[] bArr = new byte[16];
        bArr[0] = 82;
        bArr[1] = (byte) i;
        if (calendar != null) {
            insertDateValue(bArr, calendar);
        }
        crcValue(bArr);
        return bArr;
    }

    public static byte[] GetDetailSleepDataWithMode(int i, Calendar calendar) {
        byte[] bArr = new byte[16];
        bArr[0] = 83;
        bArr[1] = (byte) i;
        if (calendar != null) {
            insertDateValue(bArr, calendar);
        }
        crcValue(bArr);
        return bArr;
    }

    public static byte[] GetDeviceBatteryLevel() {
        byte[] bArr = new byte[16];
        bArr[0] = 19;
        crcValue(bArr);
        return bArr;
    }

    public static byte[] GetDeviceInfo() {
        byte[] bArr = new byte[16];
        bArr[0] = 4;
        crcValue(bArr);
        return bArr;
    }

    public static byte[] GetDeviceMacAddress() {
        byte[] bArr = new byte[16];
        bArr[0] = 34;
        crcValue(bArr);
        return bArr;
    }

    public static byte[] GetDeviceName() {
        byte[] bArr = new byte[16];
        bArr[0] = 62;
        crcValue(bArr);
        return bArr;
    }

    public static byte[] GetDeviceTime() {
        byte[] bArr = new byte[16];
        bArr[0] = 65;
        crcValue(bArr);
        return bArr;
    }

    public static byte[] GetDeviceVersion() {
        byte[] bArr = new byte[16];
        bArr[0] = 39;
        crcValue(bArr);
        return bArr;
    }

    public static byte[] GetDynamicHRWithMode(int i, Calendar calendar) {
        byte[] bArr = new byte[16];
        bArr[0] = 84;
        bArr[1] = (byte) i;
        if (calendar != null) {
            insertDateValue(bArr, calendar);
        }
        crcValue(bArr);
        return bArr;
    }

    public static byte[] GetGpsData(int i) {
        byte[] bArr = new byte[16];
        bArr[0] = 90;
        bArr[1] = (byte) i;
        crcValue(bArr);
        return bArr;
    }

    public static byte[] GetHRVDataWithMode(int i, Calendar calendar) {
        byte[] bArr = new byte[16];
        bArr[0] = 86;
        bArr[1] = (byte) i;
        if (calendar != null) {
            insertDateValue(bArr, calendar);
        }
        crcValue(bArr);
        return bArr;
    }

    public static byte[] GetMusicControl() {
        MusicControl = false;
        byte[] bArr = new byte[16];
        bArr[0] = 8;
        crcValue(bArr);
        return bArr;
    }

    public static byte[] GetPersonalInfo() {
        byte[] bArr = new byte[16];
        bArr[0] = 66;
        crcValue(bArr);
        return bArr;
    }

    public static byte[] GetPpgRawDataWithStatus(boolean z) {
        byte[] bArr = new byte[16];
        bArr[0] = 57;
        bArr[1] = z ? (byte) 1 : (byte) 0;
        crcValue(bArr);
        return bArr;
    }

    public static byte[] GetSedentaryReminder() {
        byte[] bArr = new byte[16];
        bArr[0] = 38;
        crcValue(bArr);
        return bArr;
    }

    public static byte[] GetStaticHRWithMode(int i, Calendar calendar) {
        byte[] bArr = new byte[16];
        bArr[0] = 85;
        bArr[1] = (byte) i;
        if (calendar != null) {
            insertDateValue(bArr, calendar);
        }
        crcValue(bArr);
        return bArr;
    }

    public static byte[] GetStepGoal() {
        byte[] bArr = new byte[16];
        bArr[0] = 75;
        crcValue(bArr);
        return bArr;
    }

    public static byte[] GetTemperatureCorrectionValue() {
        byte[] bArr = new byte[16];
        bArr[0] = 56;
        bArr[1] = 0;
        crcValue(bArr);
        return bArr;
    }

    public static byte[] GetTheFactoryTimeOfTheEquipment() {
        byte[] bArr = new byte[16];
        bArr[0] = 7;
        crcValue(bArr);
        return bArr;
    }

    public static byte[] GetTotalActivityDataWithMode(int i, Calendar calendar) {
        byte[] bArr = new byte[16];
        bArr[0] = 81;
        bArr[1] = (byte) i;
        if (calendar != null) {
            insertDateValueNoH(bArr, calendar);
        }
        crcValue(bArr);
        return bArr;
    }

    public static byte[] Get_Blood_oxygen(int i, Calendar calendar) {
        byte[] bArr = new byte[16];
        bArr[0] = 96;
        bArr[1] = (byte) i;
        if (calendar != null) {
            insertDateValueNoH(bArr, calendar);
        }
        crcValue(bArr);
        return bArr;
    }

    public static byte[] Get_indoor_outdoor() {
        byte[] bArr = new byte[16];
        bArr[0] = 40;
        crcValue(bArr);
        return bArr;
    }

    public static byte[] Hand_up_light_screen_switch(boolean z) {
        byte[] bArr = new byte[16];
        bArr[0] = 3;
        bArr[3] = (byte) (z ? 129 : 128);
        crcValue(bArr);
        return bArr;
    }

    public static byte[] LanguageSwitching(boolean z) {
        byte[] bArr = new byte[16];
        bArr[0] = 41;
        bArr[1] = 1;
        bArr[2] = !z ? 1 : 0;
        crcValue(bArr);
        return bArr;
    }

    public static byte[] MCUReset() {
        byte[] bArr = new byte[16];
        bArr[0] = 46;
        crcValue(bArr);
        return bArr;
    }

    public static byte[] MotorVibrationWithTimes(int i) {
        byte[] bArr = new byte[16];
        bArr[0] = 54;
        bArr[1] = (byte) i;
        crcValue(bArr);
        return bArr;
    }

    public static byte[] Night_mode(boolean z) {
        byte[] bArr = new byte[16];
        bArr[0] = 3;
        bArr[10] = (byte) (z ? 129 : 128);
        crcValue(bArr);
        return bArr;
    }

    public static byte[] PushMsg(String str) {
        byte[] infoValue = TextUtils.isEmpty(str) ? new byte[1] : getInfoValue(str, 128);
        byte[] bArr = new byte[infoValue.length + 2];
        bArr[0] = 26;
        bArr[1] = (byte) infoValue.length;
        System.arraycopy(infoValue, 0, bArr, 2, infoValue.length);
        return bArr;
    }

    public static byte[] ReadBloodOxygen_PPG(boolean z) {
        byte[] bArr = new byte[16];
        bArr[0] = 64;
        bArr[1] = z ? (byte) 1 : (byte) 0;
        crcValue(bArr);
        return bArr;
    }

    public static byte[] ReadHeartateSensorstatus() {
        byte[] bArr = new byte[16];
        bArr[0] = -121;
        bArr[1] = 1;
        crcValue(bArr);
        return bArr;
    }

    public static byte[] ReadSerialNumber() {
        byte[] bArr = new byte[16];
        bArr[0] = 98;
        crcValue(bArr);
        return bArr;
    }

    public static byte[] RealTimeStep(boolean z, boolean z2) {
        byte[] bArr = new byte[16];
        bArr[0] = 9;
        bArr[1] = z ? (byte) 1 : (byte) 0;
        bArr[2] = z2 ? (byte) 1 : (byte) 0;
        crcValue(bArr);
        return bArr;
    }

    public static byte[] RealTimeThreeAxisSensorData(boolean z) {
        RealTimeThreeAxisSensorData = z;
        byte[] bArr = new byte[16];
        bArr[0] = 73;
        bArr[1] = z ? (byte) 1 : (byte) 0;
        crcValue(bArr);
        return bArr;
    }

    public static byte[] Reset() {
        byte[] bArr = new byte[16];
        bArr[0] = 18;
        crcValue(bArr);
        return bArr;
    }

    public static byte[] ScreenBrightness(int i) {
        byte[] bArr = new byte[16];
        bArr[0] = 3;
        bArr[11] = (byte) (i + 128);
        crcValue(bArr);
        return bArr;
    }

    public static byte[] SendMusicname(String str) {
        byte[] infoValue = TextUtils.isEmpty(str) ? new byte[1] : getInfoValue(str, 12);
        byte[] bArr = new byte[16];
        bArr[0] = 22;
        bArr[1] = 3;
        bArr[2] = -2;
        System.arraycopy(infoValue, 0, bArr, 3, infoValue.length);
        crcValue(bArr);
        return bArr;
    }

    public static byte[] SetAGPS(int i) {
        f12520a = true;
        if (i == 0) {
            i = 1;
        }
        byte[] bArr = new byte[16];
        bArr[0] = 68;
        bArr[1] = 1;
        bArr[2] = (byte) (i & 255);
        bArr[3] = (byte) ((i >> 8) & 255);
        crcValue(bArr);
        return bArr;
    }

    public static byte[] SetAlarmClockWithAllClock(Clock clock) {
        byte[] bArr = new byte[37];
        byte[] infoValue = getInfoValue(clock.getContent(), 30);
        bArr[0] = 35;
        bArr[1] = (byte) clock.getNumber();
        bArr[2] = (byte) clock.getType();
        bArr[3] = ResolveUtil.getTimeValue(clock.getHour());
        bArr[4] = ResolveUtil.getTimeValue(clock.getMinute());
        bArr[5] = (byte) clock.getWeek();
        bArr[6] = (byte) infoValue.length;
        System.arraycopy(infoValue, 0, bArr, 7, infoValue.length);
        return bArr;
    }

    public static byte[] SetAutomaticHRMonitoring(MyAutomaticHRMonitoring myAutomaticHRMonitoring) {
        int time = myAutomaticHRMonitoring.getTime();
        byte[] bArr = {42, (byte) myAutomaticHRMonitoring.getOpen(), ResolveUtil.getTimeValue(myAutomaticHRMonitoring.getStartHour()), ResolveUtil.getTimeValue(myAutomaticHRMonitoring.getStartMinute()), ResolveUtil.getTimeValue(myAutomaticHRMonitoring.getEndHour()), ResolveUtil.getTimeValue(myAutomaticHRMonitoring.getEndMinute()), (byte) myAutomaticHRMonitoring.getWeek(), (byte) (time & 255), (byte) ((time >> 8) & 255)};
        crcValue(bArr);
        return bArr;
    }

    public static byte[] SetDeviceID(String str) {
        byte[] bArr = new byte[16];
        int i = 0;
        bArr[0] = 5;
        while (i < 6) {
            int i2 = i + 1;
            bArr[i2] = (byte) str.charAt(i);
            i = i2;
        }
        crcValue(bArr);
        return bArr;
    }

    public static byte[] SetDeviceInfo(MyDeviceInfo myDeviceInfo) {
        byte[] bArr = new byte[16];
        bArr[0] = 3;
        bArr[1] = (byte) (myDeviceInfo.isDistanceUnit() ? 129 : 128);
        bArr[2] = (byte) (myDeviceInfo.isIs12Hour() ? 129 : 128);
        bArr[3] = (byte) (myDeviceInfo.isHand_up_light_screen_switch() ? 129 : 128);
        bArr[4] = (byte) (myDeviceInfo.isAlarm_clock_master_switch() ? 129 : 128);
        bArr[5] = (byte) (myDeviceInfo.isDo_not_disturb_mode_switch() ? 129 : 128);
        bArr[6] = Byte.MIN_VALUE;
        bArr[9] = (byte) (myDeviceInfo.getBaseheart() + 128);
        bArr[10] = (byte) (myDeviceInfo.isNight_mode() ? 129 : 128);
        if (-1 != myDeviceInfo.getScreenBrightness()) {
            bArr[11] = (byte) (myDeviceInfo.getScreenBrightness() + 128);
        }
        bArr[12] = (byte) (myDeviceInfo.getDialinterface() + 128);
        bArr[13] = (byte) (myDeviceInfo.isChinese() ? 129 : 128);
        crcValue(bArr);
        return bArr;
    }

    public static byte[] SetDeviceName(String str) {
        byte[] bArr = new byte[16];
        int i = 0;
        bArr[0] = 61;
        int length = str.length() <= 14 ? str.length() : 14;
        while (i < length) {
            int i2 = i + 1;
            bArr[i2] = (byte) str.charAt(i);
            i = i2;
        }
        crcValue(bArr);
        return bArr;
    }

    public static byte[] SetDeviceTime(MyDeviceTime myDeviceTime) {
        int year = myDeviceTime.getYear();
        int month = myDeviceTime.getMonth();
        int day = myDeviceTime.getDay();
        int hour = myDeviceTime.getHour();
        int minute = myDeviceTime.getMinute();
        int second = myDeviceTime.getSecond();
        int currentTimeZone = (int) (ResolveUtil.getCurrentTimeZone() * 60.0f);
        byte[] bArr = {1, ResolveUtil.getTimeValue(year), ResolveUtil.getTimeValue(month), ResolveUtil.getTimeValue(day), ResolveUtil.getTimeValue(hour), ResolveUtil.getTimeValue(minute), ResolveUtil.getTimeValue(second), 0, (byte) (Math.abs(currentTimeZone) & 255), (byte) ((Math.abs(currentTimeZone) >> 8) & 255)};
        if (currentTimeZone > 0) {
            bArr[9] = (byte) (bArr[9] + 128);
        }
        crcValue(bArr);
        return bArr;
    }

    public static byte[] SetDistanceUnit(boolean z) {
        byte[] bArr = new byte[16];
        bArr[0] = 3;
        bArr[1] = (byte) (z ? 129 : 128);
        crcValue(bArr);
        return bArr;
    }

    public static byte[] SetMusicControl(boolean z) {
        MusicControl = true;
        byte[] bArr = new byte[16];
        bArr[0] = 8;
        bArr[1] = 1;
        bArr[2] = z ? (byte) -127 : Byte.MIN_VALUE;
        crcValue(bArr);
        return bArr;
    }

    public static byte[] SetMusicStatus(Boolean bool) {
        byte[] bArr = new byte[16];
        bArr[0] = 22;
        bArr[1] = 3;
        bArr[2] = bool.booleanValue() ? (byte) 4 : (byte) 5;
        crcValue(bArr);
        return bArr;
    }

    public static byte[] SetPersonalInfo(MyPersonalInfo myPersonalInfo) {
        byte[] bArr = new byte[16];
        int sex = myPersonalInfo.getSex();
        int age = myPersonalInfo.getAge();
        int height = myPersonalInfo.getHeight();
        int weight = myPersonalInfo.getWeight();
        int stepLength = myPersonalInfo.getStepLength();
        bArr[0] = 2;
        bArr[1] = (byte) sex;
        bArr[2] = (byte) age;
        bArr[3] = (byte) height;
        bArr[4] = (byte) weight;
        bArr[5] = (byte) stepLength;
        crcValue(bArr);
        return bArr;
    }

    public static byte[] SetSedentaryReminder(MySedentaryReminder mySedentaryReminder) {
        byte[] bArr = {37, ResolveUtil.getTimeValue(mySedentaryReminder.getStartHour()), ResolveUtil.getTimeValue(mySedentaryReminder.getStartMinute()), ResolveUtil.getTimeValue(mySedentaryReminder.getEndHour()), ResolveUtil.getTimeValue(mySedentaryReminder.getEndMinute()), (byte) mySedentaryReminder.getWeek(), (byte) mySedentaryReminder.getIntervalTime(), (byte) (mySedentaryReminder.getLeastStep() & 255), mySedentaryReminder.isEnable() ? (byte) 1 : (byte) 0};
        crcValue(bArr);
        return bArr;
    }

    public static byte[] SetStepGoal(int i) {
        byte[] bArr = new byte[16];
        bArr[0] = 11;
        bArr[4] = (byte) ((i >> 24) & 255);
        bArr[3] = (byte) ((i >> 16) & 255);
        bArr[2] = (byte) ((i >> 8) & 255);
        bArr[1] = (byte) (i & 255);
        crcValue(bArr);
        return bArr;
    }

    public static byte[] SetTempUnit(byte b) {
        byte[] bArr = new byte[16];
        bArr[0] = 3;
        bArr[12] = b;
        crcValue(bArr);
        return bArr;
    }

    public static byte[] SetTemperatureCorrectionValue(int i) {
        byte[] bArr = new byte[16];
        bArr[0] = 56;
        bArr[1] = 1;
        byte[] intTobyte = intTobyte(i);
        bArr[2] = intTobyte[1];
        bArr[3] = intTobyte[0];
        crcValue(bArr);
        return bArr;
    }

    public static byte[] SetTheFactoryTimeOfTheEquipment(int i, int i2, int i3, int i4, int i5, int i6) {
        byte[] bArr = new byte[16];
        bArr[0] = 6;
        bArr[1] = ResolveUtil.getTimeValue(i);
        bArr[2] = ResolveUtil.getTimeValue(i2);
        bArr[3] = ResolveUtil.getTimeValue(i3);
        bArr[4] = ResolveUtil.getTimeValue(i4);
        bArr[5] = ResolveUtil.getTimeValue(i5);
        bArr[6] = ResolveUtil.getTimeValue(i6);
        crcValue(bArr);
        return bArr;
    }

    public static byte[] SetTimeModeUnit(boolean z) {
        byte[] bArr = new byte[16];
        bArr[0] = 3;
        bArr[2] = (byte) (z ? 129 : 128);
        crcValue(bArr);
        return bArr;
    }

    public static byte[] SportMode(List<Integer> list) {
        byte[] bArr = new byte[16];
        int i = 0;
        bArr[0] = 20;
        while (i < 5) {
            i++;
            bArr[i] = -1;
        }
        int i2 = 1;
        for (Integer num : list) {
            int intValue = num.intValue();
            if (intValue >= 6) {
                intValue++;
            }
            bArr[i2] = (byte) intValue;
            i2++;
        }
        crcValue(bArr);
        return bArr;
    }

    public static byte[] a(float f) {
        return a(Float.floatToIntBits(f));
    }

    public static int byteArrayToInt(byte[] bArr) {
        return (short) (((bArr[0] << 8) & 65280) | (bArr[1] & 255));
    }

    public static void crcValue(byte[] bArr) {
        byte b = 0;
        for (int i = 0; i < bArr.length - 1; i++) {
            b = (byte) (b + bArr[i]);
        }
        bArr[bArr.length - 1] = (byte) (b & 255);
    }

    public static byte[] deleteAllClock() {
        byte[] bArr = new byte[16];
        bArr[0] = 87;
        bArr[1] = -103;
        crcValue(bArr);
        return bArr;
    }

    public static byte[] enableEcgPPg(int i) {
        byte[] bArr = new byte[16];
        bArr[0] = -103;
        bArr[1] = (byte) i;
        crcValue(bArr);
        return bArr;
    }

    public static byte[] enterOTA() {
        byte[] bArr = new byte[16];
        bArr[0] = 71;
        crcValue(bArr);
        return bArr;
    }

    public static byte[] getInfoValue(String str, int i) {
        byte[] bArr = null;
        try {
            bArr = str.getBytes("UTF-8");
            if (bArr.length >= i) {
                byte[] bArr2 = new byte[i];
                int i2 = 0;
                for (char c : str.toCharArray()) {
                    byte[] bytes = String.valueOf(c).getBytes("UTF-8");
                    if (bytes.length + i2 == i) {
                        System.arraycopy(bArr, 0, bArr2, 0, i);
                        return bArr2;
                    } else if (bytes.length + i2 > i) {
                        System.arraycopy(bArr, 0, bArr2, 0, i2);
                        return bArr2;
                    } else {
                        i2 += bytes.length;
                    }
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return bArr;
    }

    public static byte[] getWorkOutReminder() {
        byte[] bArr = new byte[16];
        bArr[0] = 41;
        crcValue(bArr);
        return bArr;
    }

    public static void insertDateValue(byte[] bArr, Calendar calendar) {
        if (calendar != null) {
            bArr[4] = ResolveUtil.getTimeValue(calendar.get(1));
            bArr[5] = ResolveUtil.getTimeValue(calendar.get(2) + 1);
            bArr[6] = ResolveUtil.getTimeValue(calendar.get(5));
            bArr[7] = ResolveUtil.getTimeValue(calendar.get(11));
            bArr[8] = ResolveUtil.getTimeValue(calendar.get(12));
            bArr[9] = ResolveUtil.getTimeValue(calendar.get(13));
        }
    }

    public static void insertDateValueNoH(byte[] bArr, Calendar calendar) {
        bArr[4] = ResolveUtil.getTimeValue(calendar.get(1));
        bArr[5] = ResolveUtil.getTimeValue(calendar.get(2) + 1);
        bArr[6] = ResolveUtil.getTimeValue(calendar.get(5));
    }

    public static byte[] intTobyte(int i) {
        return new byte[]{(byte) ((i >> 8) & 255), (byte) (i & 255)};
    }

    public static byte[] sendHeartPackage(float f, int i, int i2) {
        byte[] bArr = new byte[16];
        byte[] a2 = a(f);
        bArr[0] = 23;
        System.arraycopy(a2, 0, bArr, 1, a2.length);
        bArr[5] = (byte) (i / 60);
        bArr[6] = (byte) (i % 60);
        bArr[7] = (byte) i2;
        crcValue(bArr);
        return bArr;
    }

    public static byte[] setClockData(List<Clock> list) {
        int size = list.size();
        int i = (size * 39) + 2;
        byte[] bArr = new byte[i];
        int i2 = 0;
        while (true) {
            int i3 = 1;
            if (i2 < list.size()) {
                Clock clock = list.get(i2);
                byte[] bArr2 = new byte[39];
                byte[] infoValue = getInfoValue(clock.getContent(), 30);
                bArr2[0] = 35;
                bArr2[1] = (byte) size;
                bArr2[2] = (byte) clock.getNumber();
                bArr2[3] = clock.isEnable() ? (byte) 1 : (byte) 0;
                bArr2[4] = (byte) clock.getType();
                bArr2[5] = ResolveUtil.getTimeValue(clock.getHour());
                bArr2[6] = ResolveUtil.getTimeValue(clock.getMinute());
                bArr2[7] = (byte) clock.getWeek();
                if (infoValue.length != 0) {
                    i3 = infoValue.length;
                }
                bArr2[8] = (byte) i3;
                System.arraycopy(infoValue, 0, bArr2, 9, infoValue.length);
                System.arraycopy(bArr2, 0, bArr, i2 * 39, 39);
                i2++;
            } else {
                Log.i(TAG, "setClockData: " + i);
                bArr[i + (-2)] = 35;
                bArr[i - 1] = -1;
                return bArr;
            }
        }
    }

    public static byte[] setNotifyData(Notifier notifier) {
        String info = notifier.getInfo();
        byte[] infoValue = TextUtils.isEmpty(info) ? new byte[1] : getInfoValue(info, 60);
        byte[] bArr = new byte[infoValue.length + 3];
        bArr[0] = 77;
        bArr[1] = (byte) notifier.getType();
        bArr[2] = (byte) infoValue.length;
        System.arraycopy(infoValue, 0, bArr, 3, infoValue.length);
        return bArr;
    }

    public static byte[] setStepModel(StepModel stepModel) {
        return new byte[16];
    }

    public static byte[] setWeather(WeatherData weatherData) {
        byte[] bArr = new byte[38];
        bArr[0] = 21;
        bArr[1] = (byte) weatherData.getWeatherId();
        int tempNow = weatherData.getTempNow();
        bArr[2] = (byte) (tempNow < 0 ? 1 : 0);
        bArr[3] = (byte) Math.abs(tempNow);
        int tempLow = weatherData.getTempLow();
        bArr[4] = (byte) (tempLow < 0 ? 1 : 0);
        bArr[5] = (byte) Math.abs(tempLow);
        int tempHigh = weatherData.getTempHigh();
        bArr[6] = (byte) (tempHigh >= 0 ? 0 : 1);
        bArr[7] = (byte) Math.abs(tempHigh);
        byte[] infoValue = getInfoValue(weatherData.getCityName(), 30);
        System.arraycopy(infoValue, 0, bArr, 8, infoValue.length);
        return bArr;
    }

    public static byte[] setWorkOutReminder(SportPeriod sportPeriod) {
        int intervalTime = sportPeriod.getIntervalTime();
        byte[] bArr = {40, ResolveUtil.getTimeValue(sportPeriod.getStartHour()), ResolveUtil.getTimeValue(sportPeriod.getStartMinute()), (byte) sportPeriod.getDays(), (byte) sportPeriod.getWeek(), sportPeriod.isEnable() ? (byte) 1 : (byte) 0, (byte) (intervalTime & 255), (byte) ((intervalTime >> 8) & 255)};
        crcValue(bArr);
        return bArr;
    }

    public static byte[] setWristOnEnable(boolean z) {
        byte[] bArr = new byte[16];
        bArr[0] = 3;
        bArr[3] = z ? (byte) -127 : Byte.MIN_VALUE;
        crcValue(bArr);
        return bArr;
    }

    public static byte[] startBreathMode(boolean z, int i, int i2) {
        byte[] bArr = new byte[16];
        bArr[0] = 25;
        bArr[1] = (byte) (z ? 1 : 4);
        bArr[2] = 6;
        bArr[3] = (byte) i;
        bArr[4] = (byte) i2;
        crcValue(bArr);
        return bArr;
    }

    public static byte[] stopEcgPPg() {
        byte[] bArr = new byte[16];
        bArr[0] = -104;
        crcValue(bArr);
        return bArr;
    }

    public static byte[] stopGo() {
        byte[] bArr = new byte[16];
        bArr[0] = 9;
        bArr[1] = 0;
        crcValue(bArr);
        return bArr;
    }

    public static byte[] enableEcgPPg(int i, int i2) {
        byte[] bArr = new byte[16];
        bArr[0] = -103;
        bArr[1] = (byte) i;
        bArr[3] = (byte) (i2 & 255);
        bArr[4] = (byte) ((i2 >> 8) & 255);
        crcValue(bArr);
        return bArr;
    }
}
