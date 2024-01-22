package com.sifli.ezip;
/* loaded from: classes12.dex */
public class sifliEzipUtil {
    static {
        System.loadLibrary("ezip");
    }

    public static native byte[] png2EzipWithTypeNDK(byte[] bArr, long j, String str, int i, int i2, int i3);

    public static byte[] pngToEzip(byte[] bArr, String str, int i, int i2, int i3) {
        return png2EzipWithTypeNDK(bArr, bArr.length, str, i, i2, i3);
    }
}
