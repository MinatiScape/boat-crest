package com.coveiot.mki;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
/* renamed from: com.coveiot.mki.r  reason: case insensitive filesystem */
/* loaded from: classes9.dex */
public final class C0974r extends l {
    public int b;
    public byte[] c;

    @Override // com.coveiot.mki.l
    public final List<Byte> a() {
        ArrayList arrayList = new ArrayList(this.c.length + 5);
        arrayList.add(Byte.valueOf((byte) g.a(this.b)));
        byte[] array = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(this.c.length).array();
        arrayList.add(Byte.valueOf(array[0]));
        arrayList.add(Byte.valueOf(array[1]));
        arrayList.add(Byte.valueOf(array[2]));
        arrayList.add(Byte.valueOf(array[3]));
        for (byte b : this.c) {
            arrayList.add(Byte.valueOf(b));
        }
        arrayList.addAll(0, super.a());
        return arrayList;
    }

    public final void a(byte[] bArr) {
        this.b = 3;
        this.c = bArr;
    }

    @Override // com.coveiot.mki.l
    public final Object[] a(List<Byte> list) {
        if (list.size() == 3) {
            if (list.get(2).byteValue() == 1) {
                p.b("Sending update file successful, total packages sent: %d", Integer.valueOf(ByteBuffer.wrap(new byte[]{0, 0, list.get(1).byteValue(), list.get(0).byteValue()}).getInt() + 1));
                return null;
            }
            throw new Exception("Failure status received");
        }
        throw new Exception("Invalid size");
    }
}
