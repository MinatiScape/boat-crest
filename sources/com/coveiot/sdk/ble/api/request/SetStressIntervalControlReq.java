package com.coveiot.sdk.ble.api.request;

import com.coveiot.sdk.ble.api.BleUUID;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class SetStressIntervalControlReq extends HybridCommandRequest {
    public byte[] h;
    public int i;
    public boolean j;
    public int k;
    public int l;
    public float m;
    public int n;

    public SetStressIntervalControlReq(Object obj, short s, int i, boolean z, int i2, int i3, float f, int i4) {
        super(obj, s);
        this.h = new byte[]{0, 0, 0, 0, 0, 0};
        this.i = 0;
        this.m = 0.0f;
        this.i = i;
        this.j = z;
        this.k = i2;
        this.l = i3;
        this.m = f;
        this.n = i4;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        ArrayList arrayList = new ArrayList();
        byte[] bArr = BleUUID.SET_STRESS_INTERVAL;
        ArrayList arrayList2 = new ArrayList();
        if (this.j) {
            arrayList2.add((byte) 1);
        } else {
            arrayList2.add((byte) 0);
        }
        arrayList2.add(Byte.valueOf((byte) this.i));
        ByteBuffer allocate = ByteBuffer.allocate(4);
        ByteOrder byteOrder = ByteOrder.LITTLE_ENDIAN;
        byte[] array = allocate.order(byteOrder).putInt(this.k).array();
        arrayList2.add(Byte.valueOf(array[0]));
        arrayList2.add(Byte.valueOf(array[1]));
        byte[] array2 = ByteBuffer.allocate(4).order(byteOrder).putInt(this.l).array();
        arrayList2.add(Byte.valueOf(array2[0]));
        arrayList2.add(Byte.valueOf(array2[1]));
        byte[] array3 = ByteBuffer.allocate(4).order(byteOrder).putInt((int) (this.m * 100.0f)).array();
        arrayList2.add(Byte.valueOf(array3[0]));
        arrayList2.add(Byte.valueOf(array3[1]));
        arrayList2.add(Byte.valueOf((byte) this.n));
        int i = 0;
        while (true) {
            byte[] bArr2 = this.h;
            if (i >= bArr2.length) {
                break;
            }
            arrayList2.add(Byte.valueOf(bArr2[i]));
            i++;
        }
        setPayload(arrayList2);
        ArrayList arrayList3 = new ArrayList();
        for (byte b : bArr) {
            arrayList3.add(Byte.valueOf(b));
        }
        arrayList3.addAll(super.getPayload());
        byte[] bArr3 = new byte[arrayList3.size()];
        for (int i2 = 0; i2 < arrayList3.size(); i2++) {
            bArr3[i2] = ((Byte) arrayList3.get(i2)).byteValue();
        }
        CommandBytes commandBytes = new CommandBytes();
        commandBytes.setCommandData(bArr3);
        arrayList.add(commandBytes);
        return arrayList;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.SET_STRESS_INTERVAL;
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
        return true;
    }
}
