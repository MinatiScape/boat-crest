package com.coveiot.sdk.ble.api.request;

import androidx.annotation.NonNull;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class RawEcgMeasurementReq extends BaseRequest {
    public boolean f;

    /* loaded from: classes9.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public boolean f7509a;
        public Object b;

        public Builder(boolean z) {
            this.f7509a = z;
        }

        public RawEcgMeasurementReq build() {
            RawEcgMeasurementReq rawEcgMeasurementReq = new RawEcgMeasurementReq(this.b);
            rawEcgMeasurementReq.f = this.f7509a;
            return rawEcgMeasurementReq;
        }

        public Builder setId(Object obj) {
            this.b = obj;
            return this;
        }
    }

    public RawEcgMeasurementReq(Object obj) {
        super(obj);
    }

    @NonNull
    public final List<CommandBytes> a() {
        ArrayList arrayList = new ArrayList();
        byte[] bArr = {1, -124, 5, 0};
        byte[] bArr2 = new byte[1];
        bArr2[0] = (byte) (isStart() ? 1 : 2);
        byte[] bArr3 = new byte[5];
        System.arraycopy(bArr, 0, bArr3, 0, 4);
        System.arraycopy(bArr2, 0, bArr3, 4, 1);
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
        return CommandNames.GET_ECG_RAW_DATA;
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

    public boolean isStart() {
        return this.f;
    }
}
