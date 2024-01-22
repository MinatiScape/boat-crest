package com.coveiot.sdk.ble.api.request;

import com.coveiot.sdk.ble.api.MultiPacketRequestGenerator;
import com.coveiot.sdk.ble.api.model.BleNavigationItem;
import com.coveiot.sdk.ble.api.model.CommonAppContentFeatureType;
import com.coveiot.sdk.ble.api.model.DisplayPosition;
import com.coveiot.sdk.ble.api.model.ExpectedDataSize;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class SetNavigationApplicationContentReq extends BaseRequest {
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public long l;
    public List<BleNavigationItem> m;

    public SetNavigationApplicationContentReq(Object obj, int i, int i2, int i3, int i4, int i5, long j, List<BleNavigationItem> list) {
        super(obj);
        this.f = CommonAppContentFeatureType.NAVIGATION.ordinal();
        this.h = DisplayPosition.APP_SCREEN.ordinal();
        this.g = i;
        this.h = i2;
        this.i = i3;
        this.j = i4;
        this.k = i5;
        this.l = j;
        this.m = list;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        char c;
        ArrayList<CommandBytes> arrayList = new ArrayList<>();
        if (this.m != null) {
            int i = 11;
            char c2 = 3;
            int i2 = 4;
            byte[] array = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putLong(this.l).array();
            int i3 = 6;
            byte[] bArr = {(byte) this.f, (byte) this.g, (byte) this.h, (byte) this.i, (byte) this.j, (byte) this.k, array[0], array[1], array[2], array[3], (byte) this.m.size()};
            int i4 = 0;
            for (int i5 = 0; i5 < this.m.size(); i5++) {
                if (this.m.get(i5).getBleDynamicSportFieldText() != null) {
                    i4 += this.m.get(i5).getBleDynamicSportFieldText().getDataBytes().length + 6 + 4;
                }
            }
            byte[] bArr2 = new byte[i4];
            int i6 = 0;
            int i7 = 0;
            while (i6 < this.m.size()) {
                if (this.m.get(i6).getBleDynamicSportFieldText() != null) {
                    byte[] dataBytes = this.m.get(i6).getBleDynamicSportFieldText().getDataBytes();
                    ByteBuffer allocate = ByteBuffer.allocate(8);
                    ByteOrder byteOrder = ByteOrder.LITTLE_ENDIAN;
                    byte[] array2 = allocate.order(byteOrder).putLong(dataBytes.length + i3 + i2).array();
                    int i8 = i7 + 1;
                    bArr2[i7] = array2[0];
                    int i9 = i8 + 1;
                    bArr2[i8] = array2[1];
                    int i10 = i9 + 1;
                    bArr2[i9] = array2[2];
                    int i11 = i10 + 1;
                    bArr2[i10] = array2[3];
                    byte[] array3 = ByteBuffer.allocate(4).order(byteOrder).putInt(this.m.get(i6).getImageId()).array();
                    int i12 = i11 + 1;
                    bArr2[i11] = array3[0];
                    int i13 = i12 + 1;
                    bArr2[i12] = array3[1];
                    byte[] array4 = ByteBuffer.allocate(8).order(byteOrder).putLong(this.m.get(i6).getDistance()).array();
                    int i14 = i13 + 1;
                    bArr2[i13] = array4[0];
                    int i15 = i14 + 1;
                    bArr2[i14] = array4[1];
                    int i16 = i15 + 1;
                    bArr2[i15] = array4[2];
                    c = 3;
                    bArr2[i16] = array4[3];
                    int i17 = 0;
                    i7 = i16 + 1;
                    while (i17 < dataBytes.length) {
                        bArr2[i7] = dataBytes[i17];
                        i17++;
                        i7++;
                    }
                } else {
                    c = c2;
                }
                i6++;
                c2 = c;
                i = 11;
                i2 = 4;
                i3 = 6;
            }
            if (i + i4 + 4 > 150) {
                arrayList = MultiPacketRequestGenerator.generateRequest((byte) 2, (byte) -88, bArr2, bArr, false, false);
            } else {
                arrayList = MultiPacketRequestGenerator.generateSinglePacketRequest((byte) 2, (byte) -88, bArr2, bArr, false);
            }
            this.f7474a = new ExpectedDataSize(arrayList.size());
        }
        return arrayList;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.SET_COMMON_APPLICATION_CONTENT;
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
        return true;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean isPriority() {
        return true;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean shouldSendAllPacketsAtOnce() {
        return true;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean shouldWaitForRes() {
        return true;
    }
}
