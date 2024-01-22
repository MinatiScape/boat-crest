package com.sifli.siflidfu;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.UShort;
/* loaded from: classes12.dex */
public class Utils {

    /* renamed from: a  reason: collision with root package name */
    public static final ByteOrder f13703a = ByteOrder.LITTLE_ENDIAN;

    public static void addIntToByteArray(int i, byte[] bArr, int i2) {
        System.arraycopy(ByteBuffer.allocate(4).order(f13703a).putInt(i).array(), 0, bArr, i2, 4);
    }

    public static void addShortToByteArray(int i, byte[] bArr, int i2) {
        System.arraycopy(ByteBuffer.allocate(2).order(f13703a).putShort((short) i).array(), 0, bArr, i2, 2);
    }

    public static int getIntFromByteArray(byte[] bArr, int i) {
        byte[] bArr2 = new byte[4];
        System.arraycopy(bArr, i, bArr2, 0, 4);
        return ByteBuffer.wrap(bArr2, 0, 4).order(f13703a).getInt();
    }

    public static int getUnsignedShortFromByteArray(byte[] bArr, int i) {
        byte[] bArr2 = new byte[2];
        System.arraycopy(bArr, i, bArr2, 0, 2);
        return ByteBuffer.wrap(bArr2, 0, 2).order(f13703a).getShort() & UShort.MAX_VALUE;
    }
}
