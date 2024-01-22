package com.google.crypto.tink.subtle;

import com.google.crypto.tink.Mac;
import com.google.crypto.tink.prf.Prf;
import com.google.errorprone.annotations.Immutable;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
@Immutable
/* loaded from: classes10.dex */
public class PrfMac implements Mac {

    /* renamed from: a  reason: collision with root package name */
    public final Prf f11040a;
    public final int b;

    public PrfMac(Prf prf, int i) throws GeneralSecurityException {
        this.f11040a = prf;
        this.b = i;
        if (i >= 10) {
            prf.compute(new byte[0], i);
            return;
        }
        throw new InvalidAlgorithmParameterException("tag size too small, need at least 10 bytes");
    }

    @Override // com.google.crypto.tink.Mac
    public byte[] computeMac(byte[] bArr) throws GeneralSecurityException {
        return this.f11040a.compute(bArr, this.b);
    }

    @Override // com.google.crypto.tink.Mac
    public void verifyMac(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (!Bytes.equal(computeMac(bArr2), bArr)) {
            throw new GeneralSecurityException("invalid MAC");
        }
    }
}
