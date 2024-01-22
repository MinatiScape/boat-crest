package com.jieli.bluetooth_connect.util;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.os.Build;
import android.os.ParcelUuid;
import android.text.TextUtils;
import com.jieli.bluetooth_connect.constant.BluetoothConstant;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import org.eclipse.paho.android.service.MqttServiceConstants;
/* loaded from: classes11.dex */
public class BluetoothUtil {
    private static final String TAG = "BluetoothUtil";
    private static final char[] mChars = "0123456789ABCDEF".toCharArray();

    public static byte[] addressCovertToByteArray(String str) {
        if (TextUtils.isEmpty(str) || !str.contains(":")) {
            return null;
        }
        String replace = str.replace(":", "");
        int length = replace.length() / 2;
        byte[] bArr = new byte[length];
        if (length == 6) {
            for (int i = 0; i < length; i++) {
                int i2 = i * 2;
                int i3 = i2 + 2;
                if (i3 <= replace.length()) {
                    try {
                        bArr[i] = (byte) Integer.valueOf(replace.substring(i2, i3), 16).intValue();
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            }
            return bArr;
        }
        return null;
    }

    @SuppressLint({"MissingPermission"})
    public static boolean connectDeviceA2dp(Context context, BluetoothA2dp bluetoothA2dp, BluetoothDevice bluetoothDevice) {
        if (bluetoothA2dp == null || bluetoothDevice == null || !ConnectUtil.isHasConnectPermission(context)) {
            return false;
        }
        try {
            Object invoke = bluetoothA2dp.getClass().getMethod(MqttServiceConstants.CONNECT_ACTION, BluetoothDevice.class).invoke(bluetoothA2dp, bluetoothDevice);
            if (invoke instanceof Boolean) {
                return ((Boolean) invoke).booleanValue();
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressLint({"MissingPermission"})
    public static boolean connectDeviceHfp(Context context, BluetoothHeadset bluetoothHeadset, BluetoothDevice bluetoothDevice) {
        if (bluetoothHeadset == null || bluetoothDevice == null || !ConnectUtil.isHasConnectPermission(context)) {
            return false;
        }
        try {
            Method method = bluetoothHeadset.getClass().getMethod(MqttServiceConstants.CONNECT_ACTION, BluetoothDevice.class);
            method.setAccessible(true);
            Object invoke = method.invoke(bluetoothHeadset, bluetoothDevice);
            if (invoke instanceof Boolean) {
                return ((Boolean) invoke).booleanValue();
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressLint({"DiscouragedPrivateApi"})
    public static boolean createBond(Context context, BluetoothDevice bluetoothDevice, int i) {
        if (bluetoothDevice == null || !ConnectUtil.isHasConnectPermission(context)) {
            return false;
        }
        try {
            Method declaredMethod = bluetoothDevice.getClass().getDeclaredMethod("createBond", Integer.TYPE);
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod.invoke(bluetoothDevice, Integer.valueOf(i));
            if (invoke instanceof Boolean) {
                return ((Boolean) invoke).booleanValue();
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deviceEquals(BluetoothDevice bluetoothDevice, BluetoothDevice bluetoothDevice2) {
        return (bluetoothDevice == null || bluetoothDevice2 == null || !bluetoothDevice.getAddress().equals(bluetoothDevice2.getAddress())) ? false : true;
    }

    public static boolean deviceHasA2dp(Context context, BluetoothDevice bluetoothDevice) {
        return deviceHasProfile(context, bluetoothDevice, BluetoothConstant.UUID_A2DP);
    }

    public static boolean deviceHasHfp(Context context, BluetoothDevice bluetoothDevice) {
        return deviceHasProfile(context, bluetoothDevice, BluetoothConstant.UUID_HFP);
    }

    @SuppressLint({"MissingPermission"})
    public static boolean deviceHasProfile(Context context, BluetoothDevice bluetoothDevice, UUID uuid) {
        ParcelUuid[] uuids;
        if (!isBluetoothEnable() || bluetoothDevice == null || uuid == null || TextUtils.isEmpty(uuid.toString()) || !ConnectUtil.isHasConnectPermission(context) || (uuids = bluetoothDevice.getUuids()) == null) {
            return false;
        }
        for (ParcelUuid parcelUuid : uuids) {
            if (parcelUuid != null && uuid.toString().equalsIgnoreCase(parcelUuid.toString())) {
                return true;
            }
        }
        return false;
    }

    @SuppressLint({"MissingPermission"})
    public static boolean disableBluetooth(Context context) {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter == null || !ConnectUtil.isHasConnectPermission(context)) {
            return false;
        }
        if (defaultAdapter.isEnabled()) {
            return defaultAdapter.disable();
        }
        return true;
    }

    @SuppressLint({"MissingPermission"})
    public static boolean disconnectDeviceA2dp(Context context, BluetoothA2dp bluetoothA2dp, BluetoothDevice bluetoothDevice) {
        if (bluetoothA2dp == null || bluetoothDevice == null || !ConnectUtil.isHasConnectPermission(context)) {
            return false;
        }
        try {
            Method method = bluetoothA2dp.getClass().getMethod(MqttServiceConstants.DISCONNECT_ACTION, BluetoothDevice.class);
            method.setAccessible(true);
            Object invoke = method.invoke(bluetoothA2dp, bluetoothDevice);
            if (invoke instanceof Boolean) {
                return ((Boolean) invoke).booleanValue();
            }
            return false;
        } catch (Exception e) {
            String str = TAG;
            JL_Log.e(str, "-disconnectDeviceA2dp- have an exception : " + e);
            e.printStackTrace();
            return false;
        }
    }

    @SuppressLint({"MissingPermission"})
    public static boolean disconnectDeviceHfp(Context context, BluetoothHeadset bluetoothHeadset, BluetoothDevice bluetoothDevice) {
        if (bluetoothHeadset == null || bluetoothDevice == null || !ConnectUtil.isHasConnectPermission(context)) {
            return false;
        }
        try {
            Method method = bluetoothHeadset.getClass().getMethod(MqttServiceConstants.DISCONNECT_ACTION, BluetoothDevice.class);
            method.setAccessible(true);
            Object invoke = method.invoke(bluetoothHeadset, bluetoothDevice);
            if (invoke instanceof Boolean) {
                return ((Boolean) invoke).booleanValue();
            }
            return false;
        } catch (Exception e) {
            String str = TAG;
            JL_Log.e(str, "-disconnectFromHfp- have an exception : " + e);
            e.printStackTrace();
            return false;
        }
    }

    @SuppressLint({"MissingPermission"})
    public static boolean enableBluetooth(Context context) {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter == null || !ConnectUtil.isHasConnectPermission(context)) {
            return false;
        }
        if (defaultAdapter.isEnabled()) {
            return true;
        }
        return defaultAdapter.enable();
    }

    public static int formatBleMtu(int i) {
        if (i < 20) {
            i = 20;
        }
        if (i > 509) {
            return 509;
        }
        return i;
    }

    @SuppressLint({"DiscouragedPrivateApi", "MissingPermission"})
    public static BluetoothDevice getActivityDevice(Context context, BluetoothA2dp bluetoothA2dp) {
        if (bluetoothA2dp == null || !ConnectUtil.isHasConnectPermission(context)) {
            return null;
        }
        try {
            Method declaredMethod = bluetoothA2dp.getClass().getDeclaredMethod("getActiveDevice", new Class[0]);
            declaredMethod.setAccessible(true);
            return (BluetoothDevice) declaredMethod.invoke(bluetoothA2dp, new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @SuppressLint({"DiscouragedPrivateApi", "MissingPermission"})
    public static int getBtAdapterConnectionState(Context context, BluetoothAdapter bluetoothAdapter) {
        if (ConnectUtil.isHasConnectPermission(context) && bluetoothAdapter != null) {
            try {
                Method declaredMethod = bluetoothAdapter.getClass().getDeclaredMethod("getConnectionState", new Class[0]);
                declaredMethod.setAccessible(true);
                Object invoke = declaredMethod.invoke(bluetoothAdapter, new Object[0]);
                if (invoke instanceof Integer) {
                    return ((Integer) invoke).intValue();
                }
                return -1;
            } catch (Exception e) {
                e.printStackTrace();
                return -1;
            }
        }
        return -1;
    }

    @SuppressLint({"MissingPermission"})
    public static List<BluetoothDevice> getConnectedBleDeviceList(Context context) {
        BluetoothManager bluetoothManager;
        if (ConnectUtil.isHasConnectPermission(context) && (bluetoothManager = (BluetoothManager) context.getSystemService("bluetooth")) != null) {
            try {
                return bluetoothManager.getConnectedDevices(7);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Method getDeclaredMethod() throws Exception {
        return Class.class.getDeclaredMethod("getDeclaredMethod", String.class, Class[].class);
    }

    @SuppressLint({"MissingPermission"})
    public static List<BluetoothDevice> getPairedBLEDevices(Context context) {
        List<BluetoothDevice> pairedDevices;
        if (ConnectUtil.isHasConnectPermission(context) && (pairedDevices = getPairedDevices(context)) != null) {
            ArrayList arrayList = new ArrayList();
            for (BluetoothDevice bluetoothDevice : pairedDevices) {
                int type = bluetoothDevice.getType();
                if (type == 2 || type == 3) {
                    arrayList.add(bluetoothDevice);
                }
            }
            return arrayList;
        }
        return null;
    }

    @SuppressLint({"MissingPermission"})
    public static List<BluetoothDevice> getPairedDevices(Context context) {
        BluetoothAdapter defaultAdapter;
        if (ConnectUtil.isHasConnectPermission(context) && (defaultAdapter = BluetoothAdapter.getDefaultAdapter()) != null && defaultAdapter.isEnabled()) {
            ArrayList arrayList = new ArrayList();
            if (defaultAdapter.getBondedDevices() != null) {
                arrayList.addAll(defaultAdapter.getBondedDevices());
            }
            return arrayList;
        }
        return null;
    }

    @SuppressLint({"MissingPermission"})
    public static int getPriority(Context context, BluetoothProfile bluetoothProfile, BluetoothDevice bluetoothDevice) {
        if (bluetoothProfile != null && bluetoothDevice != null && ConnectUtil.isHasConnectPermission(context)) {
            try {
                Method method = (Method) getDeclaredMethod().invoke(bluetoothProfile.getClass(), "getPriority", new Class[]{BluetoothDevice.class});
                if (method == null) {
                    return 0;
                }
                Object invoke = method.invoke(bluetoothProfile, bluetoothDevice);
                if (invoke instanceof Integer) {
                    return ((Integer) invoke).intValue();
                }
                return 0;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Deprecated
    public static BluetoothDevice getRemoteDevice(String str) {
        return getRemoteDevice(ConnectUtil.getContext(), str);
    }

    @SuppressLint({"MissingPermission"})
    public static List<BluetoothDevice> getSystemConnectedBtDeviceList(Context context) {
        if (ConnectUtil.isHasConnectPermission(context)) {
            ArrayList arrayList = new ArrayList();
            BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
            try {
                int btAdapterConnectionState = getBtAdapterConnectionState(context, defaultAdapter);
                if (btAdapterConnectionState == 2 || btAdapterConnectionState == 0) {
                    for (BluetoothDevice bluetoothDevice : defaultAdapter.getBondedDevices()) {
                        if (isBTConnected(context, bluetoothDevice)) {
                            arrayList.add(bluetoothDevice);
                        }
                    }
                }
                List<BluetoothDevice> connectedBleDeviceList = getConnectedBleDeviceList(context);
                if (connectedBleDeviceList != null) {
                    for (BluetoothDevice bluetoothDevice2 : connectedBleDeviceList) {
                        if (!arrayList.contains(bluetoothDevice2)) {
                            arrayList.add(bluetoothDevice2);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (arrayList.size() > 0) {
                return arrayList;
            }
            return null;
        }
        return null;
    }

    public static boolean hasBle(Context context) {
        return context != null && context.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le");
    }

    public static String hexDataCovetToAddress(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        if (bArr != null && bArr.length == 6) {
            for (int i = 0; i < bArr.length; i++) {
                char[] cArr = mChars;
                sb.append(cArr[(bArr[i] & 255) >> 4]);
                sb.append(cArr[bArr[i] & 15]);
                if (i != bArr.length - 1) {
                    sb.append(":");
                }
            }
        }
        return sb.toString();
    }

    public static byte[] hexStringCovertToByteArray(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            int i3 = i2 + 2;
            if (i3 <= str.length()) {
                try {
                    bArr[i] = (byte) Integer.valueOf(str.substring(i2, i3), 16).intValue();
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
        return bArr;
    }

    @SuppressLint({"MissingPermission"})
    public static boolean isBTConnected(Context context, BluetoothDevice bluetoothDevice) {
        if (ConnectUtil.isHasConnectPermission(context) && bluetoothDevice != null) {
            try {
                Object invoke = bluetoothDevice.getClass().getDeclaredMethod("isConnected", new Class[0]).invoke(bluetoothDevice, new Object[0]);
                if (invoke instanceof Boolean) {
                    return ((Boolean) invoke).booleanValue();
                }
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @SuppressLint({"MissingPermission"})
    public static boolean isBleConnected(Context context, BluetoothDevice bluetoothDevice) {
        List<BluetoothDevice> connectedBleDeviceList;
        if (context == null || bluetoothDevice == null || (connectedBleDeviceList = getConnectedBleDeviceList(context)) == null) {
            return false;
        }
        for (BluetoothDevice bluetoothDevice2 : connectedBleDeviceList) {
            if (bluetoothDevice2 != null && bluetoothDevice.getAddress().equals(bluetoothDevice2.getAddress())) {
                return true;
            }
        }
        return false;
    }

    @SuppressLint({"MissingPermission"})
    public static boolean isBluetoothEnable() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        return defaultAdapter != null && defaultAdapter.isEnabled();
    }

    @SuppressLint({"MissingPermission"})
    public static void printBleGattServices(Context context, BluetoothDevice bluetoothDevice, BluetoothGatt bluetoothGatt, int i) {
        if (bluetoothDevice == null || bluetoothGatt == null || !ConnectUtil.isHasConnectPermission(context) || !JL_Log.isLog()) {
            return;
        }
        String str = TAG;
        JL_Log.d(str, String.format(Locale.getDefault(), "[[============================Bluetooth[%s], Discovery Services status[%d]=================================]]\n", printBtDeviceInfo(context, bluetoothDevice), Integer.valueOf(i)));
        List<BluetoothGattService> services = bluetoothGatt.getServices();
        if (services != null) {
            JL_Log.d(str, "[[======Service Size:" + services.size() + "======================\n");
            for (BluetoothGattService bluetoothGattService : services) {
                if (bluetoothGattService != null) {
                    String str2 = TAG;
                    JL_Log.d(str2, "[[======Service:" + bluetoothGattService.getUuid() + "======================\n");
                    List<BluetoothGattCharacteristic> characteristics = bluetoothGattService.getCharacteristics();
                    if (characteristics != null) {
                        JL_Log.d(str2, "[[[[=============characteristics Size:" + characteristics.size() + "======================\n");
                        for (BluetoothGattCharacteristic bluetoothGattCharacteristic : characteristics) {
                            if (bluetoothGattCharacteristic != null) {
                                String str3 = TAG;
                                JL_Log.d(str3, "[[[[=============characteristic:" + bluetoothGattCharacteristic.getUuid() + ",write type : " + bluetoothGattCharacteristic.getWriteType() + "======================\n");
                                List<BluetoothGattDescriptor> descriptors = bluetoothGattCharacteristic.getDescriptors();
                                if (descriptors != null) {
                                    JL_Log.d(str3, "[[[[[[=============descriptors Size:" + descriptors.size() + "======================\n");
                                    for (BluetoothGattDescriptor bluetoothGattDescriptor : descriptors) {
                                        if (bluetoothGattDescriptor != null) {
                                            String str4 = TAG;
                                            JL_Log.d(str4, "[[[[[[=============descriptor:" + bluetoothGattDescriptor.getUuid() + ",permission:" + bluetoothGattDescriptor.getPermissions() + "\nvalue : " + CHexConverter.byte2HexStr(bluetoothGattDescriptor.getValue()) + "======================\n");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        String str5 = TAG;
        JL_Log.d(str5, "[[============================Bluetooth[" + printBtDeviceInfo(context, bluetoothDevice) + "] Services show End=================================]]\n");
    }

    @Deprecated
    public static String printBtDeviceInfo(BluetoothDevice bluetoothDevice) {
        return printBtDeviceInfo(ConnectUtil.getContext(), bluetoothDevice, true);
    }

    @SuppressLint({"MissingPermission"})
    public static boolean refreshBleDeviceCache(Context context, BluetoothGatt bluetoothGatt) {
        if (bluetoothGatt == null || !ConnectUtil.isHasConnectPermission(context)) {
            return false;
        }
        try {
            Object invoke = bluetoothGatt.getClass().getMethod("refresh", new Class[0]).invoke(bluetoothGatt, new Object[0]);
            if (invoke instanceof Boolean) {
                return ((Boolean) invoke).booleanValue();
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressLint({"MissingPermission"})
    public static boolean removeBond(Context context, BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null || !ConnectUtil.isHasConnectPermission(context)) {
            return false;
        }
        try {
            Object invoke = bluetoothDevice.getClass().getMethod("removeBond", new Class[0]).invoke(bluetoothDevice, new Object[0]);
            if (invoke instanceof Boolean) {
                return ((Boolean) invoke).booleanValue();
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            String str = TAG;
            JL_Log.e(str, "Invoke removeBond : " + e.getMessage());
            return false;
        }
    }

    public static String reverseAddressString(String str) {
        if (!BluetoothAdapter.checkBluetoothAddress(str)) {
            return str;
        }
        if (str.contains(":")) {
            str = str.replaceAll(":", "");
        }
        byte[] hexStringCovertToByteArray = hexStringCovertToByteArray(str);
        if (hexStringCovertToByteArray == null) {
            return str;
        }
        byte[] bArr = new byte[6];
        int length = hexStringCovertToByteArray.length - 1;
        int i = 0;
        while (true) {
            if ((i < hexStringCovertToByteArray.length) & (length >= 0)) {
                bArr[i] = hexStringCovertToByteArray[length];
                i++;
                length--;
            } else {
                return hexDataCovetToAddress(bArr);
            }
        }
    }

    @SuppressLint({"DiscouragedPrivateApi", "MissingPermission"})
    public static boolean setActivityDevice(Context context, BluetoothA2dp bluetoothA2dp, BluetoothDevice bluetoothDevice) {
        if (bluetoothA2dp == null || bluetoothDevice == null || !ConnectUtil.isHasConnectPermission(context)) {
            return false;
        }
        try {
            Method declaredMethod = bluetoothA2dp.getClass().getDeclaredMethod("setActiveDevice", bluetoothDevice.getClass());
            declaredMethod.setAccessible(true);
            Object invoke = declaredMethod.invoke(bluetoothA2dp, bluetoothDevice);
            if (invoke instanceof Boolean) {
                return ((Boolean) invoke).booleanValue();
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressLint({"MissingPermission"})
    public static boolean setPriority(Context context, BluetoothProfile bluetoothProfile, BluetoothDevice bluetoothDevice, int i) {
        if (bluetoothProfile != null && bluetoothDevice != null && ConnectUtil.isHasConnectPermission(context)) {
            try {
                Method method = (Method) getDeclaredMethod().invoke(bluetoothProfile.getClass(), "setPriority", new Class[]{BluetoothDevice.class, Integer.TYPE});
                if (method == null) {
                    return false;
                }
                Object invoke = method.invoke(bluetoothProfile, bluetoothDevice, Integer.valueOf(i));
                if (invoke instanceof Boolean) {
                    return ((Boolean) invoke).booleanValue();
                }
                return false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static BluetoothDevice getRemoteDevice(Context context, String str) {
        BluetoothAdapter defaultAdapter;
        if (BluetoothAdapter.checkBluetoothAddress(str) && ConnectUtil.isHasConnectPermission(context) && (defaultAdapter = BluetoothAdapter.getDefaultAdapter()) != null) {
            try {
                return defaultAdapter.getRemoteDevice(str);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }

    public static String printBtDeviceInfo(Context context, BluetoothDevice bluetoothDevice) {
        return printBtDeviceInfo(context, bluetoothDevice, true);
    }

    @SuppressLint({"MissingPermission"})
    public static String printBtDeviceInfo(Context context, BluetoothDevice bluetoothDevice, boolean z) {
        if (bluetoothDevice == null || !ConnectUtil.isHasConnectPermission(context)) {
            return "null";
        }
        if (z) {
            return "name : " + bluetoothDevice.getName() + ", type : " + bluetoothDevice.getType() + ", address : " + bluetoothDevice.getAddress();
        }
        return bluetoothDevice.getAddress();
    }

    @SuppressLint({"MissingPermission"})
    public static boolean createBond(Context context, BluetoothDevice bluetoothDevice) {
        if (bluetoothDevice == null || !ConnectUtil.isHasConnectPermission(context)) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= 20) {
            return bluetoothDevice.createBond();
        }
        try {
            Object invoke = bluetoothDevice.getClass().getMethod("createBond", new Class[0]).invoke(bluetoothDevice, new Object[0]);
            if (invoke instanceof Boolean) {
                return ((Boolean) invoke).booleanValue();
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            String str = TAG;
            JL_Log.e(str, "Invoke createBond : " + e.getMessage());
            return false;
        }
    }
}
