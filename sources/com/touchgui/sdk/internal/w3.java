package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGFunctions;
import java.util.HashMap;
/* loaded from: classes12.dex */
public final class w3 {
    public static final HashMap d;

    /* renamed from: a  reason: collision with root package name */
    public final byte[] f13838a;
    public byte[] b;
    public byte[] c;

    static {
        HashMap hashMap = new HashMap();
        d = hashMap;
        hashMap.put(Integer.valueOf((int) TGFunctions.FUNC_GPS), 34145920);
        hashMap.put(Integer.valueOf((int) TGFunctions.FUNC_USE_APP_GPS), 34145856);
        hashMap.put(Integer.valueOf((int) TGFunctions.FUNC_STRESS), 34145312);
        hashMap.put(Integer.valueOf((int) TGFunctions.FUNC_PHYSIOLOGICAL_CYCLE), 34145296);
        hashMap.put(33687816, 34146112);
    }

    public w3() {
        this.f13838a = null;
    }

    public final boolean a(int i) {
        int i2 = (-65536) & i;
        int i3 = (65280 & i) >> 8;
        int i4 = i & 255;
        Integer num = (Integer) d.get(Integer.valueOf(i));
        byte[] bArr = this.b;
        if (bArr != null || num == null) {
            if (i2 == 33685504) {
                byte[] bArr2 = this.f13838a;
                return bArr2 != null && i3 < bArr2.length && (bArr2[i3] & i4) == i4;
            } else if (i2 != 34013184) {
                if (i2 == 34144256) {
                    byte[] bArr3 = this.c;
                    return bArr3 != null && i3 < bArr3.length && (bArr3[i3] & i4) == i4;
                }
                return false;
            } else {
                boolean z = bArr != null && i3 < bArr.length && (bArr[i3] & i4) == i4;
                if (i == 34013953) {
                    if (z && a(34013968)) {
                        return false;
                    }
                    return z && !a(TGFunctions.FUNC_USE_APP_GPS);
                }
                return z;
            }
        }
        return a(num.intValue());
    }

    public w3(byte[] bArr) {
        this.f13838a = bArr;
    }
}
