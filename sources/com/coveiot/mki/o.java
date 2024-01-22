package com.coveiot.mki;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public final class o extends l {
    public short b = 3;
    public short c = 0;

    @Override // com.coveiot.mki.l
    public final List<Byte> a() {
        ArrayList arrayList = (ArrayList) super.a();
        arrayList.add(0, Byte.valueOf((byte) this.c));
        return arrayList;
    }

    @Override // com.coveiot.mki.l
    public final Object[] a(List<Byte> list) {
        if (list.size() >= 1) {
            this.b = (short) (ByteBuffer.wrap(new byte[]{0, list.get(0).byteValue()}).getShort() & 3);
            list.remove(0);
            if (this.b != 3) {
                return null;
            }
            throw new Exception("Unknown request type");
        }
        throw new Exception("Invalid size");
    }

    public final short b() {
        return this.b;
    }

    public final void c() {
        this.b = (short) 0;
        this.c = (short) 64;
    }
}
