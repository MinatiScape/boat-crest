package com.crrepa.j;

import com.crrepa.ble.conn.bean.CRPContactConfigInfo;
/* loaded from: classes9.dex */
public class o {

    /* renamed from: a  reason: collision with root package name */
    public static final byte f7735a = 0;
    public static final byte b = -2;
    public static final byte c = -18;

    public static CRPContactConfigInfo a(byte[] bArr) {
        if (bArr == null || 3 >= bArr.length) {
            return null;
        }
        CRPContactConfigInfo cRPContactConfigInfo = new CRPContactConfigInfo();
        byte b2 = bArr[1];
        cRPContactConfigInfo.setSupported(b2 != 0);
        cRPContactConfigInfo.setCount(b2);
        cRPContactConfigInfo.setWidth(bArr[2]);
        cRPContactConfigInfo.setHeight(bArr[3]);
        return cRPContactConfigInfo;
    }
}
