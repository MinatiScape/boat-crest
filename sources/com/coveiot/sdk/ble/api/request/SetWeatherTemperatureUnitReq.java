package com.coveiot.sdk.ble.api.request;

import com.coveiot.sdk.ble.api.BleUUID;
import com.coveiot.sdk.ble.api.model.TemperatureUnit;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class SetWeatherTemperatureUnitReq extends HybridCommandRequest {
    public TemperatureUnit h;

    public SetWeatherTemperatureUnitReq(Object obj, short s, TemperatureUnit temperatureUnit) {
        super(obj, s);
        this.h = temperatureUnit;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        ArrayList arrayList = new ArrayList();
        byte[] bArr = BleUUID.SET_WEATHER_TEMPERATURE_UNIT;
        ArrayList arrayList2 = new ArrayList();
        if (this.h == TemperatureUnit.CELSIUS) {
            arrayList2.add((byte) 0);
        } else {
            arrayList2.add((byte) 1);
        }
        setPayload(arrayList2);
        ArrayList arrayList3 = new ArrayList();
        for (byte b : bArr) {
            arrayList3.add(Byte.valueOf(b));
        }
        arrayList3.addAll(super.getPayload());
        byte[] bArr2 = new byte[arrayList3.size()];
        for (int i = 0; i < arrayList3.size(); i++) {
            bArr2[i] = ((Byte) arrayList3.get(i)).byteValue();
        }
        CommandBytes commandBytes = new CommandBytes();
        commandBytes.setCommandData(bArr2);
        arrayList.add(commandBytes);
        return arrayList;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.SET_WEATHER_UNIT;
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
}
