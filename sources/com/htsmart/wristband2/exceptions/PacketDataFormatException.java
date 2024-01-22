package com.htsmart.wristband2.exceptions;

import com.htsmart.wristband2.packet.PacketData;
import com.htsmart.wristband2.utils.BytesUtil;
/* loaded from: classes11.dex */
public class PacketDataFormatException extends WristbandException {

    /* renamed from: a  reason: collision with root package name */
    private String f12021a;
    private PacketData b;

    public PacketDataFormatException(String str, PacketData packetData) {
        this.f12021a = str;
        this.b = packetData;
    }

    public String getFunction() {
        return this.f12021a;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        StringBuilder sb;
        String str;
        if (this.b.getKeyData() == null) {
            sb = new StringBuilder();
            sb.append(this.f12021a);
            str = " [ null ]";
        } else {
            sb = new StringBuilder();
            sb.append(this.f12021a);
            sb.append(" [");
            sb.append(BytesUtil.bytes2HexStr(this.b.getKeyData()));
            str = "]";
        }
        sb.append(str);
        return sb.toString();
    }

    public PacketData getPacketData() {
        return this.b;
    }

    public void setFunction(String str) {
        this.f12021a = str;
    }

    public void setPacketData(PacketData packetData) {
        this.b = packetData;
    }
}
