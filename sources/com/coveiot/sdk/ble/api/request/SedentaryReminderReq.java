package com.coveiot.sdk.ble.api.request;

import com.coveiot.sdk.ble.api.BleUUID;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class SedentaryReminderReq extends BaseRequest {
    public boolean f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;
    public int o;

    /* loaded from: classes9.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Object f7530a;
        public boolean b;
        public int c;
        public int d;
        public int e;
        public int f;
        public int g;
        public int h;
        public int i;
        public int j;
        public int k;

        public SedentaryReminderReq build() {
            SedentaryReminderReq sedentaryReminderReq = new SedentaryReminderReq(this.f7530a);
            sedentaryReminderReq.f = this.b;
            sedentaryReminderReq.g = this.c;
            sedentaryReminderReq.i = this.d;
            sedentaryReminderReq.h = this.g;
            sedentaryReminderReq.j = this.h;
            sedentaryReminderReq.k = this.e;
            sedentaryReminderReq.m = this.f;
            sedentaryReminderReq.l = this.i;
            sedentaryReminderReq.n = this.j;
            sedentaryReminderReq.o = this.k;
            return sedentaryReminderReq;
        }

        public Builder setEnabled(boolean z) {
            this.b = z;
            return this;
        }

        public Builder setEndHour1(int i) {
            this.e = i;
            return this;
        }

        public Builder setEndHour2(int i) {
            this.f = i;
            return this;
        }

        public Builder setEndMin1(int i) {
            this.i = i;
            return this;
        }

        public Builder setEndMin2(int i) {
            this.j = i;
            return this;
        }

        public Builder setId(Object obj) {
            this.f7530a = obj;
            return this;
        }

        public Builder setReminderInterval(int i) {
            this.k = i;
            return this;
        }

        public Builder setStartHour1(int i) {
            this.c = i;
            return this;
        }

        public Builder setStartHour2(int i) {
            this.d = i;
            return this;
        }

        public Builder setStartMin1(int i) {
            this.g = i;
            return this;
        }

        public Builder setStartMin2(int i) {
            this.h = i;
            return this;
        }
    }

    public SedentaryReminderReq(Object obj) {
        super(obj);
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        ArrayList arrayList = new ArrayList();
        byte[] bArr = BleUUID.SET_SEDNTARY_ALERT_INFO;
        byte[] bArr2 = {this.f ? (byte) 1 : (byte) 0, (byte) this.g, (byte) this.h, (byte) this.k, (byte) this.l, (byte) this.i, (byte) this.j, (byte) this.m, (byte) this.n, (byte) this.o};
        byte[] bArr3 = new byte[bArr.length + 10];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, 10);
        CommandBytes commandBytes = new CommandBytes();
        commandBytes.setCommandData(bArr3);
        arrayList.add(commandBytes);
        return arrayList;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.SET_SEDENTARY_ALERT_INFO;
    }

    public int getEndHour1() {
        return this.k;
    }

    public int getEndHour2() {
        return this.m;
    }

    public int getEndMin1() {
        return this.l;
    }

    public int getEndMin2() {
        return this.n;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattCharacteristicToRead() {
        return null;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattServiceToRead() {
        return null;
    }

    public int getReminderInterval() {
        return this.o;
    }

    public int getStartHour1() {
        return this.g;
    }

    public int getStartHour2() {
        return this.i;
    }

    public int getStartMin1() {
        return this.h;
    }

    public int getStartMin2() {
        return this.j;
    }

    public boolean isEnabled() {
        return this.f;
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
