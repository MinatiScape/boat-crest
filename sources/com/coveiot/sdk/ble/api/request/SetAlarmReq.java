package com.coveiot.sdk.ble.api.request;

import androidx.annotation.NonNull;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class SetAlarmReq extends BaseRequest {
    public boolean f;
    public int g;
    public String h;
    public int i;
    public int j;
    public boolean k;
    public boolean l;
    public boolean m;
    public boolean n;
    public boolean o;
    public boolean p;
    public boolean q;

    /* loaded from: classes9.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public boolean f7531a = true;
        public int b;
        public String c;
        public int d;
        public int e;
        public boolean f;
        public boolean g;
        public boolean h;
        public boolean i;
        public boolean j;
        public boolean k;
        public boolean l;
        public Object m;

        public Builder(int i, int i2, int i3) {
            this.b = i;
            this.d = i2;
            this.e = i3;
        }

        public SetAlarmReq build() {
            SetAlarmReq setAlarmReq = new SetAlarmReq(this.m);
            setAlarmReq.g = this.b;
            setAlarmReq.j = this.e;
            setAlarmReq.f = this.f7531a;
            setAlarmReq.i = this.d;
            setAlarmReq.h = this.c;
            setAlarmReq.k = this.f;
            setAlarmReq.l = this.g;
            setAlarmReq.m = this.h;
            setAlarmReq.n = this.i;
            setAlarmReq.o = this.j;
            setAlarmReq.p = this.k;
            setAlarmReq.q = this.l;
            return setAlarmReq;
        }

        public Object getData() {
            return this.m;
        }

        public Builder setAlarmOn(boolean z) {
            this.f7531a = z;
            return this;
        }

        public void setData(Object obj) {
            this.m = obj;
        }

        public Builder setDays(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7) {
            this.f = z;
            this.g = z2;
            this.h = z3;
            this.i = z4;
            this.j = z5;
            this.k = z6;
            this.l = z7;
            return this;
        }

        public Builder setEventName(String str) {
            this.c = str;
            return this;
        }
    }

    public SetAlarmReq(Object obj) {
        super(obj);
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [boolean, int] */
    @NonNull
    public final List<CommandBytes> a() {
        ArrayList arrayList = new ArrayList();
        ?? r1 = this.k;
        int i = r1;
        if (this.l) {
            i = r1 + 2;
        }
        int i2 = i;
        if (this.m) {
            i2 = i + 4;
        }
        int i3 = i2;
        if (this.n) {
            i3 = i2 + 8;
        }
        int i4 = i3;
        if (this.o) {
            i4 = i3 + 16;
        }
        int i5 = i4;
        if (this.p) {
            i5 = i4 + 32;
        }
        int i6 = i5;
        if (this.q) {
            i6 = i5 + 64;
        }
        int i7 = i6;
        if (this.f) {
            i7 = i6 + 128;
        }
        String eventName = getEventName();
        int length = eventName.length();
        if (eventName.length() > 12) {
            eventName = eventName.substring(0, 11);
            length = 12;
        }
        int i8 = length + 8;
        byte[] bArr = {(byte) getAlarmId(), (byte) i7, (byte) getHour(), (byte) getMinute()};
        byte[] bArr2 = new byte[8];
        System.arraycopy(new byte[]{2, -123, (byte) i8, 0}, 0, bArr2, 0, 4);
        System.arraycopy(bArr, 0, bArr2, 4, 4);
        byte[] bytes = eventName.getBytes();
        byte[] bArr3 = new byte[i8];
        System.arraycopy(bArr2, 0, bArr3, 0, 8);
        System.arraycopy(bytes, 0, bArr3, 8, bytes.length);
        CommandBytes commandBytes = new CommandBytes();
        commandBytes.setCommandData(bArr3);
        arrayList.add(commandBytes);
        return arrayList;
    }

    public int getAlarmId() {
        return this.g;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        return a();
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.SET_ALARM_INFO;
    }

    public String getEventName() {
        return this.h;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattCharacteristicToRead() {
        return null;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattServiceToRead() {
        return null;
    }

    public int getHour() {
        return this.i;
    }

    public int getMinute() {
        return this.j;
    }

    public boolean isAlarmOn() {
        return this.f;
    }

    public boolean isFriday() {
        return this.p;
    }

    public boolean isMonday() {
        return this.l;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean isPriority() {
        return true;
    }

    public boolean isSaturday() {
        return this.q;
    }

    public boolean isSunday() {
        return this.k;
    }

    public boolean isThursday() {
        return this.o;
    }

    public boolean isTuesday() {
        return this.m;
    }

    public boolean isWednesday() {
        return this.n;
    }
}
