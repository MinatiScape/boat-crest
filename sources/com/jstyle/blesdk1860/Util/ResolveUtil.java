package com.jstyle.blesdk1860.Util;

import androidx.core.view.ViewCompat;
import com.clevertap.android.sdk.Constants;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.jstyle.blesdk1860.constant.BleConst;
import com.jstyle.blesdk1860.constant.DeviceKey;
import com.jstyle.blesdk1860.model.DeviceBean;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;
/* loaded from: classes11.dex */
public class ResolveUtil {
    public static boolean RealTimeThreeAxisSensorData = true;

    public static Map<String, Object> BloodOxygen_PPG(byte[] bArr) {
        HashMap hashMap = new HashMap();
        hashMap.put(DeviceKey.DataType, BleConst.BloodOxygen_PPG);
        hashMap.put(DeviceKey.End, Boolean.TRUE);
        ArrayList arrayList = new ArrayList();
        hashMap.put(DeviceKey.Data, arrayList);
        if (16 == bArr.length) {
            HashMap hashMap2 = new HashMap();
            hashMap2.put(DeviceKey.DataType, Byte.valueOf(bArr[1]));
            arrayList.add(hashMap2);
        } else {
            int length = bArr.length - 3;
            byte[] bArr2 = new byte[length];
            System.arraycopy(bArr, 3, bArr2, 0, length);
            for (int i = 0; i < length / 2; i++) {
                HashMap hashMap3 = new HashMap();
                int i2 = i * 2;
                int value = getValue(bArr2[i2], 0) + getValue(bArr2[i2 + 1], 1);
                hashMap3.put(DeviceKey.position, Byte.valueOf(bArr[1]));
                hashMap3.put(DeviceKey.arrayPpgRawData, Integer.valueOf(value));
                arrayList.add(hashMap3);
            }
        }
        return hashMap;
    }

    public static String ByteToHexString(byte b) {
        String hexString = Integer.toHexString(new Byte(b).intValue());
        if (hexString.length() == 1) {
            return BleConst.GetDeviceTime + hexString;
        }
        return hexString;
    }

    public static Map<String, Object> ECGData(byte[] bArr) {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        hashMap.put(DeviceKey.End, Boolean.TRUE);
        hashMap.put(DeviceKey.DataType, BleConst.ECGDATA);
        hashMap.put(DeviceKey.Data, hashMap2);
        hashMap2.put(DeviceKey.ECGValue, bArr);
        return hashMap;
    }

    public static Map<String, Object> ECGResult(byte[] bArr) {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        hashMap.put(DeviceKey.End, Boolean.TRUE);
        hashMap.put(DeviceKey.DataType, BleConst.ECGResult);
        hashMap.put(DeviceKey.Data, hashMap2);
        int value = getValue(bArr[1], 0);
        String str = BleConst.SetAlarmClockWithAllClock + ByteToHexString(bArr[10]) + "." + ByteToHexString(bArr[11]) + "." + ByteToHexString(bArr[12]) + HexStringBuilder.DEFAULT_SEPARATOR + ByteToHexString(bArr[13]) + ":" + ByteToHexString(bArr[14]) + ":" + ByteToHexString(bArr[15]);
        int value2 = getValue(bArr[2], 0);
        int value3 = getValue(bArr[3], 0);
        int value4 = getValue(bArr[4], 0);
        int value5 = getValue(bArr[5], 0);
        int value6 = getValue(bArr[6], 0);
        int value7 = getValue(bArr[7], 0);
        int value8 = getValue(bArr[8], 0);
        int value9 = getValue(bArr[9], 0);
        hashMap2.put(DeviceKey.ECGResultValue, String.valueOf(value));
        hashMap2.put("date", str);
        hashMap2.put(DeviceKey.ECGHrvValue, String.valueOf(value2));
        hashMap2.put(DeviceKey.ECGAvBlockValue, String.valueOf(value3));
        hashMap2.put(DeviceKey.ECGHrValue, String.valueOf(value4));
        hashMap2.put(DeviceKey.ECGStreesValue, String.valueOf(value5));
        hashMap2.put(DeviceKey.ECGhighBpValue, String.valueOf(value6));
        hashMap2.put(DeviceKey.ECGLowBpValue, String.valueOf(value7));
        hashMap2.put(DeviceKey.ECGMoodValue, String.valueOf(value8));
        hashMap2.put(DeviceKey.ECGBreathValue, String.valueOf(value9));
        return hashMap;
    }

    public static Map<String, Object> GetTemperatureCorrectionValue(byte[] bArr) {
        HashMap hashMap = new HashMap();
        hashMap.put(DeviceKey.DataType, BleConst.CMD_Set_TemperatureCorrection);
        hashMap.put(DeviceKey.End, Boolean.TRUE);
        HashMap hashMap2 = new HashMap();
        hashMap.put(DeviceKey.Data, hashMap2);
        hashMap2.put("TemperatureCorrectionValue", String.valueOf(BleSDK.byteArrayToInt(new byte[]{bArr[3], bArr[2]})));
        return hashMap;
    }

    public static Map<String, Object> MCUReset() {
        HashMap hashMap = new HashMap();
        hashMap.put(DeviceKey.DataType, BleConst.CMD_MCUReset);
        hashMap.put(DeviceKey.Data, new HashMap());
        hashMap.put(DeviceKey.End, Boolean.TRUE);
        return hashMap;
    }

    public static Map<String, Object> Notify() {
        HashMap hashMap = new HashMap();
        hashMap.put(DeviceKey.DataType, BleConst.Notify);
        hashMap.put(DeviceKey.Data, new HashMap());
        hashMap.put(DeviceKey.End, Boolean.TRUE);
        return hashMap;
    }

    public static Map<String, Object> PPGData(byte[] bArr) {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        hashMap.put(DeviceKey.End, Boolean.TRUE);
        hashMap.put(DeviceKey.DataType, BleConst.EcgppGstatus);
        hashMap.put(DeviceKey.Data, hashMap2);
        hashMap2.put(DeviceKey.PPGValue, bArr);
        return hashMap;
    }

    public static Map<String, Object> ReadHeartateSensorstatus(byte[] bArr) {
        HashMap hashMap = new HashMap();
        hashMap.put(DeviceKey.DataType, BleConst.ReadHeartateSensorstatus);
        hashMap.put(DeviceKey.End, Boolean.TRUE);
        HashMap hashMap2 = new HashMap();
        hashMap2.put(DeviceKey.ReadHeartateSensorstatus, ((int) bArr[1]) + "");
        hashMap.put(DeviceKey.Data, hashMap2);
        return hashMap;
    }

    public static byte[] RealTimeThreeAxisSensorData(boolean z) {
        RealTimeThreeAxisSensorData = z;
        byte[] bArr = new byte[16];
        bArr[0] = 73;
        bArr[1] = z ? (byte) 1 : (byte) 0;
        crcValue(bArr);
        return bArr;
    }

    public static Map<String, Object> Reset() {
        HashMap hashMap = new HashMap();
        hashMap.put(DeviceKey.DataType, BleConst.CMD_Reset);
        hashMap.put(DeviceKey.End, Boolean.TRUE);
        hashMap.put(DeviceKey.Data, new HashMap());
        return hashMap;
    }

    public static int a(byte[] bArr) {
        int length = bArr.length;
        if (length != 2) {
            if (length % 26 == 0) {
                return 26;
            }
            if (length % 27 != 0) {
                int i = length - 2;
                if (i % 26 == 0) {
                    return 26;
                }
                int i2 = i % 27;
            }
        }
        return 27;
    }

    public static String bcd2String(byte b) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append((int) ((byte) ((b & 240) >>> 4)));
        stringBuffer.append((int) ((byte) (b & 15)));
        return stringBuffer.toString();
    }

    public static String byte2Hex(byte b) {
        return String.format("%02X ", Byte.valueOf(b));
    }

    public static void crcValue(byte[] bArr) {
        byte b = 0;
        for (int i = 0; i < bArr.length - 1; i++) {
            b = (byte) (b + bArr[i]);
        }
        bArr[bArr.length - 1] = (byte) (b & 255);
    }

    public static Map<String, Object> enterEcg(byte[] bArr) {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        hashMap.put(DeviceKey.End, Boolean.TRUE);
        hashMap.put(DeviceKey.DataType, BleConst.ENTERECG);
        hashMap.put(DeviceKey.Data, hashMap2);
        return hashMap;
    }

    public static Map<String, Object> get3d(byte[] bArr) {
        HashMap hashMap = new HashMap();
        hashMap.put(DeviceKey.DataType, BleConst.Get3D);
        hashMap.put(DeviceKey.End, Boolean.TRUE);
        ArrayList arrayList = new ArrayList();
        hashMap.put(DeviceKey.Data, arrayList);
        for (int i = 0; i < bArr.length / 8; i++) {
            HashMap hashMap2 = new HashMap();
            int i2 = i * 6;
            int value = getValue(bArr[i2 + 1], 0) + getValue(bArr[i2 + 2], 1);
            int value2 = getValue(bArr[i2 + 3], 0) + getValue(bArr[i2 + 4], 1);
            int value3 = getValue(bArr[i2 + 5], 0) + getValue(bArr[i2 + 6], 1);
            hashMap2.put(DeviceKey.arrayX, Integer.valueOf(value));
            hashMap2.put(DeviceKey.arrayY, Integer.valueOf(value2));
            hashMap2.put(DeviceKey.arrayZ, Integer.valueOf(value3));
            arrayList.add(hashMap2);
        }
        return hashMap;
    }

    public static Map<String, Object> getActivityAlarm(byte[] bArr) {
        HashMap hashMap = new HashMap();
        hashMap.put(DeviceKey.DataType, BleConst.GetSedentaryReminder);
        hashMap.put(DeviceKey.End, Boolean.TRUE);
        HashMap hashMap2 = new HashMap();
        hashMap.put(DeviceKey.Data, hashMap2);
        String[] strArr = {ByteToHexString(bArr[1]), ByteToHexString(bArr[2]), ByteToHexString(bArr[3]), ByteToHexString(bArr[4]), getByteString(bArr[5]), String.valueOf(getValue(bArr[6], 0)), String.valueOf(getValue(bArr[7], 0))};
        hashMap2.put(DeviceKey.StartTimeHour, strArr[0]);
        hashMap2.put(DeviceKey.StartTimeMin, strArr[1]);
        hashMap2.put(DeviceKey.EndTimeHour, strArr[2]);
        hashMap2.put(DeviceKey.EndTimeMin, strArr[3]);
        hashMap2.put("weekValue", strArr[4]);
        hashMap2.put(DeviceKey.IntervalTime, strArr[5]);
        hashMap2.put(DeviceKey.LeastSteps, strArr[6]);
        return hashMap;
    }

    public static Map<String, Object> getActivityData(byte[] bArr) {
        int i;
        int i2;
        int i3;
        HashMap hashMap = new HashMap();
        hashMap.put(DeviceKey.DataType, BleConst.RealTimeStep);
        hashMap.put(DeviceKey.End, Boolean.TRUE);
        HashMap hashMap2 = new HashMap();
        hashMap.put(DeviceKey.Data, hashMap2);
        String[] strArr = new String[6];
        int i4 = 0;
        for (int i5 = 1; i5 < 5; i5++) {
            i4 += getValue(bArr[i5], i5 - 1);
        }
        float f = 0.0f;
        float f2 = 0.0f;
        int i6 = 5;
        while (true) {
            i = 9;
            if (i6 >= 9) {
                break;
            }
            f2 += getValue(bArr[i6], i6 - 5);
            i6++;
        }
        while (true) {
            i2 = 13;
            if (i >= 13) {
                break;
            }
            f += getValue(bArr[i], i - 9);
            i++;
        }
        int i7 = 0;
        while (true) {
            if (i2 >= 17) {
                break;
            }
            i7 += getValue(bArr[i2], i2 - 13);
            i2++;
        }
        int i8 = 0;
        for (i3 = 17; i3 < 21; i3++) {
            i8 += getValue(bArr[i3], i3 - 17);
        }
        int value = getValue(bArr[21], 0);
        int value2 = getValue(bArr[22], 0);
        NumberFormat numberFormat = getNumberFormat(1);
        BigDecimal scale = new BigDecimal(String.valueOf(f2 / 100.0f)).setScale(1, RoundingMode.HALF_DOWN);
        strArr[0] = String.valueOf(i4);
        strArr[1] = scale.floatValue() + "";
        numberFormat.setMinimumFractionDigits(2);
        strArr[2] = numberFormat.format((double) (f / 100.0f));
        strArr[3] = String.valueOf(i7 / 60);
        strArr[4] = String.valueOf(value);
        strArr[5] = String.valueOf(i8);
        hashMap2.put(DeviceKey.Step, strArr[0]);
        hashMap2.put("calories", strArr[1]);
        hashMap2.put("distance", strArr[2]);
        hashMap2.put(DeviceKey.ExerciseMinutes, strArr[3]);
        hashMap2.put(DeviceKey.HeartRate, strArr[4]);
        hashMap2.put(DeviceKey.IsTheDeviceProperlyWorn, value2 + "");
        numberFormat.setMinimumFractionDigits(1);
        return hashMap;
    }

    public static Map<String, Object> getActivityExerciseData(byte[] bArr) {
        HashMap hashMap = new HashMap();
        hashMap.put(DeviceKey.DataType, BleConst.EnterActivityMode);
        hashMap.put(DeviceKey.End, Boolean.TRUE);
        HashMap hashMap2 = new HashMap();
        hashMap.put(DeviceKey.Data, hashMap2);
        int value = getValue(bArr[1], 0);
        int i = 0;
        for (int i2 = 0; i2 < 4; i2++) {
            i += getValue(bArr[i2 + 2], i2);
        }
        byte[] bArr2 = new byte[4];
        for (int i3 = 0; i3 < 4; i3++) {
            bArr2[3 - i3] = bArr[i3 + 6];
        }
        float f = getFloat(bArr2, 0);
        hashMap2.put(DeviceKey.HeartRate, String.valueOf(value));
        hashMap2.put(DeviceKey.Step, String.valueOf(i));
        hashMap2.put("calories", String.valueOf(f));
        return hashMap;
    }

    public static Map<String, Object> getAutoHeart(byte[] bArr) {
        HashMap hashMap = new HashMap();
        hashMap.put(DeviceKey.DataType, BleConst.GetAutomaticHRMonitoring);
        hashMap.put(DeviceKey.End, Boolean.TRUE);
        HashMap hashMap2 = new HashMap();
        hashMap.put(DeviceKey.Data, hashMap2);
        String[] strArr = {String.valueOf(getValue(bArr[1], 0)), ByteToHexString(bArr[2]), ByteToHexString(bArr[3]), ByteToHexString(bArr[4]), ByteToHexString(bArr[5]), getByteString(bArr[6]), String.valueOf(getValue(bArr[7], 0) + getValue(bArr[8], 1))};
        hashMap2.put(DeviceKey.WorkMode, strArr[0]);
        hashMap2.put(DeviceKey.StartTime, strArr[1]);
        hashMap2.put(DeviceKey.KHeartStartMinter, strArr[2]);
        hashMap2.put(DeviceKey.EndTime, strArr[3]);
        hashMap2.put(DeviceKey.KHeartEndMinter, strArr[4]);
        hashMap2.put("weekValue", strArr[5]);
        hashMap2.put(DeviceKey.IntervalTime, strArr[6]);
        return hashMap;
    }

    public static Map<String, Object> getBloodoxygen(byte[] bArr) {
        HashMap hashMap = new HashMap();
        hashMap.put(DeviceKey.DataType, BleConst.Blood_oxygen);
        hashMap.put(DeviceKey.End, Boolean.FALSE);
        ArrayList arrayList = new ArrayList();
        hashMap.put(DeviceKey.Data, arrayList);
        int length = bArr.length;
        int i = length / 10;
        if (i == 0) {
            hashMap.put(DeviceKey.End, Boolean.TRUE);
            return hashMap;
        }
        int i2 = 0;
        while (i2 < i) {
            int i3 = i2 + 1;
            if (bArr[length - 1] == -1) {
                hashMap.put(DeviceKey.End, Boolean.TRUE);
            }
            HashMap hashMap2 = new HashMap();
            StringBuilder sb = new StringBuilder();
            sb.append(BleConst.SetAlarmClockWithAllClock);
            int i4 = i2 * 10;
            sb.append(ByteToHexString(bArr[i4 + 3]));
            sb.append(".");
            sb.append(ByteToHexString(bArr[i4 + 4]));
            sb.append(".");
            sb.append(ByteToHexString(bArr[i4 + 5]));
            sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
            sb.append(ByteToHexString(bArr[i4 + 6]));
            sb.append(":");
            sb.append(ByteToHexString(bArr[i4 + 7]));
            sb.append(":");
            sb.append(ByteToHexString(bArr[i4 + 8]));
            hashMap2.put("date", sb.toString());
            hashMap2.put(DeviceKey.Blood_oxygen, String.valueOf(getValue(bArr[i4 + 9], 0)));
            arrayList.add(hashMap2);
            i2 = i3;
        }
        return hashMap;
    }

    public static String getByteArray(byte b) {
        byte[] bArr = new byte[8];
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i <= 7; i++) {
            bArr[i] = (byte) (b & 1);
            b = (byte) (b >> 1);
            stringBuffer.append(String.valueOf((int) bArr[i]));
        }
        return stringBuffer.toString();
    }

    public static String getByteString(byte b) {
        byte[] bArr = new byte[8];
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        while (i <= 6) {
            bArr[i] = (byte) (b & 1);
            b = (byte) (b >> 1);
            stringBuffer.append(String.valueOf((int) bArr[i]));
            stringBuffer.append(i == 6 ? "" : "-");
            i++;
        }
        return stringBuffer.toString();
    }

    public static Map<String, Object> getClockData(byte[] bArr) {
        int i;
        byte[] bArr2 = bArr;
        HashMap hashMap = new HashMap();
        hashMap.put(DeviceKey.DataType, BleConst.GetAlarmClock);
        Boolean bool = Boolean.FALSE;
        String str = DeviceKey.End;
        hashMap.put(DeviceKey.End, bool);
        ArrayList arrayList = new ArrayList();
        hashMap.put(DeviceKey.Data, arrayList);
        int length = bArr2.length;
        int i2 = length / 41;
        new DeviceBean();
        if (i2 == 0) {
            hashMap.put(DeviceKey.End, Boolean.TRUE);
            return hashMap;
        }
        int i3 = 0;
        int i4 = 0;
        while (i4 < i2) {
            int i5 = i4 + 1;
            int i6 = (i5 * 41) + 1;
            if (i6 < length && bArr2[i6] == -1) {
                hashMap.put(str, Boolean.TRUE);
            }
            HashMap hashMap2 = new HashMap();
            int i7 = i4 * 41;
            String valueOf = String.valueOf(getValue(bArr2[i7 + 4], i3));
            String valueOf2 = String.valueOf(getValue(bArr2[i7 + 5], i3));
            String valueOf3 = String.valueOf(getValue(bArr2[i7 + 6], i3));
            String ByteToHexString = ByteToHexString(bArr2[i7 + 7]);
            String ByteToHexString2 = ByteToHexString(bArr2[i7 + 8]);
            String byteString = getByteString(bArr2[i7 + 9]);
            String str2 = str;
            int value = getValue(bArr2[i7 + 10], i3);
            int i8 = length;
            int i9 = i3;
            String str3 = "";
            while (i9 < value) {
                if (bArr2[i7 + 11] == 0) {
                    i = i2;
                } else {
                    i = i2;
                    str3 = str3 + ((char) getValue(bArr2[i9 + 11 + i7], 0));
                }
                i9++;
                bArr2 = bArr;
                i2 = i;
            }
            hashMap2.put(DeviceKey.KAlarmId, valueOf);
            hashMap2.put(DeviceKey.OpenOrClose, valueOf2);
            hashMap2.put(DeviceKey.ClockType, valueOf3);
            hashMap2.put(DeviceKey.ClockTime, ByteToHexString);
            hashMap2.put(DeviceKey.KAlarmMinter, ByteToHexString2);
            hashMap2.put("weekValue", byteString);
            hashMap2.put(DeviceKey.KAlarmContent, str3);
            hashMap2.put(DeviceKey.KAlarmLength, String.valueOf(value));
            arrayList.add(hashMap2);
            i3 = 0;
            i4 = i5;
            length = i8;
            str = str2;
            bArr2 = bArr;
        }
        return hashMap;
    }

    public static float getCurrentTimeZone() {
        TimeZone.getDefault().getDisplayName(false, 0);
        return TimeZone.getDefault().getOffset(System.currentTimeMillis()) / 3600000.0f;
    }

    public static int getData(int i, int i2, byte[] bArr) {
        int i3 = 0;
        for (int i4 = 0; i4 < i; i4++) {
            i3 += getValue(bArr[i4 + i2], i4);
        }
        return i3;
    }

    public static Map<String, Object> getDetailData(byte[] bArr) {
        byte[] bArr2 = bArr;
        HashMap hashMap = new HashMap();
        hashMap.put(DeviceKey.DataType, BleConst.GetDetailActivityData);
        hashMap.put(DeviceKey.End, Boolean.FALSE);
        ArrayList arrayList = new ArrayList();
        hashMap.put(DeviceKey.Data, arrayList);
        int length = bArr2.length;
        int i = length / 25;
        new DeviceBean();
        if (i == 0) {
            hashMap.put(DeviceKey.End, Boolean.TRUE);
            return hashMap;
        }
        NumberFormat numberInstance = NumberFormat.getNumberInstance();
        int i2 = 2;
        numberInstance.setMaximumFractionDigits(2);
        int i3 = 0;
        numberInstance.setGroupingUsed(false);
        int i4 = 0;
        while (i4 < i) {
            HashMap hashMap2 = new HashMap();
            int i5 = i4 + 1;
            if (bArr2[length - 1] == -1) {
                hashMap.put(DeviceKey.End, Boolean.TRUE);
            }
            StringBuilder sb = new StringBuilder();
            sb.append(BleConst.SetAlarmClockWithAllClock);
            int i6 = i4 * 25;
            sb.append(ByteToHexString(bArr2[i6 + 3]));
            sb.append(".");
            sb.append(ByteToHexString(bArr2[i6 + 4]));
            sb.append(".");
            sb.append(ByteToHexString(bArr2[i6 + 5]));
            sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
            sb.append(ByteToHexString(bArr2[i6 + 6]));
            sb.append(":");
            sb.append(ByteToHexString(bArr2[i6 + 7]));
            sb.append(":");
            sb.append(ByteToHexString(bArr2[i6 + 8]));
            String sb2 = sb.toString();
            StringBuffer stringBuffer = new StringBuffer();
            int i7 = i3;
            int i8 = i7;
            while (i7 < i2) {
                i8 += getValue(bArr2[i7 + 9 + i6], i7);
                i7++;
            }
            float f = 0.0f;
            for (int i9 = 0; i9 < i2; i9++) {
                f += getValue(bArr2[i9 + 11 + i6], i9);
            }
            float f2 = 0.0f;
            int i10 = 0;
            while (i10 < i2) {
                f2 += getValue(bArr2[i10 + 13 + i6], i10);
                i10++;
                i2 = 2;
            }
            int i11 = 0;
            while (i11 < 10) {
                stringBuffer.append(String.valueOf(getValue(bArr2[i11 + 15 + i6], 0)));
                stringBuffer.append(i11 == 9 ? "" : HexStringBuilder.DEFAULT_SEPARATOR);
                i11++;
                bArr2 = bArr;
            }
            hashMap2.put("date", sb2);
            hashMap2.put(DeviceKey.KDetailMinterStep, String.valueOf(i8));
            hashMap2.put("calories", numberInstance.format(f / 100.0f));
            hashMap2.put("distance", numberInstance.format(f2 / 100.0f));
            hashMap2.put(DeviceKey.ArraySteps, stringBuffer.toString());
            arrayList.add(hashMap2);
            i3 = 0;
            i4 = i5;
            i2 = 2;
            bArr2 = bArr;
        }
        return hashMap;
    }

    public static Map<String, Object> getDeviceAddress(byte[] bArr) {
        HashMap hashMap = new HashMap();
        hashMap.put(DeviceKey.DataType, BleConst.GetDeviceMacAddress);
        hashMap.put(DeviceKey.End, Boolean.TRUE);
        HashMap hashMap2 = new HashMap();
        hashMap.put(DeviceKey.Data, hashMap2);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < 7; i++) {
            sb.append(String.format("%02X", Byte.valueOf(bArr[i])));
            sb.append(":");
        }
        String sb2 = sb.toString();
        hashMap2.put(DeviceKey.MacAddress, sb2.substring(0, sb2.lastIndexOf(":")));
        return hashMap;
    }

    public static Map<String, Object> getDeviceBattery(byte[] bArr) {
        HashMap hashMap = new HashMap();
        hashMap.put(DeviceKey.DataType, BleConst.GetDeviceBatteryLevel);
        hashMap.put(DeviceKey.End, Boolean.TRUE);
        HashMap hashMap2 = new HashMap();
        hashMap.put(DeviceKey.Data, hashMap2);
        hashMap2.put(DeviceKey.BatteryLevel, String.valueOf(getValue(bArr[1], 0)));
        return hashMap;
    }

    public static Map<String, Object> getDeviceInfo(byte[] bArr) {
        HashMap hashMap = new HashMap();
        hashMap.put(DeviceKey.DataType, BleConst.GetDeviceInfo);
        hashMap.put(DeviceKey.End, Boolean.TRUE);
        HashMap hashMap2 = new HashMap();
        hashMap.put(DeviceKey.Data, hashMap2);
        hashMap2.put(DeviceKey.DistanceUnit, String.valueOf(getValue(bArr[1], 0)));
        hashMap2.put(DeviceKey.TimeUnit, String.valueOf(getValue(bArr[2], 0)));
        hashMap2.put(DeviceKey.Hand_up_light_screen_switch, String.valueOf(getValue(bArr[3], 0)));
        hashMap2.put(DeviceKey.Alarm_clock_master_switch, String.valueOf(getValue(bArr[4], 0)));
        hashMap2.put(DeviceKey.Do_not_disturb_mode_switch, String.valueOf(getValue(bArr[5], 0)));
        hashMap2.put(DeviceKey.KBaseHeart, String.valueOf(getValue(bArr[9], 0)));
        hashMap2.put(DeviceKey.Night_mode, String.valueOf(getValue(bArr[10], 0)));
        hashMap2.put(DeviceKey.ScreenBrightness, String.valueOf(getValue(bArr[11], 0)));
        hashMap2.put(DeviceKey.Dialinterface, String.valueOf(getValue(bArr[12], 0)));
        hashMap2.put(DeviceKey.Chinese, String.valueOf(getValue(bArr[13], 0)));
        return hashMap;
    }

    public static Map<String, Object> getDeviceName(byte[] bArr) {
        HashMap hashMap = new HashMap();
        hashMap.put(DeviceKey.DataType, BleConst.GetDeviceName);
        hashMap.put(DeviceKey.End, Boolean.TRUE);
        HashMap hashMap2 = new HashMap();
        hashMap.put(DeviceKey.Data, hashMap2);
        String str = "";
        for (int i = 1; i < 15; i++) {
            int value = getValue(bArr[i], 0);
            if (value != 0 && value <= 127) {
                str = str + ((char) value);
            }
        }
        hashMap2.put(DeviceKey.DeviceName, str);
        return hashMap;
    }

    public static Map<String, Object> getDeviceTime(byte[] bArr) {
        HashMap hashMap = new HashMap();
        hashMap.put(DeviceKey.DataType, BleConst.GetDeviceTime);
        hashMap.put(DeviceKey.End, Boolean.TRUE);
        HashMap hashMap2 = new HashMap();
        String str = BleConst.SetAlarmClockWithAllClock + ByteToHexString(bArr[1]) + "-" + ByteToHexString(bArr[2]) + "-" + ByteToHexString(bArr[3]) + HexStringBuilder.DEFAULT_SEPARATOR + ByteToHexString(bArr[4]) + ":" + ByteToHexString(bArr[5]) + ":" + ByteToHexString(bArr[6]);
        hashMap2.put(DeviceKey.DeviceTime, str);
        hashMap2.put(DeviceKey.GPSTime, ByteToHexString(bArr[9]) + "." + ByteToHexString(bArr[10]) + "." + ByteToHexString(bArr[11]));
        hashMap.put(DeviceKey.Data, hashMap2);
        return hashMap;
    }

    public static Map<String, Object> getDeviceVersion(byte[] bArr) {
        HashMap hashMap = new HashMap();
        hashMap.put(DeviceKey.DataType, BleConst.GetDeviceVersion);
        hashMap.put(DeviceKey.End, Boolean.TRUE);
        HashMap hashMap2 = new HashMap();
        hashMap.put(DeviceKey.Data, hashMap2);
        StringBuffer stringBuffer = new StringBuffer();
        int i = 1;
        while (i < 5) {
            stringBuffer.append(String.format("%X", Byte.valueOf(bArr[i])));
            stringBuffer.append(i == 4 ? "" : ".");
            i++;
        }
        hashMap2.put(DeviceKey.DeviceVersion, stringBuffer.toString());
        return hashMap;
    }

    public static Map<String, Object> getExerciseData(byte[] bArr) {
        HashMap hashMap = new HashMap();
        hashMap.put(DeviceKey.DataType, BleConst.GetActivityModeData);
        Boolean bool = Boolean.FALSE;
        String str = DeviceKey.End;
        hashMap.put(DeviceKey.End, bool);
        ArrayList arrayList = new ArrayList();
        hashMap.put(DeviceKey.Data, arrayList);
        int length = bArr.length;
        int i = length / 25;
        if (i == 0) {
            hashMap.put(DeviceKey.End, Boolean.TRUE);
            return hashMap;
        }
        int i2 = 1;
        NumberFormat numberFormat = getNumberFormat(1);
        int i3 = 2;
        NumberFormat numberFormat2 = getNumberFormat(2);
        int i4 = 0;
        int i5 = 0;
        while (i5 < i) {
            int i6 = i5 + 1;
            int i7 = (i6 * 25) + i2;
            if (i7 < length && i5 == i - 1 && bArr[i7] == -1) {
                hashMap.put(str, Boolean.TRUE);
            }
            HashMap hashMap2 = new HashMap();
            StringBuilder sb = new StringBuilder();
            sb.append(BleConst.SetAlarmClockWithAllClock);
            int i8 = i5 * 25;
            sb.append(ByteToHexString(bArr[i8 + 3]));
            sb.append(".");
            sb.append(ByteToHexString(bArr[i8 + 4]));
            sb.append(".");
            sb.append(ByteToHexString(bArr[i8 + 5]));
            sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
            sb.append(ByteToHexString(bArr[i8 + 6]));
            sb.append(":");
            sb.append(ByteToHexString(bArr[i8 + 7]));
            sb.append(":");
            sb.append(ByteToHexString(bArr[i8 + 8]));
            String sb2 = sb.toString();
            String valueOf = String.valueOf(getValue(bArr[i8 + 9], i4));
            String valueOf2 = String.valueOf(getValue(bArr[i8 + 10], i4));
            int data = getData(i3, i8 + 11, bArr);
            String str2 = str;
            int data2 = getData(i3, i8 + 13, bArr);
            int i9 = length;
            int value = getValue(bArr[i8 + 15], 0);
            int i10 = i;
            int value2 = getValue(bArr[i8 + 16], 0);
            byte[] bArr2 = new byte[4];
            HashMap hashMap3 = hashMap;
            for (int i11 = 0; i11 < 4; i11++) {
                bArr2[3 - i11] = bArr[i11 + 17 + i8];
            }
            byte[] bArr3 = new byte[4];
            ArrayList arrayList2 = arrayList;
            for (int i12 = 0; i12 < 4; i12++) {
                bArr3[3 - i12] = bArr[i12 + 21 + i8];
            }
            float f = getFloat(bArr2, 0);
            float f2 = getFloat(bArr3, 0);
            hashMap2.put("date", sb2);
            hashMap2.put(DeviceKey.ActivityMode, valueOf);
            hashMap2.put(DeviceKey.HeartRate, valueOf2);
            hashMap2.put(DeviceKey.ActiveMinutes, String.valueOf(data));
            hashMap2.put(DeviceKey.Step, String.valueOf(data2));
            hashMap2.put(DeviceKey.PaceMinutes, String.format("%02d", Integer.valueOf(value)));
            hashMap2.put(DeviceKey.PaceSeconds, String.format("%02d", Integer.valueOf(value2)));
            hashMap2.put("distance", numberFormat2.format(f2));
            hashMap2.put("calories", numberFormat.format(f));
            arrayList2.add(hashMap2);
            i2 = 1;
            i4 = 0;
            str = str2;
            length = i9;
            i = i10;
            i5 = i6;
            i3 = 2;
            arrayList = arrayList2;
            hashMap = hashMap3;
        }
        return hashMap;
    }

    public static float getFloat(byte[] bArr, int i) {
        return Float.intBitsToFloat(getInt(bArr, i));
    }

    public static Map<String, Object> getGoal(byte[] bArr) {
        HashMap hashMap = new HashMap();
        hashMap.put(DeviceKey.DataType, BleConst.GetStepGoal);
        hashMap.put(DeviceKey.End, Boolean.TRUE);
        HashMap hashMap2 = new HashMap();
        hashMap.put(DeviceKey.Data, hashMap2);
        int i = 0;
        int i2 = 0;
        while (i < 4) {
            int i3 = i + 1;
            i2 += getValue(bArr[i3], i);
            i = i3;
        }
        hashMap2.put(DeviceKey.StepGoal, String.valueOf(i2));
        return hashMap;
    }

    public static String getGpsTime(byte[] bArr) {
        return ByteToHexString(bArr[9]) + "." + ByteToHexString(bArr[10]) + "." + ByteToHexString(bArr[11]);
    }

    public static Map<String, Object> getHeartData(byte[] bArr) {
        HashMap hashMap = new HashMap();
        hashMap.put(DeviceKey.DataType, BleConst.GetDynamicHR);
        hashMap.put(DeviceKey.End, Boolean.FALSE);
        ArrayList arrayList = new ArrayList();
        hashMap.put(DeviceKey.Data, arrayList);
        int length = bArr.length;
        int i = length / 24;
        if (i == 0) {
            hashMap.put(DeviceKey.End, Boolean.TRUE);
            return hashMap;
        }
        int i2 = 0;
        while (i2 < i) {
            int i3 = i2 + 1;
            int i4 = (i3 * 24) + 1;
            if (i4 < length && bArr[i4] == -1) {
                hashMap.put(DeviceKey.End, Boolean.TRUE);
            }
            HashMap hashMap2 = new HashMap();
            StringBuilder sb = new StringBuilder();
            sb.append(BleConst.SetAlarmClockWithAllClock);
            int i5 = i2 * 24;
            sb.append(ByteToHexString(bArr[i5 + 3]));
            sb.append(".");
            sb.append(ByteToHexString(bArr[i5 + 4]));
            sb.append(".");
            sb.append(ByteToHexString(bArr[i5 + 5]));
            sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
            sb.append(ByteToHexString(bArr[i5 + 6]));
            sb.append(":");
            sb.append(ByteToHexString(bArr[i5 + 7]));
            sb.append(":");
            sb.append(ByteToHexString(bArr[i5 + 8]));
            String sb2 = sb.toString();
            StringBuffer stringBuffer = new StringBuffer();
            int i6 = 0;
            while (i6 < 15) {
                stringBuffer.append(String.valueOf(getValue(bArr[i6 + 9 + i5], 0)));
                stringBuffer.append(i6 == 14 ? "" : HexStringBuilder.DEFAULT_SEPARATOR);
                i6++;
            }
            hashMap2.put("date", sb2);
            hashMap2.put(DeviceKey.ArrayDynamicHR, stringBuffer.toString());
            arrayList.add(hashMap2);
            i2 = i3;
        }
        return hashMap;
    }

    public static Map<String, Object> getHistoryGpsData(byte[] bArr) {
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        hashMap.put(DeviceKey.DataType, BleConst.Gps);
        int length = bArr.length / 59;
        if (length != 0 && (bArr[bArr.length - 2] != 90 || bArr[bArr.length - 1] != -1)) {
            hashMap.put(DeviceKey.End, Boolean.FALSE);
            int i = 0;
            int i2 = 0;
            while (i2 < length) {
                HashMap hashMap2 = new HashMap();
                StringBuilder sb = new StringBuilder();
                sb.append(BleConst.SetAlarmClockWithAllClock);
                int i3 = i2 * 59;
                sb.append(bcd2String(bArr[i3 + 3]));
                sb.append(".");
                sb.append(bcd2String(bArr[i3 + 4]));
                sb.append(".");
                sb.append(bcd2String(bArr[i3 + 5]));
                sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
                sb.append(bcd2String(bArr[i3 + 6]));
                sb.append(":");
                sb.append(bcd2String(bArr[i3 + 7]));
                sb.append(":");
                sb.append(bcd2String(bArr[i3 + 8]));
                String sb2 = sb.toString();
                int i4 = 4;
                byte[] bArr2 = new byte[4];
                byte[] bArr3 = new byte[4];
                StringBuffer stringBuffer = new StringBuffer();
                StringBuffer stringBuffer2 = new StringBuffer();
                int i5 = i;
                while (i5 < 6) {
                    for (int i6 = i; i6 < i4; i6++) {
                        int i7 = 3 - i6;
                        int i8 = i5 * 8;
                        bArr2[i7] = bArr[i6 + 9 + i3 + i8];
                        bArr3[i7] = bArr[i6 + 13 + i3 + i8];
                    }
                    String valueOf = String.valueOf(getFloat(bArr2, i));
                    String valueOf2 = String.valueOf(getFloat(bArr3, i));
                    stringBuffer.append(valueOf);
                    String str = "";
                    stringBuffer.append(i5 == 5 ? "" : Constants.SEPARATOR_COMMA);
                    stringBuffer2.append(valueOf2);
                    if (i5 != 5) {
                        str = Constants.SEPARATOR_COMMA;
                    }
                    stringBuffer2.append(str);
                    i5++;
                    i = 0;
                    i4 = 4;
                }
                hashMap2.put("date", sb2);
                hashMap2.put(DeviceKey.Latitude, stringBuffer.toString());
                hashMap2.put(DeviceKey.Longitude, stringBuffer2.toString());
                arrayList.add(hashMap2);
                i2++;
                i = 0;
            }
            hashMap.put(DeviceKey.Data, arrayList);
        } else {
            hashMap.put(DeviceKey.End, Boolean.TRUE);
        }
        return hashMap;
    }

    public static Map<String, Object> getHrvTestData(byte[] bArr) {
        HashMap hashMap = new HashMap();
        hashMap.put(DeviceKey.DataType, "42");
        hashMap.put(DeviceKey.End, Boolean.FALSE);
        ArrayList arrayList = new ArrayList();
        hashMap.put(DeviceKey.Data, arrayList);
        int length = bArr.length / 15;
        if (length == 0) {
            hashMap.put(DeviceKey.End, Boolean.TRUE);
            return hashMap;
        }
        if (bArr[bArr.length - 1] == -1) {
            hashMap.put(DeviceKey.End, Boolean.TRUE);
        }
        for (int i = 0; i < length; i++) {
            HashMap hashMap2 = new HashMap();
            StringBuilder sb = new StringBuilder();
            sb.append(BleConst.SetAlarmClockWithAllClock);
            int i2 = i * 15;
            sb.append(ByteToHexString(bArr[i2 + 3]));
            sb.append(".");
            sb.append(ByteToHexString(bArr[i2 + 4]));
            sb.append(".");
            sb.append(ByteToHexString(bArr[i2 + 5]));
            sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
            sb.append(ByteToHexString(bArr[i2 + 6]));
            sb.append(":");
            sb.append(ByteToHexString(bArr[i2 + 7]));
            sb.append(":");
            sb.append(ByteToHexString(bArr[i2 + 8]));
            String sb2 = sb.toString();
            String valueOf = String.valueOf(getValue(bArr[i2 + 9], 0));
            String valueOf2 = String.valueOf(getValue(bArr[i2 + 10], 0));
            String valueOf3 = String.valueOf(getValue(bArr[i2 + 11], 0));
            String valueOf4 = String.valueOf(getValue(bArr[i2 + 12], 0));
            String valueOf5 = String.valueOf(getValue(bArr[i2 + 13], 0));
            String valueOf6 = String.valueOf(getValue(bArr[i2 + 14], 0));
            hashMap2.put("date", sb2);
            hashMap2.put(DeviceKey.HRV, valueOf);
            hashMap2.put(DeviceKey.VascularAging, valueOf2);
            hashMap2.put(DeviceKey.Stress, valueOf4);
            hashMap2.put(DeviceKey.highBP, valueOf5);
            hashMap2.put(DeviceKey.lowBP, valueOf6);
            hashMap2.put(DeviceKey.HeartRate, valueOf3);
            arrayList.add(hashMap2);
        }
        return hashMap;
    }

    public static int getInt(byte[] bArr, int i) {
        return (bArr[i + 3] & 255) | ((bArr[i + 0] << 24) & ViewCompat.MEASURED_STATE_MASK) | ((bArr[i + 1] << 16) & 16711680) | ((bArr[i + 2] << 8) & 65280);
    }

    public static NumberFormat getNumberFormat(int i) {
        NumberFormat numberInstance = NumberFormat.getNumberInstance();
        numberInstance.setMaximumFractionDigits(i);
        numberInstance.setGroupingUsed(false);
        return numberInstance;
    }

    public static Map<String, Object> getOnceHeartData(byte[] bArr) {
        HashMap hashMap = new HashMap();
        hashMap.put(DeviceKey.DataType, BleConst.GetStaticHR);
        hashMap.put(DeviceKey.End, Boolean.FALSE);
        ArrayList arrayList = new ArrayList();
        hashMap.put(DeviceKey.Data, arrayList);
        int length = bArr.length;
        int i = length / 10;
        if (i == 0) {
            hashMap.put(DeviceKey.End, Boolean.TRUE);
            return hashMap;
        }
        int i2 = 0;
        while (i2 < i) {
            int i3 = i2 + 1;
            if (bArr[length - 1] == -1) {
                hashMap.put(DeviceKey.End, Boolean.TRUE);
            }
            HashMap hashMap2 = new HashMap();
            StringBuilder sb = new StringBuilder();
            sb.append(BleConst.SetAlarmClockWithAllClock);
            int i4 = i2 * 10;
            sb.append(ByteToHexString(bArr[i4 + 3]));
            sb.append(".");
            sb.append(ByteToHexString(bArr[i4 + 4]));
            sb.append(".");
            sb.append(ByteToHexString(bArr[i4 + 5]));
            sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
            sb.append(ByteToHexString(bArr[i4 + 6]));
            sb.append(":");
            sb.append(ByteToHexString(bArr[i4 + 7]));
            sb.append(":");
            sb.append(ByteToHexString(bArr[i4 + 8]));
            String sb2 = sb.toString();
            String valueOf = String.valueOf(getValue(bArr[i4 + 9], 0));
            hashMap2.put("date", sb2);
            hashMap2.put(DeviceKey.StaticHR, valueOf);
            arrayList.add(hashMap2);
            i2 = i3;
        }
        return hashMap;
    }

    public static Map<String, Object> getPPG(byte[] bArr) {
        HashMap hashMap = new HashMap();
        hashMap.put(DeviceKey.DataType, BleConst.Getppg);
        hashMap.put(DeviceKey.End, Boolean.TRUE);
        ArrayList arrayList = new ArrayList();
        hashMap.put(DeviceKey.Data, arrayList);
        int length = bArr.length - 3;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 3, bArr2, 0, length);
        for (int i = 0; i < length / 2; i++) {
            HashMap hashMap2 = new HashMap();
            int i2 = i * 2;
            int value = getValue(bArr2[i2], 0) + getValue(bArr2[i2 + 1], 1);
            hashMap2.put(DeviceKey.DataType, Byte.valueOf(bArr[1]));
            hashMap2.put(DeviceKey.arrayPpgRawData, Integer.valueOf(value));
            arrayList.add(hashMap2);
        }
        return hashMap;
    }

    public static Map<String, Object> getSleepData(byte[] bArr) {
        HashMap hashMap = new HashMap();
        hashMap.put(DeviceKey.DataType, BleConst.GetDetailSleepData);
        hashMap.put(DeviceKey.End, Boolean.FALSE);
        ArrayList arrayList = new ArrayList();
        hashMap.put(DeviceKey.Data, arrayList);
        int length = bArr.length;
        int i = length / 34;
        if (i == 0) {
            hashMap.put(DeviceKey.End, Boolean.TRUE);
            return hashMap;
        }
        int i2 = 0;
        while (i2 < i) {
            int i3 = i2 + 1;
            int i4 = (i3 * 34) + 1;
            if (i4 < length && bArr[i4] == -1) {
                hashMap.put(DeviceKey.End, Boolean.TRUE);
            }
            HashMap hashMap2 = new HashMap();
            StringBuilder sb = new StringBuilder();
            sb.append(BleConst.SetAlarmClockWithAllClock);
            int i5 = i2 * 34;
            sb.append(ByteToHexString(bArr[i5 + 3]));
            sb.append(".");
            sb.append(ByteToHexString(bArr[i5 + 4]));
            sb.append(".");
            sb.append(ByteToHexString(bArr[i5 + 5]));
            sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
            sb.append(ByteToHexString(bArr[i5 + 6]));
            sb.append(":");
            sb.append(ByteToHexString(bArr[i5 + 7]));
            sb.append(":");
            sb.append(ByteToHexString(bArr[i5 + 8]));
            hashMap2.put("date", sb.toString());
            int value = getValue(bArr[i5 + 9], 0);
            StringBuffer stringBuffer = new StringBuffer();
            int i6 = 0;
            while (i6 < value) {
                stringBuffer.append(String.valueOf(getValue(bArr[i6 + 10 + i5], 0)));
                stringBuffer.append(i6 == value ? "" : HexStringBuilder.DEFAULT_SEPARATOR);
                i6++;
            }
            hashMap2.put(DeviceKey.ArraySleep, stringBuffer.toString());
            arrayList.add(hashMap2);
            i2 = i3;
        }
        return hashMap;
    }

    public static Map<String, Object> getTempData(byte[] bArr) {
        HashMap hashMap = new HashMap();
        hashMap.put(DeviceKey.DataType, BleConst.GetTempHistoryData);
        hashMap.put(DeviceKey.End, Boolean.FALSE);
        ArrayList arrayList = new ArrayList();
        hashMap.put(DeviceKey.Data, arrayList);
        int length = bArr.length;
        int i = length / 11;
        if (i == 0) {
            hashMap.put(DeviceKey.End, Boolean.TRUE);
            return hashMap;
        }
        NumberFormat numberFormat = getNumberFormat(1);
        int i2 = 0;
        while (i2 < i) {
            int i3 = i2 + 1;
            int i4 = (i3 * 11) + 1;
            if (i4 < length && bArr[i4] == -1) {
                hashMap.put(DeviceKey.End, Boolean.TRUE);
            }
            HashMap hashMap2 = new HashMap();
            StringBuilder sb = new StringBuilder();
            sb.append(BleConst.SetAlarmClockWithAllClock);
            int i5 = i2 * 11;
            sb.append(ByteToHexString(bArr[i5 + 3]));
            sb.append(".");
            sb.append(ByteToHexString(bArr[i5 + 4]));
            sb.append(".");
            sb.append(ByteToHexString(bArr[i5 + 5]));
            sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
            sb.append(ByteToHexString(bArr[i5 + 6]));
            sb.append(":");
            sb.append(ByteToHexString(bArr[i5 + 7]));
            sb.append(":");
            sb.append(ByteToHexString(bArr[i5 + 8]));
            hashMap2.put("date", sb.toString());
            hashMap2.put(DeviceKey.TempData, numberFormat.format((getValue(bArr[i5 + 9], 0) + getValue(bArr[i5 + 10], 1)) * 0.1f));
            arrayList.add(hashMap2);
            i2 = i3;
        }
        return hashMap;
    }

    public static byte getTimeValue(int i) {
        return (byte) Integer.valueOf(Integer.parseInt(i + "", 16)).intValue();
    }

    public static Map<String, Object> getTotalStepData(byte[] bArr) {
        int i;
        String str;
        int value;
        HashMap hashMap = new HashMap();
        hashMap.put(DeviceKey.DataType, BleConst.GetTotalActivityData);
        Boolean bool = Boolean.FALSE;
        String str2 = DeviceKey.End;
        hashMap.put(DeviceKey.End, bool);
        ArrayList arrayList = new ArrayList();
        hashMap.put(DeviceKey.Data, arrayList);
        int a2 = a(bArr);
        int length = bArr.length;
        int i2 = length / a2;
        if (i2 == 0) {
            hashMap.put(DeviceKey.End, Boolean.TRUE);
            return hashMap;
        }
        NumberFormat numberInstance = NumberFormat.getNumberInstance();
        numberInstance.setMinimumFractionDigits(2);
        int i3 = 0;
        numberInstance.setGroupingUsed(false);
        int i4 = 0;
        while (i4 < i2) {
            int i5 = i4 + 1;
            int i6 = (i5 * a2) + 1;
            if (i6 < length && bArr[i6] == -1) {
                hashMap.put(str2, Boolean.TRUE);
            }
            HashMap hashMap2 = new HashMap();
            StringBuilder sb = new StringBuilder();
            sb.append(BleConst.SetAlarmClockWithAllClock);
            int i7 = i4 * a2;
            sb.append(ByteToHexString(bArr[i7 + 2]));
            sb.append(".");
            sb.append(ByteToHexString(bArr[i7 + 3]));
            sb.append(".");
            sb.append(ByteToHexString(bArr[i7 + 4]));
            String sb2 = sb.toString();
            int i8 = i3;
            int i9 = i8;
            while (true) {
                if (i8 >= 4) {
                    break;
                }
                i9 += getValue(bArr[i8 + 5 + i7], i8);
                i8++;
            }
            int i10 = i3;
            int i11 = i10;
            while (i10 < 4) {
                i11 += getValue(bArr[i10 + 9 + i7], i10);
                i10++;
            }
            float f = 0.0f;
            for (int i12 = 0; i12 < 4; i12++) {
                f += getValue(bArr[i12 + 13 + i7], i12);
            }
            float f2 = 0.0f;
            int i13 = 0;
            for (i = 4; i13 < i; i = 4) {
                f2 += getValue(bArr[i13 + 17 + i7], i13);
                i13++;
            }
            if (a2 == 26) {
                value = getValue(bArr[i7 + 21], 0);
                str = str2;
            } else {
                str = str2;
                value = getValue(bArr[i7 + 21], 0) + getValue(bArr[i7 + 22], 1);
            }
            int i14 = length;
            int i15 = 0;
            int i16 = 0;
            for (int i17 = 4; i15 < i17; i17 = 4) {
                i16 += getValue(bArr[(a2 - 4) + i15 + i7], i15);
                i15++;
            }
            hashMap2.put("date", sb2);
            hashMap2.put(DeviceKey.Step, String.valueOf(i9));
            hashMap2.put(DeviceKey.ExerciseMinutes, String.valueOf(i11));
            hashMap2.put("calories", numberInstance.format(f2 / 100.0f));
            hashMap2.put("distance", numberInstance.format(f / 100.0f));
            hashMap2.put(DeviceKey.Goal, String.valueOf(value));
            hashMap2.put(DeviceKey.ActiveMinutes, String.valueOf(i16));
            arrayList.add(hashMap2);
            i4 = i5;
            length = i14;
            str2 = str;
            i3 = 0;
        }
        return hashMap;
    }

    public static Map<String, Object> getUserInfo(byte[] bArr) {
        HashMap hashMap = new HashMap();
        hashMap.put(DeviceKey.DataType, "2");
        hashMap.put(DeviceKey.End, Boolean.TRUE);
        HashMap hashMap2 = new HashMap();
        hashMap.put(DeviceKey.Data, hashMap2);
        String[] strArr = new String[6];
        int i = 0;
        while (i < 5) {
            int i2 = i + 1;
            strArr[i] = String.valueOf(getValue(bArr[i2], 0));
            i = i2;
        }
        String str = "";
        for (int i3 = 6; i3 < 12; i3++) {
            if (bArr[i3] != 0) {
                str = str + ((char) getValue(bArr[i3], 0));
            }
        }
        strArr[5] = str;
        hashMap2.put(DeviceKey.Gender, strArr[0]);
        hashMap2.put(DeviceKey.Age, strArr[1]);
        hashMap2.put(DeviceKey.Height, strArr[2]);
        hashMap2.put(DeviceKey.Weight, strArr[3]);
        hashMap2.put(DeviceKey.Stride, strArr[4]);
        hashMap2.put("deviceId", strArr[5]);
        return hashMap;
    }

    public static int getValue(byte b, int i) {
        return (int) ((b & 255) * Math.pow(256.0d, i));
    }

    public static String[] getWorkOutReminder(byte[] bArr) {
        String bcd2String = bcd2String(bArr[1]);
        String bcd2String2 = bcd2String(bArr[2]);
        return new String[]{bcd2String, bcd2String2, getValue(bArr[3], 0) + "", getByteString(bArr[4]), String.valueOf(getValue(bArr[5], 0)), String.valueOf(getValue(bArr[6], 0) + getValue(bArr[7], 1))};
    }

    public static Map<String, Object> setMacSuccessful() {
        HashMap hashMap = new HashMap();
        hashMap.put(DeviceKey.DataType, BleConst.CMD_Set_Mac);
        hashMap.put(DeviceKey.End, Boolean.TRUE);
        hashMap.put(DeviceKey.Data, new HashMap());
        return hashMap;
    }

    public static Map<String, Object> setMethodSuccessful(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put(DeviceKey.DataType, str);
        hashMap.put(DeviceKey.End, Boolean.TRUE);
        hashMap.put(DeviceKey.Data, new HashMap());
        return hashMap;
    }

    public static Map<String, Object> setTimeSuccessful(byte[] bArr) {
        HashMap hashMap = new HashMap();
        hashMap.put(DeviceKey.DataType, "1");
        hashMap.put(DeviceKey.End, Boolean.TRUE);
        HashMap hashMap2 = new HashMap();
        hashMap.put(DeviceKey.Data, hashMap2);
        hashMap2.put(DeviceKey.KPhoneDataLength, String.valueOf(getValue(bArr[1], 0)));
        return hashMap;
    }

    public static Map<String, Object> updateClockSuccessful(byte[] bArr) {
        HashMap hashMap = new HashMap();
        hashMap.put(DeviceKey.DataType, BleConst.SetAlarmClockWithAllClock);
        hashMap.put(DeviceKey.End, Boolean.TRUE);
        HashMap hashMap2 = new HashMap();
        hashMap.put(DeviceKey.Data, hashMap2);
        hashMap2.put(DeviceKey.KClockLast, String.valueOf(getValue(bArr[bArr.length - 1], 0)));
        return hashMap;
    }
}
