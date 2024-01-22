package com.coveiot.sdk.ble.api.request;

import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/* loaded from: classes9.dex */
public class GetDistanceDataReq extends BaseRequest {
    public Date f;

    public GetDistanceDataReq(Object obj, Date date) {
        super(obj);
        this.f = date;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        ArrayList arrayList = new ArrayList();
        try {
            byte[] bArr = {1, 45, 5, 0, (byte) BleUtils.findDateDifference(this.f, new Date())};
            CommandBytes commandBytes = new CommandBytes();
            commandBytes.setCommandData(bArr);
            arrayList.add(commandBytes);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.GET_DISTANCE;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattCharacteristicToRead() {
        return null;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattServiceToRead() {
        return null;
    }

    public Date getmDate() {
        return this.f;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean isMultiPacket() {
        return true;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean isPriority() {
        return false;
    }
}
