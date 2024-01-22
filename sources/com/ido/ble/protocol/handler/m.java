package com.ido.ble.protocol.handler;

import com.google.gson.Gson;
import com.ido.ble.callback.GetDeviceParaCallBack;
import com.ido.ble.logs.LogTool;
import com.ido.ble.protocol.model.ActivitySwitch;
import com.ido.ble.protocol.model.AlarmV3;
import com.ido.ble.protocol.model.AlarmV3CmdParaWrapper;
import com.ido.ble.protocol.model.AllHealthMonitorSwitch;
import com.ido.ble.protocol.model.BtA2dpHfpStatus;
import com.ido.ble.protocol.model.CalorieAndDistanceGoal;
import com.ido.ble.protocol.model.DeviceUpgradeState;
import com.ido.ble.protocol.model.FirmwareAndBt3Version;
import com.ido.ble.protocol.model.MenuList;
import com.ido.ble.protocol.model.NotDisturbPara;
import com.ido.ble.protocol.model.PressCalibrationValue;
import com.ido.ble.protocol.model.ScreenBrightness;
import com.ido.ble.protocol.model.SupportSportInfoV3;
import com.ido.ble.protocol.model.UpHandGesture;
import com.ido.ble.protocol.model.WalkReminder;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes11.dex */
final class m {
    public static void a(int i, int i2, int i3) {
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS == com.veryfit.multi.nativeprotocol.a.a(i2)) {
            return;
        }
        LogTool.b(com.ido.ble.logs.a.f12307a, "[GET_PARA] get device para failed! evt_type =" + i + ", errorCode = " + com.veryfit.multi.nativeprotocol.a.a(i2));
        if (i == 316) {
            i(null, i2);
        } else if (i == 335) {
            a(null, i2);
        } else if (i == 5016) {
            l(null, i2);
        } else if (i == 5018) {
            b(null, i2);
        } else {
            switch (i) {
                case com.veryfit.multi.nativeprotocol.b.i1 /* 325 */:
                    j(null, i2);
                    return;
                case com.veryfit.multi.nativeprotocol.b.j1 /* 326 */:
                    m(null, i2);
                    return;
                case com.veryfit.multi.nativeprotocol.b.k1 /* 327 */:
                    n(null, i2);
                    return;
                default:
                    switch (i) {
                        case com.veryfit.multi.nativeprotocol.b.l1 /* 331 */:
                            k(null, i2);
                            return;
                        case com.veryfit.multi.nativeprotocol.b.m1 /* 332 */:
                            o(null, i2);
                            return;
                        case com.veryfit.multi.nativeprotocol.b.n1 /* 333 */:
                            c(null, i2);
                            return;
                        default:
                            return;
                    }
            }
        }
    }

    public static void a(int i, byte[] bArr, int i2) {
        if (i == 316) {
            i(bArr, i2);
        } else if (i == 323) {
            h(bArr, i2);
        } else if (i == 5016) {
            l(bArr, i2);
        } else if (i == 5018) {
            b(bArr, i2);
        } else if (i == 352) {
            e(bArr, i2);
        } else if (i == 353) {
            f(bArr, i2);
        } else {
            switch (i) {
                case com.veryfit.multi.nativeprotocol.b.i1 /* 325 */:
                    j(bArr, i2);
                    return;
                case com.veryfit.multi.nativeprotocol.b.j1 /* 326 */:
                    m(bArr, i2);
                    return;
                case com.veryfit.multi.nativeprotocol.b.k1 /* 327 */:
                    n(bArr, i2);
                    return;
                default:
                    switch (i) {
                        case com.veryfit.multi.nativeprotocol.b.l1 /* 331 */:
                            k(bArr, i2);
                            return;
                        case com.veryfit.multi.nativeprotocol.b.m1 /* 332 */:
                            o(bArr, i2);
                            return;
                        case com.veryfit.multi.nativeprotocol.b.n1 /* 333 */:
                            c(bArr, i2);
                            return;
                        default:
                            switch (i) {
                                case com.veryfit.multi.nativeprotocol.b.p1 /* 335 */:
                                    a(bArr, i2);
                                    return;
                                case com.veryfit.multi.nativeprotocol.b.q1 /* 336 */:
                                    d(bArr, i2);
                                    return;
                                case com.veryfit.multi.nativeprotocol.b.r1 /* 337 */:
                                    g(bArr, i2);
                                    return;
                                default:
                                    return;
                            }
                    }
            }
        }
    }

    private static void a(byte[] bArr, int i) {
        ActivitySwitch activitySwitch;
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            activitySwitch = null;
        } else {
            String d = com.ido.ble.common.c.d(bArr);
            LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_PARA] [handleActivitySwitch] jsonString is " + d);
            activitySwitch = (ActivitySwitch) new Gson().fromJson(d, (Class<Object>) ActivitySwitch.class);
        }
        GetDeviceParaCallBack.onGetActivitySwitch(activitySwitch);
    }

    public static boolean a(int i) {
        if (i == 316 || i == 323 || i == 5016 || i == 5018 || i == 352 || i == 353) {
            return true;
        }
        switch (i) {
            case com.veryfit.multi.nativeprotocol.b.i1 /* 325 */:
            case com.veryfit.multi.nativeprotocol.b.j1 /* 326 */:
            case com.veryfit.multi.nativeprotocol.b.k1 /* 327 */:
                return true;
            default:
                switch (i) {
                    case com.veryfit.multi.nativeprotocol.b.l1 /* 331 */:
                    case com.veryfit.multi.nativeprotocol.b.m1 /* 332 */:
                    case com.veryfit.multi.nativeprotocol.b.n1 /* 333 */:
                        return true;
                    default:
                        switch (i) {
                            case com.veryfit.multi.nativeprotocol.b.p1 /* 335 */:
                            case com.veryfit.multi.nativeprotocol.b.q1 /* 336 */:
                            case com.veryfit.multi.nativeprotocol.b.r1 /* 337 */:
                                return true;
                            default:
                                return false;
                        }
                }
        }
    }

    private static void b(byte[] bArr, int i) {
        List<AlarmV3> list;
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS == com.veryfit.multi.nativeprotocol.a.a(i)) {
            String d = com.ido.ble.common.c.d(bArr);
            LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_PARA] [handleAlarmV3] jsonString is " + d);
            AlarmV3CmdParaWrapper.AlarmGetResponse alarmGetResponse = (AlarmV3CmdParaWrapper.AlarmGetResponse) new Gson().fromJson(d, (Class<Object>) AlarmV3CmdParaWrapper.AlarmGetResponse.class);
            if (alarmGetResponse != null) {
                list = alarmGetResponse.item;
                GetDeviceParaCallBack.onGetAlarmV3(list);
            }
        }
        list = null;
        GetDeviceParaCallBack.onGetAlarmV3(list);
    }

    private static void c(byte[] bArr, int i) {
        AllHealthMonitorSwitch allHealthMonitorSwitch;
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            allHealthMonitorSwitch = null;
        } else {
            String d = com.ido.ble.common.c.d(bArr);
            LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_PARA] [handleAllHealthMonitorState] jsonString is " + d);
            allHealthMonitorSwitch = (AllHealthMonitorSwitch) new Gson().fromJson(d, (Class<Object>) AllHealthMonitorSwitch.class);
        }
        GetDeviceParaCallBack.onGetAllHealthMonitorSwitch(allHealthMonitorSwitch);
    }

    private static void d(byte[] bArr, int i) {
        FirmwareAndBt3Version firmwareAndBt3Version;
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            firmwareAndBt3Version = null;
        } else {
            String d = com.ido.ble.common.c.d(bArr);
            LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_PARA] [handleFirmwareAndBt3Version] jsonString is " + d);
            firmwareAndBt3Version = (FirmwareAndBt3Version) new Gson().fromJson(d, (Class<Object>) FirmwareAndBt3Version.class);
        }
        GetDeviceParaCallBack.onGetFirmwareAndBt3Version(firmwareAndBt3Version);
    }

    private static void e(byte[] bArr, int i) {
        BtA2dpHfpStatus btA2dpHfpStatus;
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            btA2dpHfpStatus = null;
        } else {
            String d = com.ido.ble.common.c.d(bArr);
            LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_PARA] [handleGetBtA2dpHfpStatus] jsonString is " + d);
            btA2dpHfpStatus = (BtA2dpHfpStatus) new Gson().fromJson(d, (Class<Object>) BtA2dpHfpStatus.class);
        }
        GetDeviceParaCallBack.onGetBtA2dpHfpStatus(btA2dpHfpStatus);
    }

    private static void f(byte[] bArr, int i) {
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            GetDeviceParaCallBack.onGetContactReceiveTime(false);
            return;
        }
        String d = com.ido.ble.common.c.d(bArr);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_PARA] [handleGetContactReceiveTime] jsonString is " + d);
        try {
            if (new JSONObject(d).getInt("result") == 1) {
                GetDeviceParaCallBack.onGetContactReceiveTime(true);
            } else {
                GetDeviceParaCallBack.onGetContactReceiveTime(false);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static void g(byte[] bArr, int i) {
        PressCalibrationValue pressCalibrationValue;
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            pressCalibrationValue = null;
        } else {
            String d = com.ido.ble.common.c.d(bArr);
            LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_PARA] [handleGetPressValue] jsonString is " + d);
            pressCalibrationValue = (PressCalibrationValue) new Gson().fromJson(d, (Class<Object>) PressCalibrationValue.class);
        }
        GetDeviceParaCallBack.onGetPressCalibrationValue(pressCalibrationValue);
    }

    private static void h(byte[] bArr, int i) {
        MenuList.DeviceReturnInfo deviceReturnInfo;
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            deviceReturnInfo = null;
        } else {
            String d = com.ido.ble.common.c.d(bArr);
            LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_PARA] [handleMenuList] jsonString is " + d);
            deviceReturnInfo = (MenuList.DeviceReturnInfo) new Gson().fromJson(d, (Class<Object>) MenuList.DeviceReturnInfo.class);
        }
        GetDeviceParaCallBack.onGetMenuList(deviceReturnInfo);
    }

    private static void i(byte[] bArr, int i) {
        NotDisturbPara notDisturbPara;
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            notDisturbPara = null;
        } else {
            String d = com.ido.ble.common.c.d(bArr);
            LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_PARA] [handleNotDisturbPara] jsonString is " + d);
            notDisturbPara = (NotDisturbPara) new Gson().fromJson(d, (Class<Object>) NotDisturbPara.class);
        }
        GetDeviceParaCallBack.onGetDoNotDisturbPara(notDisturbPara);
    }

    private static void j(byte[] bArr, int i) {
        ScreenBrightness screenBrightness;
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            screenBrightness = null;
        } else {
            String d = com.ido.ble.common.c.d(bArr);
            LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_PARA] [handleScreenBrightness] jsonString is " + d);
            screenBrightness = (ScreenBrightness) new Gson().fromJson(d, (Class<Object>) ScreenBrightness.class);
        }
        GetDeviceParaCallBack.onGetScreenBrightness(screenBrightness);
    }

    private static void k(byte[] bArr, int i) {
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            GetDeviceParaCallBack.onGetSportThreeCircleGoal(null, com.ido.ble.bluetooth.a.e());
            return;
        }
        String d = com.ido.ble.common.c.d(bArr);
        LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_PARA] [handleSportThreeCircleGoal] jsonString is " + d);
        GetDeviceParaCallBack.onGetSportThreeCircleGoal((CalorieAndDistanceGoal) new Gson().fromJson(d, (Class<Object>) CalorieAndDistanceGoal.class), com.ido.ble.bluetooth.a.e());
    }

    private static void l(byte[] bArr, int i) {
        SupportSportInfoV3 supportSportInfoV3;
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            supportSportInfoV3 = null;
        } else {
            String d = com.ido.ble.common.c.d(bArr);
            LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_PARA] [handleSupportSportInfoV3] jsonString is " + d);
            supportSportInfoV3 = (SupportSportInfoV3) new Gson().fromJson(d, (Class<Object>) SupportSportInfoV3.class);
        }
        GetDeviceParaCallBack.onGetSupportSportInfoV3(supportSportInfoV3);
    }

    private static void m(byte[] bArr, int i) {
        UpHandGesture upHandGesture;
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            upHandGesture = null;
        } else {
            String d = com.ido.ble.common.c.d(bArr);
            LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_PARA] [handleUpHandGesture] jsonString is " + d);
            upHandGesture = (UpHandGesture) new Gson().fromJson(d, (Class<Object>) UpHandGesture.class);
        }
        GetDeviceParaCallBack.onGetUpHandGesture(upHandGesture);
    }

    private static void n(byte[] bArr, int i) {
        DeviceUpgradeState deviceUpgradeState;
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            deviceUpgradeState = null;
        } else {
            String d = com.ido.ble.common.c.d(bArr);
            LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_PARA] [handleUpgradeState] jsonString is " + d);
            deviceUpgradeState = (DeviceUpgradeState) new Gson().fromJson(d, (Class<Object>) DeviceUpgradeState.class);
        }
        GetDeviceParaCallBack.onGetDeviceUpgradeState(deviceUpgradeState);
    }

    private static void o(byte[] bArr, int i) {
        WalkReminder walkReminder;
        if (com.veryfit.multi.nativeprotocol.a.SUCCESS != com.veryfit.multi.nativeprotocol.a.a(i)) {
            walkReminder = null;
        } else {
            String d = com.ido.ble.common.c.d(bArr);
            LogTool.d(com.ido.ble.logs.a.f12307a, "[GET_PARA] [handleWalkReminder] jsonString is " + d);
            walkReminder = (WalkReminder) new Gson().fromJson(d, (Class<Object>) WalkReminder.class);
        }
        GetDeviceParaCallBack.onGetWalkReminder(walkReminder);
    }
}
