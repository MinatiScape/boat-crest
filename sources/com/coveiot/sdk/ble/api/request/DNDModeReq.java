package com.coveiot.sdk.ble.api.request;

import com.coveiot.sdk.ble.api.BleUUID;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class DNDModeReq extends BaseRequest {
    public boolean f;
    public boolean g;
    public boolean h;
    public int i;
    public int j;
    public int k;
    public int l;

    /* loaded from: classes9.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Object f7484a;
        public boolean b;
        public boolean c;
        public boolean d;
        public int e;
        public int f;
        public int g;
        public int h;

        public Builder(boolean z, int i, int i2, int i3, int i4) {
            this.b = z;
            this.e = i;
            this.f = i2;
            this.g = i3;
            this.h = i4;
        }

        public DNDModeReq build() {
            DNDModeReq dNDModeReq = new DNDModeReq(this.f7484a);
            dNDModeReq.f = this.b;
            dNDModeReq.h = this.d;
            dNDModeReq.g = this.c;
            dNDModeReq.i = this.e;
            dNDModeReq.j = this.f;
            dNDModeReq.k = this.g;
            dNDModeReq.l = this.h;
            return dNDModeReq;
        }

        public Builder setDNDEnabled(boolean z) {
            this.b = z;
            return this;
        }

        public Builder setEndHour(int i) {
            this.g = i;
            return this;
        }

        public Builder setEndMin(int i) {
            this.h = i;
            return this;
        }

        public Builder setLiftWristEnabled(boolean z) {
            this.d = z;
            return this;
        }

        public Builder setNotificationEnabled(boolean z) {
            this.c = z;
            return this;
        }

        public Builder setStartHour(int i) {
            this.e = i;
            return this;
        }

        public Builder setStartMin(int i) {
            this.f = i;
            return this;
        }
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        ArrayList arrayList = new ArrayList();
        byte[] bArr = BleUUID.SET_DND_MODE;
        byte[] bArr2 = {this.f ? (byte) 1 : (byte) 0, this.g ? (byte) 1 : (byte) 0, this.h ? (byte) 1 : (byte) 0, (byte) this.i, (byte) this.j, (byte) this.k, (byte) this.l};
        byte[] bArr3 = new byte[bArr.length + 7];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, 7);
        CommandBytes commandBytes = new CommandBytes();
        commandBytes.setCommandData(bArr3);
        arrayList.add(commandBytes);
        return arrayList;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.SET_DND;
    }

    public int getEndHour() {
        return this.k;
    }

    public int getEndMin() {
        return this.l;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattCharacteristicToRead() {
        return null;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattServiceToRead() {
        return null;
    }

    public int getStartHour() {
        return this.i;
    }

    public int getStartMin() {
        return this.j;
    }

    public boolean isDNDEnabled() {
        return this.f;
    }

    public boolean isLiftWristEnabled() {
        return this.h;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean isMultiPacket() {
        return false;
    }

    public boolean isNotificationEnabled() {
        return this.g;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean isPriority() {
        return true;
    }

    public DNDModeReq(Object obj) {
        super(obj);
    }
}
