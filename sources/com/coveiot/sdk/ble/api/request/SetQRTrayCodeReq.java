package com.coveiot.sdk.ble.api.request;

import com.coveiot.sdk.ble.api.MultiPacketRequestGenerator;
import com.coveiot.sdk.ble.api.model.ExpectedDataSize;
import com.coveiot.sdk.ble.model.QRCodeData;
import com.coveiot.sdk.ble.utils.CommandNames;
import com.coveiot.utils.utility.LogHelper;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import com.google.gson.Gson;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes9.dex */
public class SetQRTrayCodeReq extends BaseRequest {
    public ArrayList<QRCodeData> f;

    public SetQRTrayCodeReq(Object obj, ArrayList<QRCodeData> arrayList) {
        super(obj);
        this.f = arrayList;
        LogHelper.d("qrCheck", "SetQRTrayCodeReq: " + new Gson().toJson(this.f));
    }

    public final byte[] a() {
        int i;
        ArrayList arrayList = new ArrayList();
        arrayList.add(Byte.valueOf((byte) this.f.size()));
        for (int i2 = 0; i2 < this.f.size(); i2++) {
            LogHelper.d("qrCheck", "imageId: " + this.f.get(i2).getImageId());
            byte[] array = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(this.f.get(i2).getImageId()).array();
            arrayList.add(Byte.valueOf(array[0]));
            arrayList.add(Byte.valueOf(array[1]));
            byte[] bytes = this.f.get(i2).getImageTag().getBytes(StandardCharsets.UTF_8);
            if (bytes.length >= 30) {
                bytes = Arrays.copyOfRange(bytes, 0, 30);
            }
            if (this.f.get(i2).getImageTitle() != null) {
                byte[] bytes2 = this.f.get(i2).getImageTitle().getBytes(StandardCharsets.UTF_8);
                if (bytes2.length >= 40) {
                    bytes2 = Arrays.copyOfRange(bytes, 0, 40);
                }
                i = bytes2.length;
            } else {
                i = 0;
            }
            arrayList.add(Byte.valueOf((byte) (((byte) bytes.length) + 2 + i)));
            for (byte b : bytes) {
                arrayList.add(Byte.valueOf(b));
            }
            for (byte b2 : ": ".getBytes(StandardCharsets.UTF_8)) {
                arrayList.add(Byte.valueOf(b2));
            }
            if (this.f.get(i2).getImageTitle() != null) {
                byte[] bytes3 = this.f.get(i2).getImageTitle().getBytes(StandardCharsets.UTF_8);
                if (bytes3.length >= 40) {
                    bytes3 = Arrays.copyOfRange(bytes, 0, 40);
                }
                for (byte b3 : bytes3) {
                    arrayList.add(Byte.valueOf(b3));
                }
            }
            byte[] bytes4 = HexStringBuilder.DEFAULT_SEPARATOR.getBytes(StandardCharsets.UTF_8);
            arrayList.add(Byte.valueOf((byte) bytes4.length));
            for (byte b4 : bytes4) {
                arrayList.add(Byte.valueOf(b4));
            }
        }
        byte[] bArr = new byte[arrayList.size()];
        int i3 = -1;
        for (int i4 = 0; i4 < arrayList.size(); i4++) {
            i3++;
            bArr[i3] = ((Byte) arrayList.get(i4)).byteValue();
        }
        LogHelper.d("qrCheck", "finalByteArray: " + new Gson().toJson(arrayList));
        return bArr;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        ArrayList<CommandBytes> generateSinglePacketRequest;
        byte[] a2 = a();
        byte[] bArr = {65};
        if (a2.length + 4 > 150) {
            generateSinglePacketRequest = MultiPacketRequestGenerator.generateRequest((byte) 2, (byte) -87, a2, bArr, false, false);
        } else {
            generateSinglePacketRequest = MultiPacketRequestGenerator.generateSinglePacketRequest((byte) 2, (byte) -87, a2, bArr, false);
        }
        this.f7474a = new ExpectedDataSize(generateSinglePacketRequest.size());
        return generateSinglePacketRequest;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.SET_QR_TRAY_CODE;
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
        return true;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean isPriority() {
        return true;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean shouldSendAllPacketsAtOnce() {
        return true;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean shouldWaitForRes() {
        return true;
    }
}
