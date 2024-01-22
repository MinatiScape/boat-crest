package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGWorkoutSupportList;
import java.util.ArrayList;
/* loaded from: classes12.dex */
public final class t7 extends y8 {
    public t7() {
        super((byte) 2, (byte) 21);
    }

    @Override // com.touchgui.sdk.internal.y8
    public final Object c(byte[] bArr) {
        TGWorkoutSupportList tGWorkoutSupportList = new TGWorkoutSupportList();
        ArrayList arrayList = new ArrayList();
        if (bArr != null && bArr.length > 2) {
            int length = ((bArr.length - 2) * 8) - 1;
            for (int i = 1; i <= length; i++) {
                int i2 = (i / 8) + 2;
                int i3 = i % 8;
                if (i2 < bArr.length && ((bArr[i2] >> i3) & 1) == 1) {
                    arrayList.add(Integer.valueOf(i));
                }
            }
        }
        tGWorkoutSupportList.setItems(arrayList);
        return tGWorkoutSupportList;
    }
}
