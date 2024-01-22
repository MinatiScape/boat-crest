package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGQuickCard;
import java.util.ArrayList;
/* loaded from: classes12.dex */
public final class p8 extends y8 {
    public p8() {
        super((byte) 11, (byte) 1);
    }

    @Override // com.touchgui.sdk.internal.y8
    public final Object c(byte[] bArr) {
        ArrayList arrayList = new ArrayList();
        int i = bArr[2] & 255;
        for (int i2 = 3; i2 < Math.min(i + 3, bArr.length); i2++) {
            TGQuickCard tGQuickCard = new TGQuickCard();
            tGQuickCard.setId(bArr[i2] & Byte.MAX_VALUE);
            tGQuickCard.setVisible((bArr[i2] & 128) == 128);
            arrayList.add(tGQuickCard);
        }
        return arrayList;
    }
}
