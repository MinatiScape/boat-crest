package com.coveiot.sdk.ble.api.request;

import com.coveiot.sdk.ble.api.MultiPacketRequestGenerator;
import com.coveiot.sdk.ble.api.model.ExpectedDataSize;
import com.coveiot.sdk.ble.utils.BleFileUtils;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class WatchFaceUploadReq extends BaseRequest {
    public File f;
    public int g;

    public WatchFaceUploadReq(Object obj, File file, int i) {
        super(obj);
        this.f = file;
        this.g = i;
        this.noOfPacketsAtaTime = 50;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        byte[] array = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(this.g).array();
        ArrayList<CommandBytes> generateRequest = MultiPacketRequestGenerator.generateRequest((byte) 2, (byte) -114, BleFileUtils.convertFileToByteArray(this.f), new byte[]{array[0], array[1]}, true, true);
        this.f7474a = new ExpectedDataSize(generateRequest.size());
        return generateRequest;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.WATCH_FACE;
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

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean shouldWaitForRes() {
        return false;
    }
}
