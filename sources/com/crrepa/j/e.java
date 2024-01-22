package com.crrepa.j;

import com.crrepa.ble.conn.bean.CRPFunctionInfo;
import java.util.ArrayList;
/* loaded from: classes9.dex */
public class e {
    public static CRPFunctionInfo a(byte[] bArr) {
        if (com.crrepa.i0.e.e(bArr)) {
            return null;
        }
        CRPFunctionInfo cRPFunctionInfo = new CRPFunctionInfo();
        int i = 0;
        if (bArr[0] != -1) {
            cRPFunctionInfo.setDisplayFunction(true);
        } else {
            i = 1;
        }
        ArrayList arrayList = new ArrayList();
        int length = bArr.length;
        while (i < length) {
            byte b = bArr[i];
            if (b > 0) {
                arrayList.add(Integer.valueOf(b));
            }
            i++;
        }
        cRPFunctionInfo.setFunctionList(arrayList);
        return cRPFunctionInfo;
    }
}
