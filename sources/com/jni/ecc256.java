package com.jni;
/* loaded from: classes11.dex */
public class ecc256 {
    static {
        System.loadLibrary("ecc256");
    }

    public native void ecc_generate_dhkey(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4);

    public native void ecc_generate_public_key(byte[] bArr, byte[] bArr2, byte[] bArr3);

    public native boolean ecc_private_key_is_valid(byte[] bArr);
}
