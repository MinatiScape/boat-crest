package com.google.android.gms.internal.fido;

import java.io.IOException;
/* loaded from: classes7.dex */
public abstract class zzbf {

    /* renamed from: a  reason: collision with root package name */
    public static final zzbf f8637a;

    static {
        new n("base64()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", '=');
        new n("base64Url()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_", '=');
        new o("base32()", "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567", '=');
        new o("base32Hex()", "0123456789ABCDEFGHIJKLMNOPQRSTUV", '=');
        f8637a = new m("base16()", "0123456789ABCDEF");
    }

    public static zzbf zzd() {
        return f8637a;
    }

    public abstract void a(Appendable appendable, byte[] bArr, int i, int i2) throws IOException;

    public abstract int b(int i);

    public final String zze(byte[] bArr, int i, int i2) {
        zzam.zze(0, i2, bArr.length);
        StringBuilder sb = new StringBuilder(b(i2));
        try {
            a(sb, bArr, 0, i2);
            return sb.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
