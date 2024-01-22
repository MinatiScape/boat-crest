package com.google.crypto.tink.subtle;

import com.google.crypto.tink.HybridDecrypt;
import com.google.crypto.tink.subtle.EllipticCurves;
import java.security.GeneralSecurityException;
import java.security.interfaces.ECPrivateKey;
import java.util.Arrays;
/* loaded from: classes10.dex */
public final class EciesAeadHkdfHybridDecrypt implements HybridDecrypt {
    public static final byte[] g = new byte[0];

    /* renamed from: a  reason: collision with root package name */
    public final ECPrivateKey f11024a;
    public final EciesHkdfRecipientKem b;
    public final String c;
    public final byte[] d;
    public final EllipticCurves.PointFormatType e;
    public final EciesAeadHkdfDemHelper f;

    public EciesAeadHkdfHybridDecrypt(ECPrivateKey eCPrivateKey, byte[] bArr, String str, EllipticCurves.PointFormatType pointFormatType, EciesAeadHkdfDemHelper eciesAeadHkdfDemHelper) throws GeneralSecurityException {
        this.f11024a = eCPrivateKey;
        this.b = new EciesHkdfRecipientKem(eCPrivateKey);
        this.d = bArr;
        this.c = str;
        this.e = pointFormatType;
        this.f = eciesAeadHkdfDemHelper;
    }

    @Override // com.google.crypto.tink.HybridDecrypt
    public byte[] decrypt(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        int encodingSizeInBytes = EllipticCurves.encodingSizeInBytes(this.f11024a.getParams().getCurve(), this.e);
        if (bArr.length >= encodingSizeInBytes) {
            return this.f.getAead(this.b.generateKey(Arrays.copyOfRange(bArr, 0, encodingSizeInBytes), this.c, this.d, bArr2, this.f.getSymmetricKeySizeInBytes(), this.e)).decrypt(Arrays.copyOfRange(bArr, encodingSizeInBytes, bArr.length), g);
        }
        throw new GeneralSecurityException("ciphertext too short");
    }
}
