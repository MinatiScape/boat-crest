package com.realsil.sdk.bbpro.core.transportlayer;

import com.realsil.sdk.core.logger.ZLogger;
import com.realsil.sdk.core.protocol.BasePacket;
import com.realsil.sdk.core.utility.DataConverter;
/* loaded from: classes12.dex */
public class TransportLayerPacket extends BasePacket {
    public static final int HEADER_LENGTH = 4;
    public static final byte SYNC_WORD = -86;

    /* renamed from: a  reason: collision with root package name */
    public byte f13540a = 0;
    public byte b = 0;
    public int c = 0;
    public byte[] d = null;
    public int e = -1;
    public byte[] f = null;

    public static TransportLayerPacket builderPacket(byte[] bArr) {
        TransportLayerPacket transportLayerPacket = new TransportLayerPacket();
        if (transportLayerPacket.parse(bArr)) {
            return transportLayerPacket;
        }
        return null;
    }

    public static byte[] encode(int i, short s, byte[] bArr) {
        int length = bArr != null ? bArr.length : 0;
        byte[] bArr2 = new byte[length + 2];
        bArr2[0] = (byte) (s & 255);
        bArr2[1] = (byte) ((s >> 8) & 255);
        if (length > 0) {
            System.arraycopy(bArr, 0, bArr2, 2, length);
        }
        return encode(i, bArr2);
    }

    public static byte[] encodePayload(short s, byte[] bArr) {
        int length = bArr != null ? bArr.length : 0;
        byte[] bArr2 = new byte[length + 2];
        bArr2[0] = (byte) (s & 255);
        bArr2[1] = (byte) ((s >> 8) & 255);
        if (length > 0) {
            System.arraycopy(bArr, 0, bArr2, 2, length);
        }
        return bArr2;
    }

    public int getOpcode() {
        return this.e;
    }

    public int getPacketLength() {
        return this.c + 4;
    }

    public byte[] getParameters() {
        return this.f;
    }

    public byte[] getPayload() {
        return this.d;
    }

    public byte getSeqNum() {
        return this.b;
    }

    public boolean parse(byte[] bArr) {
        if (bArr != null && bArr.length >= 4) {
            byte b = bArr[0];
            this.f13540a = b;
            this.b = bArr[1];
            int i = ((bArr[3] << 8) | (bArr[2] & 255)) & 65535;
            this.c = i;
            if (b != -86) {
                ZLogger.w(String.format("LT_SYNCWORD_ERROR: %s", DataConverter.bytes2Hex(bArr)));
                return false;
            } else if (i < 2) {
                ZLogger.w(String.format("LT_PAYLOAD_OPCODE_LENGTH_ERROR: %s", DataConverter.bytes2Hex(bArr)));
                return false;
            } else {
                byte[] bArr2 = new byte[i];
                this.d = bArr2;
                System.arraycopy(bArr, 4, bArr2, 0, i);
                byte[] bArr3 = this.d;
                this.e = ((bArr3[0] & 255) | (bArr3[1] << 8)) & 65535;
                int length = bArr.length;
                int i2 = this.c;
                if (length < i2 + 4) {
                    ZLogger.w(String.format("LT_PAYLOAD_LENGTH_ERROR: %s", DataConverter.bytes2Hex(bArr)));
                    return false;
                }
                int i3 = i2 - 2;
                byte[] bArr4 = new byte[i3];
                this.f = bArr4;
                System.arraycopy(bArr, 6, bArr4, 0, i3);
                return true;
            }
        }
        ZLogger.w("LT_LENGTH_ERROR");
        return false;
    }

    public static byte[] encode(int i, byte[] bArr) {
        int length = bArr != null ? bArr.length : 0;
        byte[] bArr2 = new byte[length + 4];
        bArr2[0] = -86;
        bArr2[1] = (byte) i;
        bArr2[2] = (byte) (length & 255);
        bArr2[3] = (byte) ((length >> 8) & 255);
        if (length > 0) {
            System.arraycopy(bArr, 0, bArr2, 4, length);
        }
        return bArr2;
    }
}
