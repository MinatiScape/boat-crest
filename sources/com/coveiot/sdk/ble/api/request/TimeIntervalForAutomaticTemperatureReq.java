package com.coveiot.sdk.ble.api.request;

import androidx.annotation.NonNull;
import com.coveiot.sdk.ble.api.BleUUID;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class TimeIntervalForAutomaticTemperatureReq extends BaseRequest {
    public int f;
    public boolean g;

    /* loaded from: classes9.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public int f7544a;
        public boolean b;

        public Builder(int i, boolean z) {
            this.f7544a = i;
            this.b = z;
        }

        public TimeIntervalForAutomaticTemperatureReq build() {
            return new TimeIntervalForAutomaticTemperatureReq(this.f7544a, this.b);
        }
    }

    public TimeIntervalForAutomaticTemperatureReq(int i, boolean z) {
        super(Integer.valueOf(i));
        this.f = i;
        this.g = z;
    }

    @NonNull
    public final List<CommandBytes> a() {
        ArrayList arrayList = new ArrayList();
        byte[] bArr = BleUUID.SET_AUTO_TEMPERATURE_TIME_INTERVAL;
        if (!this.g) {
            this.f = 0;
        }
        byte[] bArr2 = {(byte) getTimeInterval()};
        byte[] bArr3 = new byte[bArr.length + 1];
        System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bArr.length, 1);
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
        return CommandNames.SET_TIME_INTERVAL_FOR_AUTOMATIC_TEMPERATURE;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattCharacteristicToRead() {
        return null;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattServiceToRead() {
        return null;
    }

    public int getTimeInterval() {
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
