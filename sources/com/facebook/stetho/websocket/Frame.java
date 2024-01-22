package com.facebook.stetho.websocket;

import com.crrepa.c.a;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import okhttp3.internal.ws.WebSocketProtocol;
/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes9.dex */
public class Frame {
    public static final byte OPCODE_BINARY_FRAME = 2;
    public static final byte OPCODE_CONNECTION_CLOSE = 8;
    public static final byte OPCODE_CONNECTION_PING = 9;
    public static final byte OPCODE_CONNECTION_PONG = 10;
    public static final byte OPCODE_TEXT_FRAME = 1;
    public boolean fin;
    public boolean hasMask;
    public byte[] maskingKey;
    public byte opcode;
    public byte[] payloadData;
    public long payloadLen;
    public boolean rsv1;
    public boolean rsv2;
    public boolean rsv3;

    private void decodeFirstByte(byte b) {
        this.fin = (b & 128) != 0;
        this.rsv1 = (b & 64) != 0;
        this.rsv2 = (b & 32) != 0;
        this.rsv3 = (b & 16) != 0;
        this.opcode = (byte) (b & 15);
    }

    private long decodeLength(byte b, InputStream inputStream) throws IOException {
        if (b <= 125) {
            return b;
        }
        if (b == 126) {
            return ((readByteOrThrow(inputStream) & 255) << 8) | (readByteOrThrow(inputStream) & 255);
        }
        if (b != Byte.MAX_VALUE) {
            throw new IOException("Unexpected length byte: " + ((int) b));
        }
        long j = 0;
        for (int i = 0; i < 8; i++) {
            j = (j << 8) | (readByteOrThrow(inputStream) & 255);
        }
        return j;
    }

    private static byte[] decodeMaskingKey(InputStream inputStream) throws IOException {
        byte[] bArr = new byte[4];
        readBytesOrThrow(inputStream, bArr, 0, 4);
        return bArr;
    }

    private byte encodeFirstByte() {
        byte b = this.fin ? (byte) 128 : (byte) 0;
        if (this.rsv1) {
            b = (byte) (b | 64);
        }
        if (this.rsv2) {
            b = (byte) (b | 32);
        }
        if (this.rsv3) {
            b = (byte) (b | 16);
        }
        return (byte) (b | (this.opcode & 15));
    }

    private static byte[] encodeLength(long j) {
        return j <= 125 ? new byte[]{(byte) j} : j <= WebSocketProtocol.PAYLOAD_SHORT_MAX ? new byte[]{a.l1, (byte) ((j >> 8) & 255), (byte) (j & 255)} : new byte[]{Byte.MAX_VALUE, (byte) ((j >> 56) & 255), (byte) ((j >> 48) & 255), (byte) ((j >> 40) & 255), (byte) ((j >> 32) & 255), (byte) ((j >> 24) & 255), (byte) ((j >> 16) & 255), (byte) ((j >> 8) & 255), (byte) (j & 255)};
    }

    private static byte readByteOrThrow(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        if (read != -1) {
            return (byte) read;
        }
        throw new EOFException();
    }

    private static void readBytesOrThrow(InputStream inputStream, byte[] bArr, int i, int i2) throws IOException {
        while (i2 > 0) {
            int read = inputStream.read(bArr, i, i2);
            if (read == -1) {
                throw new EOFException();
            }
            i2 -= read;
            i += read;
        }
    }

    public void readFrom(BufferedInputStream bufferedInputStream) throws IOException {
        decodeFirstByte(readByteOrThrow(bufferedInputStream));
        byte readByteOrThrow = readByteOrThrow(bufferedInputStream);
        this.hasMask = (readByteOrThrow & 128) != 0;
        this.payloadLen = decodeLength((byte) (readByteOrThrow & (-129)), bufferedInputStream);
        this.maskingKey = this.hasMask ? decodeMaskingKey(bufferedInputStream) : null;
        long j = this.payloadLen;
        byte[] bArr = new byte[(int) j];
        this.payloadData = bArr;
        readBytesOrThrow(bufferedInputStream, bArr, 0, (int) j);
        MaskingHelper.unmask(this.maskingKey, this.payloadData, 0, (int) this.payloadLen);
    }

    public void writeTo(BufferedOutputStream bufferedOutputStream) throws IOException {
        bufferedOutputStream.write(encodeFirstByte());
        byte[] encodeLength = encodeLength(this.payloadLen);
        if (this.hasMask) {
            encodeLength[0] = (byte) (encodeLength[0] | 128);
        }
        bufferedOutputStream.write(encodeLength, 0, encodeLength.length);
        if (!this.hasMask) {
            bufferedOutputStream.write(this.payloadData, 0, (int) this.payloadLen);
            return;
        }
        throw new UnsupportedOperationException("Writing masked data not implemented");
    }
}
