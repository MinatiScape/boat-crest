package com.google.crypto.tink.subtle;

import com.google.crypto.tink.subtle.EllipticCurves;
import java.security.GeneralSecurityException;
import java.security.interfaces.ECPrivateKey;
/* loaded from: classes10.dex */
public final class EciesHkdfRecipientKem {

    /* renamed from: a  reason: collision with root package name */
    public ECPrivateKey f11026a;

    public EciesHkdfRecipientKem(ECPrivateKey eCPrivateKey) {
        this.f11026a = eCPrivateKey;
    }

    public byte[] generateKey(byte[] bArr, String str, byte[] bArr2, byte[] bArr3, int i, EllipticCurves.PointFormatType pointFormatType) throws GeneralSecurityException {
        return Hkdf.computeEciesHkdfSymmetricKey(bArr, EllipticCurves.computeSharedSecret(this.f11026a, EllipticCurves.getEcPublicKey(this.f11026a.getParams(), pointFormatType, bArr)), str, bArr2, bArr3, i);
    }
}
