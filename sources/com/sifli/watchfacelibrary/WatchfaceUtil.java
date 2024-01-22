package com.sifli.watchfacelibrary;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.UShort;
/* loaded from: classes12.dex */
public class WatchfaceUtil {

    /* renamed from: a  reason: collision with root package name */
    public static final ByteOrder f13708a = ByteOrder.LITTLE_ENDIAN;

    public static byte[] addIntToByteArray(int i, byte[] bArr, int i2) {
        System.arraycopy(ByteBuffer.allocate(4).order(f13708a).putInt(i).array(), 0, bArr, i2, 4);
        return bArr;
    }

    public static byte[] addShortToByteArray(int i, byte[] bArr, int i2) {
        System.arraycopy(ByteBuffer.allocate(2).order(f13708a).putShort((short) i).array(), 0, bArr, i2, 2);
        return bArr;
    }

    public static int getIntFromByteArray(byte[] bArr, int i) {
        byte[] bArr2 = new byte[4];
        System.arraycopy(bArr, i, bArr2, 0, 4);
        return ByteBuffer.wrap(bArr2, 0, 4).order(f13708a).getInt();
    }

    public static int getShortFromByteArray(byte[] bArr, int i) {
        byte[] bArr2 = new byte[2];
        System.arraycopy(bArr, i, bArr2, 0, 2);
        return ByteBuffer.wrap(bArr2, 0, 2).order(f13708a).getShort();
    }

    public static int getUnsignedShortFromByteArray(byte[] bArr, int i) {
        byte[] bArr2 = new byte[2];
        System.arraycopy(bArr, i, bArr2, 0, 2);
        return ByteBuffer.wrap(bArr2, 0, 2).order(f13708a).getShort() & UShort.MAX_VALUE;
    }
}
