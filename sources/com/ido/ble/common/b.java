package com.ido.ble.common;

import java.util.concurrent.CopyOnWriteArrayList;
/* loaded from: classes11.dex */
public final class b {
    public static boolean a(CopyOnWriteArrayList<String> copyOnWriteArrayList) {
        for (int i = 0; i < copyOnWriteArrayList.size(); i++) {
            if (copyOnWriteArrayList.get(i).contains("Rego")) {
                return true;
            }
        }
        return false;
    }

    public static boolean a(byte[] bArr, byte b) {
        for (byte b2 : bArr) {
            if (b2 == b) {
                return true;
            }
        }
        return false;
    }

    public static boolean a(int[] iArr, int i) {
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }
}
