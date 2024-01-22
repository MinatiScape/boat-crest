package com.coveiot.mki;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public final class q extends l {
    public int b;
    public int c;
    public byte[] d;

    @Override // com.coveiot.mki.l
    public final List<Byte> a() {
        List<Byte> a2 = super.a();
        ByteBuffer allocate = ByteBuffer.allocate(4);
        ByteOrder byteOrder = ByteOrder.LITTLE_ENDIAN;
        byte[] array = allocate.order(byteOrder).putInt(this.b).array();
        ArrayList arrayList = (ArrayList) a2;
        arrayList.add(Byte.valueOf(array[0]));
        arrayList.add(Byte.valueOf(array[1]));
        byte[] array2 = ByteBuffer.allocate(4).order(byteOrder).putInt(this.c).array();
        arrayList.add(Byte.valueOf(array2[0]));
        arrayList.add(Byte.valueOf(array2[1]));
        for (byte b : this.d) {
            arrayList.add(Byte.valueOf(b));
        }
        return arrayList;
    }

    public final void a(int i, int i2, byte[] bArr) {
        this.b = i;
        this.c = i2;
        this.d = bArr;
    }
}
