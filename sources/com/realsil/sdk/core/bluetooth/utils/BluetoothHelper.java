package com.realsil.sdk.core.bluetooth.utils;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.ParcelUuid;
import android.provider.Settings;
import com.jstyle.blesdk1860.constant.BleConst;
import com.mappls.sdk.services.account.Region;
import com.realsil.sdk.core.bluetooth.impl.BluetoothDeviceImpl;
import com.realsil.sdk.core.bluetooth.impl.BluetoothGattImpl;
import com.realsil.sdk.core.logger.ZLogger;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
/* loaded from: classes12.dex */
public final class BluetoothHelper {
    public static final int BD_ADDR_LEN = 6;
    public static final int BD_UUID_LEN = 16;

    public static byte[] convertAddress(String str) {
        byte[] bArr = new byte[6];
        if (str != null) {
            bArr[0] = (byte) ((Character.digit(str.charAt(15), 16) * 16) + Character.digit(str.charAt(16), 16));
            bArr[1] = (byte) ((Character.digit(str.charAt(12), 16) * 16) + Character.digit(str.charAt(13), 16));
            bArr[2] = (byte) ((Character.digit(str.charAt(9), 16) * 16) + Character.digit(str.charAt(10), 16));
            bArr[3] = (byte) ((Character.digit(str.charAt(6), 16) * 16) + Character.digit(str.charAt(7), 16));
            bArr[4] = (byte) ((Character.digit(str.charAt(3), 16) * 16) + Character.digit(str.charAt(4), 16));
            bArr[5] = (byte) ((Character.digit(str.charAt(0), 16) * 16) + Character.digit(str.charAt(1), 16));
        } else {
            bArr[0] = 0;
            bArr[1] = 0;
            bArr[2] = 0;
            bArr[3] = 0;
            bArr[4] = 0;
            bArr[5] = 0;
        }
        return bArr;
    }

    public static String convertMac(byte[] bArr) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        if (bArr == null || bArr.length < 6) {
            return null;
        }
        if ((bArr[5] & 255) <= 15) {
            str = BleConst.GetDeviceTime + Integer.toHexString(bArr[5] & 255).toUpperCase() + ":";
        } else {
            str = Integer.toHexString(bArr[5] & 255).toUpperCase() + ":";
        }
        if ((bArr[4] & 255) <= 15) {
            str2 = str + BleConst.GetDeviceTime + Integer.toHexString(bArr[4] & 255).toUpperCase() + ":";
        } else {
            str2 = str + Integer.toHexString(bArr[4] & 255).toUpperCase() + ":";
        }
        if ((bArr[3] & 255) <= 15) {
            str3 = str2 + BleConst.GetDeviceTime + Integer.toHexString(bArr[3] & 255).toUpperCase() + ":";
        } else {
            str3 = str2 + Integer.toHexString(bArr[3] & 255).toUpperCase() + ":";
        }
        if ((bArr[2] & 255) <= 15) {
            str4 = str3 + BleConst.GetDeviceTime + Integer.toHexString(bArr[2] & 255).toUpperCase() + ":";
        } else {
            str4 = str3 + Integer.toHexString(bArr[2] & 255).toUpperCase() + ":";
        }
        if ((bArr[1] & 255) <= 15) {
            str5 = str4 + BleConst.GetDeviceTime + Integer.toHexString(bArr[1] & 255).toUpperCase() + ":";
        } else {
            str5 = str4 + Integer.toHexString(bArr[1] & 255).toUpperCase() + ":";
        }
        if ((bArr[0] & 255) <= 15) {
            return str5 + BleConst.GetDeviceTime + Integer.toHexString(bArr[0] & 255).toUpperCase();
        }
        return str5 + Integer.toHexString(bArr[0] & 255).toUpperCase();
    }

    public static String dumpBluetoothDevice(BluetoothDevice bluetoothDevice) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("BluetoothDevice{ %s/%s", formatAddress(bluetoothDevice.getAddress(), true), bluetoothDevice.getName()));
        Locale locale = Locale.US;
        sb.append(String.format(locale, "\n\tbondState=%d", Integer.valueOf(bluetoothDevice.getBondState())));
        sb.append(String.format(locale, "\n\ttype=%d", Integer.valueOf(bluetoothDevice.getType())));
        BluetoothClass bluetoothClass = bluetoothDevice.getBluetoothClass();
        if (bluetoothClass != null) {
            sb.append(String.format(locale, "majorDeviceClass=%d", Integer.valueOf(bluetoothClass.getMajorDeviceClass())));
        }
        ParcelUuid[] uuids = bluetoothDevice.getUuids();
        if (uuids != null && uuids.length > 0) {
            sb.append("\n\tsupportedFeaturesUuids");
            int length = uuids.length;
            for (int i = 0; i < length; i++) {
                sb.append(String.format("\n\t\t%s", uuids[i].toString()));
            }
        }
        sb.append("\n}");
        return sb.toString();
    }

    public static void enableBle() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null) {
            defaultAdapter.enable();
        }
    }

    public static String formatAddress(String str, boolean z) {
        if (z) {
            if (str == null || str.length() != 17) {
                return "";
            }
            byte[] convertAddress = convertAddress(str);
            return convertAddress.length < 6 ? "" : String.format("**:**:**:%02X:%02X:%02X", Byte.valueOf(convertAddress[2]), Byte.valueOf(convertAddress[1]), Byte.valueOf(convertAddress[0]));
        }
        return str;
    }

    public static String formatAddressNegatitive(byte[] bArr) {
        if (bArr == null || bArr.length < 6) {
            return null;
        }
        return String.format("%02X:%02X:%02X:%02X:%02X:%02X", Byte.valueOf(bArr[5]), Byte.valueOf(bArr[4]), Byte.valueOf(bArr[3]), Byte.valueOf(bArr[2]), Byte.valueOf(bArr[1]), Byte.valueOf(bArr[0]));
    }

    public static String formatAddressPositive(byte[] bArr) {
        if (bArr == null || bArr.length < 6) {
            return null;
        }
        return String.format("%02X:%02X:%02X:%02X:%02X:%02X", Byte.valueOf(bArr[0]), Byte.valueOf(bArr[1]), Byte.valueOf(bArr[2]), Byte.valueOf(bArr[3]), Byte.valueOf(bArr[4]), Byte.valueOf(bArr[5]));
    }

    public static String getBluetoothAddress(Context context, BluetoothAdapter bluetoothAdapter) {
        if (bluetoothAdapter != null) {
            Class<?> cls = bluetoothAdapter.getClass();
            try {
                Class<?> cls2 = Class.forName("android.bluetooth.IBluetooth");
                Field declaredField = cls.getDeclaredField("mService");
                declaredField.setAccessible(true);
                Method method = cls2.getMethod("getAddress", new Class[0]);
                method.setAccessible(true);
                return (String) method.invoke(declaredField.get(bluetoothAdapter), new Object[0]);
            } catch (Exception e) {
                ZLogger.e(e.toString());
            }
        } else if (context != null) {
            return Settings.Secure.getString(context.getContentResolver(), "bluetooth_address");
        }
        return null;
    }

    public static List<BluetoothDevice> getBondedBluetoothDevices() {
        ArrayList arrayList = new ArrayList();
        try {
            for (BluetoothDevice bluetoothDevice : BluetoothAdapter.getDefaultAdapter().getBondedDevices()) {
                if (BluetoothDeviceImpl.isConnected(bluetoothDevice)) {
                    ZLogger.v("connected: " + formatAddress(bluetoothDevice.getAddress(), true));
                    arrayList.add(bluetoothDevice);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public static String getCharPermission(int i) {
        return BluetoothGattImpl.getCharPermission(i);
    }

    public static String getCharPropertie(int i) {
        return BluetoothGattImpl.getCharPropertie(i);
    }

    public static List<BluetoothDevice> getConnectedBluetoothDevices() {
        ArrayList arrayList = new ArrayList();
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        try {
            Method declaredMethod = BluetoothAdapter.class.getDeclaredMethod("getConnectionState", null);
            declaredMethod.setAccessible(true);
            if (((Integer) declaredMethod.invoke(defaultAdapter, null)).intValue() == 2) {
                for (BluetoothDevice bluetoothDevice : defaultAdapter.getBondedDevices()) {
                    if (BluetoothDeviceImpl.isConnected(bluetoothDevice)) {
                        ZLogger.v("connected: " + formatAddress(bluetoothDevice.getAddress(), true));
                        arrayList.add(bluetoothDevice);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public static String getDescPermission(int i) {
        return BluetoothGattImpl.getDescPermission(i);
    }

    public static boolean isBleEnabled() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        return defaultAdapter != null && defaultAdapter.isEnabled();
    }

    public static boolean isBleSupported(Context context) {
        return context.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le");
    }

    public static String parseDeviceType(int i) {
        return BluetoothDeviceImpl.parseDeviceType(i);
    }

    public static String parseProfile(int i) {
        if (i != 16) {
            if (i != 17) {
                switch (i) {
                    case 1:
                        return "HEADSET";
                    case 2:
                        return "A2DP";
                    case 3:
                        return "HEALTH";
                    case 4:
                        return "HID_HOST";
                    case 5:
                        return Region.REGION_PANAMA;
                    case 6:
                        return "PBAP";
                    case 7:
                        return "GATT";
                    case 8:
                        return "GATT_SERVER";
                    case 9:
                        return "MAP";
                    case 10:
                        return "SAP";
                    case 11:
                        return "A2DP_SINK";
                    case 12:
                        return "AVRCP_CONTROLLER";
                    default:
                        return "Unknown";
                }
            }
            return "PBAP_CLIENT";
        }
        return "HEADSET_CLIENT";
    }

    public static String parseProfileState(int i) {
        if (i != 0) {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        return "UNKNOWN (" + i + ")";
                    }
                    return "3-BluetoothProfile.STATE_DISCONNECTING";
                }
                return "2-BluetoothProfile.STATE_CONNECTED";
            }
            return "1-BluetoothProfile.STATE_CONNECTING";
        }
        return "0-BluetoothProfile.STATE_DISCONNECTED";
    }

    public static List<String> parseProperty(int i) {
        return BluetoothGattImpl.parseProperty(i);
    }

    public static String parseProperty2(int i) {
        return BluetoothGattImpl.parseProperty2(i);
    }

    public static String formatAddress(byte[] bArr, boolean z) {
        return (bArr == null || bArr.length < 6) ? "" : z ? String.format("**:**:**:%02X:%02X:%02X", Byte.valueOf(bArr[3]), Byte.valueOf(bArr[4]), Byte.valueOf(bArr[5])) : String.format("%02X:%02X:%02X:%02X:%02X:%02X", Byte.valueOf(bArr[0]), Byte.valueOf(bArr[1]), Byte.valueOf(bArr[2]), Byte.valueOf(bArr[3]), Byte.valueOf(bArr[4]), Byte.valueOf(bArr[5]));
    }

    public static List<BluetoothDevice> getConnectedBluetoothDevices(int i) {
        ArrayList arrayList = new ArrayList();
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        try {
            if (defaultAdapter.getProfileConnectionState(i) == 2) {
                for (BluetoothDevice bluetoothDevice : defaultAdapter.getBondedDevices()) {
                    if (BluetoothDeviceImpl.isConnected(bluetoothDevice)) {
                        ZLogger.v("connected: " + formatAddress(bluetoothDevice.getAddress(), true));
                        arrayList.add(bluetoothDevice);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
