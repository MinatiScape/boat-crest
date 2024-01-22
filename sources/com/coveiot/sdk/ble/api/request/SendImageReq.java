package com.coveiot.sdk.ble.api.request;

import com.coveiot.sdk.ble.api.MultiPacketRequestGenerator;
import com.coveiot.sdk.ble.api.model.ExpectedDataSize;
import com.coveiot.sdk.ble.utils.BleFileUtils;
import com.coveiot.sdk.ble.utils.CommandNames;
import com.coveiot.sdk.ble.utils.DevicePlatformEnum;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
/* loaded from: classes9.dex */
public class SendImageReq extends BaseRequest {
    public int f;
    public File g;
    public int h;
    public int i;
    public int j;
    public int k;
    public DevicePlatformEnum l;

    public SendImageReq(Object obj, int i, File file, int i2, int i3, int i4, int i5, int i6, int i7, DevicePlatformEnum devicePlatformEnum) {
        super(obj);
        this.f = i;
        this.g = file;
        this.h = i2;
        this.i = i3;
        this.j = i6;
        this.k = i7;
        this.l = devicePlatformEnum;
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
            byte[] imageBuffer = BleFileUtils.getImageBuffer(this.g, this.h == 1, this.i == 1, this.l);
            ByteBuffer allocate = ByteBuffer.allocate(4);
            ByteOrder byteOrder = ByteOrder.LITTLE_ENDIAN;
            byte[] array = allocate.order(byteOrder).putInt(this.f).array();
            byte[] array2 = ByteBuffer.allocate(4).order(byteOrder).putInt(this.j).array();
            byte[] array3 = ByteBuffer.allocate(4).order(byteOrder).putInt(this.k).array();
            ArrayList<CommandBytes> generateRequest = MultiPacketRequestGenerator.generateRequest((byte) 2, (byte) -108, imageBuffer, new byte[]{array[0], array[1], (byte) this.h, (byte) this.i, array2[0], array2[1], array3[0], array3[1], (byte) crc16(imageBuffer)}, true, false);
            this.f7474a = new ExpectedDataSize(generateRequest.size());
            return generateRequest;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.SEND_IMAGE;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public DevicePlatformEnum getDevicePlatformEnum() {
        return this.l;
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
