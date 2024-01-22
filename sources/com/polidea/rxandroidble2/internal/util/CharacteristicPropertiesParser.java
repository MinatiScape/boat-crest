package com.polidea.rxandroidble2.internal.util;

import androidx.annotation.NonNull;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.polidea.rxandroidble2.internal.RxBleLog;
/* loaded from: classes12.dex */
public class CharacteristicPropertiesParser {

    /* renamed from: a  reason: collision with root package name */
    public final int f13502a;
    public final int b;
    public final int c;
    public final int d;
    public final int e;
    public final int f;
    public final int g;
    public final int[] h = a();

    public CharacteristicPropertiesParser(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        this.f13502a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
        this.e = i5;
        this.f = i6;
        this.g = i7;
    }

    public static boolean b(int i, int i2) {
        return (i & i2) != 0;
    }

    @NonNull
    public final int[] a() {
        return new int[]{this.f13502a, this.b, this.c, this.d, this.e, this.f, this.g};
    }

    @NonNull
    public final String c(int i) {
        if (i == this.b) {
            return "READ";
        }
        if (i == this.d) {
            return "WRITE";
        }
        if (i == this.c) {
            return "WRITE_NO_RESPONSE";
        }
        if (i == this.g) {
            return "SIGNED_WRITE";
        }
        if (i == this.f) {
            return "INDICATE";
        }
        if (i == this.f13502a) {
            return "BROADCAST";
        }
        if (i == this.e) {
            return "NOTIFY";
        }
        if (i == 0) {
            return "";
        }
        RxBleLog.e("Unknown property specified (%d)", Integer.valueOf(i));
        return "UNKNOWN (" + i + " -> check android.bluetooth.BluetoothGattCharacteristic)";
    }

    @NonNull
    public String propertiesIntToString(int i) {
        int[] iArr;
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for (int i2 : this.h) {
            if (b(i, i2)) {
                sb.append(c(i2));
                sb.append(HexStringBuilder.DEFAULT_SEPARATOR);
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
