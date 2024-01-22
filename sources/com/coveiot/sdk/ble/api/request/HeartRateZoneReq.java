package com.coveiot.sdk.ble.api.request;

import androidx.annotation.NonNull;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class HeartRateZoneReq extends BaseRequest {
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;

    /* loaded from: classes9.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Object f7500a;
        public int b;
        public int c;
        public int d;
        public int e;
        public int f;

        public Builder(int i, int i2, int i3, int i4, int i5) {
            this.b = i;
            this.c = i2;
            this.d = i3;
            this.e = i4;
            this.f = i5;
        }

        public HeartRateZoneReq build() {
            HeartRateZoneReq heartRateZoneReq = new HeartRateZoneReq(this.f7500a);
            heartRateZoneReq.f = this.b;
            heartRateZoneReq.g = this.c;
            heartRateZoneReq.h = this.d;
            heartRateZoneReq.i = this.e;
            heartRateZoneReq.j = this.f;
            return heartRateZoneReq;
        }
    }

    public HeartRateZoneReq(Object obj) {
        super(obj);
    }

    @NonNull
    public final List<CommandBytes> a() {
        ArrayList arrayList = new ArrayList();
        byte[] bArr = {(byte) getValue1(), (byte) getValue2(), (byte) getValue3(), (byte) getValue4(), (byte) getValue5()};
        byte[] bArr2 = new byte[9];
        System.arraycopy(new byte[]{1, -110, 9, 0}, 0, bArr2, 0, 4);
        System.arraycopy(bArr, 0, bArr2, 4, 5);
        CommandBytes commandBytes = new CommandBytes();
        commandBytes.setCommandData(bArr2);
        arrayList.add(commandBytes);
        return arrayList;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        return a();
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.UPDATE_HEARTRATE_ZONE;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattCharacteristicToRead() {
        return null;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattServiceToRead() {
        return null;
    }

    public int getValue1() {
        return this.f;
    }

    public int getValue2() {
        return this.g;
    }

    public int getValue3() {
        return this.h;
    }

    public int getValue4() {
        return this.i;
    }

    public int getValue5() {
        return this.j;
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
