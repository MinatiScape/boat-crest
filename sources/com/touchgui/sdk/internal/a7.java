package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGProfile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.UShort;
/* loaded from: classes12.dex */
public final class a7 extends y8 {
    public a7() {
        super((byte) 2, (byte) -79);
    }

    @Override // com.touchgui.sdk.internal.y8
    public final Object c(byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        wrap.position(2);
        TGProfile tGProfile = new TGProfile();
        tGProfile.setHeight(wrap.get() & 255);
        tGProfile.setWeight(wrap.getShort() & UShort.MAX_VALUE);
        tGProfile.setGender(wrap.get() & 255);
        tGProfile.setBirthday(h2.a(wrap.getShort() & UShort.MAX_VALUE, wrap.get() & 255, wrap.get() & 255));
        return tGProfile;
    }
}
