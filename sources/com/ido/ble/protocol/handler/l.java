package com.ido.ble.protocol.handler;

import android.text.TextUtils;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.gson.Gson;
import com.ido.ble.callback.ConnectCallBack;
import com.ido.ble.callback.GetDeviceInfoCallBack;
import com.ido.ble.callback.UserHabitCallBack;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.model.ActivityDataCount;
import com.ido.ble.protocol.model.BasicInfo;
import com.ido.ble.protocol.model.BatteryInfo;
import com.ido.ble.protocol.model.CanDownLangInfo;
import com.ido.ble.protocol.model.CanDownLangInfoV3;
import com.ido.ble.protocol.model.DeviceSummarySoftVersionInfo;
import com.ido.ble.protocol.model.FlashBinInfo;
import com.ido.ble.protocol.model.HIDInfo;
import com.ido.ble.protocol.model.HabitInfo;
import com.ido.ble.protocol.model.LiveData;
import com.ido.ble.protocol.model.MacAddressInfo;
import com.ido.ble.protocol.model.NoticeReminderSwitchStatus;
import com.ido.ble.protocol.model.SupportFunctionInfo;
import org.json.JSONArray;
import org.json.JSONObject;
/* loaded from: classes11.dex */
final class l {
    private static String a(JSONArray jSONArray) {
        String replace;
        byte[] bArr = new byte[jSONArray.length()];
        for (int i = 0; i < jSONArray.length(); i++) {
            bArr[i] = (byte) jSONArray.getInt(i);
        }
        String b = com.ido.ble.common.c.b(bArr);
        if (TextUtils.isEmpty(b)) {
            return null;
        }
        return b.replace(HexStringBuilder.DEFAULT_SEPARATOR, ":").substring(0, replace.length() - 1).toUpperCase();
    }

    public static void a(int i, int i2, int i3) {
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS == com.veryfit.multi.nativeprotocol.a.a(i2)) {
            return;
        }
        LogTool.b(com.ido.ble.logs.a.f12307a, "[GET_INFO] get info failed! evt_type =" + i + ", errorCode = " + com.veryfit.multi.nativeprotocol.a.a(i2));
        if (i == 300) {
            l(null, i2);
        } else if (i == 301) {
            b(null, i2);
        } else if (i == 310) {
            i(null, i2);
        } else {
            switch (i) {
                case 303:
                    p(null, i2);
                    return;
                case 304:
                    k(null, i2);
                    return;
                case 305:
                    g(null, i2);
                    return;
                default:
                    return;
            }
        }
    }

    public static void a(int i, byte[] bArr, int i2) {
        if (i == 300) {
            l(bArr, i2);
        } else if (i == 301) {
            b(bArr, i2);
        } else if (i == 309) {
            a(bArr, i2);
        } else if (i == 310) {
            i(bArr, i2);
        } else if (i == 315) {
            f(bArr, i2);
        } else if (i == 319) {
            d(bArr, i2);
        } else if (i == 5024) {
            e(bArr, i2);
        } else if (i == 5059) {
            j(bArr, i2);
        } else if (i == 321) {
            c(bArr, i2);
        } else if (i == 322) {
            h(bArr, i2);
        } else {
            switch (i) {
                case 303:
                    p(bArr, i2);
                    return;
                case 304:
                    k(bArr, i2);
                    return;
                case 305:
                    g(bArr, i2);
                    return;
                case 306:
                    m(bArr, i2);
                    return;
                default:
                    return;
            }
        }
    }

    private static void a(BasicInfo basicInfo) {
        if (basicInfo != null && com.ido.ble.bluetooth.a.g() && basicInfo.pairFlag == 0) {
            ConnectCallBack.e(com.ido.ble.bluetooth.a.e());
        }
    }

    private static void a(byte[] bArr, int i) {
        String d = com.ido.ble.common.c.d(bArr);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_INFO] [handleActivityCount] " + d);
        GetDeviceInfoCallBack.a(TextUtils.isEmpty(d) ? null : (ActivityDataCount) com.ido.ble.common.k.c(d, ActivityDataCount.class));
    }

    public static boolean a(int i) {
        if (i == 300 || i == 301 || i == 315 || i == 319 || i == 5024 || i == 5059 || i == 321 || i == 322) {
            return true;
        }
        switch (i) {
            case 303:
            case 304:
            case 305:
            case 306:
            case 307:
            case 308:
            case 309:
            case 310:
                return true;
            default:
                return false;
        }
    }

    private static void b(byte[] bArr, int i) {
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            GetDeviceInfoCallBack.a((BasicInfo) null);
            return;
        }
        String d = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(d)) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[GET_INFO] [handleBasicInfo] json is null");
            GetDeviceInfoCallBack.a((BasicInfo) null);
            return;
        }
        LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_INFO] [handleBasicInfo] json is" + d);
        BasicInfo basicInfo = (BasicInfo) new Gson().fromJson(d, (Class<Object>) BasicInfo.class);
        GetDeviceInfoCallBack.a(basicInfo);
        a(basicInfo);
    }

    private static void c(byte[] bArr, int i) {
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            GetDeviceInfoCallBack.a((BatteryInfo) null);
            return;
        }
        String d = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(d)) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[GET_INFO] [handleBatteryInfo] json is null");
            GetDeviceInfoCallBack.a((BatteryInfo) null);
            return;
        }
        LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_INFO] [handleBatteryInfo] json is" + d);
        GetDeviceInfoCallBack.a((BatteryInfo) new Gson().fromJson(d, (Class<Object>) BatteryInfo.class));
    }

    private static void d(byte[] bArr, int i) {
        CanDownLangInfo canDownLangInfo;
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            canDownLangInfo = null;
        } else {
            String d = com.ido.ble.common.c.d(bArr);
            if (TextUtils.isEmpty(d)) {
                LogTool.b(com.ido.ble.logs.a.f12307a, "[GET_INFO] [handleCanDownloadLangInfo] json is null");
                return;
            }
            LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_INFO] [handleCanDownloadLangInfo] json is" + d);
            canDownLangInfo = (CanDownLangInfo) new Gson().fromJson(d, (Class<Object>) CanDownLangInfo.class);
        }
        GetDeviceInfoCallBack.a(canDownLangInfo);
    }

    private static void e(byte[] bArr, int i) {
        CanDownLangInfoV3 canDownLangInfoV3;
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            canDownLangInfoV3 = null;
        } else {
            String d = com.ido.ble.common.c.d(bArr);
            if (TextUtils.isEmpty(d)) {
                LogTool.b(com.ido.ble.logs.a.f12307a, "[GET_INFO] [handleCanDownloadLangInfoV3] json is null");
                return;
            }
            LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_INFO] [handleCanDownloadLangInfoV3] json is" + d);
            canDownLangInfoV3 = (CanDownLangInfoV3) new Gson().fromJson(d, (Class<Object>) CanDownLangInfoV3.class);
        }
        GetDeviceInfoCallBack.a(canDownLangInfoV3);
    }

    private static void f(byte[] bArr, int i) {
        GetDeviceInfoCallBack.a(com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i) ? null : (DeviceSummarySoftVersionInfo) new Gson().fromJson(com.ido.ble.common.c.d(bArr), (Class<Object>) DeviceSummarySoftVersionInfo.class));
    }

    private static void g(byte[] bArr, int i) {
    }

    private static void h(byte[] bArr, int i) {
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            GetDeviceInfoCallBack.a((FlashBinInfo) null);
            return;
        }
        String d = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(d)) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[GET_INFO] [handleFlashBinInfo] json is null");
            GetDeviceInfoCallBack.a((BasicInfo) null);
            return;
        }
        LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_INFO] [handleFlashBinInfo] json is" + d);
        GetDeviceInfoCallBack.a((FlashBinInfo) new Gson().fromJson(d, (Class<Object>) FlashBinInfo.class));
    }

    private static void i(byte[] bArr, int i) {
        GetDeviceInfoCallBack.a(com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i) ? null : (HIDInfo) new Gson().fromJson(com.ido.ble.common.c.d(bArr), (Class<Object>) HIDInfo.class));
    }

    private static void j(byte[] bArr, int i) {
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            UserHabitCallBack.onGetHabitInfo(null);
            return;
        }
        String d = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(d)) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[GET_INFO] [handleHabitInfo] json is null");
            UserHabitCallBack.onGetHabitInfo(null);
            return;
        }
        LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_INFO] [handleHabitInfo] json is" + d);
        UserHabitCallBack.onGetHabitInfo((HabitInfo) new Gson().fromJson(d, (Class<Object>) HabitInfo.class));
    }

    private static void k(byte[] bArr, int i) {
        GetDeviceInfoCallBack.a(com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i) ? null : (LiveData) new Gson().fromJson(com.ido.ble.common.c.d(bArr), (Class<Object>) LiveData.class));
    }

    private static void l(byte[] bArr, int i) {
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS == com.veryfit.multi.nativeprotocol.a.a(i)) {
            String d = com.ido.ble.common.c.d(bArr);
            LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_INFO] [handleMacAddress] json is" + d);
            if (TextUtils.isEmpty(d)) {
                LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_INFO] [handleMacAddress] json is null");
                GetDeviceInfoCallBack.a((MacAddressInfo) null);
                return;
            }
            try {
                MacAddressInfo macAddressInfo = new MacAddressInfo();
                JSONObject jSONObject = new JSONObject(d);
                if (jSONObject.has("macAddr")) {
                    macAddressInfo.bleAddress = a(jSONObject.getJSONArray("macAddr"));
                }
                if (jSONObject.has("btAddr")) {
                    macAddressInfo.btAddress = a(jSONObject.getJSONArray("btAddr"));
                }
                GetDeviceInfoCallBack.a(macAddressInfo);
                return;
            } catch (Exception unused) {
            }
        }
        GetDeviceInfoCallBack.a((MacAddressInfo) null);
    }

    private static void m(byte[] bArr, int i) {
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            GetDeviceInfoCallBack.a((NoticeReminderSwitchStatus) null);
            return;
        }
        String d = com.ido.ble.common.c.d(bArr);
        if (TextUtils.isEmpty(d)) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[GET_INFO] [handleNoticeReminderSwitchStatus] json is null");
            GetDeviceInfoCallBack.a((NoticeReminderSwitchStatus) null);
            return;
        }
        LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_INFO] [handleNoticeReminderSwitchStatus] json is" + d);
        GetDeviceInfoCallBack.a((NoticeReminderSwitchStatus) new Gson().fromJson(d, (Class<Object>) NoticeReminderSwitchStatus.class));
    }

    private static void n(byte[] bArr, int i) {
    }

    private static void o(byte[] bArr, int i) {
    }

    private static void p(byte[] bArr, int i) {
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            GetDeviceInfoCallBack.a((SupportFunctionInfo) null);
            LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_INFO] [handleSupportFunctionInfo] get failed, errorCode=" + i);
            return;
        }
        String d = com.ido.ble.common.c.d(bArr);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_INFO] [handleSupportFunctionInfo] json is" + d);
        SupportFunctionInfo supportFunctionInfo = (SupportFunctionInfo) new Gson().fromJson(d, (Class<Object>) SupportFunctionInfo.class);
        if (supportFunctionInfo == null) {
            LogTool.b(com.ido.ble.logs.a.f12307a, "[GET_INFO] [handleSupportFunctionInfo] transform json failed!");
        } else {
            com.ido.ble.f.a.f.a.g0().a(supportFunctionInfo);
        }
        GetDeviceInfoCallBack.a(supportFunctionInfo);
    }
}
