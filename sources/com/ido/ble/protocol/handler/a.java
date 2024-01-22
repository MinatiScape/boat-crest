package com.ido.ble.protocol.handler;

import com.google.gson.Gson;
import com.ido.ble.callback.AppControlDeviceCallBack;
import com.ido.ble.callback.BloodPressureMeasureCallBack;
import com.ido.ble.protocol.model.BloodPressureMeasureDeviceReplyData;
/* loaded from: classes11.dex */
final class a {
    public static void a(int i, int i2, int i3) {
        AppControlDeviceCallBack.AppControlType appControlType;
        switch (i) {
            case 500:
                appControlType = AppControlDeviceCallBack.AppControlType.MUSIC_PLAY_ENTER;
                break;
            case 501:
                appControlType = AppControlDeviceCallBack.AppControlType.MUSIC_PLAY_EXIT;
                break;
            case 502:
                appControlType = AppControlDeviceCallBack.AppControlType.CAMERA_ENTER;
                break;
            case 503:
                appControlType = AppControlDeviceCallBack.AppControlType.CAMERA_EXIT;
                break;
            case 504:
                appControlType = AppControlDeviceCallBack.AppControlType.FIND_DEVICE_ENTER;
                break;
            case 505:
                appControlType = AppControlDeviceCallBack.AppControlType.FIND_DEVICE_EXIT;
                break;
            case 506:
                appControlType = AppControlDeviceCallBack.AppControlType.OPEN_ANCS;
                break;
            case 507:
                appControlType = AppControlDeviceCallBack.AppControlType.CLOSE_ANCS;
                break;
            default:
                return;
        }
        a(i2, appControlType);
    }

    private static void a(int i, AppControlDeviceCallBack.AppControlType appControlType) {
        AppControlDeviceCallBack.a(i == 0 ? Boolean.TRUE : Boolean.FALSE, appControlType);
    }

    public static void a(int i, byte[] bArr, int i2) {
        if (i == 127) {
            a(bArr);
        }
    }

    private static void a(byte[] bArr) {
        BloodPressureMeasureCallBack.a((BloodPressureMeasureDeviceReplyData) new Gson().fromJson(com.ido.ble.common.c.d(bArr), (Class<Object>) BloodPressureMeasureDeviceReplyData.class));
    }

    public static boolean a(int i) {
        if (i != 127) {
            switch (i) {
                case 500:
                case 501:
                case 502:
                case 503:
                case 504:
                case 505:
                case 506:
                case 507:
                case 508:
                case 509:
                case 510:
                    return true;
                default:
                    return false;
            }
        }
        return true;
    }
}
