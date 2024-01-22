package com.coveiot.sdk.ble.api.request;

import com.coveiot.sdk.ble.api.model.MusicPlayBackState;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class SetMusicPlayBackStatusReq extends BaseRequest {
    public MusicPlayBackState f;

    public SetMusicPlayBackStatusReq(Object obj, MusicPlayBackState musicPlayBackState) {
        super(obj);
        this.f = musicPlayBackState;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        ArrayList arrayList = new ArrayList();
        byte[] bArr = {2, -127, 5, 0, this.f == MusicPlayBackState.PLAY ? (byte) 1 : (byte) 2};
        CommandBytes commandBytes = new CommandBytes();
        commandBytes.setCommandData(bArr);
        arrayList.add(commandBytes);
        return arrayList;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.SET_MUSIC_PLAYBACK_STATUS;
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
