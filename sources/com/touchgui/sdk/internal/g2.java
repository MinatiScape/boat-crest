package com.touchgui.sdk.internal;

import com.touchgui.sdk.bean.TGContacts;
import java.nio.ByteBuffer;
import java.util.List;
/* loaded from: classes12.dex */
public final class g2 extends h5 {
    public final /* synthetic */ List l;
    public final /* synthetic */ boolean m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g2(List list, boolean z) {
        super((short) 25);
        this.l = list;
        this.m = z;
    }

    @Override // com.touchgui.sdk.internal.h5
    public final Object c(byte[] bArr) {
        return Integer.valueOf(bArr[11] & 255);
    }

    @Override // com.touchgui.sdk.internal.h5
    public final byte[] e() {
        int i = 1;
        for (TGContacts tGContacts : this.l) {
            i = tGContacts.getPhoneNumLen() + tGContacts.getNameLen() + i + 3;
        }
        ByteBuffer allocate = ByteBuffer.allocate(i);
        allocate.put((byte) this.l.size());
        int i2 = 0;
        while (i2 < this.l.size()) {
            TGContacts tGContacts2 = (TGContacts) this.l.get(i2);
            i2++;
            allocate.put((byte) (i2 & 255));
            allocate.put((byte) (tGContacts2.getPhoneNumLen() & 255));
            allocate.put((byte) (tGContacts2.getNameLen() & 255));
            if (tGContacts2.getPhoneNumLen() > 0) {
                byte[] phoneNumBytes = tGContacts2.getPhoneNumBytes();
                if (this.m) {
                    phoneNumBytes = s.b(phoneNumBytes);
                }
                allocate.put(phoneNumBytes);
            }
            if (tGContacts2.getNameLen() > 0) {
                byte[] nameBytes = tGContacts2.getNameBytes();
                if (this.m) {
                    nameBytes = s.b(nameBytes);
                }
                allocate.put(nameBytes);
            }
        }
        return allocate.array();
    }
}
