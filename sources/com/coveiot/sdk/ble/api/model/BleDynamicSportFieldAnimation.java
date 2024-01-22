package com.coveiot.sdk.ble.api.model;

import com.coveiot.sdk.ble.utils.BleUtils;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
/* loaded from: classes9.dex */
public class BleDynamicSportFieldAnimation extends BleDynamicSportsField {
    private byte dataType;
    private int format;
    private int frameTime;
    private ArrayList<Integer> imgIdList;
    private int imgNum;

    public BleDynamicSportFieldAnimation(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, ArrayList<Integer> arrayList) {
        super(i, i2, i3, i4, i5);
        this.dataType = (byte) 6;
        this.imgIdList = new ArrayList<>();
        this.format = i6;
        this.frameTime = i7;
        this.imgNum = i8;
        if (arrayList != null) {
            this.imgIdList = arrayList;
        }
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
        byte[] array3 = ByteBuffer.allocate(4).order(byteOrder).putInt(this.width).array();
        arrayList.add(Byte.valueOf(array3[0]));
        arrayList.add(Byte.valueOf(array3[1]));
        byte[] array4 = ByteBuffer.allocate(4).order(byteOrder).putInt(this.length).array();
        arrayList.add(Byte.valueOf(array4[0]));
        arrayList.add(Byte.valueOf(array4[1]));
        arrayList.add(Byte.valueOf((byte) this.format));
        arrayList.add(Byte.valueOf((byte) this.frameTime));
        arrayList.add(Byte.valueOf((byte) this.imgIdList.size()));
        for (int i = 0; i < this.imgIdList.size(); i++) {
            byte[] array5 = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(this.imgIdList.get(i).intValue()).array();
            arrayList.add(Byte.valueOf(array5[0]));
            arrayList.add(Byte.valueOf(array5[1]));
        }
        byte[] array6 = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(arrayList.size() + 4).array();
        arrayList.add(0, Byte.valueOf(array6[3]));
        arrayList.add(0, Byte.valueOf(array6[2]));
        arrayList.add(0, Byte.valueOf(array6[1]));
        arrayList.add(0, Byte.valueOf(array6[0]));
        return BleUtils.convertByteArrayListToByteArray(arrayList);
    }
}
