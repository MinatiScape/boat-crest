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
public class SendFileReq extends BaseRequest {
    public int f;
    public File g;

    public SendFileReq(Object obj, int i, File file) {
        super(obj);
        this.f = i;
        this.g = file;
        this.noOfPacketsAtaTime = 50;
    }

    public static short crc16(byte[] bArr) {
        int i = 0;
        for (byte b : bArr) {
            int i2 = (((i << 8) | (i >>> 8)) & 65535) ^ (b & 255);
            int i3 = i2 ^ ((i2 & 255) >> 4);
            int i4 = i3 ^ ((i3 << 12) & 65535);
            i = i4 ^ (65535 & ((i4 & 255) << 5));
        }
        return (short) (i & 65535);
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        try {
            byte[] convertFileToByteArray = BleFileUtils.convertFileToByteArray(this.g);
            byte[] array = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(this.f).array();
            ArrayList<CommandBytes> generateRequest = MultiPacketRequestGenerator.generateRequest((byte) 6, (byte) -123, convertFileToByteArray, new byte[]{array[0], array[1]}, true, false);
            this.f7474a = new ExpectedDataSize(generateRequest.size());
            return generateRequest;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.SEND_FILE;
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
