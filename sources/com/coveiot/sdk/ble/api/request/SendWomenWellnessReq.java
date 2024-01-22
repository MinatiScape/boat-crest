package com.coveiot.sdk.ble.api.request;

import com.coveiot.sdk.ble.api.BleUUID;
import com.coveiot.sdk.ble.api.model.WomenWellnessModel;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class SendWomenWellnessReq extends BaseRequest {
    public boolean f;
    public long g;
    public short h;
    public short i;
    public short j;
    public int k;
    public short l;
    public int m;
    public short n;
    public boolean o;

    public SendWomenWellnessReq(Object obj, WomenWellnessModel womenWellnessModel) {
        super(obj);
        this.f = womenWellnessModel.isEnabled();
        this.g = womenWellnessModel.getLastMenstrualTimestamp();
        this.h = womenWellnessModel.getMenstrualPeriodLength();
        this.i = womenWellnessModel.getMenstrualCycleLength();
        this.j = womenWellnessModel.getMenstruationNotificationDay();
        this.k = womenWellnessModel.getMenstruationNotificationMinute();
        this.l = womenWellnessModel.getOvulationNotificationDay();
        this.m = womenWellnessModel.getOvulationNotificationMinute();
        this.n = womenWellnessModel.getReminderType();
        this.o = womenWellnessModel.isVibrationEnabled();
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        ArrayList arrayList = new ArrayList();
        byte[] bArr = BleUUID.SET_WOMEN_WELNESS;
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(Byte.valueOf(this.f ? (byte) 1 : (byte) 0));
        ByteBuffer allocate = ByteBuffer.allocate(4);
        ByteOrder byteOrder = ByteOrder.LITTLE_ENDIAN;
        byte[] array = allocate.order(byteOrder).putInt((int) this.g).array();
        arrayList2.add(Byte.valueOf(array[0]));
        arrayList2.add(Byte.valueOf(array[1]));
        arrayList2.add(Byte.valueOf(array[2]));
        arrayList2.add(Byte.valueOf(array[3]));
        arrayList2.add(Byte.valueOf((byte) this.h));
        arrayList2.add(Byte.valueOf((byte) this.i));
        arrayList2.add(Byte.valueOf((byte) this.j));
        byte[] array2 = ByteBuffer.allocate(2).order(byteOrder).putShort((short) this.k).array();
        arrayList2.add(Byte.valueOf(array2[0]));
        arrayList2.add(Byte.valueOf(array2[1]));
        arrayList2.add(Byte.valueOf((byte) this.l));
        byte[] array3 = ByteBuffer.allocate(2).order(byteOrder).putShort((short) this.m).array();
        arrayList2.add(Byte.valueOf(array3[0]));
        arrayList2.add(Byte.valueOf(array3[1]));
        arrayList2.add(Byte.valueOf((byte) this.n));
        arrayList2.add(Byte.valueOf(this.o ? (byte) 1 : (byte) 0));
        int size = arrayList2.size();
        byte[] bArr2 = new byte[size];
        for (int i = 0; i < arrayList2.size(); i++) {
            bArr2[i] = ((Byte) arrayList2.get(i)).byteValue();
        }
        byte[] bArr3 = new byte[bArr.length + arrayList2.size()];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, size);
        CommandBytes commandBytes = new CommandBytes();
        commandBytes.setCommandData(bArr3);
        arrayList.add(commandBytes);
        return arrayList;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.SET_WOMEN_WELLNESS;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattCharacteristicToRead() {
        return null;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattServiceToRead() {
        return null;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean isPriority() {
        return false;
    }
}
