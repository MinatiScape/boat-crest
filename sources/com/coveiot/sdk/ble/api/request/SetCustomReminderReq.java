package com.coveiot.sdk.ble.api.request;

import com.coveiot.sdk.ble.api.MultiPacketRequestGenerator;
import com.coveiot.sdk.ble.api.model.CustomReminder;
import com.coveiot.sdk.ble.utils.CommandNames;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/* loaded from: classes9.dex */
public class SetCustomReminderReq extends HybridCommandRequest {
    public ArrayList<CustomReminder> h;

    public SetCustomReminderReq(Object obj, short s, ArrayList<CustomReminder> arrayList) {
        super(obj, s);
        this.h = arrayList;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public List<CommandBytes> getCommandBytes() {
        Iterator<CustomReminder> it = this.h.iterator();
        int i = 0;
        while (it.hasNext()) {
            i += it.next().getDataBytes().length;
        }
        int i2 = i + 2;
        byte[] bArr = new byte[i2];
        bArr[0] = (byte) this.deploy;
        bArr[1] = (byte) this.h.size();
        Iterator<CustomReminder> it2 = this.h.iterator();
        int i3 = 2;
        while (it2.hasNext()) {
            CustomReminder next = it2.next();
            for (int i4 = 0; i4 < next.getDataBytes().length; i4++) {
                bArr[i3] = next.getDataBytes()[i4];
                i3++;
            }
        }
        if (i2 + 4 > 150) {
            return MultiPacketRequestGenerator.generateRequest((byte) 2, (byte) -102, bArr, new byte[0], false, false);
        }
        return MultiPacketRequestGenerator.generateSinglePacketRequest((byte) 2, (byte) -102, bArr, new byte[0], false);
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public CommandNames getCommandName() {
        return CommandNames.SET_CUSTOM_REMINDERS;
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
    public boolean shouldSendAllPacketsAtOnce() {
        return true;
    }

    @Override // com.coveiot.sdk.ble.api.request.BaseRequest
    public boolean shouldWaitForRes() {
        return true;
    }
}
