package com.coveiot.sdk.ble.api.request;

import com.coveiot.sdk.ble.api.BleUUID;
import com.coveiot.sdk.ble.api.model.TemperatureUnit;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class SetWeatherConfigInfoReq extends HybridCommandRequest {
    public TemperatureUnit h;
    public boolean i;
    public byte[] j;

    public SetWeatherConfigInfoReq(Object obj, short s, TemperatureUnit temperatureUnit) {
        super(obj, s);
        this.i = true;
        this.j = new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        this.h = temperatureUnit;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        ArrayList arrayList = new ArrayList();
        byte[] bArr = BleUUID.SET_WEATHER_CONFIG_INFO;
        ArrayList arrayList2 = new ArrayList();
        if (this.h == TemperatureUnit.CELSIUS) {
            arrayList2.add((byte) 0);
        } else {
            arrayList2.add((byte) 1);
        }
        if (this.i) {
            arrayList2.add((byte) 1);
        } else {
            arrayList2.add((byte) 0);
        }
        int i = 0;
        while (true) {
            byte[] bArr2 = this.j;
            if (i >= bArr2.length) {
                break;
            }
            arrayList2.add(Byte.valueOf(bArr2[i]));
            i++;
        }
        setPayload(arrayList2);
        ArrayList arrayList3 = new ArrayList();
        for (byte b : bArr) {
            arrayList3.add(Byte.valueOf(b));
        }
        arrayList3.addAll(super.getPayload());
        byte[] bArr3 = new byte[arrayList3.size()];
        for (int i2 = 0; i2 < arrayList3.size(); i2++) {
            bArr3[i2] = ((Byte) arrayList3.get(i2)).byteValue();
        }
        CommandBytes commandBytes = new CommandBytes();
        commandBytes.setCommandData(bArr3);
        arrayList.add(commandBytes);
        return arrayList;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.SET_WEATHER_CONFIG_INFO;
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

    public SetWeatherConfigInfoReq(Object obj, short s, TemperatureUnit temperatureUnit, boolean z) {
        super(obj, s);
        this.i = true;
        this.j = new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        this.h = temperatureUnit;
        this.i = z;
    }
}
