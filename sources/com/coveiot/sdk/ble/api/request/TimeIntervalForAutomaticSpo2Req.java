package com.coveiot.sdk.ble.api.request;

import androidx.annotation.NonNull;
import com.coveiot.sdk.ble.api.BleUUID;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class TimeIntervalForAutomaticSpo2Req extends BaseRequest {
    public int f;
    public boolean g;
    public int h;
    public int i;
    public int j;
    public int k;

    /* loaded from: classes9.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public int f7543a;
        public boolean b;
        public int c;
        public int d = 0;
        public int e;
        public int f;

        public Builder(int i, boolean z, int i2, int i3, int i4, int i5) {
            this.c = 0;
            this.e = 0;
            this.f = 0;
            this.f7543a = i;
            this.b = z;
            this.c = i2;
            this.c = i3;
            this.e = i4;
            this.f = i5;
        }

        public TimeIntervalForAutomaticSpo2Req build() {
            return new TimeIntervalForAutomaticSpo2Req(this.f7543a, this.b, this.c, this.d, this.e, this.f);
        }
    }

    public TimeIntervalForAutomaticSpo2Req(int i, boolean z, int i2, int i3, int i4, int i5) {
        super(Integer.valueOf(i));
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.f = i;
        this.g = z;
        this.h = i2;
        this.h = i3;
        this.j = i4;
        this.k = i5;
    }

    @NonNull
    public final List<CommandBytes> a() {
        ArrayList arrayList = new ArrayList();
        byte[] bArr = BleUUID.SET_AUTO_SPO2_TIME_INTERVAL;
        if (!this.g) {
            this.f = 0;
        }
        byte[] bArr2 = {(byte) isEnabled(), (byte) getStartHour(), (byte) getStartMinute(), (byte) getEndHour(), (byte) getEndMinute(), (byte) getTimeInterval()};
        byte[] bArr3 = new byte[bArr.length + 6];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, 6);
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
        return CommandNames.SET_TIME_INTERVAL_FOR_SPO2;
    }

    public int getEndHour() {
        return this.j;
    }

    public int getEndMinute() {
        return this.k;
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
        return this.h;
    }

    public int getStartMinute() {
        return this.i;
    }

    public int getTimeInterval() {
        return this.f;
    }

    public int isEnabled() {
        return this.g ? 1 : 0;
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
