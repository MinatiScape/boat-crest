package com.coveiot.sdk.ble.api.model;

import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.sdk.ble.utils.DevicePlatformEnum;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
/* loaded from: classes9.dex */
public class BleDynamicSportFieldProgressBar extends BleDynamicSportsField {
    private int barBackgroundImageId;
    private int barEndImageId;
    private int barPercentage;
    private int barSlideImageId;

    public BleDynamicSportFieldProgressBar(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        super(i, i2, i3, 0, 0);
        this.barBackgroundImageId = i4;
        this.barSlideImageId = i5;
        this.barEndImageId = i6;
        this.barPercentage = i7;
    }

    @Override // com.coveiot.sdk.ble.api.model.BleDynamicSportsField
    public byte[] getDataBytes() {
        ArrayList arrayList = new ArrayList();
        arrayList.add((byte) 10);
        ByteBuffer allocate = ByteBuffer.allocate(4);
        ByteOrder byteOrder = ByteOrder.LITTLE_ENDIAN;
        byte[] array = allocate.order(byteOrder).putInt(this.xPosition).array();
        arrayList.add(Byte.valueOf(array[0]));
        arrayList.add(Byte.valueOf(array[1]));
        byte[] array2 = ByteBuffer.allocate(4).order(byteOrder).putInt(this.yPosition).array();
        arrayList.add(Byte.valueOf(array2[0]));
        arrayList.add(Byte.valueOf(array2[1]));
        byte[] array3 = ByteBuffer.allocate(4).order(byteOrder).putInt(this.barBackgroundImageId).array();
        arrayList.add(Byte.valueOf(array3[0]));
        arrayList.add(Byte.valueOf(array3[1]));
        byte[] array4 = ByteBuffer.allocate(4).order(byteOrder).putInt(this.barSlideImageId).array();
        arrayList.add(Byte.valueOf(array4[0]));
        arrayList.add(Byte.valueOf(array4[1]));
        byte[] array5 = ByteBuffer.allocate(4).order(byteOrder).putInt(this.barEndImageId).array();
        arrayList.add(Byte.valueOf(array5[0]));
        arrayList.add(Byte.valueOf(array5[1]));
        arrayList.add(Byte.valueOf((byte) this.barPercentage));
        byte[] array6 = ByteBuffer.allocate(4).order(byteOrder).putInt(this.color_rgb_565).array();
        if (this.devicePlatformEnum == DevicePlatformEnum.Apollo) {
            arrayList.add(Byte.valueOf(array6[0]));
            arrayList.add(Byte.valueOf(array6[1]));
        } else {
            arrayList.add(Byte.valueOf(array6[1]));
            arrayList.add(Byte.valueOf(array6[0]));
        }
        byte[] array7 = ByteBuffer.allocate(4).order(byteOrder).putInt(arrayList.size() + 4).array();
        arrayList.add(0, Byte.valueOf(array7[3]));
        arrayList.add(0, Byte.valueOf(array7[2]));
        arrayList.add(0, Byte.valueOf(array7[1]));
        arrayList.add(0, Byte.valueOf(array7[0]));
        return BleUtils.convertByteArrayListToByteArray(arrayList);
    }
}
