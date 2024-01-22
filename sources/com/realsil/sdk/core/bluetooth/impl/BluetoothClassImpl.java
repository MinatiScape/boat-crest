package com.realsil.sdk.core.bluetooth.impl;

import android.bluetooth.BluetoothClass;
import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.dfu.DfuConstants;
import com.realsil.sdk.dfu.DfuException;
import com.realsil.sdk.dfu.image.constants.SubBinId;
/* loaded from: classes12.dex */
public class BluetoothClassImpl {
    public static final int PROFILE_A2DP = 1;
    public static final int PROFILE_A2DP_SINK = 6;
    public static final int PROFILE_HEADSET = 0;
    public static final int PROFILE_HID = 3;
    public static final int PROFILE_NAP = 5;
    public static final int PROFILE_OPP = 2;
    public static final int PROFILE_PANU = 4;

    /* loaded from: classes12.dex */
    public static class Device {
        public static final int PERIPHERAL_KEYBOARD = 1344;
        public static final int PERIPHERAL_KEYBOARD_POINTING = 1472;
        public static final int PERIPHERAL_NON_KEYBOARD_NON_POINTING = 1280;
        public static final int PERIPHERAL_POINTING = 1408;
    }

    public static boolean doesClassMatch(BluetoothClass bluetoothClass, int i) {
        int deviceClass;
        int deviceClass2;
        int deviceClass3;
        if (i == 1) {
            return bluetoothClass.hasService(262144) || (deviceClass3 = bluetoothClass.getDeviceClass()) == 1044 || deviceClass3 == 1048 || deviceClass3 == 1056 || deviceClass3 == 1064;
        } else if (i == 6) {
            return bluetoothClass.hasService(524288) || (deviceClass2 = bluetoothClass.getDeviceClass()) == 1060 || deviceClass2 == 1064 || deviceClass2 == 1068;
        } else if (i == 0) {
            return bluetoothClass.hasService(262144) || (deviceClass = bluetoothClass.getDeviceClass()) == 1028 || deviceClass == 1032 || deviceClass == 1056;
        } else if (i == 2) {
            if (bluetoothClass.hasService(1048576)) {
                return true;
            }
            switch (bluetoothClass.getDeviceClass()) {
                case 256:
                case 260:
                case DfuException.ERROR_CONNECT_ERROR /* 264 */:
                case DfuException.ERROR_SEND_COMMAND_REACH_MAX_RETRY_TIMES /* 268 */:
                case 272:
                case DfuException.ERROR_REQUEST_MTU_NO_CALLBACK /* 276 */:
                case DfuException.ERROR_ENTER_OTA_MODE_FAILED /* 280 */:
                case 512:
                case 516:
                case 520:
                case DfuConstants.PROGRESS_ACTIVE_IMAGE_AND_RESET /* 524 */:
                case 528:
                case 532:
                    return true;
                default:
                    return false;
            }
        } else if (i == 3) {
            return (bluetoothClass.getDeviceClass() & 1280) == 1280;
        } else if (i == 4 || i == 5) {
            return bluetoothClass.hasService(131072) || (bluetoothClass.getDeviceClass() & 768) == 768;
        } else {
            return false;
        }
    }

    public static boolean isAudioDevice(BluetoothClass bluetoothClass) {
        if (bluetoothClass == null) {
            return false;
        }
        ZLogger.v(String.format("getDeviceClass: 0x%04X", Integer.valueOf(bluetoothClass.getDeviceClass())));
        switch (bluetoothClass.getDeviceClass()) {
            case 1024:
            case DfuException.ERROR_GATT_INVALID_PDU /* 1028 */:
            case 1032:
            case SubBinId.Bbpro.DSP_UI_PARAMETER_FILE /* 1040 */:
            case 1044:
            case 1048:
            case 1052:
            case 1056:
            case 1060:
            case 1064:
            case 1068:
            case 1072:
            case 1076:
            case 1080:
            case 1084:
            case 1088:
            case 1096:
                return true;
            default:
                return doesClassMatch(bluetoothClass, 0) || doesClassMatch(bluetoothClass, 1);
        }
    }

    public static boolean isHidDevice(BluetoothClass bluetoothClass) {
        if (bluetoothClass == null) {
            return false;
        }
        int deviceClass = bluetoothClass.getDeviceClass();
        boolean z = deviceClass == 1344 || deviceClass == 1408 || deviceClass == 1472;
        ZLogger.v(String.format("getDeviceClass: 0x%04X, isHid=%b", Integer.valueOf(bluetoothClass.getDeviceClass()), Boolean.valueOf(z)));
        return z;
    }
}
