package com.coveiot.sdk.ble.api.request;

import com.coveiot.sdk.ble.utils.CommandNames;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class SetCurrentWatchFaceReq extends BaseRequest {
    public int f;

    public SetCurrentWatchFaceReq(Object obj, int i) {
        super(obj);
        this.f = i;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        ArrayList arrayList = new ArrayList();
        byte b = (byte) this.f;
        byte[] bArr = new byte[6];
        System.arraycopy(new byte[]{2, -113, 6, 0}, 0, bArr, 0, 4);
        System.arraycopy(new byte[]{b, (byte) (b << 8)}, 0, bArr, 4, 2);
        CommandBytes commandBytes = new CommandBytes();
        commandBytes.setCommandData(bArr);
        arrayList.add(commandBytes);
        return arrayList;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.GET_CURRENT_WATCH_FACE;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattCharacteristicToRead() {
        return null;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public String getGattServiceToRead() {
        return null;
    }

    public int getWatchFaceId() {
        return this.f;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean isMultiPacket() {
        return false;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean isPriority() {
        return false;
    }

    public void setWatchFaceId(int i) {
        this.f = i;
    }
}
