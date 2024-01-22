package com.touchgui.sdk.jni;
/* loaded from: classes12.dex */
public class JNITools {
    static {
        System.loadLibrary("touchsdk");
    }

    public static native byte[] lz4_compress(byte[] bArr, int i);

    public static native byte[] unzip16(byte[] bArr, int i);

    public static native byte[] zipBmp16(byte[] bArr, int i);
}
