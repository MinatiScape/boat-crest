package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGQuickReply;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
/* loaded from: classes12.dex */
public final class v5 extends h5 {
    public v5() {
        super((short) 22, 0);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x004b  */
    @Override // com.touchgui.sdk.internal.h5, com.touchgui.sdk.internal.d8
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean a(byte[] r8) {
        /*
            r7 = this;
            r0 = 1
            r1 = r8[r0]
            r2 = 5
            r3 = -38
            r4 = 0
            if (r1 != r3) goto L48
            r1 = 2
            r1 = r8[r1]
            r5 = -83
            if (r1 != r5) goto L48
            r1 = 3
            r1 = r8[r1]
            if (r1 != r3) goto L48
            r1 = 4
            r1 = r8[r1]
            if (r1 != r5) goto L48
            java.nio.ByteBuffer r1 = java.nio.ByteBuffer.wrap(r8)
            java.nio.ByteOrder r3 = java.nio.ByteOrder.LITTLE_ENDIAN
            r1.order(r3)
            r1.position(r2)
            byte r3 = r1.get()
            r1.getShort()
            short r5 = r1.getShort()
            short r1 = r1.getShort()
            byte r6 = r7.f13770a
            if (r6 != r3) goto L43
            short r3 = r7.c
            if (r3 != r5) goto L43
            short r3 = r7.d
            if (r3 != r1) goto L43
            r1 = r0
            goto L44
        L43:
            r1 = r4
        L44:
            if (r1 == 0) goto L48
            r1 = r0
            goto L49
        L48:
            r1 = r4
        L49:
            if (r1 == 0) goto L78
            java.nio.ByteBuffer r8 = java.nio.ByteBuffer.wrap(r8)
            java.nio.ByteOrder r1 = java.nio.ByteOrder.LITTLE_ENDIAN
            r8.order(r1)
            r8.position(r2)
            byte r1 = r8.get()
            r8.getShort()
            short r2 = r8.getShort()
            short r8 = r8.getShort()
            byte r3 = r7.f13770a
            if (r3 != r1) goto L74
            short r1 = r7.c
            if (r1 != r2) goto L74
            short r1 = r7.d
            if (r1 != r8) goto L74
            r8 = r0
            goto L75
        L74:
            r8 = r4
        L75:
            if (r8 == 0) goto L78
            goto L79
        L78:
            r0 = r4
        L79:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.touchgui.sdk.internal.v5.a(byte[]):boolean");
    }

    @Override // com.touchgui.sdk.internal.h5
    public final Object c(byte[] bArr) {
        int i;
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(ByteOrder.LITTLE_ENDIAN);
        wrap.position(11);
        TGQuickReply tGQuickReply = new TGQuickReply();
        tGQuickReply.setMsgType(wrap.get() & 255);
        int i2 = wrap.get() & 255;
        int i3 = wrap.get() & 255;
        if ((i2 & 240) == 240) {
            i = wrap.get() & 255;
            tGQuickReply.setReplyTo("");
            tGQuickReply.setMsgIndex((i2 & 15) + 1);
        } else {
            tGQuickReply.setMsgIndex(i2 & 15);
            i = 0;
        }
        if (i > 0) {
            byte[] bArr2 = new byte[i];
            wrap.get(bArr2, 0, i);
            int i4 = 0;
            for (int i5 = 0; i5 < i && bArr2[i5] != 0; i5++) {
                i4++;
            }
            tGQuickReply.setReplyTo(new String(bArr2, 0, i4));
        }
        if (i3 > 0) {
            byte[] bArr3 = new byte[i3];
            wrap.get(bArr3, 0, i3);
            int i6 = 0;
            for (int i7 = 0; i7 < i3 && bArr3[i7] != 0; i7++) {
                i6++;
            }
            tGQuickReply.setContent(new String(bArr3, 0, i6));
        }
        return tGQuickReply;
    }

    @Override // com.touchgui.sdk.internal.h5
    public final /* bridge */ /* synthetic */ boolean d() {
        return true;
    }
}
