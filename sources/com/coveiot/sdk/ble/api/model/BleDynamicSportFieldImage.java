package com.coveiot.sdk.ble.api.model;

import com.coveiot.sdk.ble.utils.BleUtils;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
/* loaded from: classes9.dex */
public class BleDynamicSportFieldImage extends BleDynamicSportsField {
    private byte dataType;
    private int imageId;

    public BleDynamicSportFieldImage(int i, int i2, int i3, int i4) {
        super(i, i2, i3, -1, -1);
        this.dataType = (byte) 5;
        this.imageId = i4;
    }

    @Override // com.coveiot.sdk.ble.api.model.BleDynamicSportsField
    public byte[] getDataBytes() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Byte.valueOf(this.dataType));
        ByteBuffer allocate = ByteBuffer.allocate(4);
        ByteOrder byteOrder = ByteOrder.LITTLE_ENDIAN;
        byte[] array = allocate.order(byteOrder).putInt(this.xPosition).array();
        arrayList.add(Byte.valueOf(array[0]));
        arrayList.add(Byte.valueOf(array[1]));
        byte[] array2 = ByteBuffer.allocate(4).order(byteOrder).putInt(this.yPosition).array();
        arrayList.add(Byte.valueOf(array2[0]));
        arrayList.add(Byte.valueOf(array2[1]));
        byte[] array3 = ByteBuffer.allocate(4).order(byteOrder).putInt(this.imageId).array();
        arrayList.add(Byte.valueOf(array3[0]));
        arrayList.add(Byte.valueOf(array3[1]));
        byte[] array4 = ByteBuffer.allocate(4).order(byteOrder).putInt(arrayList.size() + 4).array();
        arrayList.add(0, Byte.valueOf(array4[3]));
        arrayList.add(0, Byte.valueOf(array4[2]));
        arrayList.add(0, Byte.valueOf(array4[1]));
        arrayList.add(0, Byte.valueOf(array4[0]));
        return BleUtils.convertByteArrayListToByteArray(arrayList);
    }
}
