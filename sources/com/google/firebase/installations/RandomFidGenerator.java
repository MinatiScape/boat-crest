package com.google.firebase.installations;

import android.util.Base64;
import androidx.annotation.NonNull;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.UUID;
/* loaded from: classes10.dex */
public class RandomFidGenerator {

    /* renamed from: a  reason: collision with root package name */
    public static final byte f11307a = Byte.parseByte("01110000", 2);
    public static final byte b = Byte.parseByte("00001111", 2);

    public static String a(byte[] bArr) {
        return new String(Base64.encode(bArr, 11), Charset.defaultCharset()).substring(0, 22);
    }

    public static byte[] b(UUID uuid, byte[] bArr) {
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.putLong(uuid.getMostSignificantBits());
        wrap.putLong(uuid.getLeastSignificantBits());
        return wrap.array();
    }

    @NonNull
    public String createRandomFid() {
        byte[] b2 = b(UUID.randomUUID(), new byte[17]);
        b2[16] = b2[0];
        b2[0] = (byte) ((b & b2[0]) | f11307a);
        return a(b2);
    }
}
