package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGBrightnessConfig;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.bouncycastle.crypto.signers.PSSSigner;
/* loaded from: classes12.dex */
public final class n7 extends y8 {
    public n7() {
        super((byte) 2, PSSSigner.TRAILER_IMPLICIT);
    }

    @Override // com.touchgui.sdk.internal.y8
    public final Object c(byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        wrap.position(2);
        TGBrightnessConfig tGBrightnessConfig = new TGBrightnessConfig();
        tGBrightnessConfig.setLevel(wrap.get() & 255);
        tGBrightnessConfig.setMode(wrap.get() & 255);
        return tGBrightnessConfig;
    }
}
