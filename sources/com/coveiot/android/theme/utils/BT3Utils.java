package com.coveiot.android.theme.utils;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.Build;
import androidx.core.content.ContextCompat;
import com.coveiot.android.bleabstract.api.BleApiManager;
import com.coveiot.android.bleabstract.models.ConnectionStatus;
import com.coveiot.android.bleabstract.preferences.PreferenceManagerAbstract;
import com.coveiot.android.devicemodels.DeviceUtils;
import com.coveiot.android.theme.CleverTapConstants;
import com.coveiot.covepreferences.SessionManager;
import com.coveiot.covepreferences.UserDataManager;
import com.coveiot.covepreferences.data.AclBT3LastReceivedConnectionStatus;
import com.coveiot.covepreferences.data.BatteryLevelData;
import com.coveiot.utils.utility.AppUtils;
import com.coveiot.utils.utility.LogHelper;
import java.util.HashMap;
/* loaded from: classes7.dex */
public class BT3Utils {
    public static void checkBT3ConnectionStatusAndLogEvent(Context context, Boolean bool) {
        HashMap<String, Object> hashMap = new HashMap<>();
        if (bool.booleanValue()) {
            hashMap.put(CleverTapConstants.CustomEventProperties.HP_LOAD_SOURCE.getValue(), CleverTapConstants.CustomEventValues.ONBOARDING.getValue());
        } else {
            hashMap.put(CleverTapConstants.CustomEventProperties.HP_LOAD_SOURCE.getValue(), CleverTapConstants.CustomEventValues.ORGANIC.getValue());
            BatteryLevelData batteryLevelData = UserDataManager.getInstance(context).getBatteryLevelData();
            if (batteryLevelData != null) {
                hashMap.put(CleverTapConstants.CustomEventProperties.BATTERY_PERCENTAGE.getValue(), Integer.valueOf(batteryLevelData.batteryLevel).toString());
            }
        }
        hashMap.put(CleverTapConstants.CustomEventProperties.HP_LOAD_USER_STATUS.getValue(), CleverTapConstants.CustomEventValues.LOGGEDIN.getValue());
        hashMap.put(CleverTapConstants.CustomEventProperties.HP_PAIRED.getValue(), CleverTapConstants.CustomEventValues.YES.getValue());
        ConnectionStatus connectionStatus = BleApiManager.getInstance(context).getBleApi().getConnectionStatus();
        ConnectionStatus connectionStatus2 = ConnectionStatus.CONNECTED;
        if (connectionStatus == connectionStatus2) {
            String value = CleverTapConstants.CustomEventProperties.HP_LOAD_WATCH_BT_STATUS.getValue();
            CleverTapConstants.CustomEventValues customEventValues = CleverTapConstants.CustomEventValues.CONNECTED;
            hashMap.put(value, customEventValues.getValue());
            String macAddress = BleApiManager.getInstance(context).getBleApi().getMacAddress();
            if (BleApiManager.getInstance(context).getBleApi().getDeviceSupportedFeatures().isKahaBTCallSupported()) {
                macAddress = UserDataManager.getInstance(context).getConnectedBTCallDeviceMac();
            } else if (DeviceUtils.isTouchELXDevice(context)) {
                macAddress = PreferenceManagerAbstract.getInstance(context).getConnectedDeviceBT3MacAddress();
            } else if (DeviceUtils.isEastApexDevice(context)) {
                macAddress = BleApiManager.getInstance(context).getBleApi().getMacAddress();
            }
            if (getBTDeviceBondedState(macAddress, context).intValue() == 12) {
                if (DeviceUtils.Companion.isOPPDevice(context)) {
                    AclBT3LastReceivedConnectionStatus aclBt3LastReceivedConnectionStatus = SessionManager.getInstance(context).getAclBt3LastReceivedConnectionStatus();
                    if (aclBt3LastReceivedConnectionStatus != null && (aclBt3LastReceivedConnectionStatus.getConnectionStatus() != connectionStatus2 || aclBt3LastReceivedConnectionStatus.getMacAddress() != macAddress)) {
                        hashMap.put(CleverTapConstants.CustomEventProperties.HP_LOAD_WATCH_BT_CALLING_STATUS.getValue(), CleverTapConstants.CustomEventValues.DISCONNECTED.getValue());
                    } else {
                        hashMap.put(CleverTapConstants.CustomEventProperties.HP_LOAD_WATCH_BT_CALLING_STATUS.getValue(), customEventValues.getValue());
                    }
                } else {
                    hashMap.put(CleverTapConstants.CustomEventProperties.HP_LOAD_WATCH_BT_CALLING_STATUS.getValue(), customEventValues.getValue());
                }
            } else {
                hashMap.put(CleverTapConstants.CustomEventProperties.HP_LOAD_WATCH_BT_CALLING_STATUS.getValue(), CleverTapConstants.CustomEventValues.DISCONNECTED.getValue());
            }
        } else {
            hashMap.put(CleverTapConstants.CustomEventProperties.HP_LOAD_WATCH_BT_STATUS.getValue(), CleverTapConstants.CustomEventValues.DISCONNECTED.getValue());
        }
        DeviceUtils.Companion companion = DeviceUtils.Companion;
        hashMap.putAll(companion.getDeviceId(context));
        hashMap.putAll(companion.getWatchDetails(context));
        if (bool.booleanValue()) {
            companion.logAnalyticsEvent(CleverTapConstants.EventName.ONBOARD_HP_VIEWED.getValue(), hashMap);
        } else {
            companion.logAnalyticsEvent(CleverTapConstants.EventName.HP_VIEWED.getValue(), hashMap);
        }
    }

    public static Integer getBTDeviceBondedState(String str, Context context) {
        int i = 10;
        if (str == null || str.isEmpty() || !AppUtils.isBluetoothEnabled(context)) {
            return i;
        }
        try {
            return (ContextCompat.checkSelfPermission(context, "android.permission.BLUETOOTH_CONNECT") == 0 || Build.VERSION.SDK_INT < 31) ? Integer.valueOf(BluetoothAdapter.getDefaultAdapter().getRemoteDevice(str).getBondState()) : i;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return i;
        } catch (SecurityException e2) {
            e2.printStackTrace();
            return i;
        }
    }

    public static String getPairedRemoteDeviceName(String str, Context context) {
        if (str != null && !str.isEmpty() && AppUtils.isBluetoothEnabled(context)) {
            try {
                BluetoothDevice remoteDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(str);
                if (ContextCompat.checkSelfPermission(context, "android.permission.BLUETOOTH_CONNECT") == 0 || Build.VERSION.SDK_INT < 31) {
                    return remoteDevice.getName();
                }
                return null;
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (SecurityException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    public static void unpairDevice(String str) {
        try {
            BluetoothDevice remoteDevice = BluetoothAdapter.getDefaultAdapter().getRemoteDevice(str);
            remoteDevice.getClass().getMethod("removeBond", null).invoke(remoteDevice, null);
        } catch (Exception e) {
            LogHelper.e("BT3Utils", e.getMessage());
        }
    }
}
