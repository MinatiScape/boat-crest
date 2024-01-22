package com.google.crypto.tink.subtle;

import com.google.crypto.tink.PublicKeyVerify;
import java.security.GeneralSecurityException;
/* loaded from: classes10.dex */
public final class Ed25519Verify implements PublicKeyVerify {
    public static final int PUBLIC_KEY_LEN = 32;
    public static final int SIGNATURE_LEN = 64;

    /* renamed from: a  reason: collision with root package name */
    public final ImmutableByteArray f11031a;

    public Ed25519Verify(byte[] bArr) {
        if (bArr.length == 32) {
            this.f11031a = ImmutableByteArray.of(bArr);
            return;
        }
        throw new IllegalArgumentException(String.format("Given public key's length is not %s.", 32));
    }

    @Override // com.google.crypto.tink.PublicKeyVerify
    public void verify(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr.length == 64) {
            if (!f.z(bArr2, bArr, this.f11031a.getBytes())) {
                throw new GeneralSecurityException("Signature check failed.");
            }
            return;
        }
        throw new GeneralSecurityException(String.format("The length of the signature is not %s.", 64));
    }
}
