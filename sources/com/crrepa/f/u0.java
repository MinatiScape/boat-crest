package com.crrepa.f;

import com.crrepa.ble.conn.type.CRPTempTimeType;
/* loaded from: classes9.dex */
public class u0 {

    /* renamed from: a  reason: collision with root package name */
    public static final byte f7711a = 3;
    public static final byte b = 0;
    public static final byte c = 19;

    public static byte[] a() {
        return d1.a(-114, new byte[]{3});
    }

    public static byte[] a(byte b2) {
        return d1.a(59, new byte[]{4, b2});
    }

    public static byte[] a(int i) {
        return d1.a(63, new byte[]{(byte) i});
    }

    public static byte[] a(CRPTempTimeType cRPTempTimeType) {
        return a(cRPTempTimeType == CRPTempTimeType.YESTERDAY ? 12 <= com.crrepa.i0.h.a() ? (byte) 2 : (byte) 1 : (byte) 0);
    }

    public static byte[] a(boolean z) {
        return d1.a(126, new byte[]{3, z ? (byte) 1 : (byte) 0});
    }

    public static byte[] b() {
        return d1.a(59, new byte[]{3});
    }

    public static byte[] b(boolean z) {
        return d1.a(59, new byte[]{1, z ? (byte) 1 : (byte) 0});
    }

    public static byte[] c(boolean z) {
        return d1.a(59, new byte[]{2, z ? (byte) 1 : (byte) 0});
    }
}
