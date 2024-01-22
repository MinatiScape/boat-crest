package com.crrepa.j;

import com.crrepa.ble.conn.bean.CRPSupportWatchFaceInfo;
import com.crrepa.ble.conn.bean.CRPWatchFaceListInfo;
import java.util.ArrayList;
/* loaded from: classes9.dex */
public class y {
    public static CRPSupportWatchFaceInfo a(byte[] bArr) {
        if (bArr != null) {
            if (bArr.length < 2) {
                return null;
            }
            int b = com.crrepa.i0.e.b(bArr[0], bArr[1]);
            com.crrepa.i0.c.c("watch face index: " + b);
            ArrayList arrayList = new ArrayList();
            for (int i = 2; i < bArr.length; i++) {
                arrayList.add(Integer.valueOf(bArr[i]));
            }
            return new CRPSupportWatchFaceInfo(b, arrayList);
        }
        return null;
    }

    public static int b(byte[] bArr) {
        if (com.crrepa.i0.e.e(bArr)) {
            return -1;
        }
        return bArr[0];
    }

    public static CRPWatchFaceListInfo c(byte[] bArr) {
        if (bArr != null) {
            if (bArr.length < 2) {
                return null;
            }
            int a2 = com.crrepa.i0.e.a(bArr[1]);
            ArrayList arrayList = new ArrayList();
            for (int i = 2; i < bArr.length; i += 4) {
                arrayList.add(new CRPWatchFaceListInfo.WatchFaceBean(bArr[i], new String(new byte[]{bArr[i + 1]}), com.crrepa.i0.e.b(bArr[i + 2], bArr[i + 3])));
            }
            return new CRPWatchFaceListInfo(a2, arrayList);
        }
        return null;
    }
}
