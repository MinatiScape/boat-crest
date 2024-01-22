package com.blankj.utilcode.util;

import androidx.collection.SimpleArrayMap;
import com.blankj.utilcode.constant.RegexConstants;
import com.jstyle.blesdk1860.constant.BleConst;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/* loaded from: classes.dex */
public final class RegexUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final SimpleArrayMap<String, String> f2280a = new SimpleArrayMap<>();

    public RegexUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static List<String> getMatches(String str, CharSequence charSequence) {
        if (charSequence == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        Matcher matcher = Pattern.compile(str).matcher(charSequence);
        while (matcher.find()) {
            arrayList.add(matcher.group());
        }
        return arrayList;
    }

    public static String getReplaceAll(String str, String str2, String str3) {
        return str == null ? "" : Pattern.compile(str2).matcher(str).replaceAll(str3);
    }

    public static String getReplaceFirst(String str, String str2, String str3) {
        return str == null ? "" : Pattern.compile(str2).matcher(str).replaceFirst(str3);
    }

    public static String[] getSplits(String str, String str2) {
        return str == null ? new String[0] : str.split(str2);
    }

    public static boolean isDate(CharSequence charSequence) {
        return isMatch(RegexConstants.REGEX_DATE, charSequence);
    }

    public static boolean isEmail(CharSequence charSequence) {
        return isMatch(RegexConstants.REGEX_EMAIL, charSequence);
    }

    public static boolean isIDCard15(CharSequence charSequence) {
        return isMatch(RegexConstants.REGEX_ID_CARD15, charSequence);
    }

    public static boolean isIDCard18(CharSequence charSequence) {
        return isMatch(RegexConstants.REGEX_ID_CARD18, charSequence);
    }

    public static boolean isIDCard18Exact(CharSequence charSequence) {
        if (isIDCard18(charSequence)) {
            int[] iArr = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
            char[] cArr = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
            SimpleArrayMap<String, String> simpleArrayMap = f2280a;
            if (simpleArrayMap.isEmpty()) {
                simpleArrayMap.put(BleConst.GetDeviceVersion, "北京");
                simpleArrayMap.put(BleConst.CMD_Reset, "天津");
                simpleArrayMap.put(BleConst.CMD_MCUReset, "河北");
                simpleArrayMap.put(BleConst.SetMotorVibrationWithTimes, "山西");
                simpleArrayMap.put(BleConst.GetDeviceName, "内蒙古");
                simpleArrayMap.put(BleConst.GetSedentaryReminder, "辽宁");
                simpleArrayMap.put(BleConst.SetSedentaryReminder, "吉林");
                simpleArrayMap.put(BleConst.RealTimeStep, "黑龙江");
                simpleArrayMap.put(BleConst.DeviceSendDataToAPP, "上海");
                simpleArrayMap.put("32", "江苏");
                simpleArrayMap.put("33", "浙江");
                simpleArrayMap.put("34", "安徽");
                simpleArrayMap.put(BleConst.BackHomeView, "福建");
                simpleArrayMap.put(BleConst.GetTempHistoryData, "江西");
                simpleArrayMap.put(BleConst.ECGDATA, "山东");
                simpleArrayMap.put(BleConst.Gps, "河南");
                simpleArrayMap.put("42", "湖北");
                simpleArrayMap.put(BleConst.CMD_Set_TemperatureCorrection, "湖南");
                simpleArrayMap.put(BleConst.Weather, "广东");
                simpleArrayMap.put(BleConst.Braceletdial, "广西");
                simpleArrayMap.put(BleConst.Braceletdialok, "海南");
                simpleArrayMap.put(BleConst.CMD_Get_WorkOutReminder, "重庆");
                simpleArrayMap.put(BleConst.ReadSerialNumber, "四川");
                simpleArrayMap.put(BleConst.Notify, "贵州");
                simpleArrayMap.put(BleConst.CloseECG, "云南");
                simpleArrayMap.put(BleConst.Language, "西藏");
                simpleArrayMap.put(BleConst.BloodOxygen_PPG, "陕西");
                simpleArrayMap.put(BleConst.AGPS, "甘肃");
                simpleArrayMap.put(BleConst.GetAGPS, "青海");
                simpleArrayMap.put(BleConst.SetMusicControl, "宁夏");
                simpleArrayMap.put(BleConst.GetMusicControl, "新疆");
                simpleArrayMap.put("71", "台湾老");
                simpleArrayMap.put("81", "香港");
                simpleArrayMap.put("82", "澳门");
                simpleArrayMap.put("83", "台湾新");
                simpleArrayMap.put("91", "国外");
            }
            if (simpleArrayMap.get(charSequence.subSequence(0, 2).toString()) != null) {
                int i = 0;
                for (int i2 = 0; i2 < 17; i2++) {
                    i += (charSequence.charAt(i2) - '0') * iArr[i2];
                }
                return charSequence.charAt(17) == cArr[i % 11];
            }
            return false;
        }
        return false;
    }

    public static boolean isIP(CharSequence charSequence) {
        return isMatch(RegexConstants.REGEX_IP, charSequence);
    }

    public static boolean isMatch(String str, CharSequence charSequence) {
        return charSequence != null && charSequence.length() > 0 && Pattern.matches(str, charSequence);
    }

    public static boolean isMobileExact(CharSequence charSequence) {
        return isMobileExact(charSequence, null);
    }

    public static boolean isMobileSimple(CharSequence charSequence) {
        return isMatch(RegexConstants.REGEX_MOBILE_SIMPLE, charSequence);
    }

    public static boolean isTel(CharSequence charSequence) {
        return isMatch(RegexConstants.REGEX_TEL, charSequence);
    }

    public static boolean isURL(CharSequence charSequence) {
        return isMatch(RegexConstants.REGEX_URL, charSequence);
    }

    public static boolean isUsername(CharSequence charSequence) {
        return isMatch(RegexConstants.REGEX_USERNAME, charSequence);
    }

    public static boolean isZh(CharSequence charSequence) {
        return isMatch(RegexConstants.REGEX_ZH, charSequence);
    }

    public static boolean isMobileExact(CharSequence charSequence, List<String> list) {
        if (isMatch(RegexConstants.REGEX_MOBILE_EXACT, charSequence)) {
            return true;
        }
        if (list != null && charSequence != null && charSequence.length() == 11) {
            String charSequence2 = charSequence.toString();
            for (char c : charSequence2.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
            for (String str : list) {
                if (charSequence2.startsWith(str)) {
                    return true;
                }
            }
        }
        return false;
    }
}
