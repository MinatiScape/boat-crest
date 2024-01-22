package com.coveiot.sdk.ble.api.request;

import com.coveiot.sdk.ble.utils.CommandNames;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes9.dex */
public class SendBTCallInfoReq extends HybridCommandRequest {
    public short h;
    public String i;
    public String j;

    public SendBTCallInfoReq(Object obj, short s, short s2, String str, String str2) {
        super(obj, s);
        this.h = (short) 0;
        this.h = s2;
        this.i = str;
        this.j = str2;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(Byte.valueOf((byte) this.h));
        String str = this.i;
        if (str != null && !str.isEmpty()) {
            byte[] bytes = this.i.replace(HexStringBuilder.DEFAULT_SEPARATOR, "").getBytes(StandardCharsets.UTF_8);
            if (bytes.length >= 20) {
                bytes = Arrays.copyOfRange(bytes, 0, 20);
            }
            for (byte b : bytes) {
                arrayList2.add(Byte.valueOf(b));
            }
            arrayList2.add((byte) 0);
        }
        String str2 = this.j;
        if (str2 != null && !str2.isEmpty()) {
            byte[] bytes2 = this.j.getBytes(StandardCharsets.UTF_8);
            if (bytes2.length >= 20) {
                bytes2 = Arrays.copyOfRange(bytes2, 0, 20);
            }
            for (byte b2 : bytes2) {
                arrayList2.add(Byte.valueOf(b2));
            }
            arrayList2.add((byte) 0);
        }
        setPayload(arrayList2);
        List<Byte> payload = super.getPayload();
        ArrayList arrayList3 = new ArrayList();
        arrayList3.add((byte) 0);
        arrayList3.add((byte) -84);
        byte[] array = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(payload.size() + 4).array();
        arrayList3.add(Byte.valueOf(array[0]));
        arrayList3.add(Byte.valueOf(array[1]));
        arrayList3.addAll(payload);
        byte[] bArr = new byte[arrayList3.size()];
        for (int i = 0; i < arrayList3.size(); i++) {
            bArr[i] = ((Byte) arrayList3.get(i)).byteValue();
        }
        CommandBytes commandBytes = new CommandBytes();
        commandBytes.setCommandData(bArr);
        arrayList.add(commandBytes);
        return arrayList;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.SEND_BT_CALL_INFO;
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
