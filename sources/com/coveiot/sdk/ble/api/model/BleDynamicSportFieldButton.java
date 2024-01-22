package com.coveiot.sdk.ble.api.model;

import com.coveiot.sdk.ble.utils.BleUtils;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
/* loaded from: classes9.dex */
public class BleDynamicSportFieldButton extends BleDynamicSportsField {
    private int buttonAction;
    private int buttonId;
    private int buttonRgb565Color;
    private String buttonText;
    private byte dataType;
    private int sideBySide;
    private int textFontSize;

    public BleDynamicSportFieldButton(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, String str) {
        super(i2, i3, i4, i6, i7);
        this.dataType = (byte) 8;
        this.buttonId = i;
        this.sideBySide = i5;
        this.buttonAction = i8;
        this.buttonRgb565Color = i9;
        this.textFontSize = i10;
        this.buttonText = str;
    }

    @Override // com.coveiot.sdk.ble.api.model.BleDynamicSportsField
    public byte[] getDataBytes() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Byte.valueOf(this.dataType));
        arrayList.add(Byte.valueOf((byte) this.buttonId));
        arrayList.add(Byte.valueOf((byte) this.sideBySide));
        arrayList.add(Byte.valueOf((byte) this.yPosition));
        arrayList.add(Byte.valueOf((byte) this.xPosition));
        ByteBuffer allocate = ByteBuffer.allocate(4);
        ByteOrder byteOrder = ByteOrder.LITTLE_ENDIAN;
        byte[] array = allocate.order(byteOrder).putInt(this.length).array();
        arrayList.add(Byte.valueOf(array[0]));
        arrayList.add(Byte.valueOf(array[1]));
        byte[] array2 = ByteBuffer.allocate(4).order(byteOrder).putInt(this.width).array();
        arrayList.add(Byte.valueOf(array2[0]));
        arrayList.add(Byte.valueOf(array2[1]));
        byte[] array3 = ByteBuffer.allocate(4).order(byteOrder).putInt(this.buttonAction).array();
        arrayList.add(Byte.valueOf(array3[0]));
        arrayList.add(Byte.valueOf(array3[1]));
        byte[] array4 = ByteBuffer.allocate(4).order(byteOrder).putInt(this.buttonRgb565Color).array();
        arrayList.add(Byte.valueOf(array4[1]));
        arrayList.add(Byte.valueOf(array4[0]));
        byte[] array5 = ByteBuffer.allocate(4).order(byteOrder).putInt(this.color_rgb_565).array();
        arrayList.add(Byte.valueOf(array5[1]));
        arrayList.add(Byte.valueOf(array5[0]));
        arrayList.add(Byte.valueOf((byte) this.textFontSize));
        String str = this.buttonText;
        if (str != null && !str.isEmpty()) {
            byte[] bytes = this.buttonText.getBytes(StandardCharsets.UTF_16LE);
            arrayList.add(Byte.valueOf((byte) bytes.length));
            for (byte b : bytes) {
                arrayList.add(Byte.valueOf(b));
            }
        } else {
            arrayList.add((byte) 0);
        }
        byte[] array6 = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(arrayList.size() + 4).array();
        arrayList.add(0, Byte.valueOf(array6[3]));
        arrayList.add(0, Byte.valueOf(array6[2]));
        arrayList.add(0, Byte.valueOf(array6[1]));
        arrayList.add(0, Byte.valueOf(array6[0]));
        return BleUtils.convertByteArrayListToByteArray(arrayList);
    }
}
