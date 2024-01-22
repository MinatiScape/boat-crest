package com.google.crypto.tink.aead.subtle;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.subtle.AesGcmJce;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
@Immutable
/* loaded from: classes10.dex */
public final class AesGcmFactory implements AeadFactory {

    /* renamed from: a  reason: collision with root package name */
    public final int f10837a;

    public AesGcmFactory(int i) throws GeneralSecurityException {
        this.f10837a = a(i);
    }

    public static int a(int i) throws InvalidAlgorithmParameterException {
        if (i == 16 || i == 32) {
            return i;
        }
        throw new InvalidAlgorithmParameterException(String.format("Invalid AES key size, expected 16 or 32, but got %d", Integer.valueOf(i)));
    }

    @Override // com.google.crypto.tink.aead.subtle.AeadFactory
    public Aead createAead(byte[] bArr) throws GeneralSecurityException {
        if (bArr.length == getKeySizeInBytes()) {
            return new AesGcmJce(bArr);
        }
        throw new GeneralSecurityException(String.format("Symmetric key has incorrect length; expected %s, but got %s", Integer.valueOf(getKeySizeInBytes()), Integer.valueOf(bArr.length)));
    }

    @Override // com.google.crypto.tink.aead.subtle.AeadFactory
    public int getKeySizeInBytes() {
        return this.f10837a;
    }
}
