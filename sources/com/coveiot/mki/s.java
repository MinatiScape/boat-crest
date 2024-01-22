package com.coveiot.mki;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public final class s extends l {
    public short b;
    public long c;
    public int d;

    @Override // com.coveiot.mki.l
    public final List<Byte> a() {
        ArrayList arrayList = (ArrayList) super.a();
        arrayList.add(Byte.valueOf((byte) this.b));
        ByteBuffer allocate = ByteBuffer.allocate(8);
        ByteOrder byteOrder = ByteOrder.LITTLE_ENDIAN;
        byte[] array = allocate.order(byteOrder).putLong(this.c).array();
        arrayList.add(Byte.valueOf(array[0]));
        arrayList.add(Byte.valueOf(array[1]));
        arrayList.add(Byte.valueOf(array[2]));
        arrayList.add(Byte.valueOf(array[3]));
        byte[] array2 = ByteBuffer.allocate(4).order(byteOrder).putInt(this.d).array();
        arrayList.add(Byte.valueOf(array2[0]));
        arrayList.add(Byte.valueOf(array2[1]));
        return arrayList;
    }

    public final void a(long j, int i) {
        this.b = (short) 0;
        this.c = j;
        this.d = i;
    }
}
