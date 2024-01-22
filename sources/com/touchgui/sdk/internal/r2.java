package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGMyDials;
import java.util.ArrayList;
/* loaded from: classes12.dex */
public final class r2 extends y8 {
    public r2() {
        super((byte) 14, (byte) 2);
    }

    @Override // com.touchgui.sdk.internal.y8
    public final Object c(byte[] bArr) {
        ArrayList arrayList = new ArrayList();
        if (bArr.length >= 3) {
            int i = bArr[2] & 255;
            if ((i * 5) + 3 <= bArr.length) {
                for (int i2 = 0; i2 < Math.min(i, (bArr.length - 3) / 5); i2++) {
                    TGMyDials.ItemBean itemBean = new TGMyDials.ItemBean();
                    int i3 = i2 * 5;
                    itemBean.setDialId(s.b(bArr, i3 + 3, 4));
                    itemBean.setFlag(bArr[i3 + 7] & 255);
                    arrayList.add(itemBean);
                }
            }
        }
        return new TGMyDials(arrayList);
    }
}
