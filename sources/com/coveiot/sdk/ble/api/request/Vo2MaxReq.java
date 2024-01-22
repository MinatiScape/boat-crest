package com.coveiot.sdk.ble.api.request;

import androidx.annotation.NonNull;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class Vo2MaxReq extends BaseRequest {
    public int f;
    public int g;
    public int h;
    public int i;

    /* loaded from: classes9.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public Object f7548a;
        public int b;
        public int c;
        public int d;
        public int e;

        public Builder(int i, int i2, int i3, int i4) {
            this.b = i;
            this.c = i2;
            this.d = i3;
            this.e = i4;
        }

        public Vo2MaxReq build() {
            Vo2MaxReq vo2MaxReq = new Vo2MaxReq(this.f7548a);
            vo2MaxReq.f = this.b;
            vo2MaxReq.h = this.d;
            vo2MaxReq.i = this.e;
            vo2MaxReq.g = this.c;
            return vo2MaxReq;
        }
    }

    public Vo2MaxReq(Object obj) {
        super(obj);
    }

    @NonNull
    public final List<CommandBytes> a() {
        ArrayList arrayList = new ArrayList();
        byte[] bArr = {(byte) getVo2MaxValue(), (byte) getYear(), (byte) getMonth(), (byte) getDay()};
        byte[] bArr2 = new byte[8];
        System.arraycopy(new byte[]{1, -111, 8, 0}, 0, bArr2, 0, 4);
        System.arraycopy(bArr, 0, bArr2, 4, 4);
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
        return CommandNames.UPDATE_VO2MAX_VALUE;
    }

    public int getDay() {
        return this.i;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattCharacteristicToRead() {
        return null;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattServiceToRead() {
        return null;
    }

    public int getMonth() {
        return this.h;
    }

    public int getVo2MaxValue() {
        return this.f;
    }

    public int getYear() {
        return this.g;
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
