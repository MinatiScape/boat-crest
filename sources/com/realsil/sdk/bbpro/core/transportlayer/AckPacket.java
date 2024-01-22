package com.realsil.sdk.bbpro.core.transportlayer;

import com.realsil.sdk.core.protocol.BasePacket;
/* loaded from: classes12.dex */
public class AckPacket extends BasePacket {
    public static final byte ACK_STATUS_BUSY = 4;
    public static final byte ACK_STATUS_COMPLETE = 0;
    public static final byte ACK_STATUS_DISALLOW = 1;
    public static final byte ACK_STATUS_PARAMETERS_ERROR = 3;
    public static final byte ACK_STATUS_PROCESS_FAIL = 5;
    public static final byte ACK_STATUS_UNKNOWN_COMMAND = 2;

    /* renamed from: a  reason: collision with root package name */
    public int f13532a;
    public byte b;

    public AckPacket() {
        this.f13532a = 0;
        this.b = (byte) 0;
        this.f13532a = 0;
        this.b = (byte) 0;
    }

    public static AckPacket builder(byte[] bArr) {
        AckPacket ackPacket = new AckPacket();
        if (ackPacket.parse(bArr)) {
            return ackPacket;
        }
        return null;
    }

    public static byte[] encode(int i, byte b) {
        return new byte[]{0, 0, (byte) (i & 255), (byte) ((i >> 8) & 255), b};
    }

    public byte getStatus() {
        return this.b;
    }

    public int getToAckId() {
        return this.f13532a;
    }

    public boolean parse(byte[] bArr) {
        if (bArr == null || bArr.length < 2) {
            return false;
        }
        this.f13532a = ((bArr[0] & 255) | (bArr[1] << 8)) & 65535;
        this.b = (byte) (bArr[2] & 255);
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("\tmToAckId=0x%04x", Integer.valueOf(this.f13532a)));
        sb.append("\tmStatus=" + ((int) this.b));
        return sb.toString();
    }
}
