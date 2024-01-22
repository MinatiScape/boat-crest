package com.coveiot.sdk.ble.api.model;

import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.sdk.ble.utils.DevicePlatformEnum;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
/* loaded from: classes9.dex */
public class BleDynamicSportFieldTextV2 extends BleDynamicSportsField {
    private byte dataType;
    public int font;
    private int format;
    public String text;
    public int typeFace;

    public BleDynamicSportFieldTextV2(int i, int i2, int i3, int i4, int i5, int i6, int i7, String str, int i8) {
        super(i, i2, i3, i4, i5);
        this.dataType = (byte) 0;
        this.font = i6;
        this.typeFace = i7;
        this.text = str;
        this.format = i8;
    }

    @Override // com.coveiot.sdk.ble.api.model.BleDynamicSportsField
    public byte[] getDataBytes() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Byte.valueOf(this.dataType));
        arrayList.add(Byte.valueOf((byte) this.font));
        arrayList.add(Byte.valueOf((byte) this.typeFace));
        ByteBuffer allocate = ByteBuffer.allocate(4);
        ByteOrder byteOrder = ByteOrder.LITTLE_ENDIAN;
        byte[] array = allocate.order(byteOrder).putInt(this.color_rgb_565).array();
        if (this.devicePlatformEnum == DevicePlatformEnum.Apollo) {
            arrayList.add(Byte.valueOf(array[0]));
            arrayList.add(Byte.valueOf(array[1]));
        } else {
            arrayList.add(Byte.valueOf(array[1]));
            arrayList.add(Byte.valueOf(array[0]));
        }
        byte[] array2 = ByteBuffer.allocate(4).order(byteOrder).putInt(this.xPosition).array();
        arrayList.add(Byte.valueOf(array2[0]));
        arrayList.add(Byte.valueOf(array2[1]));
        byte[] array3 = ByteBuffer.allocate(4).order(byteOrder).putInt(this.yPosition).array();
        arrayList.add(Byte.valueOf(array3[0]));
        arrayList.add(Byte.valueOf(array3[1]));
        byte[] array4 = ByteBuffer.allocate(4).order(byteOrder).putInt(this.length).array();
        arrayList.add(Byte.valueOf(array4[0]));
        arrayList.add(Byte.valueOf(array4[1]));
        byte[] array5 = ByteBuffer.allocate(4).order(byteOrder).putInt(this.width).array();
        arrayList.add(Byte.valueOf(array5[0]));
        arrayList.add(Byte.valueOf(array5[1]));
        arrayList.add(Byte.valueOf((byte) this.format));
        for (int i = 0; i < this.text.getBytes(StandardCharsets.UTF_16LE).length; i++) {
            arrayList.add(Byte.valueOf(this.text.getBytes(StandardCharsets.UTF_16LE)[i]));
        }
        arrayList.add((byte) 0);
        arrayList.add((byte) 0);
        byte[] array6 = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(arrayList.size() + 4).array();
        arrayList.add(0, Byte.valueOf(array6[3]));
        arrayList.add(0, Byte.valueOf(array6[2]));
        arrayList.add(0, Byte.valueOf(array6[1]));
        arrayList.add(0, Byte.valueOf(array6[0]));
        return BleUtils.convertByteArrayListToByteArray(arrayList);
    }
}
