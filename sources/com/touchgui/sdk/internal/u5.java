package com.touchgui.sdk.internal;

import com.touchgui.sdk.TGLogger;
import com.touchgui.sdk.bean.TGQuickReply;
import java.nio.ByteBuffer;
/* loaded from: classes12.dex */
public final class u5 extends h5 {
    public final /* synthetic */ TGQuickReply l;
    public final /* synthetic */ int m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u5(TGQuickReply tGQuickReply, int i) {
        super((short) 21);
        this.l = tGQuickReply;
        this.m = i;
    }

    @Override // com.touchgui.sdk.internal.h5
    public final Object c(byte[] bArr) {
        return Integer.valueOf(bArr[11]);
    }

    @Override // com.touchgui.sdk.internal.h5
    public final byte[] e() {
        String a2 = s.a(128, this.l.getContent());
        if (a2.length() > 0 && a2.length() != this.l.getContent().length()) {
            TGLogger.w("The length of quick reply content must be less than 128 bytes");
        }
        byte[] bytes = a2.getBytes();
        ByteBuffer allocate = ByteBuffer.allocate(bytes.length + 3);
        allocate.put((byte) this.m);
        allocate.put((byte) this.l.getMsgIndex());
        allocate.put((byte) bytes.length);
        allocate.put(bytes);
        return allocate.array();
    }
}
