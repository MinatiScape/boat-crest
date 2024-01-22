package com.coveiot.sdk.ble.api.model;

import com.coveiot.sdk.ble.utils.BleUtils;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
/* loaded from: classes9.dex */
public class DrinkingReminder extends CustomReminder {
    private TimeInfo endTime;
    private int frequency;
    private TimeInfo startTime;

    public DrinkingReminder(boolean z, int i, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8, int i2, String str, TimeInfo timeInfo, TimeInfo timeInfo2, int i3) {
        super(i, z2, z3, z4, z5, z6, z7, z8, i2, str, z);
        this.startTime = timeInfo;
        this.endTime = timeInfo2;
        this.frequency = i3;
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [boolean, int] */
    private int getRepeatValue() {
        ?? r0 = this.sunday;
        int i = r0;
        if (this.monday) {
            i = r0 + 2;
        }
        int i2 = i;
        if (this.tuesday) {
            i2 = i + 4;
        }
        int i3 = i2;
        if (this.wednesday) {
            i3 = i2 + 8;
        }
        int i4 = i3;
        if (this.thursday) {
            i4 = i3 + 16;
        }
        int i5 = i4;
        if (this.friday) {
            i5 = i4 + 32;
        }
        int i6 = i5;
        if (this.saturday) {
            i6 = i5 + 64;
        }
        return this.isReminderOn ? i6 + 128 : i6;
    }

    @Override // com.coveiot.sdk.ble.api.model.CustomReminder
    public byte[] getDataBytes() {
        ArrayList arrayList = new ArrayList();
        arrayList.add((byte) 0);
        ByteBuffer allocate = ByteBuffer.allocate(4);
        ByteOrder byteOrder = ByteOrder.LITTLE_ENDIAN;
        byte[] array = allocate.order(byteOrder).putInt(this.imageId).array();
        arrayList.add(Byte.valueOf(array[0]));
        arrayList.add(Byte.valueOf(array[1]));
        arrayList.add(Byte.valueOf((byte) getRepeatValue()));
        arrayList.add(Byte.valueOf((byte) this.vibrationSeqID));
        byte[] array2 = ByteBuffer.allocate(4).order(byteOrder).putInt(this.startTime.getTimeInMinutes()).array();
        arrayList.add(Byte.valueOf(array2[0]));
        arrayList.add(Byte.valueOf(array2[1]));
        byte[] array3 = ByteBuffer.allocate(8).order(byteOrder).putInt(this.endTime.getTimeInMinutes()).array();
        arrayList.add(Byte.valueOf(array3[0]));
        arrayList.add(Byte.valueOf(array3[1]));
        byte[] array4 = ByteBuffer.allocate(4).order(byteOrder).putInt(this.frequency).array();
        arrayList.add(Byte.valueOf(array4[0]));
        arrayList.add(Byte.valueOf(array4[1]));
        for (int i = 0; i < this.description.getBytes(StandardCharsets.UTF_16LE).length; i++) {
            arrayList.add(Byte.valueOf(this.description.getBytes(StandardCharsets.UTF_16LE)[i]));
        }
        arrayList.add((byte) 0);
        arrayList.add((byte) 0);
        byte[] array5 = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(arrayList.size() + 2).array();
        arrayList.add(0, Byte.valueOf(array5[1]));
        arrayList.add(0, Byte.valueOf(array5[0]));
        return BleUtils.convertByteArrayListToByteArray(arrayList);
    }
}
