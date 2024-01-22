package com.coveiot.sdk.ble.api.request;

import androidx.annotation.NonNull;
import com.coveiot.sdk.ble.api.BleUUID;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class LiftWristViewReq extends BaseRequest {
    public boolean f;
    public boolean g;
    public int h;
    public int i;
    public int j;
    public int k;

    /* loaded from: classes9.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public boolean f7501a;
        public boolean b;
        public int c;
        public int d;
        public int e;
        public int f;
        public Object g;

        public Builder(boolean z) {
            this.f7501a = z;
        }

        public LiftWristViewReq build() {
            LiftWristViewReq liftWristViewReq = new LiftWristViewReq(this.g);
            liftWristViewReq.f = this.f7501a;
            liftWristViewReq.g = this.b;
            liftWristViewReq.h = this.c;
            liftWristViewReq.i = this.d;
            liftWristViewReq.j = this.e;
            liftWristViewReq.k = this.f;
            return liftWristViewReq;
        }

        public Builder setEndHour(int i) {
            this.d = i;
            return this;
        }

        public Builder setEndMinute(int i) {
            this.f = i;
            return this;
        }

        public Builder setId(Object obj) {
            this.g = obj;
            return this;
        }

        public Builder setScheduledDndSupported(boolean z) {
            this.b = z;
            return this;
        }

        public Builder setStartHour(int i) {
            this.c = i;
            return this;
        }

        public Builder setStartMinute(int i) {
            this.e = i;
            return this;
        }
    }

    public LiftWristViewReq(Object obj) {
        super(obj);
    }

    @NonNull
    public final List<CommandBytes> a() {
        ArrayList arrayList = new ArrayList();
        byte[] bArr = BleUUID.SET_LIFT_WRIST;
        byte[] bArr2 = {isLiftWristEnabled() ? (byte) 1 : (byte) 0};
        if (this.g) {
            bArr = BleUUID.SET_LIFT_WRIST_REVISED;
            bArr2 = new byte[]{isLiftWristEnabled() ? (byte) 1 : (byte) 0, (byte) this.h, (byte) this.j, (byte) this.i, (byte) this.k};
        }
        byte[] bArr3 = new byte[bArr.length + bArr2.length];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
        CommandBytes commandBytes = new CommandBytes();
        commandBytes.setCommandData(bArr3);
        arrayList.add(commandBytes);
        return arrayList;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        return a();
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.SET_LIFT_WRIST;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattCharacteristicToRead() {
        return null;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattServiceToRead() {
        return null;
    }

    public boolean isLiftWristEnabled() {
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
