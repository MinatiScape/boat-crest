package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGWorkoutMode;
import java.util.ArrayList;
/* loaded from: classes12.dex */
public final class t8 extends y8 {
    public t8() {
        super((byte) 11, (byte) 5);
    }

    @Override // com.touchgui.sdk.internal.y8
    public final Object c(byte[] bArr) {
        ArrayList arrayList = new ArrayList();
        int i = bArr[2] & 255;
        for (int i2 = 3; i2 < Math.min(i + 3, bArr.length); i2++) {
            arrayList.add(Integer.valueOf(bArr[i2] & 255));
        }
        TGWorkoutMode tGWorkoutMode = new TGWorkoutMode();
        tGWorkoutMode.setWorkouts(arrayList);
        return tGWorkoutMode;
    }
}
