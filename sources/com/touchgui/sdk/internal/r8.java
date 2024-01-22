package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGAppMenu;
import java.util.ArrayList;
/* loaded from: classes12.dex */
public final class r8 extends y8 {
    public r8() {
        super((byte) 11, (byte) 3);
    }

    @Override // com.touchgui.sdk.internal.y8
    public final Object c(byte[] bArr) {
        ArrayList arrayList = new ArrayList();
        int i = bArr[2] & 255;
        for (int i2 = 3; i2 < Math.min(i + 3, bArr.length); i2++) {
            TGAppMenu tGAppMenu = new TGAppMenu();
            tGAppMenu.setId(bArr[i2] & Byte.MAX_VALUE);
            tGAppMenu.setVisible((bArr[i2] & 128) == 128);
            arrayList.add(tGAppMenu);
        }
        return arrayList;
    }
}
