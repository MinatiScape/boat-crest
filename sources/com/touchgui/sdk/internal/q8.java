package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGAppMenuStyle;
import java.util.ArrayList;
/* loaded from: classes12.dex */
public final class q8 extends y8 {
    public q8() {
        super(2823);
    }

    @Override // com.touchgui.sdk.internal.y8
    public final Object c(byte[] bArr) {
        Integer num;
        ArrayList arrayList = new ArrayList();
        byte b = bArr[2];
        for (int i = 0; i < 8; i++) {
            if ((bArr[3] & (1 << i)) != 0 && (num = (Integer) u8.f13829a.get(Integer.valueOf(i))) != null) {
                arrayList.add(num);
            }
        }
        TGAppMenuStyle tGAppMenuStyle = new TGAppMenuStyle();
        tGAppMenuStyle.setStyle(b);
        tGAppMenuStyle.setSupportList(arrayList);
        return tGAppMenuStyle;
    }
}
