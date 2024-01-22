package com.realsil.sdk.bbpro.core.transportlayer;

import com.realsil.sdk.core.protocol.BasePacket;
import com.realsil.sdk.core.utility.DataConverter;
/* loaded from: classes12.dex */
public class EventPacket extends BasePacket {

    /* renamed from: a  reason: collision with root package name */
    public int f13534a;
    public byte[] mEventParams;
    public int paramsLen = 0;

    public static EventPacket builderPacket(byte[] bArr) {
        EventPacket eventPacket = new EventPacket();
        if (eventPacket.parse(bArr)) {
            return eventPacket;
        }
        return null;
    }

    public int getEventId() {
        return this.f13534a;
    }

    public byte[] getEventParams() {
        return this.mEventParams;
    }

    public boolean parse(byte[] bArr) {
        if (bArr == null || bArr.length < 2) {
            return false;
        }
        this.f13534a = ((bArr[1] << 8) | (bArr[0] & 255)) & 65535;
        int length = bArr.length - 2;
        this.paramsLen = length;
        if (length > 0) {
            byte[] bArr2 = new byte[length];
            this.mEventParams = bArr2;
            System.arraycopy(bArr, 2, bArr2, 0, length);
        } else {
            this.mEventParams = null;
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("mEventId=" + this.f13534a);
        sb.append("params: " + DataConverter.bytes2Hex(this.mEventParams));
        return sb.toString();
    }
}
