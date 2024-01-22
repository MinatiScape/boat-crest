package com.coveiot.sdk.ble.api.request;

import com.coveiot.sdk.ble.api.MultiPacketRequestGenerator;
import com.coveiot.sdk.ble.api.model.ContactData;
import com.coveiot.sdk.ble.api.model.ExpectedDataSize;
import com.coveiot.sdk.ble.utils.CommandNames;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes9.dex */
public class SetPhoneBookReq extends BaseRequest {
    public List<ContactData> f;

    public SetPhoneBookReq(Object obj, List<ContactData> list) {
        super(obj);
        this.f = list;
    }

    public final byte[] a() {
        int i;
        ArrayList arrayList = new ArrayList();
        Iterator<ContactData> it = this.f.iterator();
        int i2 = -1;
        int i3 = -1;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            ContactData next = it.next();
            byte[] bytes = next.getName().getBytes(StandardCharsets.UTF_8);
            byte[] bytes2 = next.getMobileNumber().replace(HexStringBuilder.DEFAULT_SEPARATOR, "").getBytes(StandardCharsets.UTF_8);
            if (bytes.length >= 20) {
                bytes = Arrays.copyOfRange(bytes, 0, 20);
            }
            for (byte b : bytes) {
                i3++;
                arrayList.add(i3, Byte.valueOf(b));
            }
            int i4 = i3 + 1;
            arrayList.add(i4, (byte) 0);
            if (bytes2.length >= 20) {
                bytes2 = Arrays.copyOfRange(bytes2, 0, 20);
            }
            for (byte b2 : bytes2) {
                i4++;
                arrayList.add(i4, Byte.valueOf(b2));
            }
            i3 = i4 + 1;
            arrayList.add(i3, (byte) 0);
        }
        byte[] bArr = new byte[arrayList.size()];
        for (i = 0; i < arrayList.size(); i++) {
            i2++;
            bArr[i2] = ((Byte) arrayList.get(i)).byteValue();
        }
        return bArr;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        ArrayList<CommandBytes> generateSinglePacketRequest;
        byte[] a2 = a();
        byte[] array = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(this.f.size()).array();
        int length = a().length;
        byte[] bArr = {array[0]};
        if (length + 4 > 150) {
            generateSinglePacketRequest = MultiPacketRequestGenerator.generateRequest((byte) 0, (byte) -88, a2, bArr, false, false);
        } else {
            generateSinglePacketRequest = MultiPacketRequestGenerator.generateSinglePacketRequest((byte) 0, (byte) -88, a2, bArr, false);
        }
        this.f7474a = new ExpectedDataSize(generateSinglePacketRequest.size());
        return generateSinglePacketRequest;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.SET_PHONE_BOOK;
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
