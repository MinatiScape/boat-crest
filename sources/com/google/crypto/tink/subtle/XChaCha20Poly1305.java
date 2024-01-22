package com.google.crypto.tink.subtle;

import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
/* loaded from: classes10.dex */
public final class XChaCha20Poly1305 extends d {
    public XChaCha20Poly1305(byte[] bArr) throws InvalidKeyException {
        super(bArr);
    }

    @Override // com.google.crypto.tink.subtle.d, com.google.crypto.tink.Aead
    public /* bridge */ /* synthetic */ byte[] decrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        return super.decrypt(bArr, bArr2);
    }

    @Override // com.google.crypto.tink.subtle.d
    public c e(byte[] bArr, int i) throws InvalidKeyException {
        return new p(bArr, i);
    }

    @Override // com.google.crypto.tink.subtle.d, com.google.crypto.tink.Aead
    public /* bridge */ /* synthetic */ byte[] encrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        return super.encrypt(bArr, bArr2);
    }
}
