package com.coveiot.sdk.ble.api.request;

import com.coveiot.sdk.ble.utils.CommandNames;
import com.coveiot.utils.utility.LogHelper;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class FindMyWatchReq extends BaseRequest {
    public Event f;
    public int g;

    /* loaded from: classes9.dex */
    public enum Event {
        START,
        STOP
    }

    public FindMyWatchReq(Object obj, Event event, Integer num) {
        super(obj);
        this.f = event;
        if (num.intValue() > 120) {
            this.g = 120;
            LogHelper.d(getClass().getSimpleName(), "noOfTimes is >120 so setting to 120");
            return;
        }
        this.g = num.intValue();
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        ArrayList arrayList = new ArrayList();
        byte[] bArr = {2, -91, 6, 0, this.f == Event.STOP ? (byte) 2 : (byte) 1, (byte) this.g};
        CommandBytes commandBytes = new CommandBytes();
        commandBytes.setCommandData(bArr);
        arrayList.add(commandBytes);
        return arrayList;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.FIND_MY_WATCH;
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
        return false;
    }
}
