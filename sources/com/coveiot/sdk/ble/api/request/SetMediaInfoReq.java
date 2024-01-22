package com.coveiot.sdk.ble.api.request;

import com.coveiot.sdk.ble.utils.BleUtils;
import com.coveiot.sdk.ble.utils.CommandNames;
import com.coveiot.utils.utility.AppUtils;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/* loaded from: classes9.dex */
public class SetMediaInfoReq extends BaseRequest {
    public String f;
    public String g;
    public String h;

    public SetMediaInfoReq(Object obj, String str, String str2) {
        super(obj);
        this.f = str;
        this.g = str2;
    }

    public final CommandBytes a() {
        CommandBytes commandBytes = new CommandBytes();
        if (!AppUtils.isEmpty(this.h)) {
            byte[] bytes = this.h.getBytes(StandardCharsets.UTF_16LE);
            int i = 4;
            int length = bytes.length + 4;
            if (bytes.length > 60) {
                bytes = Arrays.copyOfRange(bytes, 0, 60);
            }
            byte[] bArr = new byte[bytes.length + 4];
            bArr[0] = 2;
            bArr[1] = -118;
            bArr[2] = (byte) length;
            bArr[3] = 0;
            for (byte b : bytes) {
                bArr[i] = b;
                i++;
            }
            commandBytes.setCommandData(bArr);
        }
        return commandBytes;
    }

    public final CommandBytes b() {
        CommandBytes commandBytes = new CommandBytes();
        int i = 4;
        int length = this.g.getBytes(StandardCharsets.UTF_16LE).length + 4;
        byte[] bArr = new byte[length];
        bArr[0] = 2;
        bArr[1] = -117;
        bArr[2] = (byte) length;
        bArr[3] = 0;
        for (int i2 = 0; i2 < this.g.getBytes(StandardCharsets.UTF_16LE).length; i2++) {
            bArr[i] = this.g.getBytes(StandardCharsets.UTF_16LE)[i2];
            i++;
        }
        commandBytes.setCommandData(bArr);
        return commandBytes;
    }

    public final CommandBytes c() {
        CommandBytes commandBytes = new CommandBytes();
        byte[] bytes = this.f.getBytes(StandardCharsets.UTF_16LE);
        if (bytes.length > 60) {
            bytes = Arrays.copyOfRange(bytes, 0, 60);
        }
        int i = 4;
        int length = bytes.length + 4;
        byte[] bArr = new byte[length];
        bArr[0] = 2;
        bArr[1] = -117;
        bArr[2] = (byte) length;
        bArr[3] = 0;
        for (byte b : bytes) {
            bArr[i] = b;
            i++;
        }
        commandBytes.setCommandData(bArr);
        return commandBytes;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(c());
        if (!BleUtils.isEmpty(this.h)) {
            arrayList.add(a());
        }
        if (!BleUtils.isEmpty(this.g)) {
            arrayList.add(b());
        }
        return arrayList;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.SEND_MEDIA_INFO;
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

    public void setArtist(String str) {
        this.h = str;
    }
}
