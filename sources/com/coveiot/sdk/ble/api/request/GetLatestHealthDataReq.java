package com.coveiot.sdk.ble.api.request;

import com.coveiot.sdk.ble.model.HealthDataType;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class GetLatestHealthDataReq extends BaseRequest {
    public HealthDataType f;

    public GetLatestHealthDataReq(Object obj, HealthDataType healthDataType) {
        super(obj);
        this.f = healthDataType;
    }

    public final byte a() {
        HealthDataType healthDataType = this.f;
        if (healthDataType == HealthDataType.HEART_RATE) {
            return (byte) 0;
        }
        if (healthDataType == HealthDataType.SPO2) {
            return (byte) 1;
        }
        if (healthDataType == HealthDataType.TEMPERATURE) {
            return (byte) 2;
        }
        return healthDataType == HealthDataType.BP ? (byte) 3 : (byte) 0;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        byte a2 = a();
        ArrayList arrayList = new ArrayList();
        byte[] bArr = {1, 10, 5, 0, a2};
        CommandBytes commandBytes = new CommandBytes();
        commandBytes.setCommandData(bArr);
        arrayList.add(commandBytes);
        return arrayList;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.GET_LATEST_HEALTH_DATA;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattCharacteristicToRead() {
        return null;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattServiceToRead() {
        return null;
    }

    public HealthDataType getHealthDataType() {
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
