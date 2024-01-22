package com.crrepa.f;

import com.crrepa.ble.conn.bean.CRPFunctionInfo;
import java.util.List;
/* loaded from: classes9.dex */
public class u {
    public static byte[] a() {
        return d1.a(37, null);
    }

    public static byte[] a(CRPFunctionInfo cRPFunctionInfo) {
        List<Integer> functionList = cRPFunctionInfo.getFunctionList();
        byte[] bArr = new byte[functionList.size() + 1];
        for (int i = 0; i < functionList.size(); i++) {
            bArr[i] = functionList.get(i).byteValue();
        }
        bArr[functionList.size()] = 0;
        return d1.a(21, bArr);
    }

    public static byte[] b() {
        return d1.a(37, new byte[]{-1});
    }
}
