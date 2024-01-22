package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGContacts;
import java.nio.ByteBuffer;
import java.util.ArrayList;
/* loaded from: classes12.dex */
public final class f2 extends h5 {
    public final /* synthetic */ boolean l;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f2(boolean z) {
        super((short) 32);
        this.l = z;
    }

    @Override // com.touchgui.sdk.internal.h5
    public final Object c(byte[] bArr) {
        ArrayList arrayList = new ArrayList();
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.position(11);
        int i = wrap.get() & 255;
        for (int i2 = 0; i2 < i; i2++) {
            TGContacts tGContacts = new TGContacts();
            wrap.get();
            int i3 = wrap.get() & 255;
            int i4 = wrap.get() & 255;
            if (i3 > 0) {
                byte[] bArr2 = new byte[i3];
                wrap.get(bArr2, 0, i3);
                if (this.l) {
                    bArr2 = s.b(bArr2);
                }
                tGContacts.setPhoneNum(new String(bArr2));
            }
            if (i4 > 0) {
                byte[] bArr3 = new byte[i4];
                wrap.get(bArr3, 0, i4);
                if (this.l) {
                    bArr3 = s.b(bArr3);
                }
                tGContacts.setName(new String(bArr3));
            }
            arrayList.add(tGContacts);
        }
        return arrayList;
    }

    @Override // com.touchgui.sdk.internal.h5
    public final byte[] e() {
        return new byte[]{0};
    }
}
