package com.ido.ble.protocol.handler;

import com.ido.ble.callback.OtherProtocolCallBack;
/* loaded from: classes11.dex */
public class o {
    public static void a(int i, int i2, int i3) {
        OtherProtocolCallBack.SettingType settingType;
        switch (i) {
            case 159:
                settingType = OtherProtocolCallBack.SettingType.MENSTRUAL;
                break;
            case 160:
                settingType = OtherProtocolCallBack.SettingType.MENSTRUAL_REMIND;
                break;
            case 161:
                settingType = OtherProtocolCallBack.SettingType.CALORIE_DISTANCE_GOAL;
                break;
            case 162:
            default:
                return;
            case 163:
                settingType = OtherProtocolCallBack.SettingType.PRESSURE;
                break;
            case 164:
                settingType = OtherProtocolCallBack.SettingType.SPORT_MODE_SORT;
                break;
        }
        a(i2, settingType);
    }

    private static void a(int i, OtherProtocolCallBack.SettingType settingType) {
        OtherProtocolCallBack.onSetCallBack(i == 0 ? Boolean.TRUE : Boolean.FALSE, settingType);
    }

    public static void a(int i, byte[] bArr, int i2) {
        if (i != 162) {
            return;
        }
        a(i2, OtherProtocolCallBack.SettingType.SPO2);
    }

    public static boolean a(int i) {
        switch (i) {
            case 159:
            case 160:
            case 161:
            case 162:
            case 163:
            case 164:
                return true;
            default:
                return false;
        }
    }
}
