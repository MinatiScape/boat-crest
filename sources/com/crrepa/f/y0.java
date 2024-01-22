package com.crrepa.f;

import com.crrepa.ble.conn.bean.CRPUserInfo;
/* loaded from: classes9.dex */
public class y0 {
    public static byte[] a(CRPUserInfo cRPUserInfo) {
        if (cRPUserInfo == null) {
            return null;
        }
        return d1.a(18, new byte[]{(byte) cRPUserInfo.getHeight(), (byte) cRPUserInfo.getWeight(), (byte) cRPUserInfo.getAge(), (byte) cRPUserInfo.getGender()});
    }
}
