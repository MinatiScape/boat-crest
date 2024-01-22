package com.coveiot.sdk.ble.api.request;

import com.coveiot.sdk.ble.api.BleUUID;
import com.coveiot.sdk.ble.api.MultiPacketRequestGenerator;
import com.coveiot.sdk.ble.api.model.BleQuickReplyModel;
import com.coveiot.sdk.ble.api.model.ExpectedDataSize;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes9.dex */
public class SetQuickReplyReq extends BaseRequest {
    public boolean f;
    public List<BleQuickReplyModel> g;

    public SetQuickReplyReq(Object obj, boolean z, List<BleQuickReplyModel> list) {
        super(obj);
        this.f = z;
        this.g = list;
    }

    public final byte[] a() {
        int i;
        ArrayList arrayList = new ArrayList();
        Iterator<BleQuickReplyModel> it = this.g.iterator();
        int i2 = -1;
        int i3 = -1;
        while (true) {
            i = 0;
            if (!it.hasNext()) {
                break;
            }
            byte[] bytes = it.next().getReplyText().getBytes(StandardCharsets.UTF_16LE);
            if (bytes.length >= 120) {
                bytes = Arrays.copyOfRange(bytes, 0, 120);
            }
            i3++;
            arrayList.add(i3, Byte.valueOf((byte) bytes.length));
            while (i < bytes.length) {
                i3++;
                arrayList.add(i3, Byte.valueOf(bytes[i]));
                i++;
            }
        }
        byte[] bArr = new byte[arrayList.size()];
        while (i < arrayList.size()) {
            i2++;
            bArr[i2] = ((Byte) arrayList.get(i)).byteValue();
            i++;
        }
        return bArr;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        ArrayList<CommandBytes> generateSinglePacketRequest;
        byte[] a2 = a();
        ByteBuffer allocate = ByteBuffer.allocate(4);
        ByteOrder byteOrder = ByteOrder.LITTLE_ENDIAN;
        byte[] array = allocate.order(byteOrder).putInt(this.f ? 1 : 0).array();
        byte[] array2 = ByteBuffer.allocate(4).order(byteOrder).putInt(this.g.size()).array();
        int length = a().length;
        byte[] bArr = {array[0], array2[0]};
        if (length + 4 > 150) {
            generateSinglePacketRequest = MultiPacketRequestGenerator.generateRequest((byte) 2, BleUUID.CMD_ID_97, a2, bArr, false, false);
        } else {
            generateSinglePacketRequest = MultiPacketRequestGenerator.generateSinglePacketRequest((byte) 2, BleUUID.CMD_ID_97, a2, bArr, false);
        }
        this.f7474a = new ExpectedDataSize(generateSinglePacketRequest.size());
        return generateSinglePacketRequest;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.SET_QUICK_REPLY;
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
