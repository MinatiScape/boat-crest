package com.realsil.sdk.core.bluetooth.impl;

import android.bluetooth.BluetoothGatt;
import android.text.TextUtils;
import androidx.annotation.RequiresApi;
import com.realsil.sdk.core.logger.ZLogger;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/* loaded from: classes12.dex */
public class BluetoothGattImpl {

    /* renamed from: a  reason: collision with root package name */
    public static HashMap<Integer, String> f13570a;
    public static HashMap<Integer, String> b;
    public static HashMap<Integer, String> c;

    static {
        HashMap<Integer, String> hashMap = new HashMap<>();
        f13570a = hashMap;
        hashMap.put(0, "UNKNOW");
        f13570a.put(1, "READ");
        f13570a.put(2, "READ_ENCRYPTED");
        f13570a.put(4, "READ_ENCRYPTED_MITM");
        f13570a.put(16, "WRITE");
        f13570a.put(32, "WRITE_ENCRYPTED");
        f13570a.put(64, "WRITE_ENCRYPTED_MITM");
        f13570a.put(128, "WRITE_SIGNED");
        f13570a.put(256, "WRITE_SIGNED_MITM");
        HashMap<Integer, String> hashMap2 = new HashMap<>();
        b = hashMap2;
        hashMap2.put(1, "BROADCAST");
        b.put(128, "EXTENDED_PROPS");
        b.put(32, "INDICATE");
        b.put(16, "NOTIFY");
        b.put(2, "READ");
        b.put(64, "SIGNED_WRITE");
        b.put(8, "WRITE");
        b.put(4, "WRITE_NO_RESPONSE");
        HashMap<Integer, String> hashMap3 = new HashMap<>();
        c = hashMap3;
        hashMap3.put(0, "UNKNOW");
        c.put(1, "READ");
        c.put(2, "READ_ENCRYPTED");
        c.put(4, "READ_ENCRYPTED_MITM");
        c.put(16, "WRITE");
        c.put(32, "WRITE_ENCRYPTED");
        c.put(64, "WRITE_ENCRYPTED_MITM");
        c.put(128, "WRITE_SIGNED");
        c.put(256, "WRITE_SIGNED_MITM");
    }

    public static String a(HashMap<Integer, String> hashMap, int i) {
        String str = hashMap.get(Integer.valueOf(i));
        if (TextUtils.isEmpty(str)) {
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < 32; i2++) {
                int i3 = 1 << i2;
                if ((i & i3) > 0) {
                    arrayList.add(Integer.valueOf(i3));
                }
            }
            String str2 = "";
            for (int i4 = 0; i4 < arrayList.size(); i4++) {
                str2 = str2 + hashMap.get(arrayList.get(i4)) + "|";
            }
            return str2;
        }
        return str;
    }

    public static String getCharPermission(int i) {
        return a(f13570a, i);
    }

    public static String getCharPropertie(int i) {
        return a(b, i);
    }

    public static String getDescPermission(int i) {
        return a(c, i);
    }

    public static List<String> parseProperty(int i) {
        ArrayList arrayList = new ArrayList();
        if ((i & 1) == 1) {
            arrayList.add("BROADCAST");
        }
        if ((i & 2) == 2) {
            arrayList.add("READ");
        }
        if ((i & 4) == 4) {
            arrayList.add("WRITE_NO_RESPONSE");
        }
        if ((i & 8) == 8) {
            arrayList.add("WRITE");
        }
        return arrayList;
    }

    public static String parseProperty2(int i) {
        StringBuilder sb = new StringBuilder();
        List<String> parseProperty = parseProperty(i);
        if (parseProperty != null && parseProperty.size() > 0) {
            for (String str : parseProperty) {
                sb.append(str);
            }
        }
        return sb.toString();
    }

    @RequiresApi(api = 18)
    public static boolean refresh(BluetoothGatt bluetoothGatt) {
        try {
            Method method = bluetoothGatt.getClass().getMethod("refresh", new Class[0]);
            if (method != null) {
                return ((Boolean) method.invoke(bluetoothGatt, new Object[0])).booleanValue();
            }
        } catch (Exception e) {
            ZLogger.w("refreshing device failed: " + e.toString());
        }
        return false;
    }

    @RequiresApi(api = 18)
    public static boolean refreshDeviceCache(BluetoothGatt bluetoothGatt, boolean z) {
        if (z || bluetoothGatt.getDevice().getBondState() == 10) {
            return refresh(bluetoothGatt);
        }
        return false;
    }
}
